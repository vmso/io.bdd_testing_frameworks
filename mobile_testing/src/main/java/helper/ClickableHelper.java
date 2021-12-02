package helper;

import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickableHelper extends WaitHelper {

    public MobileElement waitForClickable(String jsonKey, int timeOut) throws FileNotFound {
        By by = getByValue(jsonKey);
        return (MobileElement) getWebDriverWait(timeOut).until(ExpectedConditions.elementToBeClickable(by));
    }

    public MobileElement waitForClickable(String jsonKey) throws FileNotFound {
        By by = getByValue(jsonKey);
        return (MobileElement) getWebDriverWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public MobileElement waitForClickable(String jsonKey, int timeOut, int sleepInt) throws FileNotFound {
        By by = getByValue(jsonKey);
        return (MobileElement) getWebDriverWait(timeOut,sleepInt).until(ExpectedConditions.elementToBeClickable(by));
    }
}
