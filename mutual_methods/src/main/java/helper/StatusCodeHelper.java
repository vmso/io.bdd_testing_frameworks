package helper;

import enums.RequestInfo;
import exceptions.NullResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class StatusCodeHelper extends ResponseBodyHelper {

    private final Logger log = LogManager.getLogger(StatusCodeHelper.class);

    /**
     * checks if the status code is the same as the requested status code.
     *
     * @param statusCode is requested status code
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    protected void checkStatusCode(int statusCode) throws NullResponse {
        checkIfResponseNull();
        Response response = (Response) StoreApiInfo.get(RequestInfo.RESPONSE.info);
        response.then().statusCode(statusCode);
        log.info("Status code is {}", statusCode);
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
            Response response = (Response) StoreApiInfo.get(RequestInfo.RESPONSE.info);
            return response.statusCode();
        } catch (Exception e) {
            log.warn("Status code couldn't get an error occurred");
            throw e;
        }
    }
}
