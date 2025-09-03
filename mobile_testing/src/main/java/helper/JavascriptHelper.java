package helper;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.ReuseStoreData;

public class JavascriptHelper {
    private AppiumDriver driver;

    public JavascriptHelper() {
        this.driver = (AppiumDriver) ReuseStoreData.get("driver");
    }

    public Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
