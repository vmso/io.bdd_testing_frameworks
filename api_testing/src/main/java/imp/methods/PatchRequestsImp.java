package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.PatchHelper;
import io.cucumber.java.en.When;

public class PatchRequestsImp extends PatchHelper {

    @Step({"Patch request", "Patch isteği gönder"})
    @When("Patch request")
    public void patchRequests() throws RequestNotDefined {
        patchRequest();
    }

    @Step({"Patch request <url>", "Patch isteği gönder <url>"})
    @When("Patch request {string}")
    public void patchRequests(String url) throws RequestNotDefined {
        patchRequest(url);
    }
}
