package helper;

import elements.GetElement;
import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;

import java.util.List;

public class GetElementHelper extends GetElement {

    private PresenceHelper presenceHelper;
    private VisibleHelper visibleHelper;

    public GetElementHelper() {
        presenceHelper = new PresenceHelper();
        visibleHelper = new VisibleHelper();
    }

    protected MobileElement getElementWithWait(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        presenceHelper.waitUntilPresence(by);
        return visibleHelper.waitUntilVisible(by);
    }

    protected MobileElement getElementWithWait(String jsonKey, int timeOut) throws FileNotFound {
        var by = getByValue(jsonKey);
        presenceHelper.waitUntilPresence(by, timeOut);
        return visibleHelper.waitUntilVisible(by, timeOut);
    }

    protected MobileElement getElementWithoutWait(String jsonKey) throws FileNotFound {
        return getMobileElement(jsonKey);
    }

    protected List<MobileElement> getElementsWithWait(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        presenceHelper.waitUntilPresenceAll(by);
        return visibleHelper.waitUntilVisibleAll(by);
    }

    protected List<MobileElement> getElementsWithWait(String jsonKey, int timeOut) throws FileNotFound {
        var by = getByValue(jsonKey);
        presenceHelper.waitUntilPresenceAll(by, timeOut);
        new VisibleHelper().waitUntilVisibleAll(by, timeOut);
        return visibleHelper.waitUntilVisibleAll(by, timeOut);
    }

    protected List<MobileElement> getElementsWithoutWait(String jsonKey) throws FileNotFound {
        return getMobileElements(jsonKey);
    }
}
