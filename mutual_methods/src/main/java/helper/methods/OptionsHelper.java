package helper.methods;

import filter.RestAssuredFilter;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OptionsHelper {
    private final Logger log = LogManager.getLogger(OptionsHelper.class);

    /**
     * Create an options request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void optionsRequest(String url) {
        Response response = ApiHelper
                .getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .options(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Options request sent to {}", url);
    }

    /**
     * Create an options request and update ApiHelper class' response object.
     */
    protected void optionsRequest() {
        Response response = ApiHelper
                .getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .options()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Options request sent");
    }
}
