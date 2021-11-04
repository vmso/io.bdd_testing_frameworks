package helper;

import exceptions.FileNotFound;

public class SendKeysHelper extends GetElementHelper {

    public void sendKeys(String jsonKey, String text) throws FileNotFound {
        getElementWithWait(jsonKey).sendKeys(text);
    }

    public void senKeysWithKeyBoard(String keys) {
        new GetKeyboard().getKeyBoard().sendKeys(keys);
    }

}
