package helpers;

import elements.GetElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetElementHelper extends GetElement {
    private PresenceHelper presenceHelper;
    private ScrollHelper scrollHelper;
    private VisibleHelper visibleHelper;
    private ClickableHelper clickableHelper;

    public GetElementHelper() {
        presenceHelper = new PresenceHelper();
        scrollHelper = new ScrollHelper();
        visibleHelper = new VisibleHelper();
        clickableHelper = new ClickableHelper();
    }

    public WebElement getElementWithWait(String jsonKey) {
        return getElementWithWait(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebElement getClickableElement(String jsonKey) {
        return getClickableElement(jsonKey, DEFAULT_WAIT);
    }

    public WebElement getClickableElement(By by) {
        return getClickableElement(by, DEFAULT_WAIT);
    }


    public WebElement getClickableElement(By by, long timeout) {
        presenceHelper.presenceWait(by, timeout);
        visibleHelper.waitVisibleOfElement(by, timeout);
        scrollHelper.scrollToElement(by);
        return clickableHelper.elementToBeClickable(by);
    }

    public WebElement getClickableElement(String jsonKey, long timeout) {
        presenceHelper.presenceWait(jsonKey, timeout);
        visibleHelper.waitVisibleOfElement(jsonKey, timeout);
        scrollHelper.scrollToElement(jsonKey);
        return clickableHelper.elementToBeClickable(jsonKey, timeout);
    }

    public WebElement getClickableElement(String jsonKey, long timeout, long sleepMillis) {
        presenceHelper.presenceWait(jsonKey, timeout, sleepMillis);
        visibleHelper.waitVisibleOfElement(jsonKey, timeout, sleepMillis);
        scrollHelper.scrollToElement(jsonKey);
        return clickableHelper.elementToBeClickable(jsonKey, timeout, sleepMillis);
    }

    public WebElement getElementWithWait(String jsonKey, long timeout) {
        return getElementWithWait(jsonKey, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebElement getElementWithWait(String jsonKey, long timeout, long sleepMils) {
        return presenceHelper.presenceWait(jsonKey, timeout, sleepMils);
    }

    public WebElement getElementWithoutWait(String jsonKey) {
        return getElement(jsonKey);
    }

    public WebElement getElementWithoutWait(By by) {
        return getElement(by);
    }

    public List<WebElement> getElementsWithoutWait(String jsonKey) {
        return getElements(jsonKey);
    }

    public List<WebElement> getElementsWithoutWait(By by) {
        return getElements(by);
    }

    public List<WebElement> getElementsWithWait(String jsonKey, long timeout, long sleepMillis) {
        return presenceHelper.presenceWaitForAllElements(jsonKey, timeout, sleepMillis);
    }

    public List<WebElement> getElementsWithWait(By by, long timeout, long sleepMillis) {
        return presenceHelper.presenceWaitForAllElements(by, timeout, sleepMillis);
    }

    public List<WebElement> getElementsWitWait(String jsonKey, long timeout) {
        return getElementsWithWait(jsonKey, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public List<WebElement> getElementsWithWait(By by, long timeout) {
        return getElementsWithWait(by, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public List<WebElement> getElementsWitWait(String jsonKey) {
        return getElementsWithWait(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public List<WebElement> getElementsWithWait(By by) {
        return getElementsWithWait(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }


    public WebElement getVisibleElement(String jsonKey, long timeout, long sleepMillis) {
        presenceHelper.presenceWait(jsonKey, timeout, sleepMillis);
        scrollHelper.scrollToElement(jsonKey);
        return visibleHelper.waitVisibleOfElement(jsonKey, timeout, sleepMillis);
    }

    public WebElement getVisibleElement(String jsonKey, long timeout) {
        return getVisibleElement(jsonKey, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebElement getVisibleElement(String jsonKey) {
        return getVisibleElement(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebElement getVisibleElement(By by, long timeout, long sleepMillis) {
        presenceHelper.presenceWait(by, timeout, sleepMillis);
        scrollHelper.scrollToElement(by);
        return visibleHelper.waitVisibleOfElement(by, timeout, sleepMillis);
    }

    public WebElement getVisibleElement(By by, long timeout) {
        return getVisibleElement(by, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebElement getVisibleElement(By by) {
        return getVisibleElement(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

}
