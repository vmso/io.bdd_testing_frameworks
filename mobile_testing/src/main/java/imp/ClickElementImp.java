package imp;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import exceptions.FileNotFound;
import helper.ClickHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

public class ClickElementImp extends ClickHelper {

    @Step("Click the <element> element")
    @And("Click the {string} element")
    public void clickElementStep(String element) {
        clickElement(element);
    }

    @Step("Click the <element> element if exists")
    @And("Click the {string} element if exists")
    public void clickElementIfExistsStep(String element) {
        clickIfExists(element);
    }

    @Step("Wait for <second> and then click the <element> element if exists")
    @And("Wait for <int> and then click the <string> element if exists")
    public void clickElementIfExistsStepWithTimeout(int timeout, String element) {
        clickIfExists(element, timeout);
    }


    @Step("Wait for the presence of it for <second> seconds and click <elm> element")
    @And("Wait for the presence of it for {int} seconds and click {string} element")
    public void clickElementStep(int timeout, String element) {
        clickElement(element, timeout);
    }

    @Step("Click <x> width and <height> height point")
    @And("Click {int} width and {int} height point")
    public void clickPoint(int width, int height) {
        clickThePoint(width, height);
    }


}
