package helper;


import exceptions.RequestNotDefined;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class RequestBodyHelper {

    private final Logger log = LogManager.getLogger(RequestBodyHelper.class);
    private static final String LOG_INFO = "Body added to request \n Body detail: {}";

    /**
     * Add payload to request.
     *
     * @param body is request body as object
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(Object body) throws RequestNotDefined {
        Utils utils = new Utils();
        ApiHelper.getInstance().getRequestSpecification().body(body);
        String stringBody = utils.prettyPrint(String.valueOf(body));
        log.info(LOG_INFO, stringBody);
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as Map
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(Map<Object, Object> body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        log.info(LOG_INFO, body);
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as String
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(String body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        log.info(LOG_INFO, body);
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as file
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(File body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        log.info(LOG_INFO, body.getAbsolutePath());
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as InputStream
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(InputStream body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        var utils = new Utils();
        var stringBody = utils.prettyPrint(body.toString());
        log.info(LOG_INFO, stringBody);
    }
}
