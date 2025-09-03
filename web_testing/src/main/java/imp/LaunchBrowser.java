package imp;


import base.BaseBrowser;
import com.thoughtworks.gauge.Step;

import configuration.Configuration;
import driver.DriverManager;

import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LaunchBrowser {

    Logger log = LogManager.getLogger(LaunchBrowser.class);

    @Step("Open <browser> and get <url>")
    @Given("Open {string} and get {string}")
    public void lunchApp(String browser, String url) {
        System.setProperty("browser", browser);
        var base = new BaseBrowser();
        base.setUp();
        DriverManager.getInstance().getDriver().get(url);
        log.info("{} browser launched and {} is opened", browser, url);
    }

    @Step("Open <browser> and get base url")
    @Given("Open {string} and get base url")
    public void lunchApp(String browser) {
        System.setProperty("browser", browser);
        var base = new BaseBrowser();
        var baseUrl = Configuration.getInstance().getStringValueOfProp("base.url");
        base.setUp();
        DriverManager.getInstance().getDriver().get(baseUrl);
        log.info("{} browser launched and {} is opened", browser, baseUrl);
    }

    @Step("Open browser and get base url")
    @Given("Open browser and get base url")
    public void lunchApp() {
        String defaultBrowser = Configuration.getInstance().getStringValueOfProp("default.browser");
        System.setProperty("browser", defaultBrowser);
        var base = new BaseBrowser();
        var baseUrl = Configuration.getInstance().getStringValueOfProp("base.url");
        base.setUp();
        DriverManager.getInstance().getDriver().get(baseUrl);
        log.info("{} browser launched and {} is opened", defaultBrowser, baseUrl);
    }
}
