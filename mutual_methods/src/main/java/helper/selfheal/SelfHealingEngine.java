package helper.selfheal;

import com.openai.models.ChatModel;
import configuration.Configuration;
import helper.selfheal.ai.AiLocatorClient;
import helper.selfheal.ai.AiLocatorSuggester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Minimal self-healing MVP: on first locator failure, capture screenshot and a compact DOM snapshot,
 * generate a few heuristic candidates and optionally act in shadow-mode (observe only).
 */
public class SelfHealingEngine {

    private static final Logger log = LogManager.getLogger(SelfHealingEngine.class);

    public static class HealingResult {
        public final Optional<By> winner;
        public final List<String> logs;
        public HealingResult(Optional<By> winner, List<String> logs) {
            this.winner = winner;
            this.logs = logs;
        }
    }

    public HealingResult onFailure(WebDriver driver, By original, String action, String stepText) {
        boolean enabled = Boolean.parseBoolean(Configuration.getInstance().getStringValueOfProp("selfheal.enabled"));
        boolean shadowMode = Boolean.parseBoolean(Configuration.getInstance().getStringValueOfProp("selfheal.shadow_mode"));
        boolean aiEnabled = Boolean.parseBoolean(Configuration.getInstance().getStringValueOfProp("selfheal.ai.enabled"));

        if (!enabled) {
            return new HealingResult(Optional.empty(), List.of("self-heal disabled"));
        }


        List<String> trace = new ArrayList<>();
        String ts = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS").format(LocalDateTime.now());
        Path outDir = Path.of("reports", "self-heal", ts);
        try {
            Files.createDirectories(outDir);
        } catch (IOException ignored) {}

        // --- Fast path reuse ---
        try {
            String url = driver.getCurrentUrl();
            var fast = ModelStore.getWinner(url, original);
            if (fast.isPresent()) {
                try {
                    driver.findElement(fast.get());
                    trace.add("fast-path winner reused: " + fast.get());
                    if (shadowMode) return new HealingResult(Optional.empty(), trace);
                    return new HealingResult(fast, trace);
                } catch (org.openqa.selenium.NoSuchElementException ignore) {
                    trace.add("fast-path winner stale; falling back");
                }
            }
        } catch (Exception e) {
            trace.add("fast-path error: " + e.getMessage());
        }

        // --- capture evidence ---
        captureScreenshot(driver, outDir, trace);
        String dom = captureDomSnapshot(driver, trace);
        writeText(outDir.resolve("dom.txt"), dom);

        Set<By> candidateSet = new LinkedHashSet<>();

        // --- AI candidates ---
        if (aiEnabled) {
            try {
                String domSnippet = dom.length() > 20_000 ? dom.substring(0, 20_000) : dom;
                AiLocatorClient aiClient = new AiLocatorClient(ChatModel.GPT_4_1);
                aiClient.suggest(original.toString(), stepText, domSnippet, driver.getCurrentUrl())
                        .ifPresent(aiRes -> {
                            var aiCands = AiLocatorSuggester.toByCandidates(aiRes, original.toString());
                            trace.add("AI candidates: " + aiCands);
                            candidateSet.addAll(aiCands);   // ✅ add to set
                        });
            } catch (Exception e) {
                trace.add("AI suggest error: " + e.getMessage());
            }
        }

        // --- Heuristic candidates ---
        List<By> candidates = new ArrayList<>(candidateSet);

        if (OcrAndVisualHelper.isEnabled()) {
            trace.add("OCR enabled (stub) - visual alignment would run here");
        }

        // --- Try all candidates ---
        Optional<By> winner = Optional.empty();
        for (By by : candidates) {
            var currentImplicitWait = driver.manage().timeouts().getImplicitWaitTimeout();
            try {
                driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
                driver.findElement(by);
                winner = Optional.of(by);
                trace.add("candidate found: " + by);
                // Persist winner
                try {
                    ModelStore.saveWinner(driver.getCurrentUrl(), original, by);
                } catch (Exception ignored) {}
                driver.manage().timeouts().implicitlyWait(currentImplicitWait);
                break;
            } catch (org.openqa.selenium.NoSuchElementException ignore) {
                trace.add("candidate not found: " + by);
                driver.manage().timeouts().implicitlyWait(currentImplicitWait);
            }
        }

        // --- log decision ---
        writeText(outDir.resolve("decision.log"), String.join(System.lineSeparator(), trace));

        if (winner.isPresent()) {
            if (shadowMode) {
                log.info("Self-heal (shadow-mode) would use: {} for action {}", winner.get(), action);
                return new HealingResult(Optional.empty(), trace);
            } else {
                log.info("Self-heal will use: {} for action {}", winner.get(), action);
                return new HealingResult(winner, trace);
            }
        }

        return new HealingResult(Optional.empty(), trace);
    }


