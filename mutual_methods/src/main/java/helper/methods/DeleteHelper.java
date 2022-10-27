package helper.methods;

import helper.ApiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DeleteHelper{

    private final Logger log = LogManager.getLogger(DeleteHelper.class);

    /**
     * Create a delete request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void deleteRequest(String url) {
        var response = ApiHelper.getInstance().getRequestSpecification().delete(url)
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
        var response = ApiHelper.getInstance().getRequestSpecification().delete()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Delete request sent");
    }
}
