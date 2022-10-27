package helper.methods;

import filter.RestAssuredFilter;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DeleteHelper {
    private final Logger log = LogManager.getLogger(DeleteHelper.class);

    /**
     * Create a delete request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void deleteRequest(String url) {
        Response response = ApiHelper.getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .delete(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Delete request sent to {}", url);
    }

    /**
     * Create a delete request and update ApiHelper class' response object.
     */
    protected void deleteRequest() {
        Response response = ApiHelper.getInstance()
                .getRequestSpecification()
                .filter(new RestAssuredFilter())
                .delete()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Delete request sent");
    }
}
