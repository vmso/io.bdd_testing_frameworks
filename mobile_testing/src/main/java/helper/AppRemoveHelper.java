package helper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import platform.manager.PlatformManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppRemoveHelper {

    private final Logger log = LogManager.getLogger(AppRemoveHelper.class);
    private AppiumDriver driver;

    public AppRemoveHelper() {
        driver = PlatformManager.getInstances().getDriver();
    }

    protected boolean checkIsAppInstalled(String appName) {
        return driver.isAppInstalled(appName);
    }

    protected void checkIsAppInstalledAndRemove(String appName) {
        if (checkIsAppInstalled(appName))
            driver.removeApp(appName);
        log.info("App {} removed", appName);
    }

    protected void installApp(String appPath) {
        driver.installApp(appPath);
        log.info("App installed from {}", appPath);
    }

    protected void launchApp() {
        driver.launchApp();
    }

}
