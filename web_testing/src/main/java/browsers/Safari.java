package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.MutableCapabilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Safari browser implementation
 * Provides Safari-specific WebDriver configuration
 */
public class Safari implements BrowserSelectable {
    
    private static final Logger log = LogManager.getLogger(Safari.class);
    private final SafariOptions options;
    
    public Safari() {
        this.options = new SafariOptions();
        configureOptions();
    }
    
    @Override
    public WebDriver createDriver() {
        log.info("Creating Safari WebDriver instance");
        try {
            SafariDriver safariDriver = new SafariDriver(options);
            
            // Add shutdown hook for Safari to ensure proper cleanup
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (safariDriver != null) {
                        log.info("Shutdown hook: Cleaning up Safari driver");
                        safariDriver.quit();
                    }
                } catch (Exception e) {
                    log.warn("Error during Safari shutdown hook: {}", e.getMessage());
                }
            }));
            
            return safariDriver;
            
        } catch (Exception e) {
            log.error("Failed to create Safari driver: {}", e.getMessage());
            throw new RuntimeException("Failed to create Safari driver", e);
        }
    }
    
    @Override
    public MutableCapabilities getCapabilities() {
        return options;
    }
    
    @Override
    public String getBrowserName() {
        return "safari";
    }
    
    @Override
    public boolean isSupported() {
        // Safari is only supported on macOS
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("mac");
    }
    
    /**
     * Configure Safari-specific options
     */
    private void configureOptions() {
        // Safari-specific options
        options.setAutomaticInspection(true);
        options.setAutomaticProfiling(true);
        
        // Additional Safari preferences
        options.setUseTechnologyPreview(false);
        
        // Note: Safari has limited options compared to other browsers
        // Most configuration is done through Safari's developer menu
    }
    
    /**
     * Get SafariOptions for additional configuration
     * @return SafariOptions instance
     */
    public SafariOptions getSafariOptions() {
        return options;
    }
} 