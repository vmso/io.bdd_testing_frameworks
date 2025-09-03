package driver;

import browsers.*;
import enums.Browser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReuseStoreData;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Modern WebDriver Manager using Selenium 4 features with cross-browser support
 * Uses browser-specific implementations for better abstraction and extensibility
 */
public class DriverManager {

    private static volatile DriverManager instance;
    private Browser browsersType;
    private WebDriver driver;
    private WebDriverWait wait;
    private final Logger log = LogManager.getLogger(DriverManager.class);
    private final Map<Browser, BrowserSelectable> browserImplementations;

    private DriverManager() {
        // Initialize browser implementations
        browserImplementations = new HashMap<>();
        browserImplementations.put(Browser.CHROME, new Chrome());
        browserImplementations.put(Browser.FIREFOX, new Firefox());
        browserImplementations.put(Browser.EDGE, new Edge());
        browserImplementations.put(Browser.SAFARI, new Safari());
        browserImplementations.put(Browser.MOCK, new MockBrowser());
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            synchronized (DriverManager.class) {
                if (instance == null) {
                    instance = new DriverManager();
                }
            }
        }
        return instance;
    }

    /**
     * Create local WebDriver instance using browser-specific implementations
     */
    public void createLocalDriver() {
        String browserName = System.getProperty("browser", "chrome");
        Browser browserType = Browser.valueOf(browserName.toUpperCase(Locale.ROOT));
        setBrowsersType(browserType);
        
        log.info("Creating {} browser instance", browserType);
        
        BrowserSelectable browserImpl = browserImplementations.get(browserType);
        if (browserImpl == null) {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        
        if (!browserImpl.isSupported()) {
            throw new UnsupportedOperationException("Browser " + browserType + " is not supported on this platform");
        }
        
        driver = browserImpl.createDriver();
        
        // Configure common driver settings only if driver is not null (for mock browsers)
        if (driver != null) {
            configureDriver();
        }
        
        // Store driver in thread-safe storage
        ReuseStoreData.put("driver", driver);
        log.info("{} browser instance created successfully", browserType);
    }



    /**
     * Configure common driver settings
     */
    private void configureDriver() {
        // Set timeouts
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        
        // Set window size if not maximized
        if (!Boolean.parseBoolean(System.getProperty("maximize", "true"))) {
            String width = System.getProperty("browser.width", "1920");
            String height = System.getProperty("browser.height", "1080");
            driver.manage().window().setSize(new Dimension(Integer.parseInt(width), Integer.parseInt(height)));
        }
        
        // Create WebDriverWait instance
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Create remote WebDriver for Selenium Grid
     */
    public void createRemoteDriver(String gridUrl) {
        String browserName = System.getProperty("browser", "chrome");
        Browser browserType = Browser.valueOf(browserName.toUpperCase(Locale.ROOT));
        setBrowsersType(browserType);
        
        log.info("Creating remote {} browser instance on {}", browserType, gridUrl);
        
        // Special handling for mock browser - don't create real RemoteWebDriver
        if (browserType == Browser.MOCK) {
            log.info("Mock browser detected - skipping real RemoteWebDriver creation");
            // Set a mock driver or null to indicate mock mode
            driver = null;
            ReuseStoreData.put("driver", driver);
            return;
        }
        
        MutableCapabilities capabilities = getCapabilitiesForBrowser(browserType);
        
        try {
            driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
            configureDriver();
            ReuseStoreData.put("driver", driver);
            log.info("Remote {} browser instance created successfully", browserType);
        } catch (Exception e) {
            log.error("Failed to create remote driver", e);
            throw new RuntimeException("Failed to create remote driver", e);
        }
    }

    /**
     * Get capabilities for specific browser
     */
    private MutableCapabilities getCapabilitiesForBrowser(Browser browserType) {
        BrowserSelectable browserImpl = browserImplementations.get(browserType);
        if (browserImpl == null) {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
        return browserImpl.getCapabilities();
    }

    /**
     * Quit driver safely
     */
    public void quitDriver() {
        try {
            if (driver != null) {
                log.info("Quitting driver instance");
                
                // Special handling for Safari on macOS
                if (driver instanceof org.openqa.selenium.safari.SafariDriver) {
                    log.info("Detected Safari driver - applying special cleanup");
                    try {
                        // First try normal quit
                        driver.quit();
                    } catch (Exception e) {
                        log.warn("Normal Safari quit failed: {}", e.getMessage());
                    }
                    
                    // Force cleanup Safari processes on macOS
                    if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                        forceCleanupSafari();
                    }
                } else {
                    // Normal quit for other browsers
                    driver.quit();
                }
                
                driver = null;
                wait = null;
                ReuseStoreData.remove("driver");
            }
        } catch (NoSuchSessionException e) {
            log.warn("Driver already closed");
        } catch (Exception e) {
            log.error("Error while quitting driver", e);
        }
    }
    
    /**
     * Force cleanup Safari processes on macOS
     */
    private void forceCleanupSafari() {
        try {
            log.info("Force cleaning Safari processes");
            
            // Kill Safari processes
            ProcessBuilder pb = new ProcessBuilder("pkill", "-f", "Safari");
            Process process = pb.start();
            process.waitFor(5, TimeUnit.SECONDS);
            
            // Also kill any remaining WebDriver processes
            ProcessBuilder pb2 = new ProcessBuilder("pkill", "-f", "safaridriver");
            Process process2 = pb2.start();
            process2.waitFor(5, TimeUnit.SECONDS);
            
            log.info("Safari force cleanup completed");
        } catch (Exception e) {
            log.warn("Force cleanup failed: {}", e.getMessage());
        }
    }

    /**
     * Get current WebDriver instance
     */
    public WebDriver getDriver() {
        if (driver == null) {
            driver = (WebDriver) ReuseStoreData.get("driver");
        }
        return driver;
    }

    /**
     * Get WebDriverWait instance
     */
    public WebDriverWait getWait() {
        if (wait == null && driver != null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    /**
     * Set driver instance
     */
    public void setDriver(WebDriver driver) {
        this.driver = driver;
        ReuseStoreData.put("driver", driver);
    }

    /**
     * Get browser type
     */
    public Browser getBrowsersType() {
        return browsersType;
    }

    /**
     * Set browser type
     */
    public void setBrowsersType(Browser browser) {
        this.browsersType = browser;
    }

    /**
     * Check if driver is active
     */
    public boolean isDriverActive() {
        try {
            if (driver != null) {
                driver.getCurrentUrl();
                return true;
            }
        } catch (Exception e) {
            log.debug("Driver is not active: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Refresh driver instance
     */
    public void refreshDriver() {
        // Store current browser type before quitting
        Browser currentBrowserType = browsersType;
        quitDriver();
        
        // Restore browser type if it was set
        if (currentBrowserType != null) {
            setBrowsersType(currentBrowserType);
            // Set system property to ensure createLocalDriver uses the correct browser
            System.setProperty("browser", currentBrowserType.getValue());
        }
        
        createLocalDriver();
    }
}
