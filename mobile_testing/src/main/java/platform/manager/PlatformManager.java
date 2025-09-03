package platform.manager;

import configuration.Configuration;
import enums.AppType;
import exceptions.FileNotFound;
import exceptions.UndefinedAppType;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import platforms.Android;
import platforms.IOS;
import platforms.MobileSystemSelectable;
import utils.ReuseStoreData;

import java.io.IOException;
import java.util.Properties;

public class PlatformManager {
    private static volatile PlatformManager instance;
    private AppType platform;
    private AppiumDriver driver;
    private final Logger log = LogManager.getLogger(PlatformManager.class);

    private PlatformManager() {
    }

    public static PlatformManager getInstances() {
        if (instance == null) {
            synchronized (PlatformManager.class) {
                if (instance == null) {
                    instance = new PlatformManager();
                }
            }
        }
        return instance;
    }

    public void createLocalMobileDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType, FileNotFound {
        var capabilities = getCapabilities(capabilitiesFile, capabilitiesName);
        var appType = capabilities.getProperty("platformName");
        platform = AppType.valueOf(appType.toUpperCase());
        MobileSystemSelectable mobileSystemSelectable = getMobileSystemSelectable();
        driver = mobileSystemSelectable.getLocalDriver();
        setDriver(driver);
        log.info("Local mobile driver created for platform: {}", platform);
    }

    public void createRemoteMobileDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType, FileNotFound {
        var capabilities = getCapabilities(capabilitiesFile, capabilitiesName);
        var appType = capabilities.getProperty("platformName");
        platform = AppType.valueOf(appType.toUpperCase());
        MobileSystemSelectable mobileSystemSelectable = getMobileSystemSelectable();
        driver = mobileSystemSelectable.getRemoteDriver();
        setDriver(driver);
        log.info("Remote mobile driver created for platform: {}", platform);
    }

    private MobileSystemSelectable getMobileSystemSelectable() throws UndefinedAppType {
        return switch (platform) {
            case ANDROID -> new Android();
            case IOS -> new IOS();
            default -> throw new UndefinedAppType(platform);
        };
    }

    private Properties getCapabilities(String capabilitiesFile, String capabilitiesName) throws FileNotFound {
        try {
            var properties = new Properties();
            properties.load(getClass().getClassLoader().getResourceAsStream(capabilitiesFile));
            var capabilities = new Properties();
            properties.forEach((key, value) -> {
                if (key.toString().startsWith(capabilitiesName + ".")) {
                    var newKey = key.toString().substring(capabilitiesName.length() + 1);
                    capabilities.setProperty(newKey, value.toString());
                }
            });
            return capabilities;
        } catch (IOException e) {
            throw new FileNotFound("Capabilities file not found: " + capabilitiesFile);
        }
    }

    public void setDriver(AppiumDriver driver) {
        ReuseStoreData.put("driver", driver);
    }

    public AppiumDriver getDriver() {
        return driver;
    }

    public AppType getPlatform() {
        return platform;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            log.info("Mobile driver quit");
        }
    }
}

