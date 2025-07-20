package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.MutableCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Firefox browser implementation
 * Provides Firefox-specific WebDriver configuration
 */
public class Firefox implements BrowserSelectable {
    
    private static final Logger log = LogManager.getLogger(Firefox.class);
    private final FirefoxOptions options;
    
    public Firefox() {
        this.options = new FirefoxOptions();
        configureOptions();
    }
    
    @Override
    public WebDriver createDriver() {
        log.info("Creating Firefox WebDriver instance");
        return new FirefoxDriver(options);
    }
    
    @Override
    public MutableCapabilities getCapabilities() {
        return options;
    }
    
    @Override
    public String getBrowserName() {
        return "firefox";
    }
    
    @Override
    public boolean isSupported() {
        // Firefox is supported on all platforms
        return true;
    }
    
    /**
     * Configure Firefox-specific options
     */
    private void configureOptions() {
        // Modern Firefox options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        
        // Performance options
        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");
        
        // Headless mode if specified
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless");
        }
        
        // Firefox-specific preferences
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("dom.push.enabled", false);
        options.addPreference("geo.enabled", false);
        options.addPreference("media.navigator.enabled", false);
        options.addPreference("network.http.use-cache", false);
        options.addPreference("browser.cache.disk.enable", false);
        options.addPreference("browser.cache.memory.enable", false);
        options.addPreference("browser.cache.offline.enable", false);
        options.addPreference("network.http.pipelining", true);
        options.addPreference("network.http.proxy.pipelining", true);
    }
    
    /**
     * Get FirefoxOptions for additional configuration
     * @return FirefoxOptions instance
     */
    public FirefoxOptions getFirefoxOptions() {
        return options;
    }
} 