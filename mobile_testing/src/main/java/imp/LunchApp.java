package imp;

import base.App;
import com.thoughtworks.gauge.Step;
import exceptions.UndefinedAppType;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LunchApp {
    Logger log = LogManager.getLogger(LunchApp.class);

    @Step("Get <jsonKey> capabilities from resource with JSON <json file> file and lunch <platform> platform")
    @Given("Get {string} capabilities from resource with JSON {string} file and lunch {string} platform")
    public void lunchApp(String jsonKey, String jsonFile, String platformName) throws UndefinedAppType {
        System.setProperty("Platform", platformName);
        var app = new App();
        app.lunchLocalDriver(jsonFile, jsonKey);
        log.info("appium lunched with capabilities {} on {}", jsonFile, platformName);
    }
}
