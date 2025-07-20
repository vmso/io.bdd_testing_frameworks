package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.MutableCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Chrome browser implementation
 * Provides Chrome-specific WebDriver configuration
 */
public class Chrome implements BrowserSelectable {
    
    private static final Logger log = LogManager.getLogger(Chrome.class);
    private final ChromeOptions options;
    
    public Chrome() {
        this.options = new ChromeOptions();
        configureOptions();
    }
    
    @Override
    public WebDriver createDriver() {
        log.info("Creating Chrome WebDriver instance");
        return new ChromeDriver(options);
    }
    
    @Override
    public MutableCapabilities getCapabilities() {
        return options;
    }
    
    @Override
    public String getBrowserName() {
        return "chrome";
    }
    
    @Override
    public boolean isSupported() {
        // Chrome is supported on all platforms
        return true;
    }
    
    /**
     * Configure Chrome-specific options
     */
    private void configureOptions() {
        // Modern Chrome options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        
        // Performance options
        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");
        options.addArguments("--silent");
        
        // Security options
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        
        // Headless mode if specified
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        
        // Additional Chrome preferences
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-features=VizDisplayCompositor");
    }
    
    /**
     * Get ChromeOptions for additional configuration
     * @return ChromeOptions instance
     */
    public ChromeOptions getChromeOptions() {
        return options;
    }
} 