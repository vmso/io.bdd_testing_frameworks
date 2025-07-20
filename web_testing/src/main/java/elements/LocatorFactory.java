package elements;

import elements.strategies.RelativeLocatorStrategy;
import elements.strategies.StandardLocatorStrategy;
import enums.LocatorType;
import exceptions.KeywordNotFoundException;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory class for creating different types of locators.
 * Follows the Factory pattern to encapsulate locator creation logic.
 */
public class LocatorFactory {
    
    /**
     * Creates a standard locator strategy.
     * 
     * @param locatorType the type of locator
     * @return StandardLocatorStrategy instance
     */
    public static StandardLocatorStrategy createStandardStrategy(LocatorType locatorType) {
        return new StandardLocatorStrategy(locatorType);
    }
    
    /**
     * Creates a relative locator strategy.
     * 
     * @param relativeParams the parameters for relative positioning
     * @param jsonKey the JSON key for error reporting
     * @return RelativeLocatorStrategy instance
     */
    public static RelativeLocatorStrategy createRelativeStrategy(Map<String, Object> relativeParams, String jsonKey) {
        return new RelativeLocatorStrategy(relativeParams, jsonKey);
    }
    
    /**
     * Creates a By locator from a standard locator configuration.
     * 
     * @param locatorMap the locator configuration map
     * @param jsonKey the JSON key for error reporting
     * @return By locator instance
     */
    public static By createStandardLocator(Map<String, String> locatorMap, String jsonKey) {
        LocatorType locatorType = getLocatorType(locatorMap, jsonKey);
        String locatorValue = getLocatorValue(locatorMap, jsonKey);
        
        StandardLocatorStrategy strategy = createStandardStrategy(locatorType);
        return strategy.createLocator(locatorValue);
    }
    
    /**
     * Creates a By locator from a relative locator configuration.
     * 
     * @param jsonMap the JSON configuration map
     * @param jsonKey the JSON key for error reporting
     * @param elementFinder the element finder for locating the first element
     * @return By locator instance
     */
    public static By createRelativeLocator(Map<String, Object> jsonMap, String jsonKey, ElementFinder elementFinder) {
        Map<String, Object> relativeParams = getRelativeLocatorsFromMap(jsonMap, jsonKey);
        
        // Get first locator
        Map<String, String> firstLocator = getLocatorWithKey(relativeParams, "firstLocator", jsonKey);
        By firstBy = createStandardLocator(firstLocator, jsonKey);
        
        // Get second locator
        Map<String, String> secondLocator = getLocatorWithKey(relativeParams, "secondLocator", jsonKey);
        By secondBy = createStandardLocator(secondLocator, jsonKey);
        
        // Find first element
        var firstElement = elementFinder.findElement(firstBy);
        
        // Create relative locator
        RelativeLocatorStrategy strategy = createRelativeStrategy(jsonMap, jsonKey);
        return strategy.createRelativeLocator(firstElement, secondBy);
    }
    
    /**
     * Creates a By locator from JSON configuration.
     * Handles both standard and relative locators.
     * 
     * @param jsonMap the JSON configuration map
     * @param jsonKey the JSON key for error reporting
     * @param elementFinder the element finder for relative locators
     * @return By locator instance
     */
    public static By createLocator(Map<String, Object> jsonMap, String jsonKey, ElementFinder elementFinder) {
        var isRelative = Boolean.parseBoolean(String.valueOf(jsonMap.get("isRelative")));
        
        if (isRelative) {
            return createRelativeLocator(jsonMap, jsonKey, elementFinder);
        } else {
            Map<String, String> locatorMap = new HashMap<>();
            jsonMap.forEach((k, v) -> locatorMap.put(k, String.valueOf(v)));
            return createStandardLocator(locatorMap, jsonKey);
        }
    }
    
    private static LocatorType getLocatorType(Map<String, String> locator, String jsonKey) {
        var locatorType = locator.get("locatorType");
        if (locatorType != null) {
            return LocatorType.valueOf(locatorType);
        } else {
            throw new KeywordNotFoundException("locatorType", jsonKey);
        }
    }
    
    private static String getLocatorValue(Map<String, String> locator, String jsonKey) {
        var locatorValue = locator.get("locatorValue");
        if (locatorValue != null) {
            return locatorValue;
        } else {
            throw new KeywordNotFoundException("locatorValue", jsonKey);
        }
    }
    
    private static Map<String, Object> getRelativeLocatorsFromMap(Map<String, Object> params, String jsonKey) {
        var locators = params.get("locators");
        if (locators != null) {
            @SuppressWarnings("unchecked")
            var locatorsMap = (Map<String, Object>) locators;
            return locatorsMap;
        } else {
            throw new KeywordNotFoundException("locators", jsonKey);
        }
    }
    
    private static Map<String, String> getLocatorWithKey(Map<String, Object> params, String locatorKey, String jsonKey) {
        var locator = params.get(locatorKey);
        if (locator != null) {
            @SuppressWarnings("unchecked")
            var locatorMap = (Map<String, String>) locator;
            return locatorMap;
        } else {
            throw new KeywordNotFoundException(locatorKey, jsonKey);
        }
    }
} 