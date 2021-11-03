package imp;

import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.ClickHelper;
import io.cucumber.java.en.Given;

public class ClickElementImp extends ClickHelper {

    @Step("Click <element> element")
    @Given("Click {string} element")
    public void clickElementStep(String element) throws FileNotFound {
        clickElement(element);
    }

}
