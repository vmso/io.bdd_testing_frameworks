package elements;

import exceptions.FileNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import platform.manager.PlatformManager;

import java.util.List;

public class GetElement extends GetBy {

    protected WebElement getWebElement(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return PlatformManager.getInstances().getDriver().findElement(by);
    }

    protected WebElement getWebElement(By by) {
        return PlatformManager.getInstances().getDriver().findElement(by);
    }

    protected List<WebElement> getWebElements(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return PlatformManager.getInstances().getDriver().findElements(by);
    }

    protected List<WebElement> getWebElements(By by) {
        return PlatformManager.getInstances().getDriver().findElements(by);
    }
}