    private void captureScreenshot(WebDriver driver, Path outDir, List<String> trace) {
        try {
            if (driver instanceof TakesScreenshot ts) {
                File src = ts.getScreenshotAs(OutputType.FILE);
                File dest = outDir.resolve("page.png").toFile();
                FileHandler.createDir(dest.getParentFile());
                FileHandler.copy(src, dest);
                trace.add("screenshot saved: " + dest.getAbsolutePath());
            } else {
                trace.add("driver not screenshot-capable");
            }
        } catch (Exception e) {
            trace.add("screenshot error: " + e.getMessage());
        }
    }

    private String captureDomSnapshot(WebDriver driver, List<String> trace) {
        try {
            if (driver instanceof JavascriptExecutor js) {
                Object html = js.executeScript("return document.documentElement.outerHTML;");
                trace.add("captured outerHTML");
                return String.valueOf(html);
            }
        } catch (Exception e) {
            trace.add("dom snapshot error: " + e.getMessage());
        }
        return "";
    }

    private List<By> generateHeuristicCandidates(By original, String stepText, List<String> trace) {
        List<By> list = new ArrayList<>();
        trace.add("original: " + original);
        // Keep original as last retry
        // Simple text-based contains if stepText exists
        if (stepText != null && !stepText.isBlank()) {
            String txt = stepText.trim();
            list.add(By.xpath("//*[normalize-space(.)='" + escapeXPath(txt) + "']"));
            list.add(By.xpath("//*[contains(normalize-space(.), '" + escapeXPath(txt) + "')]"));
        }
        // Common data-* and aria attributes
        String[] attrs = {"data-testid", "data-qa", "aria-label", "title", "name"};
        for (String a : attrs) {
            list.add(By.cssSelector("*[" + a + "~='" + cssEscape(stepText) + "']"));
            list.add(By.cssSelector("*[" + a + "*='" + cssEscape(stepText) + "']"));
        }
        // Fallback: original at the end
        list.add(original);
        // De-duplicate
        LinkedHashSet<By> set = new LinkedHashSet<>(list);
        List<By> deduped = new ArrayList<>(set);
        if (OcrAndVisualHelper.isEnabled() && stepText != null && !stepText.isBlank()) {
            deduped.sort((a, b) -> Double.compare(score(b, stepText), score(a, stepText)));
        }
        return deduped;
    }

    private void writeText(Path path, String content) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, content == null ? "" : content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ignored) {}
    }

    private String escapeXPath(String s) {
        return s.replace("'", "\"\"");
    }

    private String cssEscape(String s) {
        if (s == null) return "";
        return s.replace("'", "\\'").replace("\"", "\\\"");
    }

    private double score(By by, String stepText) {
        double base = 0.0;
        String s = by.toString().toLowerCase(Locale.ROOT);
        if (s.contains("contains(normalize-space") || s.contains("normalize-space(.)='")) {
            base += 0.1;
        }
        base += OcrAndVisualHelper.visualBoost(stepText);
        return base;
    }
}


