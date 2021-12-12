package imp;

import com.thoughtworks.gauge.Step;
import helpers.ClickHelper;
import io.cucumber.java.en.And;

public class ClickImp extends ClickHelper {

    @And("Click on {string}")
    @Step("Click on <string>")
    public void clickImp(String jsonKey) {
        clickElement(jsonKey);
    }

    @And("Click on {string} without wait")
    @Step("Click on <string> without wait")
    public void clickWithOutWaitImp(String jsonKey) {
        clickElementWithoutWait(jsonKey);
    }

    @And("Wait for {string} second presence of {long} then click")
    @Step("Wait for <int> second presence of <element> then click")
    public void clickImp(String jsonKey, long timeout) {
        clickElement(jsonKey, timeout);
    }

    @And("Wait for {string} second with sleep in {long} millis presence of {long} then click")
    @Step("Wait for <long> second with sleep in <long> millis presence of <string> then click")
    public void clickImp(String jsonKey, long timeout, long sleepInMillis) {
        clickElement(jsonKey, timeout, sleepInMillis);
    }

    @And("Click if exists if so click on {string}")
    @Step("Click if exists if so click on <element>")
    public void clickIfExistsImp(String jsonKey) {
        checkIfExitsClick(jsonKey);
    }

    @And("Click if exists if so click on {string}, if not click {string}")
    @Step("Click if exists on <element>if not click <string>")
    public void ifExistsClickXIfNotClickYImp(String xJsonKey, String yJsonKey) {
        ifExistsClickXIfNotClickY(xJsonKey, yJsonKey);
    }

    @And("Wait for {long} second presence of {string} then click, if not exists click {string}")
    @Step("Wait for <long> second presence of <string> then click, if not exists click <string>")
    public void ifExistsClickXIfNotClickYImp(long timeout, String xJsonKey, String yJsonKey) {
        ifExistsClickXIfNotClickY(xJsonKey, yJsonKey, timeout);
    }

    @And("Click at {element} with javaScript")
    @Step("Click at <string> with javaScript")
    public void clickWithJavaScriptImp(String jsonKey) {
        clickWithJavaScript(jsonKey);
    }
}
