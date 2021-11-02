package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helper.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

public class StringImp extends StringHelper {
    private final Logger log = LogManager.getLogger(StringImp.class);
    private static final String SCENARIO_INFO = "\"{}\" is stored on Scenario store";
    private static final String SPEC_INFO = "\"{}\" is stored on Spec store";
    private static final String SUIT_INFO = "\"{}\" is stored on Suit store";

    @Step({"Replace <old> old chars to new <new> chars on <text> text and store it on Scenario store with <> key"})
    public void replaceAndStoreScenarioData(String regex, String replacement, String text, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        text = replace(text, regex, replacement);
        ScenarioDataStore.put(key, text);
        log.info(SCENARIO_INFO, text);
    }

    @Step({"Replace <old> old chars to new <new> chars on <text> text but only first and store it on Scenario store with <> key"})
    public void replaceAndStoreScenarioDataOnlyFirst(String regex, String replacement, String text, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        text = replace(text, regex, replacement, true);
        ScenarioDataStore.put(key, text);
        log.info(SCENARIO_INFO, text);
    }

    @Step("Replace <old> old chars to new <new> chars on <text> text and store it on Spec store with <> key")
    public void replaceAndStoreSpecData(String regex, String replacement, String text, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        text = replace(text, regex, replacement);
        SpecDataStore.put(key, text);
        log.info(SPEC_INFO, text);

    }

    @Step({"Replace <old> old chars to new <new> chars on <text> text but only first and store it on Spec store with <> key"})
    public void replaceAndStoreSpecDataOnlyFirst(String regex, String replacement, String text, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        text = replace(text, regex, replacement, true);
        SpecDataStore.put(key, text);
        log.info(SPEC_INFO, text);
    }

    @Step("Replace <old> old chars to new <new> chars on <text> text and store it on Suit store with <> key")
    public void replaceAndStoreSuitData(String regex, String replacement, String text, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        text = replace(text, regex, replacement);
        SpecDataStore.put(key, text);
        log.info(SUIT_INFO, text);

    }

    @Step({"Replace <old> old chars to new <new> chars on <text> text but only first and store it on Suit store with <> key"})
    public void replaceAndStoreSuitDataOnlyFirst(String regex, String replacement, String text, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        text = replace(text, regex, replacement, true);
        SuiteDataStore.put(key, text);
        log.info(SUIT_INFO, text);
    }

    @Step({"Substring text <text> from <firstIndex> index to <lastIndex> index then store it in Scenario store with <key> key"})
    public void subStringAndStoreScenarioStore(String text, int firstInt, int lastInt, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        var newText = subString(text, firstInt, lastInt);
        ScenarioDataStore.put(key, newText);
        log.info(SCENARIO_INFO, newText);
    }

    @Step({"Substring text <text> from <firstIndex> index to <lastIndex> index then store it in Spec store with <key> key"})
    public void subStringAndStoreSpecStore(String text, int firstInt, int lastInt, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        var newText = subString(text, firstInt, lastInt);
        SpecDataStore.put(key, newText);
        log.info(SCENARIO_INFO, newText);
    }

    @Step({"Substring text <text> from <firstIndex> index to <lastIndex> index then store it in Suit store with <key> key"})
    public void subStringAndStoreSuitStore(String text, int firstInt, int lastInt, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        var newText = subString(text, firstInt, lastInt);
        SuiteDataStore.put(key, newText);
        log.info(SCENARIO_INFO, newText);
    }

    @Step({"Substring text <text> from <firstIndex> index to the end then store it in Scenario store with <key> key"})
    public void subStringAndStoreScenarioStore(String text, int firstInt, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        var newText = subString(text, firstInt);
        ScenarioDataStore.put(key, newText);
        log.info(SCENARIO_INFO, newText);
    }

    @Step({"Substring text <text> from <firstIndex> index to the end then store it in Spec store with <key> key"})
    public void subStringAndStoreSpecStore(String text, int firstInt, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        var newText = subString(text, firstInt);
        SpecDataStore.put(key, newText);
        log.info(SCENARIO_INFO, newText);
    }

    @Step({"Substring text <text> from <firstIndex> index to the end then store it in Suit store with <key> key"})
    public void subStringAndStoreSuitStore(String text, int firstInt, String key) {
        text = String.valueOf(Utils.getFromStoreData(text));
        var newText = subString(text, firstInt);
        SuiteDataStore.put(key, newText);
        log.info(SCENARIO_INFO, newText);
    }


}
