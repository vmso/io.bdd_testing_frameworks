package helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;

public class SelectHelper extends GetElementHelper {

    private final Logger log = LogManager.getLogger(SelectHelper.class);

    private Select getSelect(By by, long timeOut, long sleepInMillis) {
        var selectElm = getClickableElement(by, timeOut, sleepInMillis);
        return new Select(selectElm);
    }

    private Select getSelect(String jsonKey, long timeOut, long sleepInMillis) {
        var by = getByValue(jsonKey);
        return getSelect(by, timeOut, sleepInMillis);
    }

    private Select getSelect(String jsonKey, long timeOut) {
        var by = getByValue(jsonKey);
        return getSelect(by, timeOut, DEFAULT_SLEEP_IN_MILLIS);
    }

    protected Select getSelect(By by, long timeOut) {
        return getSelect(by, timeOut, DEFAULT_SLEEP_IN_MILLIS);
    }

    protected Select getSelect(By by) {
        return getSelect(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    protected Select getSelect(String jsonKey) {
        var by = getByValue(jsonKey);
        return getSelect(by, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    protected void selectByIndex(By by, int index, long timeout, long sleepInMillis) {
        getSelect(by, timeout, sleepInMillis).selectByIndex(index);
    }

    public void selectByIndex(By by, int index) {
        selectByIndex(by, index, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByIndex(By by, int index, long timeout) {
        selectByIndex(by, index, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByIndex(String jsonKey, int index) {
        var by = getByValue(jsonKey);
        selectByIndex(by, index, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByIndex(String jsonKey, int index, long timeout) {
        var by = getByValue(jsonKey);
        selectByIndex(by, index, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByIndex(String jsonKey, int index, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        selectByIndex(by, index, timeout, sleepInMillis);
    }

    public void selectByValue(By by, String value, long timeout, long sleepInMillis) {
        getSelect(by, timeout, sleepInMillis).selectByValue(value);
    }

    public void selectByValue(By by, String value) {
        selectByValue(by, value, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByValue(By by, String value, long timeout) {
        selectByValue(by, value, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByValue(String jsonKey, String value) {
        var by = getByValue(jsonKey);
        selectByValue(by, value, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByValue(String jsonKey, String value, long timeout) {
        var by = getByValue(jsonKey);
        selectByValue(by, value, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByValue(String jsonKey, String value, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        selectByValue(by, value, timeout, sleepInMillis);
    }

    public void selectByVisibleText(By by, String value, long timeout, long sleepInMillis) {
        getSelect(by, timeout, sleepInMillis).selectByVisibleText(value);
    }

    public void selectByVisibleText(By by, String value) {
        selectByVisibleText(by, value, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByVisibleText(By by, String value, long timeout) {
        selectByVisibleText(by, value, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByVisibleText(String jsonKey, String value) {
        var by = getByValue(jsonKey);
        selectByVisibleText(by, value, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByVisibleText(String jsonKey, String value, long timeout) {
        var by = getByValue(jsonKey);
        selectByVisibleText(by, value, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void selectByVisibleText(String jsonKey, String value, long timeout, long sleepInMillis) {
        var by = getByValue(jsonKey);
        selectByVisibleText(by, value, timeout, sleepInMillis);
    }

    public void multipleSelectByVisibleText(String jsonKey, long timeout, long sleepInMillis, String[] value) {
        if (getSelect(jsonKey).isMultiple()) {
            Arrays.stream(value)
                    .forEach(v -> selectByVisibleText(jsonKey, v, timeout, sleepInMillis));
        } else {
            log.warn("'{}' is not multiple select", jsonKey);
        }
    }

    public void multipleSelectByVisibleText(String jsonKey, long timeout, String[] value) {
        if (getSelect(jsonKey).isMultiple()) {
            Arrays.stream(value)
                    .forEach(v -> selectByVisibleText(jsonKey, v, timeout));
        } else {
            log.warn("'{}' is not multiple select", jsonKey);
        }
    }

    public void multipleSelectByVisibleText(String jsonKey, String[] value) {
        if (getSelect(jsonKey).isMultiple()) {
            Arrays.stream(value)
                    .forEach(v -> selectByVisibleText(jsonKey, v));
        } else {
            log.warn("'{}' is not multiple select", jsonKey);
        }
    }
}
