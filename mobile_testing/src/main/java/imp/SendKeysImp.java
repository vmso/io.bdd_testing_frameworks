package imp;

import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.SendKeysHelper;

public class SendKeysImp extends SendKeysHelper {

    @Step("SendKeys the <element> for <key>")
    public void sendKeyStep (String element, String key) throws FileNotFound {
        sendKeys(element, key);
    }

    @Step("SendKeys the <element> for <key> wait <timeout>")
    public void sendKeyStep (String element, String key, int timeout) throws FileNotFound {
        sendKeys(element, key, timeout);
    }

    @Step("SendKeys Without the <element> for <key>")
    public void sendKeyWithoutStep (String element, String key) throws FileNotFound {
        sendKeysWithoutWait(element, key);
    }

}
