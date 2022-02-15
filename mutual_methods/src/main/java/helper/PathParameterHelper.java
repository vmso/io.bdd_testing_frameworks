package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class PathParameterHelper {

    private final Logger log = LogManager.getLogger(PathParameterHelper.class);
    private static final String LOG_INFO = "{} = {} added to request as path param";

    /**
     * Add path param to request
     *
     * @param key   is param key as string.
     * @param value is param value as string.
     */
    protected void addPathParam(String key, String value) {
        ApiHelper.getInstance().getRequestSpecification().pathParam(key, value);
        log.info(LOG_INFO, key, value);
    }

    /**
     * Add bulk path param to request
     *
     * @param qParam bulk params as map object
     */
    protected void addPathParams(Map<String, Object> qParam) {
        ApiHelper.getInstance().getRequestSpecification().pathParams(qParam);
        log.info("{} bulk added to request as path params ", qParam);
    }

    /**
     * Add path param to request
     *
     * @param key   is param key as string.
     * @param value is param value as object.
     */
    protected void addPathParam(String key, Object value) {
        ApiHelper.getInstance().getRequestSpecification().pathParam(key, value);
        var stringValue = String.valueOf(value);
        log.info(LOG_INFO, key, stringValue);
    }

    /**
     * Add bulk path param to request
     *
     * @param key   is param key as string.
     * @param value is param value as object.
     */
    protected void addPathParams(String key, Object value) {
        ApiHelper.getInstance().getRequestSpecification().pathParams(key, value);
        var stringValue = String.valueOf(value);
        log.info(LOG_INFO, key, stringValue);
    }
}
