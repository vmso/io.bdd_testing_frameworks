package filter;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.specification.MultiPartSpecification;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RestAssuredFilter implements Filter {

    private final Logger log = LogManager.getLogger(RestAssuredFilter.class);
    Integer[] failedStatusCode;
    private static final String LINE = "-------------------------------";


    public RestAssuredFilter(Integer... failedStatusCode) {
        this.failedStatusCode = failedStatusCode;
    }

    /**
     * Bu method da Rest-Assured filter method'unu override ediyoruz ve Rest-Assured
     * Logları'nı log4j2 ile log dosyamıza yazıyoruz.
     *
     * @param reqSpec       Rest-Assured request detayları.
     * @param resSpec       Rest-Assured response detayları.
     * @param filterContext Rest- Assured filter context.
     * @return Rest-Assured response
     */
    @Override
    public Response filter(FilterableRequestSpecification reqSpec,
                           FilterableResponseSpecification resSpec,
                           FilterContext filterContext) {

        Response response = filterContext.next(reqSpec, resSpec);
        int statusCode = response.statusCode();

        if (Arrays.stream(failedStatusCode).anyMatch(i -> i == statusCode)) {
            logErrorStatus(response, reqSpec);
        } else if (Arrays.stream(failedStatusCode).anyMatch(i -> i != statusCode)
                || failedStatusCode.length == 0) {
            logInfoStatus(response, reqSpec);
        }
        return response;
    }

    private String getFileAsString(File file) {
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return "null";
        }
    }

    private String getRequestBody(FilterableRequestSpecification reqSpec) {

        Object reqBody;
        String bodyType;
        try {
            bodyType = reqSpec.getBody().getClass().getName();
        } catch (NullPointerException e) {
            bodyType = null;
        }

        if (bodyType != null && bodyType.equalsIgnoreCase("java.io.File")) {
            reqBody = getFileAsString(reqSpec.getBody());
        } else {
            reqBody = reqSpec.getBody();
        }

        return String.valueOf(reqBody);
    }

    private void logErrorStatus(Response response, FilterableRequestSpecification reqSpec) {
        Utils utils = new Utils();
        log.error(LINE);
        log.error("Methods:");
        log.error(reqSpec.getMethod());

        log.error(LINE);
        log.error("URI:");
        log.error(reqSpec.getURI());

        log.error(LINE);
        log.error("Request Headers:");
        log.error(reqSpec.getHeaders());

        if (!reqSpec.getQueryParams().isEmpty()) {
            log.error(LINE);
            log.error("Request Query Parameters:");
            logMap(reqSpec.getQueryParams(), false);
        }
        if (reqSpec.getFormParams().size() > 0) {
            log.error(LINE);
            log.error("Request Form Parameters:");
            logMap(reqSpec.getFormParams(), false);
        }
        String prettyJson = utils.prettyPrint(getRequestBody(reqSpec));
        if (getRequestBody(reqSpec) != null && getRequestBody(reqSpec).length() > 0) {
            log.error(LINE);
            log.error("Request Body");
            log.error(prettyJson);
        }


        log.error(LINE);
        log.error("Response Status Code");
        log.error(response.statusCode());

        log.error(LINE);
        log.error("Response Headers:");
        log.error(response.getHeaders());

        if (!reqSpec.getMultiPartParams().isEmpty()) {
            log.error(LINE);
            log.error("Multi-form data:");
            logMapMultiPartSpecification(reqSpec.getMultiPartParams(), false);
        }

        log.error(LINE);
        log.error(utils.prettyPrint(response.getBody().asString()));

    }


    private void logInfoStatus(Response response, FilterableRequestSpecification reqSpec) {

        Utils utils = new Utils();

        log.info(LINE);
        log.info("Methods:");
        log.info(reqSpec.getMethod());

        log.info(LINE);
        log.info("URI:");
        log.info(reqSpec.getURI());

        log.info(LINE);
        log.info("Request Headers:");
        log.info(reqSpec.getHeaders());

        if (!reqSpec.getQueryParams().isEmpty()) {
            log.info(LINE);
            log.info("Request Query Parameters:");
            logMap(reqSpec.getQueryParams(), true);
        }

        if (!reqSpec.getFormParams().isEmpty()) {
            log.info(LINE);
            log.info("Request Form Parameters:");
            logMap(reqSpec.getFormParams(), true);
        }
        String prettyJson = utils.prettyPrint(getRequestBody(reqSpec));
        if (getRequestBody(reqSpec) != null && !getRequestBody(reqSpec).equalsIgnoreCase("null")) {
            log.info(LINE);
            log.info("Request Body");
            log.info(prettyJson);
        }


        log.info(LINE);
        log.info("Response Status Code");
        log.info(response.statusCode());

        log.info(LINE);
        log.info("Response Headers:");
        log.info(response.getHeaders());

        if (!reqSpec.getMultiPartParams().isEmpty()) {
            log.info(LINE);
            log.info("Multi-form data:");
            logMapMultiPartSpecification(reqSpec.getMultiPartParams(), true);
        }

        log.info(LINE);
        log.info("Response Body:");
        log.info(utils.prettyPrint(response.getBody().asString()));
    }

    private void logMapMultiPartSpecification(List<MultiPartSpecification> reqSpec, boolean isInfo) {
        for (Object params : reqSpec) {
            if (isInfo)
                log.info(params);
            else
                log.error(params);
        }
    }

    private void logMap(Map<String, String> map, boolean isInfo) {
        map.forEach((key, value) -> {
            if (isInfo)
                log.info("{}:{}", key, value);
            else
                log.error("{}:{}", key, value);
        });
    }
}
