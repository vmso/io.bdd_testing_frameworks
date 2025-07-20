package helpers;

import driver.DriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WindowNavigationHelper {
    public void windowBack() {
        DriverManager.getInstance().getDriver().navigate().back();
    }

    public void windowForward() {
        DriverManager.getInstance().getDriver().navigate().forward();
    }

    public void windowRefresh() {
        DriverManager.getInstance().getDriver().navigate().refresh();
    }
}
