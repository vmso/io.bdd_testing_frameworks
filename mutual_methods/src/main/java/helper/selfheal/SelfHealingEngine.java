package helper.selfheal;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.openai.models.ChatModel;
import configuration.Configuration;
import helper.selfheal.ai.AiLocatorClient;
import helper.selfheal.ai.AiLocatorSuggester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
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
 * Minimal self-healing MVP: on locator failure, capture screenshot & DOM snapshot,
 * generate candidates (AI + heuristics), and try to recover.
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
        Path outDir = prepareOutDir(trace);

        // --- Fast path reuse ---
        Optional<By> fastPath = tryReuseWinner(driver, original, shadowMode, trace);
        if (fastPath.isPresent()) {
            return new HealingResult(fastPath, trace);
        }

        // --- capture evidence ---
        File screenshotBase64 = null;
        try {
            if (driver instanceof TakesScreenshot ts) {
                screenshotBase64 = ts.getScreenshotAs(OutputType.FILE);
            }
        } catch (Exception e) {
            trace.add("screenshot capture failed: " + e.getMessage());
        }
        String dom = captureDomSnapshot(driver, trace);
        writeText(outDir.resolve("dom.txt"), dom);

        // --- Generate candidates (AI + heuristics) ---
        List<By> candidates = generateCandidates(original, stepText, dom, aiEnabled, driver, trace, screenshotBase64);

        // --- Try all candidates ---
        Optional<By> winner = tryCandidates(driver, original, candidates, trace);

        // --- log decision ---
        logHealingResult(outDir, original, winner, candidates, trace);

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

    // ----------------- Candidate generation -----------------

    private List<By> generateCandidates(
            By original,
            String stepText,
            String dom,
            boolean aiEnabled,
            WebDriver driver,
            List<String> trace,
            File screenshotBase64
    ) {
        Set<By> candidateSet = new LinkedHashSet<>();

        // AI candidates
        if (aiEnabled) {
            try {
                String domSnippet = dom.length() > 20_000 ? dom.substring(0, 20_000) : dom;
                AiLocatorClient aiClient = new AiLocatorClient(ChatModel.GPT_4_1);
                aiClient.suggest(original.toString(), stepText, domSnippet, driver.getCurrentUrl(), screenshotBase64)
                        .ifPresent(aiRes -> {
                            var aiCands = AiLocatorSuggester.toByCandidates(aiRes, original.toString());
                            trace.add("AI candidates: " + aiCands);
                            candidateSet.addAll(aiCands);
                        });
            } catch (Exception e) {
                trace.add("AI suggest error: " + e.getMessage());
            }
        } else {
            trace.add("AI not enabled — using heuristic candidates only");
        }

        // Heuristic candidates
        candidateSet.addAll(generateHeuristicCandidates(original, stepText, trace));

        return new ArrayList<>(candidateSet);
    }

    private List<By> generateHeuristicCandidates(By original, String stepText, List<String> trace) {
        List<By> list = new ArrayList<>();
        trace.add("heuristic: from original=" + original);

        if (stepText != null && !stepText.isBlank()) {
            String txt = stepText.trim();
            list.add(By.xpath("//*[normalize-space(.)='" + escapeXPath(txt) + "']"));
            list.add(By.xpath("//*[contains(normalize-space(.), '" + escapeXPath(txt) + "')]"));

            String[] attrs = {"data-testid", "data-qa", "aria-label", "title", "name"};
            for (String a : attrs) {
                list.add(By.cssSelector("*[" + a + "~='" + cssEscape(stepText) + "']"));
                list.add(By.cssSelector("*[" + a + "*='" + cssEscape(stepText) + "']"));
            }
        }

        list.add(original); // always last fallback
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    // ----------------- Candidate validation -----------------

    private Optional<By> tryCandidates(WebDriver driver, By original, List<By> candidates, List<String> trace) {
        Duration originalWait = driver.manage().timeouts().getImplicitWaitTimeout();

        try {
            for (By by : candidates) {
                try {
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
                    driver.findElement(by);
                    trace.add("candidate found: " + by);
                    try {
                        ModelStore.saveWinner(driver.getCurrentUrl(), original, by);
                    } catch (Exception ignored) {}
                    return Optional.of(by);
                } catch (NoSuchElementException ignore) {
                    trace.add("candidate not found: " + by);
                }
            }
        } finally {
            driver.manage().timeouts().implicitlyWait(originalWait);
        }
        return Optional.empty();
    }

    private Optional<By> tryReuseWinner(WebDriver driver, By original, boolean shadowMode, List<String> trace) {
        Duration originalWait = driver.manage().timeouts().getImplicitWaitTimeout();

        try {
            String url = driver.getCurrentUrl();
            var fast = ModelStore.getWinner(url, original);
            if (fast.isPresent()) {
                try {
                    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10));
                    driver.findElement(fast.get());
                    trace.add("fast-path winner reused: " + fast.get());
                    if (shadowMode) return Optional.empty();
                    return fast;
                } catch (NoSuchElementException ignore) {
                    trace.add("fast-path winner stale; falling back");
                }
            }
        } catch (Exception e) {
            trace.add("fast-path error: " + e.getMessage());
        } finally {
            driver.manage().timeouts().implicitlyWait(originalWait);
        }
        return Optional.empty();
    }

    // ----------------- Evidence helpers -----------------

    private Path prepareOutDir(List<String> trace) {
        String ts = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS").format(LocalDateTime.now());
        Path outDir = Path.of("reports", "self-heal", ts);
        try {
            Files.createDirectories(outDir);
        } catch (IOException ignored) {}
        return outDir;
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

    private void writeText(Path path, String content) {
        try {
            Files.createDirectories(path.getParent());
            Files.writeString(path, content == null ? "" : content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ignored) {}
    }

    // ----------------- Utility -----------------

    private String escapeXPath(String s) {
        return s.replace("'", "\"\"");
    }

    private String cssEscape(String s) {
        if (s == null) return "";
        return s.replace("'", "\\'").replace("\"", "\\\"");
    }

    private void logHealingResult(Path outDir, By original, Optional<By> winner,
                                  List<By> candidates, List<String> trace) {
        try {
            // 1. Human-readable decision log
            writeText(outDir.resolve("decision.log"), String.join(System.lineSeparator(), trace));

            // 2. Structured JSON log
            Map<String, Object> data = new LinkedHashMap<>();
            data.put("originalLocator", original.toString());
            data.put("winner", winner.map(Object::toString).orElse("none"));
            data.put("candidates", candidates.stream().map(Object::toString).toList());
            data.put("trace", trace);
            data.put("timestamp", LocalDateTime.now().toString());
            ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
            String json = mapper.writeValueAsString(data);

            TelemetryLogger.writeJson(outDir,"healing.json", json);

        } catch (Exception e) {
            log.warn("Failed to log healing result", e);
        }
    }
}
