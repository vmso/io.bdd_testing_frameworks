package helper;

import enums.AppType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import platform.manager.PlatformManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.Keys.SHIFT;

//todo need steps, OK
public class PressKeysHelper extends GetKeyboard {
    private AppiumDriver<MobileElement> driver;
    private AppType appType;

    protected PressKeysHelper() {
        driver = PlatformManager.getInstances().getDriver();
        appType = PlatformManager.getInstances().getPlatform();
    }

    //todo write new methods for like below special buttons, I dont Understand
    protected void pressEnter() {
        switch (appType) {
            case ANDROID -> presKeys("ENTER");
            case IOS -> getKeyBoard().pressKey(ENTER);
            default -> throw new IllegalStateException("Unexpected value: " + appType);
        }

    }

    //todo like below.
    protected void pressShift() {
        switch (appType) {
            case ANDROID -> presKeys("SHIFT");
            case IOS -> getKeyBoard().pressKey(SHIFT);
            default -> throw new IllegalStateException("Unexpected value: " + appType);
        }
    }

    protected void pressBack() {
        switch (appType) {
            case ANDROID -> presKeys("BACK");
            case IOS -> driver.navigate().back();
            default -> throw new IllegalStateException("Unexpected value: " + appType);

        }

    }


    protected void presKeys(String key) {
        switch (appType) {
            case ANDROID -> {
                try {
                    androidPresKey(key);
                } catch (IllegalArgumentException e) {
                    androidPresKeys(key);
                }
            }
            case IOS -> getKeyBoard().sendKeys(key);
            default -> throw new IllegalStateException("Unexpected value: " + appType);
        }
    }

    protected void androidPresKey(String key) {
        AndroidKey androidKey = AndroidKey.valueOf(key);
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(androidKey));
    }

    protected void androidPresKeys(String key) {
        for (Character c : key.toCharArray()) {
            var newKey = String.valueOf(c).toUpperCase(Locale.ENGLISH);
            newKey = newKey.equals("@") ? "AT" : newKey;
            newKey = newKey.equals(".") ? "NUMPAD_DOT" : newKey;
            newKey = newKey.equals(",") ? "NUMPAD_COMMA" : newKey;
            AndroidKey androidKey = AndroidKey.valueOf(newKey);
            ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(androidKey));
        }
    }
}
