package helper.methods;

import helper.ApiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PostHelper {

    private final Logger log = LogManager.getLogger(PostHelper.class);

    /**
     * Create a post request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void postRequest(String url) {
        var response = ApiHelper.getInstance().getRequestSpecification().post(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Post request sent to {}", url);
    }

    /**
     * Create a post request and update ApiHelper class' response object.
     */
    protected void postRequest() {
        var response = ApiHelper.getInstance().getRequestSpecification().post()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Post request sent");
    }
}
