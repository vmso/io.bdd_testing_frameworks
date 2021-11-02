package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class QueryParamsHelper {

    private final Logger log = LogManager.getLogger(QueryParamsHelper.class);
    private static final String LOG_INFO = "{} = {} added to request as query param";
    /**
     * Add query param to request
     *
     * @param key   is param key as string.
     * @param value is param value as string.
     */
    protected void addQueryParam(String key, String value) {
        ApiHelper.getInstance().getRequestSpecification().queryParam(key, value);
        log.info(LOG_INFO, key, value);
    }

    /**
     * Add bulk query param to request
     *
     * @param qParam bulk params as map object
     */
    protected void addQueryParams(Map<String, Object> qParam) {
        ApiHelper.getInstance().getRequestSpecification().queryParams(qParam);
        log.info("{} bulk added to request as query params ", qParam);
    }

    /**
     * Add query param to request
     *
     * @param key   is param key as string.
     * @param value is param value as object.
     */
    protected void addQueryParam(String key, Object value) {
        ApiHelper.getInstance().getRequestSpecification().queryParam(key, value);
        var stringValue = String.valueOf(value);
        log.info(LOG_INFO, key, stringValue);
    }

    /**
     * Add bulk query param to request
     *
     * @param key   is param key as string.
     * @param value is param value as object.
     */
    protected void addQueryParams(String key, Object value) {
        ApiHelper.getInstance().getRequestSpecification().queryParams(key, value);
        var stringValue = String.valueOf(value);
        log.info(LOG_INFO, key, stringValue);
    }
}
