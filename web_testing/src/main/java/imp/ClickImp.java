package imp;

import com.thoughtworks.gauge.Step;
import helpers.ClickHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implementation class for click operations with step definitions.
 * Provides both Gauge and Cucumber step annotations for all ClickHelper methods.
 *
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
public class ClickImp extends ClickHelper {

    private static final Logger logger = LogManager.getLogger(ClickImp.class);

    // ==================== BASIC CLICK OPERATIONS ====================

    /**
     * Clicks on an element using JSON key with default timeout.
     */
    @When("I click on {string}")
    @And("Click on {string}")
    @Step({"Click on <element>", "Click on element <element>"})
    public void clickImp(String jsonKey) {
        logger.info("Executing step: Click on element with JSON key: {}", jsonKey);
            clickElement(jsonKey);
    }

    /**
     * Clicks on an element using JSON key with custom timeout.
     */
    @When("I click on {string} after waiting {long} seconds")
    @And("Click on {string} after waiting {long} seconds")
    @Step({"Click on <element> after waiting <timeout> seconds", "Wait for <timeout> second presence of <element> then click"})
    public void clickWithTimeoutImp(String jsonKey, long timeout) {
        logger.info("Executing step: Click on element with JSON key: {} after waiting {} seconds", jsonKey, timeout);
        clickElement(jsonKey, timeout);
    }

    /**
     * Clicks on an element using JSON key with custom timeout and sleep interval.
     */
    @When("I click on {string} after waiting {long} seconds with {long}ms sleep")
    @And("Click on {string} after waiting {long} seconds with {long}ms sleep")
    @Step({"Click on <element> after waiting <timeout> seconds with <sleep>ms sleep", "Wait for <timeout> second with sleep in <sleep> millis presence of <element> then click"})
    public void clickWithTimeoutAndSleepImp(String jsonKey, long timeout, long sleepInMillis) {
        logger.info("Executing step: Click on element with JSON key: {} after waiting {} seconds with {}ms sleep", 
                   jsonKey, timeout, sleepInMillis);
        clickElement(jsonKey, timeout, sleepInMillis);
    }

    /**
     * Clicks on an element without waiting.
     */
    @When("I click on {string} without waiting")
    @And("Click on {string} without wait")
    @Step({"Click on <element> without wait", "Click on element <element> without waiting"})
    public void clickWithoutWaitImp(String jsonKey) {
        logger.info("Executing step: Click on element with JSON key: {} without waiting", jsonKey);
        clickElementWithoutWait(jsonKey);
    }

    // ==================== CONDITIONAL CLICK OPERATIONS ====================

    /**
     * Checks if element exists and clicks it with default timeout.
     */
    @When("I click on {string} if it exists")
    @And("Click if exists if so click on {string}")
    @Step({"Click if exists if so click on <element>", "Click on <element> if it exists"})
    public void clickIfExistsImp(String jsonKey) {
        logger.info("Executing step: Click on element with JSON key: {} if it exists", jsonKey);
            checkIfExitsClick(jsonKey);
    }

    /**
     * Checks if element exists and clicks it with custom timeout.
     */
    @When("I click on {string} if it exists within {long} seconds")
    @And("Click if exists if so click on {string} within {long} seconds")
    @Step({"Click if exists if so click on <element> within <timeout> seconds", "Click on <element> if it exists within <timeout> seconds"})
    public void clickIfExistsWithTimeoutImp(String jsonKey, long timeout) {
        logger.info("Executing step: Click on element with JSON key: {} if it exists within {} seconds", jsonKey, timeout);
        checkIfExitsClick(jsonKey, timeout);
    }

    /**
     * Clicks element X if it exists, otherwise clicks element Y.
     */
    @When("I click on {string} if it exists, otherwise click on {string}")
    @And("Click if exists if so click on {string}, if not click {string}")
    @Step({"Click if exists on <element> if not click <element>", "Click on <element1> if it exists, otherwise click on <element2>"})
    public void ifExistsClickXIfNotClickYImp(String xJsonKey, String yJsonKey) {
        logger.info("Executing step: Click on element X: {} if it exists, otherwise click on element Y: {}", xJsonKey, yJsonKey);
            ifExistsClickXIfNotClickY(xJsonKey, yJsonKey);
    }

    /**
     * Clicks element X if it exists within timeout, otherwise clicks element Y.
     */
    @When("I click on {string} if it exists within {long} seconds, otherwise click on {string}")
    @And("Wait for {long} second presence of {string} then click, if not exists click {string}")
    @Step({"Wait for <timeout> second presence of <element1> then click, if not exists click <element2>", "Click on <element1> if it exists within <timeout> seconds, otherwise click on <element2>"})
    public void ifExistsClickXIfNotClickYWithTimeoutImp(long timeout, String xJsonKey, String yJsonKey) {
        logger.info("Executing step: Click on element X: {} if it exists within {} seconds, otherwise click on element Y: {}", 
                   xJsonKey, timeout, yJsonKey);
            ifExistsClickXIfNotClickY(xJsonKey, yJsonKey, timeout);
    }

    // ==================== JAVASCRIPT CLICK OPERATIONS ====================

