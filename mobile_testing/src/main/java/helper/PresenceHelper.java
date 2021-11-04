package helper;

import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class PresenceHelper extends WaitHelper {
    private final Logger log = LogManager.getLogger(PresenceHelper.class);

    public MobileElement waitUntilPresence(By by, int timeout) {
        return (MobileElement) getWebDriverWait(timeout)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public MobileElement waitUntilPresence(By by) {
        return waitUntilPresence(by, DEFAULT_TIME_OUT);
    }

    public List<MobileElement> waitUntilPresenceAll(By by, int timeout) {
        var webElements = getWebDriverWait(timeout)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        var mobileElement = new ArrayList<MobileElement>();
        webElements.stream().map(e -> (MobileElement) e).forEach(mobileElement::add);
        return mobileElement;
    }

    public List<MobileElement> waitUntilPresenceAll(By by) {
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
