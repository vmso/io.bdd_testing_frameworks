package helper.selfheal;

import configuration.Configuration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.*;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SelfHealingEngineTest {

    @Test
    void shadowModeDoesNotActAndReturnsEmptyWinner() {
        // Ensure config defaults shadow_mode=true (from resources) — no override here
        WebDriver driver = mock(WebDriver.class, withSettings().extraInterfaces(TakesScreenshot.class, JavascriptExecutor.class));

        // getCurrentUrl for fast-path key
        when(driver.getCurrentUrl()).thenReturn("https://example.com/page");

        // Simulate primary and any candidate failures
        when(driver.findElement(any(By.class))).thenThrow(new org.openqa.selenium.NoSuchElementException("not found"));

        // Screenshot mock
        TakesScreenshot ts = (TakesScreenshot) driver;
        when(ts.getScreenshotAs(OutputType.FILE)).thenReturn(new File("/tmp/screen.png"));

        // DOM snapshot mock
        JavascriptExecutor js = (JavascriptExecutor) driver;
        when(js.executeScript(anyString())).thenReturn("<html></html>");

        SelfHealingEngine engine = new SelfHealingEngine();
        var res = engine.onFailure(driver, By.id("nonexistent"), "findElement", "submit");
        assertNotNull(res);
        assertTrue(res.winner.isEmpty(), "Shadow-mode should not return a winner");
    }
}


