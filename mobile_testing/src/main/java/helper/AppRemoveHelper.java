package helper;

import elements.GetElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import platform.manager.PlatformManager;

import java.lang.reflect.Method;

public class AppRemoveHelper extends GetElement {

    public void removeApp(String appPackage) {
        var driver = PlatformManager.getInstances().getDriver();
        try {
            Method removeAppMethod = driver.getClass().getMethod("removeApp", String.class);
            removeAppMethod.invoke(driver, appPackage);
        } catch (Exception e) {
            throw new RuntimeException("Failed to remove app: " + appPackage, e);
        }
    }
}
