package imp;

import com.thoughtworks.gauge.Step;
import exceptions.NullResponse;
import helper.StatusCodeHelper;
import io.cucumber.java.en.Then;

public class StatusCodeImp extends StatusCodeHelper {

    @Step({"Check if status code is <code>",
            "Statü kodunu kontrol et <kod> mü?",
            "Verify that the status code is <code>"})
    @Then("Verify that the status code is {int}")
    public void checkStatusCodeFromRes(Integer key) throws NullResponse {
        checkStatusCode(key);
    }
}
