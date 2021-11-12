package helper;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import platform.manager.PlatformManager;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.openqa.selenium.Keys.ENTER;

//todo need steps, OK
public class PressKeysHelper extends GetKeyboard {
    MobileDriver driver;

    protected PressKeysHelper() {
        driver = PlatformManager.getInstances().getDriver();
    }

    protected void presKeys(String key) {
        if ((driver.getClass().getSimpleName().equals("AndroidDriver"))) {
            List.of(key.toCharArray()).forEach(k ->
            {
                AndroidKey androidKey = AndroidKey.valueOf(String.valueOf(k));
                ((AndroidDriver) driver).pressKey(new KeyEvent(androidKey));
            });
        } else {
            JavascriptHelper execute = new JavascriptHelper();
            HashMap<String, String> scriptObjects = new HashMap<>();
            scriptObjects.put("name", key);
            execute.executeJavascript("mobile: pressButton", scriptObjects);
        }
    }

    //todo write new methods for like below special buttons, I dont Understand
    protected void pressEnter() {
        getKeyBoard().pressKey(ENTER);
    }

}
