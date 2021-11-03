package helper;

import elements.GetElement;
import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;

import java.util.List;

public class GetElementHelper extends GetElement {

    protected MobileElement getElementWithWait(String jsonKey) throws FileNotFound {
        var by = getBy(jsonKey);
        new PrecancelHelper().waitUntilPresence(by);
        //todo visible wait will be add
        return getMobileElement(by);
    }

    protected MobileElement getElementWithWait(String jsonKey, int timeOut) throws FileNotFound {
        var by = getBy(jsonKey);
        new PrecancelHelper().waitUntilPresence(by, timeOut);
        //todo visible wait will be add
        return getMobileElement(by);
    }

    protected MobileElement getElementWithoutWait(String jsonKey) throws FileNotFound {
        return getMobileElement(jsonKey);
    }

    protected List<MobileElement> getElementsWithWait(String jsonKey) throws FileNotFound {
        var by = getBy(jsonKey);
        new PrecancelHelper().waitUntilPresence(by);
        //todo visible wait will be add
        return getMobileElements(by);
    }

    protected List<MobileElement> getElementsWithWait(String jsonKey, int timeOut) throws FileNotFound {
        var by = getBy(jsonKey);
        new PrecancelHelper().waitUntilPresence(by, timeOut);
        //todo visible wait will be add
        return getMobileElements(by);
    }

    protected List<MobileElement> getElementsWithoutWait(String jsonKey) throws FileNotFound {
        return getMobileElements(jsonKey);
    }
}
