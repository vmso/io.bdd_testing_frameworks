package helpers;

import driver.DriverManager;
import org.openqa.selenium.*;
import utils.StoreApiInfo;

import static enums.SwitchEnums.*;
import static org.openqa.selenium.WindowType.*;

public class SwitchHelper extends GetElementHelper {

    private WebDriver driver;

    public SwitchHelper() {
        driver = DriverManager.getInstances().getDriver();
    }

    public void switchToWindow() {
        storeCurrentWindowHandler();
        driver.getWindowHandles().forEach(w -> driver.switchTo().window(w));
    }

    public void switchToNewWindow() {
        storeCurrentWindowHandler();
        driver.switchTo().newWindow(WINDOW);
    }

    public void switchToNewTab() {
        storeCurrentWindowHandler();
        driver.switchTo().newWindow(TAB);
    }

    public void switchToDefaultWindow() {
        var defaultWindowHandle = String.valueOf(StoreApiInfo.get(DEFAULT_WINDOW.getValue()));
        driver.switchTo().window(defaultWindowHandle);
    }

    private void storeCurrentWindowHandler() {
        var currentWindowHandler = driver.getWindowHandle();
        StoreApiInfo.put(DEFAULT_WINDOW.getValue(), currentWindowHandler);
    }

    public void switchToIframe(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToIframe(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    public void switchToIframe(By by) {
        var elm = getElementWithWait(by);
        driver.switchTo().frame(elm);
    }

    public void switchToIframeWithElm(String jsonKey) {
        var elm = getElementWithWait(jsonKey);
        driver.switchTo().frame(elm);
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    private void switchToAlert() {
        var alert = driver.switchTo().alert();
        StoreApiInfo.put(ALERT.getValue(), alert);
    }

    public void switchToAlertAndDismiss() {
        switchToAlert();
        ((Alert) StoreApiInfo.get(ALERT.getValue())).dismiss();
    }

    public void switchToAlertAndAccept() {
        switchToAlert();
        ((Alert) StoreApiInfo.get(ALERT.getValue())).accept();
    }

    public String switchToAlertAndGetText() {
        switchToAlert();
        return ((Alert) StoreApiInfo.get(ALERT.getValue())).getText();
    }

    public void switchToAlertAndSendKeys(String keys) {
        switchToAlert();
        ((Alert) StoreApiInfo.get(ALERT.getValue())).sendKeys(keys);
    }

    public void setActiveElement() {
        var activeElm = driver.switchTo().activeElement();
        StoreApiInfo.put(ACTIVE_ELEMENT.getValue(), activeElm);
    }

    public void sendTabKeyToActiveElement() {
        setActiveElement();
        ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).sendKeys(Keys.TAB);
    }

    public void sendKeysToActiveElement(String keys) {
        setActiveElement();
        ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).sendKeys(keys);
    }

    public void clickOnActiveElement() {
        setActiveElement();
        ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).click();
    }

    public void clearActiveElement() {
        setActiveElement();
        ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).clear();
    }

    public String getTextOfActiveElement() {
        setActiveElement();
        return ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).getText();
    }

    public void switchToActiveElmAndSendEnterKey() {
        setActiveElement();
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
    }

    public String getAttributeOfActiveElement(String attribute) {
        setActiveElement();
        return ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).getAttribute(attribute);
    }

    public String getCssValueOfActiveElement(String cssValue) {
        setActiveElement();
        return ((WebElement) StoreApiInfo.get(ACTIVE_ELEMENT.getValue())).getAttribute(cssValue);
    }

}
