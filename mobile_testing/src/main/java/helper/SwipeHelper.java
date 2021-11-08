package helper;

import enums.Directions;
import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import platform.manager.PlatformManager;

import java.time.Duration;

import static enums.Directions.*;

public class SwipeHelper extends GetElementHelper {

    private final Logger log = LogManager.getLogger(SwipeHelper.class);

    protected void swipeRight(String jsonKey) throws FileNotFound {
        var elm = getElementWithWait(jsonKey);
        int width = elm.getLocation().x;
        int height = elm.getLocation().y;
        swipeByPoint(RIGHT, width, height);
    }

    protected void swipeLeft(String jsonKey) throws FileNotFound {
        var elm = getElementWithWait(jsonKey);
        int width = elm.getLocation().x;
        int height = elm.getLocation().y;
        swipeByPoint(LEFT, width, height);
    }

    protected void swipeUp(String jsonKey) throws FileNotFound {
        var elm = getElementWithWait(jsonKey);
        int width = elm.getLocation().x;
        int height = elm.getLocation().y;
        swipeByPoint(UP, width, height);
        log.info("Touched on the {} element and swipe up", jsonKey);
    }

    protected void swipeDown(String jsonKey) throws FileNotFound {
        var elm = getElementWithWait(jsonKey);
        int width = elm.getLocation().x;
        int height = elm.getLocation().y;
        swipeByPoint(DOWN, width, height);
        log.info("Touched on the {} element and swipe down", jsonKey);
    }

    protected void swipeRightFromSourceToTarget(String sourceElmKey, String targetElmKey) throws FileNotFound {
        swipe(RIGHT, sourceElmKey, targetElmKey);
    }

    protected void swipeLeftFromSourceToTarget(String sourceElmKey, String targetElmKey) throws FileNotFound {
        swipe(LEFT, sourceElmKey, targetElmKey);
    }

    protected void swipeUpFromSourceToTarget(String sourceElmKey, String targetElmKey) throws FileNotFound {
        swipe(UP, sourceElmKey, targetElmKey);
    }

    protected void swipeDownFromSourceToTarget(String sourceElmKey, String targetElmKey) throws FileNotFound {
        swipe(DOWN, sourceElmKey, targetElmKey);
    }

    private void swipe(Directions directions, String sourceElmKey, String targetElmKey) throws FileNotFound {
        if (getElementsWithoutWait(targetElmKey).size() == 1) {
            swipeUntilBetweenTwoElements(sourceElmKey, targetElmKey);
        }
        MobileElement element = getElementWithWait(sourceElmKey);
        int width = element.getLocation().x;
        int height = element.getLocation().y;
        int i = 0;
        boolean isDisplayed;
        boolean isEnabled;
        boolean isPresent;
        do {
            swipeByPoint(directions, width, height);
            i++;
            if (i == 10) {
                throw new NoSuchElementException(getByValue(targetElmKey).toString());
            }
            try {
                isPresent = getElementsWithoutWait(targetElmKey).size() == 0;
                isDisplayed = getElementWithoutWait(targetElmKey).isDisplayed();
                isEnabled = getElementWithoutWait(targetElmKey).isEnabled();
            } catch (Exception e) {
                isDisplayed = false;
                isEnabled = false;
                isPresent = true;
            }
        } while (isPresent || !isDisplayed || !isEnabled);
    }


    private void swipeByPoint(Directions direction, int width, int height) {
        log.info("swipeScreen(): dir: '{}'", direction);
        final int PRESS_TIME = 300;
        Point pointStart, pointEnd;
        PointOption pointOptionStart, pointOptionEnd;
        var driver = PlatformManager.getInstances().getDriver();
        int endX, endY;
        switch (direction) {
            case DOWN -> { // center of footer
                endY = driver.manage().window().getSize().height;
                pointEnd = new Point(width, endY);
                pointStart = new Point(width, height);
            }
            case UP -> {
                endY = driver.manage().window().getSize().height;
                pointStart = new Point(width, height);
                pointEnd = new Point(width, endY);
            }
            case RIGHT -> {
                endX = driver.manage().window().getSize().width;
                pointEnd = new Point(endX, height);
                pointStart = new Point(width, height);
            }
            case LEFT -> {
                endX = driver.manage().window().getSize().width;
                pointStart = new Point(endX, height);
                pointEnd = new Point(width, height);
            }
            default -> throw new IllegalArgumentException("swipeScreen(): dir: '" + direction + "' NOT supported");
        }
        pointOptionStart = PointOption.point(pointStart.x, pointStart.y);
        pointOptionEnd = PointOption.point(pointEnd.x, pointEnd.y);
        try {
            new TouchHelper().getTouchAction()
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            log.fatal("swipeScreen(): TouchAction FAILED\n {}", e.getMessage());
        }

    }

    protected void swipeUntilBetweenTwoElements(String sourceJsonKey, String targetJson) throws FileNotFound {
        var element1 = getElementWithWait(sourceJsonKey);
        var element2 = getElementWithWait(targetJson);
        Point firstPoint = element1.getLocation();
        Point secondPoint = element2.getLocation();
        new TouchHelper().getTouchAction()
                .longPress(PointOption.point(secondPoint.x, secondPoint.y))
                .moveTo(PointOption.point(firstPoint.x, firstPoint.y))
                .release().perform();
    }
}
