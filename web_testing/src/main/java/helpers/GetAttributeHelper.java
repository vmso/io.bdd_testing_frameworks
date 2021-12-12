package helpers;

import org.openqa.selenium.By;

public class GetAttributeHelper extends GetElementHelper {


    public String getTexWithWait(String jsonKey, long timeout, long sleepMillis) {
        return getVisibleElement(jsonKey, timeout, sleepMillis).getText();
    }

    public String getTexWithWait(String jsonKey, long timeout) {
        return getVisibleElement(jsonKey, timeout).getText();
    }

    public String getTexWithWait(String jsonKey) {
        return getVisibleElement(jsonKey).getText();
    }

    public String getTexWithWait(By by, long timeout, long sleepMillis) {
        return getVisibleElement(by, timeout, sleepMillis).getText();
    }

    public String getTexWithWait(By by, long timeout) {
        return getVisibleElement(by, timeout).getText();
    }

    public String getTexWithWait(By by) {
        return getVisibleElement(by).getText();
    }

    public String getTexWithoutWait(String jsonKey) {
        return getElementWithoutWait(jsonKey).getText();
    }

    public String getAttributeWithoutWait(String jsonKey, String attribute) {
        return getElementWithoutWait(jsonKey).getAttribute(attribute);
    }

    public String getAttributeWithDefaultWait(String jsonKey, String attribute) {
        return getVisibleElement(jsonKey).getAttribute(attribute);
    }

    public String getAttributeWithoutWait(By by, String attribute) {
        return getElementWithoutWait(by).getAttribute(attribute);
    }

    public String getAttributeWithDefaultWait(By by, String attribute) {
        return getVisibleElement(by).getAttribute(attribute);
    }

    public String getCssValueWithoutWait(By by, String cssName) {
        return getElementWithoutWait(by).getCssValue(cssName);
    }

    public String getCssValueWithoutWait(String jsonKey, String cssName) {
        return getVisibleElement(jsonKey).getCssValue(cssName);
    }

    public int getElementSize(String jsonKey) {
        return new PresenceHelper().presenceWaitForAllElements(jsonKey).size();
    }

    public int getElementSize(By by) {
        return new PresenceHelper().presenceWaitForAllElements(by).size();
    }

    public String getInnerTextOfElement(String jsonKey) {
        var by = getByValue(jsonKey);
        return getInnerTextOfElement(by);
    }

    public String getInnerTextOfElement(By by) {
        var elm = getElementWithWait(by);
        var executor = new JavascriptHelper();
        return String.valueOf(executor.getJavascriptResult("return arguments[0].innerText", elm));
    }
}
