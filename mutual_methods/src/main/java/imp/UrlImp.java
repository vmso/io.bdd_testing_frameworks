package imp;

import com.thoughtworks.gauge.Step;
import helper.RequestUrlHelper;
import io.cucumber.java.en.Given;

public class UrlImp extends RequestUrlHelper {

    @Step({"Add base url <url>", "Url ekle <url>"})
    public void addBaseUrlToReq(String url) {
        addBaseUrl(url);
    }

    @Step({"Add endpoint <url>", "Endpoint ekle <url>", "Add base path <url>"})
    public void addBasePathToReq(String url) {
        addBasePath(url);
    }
}
