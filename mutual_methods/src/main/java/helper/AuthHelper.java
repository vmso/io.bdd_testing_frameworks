package helper;


import io.restassured.authentication.OAuthSignature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AuthHelper {

    private final Logger log = LogManager.getLogger(AuthHelper.class);

    protected void basicAuth(String user, String password)  {
        ApiHelper.getInstance().getRequestSpecification().auth().basic(user, password);
        log.info("Basic auth to request as user: {}, password{}", user, password);
    }

    protected void preBasicAuth(String user, String password)  {
        ApiHelper.getInstance().getRequestSpecification().auth().preemptive().basic(user, password);
    }
    protected void addBearerToken(String token){
        HeaderHelper headerHelper = new HeaderHelper();
        headerHelper.addBearerTokenToHeader(token);
    }


}
