package imp;

import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.SendKeysHelper;
import io.cucumber.java.en.And;

public class SendKeysImp extends SendKeysHelper {

    @Step("Send <keys> text to the <element> element")
    @And("Send {string} text to the {string} element")
    public void sendKeyStep(String key, String element) throws FileNotFound {
        sendKeys(element, key);
    }

    @Step("Send Keys the <element> for <key> wait <timeout>")
    public void sendKeyStep(String element, String key, int timeout) throws FileNotFound {
        sendKeys(element, key, timeout);
    }

    @Step("Send Keys Without the <element> for <key>")
    public void sendKeyWithoutStep(String element, String key) throws FileNotFound {
        sendKeysWithoutWait(element, key);
    }

}
