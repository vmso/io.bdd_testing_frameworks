package helper;

import elements.GetElement;
import exceptions.FileNotFound;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import platform.manager.PlatformManager;

public class ScrollHelper extends GetElement {

    public void scrollToElement(String jsonKey) throws FileNotFound {
        WebElement element = getWebElement(jsonKey);
        var driver = PlatformManager.getInstances().getDriver();
        
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void scrollToElement(String jsonKey, int timeOut) throws FileNotFound {
        WebElement element = getWebElement(jsonKey);
        var driver = PlatformManager.getInstances().getDriver();
        
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void scrollUp() {
        var driver = PlatformManager.getInstances().getDriver();
        var size = driver.manage().window().getSize();
        var startX = size.getWidth() / 2;
        var startY = (int) (size.getHeight() * 0.8);
        var endY = (int) (size.getHeight() * 0.2);

        Actions actions = new Actions(driver);
        actions.moveByOffset(startX, startY)
                .clickAndHold()
                .moveByOffset(0, endY - startY)
                .release()
                .perform();
    }

    public void scrollDown() {
        var driver = PlatformManager.getInstances().getDriver();
        var size = driver.manage().window().getSize();
        var startX = size.getWidth() / 2;
        var startY = (int) (size.getHeight() * 0.2);
        var endY = (int) (size.getHeight() * 0.8);

        Actions actions = new Actions(driver);
        actions.moveByOffset(startX, startY)
                .clickAndHold()
                .moveByOffset(0, endY - startY)
                .release()
                .perform();
    }
}
