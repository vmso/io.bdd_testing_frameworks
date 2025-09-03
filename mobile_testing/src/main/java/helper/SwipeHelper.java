package helper;

import exceptions.FileNotFound;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import platform.manager.PlatformManager;

public class SwipeHelper extends GetElementHelper {

    public void swipeUp() {
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

    public void swipeDown() {
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

    public void swipeLeft() {
        var driver = PlatformManager.getInstances().getDriver();
        var size = driver.manage().window().getSize();
        var startX = (int) (size.getWidth() * 0.8);
        var startY = size.getHeight() / 2;
        var endX = (int) (size.getWidth() * 0.2);

        Actions actions = new Actions(driver);
        actions.moveByOffset(startX, startY)
                .clickAndHold()
                .moveByOffset(endX - startX, 0)
                .release()
                .perform();
    }

    public void swipeRight() {
        var driver = PlatformManager.getInstances().getDriver();
        var size = driver.manage().window().getSize();
        var startX = (int) (size.getWidth() * 0.2);
        var startY = size.getHeight() / 2;
        var endX = (int) (size.getWidth() * 0.8);

        Actions actions = new Actions(driver);
        actions.moveByOffset(startX, startY)
                .clickAndHold()
                .moveByOffset(endX - startX, 0)
                .release()
                .perform();
    }

    public void swipeToElement(String sourceElmKey) throws FileNotFound {
        WebElement element = getElementWithWait(sourceElmKey);
        var driver = PlatformManager.getInstances().getDriver();
        
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void swipeToElement(String sourceElmKey, int timeOut) throws FileNotFound {
        WebElement element = getElementWithWait(sourceElmKey, timeOut);
        var driver = PlatformManager.getInstances().getDriver();
        
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
