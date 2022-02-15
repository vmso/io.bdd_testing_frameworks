package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.OptionsHelper;
import io.cucumber.java.en.When;

public class OptionsRequestsImp extends OptionsHelper {

    @Step({"Options request", "Options isteği gönder"})
    @When("Options request")
    public void optionRequests() throws RequestNotDefined {
        optionsRequest();
    }

    @Step({"Options request <url>", "Options isteği gönder <url>"})
    @When("Options request {string}")
    public void optionRequests(String url) throws RequestNotDefined {
        optionsRequest(url);
    }
}
