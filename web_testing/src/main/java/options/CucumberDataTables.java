package options;

import helper.ParseHelper;
import io.cucumber.java.DataTableType;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;


public class CucumberDataTables {

    @DataTableType
    public Map<String, Object> parameters( Map<String, String> entry) {
        var params = new HashMap<String, Object>();
        entry.forEach((key, value) -> params.put(key, new Utils().isNumeric(value) ?
                new ParseHelper().parsStringToInt(value) :
                value));
        return params;
    }

}
