package elements;

import org.openqa.selenium.By;

/**
 * Strategy interface for different locator types.
 * Follows the Strategy pattern to allow different locator implementations.
 */
public interface LocatorStrategy {
    
    /**
     * Creates a By locator based on the provided value.
     * 
     * @param value the locator value (e.g., "button", "//div[@class='example']")
     * @return By locator instance
     */
    By createLocator(String value);
    
    /**
     * Gets the strategy name for identification.
     * 
     * @return strategy name
     */
    String getStrategyName();
} 