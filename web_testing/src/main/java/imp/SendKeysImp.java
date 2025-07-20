package imp;

import com.thoughtworks.gauge.Step;
import helpers.SendKeysHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en_scouse.An;

public class SendKeysImp extends SendKeysHelper {

    @Step("Type <text> into <element> element")
    @And("Type {string} into {string} element")
    public void sendKeysImp(String text, String jsonKey) {
            sendKeys(jsonKey, text);
    }

    @Step("Wait for <second> second presence of <element> and type <text> into  it")
    @And("Wait for {long} second presence of <{string} and type {string} into  it")
    public void sendKeysImp(long sec, String jsonKey, String text) {
            sendKeys(jsonKey, text, sec);
    }

    @Step("Wait for <second> second presence of <element> with <millis> pooling and type <text> into  it")
    @And("Wait for {long} second presence of <{string} with {long} pooling and type {string} into  it")
    public void sendKeysImp(long timeout, long poolingWait, String jsonKey, String text) {
            sendKeys(jsonKey, text, timeout, poolingWait);
    }

    @Step("Type <text> char by char into <element> element")
    @And("Type {string} char by char into {string} element")
    public void sendKeysCharByCharImp(String jsonKey, String text) {
            sendKeysCharByChar(jsonKey, text);
    }

    @Step("Type <text> into <element> element with keyboard")
    @And("Type {string} into {string} element with keyboard")
    public void sendKeysWithKeyboardImp(String text, String jsonKey) {
            sendKeysWithKeyboard(jsonKey, text);
    }

}
