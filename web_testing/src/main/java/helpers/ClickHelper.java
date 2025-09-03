package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.ElementNotInteractableException;

import helpers.JavascriptHelper;


import java.util.List;

/**
 * Helper class for element clicking operations.
 * Provides utility methods for clicking elements with various conditions and wait strategies.
 * Extends GetElementHelper to inherit element retrieval functionality.
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public class ClickHelper extends GetElementHelper {
    
    private static final Logger logger = LogManager.getLogger(ClickHelper.class);
    private static final String CLICK_ERROR = "Failed to click element";
    private static final String LOG_INFO = "Clicked on {}";

    /**
     * Clicks an element using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public void clickElement(String jsonKey) {
        logger.debug("Clicking element with JSON key: {} using default timeout", jsonKey);
        clickElement(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
        logger.info(LOG_INFO, jsonKey);
    }

    /**
     * Clicks an element using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickElement(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Clicking element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
        clickElement(jsonKey, timeoutSeconds, DEFAULT_SLEEP_IN_MILLIS);
        logger.info(LOG_INFO, jsonKey);
    }

    /**
     * Clicks an element using JSON key with custom timeout and sleep interval.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @param sleepMillis the sleep interval in milliseconds
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickElement(String jsonKey, long timeoutSeconds, long sleepMillis) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        if (sleepMillis < 0) {
            throw new IllegalArgumentException("Sleep interval cannot be negative");
        }
        try {
            logger.debug("Clicking element with JSON key: {} for {} seconds with {}ms sleep", 
                        jsonKey, timeoutSeconds, sleepMillis);
            getClickableElement(jsonKey, timeoutSeconds, sleepMillis).click();
            logger.info(LOG_INFO, jsonKey);
        } catch (Exception e) {
            logger.error("Failed to click element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element using By locator with default timeout.
     * 
     * @param by the By locator
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if by is null
     */
    public void clickElement(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Clicking element with By locator: {} using default timeout", by);
        getClickableElement(by).click();
        logger.info(LOG_INFO, by);
    }

    /**
     * Clicks an element using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @throws TimeoutException if element is not clickable within timeout
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickElement(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            logger.debug("Clicking element with By locator: {} for {} seconds", by, timeoutSeconds);
            getClickableElement(by, timeoutSeconds).click();
            logger.info(LOG_INFO, by);
        } catch (Exception e) {
            logger.error("Failed to click element with By locator: {} for {} seconds", by, timeoutSeconds, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element without wait using JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @throws NoSuchElementException if element is not found
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public void clickElementWithoutWait(String jsonKey) {
        try {
            logger.debug("Clicking element without wait using JSON key: {}", jsonKey);
            getElementWithoutWait(jsonKey).click();
            logger.info(LOG_INFO, jsonKey);
        } catch (Exception e) {
            logger.error("Failed to click element without wait using JSON key: {}", jsonKey, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element without wait using By locator.
     * 
     * @param by the By locator
     * @throws NoSuchElementException if element is not found
     * @throws IllegalArgumentException if by is null
     */
    public void clickElementWithoutWait(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        try {
            logger.debug("Clicking element without wait using By locator: {}", by);
            getElementWithoutWait(by).click();
            logger.info(LOG_INFO, by);
        } catch (Exception e) {
            logger.error("Failed to click element without wait using By locator: {}", by, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Checks if element exists and clicks it using By locator with custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @return true if element was found and clicked, false otherwise
     * @throws IllegalArgumentException if parameters are invalid
     */
    public Boolean checkIfExitsClick(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            logger.debug("Checking if element exists and clicking with By locator: {} for {} seconds", by, timeoutSeconds);
            if (new PresenceHelper().isPresence(by, timeoutSeconds)) {
                clickElement(by, timeoutSeconds);
                return true;
            } else {
                logger.debug("Element with By locator: {} not found within {} seconds", by, timeoutSeconds);
                return false;
            }
        } catch (Exception e) {
            logger.error("Failed to check and click element with By locator: {} for {} seconds", by, timeoutSeconds, e);
            return false;
        }
    }

    /**
     * Checks if element exists and clicks it using JSON key with custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @return true if element was found and clicked, false otherwise
     * @throws IllegalArgumentException if parameters are invalid
     */
    public Boolean checkIfExitsClick(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            logger.debug("Checking if element exists and clicking with JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
            if (new PresenceHelper().isPresence(jsonKey, timeoutSeconds)) {
                clickElement(jsonKey, timeoutSeconds);
                return true;
            } else {
                logger.debug("Element with JSON key: {} not found within {} seconds", jsonKey, timeoutSeconds);
                return false;
            }
        } catch (Exception e) {
            logger.error("Failed to check and click element with JSON key: {} for {} seconds", jsonKey, timeoutSeconds, e);
            return false;
        }
    }

    /**
     * Checks if element exists and clicks it using By locator with default timeout.
     * 
     * @param by the By locator
     * @return true if element was found and clicked, false otherwise
     * @throws IllegalArgumentException if by is null
     */
    public Boolean checkIfExitsClick(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        logger.debug("Checking if element exists and clicking with By locator: {} using default timeout", by);
        if (new PresenceHelper().isPresence(by, DEFAULT_WAIT)) {
            clickElement(by);
            return true;
        } else {
            logger.debug("Element with By locator: {} not found within default timeout", by);
            return false;
        }
    }

    /**
     * Checks if element exists and clicks it using JSON key with default timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @return true if element was found and clicked, false otherwise
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public Boolean checkIfExitsClick(String jsonKey) {
        logger.debug("Checking if element exists and clicking with JSON key: {} using default timeout", jsonKey);
        if (new PresenceHelper().isPresence(jsonKey, DEFAULT_WAIT)) {
            clickElement(jsonKey);
            return true;
        } else {
            logger.debug("Element with JSON key: {} not found within default timeout", jsonKey);
            return false;
        }
    }

    /**
     * Clicks element X if it exists, otherwise clicks element Y using JSON keys.
     * 
     * @param elementX the primary JSON key to try first
     * @param elementY the fallback JSON key to try if elementX is not found
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void ifExistsClickXIfNotClickY(String elementX, String elementY) {
        logger.debug("Trying to click element X: {} if not found, will click element Y: {}", elementX, elementY);
        if (checkIfExitsClick(elementX)) {
            return;
        }
        checkIfExitsClick(elementY);
    }

    /**
     * Clicks element X if it exists, otherwise clicks element Y using By locators.
     * 
     * @param byX the primary By locator to try first
     * @param byY the fallback By locator to try if byX is not found
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void ifExistsClickXIfNotClickY(By byX, By byY) {
        if (byX == null || byY == null) {
            throw new IllegalArgumentException("By locators cannot be null");
        }
        logger.debug("Trying to click element X: {} if not found, will click element Y: {}", byX, byY);
        if (checkIfExitsClick(byX)) {
            return;
        }
        checkIfExitsClick(byY);
    }

    /**
     * Clicks element X if it exists, otherwise clicks element Y using JSON keys with custom timeout.
     * 
     * @param elementX the primary JSON key to try first
     * @param elementY the fallback JSON key to try if elementX is not found
     * @param timeoutSeconds the timeout in seconds
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void ifExistsClickXIfNotClickY(String elementX, String elementY, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Trying to click element X: {} if not found, will click element Y: {} with timeout: {}", 
                    elementX, elementY, timeoutSeconds);
        By byX = getByValue(elementX);
        By byY = getByValue(elementY);
        ifExistsClickXIfNotClickY(byX, byY, timeoutSeconds);
    }

    /**
     * Clicks element X if it exists, otherwise clicks element Y using By locators with custom timeout.
     * 
     * @param byX the primary By locator to try first
     * @param byY the fallback By locator to try if byX is not found
     * @param timeoutSeconds the timeout in seconds
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void ifExistsClickXIfNotClickY(By byX, By byY, long timeoutSeconds) {
        if (byX == null || byY == null) {
            throw new IllegalArgumentException("By locators cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Trying to click element X: {} if not found, will click element Y: {} with timeout: {}", 
                    byX, byY, timeoutSeconds);
        if (checkIfExitsClick(byX, timeoutSeconds)) {
            return;
        }
        checkIfExitsClick(byY, timeoutSeconds);
    }

    /**
     * Clicks an element using JavaScript with JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public void clickWithJavaScript(String jsonKey) {
        logger.debug("Clicking element with JavaScript using JSON key: {}", jsonKey);
        By by = getByValue(jsonKey);
        clickWithJavaScript(by);
    }

    /**
     * Clicks an element using JavaScript with By locator.
     * 
     * @param by the By locator
     * @throws IllegalArgumentException if by is null
     */
    public void clickWithJavaScript(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        try {
            logger.debug("Clicking element with JavaScript using By locator: {}", by);
            WebElement element = getElementWithWait(by);
            JavascriptHelper executor = new JavascriptHelper();
            executor.executeJavascript("arguments[0].click()", element);
            logger.info(LOG_INFO, by);
        } catch (Exception e) {
            logger.error("Failed to click element with JavaScript using By locator: {}", by, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element using JavaScript with JSON key and custom timeout.
     * 
     * @param jsonKey the JSON key for the locator
     * @param timeoutSeconds the timeout in seconds
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickWithJavaScript(String jsonKey, long timeoutSeconds) {
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        logger.debug("Clicking element with JavaScript using JSON key: {} for {} seconds", jsonKey, timeoutSeconds);
        By by = getByValue(jsonKey);
        clickWithJavaScript(by, timeoutSeconds);
    }

    /**
     * Clicks an element using JavaScript with By locator and custom timeout.
     * 
     * @param by the By locator
     * @param timeoutSeconds the timeout in seconds
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickWithJavaScript(By by, long timeoutSeconds) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (timeoutSeconds < 0) {
            throw new IllegalArgumentException("Timeout cannot be negative");
        }
        try {
            logger.debug("Clicking element with JavaScript using By locator: {} for {} seconds", by, timeoutSeconds);
            WebElement element = getElementWithWait(by, timeoutSeconds);
            JavascriptHelper executor = new JavascriptHelper();
            executor.executeJavascript("arguments[0].click()", element);
            logger.info(LOG_INFO, by);
        } catch (Exception e) {
            logger.error("Failed to click element with JavaScript using By locator: {} for {} seconds", by, timeoutSeconds, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks all elements found by JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @throws IllegalArgumentException if jsonKey is null or empty
     */
    public void clickAllElements(String jsonKey) {
        try {
            logger.debug("Clicking all elements with JSON key: {}", jsonKey);
            List<WebElement> allElements = getElementsWithWait(jsonKey);
            allElements.forEach(WebElement::click);
            logger.info("Clicked on {} elements with JSON key: {}", allElements.size(), jsonKey);
        } catch (Exception e) {
            logger.error("Failed to click all elements with JSON key: {}", jsonKey, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks all elements found by By locator.
     * 
     * @param by the By locator
     * @throws IllegalArgumentException if by is null
     */
    public void clickAllElements(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        try {
            logger.debug("Clicking all elements with By locator: {}", by);
            List<WebElement> allElements = getElementsWithWait(by);
            allElements.forEach(WebElement::click);
            logger.info("Clicked on {} elements with By locator: {}", allElements.size(), by);
        } catch (Exception e) {
            logger.error("Failed to click all elements with By locator: {}", by, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element and waits for a specified time after clicking.
     * 
     * @param jsonKey the JSON key for the locator
     * @param waitAfterClick the time to wait after clicking in milliseconds
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickAndWait(String jsonKey, long waitAfterClick) {
        if (waitAfterClick < 0) {
            throw new IllegalArgumentException("Wait time cannot be negative");
        }
        try {
            logger.debug("Clicking element with JSON key: {} and waiting {}ms after click", jsonKey, waitAfterClick);
            clickElement(jsonKey);
            Thread.sleep(waitAfterClick);
            logger.debug("Finished waiting {}ms after clicking element with JSON key: {}", waitAfterClick, jsonKey);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted while waiting after clicking element with JSON key: {}", jsonKey, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Failed to click and wait with JSON key: {}", jsonKey, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element and waits for a specified time after clicking.
     * 
     * @param by the By locator
     * @param waitAfterClick the time to wait after clicking in milliseconds
     * @throws IllegalArgumentException if parameters are invalid
     */
    public void clickAndWait(By by, long waitAfterClick) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        if (waitAfterClick < 0) {
            throw new IllegalArgumentException("Wait time cannot be negative");
        }
        try {
            logger.debug("Clicking element with By locator: {} and waiting {}ms after click", by, waitAfterClick);
            clickElement(by);
            Thread.sleep(waitAfterClick);
            logger.debug("Finished waiting {}ms after clicking element with By locator: {}", waitAfterClick, by);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Interrupted while waiting after clicking element with By locator: {}", by, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Failed to click and wait with By locator: {}", by, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element only if it is enabled using JSON key.
     * 
     * @param jsonKey the JSON key for the locator
     * @throws IllegalArgumentException if jsonKey is null or empty
     * @throws ElementNotInteractableException if element is not enabled
     */
    public void clickIfEnabled(String jsonKey) {
        try {
            logger.debug("Checking if element is enabled and clicking with JSON key: {}", jsonKey);
            WebElement element = getClickableElement(jsonKey);
            if (element.isEnabled()) {
                element.click();
                logger.info(LOG_INFO, jsonKey);
            } else {
                throw new ElementNotInteractableException("Element with JSON key: " + jsonKey + " is not enabled");
            }
        } catch (Exception e) {
            logger.error("Failed to click enabled element with JSON key: {}", jsonKey, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }

    /**
     * Clicks an element only if it is enabled using By locator.
     * 
     * @param by the By locator
     * @throws IllegalArgumentException if by is null
     * @throws ElementNotInteractableException if element is not enabled
     */
    public void clickIfEnabled(By by) {
        if (by == null) {
            throw new IllegalArgumentException("By locator cannot be null");
        }
        try {
            logger.debug("Checking if element is enabled and clicking with By locator: {}", by);
            WebElement element = getClickableElement(by);
            if (element.isEnabled()) {
                element.click();
                logger.info(LOG_INFO, by);
            } else {
                throw new ElementNotInteractableException("Element with By locator: " + by + " is not enabled");
            }
        } catch (Exception e) {
            logger.error("Failed to click enabled element with By locator: {}", by, e);
            throw new IllegalStateException(CLICK_ERROR + ": " + e.getMessage(), e);
        }
    }
}
