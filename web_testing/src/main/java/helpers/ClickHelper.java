package helpers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ClickHelper extends GetElementHelper {
    private Logger log = LogManager.getLogger(ClickHelper.class);
    private final String LOG_INFO = "Clicked on {}";

    public void clickElement(String jsonKey) {
        clickElement(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
        log.info(LOG_INFO, jsonKey);
    }

    public void clickElement(String jsonKey, long timeout) {
        clickElement(jsonKey, timeout, DEFAULT_SLEEP_IN_MILLIS);
        log.info(LOG_INFO, jsonKey);
    }

    public void clickElement(String jsonKey, long timeout, long sleepMillis) {
        getClickableElement(jsonKey, timeout, sleepMillis).click();
        log.info(LOG_INFO, jsonKey);
    }

    public void clickElement(By by) {
        getClickableElement(by).click();
        log.info(LOG_INFO, by);

    }

    public void clickElement(By by, long timeout) {
        getClickableElement(by, timeout).click();
        log.info(LOG_INFO, by);
    }

    public void clickElementWithoutWait(String jsonKey) {
        getElementsWithoutWait(jsonKey);
        log.info(LOG_INFO, jsonKey);
    }

    public void clickElementWithoutWait(By by) {
        getElementsWithoutWait(by);
        log.info(LOG_INFO, by);
    }

    public Boolean checkIfExitsClick(By by, long timeout) {
        if (new PresenceHelper().isPresence(by, timeout)) {
            clickElement(by, timeout);
            return true;
        } else
            return false;
    }

    public Boolean checkIfExitsClick(String jsonKey, long timeout) {
        if (new PresenceHelper().isPresence(jsonKey, timeout)) {
            clickElement(jsonKey, timeout);
            return true;
        } else
            return false;
    }

    public Boolean checkIfExitsClick(By by) {
        if (new PresenceHelper().isPresence(by, DEFAULT_WAIT)) {
            clickElement(by);
            return true;
        } else
            return false;
    }

    public Boolean checkIfExitsClick(String jsonKey) {
        if (new PresenceHelper().isPresence(jsonKey, DEFAULT_WAIT)) {
            clickElement(jsonKey);
            return true;
        } else
            return false;
    }

    public void ifExistsClickXIfNotClickY(String elementX, String elementY) {
        if (checkIfExitsClick(elementX))
            return;
        checkIfExitsClick(elementY);
    }

    public void ifExistsClickXIfNotClickY(By byX, By byY) {
        if (checkIfExitsClick(byX))
            return;
        checkIfExitsClick(byY);
    }

    public void ifExistsClickXIfNotClickY(String elementX, String elementY, long timeout) {
        By byX = getByValue(elementX);
        By byY = getByValue(elementY);
        ifExistsClickXIfNotClickY(byX, byY, timeout);
    }

    public void ifExistsClickXIfNotClickY(By byX, By byY, long timeout) {
        if (checkIfExitsClick(byX, timeout))
            return;
        checkIfExitsClick(byY, timeout);
    }

    public void clickWithJavaScript(String jsonKey) {
        var by = getByValue(jsonKey);
        clickWithJavaScript(by);
    }

    public void clickWithJavaScript(By by) {
        var elm = getElementsWithWait(by);
        var executor = new JavascriptHelper();
        executor.executeJavascript("arguments[0].click()", elm);
        log.info(LOG_INFO, by);
    }

}
