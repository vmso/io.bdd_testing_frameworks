package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import helper.JsonListFilterHelper;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

public class JsonFilterImp extends JsonListFilterHelper {
    Logger log = LogManager.getLogger(JsonListFilterHelper.class);

    @Step("Get <json select> from <json array> json list which one equals <filter>=<filterValue>, and store it during Scenario with <key>")
    @Then("Get {string} from {string} json list which one equals {string}={string}, and store it during Scenario with {string}")
    public void jsonArrayFilter(String requested, String json, String filter, String filterValue, String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonValueByFilter(jsonArray, filter, filterValue, requested);
        ScenarioDataStore.put(key, result);
        log.info("{} stored in scenario store", result);
    }
}
