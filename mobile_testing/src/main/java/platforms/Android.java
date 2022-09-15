package platforms;

import base.ServiceBase;
import configuration.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;

import json.JsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.Map;

public class Android implements MobileSystemSelectable {
    private DesiredCapabilities capabilities;
    private final Logger log = LogManager.getLogger(Android.class);
    private static AppiumDriverLocalService service;

    public Android() {
        this.capabilities = new DesiredCapabilities();
        ServiceBase.getInstances().startService();
        service = ServiceBase.getInstances().getService();
    }

    @Override
    public AppiumDriver<MobileElement> getLocalDriver() {
        return new AndroidDriver<>(service.getUrl(), capabilities);
    }

    @Override
    public AppiumDriver<MobileElement> getRemoteDriver() {
        var ip = Configuration.getInstance().getStringValueOfProp("grid_ip");
        var gridPort = Configuration.getInstance().getStringValueOfProp("grid_port");
        try {
            return new AndroidDriver<>(new URL(String.format("http://%s:%s/wd/hub", ip, gridPort)), capabilities);
        } catch (MalformedURLException e) {
            log.fatal(e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    @Override
    public DesiredCapabilities getCapabilities() {
        return this.capabilities;

}
    @Override
    public void setCapabilities(String capabilitiesFile, String capabilitiesName) {
        JsonReader jsonReader = new JsonReader();
        Map<String, Object> capabilities = jsonReader.getJsonAsMap(capabilitiesFile, capabilitiesName);
        DesiredCapabilities cap = new DesiredCapabilities();
        capabilities
                .forEach(cap::setCapability);
        this.capabilities = cap;
    }

    @Override
    public void stopTheServices() {
        ServiceBase.getInstances().stopTheServices();
    }

}
