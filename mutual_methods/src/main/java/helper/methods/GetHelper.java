package helper.methods;

import helper.ApiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetHelper {

    private final Logger log = LogManager.getLogger(GetHelper.class);

    /**
     * Create a get request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void getRequest(String url){
        var response = ApiHelper.getInstance().getRequestSpecification()
                .get(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Get request sent to {}", url);
    }

    /**
     * Create a get request and update ApiHelper class' response object.
     *
     */
    protected void getRequest() {
        var response = ApiHelper.getInstance().getRequestSpecification().get()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Get request sent");
    }
}
