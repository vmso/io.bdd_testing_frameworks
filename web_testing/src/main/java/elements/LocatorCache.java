package elements;

import base.GetFileName;
import configuration.Configuration;
import json.UIProjectJsonReader;
import org.openqa.selenium.By;
import utils.ReuseStoreData;

import java.util.Map;

/**
 * Handles caching of locators to improve performance.
 * Separates caching logic from locator creation logic.
 */
public class LocatorCache {
    
    /**
     * Gets a cached locator or creates and caches a new one.
     * 
     * @param jsonKey the key to identify the locator
     * @return the By locator
     */
    public static By getOrCreateLocator(String jsonKey) {
        // Check cache first
        var cachedLocator = ReuseStoreData.get(jsonKey);
        if (cachedLocator != null) {
            return (By) cachedLocator;
        }
        
        // Create new locator and cache it
        var locator = createLocatorFromJson(jsonKey);
        ReuseStoreData.put(jsonKey, locator);
        return locator;
    }
    
    /**
     * Creates a locator from JSON configuration.
     * 
     * @param jsonKey the JSON key for the locator
     * @return the By locator
     */
    private static By createLocatorFromJson(String jsonKey) {
        var filePath = GetFileName.getInstance().getFileName();
        var locatorFolder = Configuration.getInstance().getStringValueOfProp("locator_folder");
        filePath = locatorFolder != null ? locatorFolder + "/" + filePath + ".json" : "locators/" + filePath + ".json";
        
        var jsonReader = new UIProjectJsonReader();
        var jsonMap = jsonReader.getJsonAsMapStringObject(filePath, jsonKey);
        
        return LocatorFactory.createLocator(jsonMap, jsonKey, new WebDriverElementFinder());
    }
    
    /**
     * Clears the locator cache.
     */
    public static void clearCache() {
        // Note: This would need to be implemented in ReuseStoreData
        // For now, we'll rely on the existing cache management
    }
} 