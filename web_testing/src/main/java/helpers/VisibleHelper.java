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
 * Helper class for element visibility operations.
 * Provides utility methods for waiting for elements to be visible in the DOM.
 * Extends WaitHelper to inherit wait functionality and timeout configurations.
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public class VisibleHelper extends WaitHelper {

    private static final Logger logger = LogManager.getLogger(VisibleHelper.class);

    // Default timeout for quick visibility checks
    private static final long DEFAULT_VISIBILITY_CHECK_TIMEOUT = 5;

    /**
     * Waits for a single element to be visible using JSON key with default timeout.
     * 
     * @param jsonHelper the JSON key for the locator
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if jsonHelper is null or empty
     */
    public WebElement waitVisibleOfElement(String jsonHelper) {
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for visibility of element with JSON key: {}", jsonHelper);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of element with JSON key: {}", jsonHelper, e);
            throw e;
        }
    }

    /**
     * Waits for a single element to be visible using By locator with default timeout.
     * 
     * @param by the By locator
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if by is null
     */
    public WebElement waitVisibleOfElement(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Waiting for visibility of element with locator: {}", by);
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits for a single element to be visible using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if by is null or timeout is negative
     */
    public WebElement waitVisibleOfElement(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for visibility of element with locator: {} for {} seconds", by, timeoutSeconds);
        return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits for a single element to be visible using By locator with custom timeout and sleep interval.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement waitVisibleOfElement(By by, long timeoutSeconds, long sleepMillis) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for visibility of element with locator: {} for {} seconds with {}ms sleep", 
                    by, timeoutSeconds, sleepMillis);
        return getWait(timeoutSeconds, sleepMillis).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    /**
     * Waits for a single WebElement to be visible with default timeout.
     * 
     * @param elm the WebElement to wait for
     * @return WebElement when visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if elm is null
     */
    public WebElement waitVisibleOfElement(WebElement elm) {
        if (elm == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        logger.debug("Waiting for visibility of WebElement: {}", elm);
        return getWait().until(ExpectedConditions.visibilityOf(elm));
    }

    /**
     * Waits for a single element to be visible using JSON key with custom timeout.
     * 
     * @param jsonHelper the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if jsonHelper is null or empty, or timeout is negative
     */
    public WebElement waitVisibleOfElement(String jsonHelper, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for visibility of element with JSON key: {} for {} seconds", jsonHelper, timeoutSeconds);
            return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of element with JSON key: {} for {} seconds", jsonHelper, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Waits for a single WebElement to be visible with custom timeout.
     * 
     * @param elm the WebElement to wait for
     * @param timeoutSeconds the timeout in seconds
     * @return WebElement when visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if elm is null or timeout is negative
     */
    public WebElement waitVisibleOfElement(WebElement elm, long timeoutSeconds) {
        if (elm == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for visibility of WebElement: {} for {} seconds", elm, timeoutSeconds);
        return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOf(elm));
    }

    /**
     * Waits for a single element to be visible using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonHelper the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when found and visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement waitVisibleOfElement(String jsonHelper, long timeoutSeconds, long sleepInMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for visibility of element with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonHelper, timeoutSeconds, sleepInMillis);
            return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of element with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonHelper, timeoutSeconds, sleepInMillis, e);
            throw e;
        }
    }

    /**
     * Waits for a single WebElement to be visible with custom timeout and sleep interval.
     * 
     * @param elm the WebElement to wait for
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebElement when visible
     * @throws TimeoutException if element is not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public WebElement waitVisibleOfElement(WebElement elm, long timeoutSeconds, long sleepInMillis) {
        if (elm == null) {
            throw new IllegalArgumentException("WebElement cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for visibility of WebElement: {} for {} seconds with {}ms sleep", 
                    elm, timeoutSeconds, sleepInMillis);
        return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.visibilityOf(elm));
    }

    /**
     * Waits for all elements to be visible using JSON key with default timeout.
     * 
     * @param jsonHelper the JSON key for the locator
     * @return List of WebElements when found and visible
     * @throws TimeoutException if elements are not visible within timeout
     * @throws IllegalArgumentException if jsonHelper is null or empty
     */
    public List<WebElement> waitVisibleOfElements(String jsonHelper) {
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for visibility of all elements with JSON key: {}", jsonHelper);
        return getWait().until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of all elements with JSON key: {}", jsonHelper, e);
            throw e;
        }
    }

    /**
     * Waits for all WebElements in a list to be visible with default timeout.
     * 
     * @param elm the list of WebElements to wait for
     * @return List of WebElements when visible
     * @throws TimeoutException if elements are not visible within timeout
     * @throws IllegalArgumentException if elm is null or empty
     */
    public List<WebElement> waitVisibleOfElements(List<WebElement> elm) {
        if (elm == null || elm.isEmpty()) {
            throw new IllegalArgumentException("WebElement list cannot be null or empty");
        }
        logger.debug("Waiting for visibility of {} WebElements", elm.size());
        return getWait().until(ExpectedConditions.visibilityOfAllElements(elm));
    }

    /**
     * Waits for all elements to be visible using JSON key with custom timeout.
     * 
     * @param jsonHelper the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return List of WebElements when found and visible
     * @throws TimeoutException if elements are not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> waitVisibleOfElements(String jsonHelper, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for visibility of all elements with JSON key: {} for {} seconds", jsonHelper, timeoutSeconds);
            return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of all elements with JSON key: {} for {} seconds", jsonHelper, timeoutSeconds, e);
            throw e;
        }
    }

    /**
     * Waits for all WebElements in a list to be visible with custom timeout.
     * 
     * @param elm the list of WebElements to wait for
     * @param timeoutSeconds the timeout in seconds
     * @return List of WebElements when visible
     * @throws TimeoutException if elements are not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> waitVisibleOfElements(List<WebElement> elm, long timeoutSeconds) {
        if (elm == null || elm.isEmpty()) {
            throw new IllegalArgumentException("WebElement list cannot be null or empty");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Waiting for visibility of {} WebElements for {} seconds", elm.size(), timeoutSeconds);
        return getWait(timeoutSeconds).until(ExpectedConditions.visibilityOfAllElements(elm));
    }

    /**
     * Waits for all elements to be visible using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonHelper the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return List of WebElements when found and visible
     * @throws TimeoutException if elements are not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> waitVisibleOfElements(String jsonHelper, long timeoutSeconds, long sleepInMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        try {
            By by = getByValue(jsonHelper);
            logger.debug("Waiting for visibility of all elements with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonHelper, timeoutSeconds, sleepInMillis);
            return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            logger.error("Failed to wait for visibility of all elements with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonHelper, timeoutSeconds, sleepInMillis, e);
            throw e;
        }
    }

    /**
     * Waits for all WebElements in a list to be visible with custom timeout and sleep interval.
     * 
     * @param elm the list of WebElements to wait for
     * @param timeoutSeconds the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return List of WebElements when visible
     * @throws TimeoutException if elements are not visible within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public List<WebElement> waitVisibleOfElements(List<WebElement> elm, long timeoutSeconds, long sleepInMillis) {
        if (elm == null || elm.isEmpty()) {
            throw new IllegalArgumentException("WebElement list cannot be null or empty");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepInMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        logger.debug("Waiting for visibility of {} WebElements for {} seconds with {}ms sleep", 
                    elm.size(), timeoutSeconds, sleepInMillis);
        return getWait(timeoutSeconds, sleepInMillis).until(ExpectedConditions.visibilityOfAllElements(elm));
    }
}
