package imp;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.ProxyHelper;
import io.restassured.specification.ProxySpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class ProxyImp extends ProxyHelper {

    private final Logger log = LogManager.getLogger(ProxyImp.class);

    @Step({"Add proxy to request with <int key>", "Proxy ekle, <int key>"})
    public void addProxy(int key) throws RequestNotDefined {
        proxy(key);
    }

    @Step({"Add proxy to request with <string key>", "Proxy ekle, <string key>"})
    public void addProxy(String key) throws RequestNotDefined {
        proxy(key);
    }

    @Step({"Add proxy to request with <string key> and <int key>", "Proxy ekle, <string key> and <int key>"})
    public void addProxy(String key, int key1) throws RequestNotDefined {
        proxy(key, key1);
    }

    @Step({"Add proxy to request with <string key>, <int key> <string key2>",
            "Proxy ekle <string key>, <int key> <string key2>"})
    public void addProxy(String key, int key1, String key2) throws RequestNotDefined {
        proxy(key, key1, key2);
    }

    @Step({"Add proxy to request with <url key>", "Proxy ekle, <url key> "})
    public void addProxyWithUri(String url) throws URISyntaxException, RequestNotDefined {
        try {
            URI myURI = new URI(url);
            proxy(myURI);
        } catch (URISyntaxException e) {
            log.warn("\"{}\" is not an url, it couldn't convert to URI object");
            throw e;
        }
    }

    @Step({"Add proxy url with host: <host>, port: <port>, schema: <schema>, username: <username> and password:<password>",
            "Proxy ekle, host: <host>, port: <port>, schema: <schema>, username: <username> and password:<password>"})
    public void addProxyWithSpecification(String host, int port, String scheme, String username, String password) throws RequestNotDefined {

        ProxySpecification spec = new ProxySpecification(host, port, scheme)
                .withAuth(username, password);
        proxy(spec);
        log.info("Proxy added with host: {}, port: {} and schema: {}, {}, {}",
                host, port, scheme, username, password);
    }

}
