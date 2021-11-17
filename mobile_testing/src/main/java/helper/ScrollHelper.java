package helper;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import platform.manager.PlatformManager;

import java.util.HashMap;

public class ScrollHelper extends TouchHelper {

    /**
     * @param jsonKey if driver is an Android driver, jsonKey is text of the element
     *                if driver is an iOS driver, jsonKey is locator key of element
     */
    protected void scrollToElement(String jsonKey) {
        var platform = PlatformManager.getInstances().getPlatform();
        switch (platform) {
            case ANDROID -> androidScroll(jsonKey);
            case IOS -> iosScroll(jsonKey);
        }
    }

    private void androidScroll(String text) {
        var driver = (AndroidDriver<MobileElement>) PlatformManager.getInstances().getDriver();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + text + "\").instance(0))");
    }

    private void iosScroll(String jsonKey) {
        var scrollObject = new HashMap<>();
        var elm = getElementWithWait(jsonKey);
        var js = new JavascriptExecutorHelper().getJsExecutor();
        scrollObject.put("direction", "down");
        scrollObject.put("element", elm.getId());
        js.executeScript("mobile: swipe", scrollObject);
    }
}
