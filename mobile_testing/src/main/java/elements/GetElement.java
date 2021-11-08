package elements;

import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import platform.manager.PlatformManager;

import java.util.List;

public class GetElement extends GetBy {


    protected MobileElement getMobileElement(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return (MobileElement) PlatformManager.getInstances().getDriver().findElement(by);
    }

    protected MobileElement getMobileElement(By by) {
        return (MobileElement) PlatformManager.getInstances().getDriver().findElement(by);
    }

    protected List<MobileElement> getMobileElements(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        @SuppressWarnings("unchecked")
        var mobileElement = (List<MobileElement>) PlatformManager.getInstances().getDriver().findElements(by);
        return mobileElement;
    }

    protected List<MobileElement> getMobileElements(By by) {
        @SuppressWarnings("unchecked")
        var mobileElement =  (List<MobileElement>) PlatformManager.getInstances().getDriver().findElements(by);
        return mobileElement;
    }
}
