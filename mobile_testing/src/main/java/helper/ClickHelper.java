package helper;

import exceptions.FileNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import platform.manager.PlatformManager;

public class ClickHelper extends GetElementHelper {

    private final Logger log = LogManager.getLogger(ClickHelper.class);

    public void clickElement(String jsonKey) {
        new ClickableHelper().waitForClickable(jsonKey).click();
        log.info("{} element clicked", jsonKey);
    }

    public void clickElement(String jsonKey, int timeout) {
        new ClickableHelper().waitForClickable(jsonKey, timeout).click();
        log.info("{} element clicked", jsonKey);
    }

    public void clickElement(String jsonKey, int timeout, int sleepTime) {
        new ClickableHelper().waitForClickable(jsonKey, timeout, sleepTime).click();
        log.info("{} element clicked", jsonKey);
    }

    public void clickIfExists(String jsonKey) {
        var presenceOfElm = new PresenceHelper();
        var by = getByValue(jsonKey);
        try {
            presenceOfElm.waitUntilPresence(by);
            clickElement(jsonKey);
        } catch (Exception e) {
            log.info("Element {} not present, not clicking", jsonKey);
        }
    }

    public void clickIfExists(String jsonKey, int timeout) {
        var presenceOfElm = new PresenceHelper();
        var by = getByValue(jsonKey);
        try {
            presenceOfElm.waitUntilPresence(by, timeout);
            clickElement(jsonKey, timeout);
        } catch (Exception e) {
            log.info("Element {} not present, not clicking", jsonKey);
        }
    }

    public void clickThePoint(int width, int height) {
        var driver = PlatformManager.getInstances().getDriver();
        Actions actions = new Actions(driver);
        actions.moveByOffset(width, height).click().perform();
        log.info("{} width and {} point clicked", width, height);
    }
}
