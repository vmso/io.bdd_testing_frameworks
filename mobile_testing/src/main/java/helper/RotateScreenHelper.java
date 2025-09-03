package helper;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ReuseStoreData;

public class RotateScreenHelper {
    AppiumDriver driver;

    public RotateScreenHelper() {
        this.driver = (AppiumDriver) ReuseStoreData.get("driver");
    }

    public void rotateScreen() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(org.openqa.selenium.By.xpath("//*"))).clickAndHold().moveByOffset(0, 100).release().perform();
    }
}
