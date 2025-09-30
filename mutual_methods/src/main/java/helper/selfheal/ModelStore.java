package helper.selfheal;

import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Simple file-based store for successful alternates per page+original locator key.
 */
public class ModelStore {

    private static final Path STORE = Path.of("reports", "self-heal", "winners.db");

    public static void saveWinner(String pageUrl, By original, By winner) {
        try {
            Files.createDirectories(STORE.getParent());
            String line = key(pageUrl, original) + " => " + winner.toString() + System.lineSeparator();
            Files.writeString(STORE, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException ignored) {}
    }

    public static Optional<By> getWinner(String pageUrl, By original) {
        try {
            if (!Files.exists(STORE)) return Optional.empty();
            var lines = Files.readAllLines(STORE);
            String k = key(pageUrl, original);
            for (int i = lines.size() - 1; i >= 0; i--) { // prefer latest
                String line = lines.get(i);
                if (line.startsWith(k + " => ")) {
                    String rhs = line.substring((k + " => ").length()).trim();
                    By by = parseBy(rhs);
                    if (by != null) return Optional.of(by);
                }
            }
        } catch (IOException ignored) {}
        return Optional.empty();
    }

    private static String key(String url, By original) {
        String page = url == null ? "" : url.split("[#?]")[0];
        return page + " | " + original.toString();
    }

    private static By parseBy(String s) {
        try {
            // Basic parsing for By.cssSelector, By.xpath, By.id, By.name
            if (s.startsWith("By.cssSelector: ")) {
                return By.cssSelector(s.substring("By.cssSelector: ".length()));
            }
            if (s.startsWith("By.xpath: ")) {
                return By.xpath(s.substring("By.xpath: ".length()));
            }
            if (s.startsWith("By.id: ")) {
                return By.id(s.substring("By.id: ".length()));
            }
            if (s.startsWith("By.name: ")) {
                return By.name(s.substring("By.name: ".length()));
            }
        } catch (Exception ignored) {}
        return null;
    }
}


