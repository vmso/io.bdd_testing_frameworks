package helper;

import exceptions.FileNotFound;

public class AttributeAndTextHelper extends GetElementHelper {

    public String getElmText(String jsonKey) throws FileNotFound {
        return getElementWithWait(jsonKey).getText();
    }

    public String getAttribute(String jsonKey, String attribute) throws FileNotFound {
        return getElementWithWait(jsonKey).getAttribute(attribute);
    }

    public String getLocation(String jsonKey, String attribute) throws FileNotFound {
        return getElementWithWait(jsonKey).getLocation().toString();
    }
}
