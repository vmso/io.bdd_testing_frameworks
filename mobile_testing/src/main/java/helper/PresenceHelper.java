package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.manager.PlatformManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PresenceHelper {
    public WebElement waitUntilPresence(By by, int timeout) {
        return getWebDriverWait(timeout)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitUntilPresence(By by) {
        return getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> waitUntilPresenceAll(By by, int timeout) {
        List<WebElement> webElements = getWebDriverWait(timeout)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        var webElementList = new ArrayList<WebElement>();
        webElements.stream().forEach(webElementList::add);
        return webElementList;
    }

    public List<WebElement> waitUntilPresenceAll(By by) {
        return waitUntilPresenceAll(by, 10);
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(10));
    }

    private WebDriverWait getWebDriverWait(int timeOut) {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(timeOut));
    }
}
