package helper;

import elements.GetElement;
import exceptions.FileNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.manager.PlatformManager;

import java.time.Duration;

public class ClickableHelper extends GetElement {
    public WebElement waitForClickable(String jsonKey, int timeOut) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait(timeOut).until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForClickable(String jsonKey) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForClickable(String jsonKey, int timeOut, int sleepInt) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait(timeOut,sleepInt).until(ExpectedConditions.elementToBeClickable(by));
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(10));
    }

    private WebDriverWait getWebDriverWait(int timeOut) {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(timeOut));
    }

    private WebDriverWait getWebDriverWait(int timeOut, int sleepInt) {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(sleepInt));
    }
}
