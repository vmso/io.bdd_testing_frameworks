package elements;

import org.openqa.selenium.By;

/**
 * Factory class for creating custom locator strategies.
 * Provides convenient methods for creating specialized locators.
 */
public class CustomBys {

    /**
     * Creates a locator that finds elements by their exact text content.
     * Uses XPath to find elements with matching text.
     * 
     * @param text the exact text to search for
     * @return By locator for text-based element finding
     */
    public static By byText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        return new Text(text);
    }
    
    /**
     * Creates a locator that finds elements containing the specified text.
     * Uses XPath with contains() function for partial text matching.
     * 
     * @param text the text to search for (partial match)
     * @return By locator for partial text-based element finding
     */
    public static By byPartialText(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        return By.xpath(String.format("//*[contains(text(),'%s')]", text));
    }
    
    /**
     * Creates a locator that finds elements with a specific attribute value.
     * 
     * @param attribute the attribute name
     * @param value the attribute value
     * @return By locator for attribute-based element finding
     */
    public static By byAttribute(String attribute, String value) {
        if (attribute == null || attribute.trim().isEmpty()) {
            throw new IllegalArgumentException("Attribute cannot be null or empty");
        }
        if (value == null) {
            throw new IllegalArgumentException("Value cannot be null");
        }
        return By.xpath(String.format("//*[@%s='%s']", attribute, value));
    }
}
