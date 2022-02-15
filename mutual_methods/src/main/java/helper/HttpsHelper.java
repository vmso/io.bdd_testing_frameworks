package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpsHelper {
    private final Logger log = LogManager.getLogger(HttpsHelper.class);

    protected void addRelaxedHTTPSValidation(){
        ApiHelper.getInstance().getRequestSpecification().relaxedHTTPSValidation();
        log.info("relaxedHTTPSValidation added");
    }



}
