package helpers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class VisibleHelper extends WaitHelper {

    public WebElement visibleHelper(String jsonHelper) {
        var by = getByValue(jsonHelper);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement visibleHelper(WebElement elm) {
        return getWait().until(ExpectedConditions.visibilityOf(elm));
    }

    public WebElement visibleHelper(String jsonHelper, long timeout) {
        var by = getByValue(jsonHelper);
        return getWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement visibleHelper(WebElement elm, long timeout) {
        return getWait(timeout).until(ExpectedConditions.visibilityOf(elm));
    }

    public WebElement visibleHelper(String jsonHelper, long timeout, long sleepInMillis) {
        var by = getByValue(jsonHelper);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement visibleHelper(WebElement elm, long timeout, long sleepInMillis) {
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.visibilityOf(elm));
    }

    public List<WebElement> visibleHelperForAllElements(String jsonHelper) {
        var by = getByValue(jsonHelper);
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public List<WebElement> visibleHelperForAllElements(List<WebElement> elm) {
        return getWait().until(ExpectedConditions.visibilityOfAllElements(elm));
    }

    public List<WebElement> visibleHelperForAllElements(String jsonHelper, long timeout) {
        var by = getByValue(jsonHelper);
        return getWait(timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public List<WebElement> visibleHelperForAllElements(List<WebElement> elm, long timeout) {
        return getWait(timeout).until(ExpectedConditions.visibilityOfAllElements(elm));
    }

    public List<WebElement> visibleHelperForAllElements(String jsonHelper, long timeout, long sleepInMillis) {
        var by = getByValue(jsonHelper);
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public List<WebElement> visibleHelperForAllElements(List<WebElement> elm, long timeout, long sleepInMillis) {
        return getWait(timeout).until(ExpectedConditions.visibilityOfAllElements(elm));
    }

}
