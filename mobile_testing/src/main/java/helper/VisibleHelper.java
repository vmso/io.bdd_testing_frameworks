package helper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.manager.PlatformManager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VisibleHelper {
    public WebElement waitUntilVisible (By by, int timeout){
        return getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitUntilVisible (By by){
        return waitUntilVisible(by, 10);
    }

    public List<WebElement> waitUntilVisibleAll (By by, int timeout){
        List<WebElement> webElements = getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        var webElementList = new ArrayList<WebElement>();
        webElements.stream().forEach(webElementList::add);
        return webElementList;
    }

    public List<WebElement> waitUntilVisibleAll (By by){
        return waitUntilVisibleAll(by, 10);
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(10));
    }

    private WebDriverWait getWebDriverWait(int timeOut) {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(timeOut));
    }
}
