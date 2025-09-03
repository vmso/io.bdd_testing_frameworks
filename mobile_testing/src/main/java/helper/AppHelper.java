package helper;

import org.openqa.selenium.WebDriver;
import utils.ReuseStoreData;

import java.lang.reflect.Method;

public class AppHelper {
    private WebDriver driver;

    public AppHelper() {
        this.driver = (WebDriver) ReuseStoreData.get("driver");
    }

    public void installApp(String appPath) {
        try {
            Method installAppMethod = driver.getClass().getMethod("installApp", String.class);
            installAppMethod.invoke(driver, appPath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to install app: " + appPath, e);
        }
    }

    public void uninstallApp(String appPackage) {
        try {
            Method removeAppMethod = driver.getClass().getMethod("removeApp", String.class);
            removeAppMethod.invoke(driver, appPackage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to uninstall app: " + appPackage, e);
        }
    }

    public boolean isAppInstalled(String appPackage) {
        try {
            Method isAppInstalledMethod = driver.getClass().getMethod("isAppInstalled", String.class);
            return (Boolean) isAppInstalledMethod.invoke(driver, appPackage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to check if app is installed: " + appPackage, e);
        }
    }

    public void activateApp(String appPackage) {
        try {
            Method activateAppMethod = driver.getClass().getMethod("activateApp", String.class);
            activateAppMethod.invoke(driver, appPackage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to activate app: " + appPackage, e);
        }
    }

    public void terminateApp(String appPackage) {
        try {
            Method terminateAppMethod = driver.getClass().getMethod("terminateApp", String.class);
            terminateAppMethod.invoke(driver, appPackage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to terminate app: " + appPackage, e);
        }
    }
}
