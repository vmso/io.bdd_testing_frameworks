package helper.methods;

import filter.RestAssuredFilter;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PatchHelper {
    private final Logger log = LogManager.getLogger(PatchHelper.class);

    /**
     * Create a patch request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void patchRequest(String url) {
        Response response = ApiHelper
                .getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .patch(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Patch request sent to {}", url);
    }

    /**
     * Create a patch request and update ApiHelper class' response object.
     */
    protected void patchRequest() {
        Response response = ApiHelper.getInstance().getRequestSpecification()
                .filter(new RestAssuredFilter()).patch()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Patch request sent");
    }
}
