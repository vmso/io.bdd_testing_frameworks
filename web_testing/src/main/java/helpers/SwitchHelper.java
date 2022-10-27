package helpers;

import driver.DriverManager;
import org.openqa.selenium.*;
import utils.ReuseStoreData;

import static enums.SwitchEnums.*;
import static org.openqa.selenium.WindowType.*;

public class SwitchHelper extends GetElementHelper {

    public void switchToWindow() {
        storeCurrentWindowHandler();
        DriverManager.getInstances().getDriver().getWindowHandles()
                .forEach(w -> DriverManager.getInstances().getDriver().switchTo().window(w));
    }

    public void switchToNewWindow() {
        storeCurrentWindowHandler();
        DriverManager.getInstances().getDriver().switchTo().newWindow(WINDOW);
    }

    public void switchToNewTab() {
        storeCurrentWindowHandler();
        DriverManager.getInstances().getDriver().switchTo().newWindow(TAB);
    }

    public void switchToDefaultWindow() {
        var defaultWindowHandle = String.valueOf(ReuseStoreData.get(DEFAULT_WINDOW.getValue()));
        DriverManager.getInstances().getDriver().switchTo().window(defaultWindowHandle);
    }

    private void storeCurrentWindowHandler() {
        var currentWindowHandler = DriverManager.getInstances().getDriver().getWindowHandle();
        ReuseStoreData.put(DEFAULT_WINDOW.getValue(), currentWindowHandler);
    }

    public void switchToIframe(int index) {
        DriverManager.getInstances().getDriver().switchTo().frame(index);
    }

    public void switchToIframe(String nameOrId) {
        DriverManager.getInstances().getDriver().switchTo().frame(nameOrId);
    }

    public void switchToParentIframe() {
        DriverManager.getInstances().getDriver().switchTo().parentFrame();
    }

    public void switchToIframe(By by) {
        var elm = getElementWithWait(by);
        DriverManager.getInstances().getDriver().switchTo().frame(elm);
    }

    public void switchToIframeWithElm(String jsonKey) {
        var by = getByValue(jsonKey);
        switchToIframe(by);
    }

    public void switchToDefaultContent() {
        DriverManager.getInstances().getDriver().switchTo().defaultContent();
    }

    private void switchToAlert() {
        AlertWaitHelper alertWaitHelper = new AlertWaitHelper();
        var alert = alertWaitHelper.waitForPresenceOfAlert(DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
        ReuseStoreData.put(ALERT.getValue(), alert);
    }

    public void switchToAlertAndDismiss() {
        switchToAlert();
        ((Alert) ReuseStoreData.get(ALERT.getValue())).dismiss();
    }

    public void switchToAlertAndAccept() {
        switchToAlert();
        ((Alert) ReuseStoreData.get(ALERT.getValue())).accept();
    }

    public String switchToAlertAndGetText() {
        switchToAlert();
        return ((Alert) ReuseStoreData.get(ALERT.getValue())).getText();
    }

    public void switchToAlertAndSendKeys(String keys) {
        switchToAlert();
        ((Alert) ReuseStoreData.get(ALERT.getValue())).sendKeys(keys);
    }

    public void setActiveElement() {
        var activeElm = DriverManager.getInstances().getDriver().switchTo().activeElement();
        ReuseStoreData.put(ACTIVE_ELEMENT.getValue(), activeElm);
    }

    public void sendTabKeyToActiveElement() {
        setActiveElement();
        ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).sendKeys(Keys.TAB);
    }

    public void sendKeysToActiveElement(String keys) {
        setActiveElement();
        ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).sendKeys(keys);
    }

    public void clickOnActiveElement() {
        setActiveElement();
        ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).click();
    }

    public void clearActiveElement() {
        setActiveElement();
        ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).clear();
    }

    public String getTextOfActiveElement() {
        setActiveElement();
        return ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).getText();
    }

    public void switchToActiveElmAndSendEnterKey() {
        setActiveElement();
        DriverManager.getInstances().getDriver().switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public String getAttributeOfActiveElement(String attribute) {
        setActiveElement();
        return ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).getAttribute(attribute);
    }

    public String getCssValueOfActiveElement(String cssValue) {
        setActiveElement();
        return ((WebElement) ReuseStoreData.get(ACTIVE_ELEMENT.getValue())).getAttribute(cssValue);
    }

}
