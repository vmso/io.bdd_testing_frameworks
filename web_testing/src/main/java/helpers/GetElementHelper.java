package helpers;

import elements.GetElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

/**
 * Helper class for element retrieval operations.
 * Provides utility methods for getting elements with various wait conditions.
 * Extends GetElement to inherit basic element finding functionality.
 * Integrates with PresenceHelper, ScrollHelper, VisibleHelper, and ClickableHelper
 * to provide comprehensive element retrieval capabilities.
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public class GetElementHelper extends GetElement {
    
    private static final Logger logger = LogManager.getLogger(GetElementHelper.class);
    
    private final PresenceHelper presenceHelper;
    private final ScrollHelper scrollHelper;
    private final VisibleHelper visibleHelper;
    private final ClickableHelper clickableHelper;
    
    /**
     * Constructor initializes all helper classes for element operations.
     */
    public GetElementHelper() {
        presenceHelper = new PresenceHelper();
        scrollHelper = new ScrollHelper();
        visibleHelper = new VisibleHelper();
        clickableHelper = new ClickableHelper();
        logger.debug("GetElementHelper initialized with all helper classes");
    }

    /**
     * Gets an element with wait using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return WebElement when found
     * @throws TimeoutException if element is not found within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public WebElement getElementWithWait(String jsonKey) {
        logger.debug("Getting element with wait using JSON key: {} with default timeout", jsonKey);
        return getElementWithWait(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }
    
    /**
     * Gets a clickable element using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public WebElement getClickableElement(String jsonKey) {
        logger.debug("Getting clickable element using JSON key: {} with default timeout", jsonKey);
        return getClickableElement(jsonKey, DEFAULT_WAIT);
    }
    
    /**
     * Gets a clickable element using By locator with default timeout.
     * 
     * @param by the By locator
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if by is null
     */
    public WebElement getClickableElement(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Getting clickable element using By locator: {} with default timeout", by);
        return getClickableElement(by, DEFAULT_WAIT);
    }
    
    /**
     * Gets a clickable element using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getClickableElement(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting clickable element using By locator: {} for {} seconds", by, timeoutSeconds);
        return getClickableElement(by, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets a clickable element using By locator with custom timeout and sleep interval.
     * Ensures element is present, visible, scrolled to, and clickable.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getClickableElement(By by, long timeoutSeconds, long sleepInMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting clickable element using By locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepInMillis);
        
        try {
            presenceHelper.presenceWait(by, timeoutSeconds, sleepInMillis);
            visibleHelper.waitVisibleOfElement(by, timeoutSeconds, sleepInMillis);
        scrollHelper.scrollToElement(by);
            return clickableHelper.elementToBeClickable(by, timeoutSeconds, sleepInMillis);
        } catch (Exception e) {
            logger.error("Failed to get clickable element with By locator: {} for {} seconds", by, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Gets a clickable element using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getClickableElement(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting clickable element using JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
        return getClickableElement(jsonKey, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets a clickable element using JSON key with custom timeout and sleep interval.
     * Ensures element is present, visible, scrolled to, and clickable.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getClickableElement(String jsonKey, long timeoutSeconds, long sleepMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting clickable element using JSON key: {} for {} seconds with {}ms sleep", 
                    jsonKey, timeoutSeconds, sleepMillis);
        
        try {
            presenceHelper.presenceWait(jsonKey, timeoutSeconds, sleepMillis);
            visibleHelper.waitVisibleOfElement(jsonKey, timeoutSeconds, sleepMillis);
            scrollHelper.scrollToElement(jsonKey);
            return clickableHelper.elementToBeClickable(jsonKey, timeoutSeconds, sleepMillis);
        } catch (Exception e) {
            logger.error("Failed to get clickable element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Gets an element with wait using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found
     * @throws TimeoutException if element is not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getElementWithWait(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting element with wait using JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
        return getElementWithWait(jsonKey, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets an element with wait using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMils the sleep interval in milliseconds
     * @return WebElement when found
     * @throws TimeoutException if element is not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getElementWithWait(String jsonKey, long timeoutSeconds, long sleepMils) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMils < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting element with wait using JSON key: {} for {} seconds with {}ms sleep", 
                    jsonKey, timeoutSeconds, sleepMils);
        return presenceHelper.presenceWait(jsonKey, timeoutSeconds, sleepMils);
    }

    /**
     * Gets an element with wait using By locator with custom timeout and sleep interval.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMils the sleep interval in milliseconds
     * @return WebElement when found
     * @throws TimeoutException if element is not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getElementWithWait(By by, long timeoutSeconds, long sleepMils) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMils < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting element with wait using By locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepMils);
        return presenceHelper.presenceWait(by, timeoutSeconds, sleepMils);
    }

    /**
     * Gets an element with wait using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found
     * @throws TimeoutException if element is not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getElementWithWait(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting element with wait using By locator: {} for {} seconds", by, timeoutSeconds);
        return getElementWithWait(by, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets an element with wait using By locator with default timeout.
     * 
     * @param by the By locator
     * @return WebElement when found
     * @throws TimeoutException if element is not found within timeout
     * @throws IllegalArgumentException if by is null
     */
    public WebElement getElementWithWait(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Getting element with wait using By locator: {} with default timeout", by);
        return getElementWithWait(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets an element without wait using JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @return WebElement when found
     * @throws NoSuchElementException if element is not found
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public WebElement getElementWithoutWait(String jsonKey) {
        logger.debug("Getting element without wait using JSON key: {}", jsonKey);
        return getElement(jsonKey);
    }
    
    /**
     * Gets an element without wait using By locator.
     * 
     * @param by the By locator
     * @return WebElement when found
     * @throws NoSuchElementException if element is not found
     * @throws IllegalArgumentException if by is null
     */
    public WebElement getElementWithoutWait(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Getting element without wait using By locator: {}", by);
        return getElement(by);
    }
    
    /**
     * Gets elements without wait using JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @return List of WebElements when found
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public List<WebElement> getElementsWithoutWait(String jsonKey) {
        logger.debug("Getting elements without wait using JSON key: {}", jsonKey);
        return getElements(jsonKey);
    }
    
    /**
     * Gets elements without wait using By locator.
     * 
     * @param by the By locator
     * @return List of WebElements when found
     * @throws IllegalArgumentException if by is null
     */
    public List<WebElement> getElementsWithoutWait(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Getting elements without wait using By locator: {}", by);
        return getElements(by);
    }
    
    /**
     * Gets elements with wait using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> getElementsWithWait(String jsonKey, long timeoutSeconds, long sleepMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting elements with wait using JSON key: {} for {} seconds with {}ms sleep", 
                    jsonKey, timeoutSeconds, sleepMillis);
        return presenceHelper.presenceWaitForAllElements(jsonKey, timeoutSeconds, sleepMillis);
    }

    /**
     * Gets elements with wait using By locator with custom timeout and sleep interval.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> getElementsWithWait(By by, long timeoutSeconds, long sleepMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting elements with wait using By locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepMillis);
        return presenceHelper.presenceWaitForAllElements(by, timeoutSeconds, sleepMillis);
    }

    /**
     * Gets elements with wait using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> getElementsWitWait(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting elements with wait using JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
        return getElementsWithWait(jsonKey, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets elements with wait using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not found within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> getElementsWithWait(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting elements with wait using By locator: {} for {} seconds", by, timeoutSeconds);
        return getElementsWithWait(by, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets elements with wait using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not found within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public List<WebElement> getElementsWithWait(String jsonKey) {
        logger.debug("Getting elements with wait using JSON key: {} with default timeout", jsonKey);
        return getElementsWithWait(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets elements with wait using By locator with default timeout.
     * 
     * @param by the By locator
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not found within timeout
     * @throws IllegalArgumentException if by is null
     */
    public List<WebElement> getElementsWithWait(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Getting elements with wait using By locator: {} with default timeout", by);
        return getElementsWithWait(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }
    
    /**
     * Gets a visible element using JSON key with custom timeout and sleep interval.
     * Ensures element is present, scrolled to, and visible.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getVisibleElement(String jsonKey, long timeoutSeconds, long sleepMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting visible element using JSON key: {} for {} seconds with {}ms sleep", 
                    jsonKey, timeoutSeconds, sleepMillis);
        
        try {
            presenceHelper.presenceWait(jsonKey, timeoutSeconds, sleepMillis);
            scrollHelper.scrollToElement(jsonKey);
            return visibleHelper.waitVisibleOfElement(jsonKey, timeoutSeconds, sleepMillis);
        } catch (Exception e) {
            logger.error("Failed to get visible element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Gets a visible element using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getVisibleElement(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting visible element using JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
        return getVisibleElement(jsonKey, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets a visible element using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public WebElement getVisibleElement(String jsonKey) {
        logger.debug("Getting visible element using JSON key: {} with default timeout", jsonKey);
        return getVisibleElement(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets a visible element using By locator with custom timeout and sleep interval.
     * Ensures element is present, scrolled to, and visible.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getVisibleElement(By by, long timeoutSeconds, long sleepMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Getting visible element using By locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepMillis);
        
        try {
            presenceHelper.presenceWait(by, timeoutSeconds, sleepMillis);
            scrollHelper.scrollToElement(by);
            return visibleHelper.waitVisibleOfElement(by, timeoutSeconds, sleepMillis);
        } catch (Exception e) {
            logger.error("Failed to get visible element with By locator: {} for {} seconds", by, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Gets a visible element using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement getVisibleElement(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Getting visible element using By locator: {} for {} seconds", by, timeoutSeconds);
        return getVisibleElement(by, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Gets a visible element using By locator with default timeout.
     * 
     * @param by the By locator
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if by is null
     */
    public WebElement getVisibleElement(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Getting visible element using By locator: {} with default timeout", by);
        return getVisibleElement(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }
}
