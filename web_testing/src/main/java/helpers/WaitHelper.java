package helpers;

import driver.DriverManager;
import elements.GetBy;
import helper.selfheal.SelfHealingEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReuseStoreData;

import java.time.Duration;
import java.util.Optional;
import java.util.function.Function;

/**
 * Base helper for waiting operations.
 * Extends GetBy to inherit locator utilities (getByValue, etc.)
 * and provides WebDriverWait instances with optional self-healing.
 *
 * Subclasses like PresenceHelper / VisibilityHelper should keep
 * using getWait() — healing is transparently applied here.
 */
public class WaitHelper extends GetBy {

    private static final Logger log = LogManager.getLogger(WaitHelper.class);

    protected static final long DEFAULT_WAIT = 15;
    protected static final long DEFAULT_SLEEP_IN_MILLIS = 500;

    protected WebDriver driver() {
        return DriverManager.getInstance().getDriver();
    }

    /**
     * Gets a WebDriverWait instance with custom timeout and sleep interval.
     */
    protected WebDriverWait getWait(long timeout, long sleepInMillis) {
        return new HealingWebDriverWait(driver(), Duration.ofSeconds(timeout), Duration.ofMillis(sleepInMillis));
    }

    /**
     * Gets a WebDriverWait instance with custom timeout and default sleep interval.
     */
    protected WebDriverWait getWait(long timeout) {
        return new HealingWebDriverWait(driver(), Duration.ofSeconds(timeout), Duration.ofMillis(DEFAULT_SLEEP_IN_MILLIS));
    }

    /**
     * Gets a WebDriverWait instance with default timeout and sleep interval.
     */
    protected WebDriverWait getWait() {
        return new HealingWebDriverWait(driver(), Duration.ofSeconds(DEFAULT_WAIT), Duration.ofMillis(DEFAULT_SLEEP_IN_MILLIS));
    }

    // ----------------------------------------------------------------
    // Inner class: HealingWebDriverWait
    // ----------------------------------------------------------------
    private static class HealingWebDriverWait extends WebDriverWait {
        private final WebDriver driver;
        private static final ThreadLocal<Boolean> healingInProgress = ThreadLocal.withInitial(() -> false);
        HealingWebDriverWait(WebDriver driver, Duration timeout, Duration sleep) {
            super(driver, timeout, sleep);
            this.driver = driver;
        }

        @Override
        public <V> V until(Function<? super WebDriver, V> condition) {
            String locatorStr = condition.toString();

            // 🔹 Check cache first
            Object cached = ReuseStoreData.get(locatorStr);
            if (cached instanceof By healed) {
                log.info("Using cached healed locator for {}", locatorStr);
                return super.until((ExpectedCondition<V>)
                        org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(healed));
            }
            try {
                // 🔹 Normal attempt
                return super.until(condition);
            } catch (org.openqa.selenium.TimeoutException tex) {
                // 🔹 Only heal if timed out
                By by = extractBy(locatorStr);
                if (by == null) throw tex;

                log.warn("Wait timed out for locator {} — attempting self-healing", by);
                SelfHealingEngine engine = new SelfHealingEngine();
                var result = engine.onFailure(driver, by, "wait", locatorStr);

                if (result.winner.isPresent()) {
                    By healed = result.winner.get();
                    log.info("Retrying wait with healed locator: {}", healed);

                    // cache healed locator
                    ReuseStoreData.put(locatorStr, healed);

                    return super.until((ExpectedCondition<V>)
                            org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated(healed));
                }

                throw tex;
            }
        }
        /**
         * Tries to parse a By locator from ExpectedCondition.toString().
         * This is a heuristic and may not always succeed.
         */
        private By extractBy(String conditionStr) {
            if (conditionStr == null) return null;
            if (conditionStr.contains("By.")) {
                try {
                    String byPart = conditionStr.substring(conditionStr.indexOf("By."));
                    return By.xpath(byPart); // minimal fallback — adapt parser as needed
                } catch (Exception ignore) {}
            }
            return null;
        }
    }
}
