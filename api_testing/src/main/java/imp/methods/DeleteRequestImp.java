package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.DeleteHelper;
import io.cucumber.java.en.When;

public class DeleteRequestImp extends DeleteHelper {


    @Step({"Delete request", "Delete isteği gönder"})
    @When("Delete request")
    public void deleteRequests() throws RequestNotDefined {
        deleteRequest();
    }

    @Step({"Delete request <url>", "Delete isteği gönder <url>"})
    @When("Delete request {string}")
    public void deleteRequests(String url) throws RequestNotDefined {
        deleteRequest(url);
    }
}
