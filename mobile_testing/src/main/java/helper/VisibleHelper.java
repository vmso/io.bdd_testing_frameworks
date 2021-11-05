package helper;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class VisibleHelper extends WaitHelper{

    public MobileElement waitUntilVisible (By by, int timeout){
        return (MobileElement) getWebDriverWait(timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public MobileElement waitUntilVisible (By by){
        return waitUntilVisible(by,DEFAULT_TIME_OUT);
    }

    public List<MobileElement> waitUntilVisibleAll (By by, int timeout){
        var webElements = getWebDriverWait(timeout)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        var mobileElement = new ArrayList<MobileElement>();
        webElements.stream().map(e -> (MobileElement) e).forEach(mobileElement::add);
        return mobileElement;
    }

    public List<MobileElement> waitUntilVisibleAll (By by){
        return waitUntilVisibleAll(by,DEFAULT_TIME_OUT);
    }
}
