package helper;

import io.restassured.specification.ProxySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;

public class ProxyHelper {

    private final Logger log = LogManager.getLogger(ProxyHelper.class);
    private static final String LOG_INFO = "{} added as proxy";

    protected void proxy(int proxy) {
        ApiHelper.getInstance().getRequestSpecification().proxy(proxy);
        log.info(LOG_INFO, proxy);
    }

    protected void proxy(String var1, int var2) {
        ApiHelper.getInstance().getRequestSpecification().proxy(var1, var2);
        log.info("{}, {} added as proxy", var1, var2);
    }

    protected void proxy(String var1) {
        ApiHelper.getInstance().getRequestSpecification().proxy(var1);
        log.info(LOG_INFO, var1);
    }

    protected void proxy(String var1, int var2, String var3) {
        ApiHelper.getInstance().getRequestSpecification().proxy(var1, var2, var3);
        log.info("{},{},{} added as proxy", var1, var2, var3);
    }

    protected void proxy(URI var1) {
        ApiHelper.getInstance().getRequestSpecification().proxy(var1);
        log.info(LOG_INFO, var1);
    }

    protected void proxy(ProxySpecification var1) {
        ApiHelper.getInstance().getRequestSpecification().proxy(var1).auth();
    }
}
