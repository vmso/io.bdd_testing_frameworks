package helper;

import elements.GetElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import platform.manager.PlatformManager;

import java.lang.reflect.Method;

public class PressKeysHelper extends GetElement {

    private WebDriver driver;

    public PressKeysHelper() {
        this.driver = PlatformManager.getInstances().getDriver();
    }

    public void pressKey(String key) {
        try {
            AndroidKey androidKey = getAndroidKey(key);
            Method pressKeyMethod = driver.getClass().getMethod("pressKey", KeyEvent.class);
            pressKeyMethod.invoke(driver, new KeyEvent(androidKey));
        } catch (Exception e) {
            throw new RuntimeException("Failed to press key: " + key, e);
        }
    }

    public void pressKey(String key, int timeOut) {
        try {
            AndroidKey androidKey = getAndroidKey(key);
            Method pressKeyMethod = driver.getClass().getMethod("pressKey", KeyEvent.class);
            pressKeyMethod.invoke(driver, new KeyEvent(androidKey));
        } catch (Exception e) {
            throw new RuntimeException("Failed to press key: " + key, e);
        }
    }

    private AndroidKey getAndroidKey(String key) {
        try {
            return AndroidKey.valueOf(key.toUpperCase());
        } catch (IllegalArgumentException e) {
            // Return a default key if the provided key is not found
            return AndroidKey.ENTER;
        }
    }
}
