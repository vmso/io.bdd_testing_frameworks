package platforms;

import exceptions.FileNotFound;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public interface MobileSystemSelectable {

    AppiumDriver<MobileElement> getLocalDriver();

    AppiumDriver<MobileElement> getRemoteDriver(String remoteIp, String port);

    DesiredCapabilities getCapabilities();

    void setCapabilities(String capabilitiesFile, String capabilitiesName) throws FileNotFound;
}
