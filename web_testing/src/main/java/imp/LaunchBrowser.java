package imp;


import base.BaseBrowser;
import com.thoughtworks.gauge.Step;

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
        DriverManager.getInstances().getDriver().get(url);
        log.info("{} browser launched and {} is opened", browser, url);
    }
}
