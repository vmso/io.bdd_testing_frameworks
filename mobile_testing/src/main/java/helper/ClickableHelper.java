package helper;

import elements.GetBy;
import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickableHelper extends WaitHelper {

    public MobileElement waitForClickable(String jsonKey, int timeOut) throws FileNotFound {
        By by = getBy(jsonKey);
        return (MobileElement) getWebDriverWait(timeOut).until(ExpectedConditions.elementToBeClickable(by));
    }

    public MobileElement waitForClickable(String jsonKey) throws FileNotFound {
        By by = getBy(jsonKey);
        return (MobileElement) getWebDriverWait().until(ExpectedConditions.elementToBeClickable(by));
    }
}
