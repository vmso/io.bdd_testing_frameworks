package helper.methods;

import enums.RequestInfo;
import exceptions.RequestNotDefined;
import helper.ApiHelper;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;


public class DeleteHelper{

    private final Logger log = LogManager.getLogger(DeleteHelper.class);

    /**
     * Create a delete request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     * @return is request result as response
     */
    protected Response deleteRequest(String url) {
        Response response = ApiHelper.getInstance().getRequestSpecification().delete(url)
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Delete request sent to {}", url);
        return response;
    }

    /**
     * Create a delete request and update ApiHelper class' response object.
     *
     * @return is request result as response
     */
    protected Response deleteRequest() {
        Response response = ApiHelper.getInstance().getRequestSpecification().delete()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Delete request sent");
        return response;
    }
}
