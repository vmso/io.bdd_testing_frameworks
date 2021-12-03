package helpers;


import org.openqa.selenium.By;

public class ClickHelper extends GetElementHelper {

    public void clickElement(String jsonKey) {
        clickElement(jsonKey, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void clickElement(String jsonKey, long timeout) {
        clickElement(jsonKey, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void clickElement(String jsonKey, long timeout, long sleepMillis) {
        getClickableElement(jsonKey, timeout, sleepMillis).click();
    }

    public void clickElement(By by) {
        getClickableElement(by).click();
    }

    public void clickElement(By by, long timeout) {
        getClickableElement(by, timeout).click();
    }

    public void clickElementWithoutWait(String jsonKey) {
        getElementsWithoutWait(jsonKey);
    }

    public void clickElementWithoutWait(By by) {
        getElementsWithoutWait(by);
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
        if (checkIfExitsClick(elementX, timeout))
            return;
        checkIfExitsClick(elementY, timeout);
    }

    public void ifExistsClickXIfNotClickY(By byX, By byY, long timeout) {
        if (checkIfExitsClick(byX, timeout))
            return;
        checkIfExitsClick(byY, timeout);
    }

}
