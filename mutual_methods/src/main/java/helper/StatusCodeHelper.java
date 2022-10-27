package helper;

import com.thoughtworks.gauge.Gauge;
import enums.RequestInfo;
import exceptions.NullResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReuseStoreData;
import utils.Utils;

public class StatusCodeHelper extends ResponseBodyHelper {

    private final Logger log = LogManager.getLogger(StatusCodeHelper.class);

    /**
     * checks if the status code is the same as the requested status code.
     *
     * @param statusCode is requested status code
     */
    public void checkStatusCode(int statusCode) {
        try {
            ApiHelper.getInstance().getResponse().then().statusCode(statusCode);
            log.info("Status code is {}", statusCode);
        } catch (java.lang.AssertionError e) {
            Utils utils = new Utils();
            log.error("Status code is {}", ApiHelper.getInstance().getResponse().statusCode());
            log.error(ReuseStoreData.get("curl"));
            Gauge.writeMessage(String.valueOf(ReuseStoreData.get("curl")));
            log.error("response\n{}", utils.prettyPrint(ApiHelper.getInstance().getResponse().getBody().asString()));
            Gauge.writeMessage("response\n" + utils.prettyPrint(ApiHelper.getInstance().getResponse().getBody().asString()));
            throw e;
        }
    }

    /**
     * Returns response status code
     *
     * @return is status code as integer
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    protected Integer getStatusCode() throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) ReuseStoreData.get(RequestInfo.RESPONSE.info);
            return response.statusCode();
        } catch (Exception e) {
            log.warn("Status code couldn't get an error occurred");
            throw e;
        }
    }
}
