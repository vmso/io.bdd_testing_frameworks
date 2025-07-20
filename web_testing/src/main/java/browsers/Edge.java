package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.MutableCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Edge browser implementation
 * Provides Edge-specific WebDriver configuration
 */
public class Edge implements BrowserSelectable {
    
    private static final Logger log = LogManager.getLogger(Edge.class);
    private final EdgeOptions options;
    
    public Edge() {
        this.options = new EdgeOptions();
        configureOptions();
    }
    
    @Override
    public WebDriver createDriver() {
        log.info("Creating Edge WebDriver instance");
        return new EdgeDriver(options);
    }
    
    @Override
    public MutableCapabilities getCapabilities() {
        return options;
    }
    
    @Override
    public String getBrowserName() {
        return "edge";
    }
    
    @Override
    public boolean isSupported() {
        // Edge is supported on Windows, macOS, and Linux
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win") || os.contains("mac") || os.contains("linux");
    }
    
    /**
     * Configure Edge-specific options
     */
    private void configureOptions() {
        // Modern Edge options
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        
        // Performance options
        options.addArguments("--disable-logging");
        options.addArguments("--log-level=3");
        
        // Headless mode if specified
        if (Boolean.parseBoolean(System.getProperty("headless", "false"))) {
            options.addArguments("--headless=new");
        }
        
        // Edge-specific preferences
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-web-security");
        options.addArguments("--allow-running-insecure-content");
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--disable-features=TranslateUI");
        options.addArguments("--disable-features=Translate");
    }
    
    /**
     * Get EdgeOptions for additional configuration
     * @return EdgeOptions instance
     */
    public EdgeOptions getEdgeOptions() {
        return options;
    }
} 