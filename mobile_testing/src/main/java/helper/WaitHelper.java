package helper;

import elements.GetBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.manager.PlatformManager;

public class WaitHelper  extends GetBy {

    public static final int DEFAULT_SLEEP_IN_MILLIS = 30;
    public static final int DEFAULT_TIME_OUT = 15;

    public WebDriverWait getWebDriverWait(int timeout) {
        return getWebDriverWait(timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebDriverWait getWebDriverWait() {
        return getWebDriverWait(DEFAULT_TIME_OUT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public WebDriverWait getWebDriverWait(int timeout, int sleepInMillis) {
        WebDriver driver = PlatformManager.getInstances().getDriver();
        return new WebDriverWait(driver, timeout, sleepInMillis);
    }
}
