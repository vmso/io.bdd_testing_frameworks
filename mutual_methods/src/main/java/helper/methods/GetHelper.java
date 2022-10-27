package helper.methods;

import filter.RestAssuredFilter;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetHelper {
    private final Logger log = LogManager.getLogger(GetHelper.class);

    /**
     * Create a get request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void getRequest(String url) {
        Response response = ApiHelper.getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .get(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Get request sent");
    }

    /**
     * Create a get request and update ApiHelper class' response object.
     *
     */
    protected void getRequest() {
        Response response = ApiHelper.getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .get()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Get request sent");
    }
}
