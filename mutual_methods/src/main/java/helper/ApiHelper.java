package helper;

import enums.RequestInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class ApiHelper {

    private final Logger log = LogManager.getLogger(ApiHelper.class);
    private static ApiHelper instance;

    private ApiHelper() {
        init();
    }

    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    public RequestSpecification getRequestSpecification() {
        return (RequestSpecification) StoreApiInfo.get(RequestInfo.REQUEST.info);
    }


    public void init() {
        StoreApiInfo.put(RequestInfo.REQUEST.info, RestAssured.given());
    }

    public void defineNewRequest() {
        init();
        log.info("New requests defined");
    }


}
