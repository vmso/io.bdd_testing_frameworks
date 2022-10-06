package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.HeadHelper;
import io.cucumber.java.en.When;

public class HeadRequestImp extends HeadHelper {

    @Step({"Head request", "Head isteği gönder"})
    public void headRequests() throws RequestNotDefined {
        headRequest();
    }

    @Step({"Head request <url>", "Head isteği gönder <url>"})
    public void headRequests(String url) throws RequestNotDefined {
        headRequest(url);
    }
}
