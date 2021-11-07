package imp;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.SwipeHelper;
import io.cucumber.java.en.And;

public class SwipeImp extends SwipeHelper {

    @Step("Touch the <element> and swipe down")
    @And("Touch the {string} and swipe down")
    public void swipeDownStep(String jsonKey) throws FileNotFound {
        swipeDown(jsonKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Touch the <element> and swipe up")
    @And("Touch the {string} and swipe up")
    public void swipeUpStep(String jsonKey) throws FileNotFound {
        swipeUp(jsonKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Touch the <element> and swipe left")
    @And("Touch the {string} and swipe left")
    public void swipeLeftStep(String jsonKey) throws FileNotFound {
        swipeLeft(jsonKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Touch the <element> and swipe right")
    @And("Touch the {string} and swipe right")
    public void swipeRightStep(String jsonKey) throws FileNotFound {
        swipeRight(jsonKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Swipe left from  the <element> until reach the <element>")
    @And("Swipe left from  the {string} until reach the {string}")
    public void swipeLeftFromSourceToTargetStep(String sourceElemKey, String targetElmKey) throws FileNotFound {
        swipeLeftFromSourceToTarget(sourceElemKey, targetElmKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Swipe right from  the <element> until reach the <element>")
    @And("Swipe right from  the {string} until reach the {string}")
    public void swipeRightFromSourceToTargetStep(String sourceElemKey, String targetElmKey) throws FileNotFound {
        swipeRightFromSourceToTarget(sourceElemKey, targetElmKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Swipe up from  the <element> until reach the <element>")
    @And("Swipe up from  the {string} until reach the {string}")
    public void swipeUpFromSourceToTargetStep(String sourceElemKey, String targetElmKey) throws FileNotFound {
        swipeUpFromSourceToTarget(sourceElemKey, targetElmKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Swipe down from  the <element> until reach the <element>")
    @And("Swipe down from  the {string} until reach the {string}")
    public void swipeDownFromSourceToTargetStep(String sourceElemKey, String targetElmKey) throws FileNotFound {
        swipeDownFromSourceToTarget(sourceElemKey, targetElmKey);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
