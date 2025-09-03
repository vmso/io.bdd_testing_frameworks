package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Interface for finding web elements.
 * Abstracts the WebDriver dependency for better testability.
 */
public interface ElementFinder {
    
    /**
     * Finds a single element using the given locator.
     * 
     * @param by the locator strategy
     * @return the found WebElement
     */
    WebElement findElement(By by);
    
    /**
     * Finds multiple elements using the given locator.
     * 
     * @param by the locator strategy
     * @return list of found WebElements
     */
    List<WebElement> findElements(By by);
} 