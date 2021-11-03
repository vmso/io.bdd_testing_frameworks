package helper;

import exceptions.FileNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClickHelper extends GetElementHelper {

    private final Logger log = LogManager.getLogger(ClickHelper.class);

    public void clickElement(String jsonKey) throws FileNotFound {
        getElementWithWait(jsonKey).click();
        log.info("{} element clicked", jsonKey);
    }
}
