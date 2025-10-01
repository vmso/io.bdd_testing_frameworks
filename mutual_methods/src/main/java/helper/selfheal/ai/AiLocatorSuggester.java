package helper.selfheal.ai;

import org.openqa.selenium.By;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AiLocatorSuggester {

    private static final Pattern XPATH_TAG_PATTERN = Pattern.compile("^//\\s*([a-zA-Z][a-zA-Z0-9_-]*)\\b.*");

    /**
     * Convert AI result into By candidates.
     *
     * @param res             the AI result with correctedTexts/xpaths/cssSelectors
     * @param originalLocator original locator string (used to extract tag)
     * @return list of By candidates, de-duplicated
     */
    public static List<By> toByCandidates(AiLocatorClient.AiLocatorResult res, String originalLocator) {
        Set<By> out = new LinkedHashSet<>();

        String tag = extractTag(originalLocator); // e.g., "legend" from //legend[text()='…']

        // 1) Direct XPaths from the model
        for (String xp : safeList(res.xpaths)) {
            addIfValidXPath(out, xp);
        }

        // 2) Build exact/fuzzy XPaths from correctedTexts
        for (String txt : safeList(res.correctedTexts)) {
            if (txt == null || txt.isBlank()) continue;
            String literal = xpathLiteral(txt.trim());
            if (tag != null) {
                out.add(By.xpath("//" + tag + "[normalize-space(.)=" + literal + "]"));
            }
            out.add(By.xpath("//*[normalize-space(.)=" + literal + "]"));
            out.add(By.xpath("//*[contains(normalize-space(.), " + literal + ")]"));
        }

        // 3) CSS selectors suggested by AI
        for (String css : safeList(res.cssSelectors)) {
            addIfValidCss(out, css);
        }

        return new ArrayList<>(out);
    }

    // --- helpers ---

    private static String extractTag(String originalXPath) {
        if (originalXPath == null) return null;
        Matcher m = XPATH_TAG_PATTERN.matcher(originalXPath.trim());
        return m.find() ? m.group(1) : null;
    }

    /** Safely escape an XPath string literal */
    public static String xpathLiteral(String s) {
        if (!s.contains("'")) return "'" + s + "'";
        if (!s.contains("\"")) return "\"" + s + "\"";
        // both quotes present → concat
        String[] parts = s.split("\"", -1);
        StringBuilder concat = new StringBuilder("concat(");
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) concat.append(", '\"', ");
            concat.append("'").append(parts[i].replace("'", "''")).append("'");
        }
        concat.append(")");
        return concat.toString();
    }

    private static List<String> safeList(List<String> in) {
        return in == null ? List.of() : in;
    }

    private static void addIfValidXPath(Set<By> out, String xp) {
        if (xp == null || xp.isBlank()) return;
        String trimmed = xp.trim();
        if (trimmed.startsWith("//") || trimmed.startsWith("(")) {
            out.add(By.xpath(trimmed));
        }
    }

    private static void addIfValidCss(Set<By> out, String css) {
        if (css == null || css.isBlank()) return;
        out.add(By.cssSelector(css.trim()));
    }
}
