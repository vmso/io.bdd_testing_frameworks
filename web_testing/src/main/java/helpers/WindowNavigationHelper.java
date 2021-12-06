package helpers;

import driver.DriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WindowNavigationHelper {

    private RemoteWebDriver driver;
    public WindowNavigationHelper(){
        driver= DriverManager.getInstances().getDriver();
    }
    public void windowBack(){
        driver.navigate().back();
    }

    public void windowForward(){
        driver.navigate().forward();
    }

    public void windowRefresh(){
        driver.navigate().refresh();
    }
}
