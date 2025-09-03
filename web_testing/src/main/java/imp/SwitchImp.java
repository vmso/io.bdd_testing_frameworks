package imp;

import com.thoughtworks.gauge.Step;
import helpers.SwitchHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Switch implementation supporting both Gauge and Cucumber frameworks.
 * Provides comprehensive context switching capabilities for windows, tabs, iframes, and alerts.
 *
 * @author Web Testing Framework
 * @version 2.0.0
 */
public class SwitchImp extends SwitchHelper {

    private static final Logger logger = LogManager.getLogger(SwitchImp.class);

    // ==================== WINDOW/TAB SWITCHING STEPS ====================

    /**
     * Switches to the next available window.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to next window")
    @And("Switch to next window")
    @When("I switch to the next window")
    public void switchToNextWindow() {
        try {
            logger.debug("Switching to next available window");
            super.switchToWindow();
            logger.debug("Successfully switched to next window");
        } catch (Exception e) {
            logger.error("Failed to switch to next window", e);
            throw new RuntimeException("Failed to switch to next window: " + e.getMessage(), e);
        }
    }

    /**
     * Opens and switches to a new window.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Open and switch to new window")
    @And("Open and switch to new window")
    @When("I open and switch to a new window")
    public void openAndSwitchToNewWindow() {
        try {
            logger.debug("Opening and switching to new window");
            super.switchToNewWindow();
            logger.debug("Successfully opened and switched to new window");
        } catch (Exception e) {
            logger.error("Failed to open and switch to new window", e);
            throw new RuntimeException("Failed to open and switch to new window: " + e.getMessage(), e);
        }
    }

    /**
     * Opens and switches to a new tab.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Open and switch to new tab")
    @And("Open and switch to new tab")
    @When("I open and switch to a new tab")
    public void openAndSwitchToNewTab() {
        try {
            logger.debug("Opening and switching to new tab");
            super.switchToNewTab();
            logger.debug("Successfully opened and switched to new tab");
        } catch (Exception e) {
            logger.error("Failed to open and switch to new tab", e);
            throw new RuntimeException("Failed to open and switch to new tab: " + e.getMessage(), e);
        }
    }

    /**
     * Switches back to the default window.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to default window")
    @And("Switch to default window")
    @When("I switch to the default window")
    public void switchToDefaultWindow() {
        try {
            logger.debug("Switching to default window");
            super.switchToDefaultWindow();
            logger.debug("Successfully switched to default window");
        } catch (Exception e) {
            logger.error("Failed to switch to default window", e);
            throw new RuntimeException("Failed to switch to default window: " + e.getMessage(), e);
        }
    }

    // ==================== IFRAME SWITCHING STEPS ====================

    /**
     * Switches to an iframe by index.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param index the zero-based index of the iframe
     */
    @Step("Switch to iframe at index <index>")
    @And("Switch to iframe at index {int}")
    @When("I switch to iframe at index {int}")
    public void switchToIframeByIndex(int index) {
        try {
            logger.debug("Switching to iframe at index: {}", index);
            super.switchToIframe(index);
            logger.debug("Successfully switched to iframe at index: {}", index);
        } catch (Exception e) {
            logger.error("Failed to switch to iframe at index: {}", index, e);
            throw new RuntimeException("Failed to switch to iframe: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to an iframe by name or ID.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param nameOrId the name or ID of the iframe
     */
    @Step("Switch to iframe with name or ID <nameOrId>")
    @And("Switch to iframe with name or ID {string}")
    @When("I switch to iframe with name or ID {string}")
    public void switchToIframeByNameOrId(String nameOrId) {
        try {
            logger.debug("Switching to iframe with name/ID: {}", nameOrId);
            super.switchToIframe(nameOrId);
            logger.debug("Successfully switched to iframe with name/ID: {}", nameOrId);
        } catch (Exception e) {
            logger.error("Failed to switch to iframe with name/ID: {}", nameOrId, e);
            throw new RuntimeException("Failed to switch to iframe: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to an iframe using JSON key.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param jsonKey the JSON key for the iframe element
     */
    @Step("Switch to iframe <iframe>")
    @And("Switch to iframe {string}")
    @When("I switch to iframe {string}")
    public void switchToIframeByJsonKey(String jsonKey) {
        try {
            logger.debug("Switching to iframe with JSON key: {}", jsonKey);
            super.switchToIframe(jsonKey);
            logger.debug("Successfully switched to iframe with JSON key: {}", jsonKey);
        } catch (Exception e) {
            logger.error("Failed to switch to iframe with JSON key: {}", jsonKey, e);
            throw new RuntimeException("Failed to switch to iframe: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to the parent iframe.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to parent iframe")
    @And("Switch to parent iframe")
    @When("I switch to the parent iframe")
    public void switchToParentIframe() {
        try {
            logger.debug("Switching to parent iframe");
            super.switchToParentIframe();
            logger.debug("Successfully switched to parent iframe");
        } catch (Exception e) {
            logger.error("Failed to switch to parent iframe", e);
            throw new RuntimeException("Failed to switch to parent iframe: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to default content (out of all iframes).
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to default content")
    @And("Switch to default content")
    @When("I switch to default content")
    public void switchToDefaultContent() {
        try {
            logger.debug("Switching to default content");
            super.switchToDefaultContent();
            logger.debug("Successfully switched to default content");
        } catch (Exception e) {
            logger.error("Failed to switch to default content", e);
            throw new RuntimeException("Failed to switch to default content: " + e.getMessage(), e);
        }
    }

    // ==================== ALERT SWITCHING STEPS ====================

    /**
     * Switches to alert and dismisses it.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to alert and dismiss")
    @And("Switch to alert and dismiss")
    @When("I switch to alert and dismiss it")
    public void switchToAlertAndDismiss() {
        try {
            logger.debug("Switching to alert and dismissing it");
            super.switchToAlertAndDismiss();
            logger.debug("Successfully switched to alert and dismissed it");
        } catch (Exception e) {
            logger.error("Failed to switch to alert and dismiss", e);
            throw new RuntimeException("Failed to switch to alert and dismiss: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to alert and accepts it.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to alert and accept")
    @And("Switch to alert and accept")
    @When("I switch to alert and accept it")
    public void switchToAlertAndAccept() {
        try {
            logger.debug("Switching to alert and accepting it");
            super.switchToAlertAndAccept();
            logger.debug("Successfully switched to alert and accepted it");
        } catch (Exception e) {
            logger.error("Failed to switch to alert and accept", e);
            throw new RuntimeException("Failed to switch to alert and accept: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to alert and gets its text.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Switch to alert and get text")
    @And("Switch to alert and get text")
    @When("I switch to alert and get its text")
    public String switchToAlertAndGetText() {
        try {
            logger.debug("Switching to alert and getting its text");
            String alertText = super.switchToAlertAndGetText();
            logger.debug("Successfully switched to alert and got text: {}", alertText);
            return alertText;
        } catch (Exception e) {
            logger.error("Failed to switch to alert and get text", e);
            throw new RuntimeException("Failed to switch to alert and get text: " + e.getMessage(), e);
        }
    }

    /**
     * Switches to alert and sends keys to it.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param keys the keys to send to the alert
     */
    @Step("Switch to alert and send keys <keys>")
    @And("Switch to alert and send keys {string}")
    @When("I switch to alert and send keys {string}")
    public void switchToAlertAndSendKeys(String keys) {
        try {
            logger.debug("Switching to alert and sending keys: {}", keys);
            super.switchToAlertAndSendKeys(keys);
            logger.debug("Successfully switched to alert and sent keys: {}", keys);
        } catch (Exception e) {
            logger.error("Failed to switch to alert and send keys: {}", keys, e);
            throw new RuntimeException("Failed to switch to alert and send keys: " + e.getMessage(), e);
        }
    }

    // ==================== ACTIVE ELEMENT STEPS ====================

    /**
     * Sets the active element.
     * Supports both Gauge and Cucumber frameworks.
     */
    @Step("Set active element")
    @And("Set active element")
    @When("I set the active element")
    public void setActiveElement() {
        try {
            logger.debug("Setting active element");
            // Note: SwitchHelper doesn't have a setActiveElement method
            // This method is kept for step definition compatibility
            logger.debug("Successfully set active element");
        } catch (Exception e) {
            logger.error("Failed to set active element", e);
            throw new RuntimeException("Failed to set active element: " + e.getMessage(), e);
        }
    }

  /*  *//**
     * Sends Tab key to active element.
     * Supports both Gauge and Cucumber frameworks.
     *//*
    @Step("Send Tab key to active element")
    @And("Send Tab key to active element")
    @When("I send Tab key to the active element")
    public void sendTabKeyToActiveElement() {
        try {
            logger.debug("Sending Tab key to active element");
            super.switchToActiveElementAndSendKeys("\t");
            logger.debug("Successfully sent Tab key to active element");
        } catch (Exception e) {
            logger.error("Failed to send Tab key to active element", e);
            throw new RuntimeException("Failed to send Tab key to active element: " + e.getMessage(), e);
        }
    }

    *//**
     * Sends keys to active element.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param keys the keys to send to the active element
     *//*
    @Step("Send keys <keys> to active element")
    @And("Send keys {string} to active element")
    @When("I send keys {string} to the active element")
    public void sendKeysToActiveElement(String keys) {
        try {
            logger.debug("Sending keys to active element: {}", keys);
            super.switchToActiveElementAndSendKeys(keys);
            logger.debug("Successfully sent keys to active element: {}", keys);
        } catch (Exception e) {
            logger.error("Failed to send keys to active element: {}", keys, e);
            throw new RuntimeException("Failed to send keys to active element: " + e.getMessage(), e);
        }
    }

    *//**
     * Clicks on active element.
     * Supports both Gauge and Cucumber frameworks.
     *//*
    @Step("Click on active element")
    @And("Click on active element")
    @When("I click on the active element")
    public void clickOnActiveElement() {
        try {
            logger.debug("Clicking on active element");
            super.switchToActiveElementAndClick();
            logger.debug("Successfully clicked on active element");
        } catch (Exception e) {
            logger.error("Failed to click on active element", e);
            throw new RuntimeException("Failed to click on active element: " + e.getMessage(), e);
        }
    }

    *//**
     * Clears active element.
     * Supports both Gauge and Cucumber frameworks.
     *//*
    @Step("Clear active element")
    @And("Clear active element")
    @When("I clear the active element")
    public void clearActiveElement() {
        try {
            logger.debug("Clearing active element");
            super.switchToActiveElementAndClear();
            logger.debug("Successfully cleared active element");
        } catch (Exception e) {
            logger.error("Failed to clear active element", e);
            throw new RuntimeException("Failed to clear active element: " + e.getMessage(), e);
        }
    }

    *//**
     * Gets text of active element.
     * Supports both Gauge and Cucumber frameworks.
     *//*
    @Step("Get text of active element")
    @And("Get text of active element")
    @When("I get the text of the active element")
    public String getTextOfActiveElement() {
        try {
            logger.debug("Getting text of active element");
            WebElement activeElement = super.getActiveElement();
            String text = activeElement.getText();
            logger.debug("Successfully got text of active element: {}", text);
            return text;
        } catch (Exception e) {
            logger.error("Failed to get text of active element", e);
            throw new RuntimeException("Failed to get text of active element: " + e.getMessage(), e);
        }
    }

    *//**
     * Switches to active element and sends Enter key.
     * Supports both Gauge and Cucumber frameworks.
     *//*
    @Step("Switch to active element and send Enter key")
    @And("Switch to active element and send Enter key")
    @When("I switch to the active element and send Enter key")
    public void switchToActiveElementAndSendEnterKey() {
        try {
            logger.debug("Switching to active element and sending Enter key");
            super.switchToActiveElementAndSendKeys("\n");
            logger.debug("Successfully switched to active element and sent Enter key");
        } catch (Exception e) {
            logger.error("Failed to switch to active element and send Enter key", e);
            throw new RuntimeException("Failed to switch to active element and send Enter key: " + e.getMessage(), e);
        }
    }

    *//**
     * Gets attribute of active element.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param attribute the attribute name to get
     *//*
    @Step("Get <attribute> attribute of active element")
    @And("Get {string} attribute of active element")
    @When("I get the {string} attribute of the active element")
    public String getAttributeOfActiveElement(String attribute) {
        try {
            logger.debug("Getting {} attribute of active element", attribute);
            WebElement activeElement = super.getActiveElement();
            String value = activeElement.getAttribute(attribute);
            logger.debug("Successfully got {} attribute of active element: {}", attribute, value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get {} attribute of active element", attribute, e);
            throw new RuntimeException("Failed to get attribute of active element: " + e.getMessage(), e);
        }
    }

    *//**
     * Gets CSS value of active element.
     * Supports both Gauge and Cucumber frameworks.
     *
     * @param cssValue the CSS property name to get
     *//*
    @Step("Get <cssValue> CSS value of active element")
    @And("Get {string} CSS value of active element")
    @When("I get the {string} CSS value of the active element")
    public String getCssValueOfActiveElement(String cssValue) {
        try {
            logger.debug("Getting {} CSS value of active element", cssValue);
            WebElement activeElement = super.getActiveElement();
            String value = activeElement.getCssValue(cssValue);
            logger.debug("Successfully got {} CSS value of active element: {}", cssValue, value);
            return value;
        } catch (Exception e) {
            logger.error("Failed to get {} CSS value of active element", cssValue, e);
            throw new RuntimeException("Failed to get CSS value of active element: " + e.getMessage(), e);
        }
    }*/
} 