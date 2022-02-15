package helper.methods;

import enums.RequestInfo;
import helper.ApiHelper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class HeadHelper {

    private final Logger log = LogManager.getLogger(GetHelper.class);

    /**
     * Create a head request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     * @return is request result as response
     */
    protected Response headRequest(String url) {
        Response response = ApiHelper.getInstance().getRequestSpecification().head(url)
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Head request sent to {}", url);
        return response;
    }

    /**
     * Create a head request and update ApiHelper class' response object.
     *
     * @return is request result as response
     */
    protected Response headRequest()  {
        Response response = ApiHelper.getInstance().getRequestSpecification()
                .head()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.info, response);
        log.info("Head request sent");
        return response;
    }
}
