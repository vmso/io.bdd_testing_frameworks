package platforms;

import base.ServiceBase;
import configuration.Configuration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import json.JsonReader;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class IOS implements MobileSystemSelectable {
    private DesiredCapabilities capabilities;
    private final AppiumDriverLocalService service;

    public IOS() {
        this.capabilities = new DesiredCapabilities();
        ServiceBase.getInstances().startService();
        service = ServiceBase.getInstances().getService();
    }

    @Override
    public AppiumDriver getLocalDriver() {
        return new IOSDriver(service.getUrl(), capabilities);
    }

    @Override
    public AppiumDriver getRemoteDriver() {
        var ip = Configuration.getInstance().getStringValueOfProp("grid_ip");
        var gridPort = Configuration.getInstance().getStringValueOfProp("grid_port");
        try {
            return new IOSDriver(new URL(String.format("http://%s:%s/wd/hub", ip, gridPort)), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
