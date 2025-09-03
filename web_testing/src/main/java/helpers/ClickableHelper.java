package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;

/**
 * Helper class for element clickability operations.
 * Provides utility methods for waiting for elements to be clickable in the DOM.
 * Extends WaitHelper to inherit wait functionality and timeout configurations.
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public class ClickableHelper extends WaitHelper {

    private static final Logger logger = LogManager.getLogger(ClickableHelper.class);

    /**
     * Waits for an element to be clickable using JSON key with default timeout.
     * 
     * @param jsonHelper the JSON key for the locator
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if jsonHelper is null or empty
     */
    public WebElement elementToBeClickable(String jsonHelper) {
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for element to be clickable with JSON key: {}", jsonHelper);
            return getWait().until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            logger.error("Failed to wait for element to be clickable with JSON key: {}", jsonHelper, e);
            throw e;
        }
    }

    /**
     * Waits for an element to be clickable using By locator with default timeout and sleep interval.
     * 
     * @param by the By locator
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if by is null
     */
    public WebElement elementToBeClickable(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Waiting for element to be clickable with locator: {} using default timeout", by);
        return elementToBeClickable(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Waits for an element to be clickable using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if by is null or timeout is negative
     */
    public WebElement elementToBeClickable(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for element to be clickable with locator: {} for {} seconds", by, timeoutSeconds);
        return elementToBeClickable(by, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
    }

    /**
     * Waits for an element to be clickable using By locator with custom timeout and sleep interval.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement elementToBeClickable(By by, long timeoutSeconds, long sleepInMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for element to be clickable with locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepInMillis);
        return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.elementToBeClickable(by));
    }

    /**
     * Waits for a WebElement to be clickable with default timeout.
     * 
     * @param elm the WebElement to wait for
     * @return WebElement when clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if elm is null
     */
    public WebElement elementToBeClickable(WebElement elm) {
        if (elm == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        logger.debug("Waiting for WebElement to be clickable: {}", elm);
        return getWait().until(ExpectedConditions.elementToBeClickable(elm));
    }

    /**
     * Waits for an element to be clickable using JSON key with custom timeout.
     * 
     * @param jsonHelper the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if jsonHelper is null or empty, or timeout is negative
     */
    public WebElement elementToBeClickable(String jsonHelper, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for element to be clickable with JSON key: {} for {} seconds", jsonHelper, timeoutSeconds);
            return getWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            logger.error("Failed to wait for element to be clickable with JSON key: {} for {} seconds", jsonHelper, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Waits for a WebElement to be clickable with custom timeout.
     * 
     * @param elm the WebElement to wait for
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if elm is null or timeout is negative
     */
    public WebElement elementToBeClickable(WebElement elm, long timeoutSeconds) {
        if (elm == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for WebElement to be clickable: {} for {} seconds", elm, timeoutSeconds);
        return getWait(timeoutSeconds).until(ExpectedConditions.elementToBeClickable(elm));
    }

    /**
     * Waits for an element to be clickable using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonHelper the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when found and clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement elementToBeClickable(String jsonHelper, long timeoutSeconds, long sleepInMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for element to be clickable with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonHelper, timeoutSeconds, sleepInMillis);
            return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.elementToBeClickable(by));
        } catch (Exception e) {
            logger.error("Failed to wait for element to be clickable with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonHelper, timeoutSeconds, sleepInMillis, e);
            throw e;
        }
    }

    /**
     * Waits for a WebElement to be clickable with custom timeout and sleep interval.
     * 
     * @param elm the WebElement to wait for
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when clickable
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement elementToBeClickable(WebElement elm, long timeoutSeconds, long sleepInMillis) {
        if (elm == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for WebElement to be clickable: {} for {} seconds with {}ms sleep", 
                    elm, timeoutSeconds, sleepInMillis);
        return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.elementToBeClickable(elm));
    }
}
