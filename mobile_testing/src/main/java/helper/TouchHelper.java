package helper;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import platform.manager.PlatformManager;

public class TouchHelper extends GetElementHelper {

    protected Actions getActions() {
        return new Actions(PlatformManager.getInstances().getDriver());
    }

    public void tap(WebElement element) {
        getActions().moveToElement(element).click().perform();
    }

    public void longPress(WebElement element) {
        getActions().moveToElement(element).clickAndHold().pause(java.time.Duration.ofSeconds(2)).release().perform();
    }

    public void moveTo(WebElement element) {
        getActions().moveToElement(element).perform();
    }

    public void dragAndDrop(WebElement source, WebElement target) {
        getActions().clickAndHold(source).moveToElement(target).release().perform();
    }

    // Add more gesture utilities as needed
}
