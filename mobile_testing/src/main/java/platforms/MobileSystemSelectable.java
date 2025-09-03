package platforms;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

public interface MobileSystemSelectable {

    AppiumDriver getLocalDriver();

    AppiumDriver getRemoteDriver();

    DesiredCapabilities getCapabilities();

    void setCapabilities(String capabilitiesFile, String capabilitiesName);

    void stopTheServices();
}
