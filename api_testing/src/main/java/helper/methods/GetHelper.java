package helper.methods;

import enums.RequestInfo;
import exceptions.RequestNotDefined;
import helper.ApiHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class GetHelper {

    private final Logger log = LogManager.getLogger(GetHelper.class);

    /**
     * Create a get request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     * @return is request result as response
     */
    protected Response getRequest(String url){
        Response response = ApiHelper.getInstance().getRequestSpecification()
                .get(url)
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Get request sent to {}", url);
        return response;
    }

    /**
     * Create a get request and update ApiHelper class' response object.
     *
     * @return is request result as response
     */
    protected Response getRequest() {
        Response response = ApiHelper.getInstance().getRequestSpecification().get()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Get request sent");
        return response;
    }
}
