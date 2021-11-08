package json;

import exceptions.FileNotFound;
import helper.FileHelper;
import helper.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Objects;

public class JsonReader {
    private final Logger log = LogManager.getLogger(JsonReader.class);

    public Map<String, Object> getJsonAsMap(String filePath, String jsonKey) throws FileNotFound {
        if (jsonKey.contains(" ")) {
            jsonKey = jsonKey.replaceAll(" ", "_");
            log.warn("""
                    The JSON key must be space-free.
                    The JSON key you specified has been automatically
                    converted to a space-free format.\s
                    new JSON key is "{}\"""", jsonKey);
        }
        var file = new FileHelper();
        String path;
        try {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getPath();
        } catch (NullPointerException e) {
            throw new FileNotFound(filePath);
        }
        var jsonStringValue = file.readFileAsString(path);
        JsonHelper helper = new JsonHelper();
        return helper.getJsonValueAsMap(jsonStringValue, jsonKey);
    }

    public String getPlatform(String filePath, String jsonKey) throws FileNotFound {
        String path;
        try {
            path = Objects.requireNonNull(getClass().getClassLoader().getResource(filePath)).getPath();
        } catch (NullPointerException e) {
            throw new FileNotFound(filePath);
        }
        var jsonStringValue = new FileHelper().readFileAsString(path);
        JsonHelper helper = new JsonHelper();
        return helper.getJsonValueAsString(jsonStringValue, jsonKey);
    }

}
