package platforms;

import exceptions.FileNotFound;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Android implements MobileSystemSelectable {
    private final Logger log = LogManager.getLogger(Android.class);
    private DesiredCapabilities capabilities;

    @Override
    public AppiumDriver<MobileElement> getLocalDriver() {
        try {
            return new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            log.fatal("Appium url hatalı");
            return null;
        }
    }

    @Override
    public AppiumDriver<MobileElement> getRemoteDriver(String remoteIp, String port) {
        try {
            return new AndroidDriver<>(new URL(String.format("http://%s:%s/wd/hub", remoteIp, port)), capabilities);
        } catch (MalformedURLException e) {
            log.fatal(e.getMessage());
            return null;
        }
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;
    }

    @Override
    public void setCapabilities(String capabilitiesFile, String capabilitiesName) throws FileNotFound {
        this.capabilities = new DesiredCapabilities();
        JsonReader jsonReader = new JsonReader();
        Map<String, Object> capabilities = jsonReader.getJsonAsMap(capabilitiesFile, capabilitiesName);
        DesiredCapabilities cap = new DesiredCapabilities();
        capabilities
                .forEach(cap::setCapability);
        this.capabilities = cap;
    }
}
