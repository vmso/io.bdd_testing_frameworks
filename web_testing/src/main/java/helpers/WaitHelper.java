package helpers;

import driver.DriverManager;
import elements.GetBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Helper class for waiting operations in web applications.
 * Extends GetBy to inherit locator functionality and provides WebDriverWait utilities.
 *
 * @author Web Testing Framework
 * @version 2.0.0
 */
public class WaitHelper extends GetBy {

    /**
     * Gets a WebDriverWait instance with custom timeout and sleep interval.
     *
     * @param timeout the timeout in seconds
     * @param sleepInMillis the sleep interval in milliseconds
     * @return WebDriverWait instance
     */
    protected WebDriverWait getWait(long timeout, long sleepInMillis) {
        return new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(sleepInMillis));
    }

    /**
     * Gets a WebDriverWait instance with custom timeout and default sleep interval.
     *
     * @param timeout the timeout in seconds
     * @return WebDriverWait instance
     */
    protected WebDriverWait getWait(long timeout) {
        return new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(DEFAULT_SLEEP_IN_MILLIS));
    }

    /**
     * Gets a WebDriverWait instance with default timeout and sleep interval.
     *
     * @return WebDriverWait instance
     */
    protected WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getInstance().getDriver(), Duration.ofSeconds(DEFAULT_WAIT), Duration.ofMillis(DEFAULT_SLEEP_IN_MILLIS));
    }
}