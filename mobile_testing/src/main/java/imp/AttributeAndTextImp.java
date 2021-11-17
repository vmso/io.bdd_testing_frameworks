package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import exceptions.FileNotFound;
import helper.AttributeAndTextHelper;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttributeAndTextImp extends AttributeAndTextHelper {
    private final Logger log = LogManager.getLogger(AttributeAndTextImp.class);

    @Step("Get <> element text and store it Scenario Store with <> key")
    @And("Get {string} element text and store it Scenario Store with {string} key")
    public void getTextAndStoreScenarioStore(String jsonKey, String storeKey) throws FileNotFound {
        var text = getElmText(jsonKey);
        ScenarioDataStore.put(jsonKey, text);
        log.info("\"{}\" text got and and stored during scenario \"{}\"=\"{}\"", jsonKey, storeKey, text);
    }
}
