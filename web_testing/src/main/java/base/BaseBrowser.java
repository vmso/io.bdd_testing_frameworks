package base;

import com.thoughtworks.gauge.*;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReuseStoreData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Modern Base Browser class using updated DriverManager
 */
public class BaseBrowser {

    private final Logger log = LogManager.getLogger(BaseBrowser.class);
    private final DriverManager driverManager = DriverManager.getInstance();

    public void setUp() {
        driverManager.createLocalDriver();
        log.info("{} browser launched successfully", driverManager.getBrowsersType().getValue());
    }

    @AfterScenario
    @After
    public void tearDown() {
        driverManager.quitDriver();
        ReuseStoreData.remove();
        log.info("Browser session closed and data cleared");
    }

    @BeforeSpec
    public void setFileNameForGauge(ExecutionContext context) {
        var fileName = context.getCurrentSpecification().getFileName();
        setFileName(fileName);
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        var fileName = String.valueOf(scenario.getUri());
        setFileName(fileName);
    }

    private void setFileName(String fileName) {
        int firstIndex = fileName.lastIndexOf('/');
        int lastIndex = fileName.lastIndexOf('.');
        if (firstIndex != -1 && lastIndex != -1 && lastIndex > firstIndex) {
            GetFileName.getInstance().setFileName(fileName.substring(firstIndex + 1, lastIndex));
        }
    }

    @AfterStep
    public void takeScreenShot() {
        var take_screens = Boolean.parseBoolean(System.getenv("screenshot_after_each_step"));
        if (take_screens) {
            Gauge.captureScreenshot();
        }
    }

    /**
     * Get the current WebDriver instance
     */
    public WebDriver getDriver() {
        return driverManager.getDriver();
    }

    /**
     * Get the WebDriverWait instance
     */
    public WebDriverWait getWait() {
        return driverManager.getWait();
    }

    /**
     * Check if driver is active
     */
    public boolean isDriverActive() {
        return driverManager.isDriverActive();
    }

    /**
     * Refresh the driver instance
     */
    public void refreshDriver() {
        driverManager.refreshDriver();
    }
}
