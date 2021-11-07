package helper;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.appmanagement.BaseActivateApplicationOptions;
import io.appium.java_client.appmanagement.BaseRemoveApplicationOptions;
import platform.manager.PlatformManager;

import javax.annotation.Nullable;

// todo bunun implerini ve loglarını yaz.
public class AppHelper {
    private MobileDriver driver;

    protected AppHelper() {
        this.driver = PlatformManager.getInstances().getDriver();
    }

    protected void launchApp() {
        driver.launchApp();
    }

    protected void removeApp(String bundleId) {
        driver.removeApp(bundleId);
    }

    protected void removeApp(String bundleId, @Nullable BaseRemoveApplicationOptions option) {
        driver.removeApp(bundleId, option);
    }

    protected void closeApp() {
        driver.closeApp();
    }

    protected void activateApp(String bundleId) {
        driver.activateApp(bundleId);
    }

    protected void activateApp(String bundleId, @Nullable BaseActivateApplicationOptions option) {
        driver.activateApp(bundleId, option);
    }
}
