package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;

/**
 * Helper class for element presence operations.
 * Provides utility methods for waiting for elements to be present in the DOM.
 * Extends WaitHelper to inherit wait functionality and timeout configurations.
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public class PresenceHelper extends WaitHelper {

    private static final Logger logger = LogManager.getLogger(PresenceHelper.class);

    // Default timeout for quick presence checks
    private static final long DEFAULT_PRESENCE_CHECK_TIMEOUT = 5;

    /**
     * Waits for a single element to be present using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return WebElement when found
     * @throws TimeoutException if element is not present within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public WebElement presenceWait(String jsonKey) {
        try {
            By by = getByValue(jsonKey);
            logger.debug("Waiting for presence of element with JSON key: {}", jsonKey);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Failed to wait for presence of element with JSON key: {}", jsonKey, e);
            throw e;
        }
    }

    /**
     * Waits for a single element to be present using By locator with default timeout.
     * 
     * @param by the By locator
     * @return WebElement when found
     * @throws TimeoutException if element is not present within timeout
     * @throws IllegalArgumentException if by is null
     */
    public WebElement presenceWait(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Waiting for presence of element with locator: {}", by);
        return getWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for a single element to be present using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found
     * @throws TimeoutException if element is not present within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty, or timeout is negative
     */
    public WebElement presenceWait(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            By by = getByValue(jsonKey);
            logger.debug("Waiting for presence of element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
            return getWait(timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Failed to wait for presence of element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Waits for a single element to be present using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found
     * @throws TimeoutException if element is not present within timeout
     * @throws IllegalArgumentException if by is null or timeout is negative
     */
    public WebElement presenceWait(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for presence of element with locator: {} for {} seconds", by, timeoutSeconds);
        return getWait(timeoutSeconds).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for a single element to be present using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when found
     * @throws TimeoutException if element is not present within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement presenceWait(String jsonKey, long timeoutSeconds, long sleepInMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        try {
            By by = getByValue(jsonKey);
            logger.debug("Waiting for presence of element with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonKey, timeoutSeconds, sleepInMillis);
            return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Failed to wait for presence of element with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonKey, timeoutSeconds, sleepInMillis, e);
            throw e;
        }
    }

    /**
     * Waits for a single element to be present using By locator with custom timeout and sleep interval.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when found
     * @throws TimeoutException if element is not present within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement presenceWait(By by, long timeoutSeconds, long sleepInMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for presence of element with locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepInMillis);
        return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    /**
     * Waits for all elements to be present using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not present within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public List<WebElement> presenceWaitForAllElements(String jsonKey) {
        try {
            By by = getByValue(jsonKey);
            logger.debug("Waiting for presence of all elements with JSON key: {}", jsonKey);
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.error("Failed to wait for presence of all elements with JSON key: {}", jsonKey, e);
            throw e;
        }
    }

    /**
     * Waits for all elements to be present using By locator with default timeout.
     * 
     * @param by the By locator
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not present within timeout
     * @throws IllegalArgumentException if by is null
     */
    public List<WebElement> presenceWaitForAllElements(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Waiting for presence of all elements with locator: {}", by);
        return getWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * Waits for all elements to be present using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not present within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> presenceWaitForAllElements(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            By by = getByValue(jsonKey);
            logger.debug("Waiting for presence of all elements with JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
            return getWait(timeoutSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.error("Failed to wait for presence of all elements with JSON key: {} for {} seconds", jsonKey, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Waits for all elements to be present using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not present within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> presenceWaitForAllElements(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for presence of all elements with locator: {} for {} seconds", by, timeoutSeconds);
        return getWait(timeoutSeconds).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * Waits for all elements to be present using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not present within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> presenceWaitForAllElements(String jsonKey, long timeoutSeconds, long sleepInMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        try {
            By by = getByValue(jsonKey);
            logger.debug("Waiting for presence of all elements with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonKey, timeoutSeconds, sleepInMillis);
            return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.error("Failed to wait for presence of all elements with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonKey, timeoutSeconds, sleepInMillis, e);
            throw e;
        }
    }

    /**
     * Waits for all elements to be present using By locator with custom timeout and sleep interval.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return List of WebElements when found
     * @throws TimeoutException if elements are not present within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> presenceWaitForAllElements(By by, long timeoutSeconds, long sleepInMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for presence of all elements with locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepInMillis);
        return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    /**
     * Checks if an element is present using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return true if element is present, false otherwise
     */
    public boolean isPresence(By by, long timeoutSeconds) {
        try {
            logger.debug("Checking presence of element with locator: {} for {} seconds", by, timeoutSeconds);
            return presenceWait(by, timeoutSeconds) != null;
        } catch (Exception e) {
            logger.debug("Element with locator: {} is not present within {} seconds", by, timeoutSeconds);
            return false;
        }
    }

    /**
     * Checks if an element is present using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return true if element is present, false otherwise
     */
    public boolean isPresence(String jsonKey, long timeoutSeconds) {
        try {
            logger.debug("Checking presence of element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
            return presenceWait(jsonKey, timeoutSeconds) != null;
        } catch (Exception e) {
            logger.debug("Element with JSON key: {} is not present within {} seconds", jsonKey, timeoutSeconds);
            return false;
        }
    }
}
