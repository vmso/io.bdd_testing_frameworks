package helper;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiHelper {

    private final Logger log = LogManager.getLogger(ApiHelper.class);
    private static ApiHelper instance;
    private RequestSpecification requestSpecification;

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
        return requestSpecification;
    }

    public void setRequestSpecification(RequestSpecification requestSpecification) {
        this.requestSpecification = requestSpecification;
    }

    public void init() {
        requestSpecification = RestAssured.given();

    }

    public void defineNewRequest() {
        init();
    }


}
