package helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertWaitHelper extends WaitHelper {

    public Alert waitForPresenceOfAlert(long timeout, long sleepInMillis) {
        return getWait(timeout, sleepInMillis).until(ExpectedConditions.alertIsPresent());
    }
}
