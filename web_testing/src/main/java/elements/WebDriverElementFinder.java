package elements;

import driver.DriverManager;
import helper.selfheal.SelfHealingEngine;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

/**
 * WebDriver-based implementation of ElementFinder.
 * Uses DriverManager to access the WebDriver instance.
 */
public class WebDriverElementFinder implements ElementFinder {
    
    @Override
    public WebElement findElement(By by) {
        try {
            return DriverManager.getInstance().getDriver().findElement(by);
        } catch (NoSuchElementException | InvalidSelectorException e) {
            var driver = DriverManager.getInstance().getDriver();
            SelfHealingEngine engine = new SelfHealingEngine();
            var result = engine.onFailure(driver, by, "findElement", by.toString());
            if (result.winner.isPresent()) {
                return driver.findElement(result.winner.get());
            } else {
                throw e;
            }
        }
    }
    
    @Override
    public List<WebElement> findElements(By by) {
        var driver = DriverManager.getInstance().getDriver();
        List<WebElement> elements = driver.findElements(by);
        if (elements != null && !elements.isEmpty()) {
            return elements;
        }
        // Attempt healing when the list is empty
        SelfHealingEngine engine = new SelfHealingEngine();
        var result = engine.onFailure(driver, by, "findElements", by.toString());
        if (result.winner.isPresent()) {
            List<WebElement> healed = driver.findElements(result.winner.get());
            if (healed != null && !healed.isEmpty()) {
                return healed;
            }
        }
        return elements;
    }
} 