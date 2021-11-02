package app.manneger;

import Mobile.os.Android;
import Mobile.os.IOS;
import Mobile.os.MobileSystemSelectable;
import exceptions.UndefinedAppType;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.util.Locale;
import java.util.Map;

import enums.AppType;

public class AppManager {
    private static AppManager instance;
    private AppiumDriver driver;

    private AppManager() {

    }

    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    public void createLocalMobileDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType {
        String platform = System.getProperty("Platform");
        AppType appType = AppType.valueOf(platform.toUpperCase(Locale.ENGLISH));
        MobileSystemSelectable mobileSystemSelectable;
        switch (appType) {
            case IOS -> {
                mobileSystemSelectable = new IOS();
                mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesName);
                driver = mobileSystemSelectable.getLocalDriver();
            }
            case ANDROID -> {
                mobileSystemSelectable = new Android();
                mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesName);
                driver = mobileSystemSelectable.getLocalDriver();
            }
            default -> throw new UndefinedAppType(appType);
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver driver) {
        this.driver = driver;
    }
}
