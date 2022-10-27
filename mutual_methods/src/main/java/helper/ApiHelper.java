package helper;

import enums.RequestInfo;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ReuseStoreData;

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
        return (RequestSpecification) ReuseStoreData.get(RequestInfo.REQUEST.info);
    }

    public Response getResponse() {
        return (Response) ReuseStoreData.get(RequestInfo.RESPONSE.info);
    }

    public void setResponse(Response response) {
        ReuseStoreData.put(RequestInfo.RESPONSE.info, response);
    }

    public void init() {
        ReuseStoreData.put(RequestInfo.REQUEST.info, RestAssured.given().relaxedHTTPSValidation());
    }

    public void defineNewRequest() {
        init();
        ReuseStoreData.remove("curl");
    }

}
