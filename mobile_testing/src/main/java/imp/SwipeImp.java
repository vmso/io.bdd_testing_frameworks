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
}
