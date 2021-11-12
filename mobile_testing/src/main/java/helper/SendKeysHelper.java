package helper;

import exceptions.FileNotFound;

// todo steplerini yaz, OK
public class SendKeysHelper extends GetElementHelper {

    public void sendKeys(String jsonKey, String text) throws FileNotFound {
        getElementWithWait(jsonKey).sendKeys(text);
    }

    public void sendKeys(String jsonKey, String keys, int timeout) throws FileNotFound {
        getElementWithWait(jsonKey, timeout).sendKeys(keys);
    }

    public void sendKeysWithoutWait(String jsonKey, String keys) throws FileNotFound {
        getElementWithoutWait(jsonKey).sendKeys(keys);
    }

}