    /**
     * Clicks on an element using JavaScript.
     */
    @When("I click on {string} using JavaScript")
    @And("Click at {string} with javaScript")
    @Step({"Click at <element> with javaScript", "Click on <element> using JavaScript"})
    public void clickWithJavaScriptImp(String jsonKey) {
        logger.info("Executing step: Click on element with JSON key: {} using JavaScript", jsonKey);
            clickWithJavaScript(jsonKey);
    }

    /**
     * Clicks on an element using JavaScript with custom timeout.
     */
    @When("I click on {string} using JavaScript after waiting {long} seconds")
    @And("Click at {string} with javaScript after waiting {long} seconds")
    @Step({"Click at <element> with javaScript after waiting <timeout> seconds", "Click on <element> using JavaScript after waiting <timeout> seconds"})
    public void clickWithJavaScriptAndTimeoutImp(String jsonKey, long timeout) {
        logger.info("Executing step: Click on element with JSON key: {} using JavaScript after waiting {} seconds", jsonKey, timeout);
        clickWithJavaScript(jsonKey, timeout);
    }

    // ==================== BULK CLICK OPERATIONS ====================

    /**
     * Clicks on all elements found by JSON key.
     */
    @When("I click on all {string} elements")
    @And("Click on all {string} elements")
    @Step({"Click on all <element> elements", "Click all elements with locator <element>"})
    public void clickAllElementsImp(String jsonKey) {
        logger.info("Executing step: Click on all elements with JSON key: {}", jsonKey);
        clickAllElements(jsonKey);
    }

    // ==================== ADVANCED CLICK OPERATIONS ====================

    /**
     * Clicks on an element and waits for a specified time after clicking.
     */
    @When("I click on {string} and wait {long} milliseconds")
    @And("Click on {string} and wait {long} milliseconds")
    @Step({"Click on <element> and wait <waitTime> milliseconds", "Click on element <element> and wait <waitTime>ms after clicking"})
    public void clickAndWaitImp(String jsonKey, long waitAfterClick) {
        logger.info("Executing step: Click on element with JSON key: {} and wait {} milliseconds", jsonKey, waitAfterClick);
        clickAndWait(jsonKey, waitAfterClick);
    }

    /**
     * Clicks on an element only if it is enabled.
     */
    @When("I click on {string} if it is enabled")
    @And("Click on {string} if it is enabled")
    @Step({"Click on <element> if it is enabled", "Click on element <element> only if enabled"})
    public void clickIfEnabledImp(String jsonKey) {
        logger.info("Executing step: Click on element with JSON key: {} if it is enabled", jsonKey);
        clickIfEnabled(jsonKey);
    }

    // ==================== ALTERNATIVE STEP DEFINITIONS ====================

    /**
     * Alternative step definition for basic click operation.
     */
    @When("I click the {string} button")
    @And("Click the {string} button")
    @Step("Click the <element> button")
    public void clickButtonImp(String jsonKey) {
        logger.info("Executing step: Click the button with JSON key: {}", jsonKey);
        clickElement(jsonKey);
    }

    /**
     * Alternative step definition for basic click operation.
     */
    @When("I click the {string} link")
    @And("Click the {string} link")
    @Step("Click the <element> link")
    public void clickLinkImp(String jsonKey) {
        logger.info("Executing step: Click the link with JSON key: {}", jsonKey);
        clickElement(jsonKey);
    }

    /**
     * Alternative step definition for basic click operation.
     */
    @When("I click the {string} menu item")
    @And("Click the {string} menu item")
    @Step("Click the <element> menu item")
    public void clickMenuItemImp(String jsonKey) {
        logger.info("Executing step: Click the menu item with JSON key: {}", jsonKey);
        clickElement(jsonKey);
    }

    /**
     * Alternative step definition for conditional click operation.
     */
    @When("I try to click on {string}, if not found click on {string}")
    @And("Try to click on {string}, if not found click on {string}")
    @Step("Try to click on <element1>, if not found click on <element2>")
    public void tryClickXIfNotClickYImp(String xJsonKey, String yJsonKey) {
        logger.info("Executing step: Try to click on element X: {}, if not found click on element Y: {}", xJsonKey, yJsonKey);
        ifExistsClickXIfNotClickY(xJsonKey, yJsonKey);
    }

    /**
     * Alternative step definition for JavaScript click operation.
     */
    @When("I force click on {string} using JavaScript")
    @And("Force click on {string} using JavaScript")
    @Step("Force click on <element> using JavaScript")
    public void forceClickWithJavaScriptImp(String jsonKey) {
        logger.info("Executing step: Force click on element with JSON key: {} using JavaScript", jsonKey);
        clickWithJavaScript(jsonKey);
    }

    /**
     * Alternative step definition for bulk click operation.
     */
    @When("I select all {string} checkboxes")
    @And("Select all {string} checkboxes")
    @Step("Select all <element> checkboxes")
    public void selectAllCheckboxesImp(String jsonKey) {
        logger.info("Executing step: Select all checkboxes with JSON key: {}", jsonKey);
        clickAllElements(jsonKey);
    }

    /**
     * Alternative step definition for enabled click operation.
     */
    @When("I click on {string} only if it is clickable")
    @And("Click on {string} only if it is clickable")
    @Step("Click on <element> only if it is clickable")
    public void clickIfClickableImp(String jsonKey) {
        logger.info("Executing step: Click on element with JSON key: {} only if it is clickable", jsonKey);
        clickIfEnabled(jsonKey);
    }
}
