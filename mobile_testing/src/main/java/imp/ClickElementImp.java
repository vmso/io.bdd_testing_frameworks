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
    public void clickElementStep(String element) throws FileNotFound {
        clickElement(element);
    }

    @Step("Wait for the presence of it for <second> seconds and click <elm> element")
    @And("Wait for the presence of it for {int} seconds and click {string} element")
    public void clickElementStep(int timeout, String element) throws FileNotFound {
        clickElement(element, timeout);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Click the <element> element if exists")
    @And("Click the {string} element if exists")
    public void clickElementIfExistsStep(String element) throws FileNotFound {
        clickIfExists(element);
    }

    @Step("Wait for <second> and click the <element> element if exists")
    @And("Wait for <int> and click the <string> element if exists")
    public void clickElementIfExistsStepWithTimeOut(int timeout, String element) throws FileNotFound {
        clickIfExists(element, timeout);
    }

    @Step("Wait for the presence of it for <seconds> seconds and click the <element> element if exists")
    @And("Wait for the presence of it for {int} seconds and click the {int} element if exists")
    public void clickElementIfExistsStep(int timeout, String element) throws FileNotFound {
        clickIfExists(element, timeout);
        try {
            Gauge.captureScreenshot();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Step("Click <x> width and <height> height point")
    @And("Click {int} width and {int} height point")
    public void clickPoint(int width, int height) {
        clickThePoint(width, height);
    }


}
