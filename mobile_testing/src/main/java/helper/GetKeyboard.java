package helper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import platform.manager.PlatformManager;

public class GetKeyboard {

    public void pressKey(Keys key) {
        var driver = PlatformManager.getInstances().getDriver();
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    public void sendKeys(String text) {
        var driver = PlatformManager.getInstances().getDriver();
        Actions actions = new Actions(driver);
        actions.sendKeys(text).perform();
    }

    public void pressKeyCombination(Keys... keys) {
        var driver = PlatformManager.getInstances().getDriver();
        Actions actions = new Actions(driver);
        actions.keyDown(keys[0]);
        for (int i = 1; i < keys.length; i++) {
            actions.keyDown(keys[i]);
        }
        for (int i = keys.length - 1; i >= 0; i--) {
            actions.keyUp(keys[i]);
        }
        actions.perform();
    }
}
