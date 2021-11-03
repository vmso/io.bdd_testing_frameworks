package base;

import exceptions.FileNotFounded;
import platform.manager.PlatformManager;
import com.thoughtworks.gauge.AfterScenario;
import exceptions.UndefinedAppType;
import org.junit.After;

public class App {

    public void lunchLocalDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType, FileNotFounded {
        capabilitiesFile = capabilitiesFile.endsWith(".json") ? capabilitiesFile : capabilitiesFile + ".json";
        PlatformManager.getInstances().createLocalMobileDriver(capabilitiesFile, capabilitiesName);
    }

    @After
    @AfterScenario
    public void quit() {
        PlatformManager.getInstances().quitDriver();
    }
}
