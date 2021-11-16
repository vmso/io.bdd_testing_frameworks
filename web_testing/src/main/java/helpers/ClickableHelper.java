package helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickableHelper extends WaitHelper {

    public WebElement clickableHelper(String jsonHelper) {
        var by = getByValue(jsonHelper);
        return getWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement clickableHelper(WebElement elm) {
        return getWait().until(ExpectedConditions.elementToBeClickable(elm));
    }

    public WebElement clickableHelper(String jsonHelper, long timeout) {
        var by = getByValue(jsonHelper);
        return getWait(timeout).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement clickableHelper(WebElement elm, long timeout) {
        return getWait(timeout).until(ExpectedConditions.elementToBeClickable(elm));
    }

    public WebElement clickableHelper(String jsonHelper, long timeout, long sleepInMillis) {
        var by = getByValue(jsonHelper);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement clickableHelper(WebElement elm, long timeout, long sleepInMillis) {
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.elementToBeClickable(elm));
    }

}
