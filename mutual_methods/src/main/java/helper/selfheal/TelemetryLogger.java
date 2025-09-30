package helper.selfheal;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class TelemetryLogger {

    public static Path createRunDir() throws IOException {
        String ts = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS").format(LocalDateTime.now());
        Path outDir = Path.of("reports", "self-heal", ts);
        Files.createDirectories(outDir);
        return outDir;
    }

    public static void writeJson(Path dir, String name, String json) {
        try {
            Files.createDirectories(dir);
            Files.writeString(dir.resolve(name), json, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException ignored) {}
    }

    public static void writeMap(Path dir, String name, Map<String, Object> map) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            int i = 0;
            for (var e : map.entrySet()) {
                sb.append("  \"").append(e.getKey()).append("\": \"").append(String.valueOf(e.getValue()).replace("\"", "\\\"")).append("\"");
                i++;
                if (i < map.size()) sb.append(",");
                sb.append("\n");
            }
            sb.append("}\n");
            writeJson(dir, name, sb.toString());
        } catch (Exception ignored) {}
    }
}


