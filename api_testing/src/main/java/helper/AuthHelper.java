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

    protected void oauth2(String var1)  {
        ApiHelper.getInstance().getRequestSpecification().auth().oauth2(var1);
    }

    protected void oauth2(String var1, OAuthSignature var2)  {
        ApiHelper.getInstance().getRequestSpecification().auth().oauth2(var1, var2);
    }

    protected void oauth(String var1, String var2, String var3, String var4)  {
        ApiHelper.getInstance().getRequestSpecification().auth().oauth(var1, var2, var3, var4);
    }

    protected void addBearerToken(String token){
        HeaderHelper headerHelper = new HeaderHelper();
        headerHelper.addBearerTokenToHeader(token);
    }


}
