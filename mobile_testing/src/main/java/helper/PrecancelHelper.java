package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PrecancelHelper extends WaitHelper {
    private final Logger log = LogManager.getLogger(PrecancelHelper.class);

    public WebElement waitUntilPresence(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitUntilPresence(By by) {
        return waitUntilPresence(by, DEFAULT_TIME_OUT);
    }

    public List<WebElement> waitUntilPresenceAll(By by, int timeout) {
        return getWebDriverWait(timeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public List<WebElement> waitUntilPresenceAll(By by) {
        return waitUntilPresenceAll(by, DEFAULT_TIME_OUT);
    }

    public boolean isPresence(By by) {
        return isPresence(by, DEFAULT_TIME_OUT);
    }

    public boolean isPresence(By by, int second) {
        try {
            log.info("{} : Elementin {}: saniye süresince var olması bekleniyor", by, second);
            return waitUntilPresence(by, second) != null;
        } catch (Exception ex) {
            return false;
        }
    }
}
