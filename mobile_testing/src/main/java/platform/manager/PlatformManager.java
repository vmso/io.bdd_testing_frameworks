package platform.manager;

import com.jayway.jsonpath.PathNotFoundException;
import configuration.Configuration;
import enums.AppType;
import exceptions.FileNotFound;
import exceptions.UndefinedAppType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import platforms.Android;
import platforms.IOS;
import platforms.MobileSystemSelectable;
import utils.ReuseStoreData;

import java.util.Locale;
import java.util.Objects;

public class PlatformManager {
    private static PlatformManager instance;
    private AppiumDriver<MobileElement> driver;
    private final Logger log = LogManager.getLogger(PlatformManager.class);
    private AppType platform;
    private MobileSystemSelectable mobileSystemSelectable;

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
        AppType platformType = null;
        try {
            platform = new JsonReader().getPlatform(capabilitiesFile, platformJsonKey);
            platformType = AppType.valueOf(platform.toUpperCase(Locale.ENGLISH));
        } catch (PathNotFoundException e) {
            log.fatal("""
                    capabilities or platform couldn't find in "{}" device json
                    json key of the capabilities is {}
                    json key of the platform is {}
                    """, capabilitiesFile, capabilitiesJsonKey, platformJsonKey);
        }

        setPlatform(platformType);
        String env = Configuration.getInstance().getStringValueOfProp("env");
        switch (Objects.requireNonNull(platformType)) {
            case IOS -> mobileSystemSelectable = new IOS();
            case ANDROID -> mobileSystemSelectable = new Android();
            default -> throw new UndefinedAppType(platformType);
        }
        mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesJsonKey);
        driver = env == null ? mobileSystemSelectable.getLocalDriver() : mobileSystemSelectable.getRemoteDriver();
        setDriver(driver);
    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        ReuseStoreData.put("driver", driver);
    }

    public void quitDriver() {
        try {
            if (PlatformManager.getInstances().getDriver() != null
                    && PlatformManager.getInstances().getDriver().getSessionId() != null) {
                driver.closeApp();
                driver.quit();
                mobileSystemSelectable.stopTheServices();
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
