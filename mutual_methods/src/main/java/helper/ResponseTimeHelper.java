package helper;

import enums.RequestInfo;
import exceptions.NullResponse;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReuseStoreData;

import java.util.concurrent.TimeUnit;

public class ResponseTimeHelper extends ResponseBodyHelper {
    private final Logger log = LogManager.getLogger(ResponseTimeHelper.class);
    private Long responseTime;

    protected Long getRequestTimeInMillis() throws NullResponse {
        checkIfResponseNull();
        Response request = (Response) ReuseStoreData.get(RequestInfo.RESPONSE.info);
        responseTime = request.getTimeIn(TimeUnit.MILLISECONDS);
        log.info("Response time is {} milliseconds", responseTime);
        return responseTime;
    }

    protected Long getRequestTimeInSecond() throws NullResponse {
        checkIfResponseNull();
        Response request = (Response) ReuseStoreData.get(RequestInfo.RESPONSE.info);
        responseTime = request.getTimeIn(TimeUnit.SECONDS);
        log.info("Response time is {} seconds", responseTime);
        return responseTime;
    }
}
