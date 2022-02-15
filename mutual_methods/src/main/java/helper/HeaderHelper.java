package helper;

import exceptions.RequestNotDefined;
import io.restassured.config.RestAssuredConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static io.restassured.config.MultiPartConfig.multiPartConfig;

public class HeaderHelper {

    private final Logger log = LogManager.getLogger(HeaderHelper.class);

    /**
     * defineNewRequest() it add bulk header to request
     *
     * @param headers it's headers as map object
     */
    protected void addHeaders(Map<String, Object> headers) {
        ApiHelper.getInstance().getRequestSpecification().headers(headers);
        log.info("{} headers added", headers);
    }

    /**
     * defineNewRequest() it add to the request a header.
     *
     * @param key   it is the header key.
     * @param value it is the header value
     */
    protected void addHeader(String key, String value) {
        ApiHelper.getInstance().getRequestSpecification().header(key, value);
        log.info("{}, {} header added", key, value);
    }


    /**
     * it is add to the request SOAPAction as header.
     *
     * @param soapAction it is the SOAPAction
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addSOAPAction(String soapAction) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().header("SOAPAction", soapAction);
    }

    /**
     * it is add to the request bearer token as header
     *
     * @param accessToken it is the token.
     */
    protected void addBearerTokenToHeader(String accessToken) {
        addHeader("Authorization", "Bearer " + accessToken);
    }

    protected void addMultiPartContentType(String defaultBoundary) {
        ApiHelper.getInstance().getRequestSpecification().contentType("multipart/form-data")
                .config(RestAssuredConfig
                        .config()
                        .multiPartConfig(multiPartConfig()
                        .defaultFileName(null).defaultBoundary(defaultBoundary)))
                .contentType("multipart/form-data;");
    }
}
