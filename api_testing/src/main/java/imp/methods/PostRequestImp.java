package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.PostHelper;
import io.cucumber.java.en.When;

public class PostRequestImp extends PostHelper {

    @Step({"Post request", "Post isteği gönder"})
    @When("Send post requests")
    public void postRequests() throws RequestNotDefined {
        postRequest();
    }

    @Step({"Post request <url>", "Post isteği gönder <url>"})
    @When("Send post request {string}")
    public void postRequests(String url) throws RequestNotDefined {
        postRequest(url);
    }
}
