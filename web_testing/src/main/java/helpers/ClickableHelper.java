package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickableHelper extends WaitHelper {

    public WebElement elementToBeClickable(String jsonHelper) {
        var by = getByValue(jsonHelper);
        return getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement elementToBeClickable(By by) {
        return getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement elementToBeClickable(WebElement elm) {
        return getWait().until(ExpectedConditions.elementToBeClickable(elm));
    }

    public WebElement elementToBeClickable(String jsonHelper, long timeout) {
        var by = getByValue(jsonHelper);
        return getWait(timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement elementToBeClickable(WebElement elm, long timeout) {
        return getWait(timeout).until(ExpectedConditions.elementToBeClickable(elm));
    }

    public WebElement elementToBeClickable(String jsonHelper, long timeout, long sleepInMillis) {
        var by = getByValue(jsonHelper);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement elementToBeClickable(WebElement elm, long timeout, long sleepInMillis) {
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.elementToBeClickable(elm));
    }

}
