package imp;

import com.thoughtworks.gauge.Step;
import helper.RequestUrlHelper;
import io.cucumber.java.en.Given;

public class UrlImp extends RequestUrlHelper {

    @Step({"Add base url <url>", "Url ekle <url>"})
    @Given("base url {string}")
    public void addBaseUrlToReq(String url) {
        addBaseUrl(url);
    }

    @Step({"Add endpoint <url>", "Endpoint ekle <url>", "Add base path <url>"})
    @Given("endpoint {string}")
    public void addBasePathToReq(String url) {
        addBasePath(url);
    }
}
