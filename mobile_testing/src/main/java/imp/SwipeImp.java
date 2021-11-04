package imp;

import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.SwipeHelper;
import io.cucumber.java.en.And;

public class SwipeImp extends SwipeHelper {

    @Step("Touch the <element> and swipe down")
    @And("Touch the {string} and swipe down")
    public void swipeDownStep(String jsonKey) throws FileNotFound {
        swipeDown(jsonKey);
    }

    @Step("Swipe left from  the <element> until reach the <element>")
    @And("Swipe left from  the {string} until reach the {string}")
    public void swipeLeftFromSourceToTargetStep(String sourceElemKey, String targetElmKey) throws FileNotFound {
        swipeLeftFromSourceToTarget(sourceElemKey, targetElmKey);
    }

    @Step("Swipe right from  the <element> until reach the <element>")
    @And("Swipe right from  the {string} until reach the {string}")
    public void swipeRightFromSourceToTargetStep(String sourceElemKey, String targetElmKey) throws FileNotFound {
        swipeRightFromSourceToTarget(sourceElemKey, targetElmKey);
    }
}
