package helper.methods;

import enums.RequestInfo;
import exceptions.RequestNotDefined;
import helper.ApiHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class PutHelper {

    private final Logger log = LogManager.getLogger(PutHelper.class);

    /**
     * Create a put request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     * @return is request result as response
     */
    protected Response putRequest(String url) {
        Response response = ApiHelper.getInstance().getRequestSpecification()
                .put(url)
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Put request sent to {}", url);
        return response;
    }

    /**
     * Create a put request and update ApiHelper class' response object.
     *
     * @return is request result as response
     */
    protected Response putRequest() {
        Response response = ApiHelper.getInstance().getRequestSpecification()
                .put()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Put request sent");
        return response;
    }
}
