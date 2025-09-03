package elements;

import exceptions.FileNotFound;
import org.openqa.selenium.By;

/**
 * Refactored GetBy class that uses the new locator architecture.
 * Now focuses solely on getting By locators from JSON keys.
 */
public abstract class GetBy {

    protected long DEFAULT_WAIT = 15;
    protected long DEFAULT_SLEEP_IN_MILLIS = 500;

    /**
     * Gets a By locator for the given JSON key.
     * Uses the new LocatorCache for improved performance and separation of concerns.
     * 
     * @param jsonKey the JSON key for the locator
     * @return By locator instance
     * @throws FileNotFound if the locator file is not found
     */
    public By getByValue(String jsonKey) throws FileNotFound {
        return LocatorCache.getOrCreateLocator(jsonKey);
    }
}
