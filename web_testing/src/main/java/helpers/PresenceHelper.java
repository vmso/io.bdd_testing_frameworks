package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PresenceHelper extends WaitHelper {

    private final Logger log = LogManager.getLogger(PresenceHelper.class);

    public WebElement presenceWait(String jsonKey) {
        var by = getByValue(jsonKey);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(By by) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(String jsonKey, long timeout) {
        var by = getByValue(jsonKey);
        return getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(By by, long timeout) {
        return getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(String jsonKey, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(By by, long timeout, long sleepInMillis) {
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> presenceWaitForAllElements(String jsonKey) {
        var by = getByValue(jsonKey);
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(By by) {
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(String jsonKey, long timeout) {
        var by = getByValue(jsonKey);
        return getWait(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(By by, long timeout) {
        return getWait(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(String jsonKey, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(By by, long timeout, long sleepInMillis) {
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }


    public boolean isPresence(By by, long second) {
        try {
            log.info(" Wait presence of {} element for {} seconds", by, second);
            return presenceWait(by, second) != null;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isPresence(String jsonKey, long second) {
        try {
            log.info(" Wait presence of {} element for {} seconds", jsonKey, second);
            return presenceWait(jsonKey, second) != null;
        } catch (Exception ex) {
            return false;
        }
    }
}
