package helper.methods;

import helper.ApiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PutHelper {

    private final Logger log = LogManager.getLogger(PutHelper.class);

    /**
     * Create a put request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void putRequest(String url) {
        var response = ApiHelper.getInstance().getRequestSpecification()
                .put(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Put request sent to {}", url);
    }

    /**
     * Create a put request and update ApiHelper class' response object.
     *
     */
    protected void putRequest() {
        var response = ApiHelper.getInstance().getRequestSpecification()
                .put()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Put request sent");
    }
}
