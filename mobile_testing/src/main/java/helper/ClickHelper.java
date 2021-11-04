package helper;

import exceptions.FileNotFound;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;

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

    public void clickIfExists(String jsonKey) throws FileNotFound {
        var presenceOfElm = new PresenceHelper();
        var by = getBy(jsonKey);
        if (presenceOfElm.isPresence(by)) {
            clickElement(jsonKey);
        }
    }

    public void clickIfExists(String jsonKey, int timeout) throws FileNotFound {
        var presenceOfElm = new PresenceHelper();
        var by = getBy(jsonKey);
        if (presenceOfElm.isPresence(by, timeout)) {
            clickElement(jsonKey, timeout);
        }
    }

    public void clickThePoint(int width, int height) {
        var touchAction = new TouchHelper();
        var point = new Point(width, height);
        var pointOption = PointOption.point(point.x, point.y);
        touchAction.getTouchAction().press(pointOption).release().perform();
        log.info("{} width and {} point clicked", width, height);
    }

}
