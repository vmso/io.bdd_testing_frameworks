package platforms;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public interface MobileSystemSelectable {

    AppiumDriver<MobileElement> getLocalDriver();

    AppiumDriver<MobileElement> getRemoteDriver();

    DesiredCapabilities getCapabilities();

    void setCapabilities(String capabilitiesFile, String capabilitiesName);

    void stopTheServices();
}
