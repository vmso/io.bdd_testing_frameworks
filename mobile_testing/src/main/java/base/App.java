package base;

import com.thoughtworks.gauge.BeforeSpec;
import com.thoughtworks.gauge.ExecutionContext;
import exceptions.FileNotFound;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import platform.manager.PlatformManager;
import com.thoughtworks.gauge.AfterScenario;
import exceptions.UndefinedAppType;
import io.cucumber.java.After;


public class App {

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
        int firstIndex = fileName.lastIndexOf('/');
        int lastIndex = fileName.lastIndexOf('.');
        GetFileName.getInstance().setFileName(fileName.substring(firstIndex + 1, lastIndex - 1));
    }

    @Before
    public void beforeScenario(Scenario scenario) {
        var fileName = String.valueOf(scenario.getUri());
        int firstIndex = fileName.lastIndexOf('/');
        int lastIndex = fileName.lastIndexOf('.');
        GetFileName.getInstance().setFileName(fileName.substring(firstIndex + 1, lastIndex));
    }

}
