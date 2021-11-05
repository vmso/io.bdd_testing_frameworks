package helper;

import elements.GetElement;
import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;

import java.util.List;

public class GetElementHelper extends GetElement {

    protected MobileElement getElementWithWait(String jsonKey) throws FileNotFound {
        var by = getBy(jsonKey);
        new PresenceHelper().waitUntilPresence(by);
        new VisibleHelper().waitUntilVisible(by);
        return getMobileElement(by);
    }

    protected MobileElement getElementWithWait(String jsonKey, int timeOut) throws FileNotFound {
        var by = getBy(jsonKey);
        new PresenceHelper().waitUntilPresence(by, timeOut);
        new VisibleHelper().waitUntilVisibleAll(by, timeOut);
        return getMobileElement(by);
    }

    protected MobileElement getElementWithoutWait(String jsonKey) throws FileNotFound {
        return getMobileElement(jsonKey);
    }

    protected List<MobileElement> getElementsWithWait(String jsonKey) throws FileNotFound {
        var by = getBy(jsonKey);
        new PresenceHelper().waitUntilPresence(by);
        new VisibleHelper().waitUntilVisibleAll(by);
        return getMobileElements(by);
    }

    protected List<MobileElement> getElementsWithWait(String jsonKey, int timeOut) throws FileNotFound {
        var by = getBy(jsonKey);
        new PresenceHelper().waitUntilPresence(by, timeOut);
        new VisibleHelper().waitUntilVisibleAll(by, timeOut);
        return new PresenceHelper().waitUntilPresenceAll(by, timeOut);
    }

    protected List<MobileElement> getElementsWithoutWait(String jsonKey) throws FileNotFound {
        return getMobileElements(jsonKey);
    }
}
