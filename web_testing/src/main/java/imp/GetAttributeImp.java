package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import helpers.GetAttributeHelper;
import io.cucumber.java.en.And;

public class GetAttributeImp extends GetAttributeHelper {

    @Step("Get text of <element> and store it in scenario store with <key> and default wait")
    @And("Get text of {string} and store it in scenario store with {string} and default wait")
    public void getTextOfElmWithWait(String jsonKey, String key) {
        ScenarioDataStore.put(key, getTexWithWait(jsonKey));
    }

    @Step("Get text of <element> and store it in scenario store with <key>")
    @And("Get text of {string} and store it in scenario store with {string}")
    public void getTextOfElmWithoutWait(String jsonKey, String key) {
        ScenarioDataStore.put(key, getTexWithoutWait(jsonKey));
    }

    @Step("Wait <second> presence of <element> then get text and store it in scenario store with <key>")
    @And("Wait {long} presence of {string} then get text and store it in scenario store with {string}")
    public void getTextOfElmWithWait(long timeout, String jsonKey, String key) {
        ScenarioDataStore.put(key, getTexWithWait(jsonKey, timeout));
    }

    @Step("Wait <second> and poling ever <millis> millis  presence of <element> then get text and store it in scenario store with <key>")
    @And("Wait {long} and poling ever {long} millis presence of {string} then get text and store it in scenario store with {string}")
    public void getTextOfElmWithWait(long timeout, long polingWait, String jsonKey, String key) {
        ScenarioDataStore.put(key, getTexWithWait(jsonKey, polingWait, timeout));
    }

    @Step("Get <attribute> of <element> and store it in scenario store with <key> and default wait")
    @And("Get {string} attribute of {string} and store it in scenario store with {string} and default wait")
    public void getAttributeOfElmWithWait(String attribute, String jsonKey, String key) {
        ScenarioDataStore.put(key, getAttributeWithDefaultWait(jsonKey, attribute));
    }

    @Step("Get <attribute> of <element> and store it in scenario store with <key> without any wait")
    @And("Get {string} attribute of {string} and store it in scenario store with {string} without any wait")
    public void getAttributeOfElmWithoutWait(String attribute, String jsonKey, String key) {
        ScenarioDataStore.put(key, getAttributeWithoutWait(jsonKey, attribute));
    }

    @Step({"Get size of <element> and store it scenario store with <key>", "sadsada"})
    @And("Get size of {string} and store it scenario store with {string}")
    public void getSizeOfElement(String jsonKey, String key) {
        ScenarioDataStore.put(key, getElementSize(jsonKey));
    }

    @Step("Get inner text of <element> and store it in scenario store with <key> and default wait")
    @And("Get inner text of {string} and store it in scenario store with {string} and default wait")
    public void getInnerTextOfElmWithWait(String jsonKey, String key) {
        ScenarioDataStore.put(key, getInnerTextOfElement(jsonKey));
    }
}
