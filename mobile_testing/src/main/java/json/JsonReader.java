package json;

import exceptions.FileNotFounded;
import helper.FileHelper;
import helper.JsonHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;

public class JsonReader {
    private final Logger log = LogManager.getLogger(JsonReader.class);

    public Map<String, Object> getJsonAsMap(String filePath, String jsonKey) throws FileNotFounded {
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
        } catch (NullPointerException e){
            throw new FileNotFounded(filePath);
        }
        var jsonStringValue = file.readFileAsString(path);
        JsonHelper helper = new JsonHelper();
        return helper.getJsonValueAsMap(jsonStringValue, jsonKey);
    }

}
