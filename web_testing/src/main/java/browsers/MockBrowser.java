package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Mock browser implementation for testing
 * Provides a mock WebDriver that doesn't require actual browser installation
 */
public class MockBrowser implements BrowserSelectable {
    
    private static final Logger log = LogManager.getLogger(MockBrowser.class);
    private final DesiredCapabilities capabilities;
    
    public MockBrowser() {
        this.capabilities = new DesiredCapabilities();
        configureCapabilities();
    }
    
    @Override
    public WebDriver createDriver() {
        log.info("Creating mock WebDriver - NO REAL BROWSER STARTED");
        // Return null to prevent any real browser startup
        // Tests should handle null driver appropriately
        return null;
    }
    
    @Override
    public MutableCapabilities getCapabilities() {
        return capabilities;
    }
    
    @Override
    public String getBrowserName() {
        return "mock";
    }
    
    @Override
    public boolean isSupported() {
        return true; // Mock browser is always supported
    }
    
    private void configureCapabilities() {
        // Use valid W3C capabilities only
        capabilities.setCapability("browserName", "chrome"); // Use a valid browser name
        capabilities.setCapability("platformName", "ANY");
        // Remove the invalid "mock" capability that causes W3C validation errors
    }
} 