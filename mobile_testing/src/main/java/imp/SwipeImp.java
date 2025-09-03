package imp;

import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.SwipeHelper;
import io.cucumber.java.en.And;

public class SwipeImp extends SwipeHelper {

    @Step("Touch the <element> and swipe down")
    @And("Touch the {string} and swipe down")
    public void swipeDownStep(String jsonKey) throws FileNotFound {
        swipeDown(); // No argument
    }

    @Step("Touch the <element> and swipe up")
    @And("Touch the {string} and swipe up")
    public void swipeUpStep(String jsonKey) throws FileNotFound {
        swipeUp(); // No argument
    }

    @Step("Touch the <element> and swipe left")
    @And("Touch the {string} and swipe left")
    public void swipeLeftStep(String jsonKey) throws FileNotFound {
        swipeLeft(); // No argument
    }

    @Step("Touch the <element> and swipe right")
    @And("Touch the {string} and swipe right")
    public void swipeRightStep(String jsonKey) throws FileNotFound {
        swipeRight(); // No argument
    }

    // The following methods are obsolete and have been removed in the helper:
    // swipeLeftFromSourceToTarget, swipeRightFromSourceToTarget, swipeUpFromSourceToTarget, swipeDownFromSourceToTarget
    // If you need to reimplement them, please provide new helper logic.
}
