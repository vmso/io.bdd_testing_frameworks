package helper;

import exceptions.FileNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttributeAndTextHelper extends GetElementHelper {

    private final Logger log = LogManager.getLogger(AttributeAndTextHelper.class);

    public String getElmText(String jsonKey) throws FileNotFound {
        return getElementWithWait(jsonKey).getText();
    }

    public String getAttribute(String jsonKey, String attribute) throws FileNotFound {
        return getElementWithWait(jsonKey).getAttribute(attribute);
    }

    public String getLocation(String jsonKey, String attribute) throws FileNotFound {
        return getElementWithWait(jsonKey).getLocation().toString();
    }

    public Integer getElementSize(String jsonKey) {
        return getElementsWithWait(jsonKey).size();
    }
}
