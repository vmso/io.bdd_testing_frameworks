package helper.methods;

import helper.ApiHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HeadHelper {

    private final Logger log = LogManager.getLogger(GetHelper.class);

    /**
     * Create a head request and update ApiHelper class' response object.
     *
     * @param url url to which the request will be sent
     */
    protected void headRequest(String url) {
        var response = ApiHelper.getInstance().getRequestSpecification().head(url)
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Head request sent to {}", url);
    }

    /**
     * Create a head request and update ApiHelper class' response object.
     *
     */
    protected void headRequest()  {
        var response = ApiHelper.getInstance().getRequestSpecification()
                .head()
                .then()
                .extract()
                .response();
        ApiHelper.getInstance().setResponse(response);
        ApiHelper.getInstance().defineNewRequest();
        log.info("Head request sent");
    }
}
