package Mobile.os;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Android implements MobileSystemSelectable {
    private final Logger log = LogManager.getLogger(Android.class);
    private DesiredCapabilities capabilities;

    @Override
    public AppiumDriver getLocalDriver() {
        try {
            return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            log.fatal("Appium url hatalı");
            return null;
        }
    }

    @Override
    public AppiumDriver getRemoteDriver(String remoteIp, String port) {
        try {
            return new AndroidDriver(new URL(String.format("http://%s:%s/wd/hub", remoteIp, port)), capabilities);
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
    public void setCapabilities(String capabilitiesFile, String capabilitiesName) {
        this.capabilities = new DesiredCapabilities();
        JsonReader jsonReader = new JsonReader();
        Map<String, Object> capabilities = jsonReader.getJsonAsMap(capabilitiesFile, capabilitiesName);
        capabilities = (Map<String, Object>) capabilities.get("capabilities");
        DesiredCapabilities cap = new DesiredCapabilities();
        capabilities
                .entrySet()
                .stream()
                .forEach(c -> {
                    if (c.getKey().equalsIgnoreCase("app")) {
                        cap.setCapability(MobileCapabilityType.APP, new File(String.valueOf(c.getValue())));
                    } else
                        cap.setCapability(c.getKey(), c.getValue());
                });
        this.capabilities = cap;
    }
}
