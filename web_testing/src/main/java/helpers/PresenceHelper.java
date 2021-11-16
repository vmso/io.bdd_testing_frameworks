package helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PresenceHelper extends WaitHelper {

    public WebElement presenceWait(String jsonKey) {
        var by = getByValue(jsonKey);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(String jsonKey, long timeout) {
        var by = getByValue(jsonKey);
        return getWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement presenceWait(String jsonKey, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> presenceWaitForAllElements(String jsonKey) {
        var by = getByValue(jsonKey);
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(String jsonKey, long timeout) {
        var by = getByValue(jsonKey);
        return getWait(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> presenceWaitForAllElements(String jsonKey, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}
