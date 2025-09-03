package helper;

import elements.GetElement;
import exceptions.FileNotFound;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import platform.manager.PlatformManager;

import java.time.Duration;
import java.util.List;

public class GetElementHelper extends GetElement {

    protected WebElement getElementWithWait(String jsonKey) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement getElementWithWait(String jsonKey, int timeOut) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait(timeOut).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected WebElement getElementWithoutWait(String jsonKey) throws FileNotFound {
        return getWebElement(jsonKey);
    }

    protected List<WebElement> getElementsWithWait(String jsonKey) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> getElementsWithWait(String jsonKey, int timeOut) throws FileNotFound {
        By by = getByValue(jsonKey);
        return getWebDriverWait(timeOut).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    protected List<WebElement> getElementsWithoutWait(String jsonKey) throws FileNotFound {
        return getWebElements(jsonKey);
    }

    private WebDriverWait getWebDriverWait() {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(10));
    }

    private WebDriverWait getWebDriverWait(int timeOut) {
        return new WebDriverWait(PlatformManager.getInstances().getDriver(), Duration.ofSeconds(timeOut));
    }
}
