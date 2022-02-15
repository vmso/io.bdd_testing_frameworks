package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class FormParamsHelper {

    private final Logger log = LogManager.getLogger(FormParamsHelper.class);
    private static final String LOG_INFO = "{} = {} added to request as form param";

    /**
     * Add form param to request
     *
     * @param key   is param key as string.
     * @param value is param value as string.
     */
    protected void addFormParam(String key, String value) {
        ApiHelper.getInstance().getRequestSpecification().formParam(key, value);
        log.info(LOG_INFO, key, value);
    }

    /**
     * Add bulk form param to request
     *
     * @param qParam bulk params as map object
     */
    protected void addFormParams(Map<String, Object> qParam){
        ApiHelper.getInstance().getRequestSpecification().formParams(qParam);
        log.info("{} added to request as form param", qParam);
    }



    /**
     * Add form param to request
     *
     * @param key   is param key as string.
     * @param value is param value as object.
     */
    protected void addFormParam(String key, Object value){
        ApiHelper.getInstance().getRequestSpecification().formParam(key, value);
        var stringValue = String.valueOf(value);
        log.info(LOG_INFO, key, stringValue);
    }

    /**
     * Add bulk form param to request
     *
     * @param key   is param key as string.
     * @param value is param value as object.
     */
    protected void addFormParams(String key, Object value) {
        ApiHelper.getInstance().getRequestSpecification().formParams(key, value);
        var stringValue = String.valueOf(value);
        log.info(LOG_INFO, key, stringValue);
    }
}
