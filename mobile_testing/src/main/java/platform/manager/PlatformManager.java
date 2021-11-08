package platform.manager;

import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import json.JsonReader;
import org.openqa.selenium.NoSuchSessionException;
import platforms.Android;
import platforms.IOS;
import platforms.MobileSystemSelectable;
import exceptions.UndefinedAppType;
import io.appium.java_client.AppiumDriver;

import java.util.Locale;

import enums.AppType;

public class PlatformManager {
    private static PlatformManager instance;
    private AppiumDriver<MobileElement> driver;

    private AppType platform;

    private PlatformManager() {

    }

    public static PlatformManager getInstances() {
        if (instance == null) {
            instance = new PlatformManager();
        }
        return instance;
    }

    public void createLocalMobileDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType, FileNotFound {
        var capabilitiesJsonKey = String.format("%s.capabilities", capabilitiesName);
        var platformJsonKey = String.format("%s.platform", capabilitiesName);
        String platform = new JsonReader().getPlatform(capabilitiesFile, platformJsonKey);
        AppType appType = AppType.valueOf(platform.toUpperCase(Locale.ENGLISH));
        setPlatform(appType);
        MobileSystemSelectable mobileSystemSelectable;
        switch (appType) {
            case IOS -> {
                mobileSystemSelectable = new IOS();
                mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesJsonKey);
                driver = mobileSystemSelectable.getLocalDriver();
            }
            case ANDROID -> {
                mobileSystemSelectable = new Android();
                mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesJsonKey);
                driver = mobileSystemSelectable.getLocalDriver();
            }
            default -> throw new UndefinedAppType(appType);
        }
    }

    public void quitDriver() {
        try {
            if (PlatformManager.getInstances().getDriver() != null) {
                PlatformManager.getInstances().getDriver().closeApp();
                PlatformManager.getInstances().getDriver().quit();
            }
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public AppType getPlatform() {
        return platform;
    }

    private void setPlatform(AppType platform) {
        this.platform = platform;
    }
}
