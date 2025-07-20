package elements;

import driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * WebDriver-based implementation of ElementFinder.
 * Uses DriverManager to access the WebDriver instance.
 */
public class WebDriverElementFinder implements ElementFinder {
    
    @Override
    public WebElement findElement(By by) {
        return DriverManager.getInstance().getDriver().findElement(by);
    }
    
    @Override
    public List<WebElement> findElements(By by) {
        return DriverManager.getInstance().getDriver().findElements(by);
    }
} 