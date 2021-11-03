package helper;

import exceptions.FileNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClickHelper extends GetElementHelper {

    private final Logger log = LogManager.getLogger(ClickHelper.class);

    public void clickElement(String jsonKey) throws FileNotFound {
        new ClickableHelper().waitForClickable(jsonKey).click();
        log.info("{} element clicked", jsonKey);
    }

    public void clickElement(String jsonKey, int timeout) throws FileNotFound {
        new ClickableHelper().waitForClickable(jsonKey, timeout).click();
        log.info("{} element clicked", jsonKey);
    }
}
