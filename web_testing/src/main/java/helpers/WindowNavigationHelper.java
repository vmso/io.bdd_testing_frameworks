package helpers;

import driver.DriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WindowNavigationHelper {
    public void windowBack() {
        DriverManager.getInstances().getDriver().navigate().back();
    }

    public void windowForward() {
        DriverManager.getInstances().getDriver().navigate().forward();
    }

    public void windowRefresh() {
        DriverManager.getInstances().getDriver().navigate().refresh();
    }
}
