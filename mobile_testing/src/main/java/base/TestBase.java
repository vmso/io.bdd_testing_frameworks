package base;

import com.thoughtworks.gauge.*;
import enums.AppType;
import exceptions.FileNotFound;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import platform.manager.PlatformManager;
import exceptions.UndefinedAppType;
import io.cucumber.java.After;
import platforms.MobileSystemSelectable;


public class TestBase {
    private AppType platformType;

    public TestBase(){
        platformType = PlatformManager.getInstances().getPlatform();
    }
    public void lunchLocalDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType, FileNotFound {
        capabilitiesFile = capabilitiesFile.endsWith(".json") ? capabilitiesFile : capabilitiesFile + ".json";
        PlatformManager.getInstances().createLocalMobileDriver(capabilitiesFile, capabilitiesName);
    }

    @After
    @AfterScenario
    public void quit() {
        PlatformManager.getInstances().quitDriver();
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
