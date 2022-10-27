package helper.methods;

import helper.ApiHelper;
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
        var response = ApiHelper.getInstance().getRequestSpecification().options(url)
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
        var response = ApiHelper.getInstance().getRequestSpecification().options()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Options request sent");
    }
}
