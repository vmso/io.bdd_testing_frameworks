package helpers;

import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class SendKeysHelper extends GetElementHelper {

    public void sendKeys(String jsonKey, String keys, long timeout, long sleepInMillis) {
        getClickableElement(jsonKey, timeout, sleepInMillis).sendKeys(keys);
    }

    public void sendKeys(String jsonKey, String keys, long timeout) {
        sendKeys(jsonKey, keys, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void sendKeys(String jsonKey, String keys) {
        sendKeys(jsonKey, keys, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void sendKeys(By by, String keys, long timeout, long sleepInMillis) {
        getClickableElement(by, timeout, sleepInMillis).sendKeys(keys);
    }

    public void sendKeys(By by, String keys, long timeout) {
        sendKeys(by, keys, timeout, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void sendKeys(By by, String keys) {
        sendKeys(by, keys, DEFAULT_WAIT, DEFAULT_SLEEP_IN_MILLIS);
    }

    public void sendKeysCharByChar(By by, String keys) {
        var chars = keys.toCharArray();
        Stream.of(chars).forEach(c -> sendKeys(by, String.valueOf(c)));
    }

    public void sendKeysCharByChar(String jsonKey, String keys) {
        var chars = keys.toCharArray();
        Stream.of(chars).forEach(c -> sendKeys(jsonKey, String.valueOf(c)));
    }

    public void sendKeysWithKeyboard(String jsonKey, String keys) {
        new ActionsHelper().sendKeys(jsonKey,keys);
    }

    public void sendKeysWithKeyboard(By by, String keys) {
        new ActionsHelper().sendKeys(by,keys);
    }

}
