package helper;

import java.util.LinkedHashMap;
import java.util.List;

public class JsonListFilterHelper {

    protected Object getJsonValueByFilter(Object jsonPathObj, String filter, String filterValue, String requestedSelector) {
        @SuppressWarnings("unchecked")
        var jsonPath = (List<LinkedHashMap<String, Object>>) jsonPathObj;

        for (LinkedHashMap<String, Object> json : jsonPath) {
            String json_result = String.valueOf(json.get(filter));
            if (json_result.equalsIgnoreCase(filterValue)) {
                return json.get(requestedSelector);
            }
        }
        return null;
    }
}
