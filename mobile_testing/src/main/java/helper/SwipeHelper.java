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

    protected void swipeRight(String jsonKey) {
        var elm = getElementWithWait(jsonKey);
        int width = elm.getLocation().x;
        int height = elm.getLocation().y;
        swipeByPoint(RIGHT, width, height);
    }

    protected void swipeLeft(String jsonKey) {
        var elm = getElementWithWait(jsonKey);
        int width = elm.getLocation().x;
        int height = elm.getLocation().y;
        swipeByPoint(LEFT, width, height);
    }

    protected void swipeUp(String jsonKey) {
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

    protected void swipeRightFromSourceToTarget(String sourceElmKey, String targetElmKey) {
        swipe(RIGHT, sourceElmKey, targetElmKey, 15);
    }

    protected void swipeRightFromSourceToTarget(String sourceElmKey, String targetElmKey, int tryCount) {
        swipe(RIGHT, sourceElmKey, targetElmKey, tryCount);
    }

    protected void swipeLeftFromSourceToTarget(String sourceElmKey, String targetElmKey) {
        swipe(LEFT, sourceElmKey, targetElmKey, 15);
    }

    protected void swipeLeftFromSourceToTarget(String sourceElmKey, String targetElmKey, int tryCount) {
        swipe(LEFT, sourceElmKey, targetElmKey, tryCount);
    }

    protected void swipeUpFromSourceToTarget(String sourceElmKey, String targetElmKey) {
        swipe(UP, sourceElmKey, targetElmKey, 15);
    }

    protected void swipeUpFromSourceToTarget(String sourceElmKey, String targetElmKey, int tryCount) {
        swipe(UP, sourceElmKey, targetElmKey, tryCount);
    }

    protected void swipeDownFromSourceToTarget(String sourceElmKey, String targetElmKey) {
        swipe(DOWN, sourceElmKey, targetElmKey, 15);
    }

    protected void swipeDownFromSourceToTarget(String sourceElmKey, String targetElmKey, int tryCount) {
        swipe(DOWN, sourceElmKey, targetElmKey, tryCount);
    }

    private void swipe(Directions directions, String sourceElmKey, String targetElmKey, int tryCount) {
        boolean isDisplayed;
        try {
            isDisplayed = getElementWithWait(targetElmKey).isDisplayed();
        }catch (NoSuchElementException e){
            isDisplayed=false;
        }
        if (isDisplayed) {
            swipeUntilBetweenTwoElements(sourceElmKey, targetElmKey);
        }
        MobileElement element = getElementWithWait(sourceElmKey);
        int width = element.getLocation().x;
        int height = element.getLocation().y;
        int i = 0;

        do {
            swipeByPoint(directions, width, height);
            i++;
            if (i == tryCount) {
                throw new NoSuchElementException(getByValue(targetElmKey).toString());
            }
            try {
                isDisplayed = getElementWithoutWait(targetElmKey).isDisplayed();
            } catch (NoSuchElementException e) {
                isDisplayed = false;
            }
        } while (!isDisplayed);
    }


    private void swipeByPoint(Directions direction, int width, int height) {
        log.info("swipeScreen to '{}'", direction);
        final int PRESS_TIME = 300;
        Point pointStart, pointEnd;
        PointOption pointOptionStart, pointOptionEnd;
        var driver = PlatformManager.getInstances().getDriver();
        var size = driver.manage().window().getSize();
        int endX, endY;
        switch (direction) {
            case UP -> {
                endY = (int) (size.height * 0.10);
                pointEnd = new Point(width, endY);
                pointStart = new Point(width, height);
            }
            case DOWN -> {
                endY = size.height;
                pointStart = new Point(width, height);
                pointEnd = new Point(width, endY);
            }
            case LEFT -> {
                endX = (int) (size.width * 0.10);
                pointStart = new Point(width, height);
                pointEnd = new Point(endX, height);
            }
            case RIGHT -> {
                endX = driver.manage().window().getSize().width;
                pointStart = new Point(width, height);
                pointEnd = new Point(endX, height);
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
