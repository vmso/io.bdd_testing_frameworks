package helper.methods;

import enums.RequestInfo;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class OptionsHelper {

    private final Logger log = LogManager.getLogger(OptionsHelper.class);

    /**
     * Create a options request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void optionsRequest(String url) {
        Response response = ApiHelper.getInstance().getRequestSpecification().options(url)
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Options request sent to {}", url);
    }

    /**
     * Create a options request and update ApiHelper class' response object.
     */
    protected void optionsRequest() {
        Response response = ApiHelper.getInstance().getRequestSpecification().options()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Options request sent");
    }
}
