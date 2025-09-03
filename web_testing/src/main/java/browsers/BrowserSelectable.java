package browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.MutableCapabilities;

/**
 * Interface for browser-specific implementations
 * Provides abstraction layer for cross-browser testing
 */
public interface BrowserSelectable {
    
    /**
     * Create WebDriver instance for this browser
     * @return WebDriver instance
     */
    WebDriver createDriver();
    
    /**
     * Get browser-specific capabilities
     * @return MutableCapabilities for this browser
     */
    MutableCapabilities getCapabilities();
    
    /**
     * Get browser name
     * @return Browser name as string
     */
    String getBrowserName();
    
    /**
     * Check if browser is supported on current platform
     * @return true if supported, false otherwise
     */
    boolean isSupported();
} 