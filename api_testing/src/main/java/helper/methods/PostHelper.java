package helper.methods;

import enums.RequestInfo;
import exceptions.RequestNotDefined;
import helper.ApiHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class PostHelper {

    private final Logger log = LogManager.getLogger(PostHelper.class);

    /**
     * Create a post request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     * @return is request result as response
     */
    protected Response postRequest(String url) {
        Response response = ApiHelper.getInstance().getRequestSpecification().post(url)
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Post request sent to {}", url);
        return response;
    }

    /**
     * Create a post request and update ApiHelper class' response object.
     *
     * @return is request result as response
     */
    protected Response postRequest() {
        Response response = ApiHelper.getInstance().getRequestSpecification().post()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Post request sent");
        return response;
    }
}
