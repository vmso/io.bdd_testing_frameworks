package imp;

import base.TestBase;
import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import exceptions.UndefinedAppType;
import io.cucumber.java.en.And;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LunchApp {
    Logger log = LogManager.getLogger(LunchApp.class);

    @Step("Get <jsonKey> capabilities from resource with JSON <json file> file and lunch app")
    @And("Get {string} capabilities from resource with JSON {string} file and lunch app")
    public void lunchApp(String jsonKey, String jsonFile) throws UndefinedAppType, FileNotFound {
        var app = new TestBase();
        app.lunchLocalDriver(jsonFile, jsonKey);
        log.info("appium lunched with capabilities {}", jsonFile);
    }
}
