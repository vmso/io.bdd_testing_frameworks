package imp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import exceptions.NullResponse;
import exceptions.NullValue;
import helper.ResponseBodyHelper;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ResponseBodyImp extends ResponseBodyHelper {

    private final Logger log = LogManager.getLogger(ResponseBodyImp.class);

    @Step({"Store response as string with <key> during scenario",
            "Response'u String olarak <key> anahtarı ile senaryo boyunca sakla."})
    @Then("Store response as string with {string} during scenario")
    public void storeResponseForScenario(String key) throws NullResponse {
        ScenarioDataStore.put(key, getResponseAsString());
        log.info("response stored with key \"{}\" during scenario", key);
    }

    @Step({"Store response as json with <key> during scenario",
            "Response'u json olarak <key> anahtarı ile senaryo boyunca sakla."})
    @Then("Store response as json with {string} during scenario")
    public void storeResponseForScenarioAsJson(String key) throws NullResponse {
        JsonObject jsonObject = JsonParser.parseString(getResponseAsString()).getAsJsonObject();
        ScenarioDataStore.put(key, jsonObject);
        log.info("response stored with key \"{}\" during scenario", key);
    }

    @Step({"Store response as string with <key> during suite",
            "Response'u String olarak <key> anahtarı ile suite boyunca sakla."})
    @Then("Store response as string with {string} during suite")
    public void storeResponseForSuite(String key) throws NullResponse {
        SuiteDataStore.put(key, getResponseAsString());
        log.info("response stored with key \"{}\" during suite", key);
    }

    @Step({"Store response as string with <key> during spec",
            "Response'u String olarak <key> anahtarı ile spec boyunca sakla."})
    @Then("Store response as string with {string} during spec")
    public void storeResponseForSpece(String key) throws NullResponse {
        SuiteDataStore.put(key, getResponseAsString());
        log.info("response stored with key \"{}\" during spec", key);
    }

    @Step({"Get <selector> from response and store it with <key> during scenario",
            "Respons'dan <selector> değerini getir ve <key> anahtarı ile senaryo boyunca sakla"})
    @Then("Get {string} from response and store it with {string} during scenario")
    public void getResponseElementValueForScenario(String selector, String key) throws NullResponse, NullValue {
        Object value = getResponseElement(selector);
        ScenarioDataStore.put(key, value);
        log.info("\"{}\" is stored with \"{}\" during scenario", selector, key);
    }

    @Step({"Get <selector> from response and store it with <key> during suite",
            "Respons'dan <selector> değerini getir ve <key> anahtarı ile suite boyunca sakla"})
    @Then("Get {string} from response and store it with {string} during suite")
    public void getResponseElementValueForSuite(String selector, String key) throws NullResponse, NullValue {
        Object value = getResponseElement(selector);
        SuiteDataStore.put(key, value);
        log.info("\"{}\" is stored with \"{}\" during suite", selector, key);
    }

    @Step({"Get <selector> from response and store it with <key> during spec",
            "Respons'dan <selector> değerini getir ve <key> anahtarı ile spec boyunca sakla"})
    @Then("Get {string} from response and store it with {string} during spec")
    public void getResponseElementValueForSpec(String selector, String key) throws NullResponse, NullValue {
        Object value = getResponseElement(selector);
        SpecDataStore.put(key, value);
        log.info("\"{}\" is stored with {} during spec", selector, key);
    }

    @Step({"Get <selector> from response and then check if is not null?",
            "Get <selector> from the response and then verify it is not null?",
            "Selector <selector> ile responsdand eğer getir ve null olmadığını doğrula"})
    @Then("Get {string} from the response and then verify it is not null?")
    public void checkDataFromResponseIsNotNull(String selector) throws NullResponse, NullValue {
        Object value2 = getResponseElementEvenNull(selector);
        assertNotNull(value2, selector + " is null");
    }

    @Step({"Get <selector> from response and then check if is null?",
            "Get <selector> from the response and then verify it is null?",
            "Selector <selector> ile responsdand eğer getir ve null olduğunu doğrula "})
    @Then("Get {string} from the response and then verify it is null?")
    public void checkDataFromResponseIsNull(String selector) throws NullResponse, NullValue {
        Object value2 = getResponseElementEvenNull(selector);
        assertNull(value2, selector + " is not null");
    }

    @Then("Get {string} from the body then convert it to list and store it with {string} during the scenario")
    @Step("Get <selector> from the body then convert it as list and store it with <key> during the scenario")
    public void convertToString(String jsonKey, String key) {
        var list = getListFromResponse(jsonKey);
        ScenarioDataStore.put(key, list);
    }

    @Step("Get <selector> from the body then convert it to list and store it with <key> during the suit")
    @Then("Get <string> from the body then convert it to list and store it with <key> during the suit")
    public void convertToStringStoreSuit(String jsonKey, String key) {
        var list = getListFromResponse(jsonKey);
        ScenarioDataStore.put(key, list);
    }

    @Step("Get <selector> from the body then convert it to list and store it with <key> during the spec")
    @Then("Get <string> from the body then convert it to list and store it with <key> during the spec")
    public void convertToStringStoreSpec(String jsonKey, String key) {
        var list = getListFromResponse(jsonKey);
        ScenarioDataStore.put(key, list);
    }

}
