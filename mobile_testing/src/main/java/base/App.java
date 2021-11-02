package base;

import app.manneger.AppManager;
import com.thoughtworks.gauge.AfterScenario;
import exceptions.UndefinedAppType;
import org.junit.After;

import java.util.Map;

public class App {

    public void lunchLocalDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType {
        capabilitiesFile = capabilitiesFile.endsWith(".json") ? capabilitiesFile : capabilitiesFile + ".json";
        AppManager.getInstance().createLocalMobileDriver(capabilitiesFile, capabilitiesName);
    }

    @After
    @AfterScenario
    public void quit() {
        if (AppManager.getInstance().getDriver() != null) {
            AppManager.getInstance().getDriver().closeApp();
            AppManager.getInstance().getDriver().quit();
        }
    }
}
