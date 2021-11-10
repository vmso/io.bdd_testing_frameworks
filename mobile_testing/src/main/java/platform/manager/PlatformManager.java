package platform.manager;

import com.jayway.jsonpath.PathNotFoundException;
import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import platforms.Android;
import platforms.IOS;
import platforms.MobileSystemSelectable;
import exceptions.UndefinedAppType;
import io.appium.java_client.AppiumDriver;

import java.util.Locale;
import java.util.Objects;

import enums.AppType;

public class PlatformManager {
    private static PlatformManager instance;
    private AppiumDriver<MobileElement> driver;
    private Logger log = LogManager.getLogger(PlatformManager.class);
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
        String platform;
        AppType appType = null;
        try {
            platform = new JsonReader().getPlatform(capabilitiesFile, platformJsonKey);
            appType = AppType.valueOf(platform.toUpperCase(Locale.ENGLISH));
        } catch (PathNotFoundException e) {
            log.fatal("""
                    capabilities or platform couldn't find in "{}" device json
                    json key of the capabilities is {}
                    json key of the platform is {}
                    """, capabilitiesFile, capabilitiesJsonKey, platformJsonKey);
        }

        setPlatform(appType);
        MobileSystemSelectable mobileSystemSelectable;
        switch (Objects.requireNonNull(appType)) {
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
                driver.closeApp();
                driver.quit();
            }
        } catch (NoSuchSessionException e) {
            e.printStackTrace();
        }
    }

    public AppiumDriver<MobileElement> getDriver() {
        return driver;
    }

    public AppType getPlatform() {
        return platform;
    }

    private void setPlatform(AppType platform) {
        this.platform = platform;
    }
}
