package Mobile.os;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.Map;

public interface MobileSystemSelectable {

    public AppiumDriver getLocalDriver();

    public AppiumDriver getRemoteDriver(String remoteIp, String port);

    public DesiredCapabilities getCapabilities();

    public void setCapabilities(String capabilitiesFile, String capabilitiesName);
}
