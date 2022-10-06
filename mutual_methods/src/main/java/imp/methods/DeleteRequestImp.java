package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.DeleteHelper;
import io.cucumber.java.en.When;

public class DeleteRequestImp extends DeleteHelper {


    @Step({"Delete request", "Delete isteği gönder"})
    public void deleteRequests() throws RequestNotDefined {
        deleteRequest();
    }

    @Step({"Delete request <url>", "Delete isteği gönder <url>"})
    public void deleteRequests(String url) throws RequestNotDefined {
        deleteRequest(url);
    }
}
