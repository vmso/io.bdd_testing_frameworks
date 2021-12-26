package base;

import com.thoughtworks.gauge.*;
import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class BaseBrowser {

    private final Logger log = LogManager.getLogger(BaseBrowser.class);


    public void setUp() {
        DriverManager.getInstances().createLocalDriver();
        log.info("{} launched",DriverManager.getInstances().getBrowsersType());
    }

    @AfterScenario
    @After
    public void tearDown() {
        DriverManager.getInstances().quitDriver();
        StoreApiInfo.remove();
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
        GetFileName.getInstance().setFileName(fileName.substring(firstIndex + 1, lastIndex));
    }

    @AfterStep
    public void takeScreenShot() {
        var take_screens = Boolean.parseBoolean(System.getenv("screenshot_after_each_step"));
        if (take_screens)
            Gauge.captureScreenshot();
    }

}
