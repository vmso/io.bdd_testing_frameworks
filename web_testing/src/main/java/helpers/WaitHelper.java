package helpers;

import driver.DriverManager;
import elements.GetBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper extends GetBy {



    protected WebDriverWait getWait(long timeout, long sleepInMillis) {
        return new WebDriverWait(DriverManager.getInstances().getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(sleepInMillis));
    }

    protected WebDriverWait getWait(long timeout) {
        return new WebDriverWait(DriverManager.getInstances().getDriver(), Duration.ofSeconds(timeout), Duration.ofMillis(DEFAULT_SLEEP_IN_MILLIS));
    }

    protected WebDriverWait getWait() {
        return new WebDriverWait(DriverManager.getInstances().getDriver(), Duration.ofSeconds(DEFAULT_WAIT), Duration.ofMillis(DEFAULT_SLEEP_IN_MILLIS));
    }
}
