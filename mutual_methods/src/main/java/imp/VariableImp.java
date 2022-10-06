package imp;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helper.FileHelper;
import io.cucumber.java.en.And;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.util.Map;
import java.util.Objects;

public class VariableImp {

    private final Logger log = LogManager.getLogger(VariableImp.class);
    private static final String LOG_INFO = "{}, {} variable stored";


    @Step({"Store variable <key> = <value> during scenario", "Senaryo boyunca değişkeni sakla <key> = <value>"})
    public void storeVariableDuringScenario(String key, String value) {
        ScenarioDataStore.put(key, value);
        log.info(LOG_INFO, key, value);
    }

    @Step({"Store <file name>'s value from classpath with <key> during scenario",
            "Senaryo boyunca <dosya adı> içeriğini <key> anahtarı ile sakla"})
    public void storeVariableDuringScenarioFromFile(String fileName, String key) {
        FileHelper fileHelper = new FileHelper();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        String value = fileHelper.readFileAsString(filePath);
        ScenarioDataStore.put(key, value);
        log.info(LOG_INFO, key, fileName);
    }

    @Step({"Store json <file name>'s value from classpath with <key> during scenario",
            "Senaryo boyunca json <dosya adı> içeriğini <key> anahtarı ile sakla"})
    public void storeJsonFileValueDuringScenario(String fileName, String key) {
        FileHelper fileHelper = new FileHelper();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        String value = fileHelper.readFileAsString(filePath);
        JsonObject jsonObject = JsonParser.parseString(value).getAsJsonObject();

        ScenarioDataStore.put(key, jsonObject);
        log.info(LOG_INFO, key, fileName);
    }

    @Step({"Store json <file name>'s value from classpath with <key> during spec",
            "Spec boyunca json <dosya adı> içeriğini <key> anahtarı ile sakla"})
    public void storeJsonFileValueDuringSpec(String fileName, String key) {
        FileHelper fileHelper = new FileHelper();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        String value = fileHelper.readFileAsString(filePath);
        JsonObject jsonObject = JsonParser.parseString(value).getAsJsonObject();

        SpecDataStore.put(key, jsonObject);
        log.info(LOG_INFO, key, fileName);
    }

    @Step({"Store json <file name>'s value from classpath with <key> during suit",
            "Suit boyunca json <dosya adı> içeriğini <key> anahtarı ile sakla"})
    public void storeJsonFileValueDuringSuit(String fileName, String key) {
        FileHelper fileHelper = new FileHelper();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        String value = fileHelper.readFileAsString(filePath);
        JsonObject jsonObject = JsonParser.parseString(value).getAsJsonObject();
        SuiteDataStore.put(key, jsonObject);
        log.info(LOG_INFO, key, fileName);
    }

    @Step({"Store variable <key> = <value> during suite", "Suit boyunca değişkeni sakla <key> = <value>"})
    public void storeVariableDuringSuite(String key, String value) {
        SuiteDataStore.put(key, value);
        log.info(LOG_INFO, key, value);
    }

    @Step({"Store <file name>'s value from classpath with <key> during suite",
            "Suite boyunca <dosya adı> içeriğini <anahtarı ile sakla.>"})
    public void storeVariableDuringSuiteFromFile(String key, String fileName) {
        FileHelper fileHelper = new FileHelper();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        String value = fileHelper.readFileAsString(filePath);
        SuiteDataStore.put(key, value);
        log.info(LOG_INFO, key, fileName);
    }

    @Step({"Store variable <key> = <value> during spec", "Spec boyunca değişkeni sakla <key> = <value>"})
    public void storeVariableDuringSpec(String key, String value) {
        SpecDataStore.put(key, value);
        log.info(LOG_INFO, key, value);
    }

    @Step({"Store <file name>'s value from classpath with <key> during spec",
            "Spec boyunca <dosya adı> içeriğini <anahtarı ile sakla.>"})
    public void storeVariableDuringSpecFromFile(String key, String fileName) {
        FileHelper fileHelper = new FileHelper();
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();
        String value = fileHelper.readFileAsString(filePath);
        SpecDataStore.put(key, value);
        log.info(LOG_INFO, key, fileName);
    }

    @Step("Remove <key> from store")
    public void removeFromStore(String key) {
        Utils.removeFromStoreData(key);
        log.info("{} romeved from store", key);
    }

    @Step({"Store table as map during scenario with <key> <table>",
            "Tabloyu map objesi olarak <key> anahtarı ile senaryo boyunca sakla <table>"})
    public void storeTable(String key, Table table) {
        Utils utils = new Utils();
        Map<String, Object> storeData = utils.gaugeDataTableToMap(table);
        ScenarioDataStore.put(key, storeData);
        log.info("\"{}\" stored as table with key \"{}\"",storeData,key);
    }

    public void storeTable(String key, Map<String,Object> map) {
        ScenarioDataStore.put(key, map);
        log.info("\"{}\" stored as table with key \"{}\"",map,key);
    }
}
