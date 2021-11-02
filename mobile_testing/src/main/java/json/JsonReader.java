package json;

import helper.FileHelper;
import helper.JsonHelper;
import org.junit.Test;

import java.util.Map;

public class JsonReader {

    public Map<String, Object> getJsonAsMap(String filePath, String jsonKey) {
        var file = new FileHelper();
        var path = getClass().getClassLoader().getResource(filePath).getPath();
        var jsonStringValue = file.readFileAsString(path);
        JsonHelper helper = new JsonHelper();
        return helper.getJsonValueAsMap(jsonStringValue, jsonKey);
    }

}
