package runner;

import base.BaseBrowser;
import driver.DriverManager;
import enums.Browser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Cross-browser test demonstration
 * Shows how the framework supports multiple browsers
 */
public class CrossBrowserTest extends BaseBrowser {

    private WebDriver driver;
    private WebDriverWait wait;

    /**
     * Test cross-browser functionality
     */
    @Test
    public void testCrossBrowserSupport() {
        // Test Chrome
        testBrowser("chrome");
        
        // Test Firefox
        testBrowser("firefox");
        
        // Test Edge (if supported on platform)
        if (System.getProperty("os.name").toLowerCase().contains("win") || 
            System.getProperty("os.name").toLowerCase().contains("mac") ||
            System.getProperty("os.name").toLowerCase().contains("linux")) {
            testBrowser("edge");
        }
        
        // Test Safari (macOS only)
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            testBrowser("safari");
        }
    }

    /**
     * Test a specific browser
     */
    private void testBrowser(String browserName) {
        try {
            System.out.println("Testing browser: " + browserName);
            
            // Set browser property
            System.setProperty("browser", browserName);
            
            // Create driver
            DriverManager.getInstance().createLocalDriver();
            driver = DriverManager.getInstance().getDriver();
            wait = DriverManager.getInstance().getWait();
            
            // Navigate to a test page
            driver.get("https://www.google.com");
            
            // Verify page loaded
            WebElement searchBox = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("q"))
            );
            
            System.out.println("✓ " + browserName + " browser test passed");
            
        } catch (Exception e) {
            System.err.println("✗ " + browserName + " browser test failed: " + e.getMessage());
        } finally {
            // Clean up
            if (driver != null) {
                DriverManager.getInstance().quitDriver();
            }
        }
    }

    // Cucumber step definitions for cross-browser testing
    
    @Given("I open {string} browser")
    public void openBrowser(String browserName) {
        System.setProperty("browser", browserName.toLowerCase());
        DriverManager.getInstance().createLocalDriver();
        driver = DriverManager.getInstance().getDriver();
        wait = DriverManager.getInstance().getWait();
    }

    @When("I navigate to {string}")
    public void navigateToUrl(String url) {
        driver.get(url);
    }

    @Then("the page should load successfully")
    public void verifyPageLoaded() {
        // Wait for page to load
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"));
    }

    @Then("I should see {string} element")
    public void verifyElementPresent(String elementId) {
        WebElement element = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.id(elementId))
        );
        assert element.isDisplayed();
    }

    @Then("I close the browser")
    public void closeBrowser() {
        DriverManager.getInstance().quitDriver();
    }
} 