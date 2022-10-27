package helper.methods;

import filter.RestAssuredFilter;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HeadHelper {
    private final Logger log = LogManager.getLogger(GetHelper.class);

    /**
     * Create a head request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void headRequest(String url) {
        Response response = ApiHelper.getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .head(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Head request sent to {}", url);
    }

    /**
     * Create a head request and update ApiHelper class' response object.
     */
    protected void headRequest() {
        Response response = ApiHelper
                .getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .head()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Head request sent");
    }
}
