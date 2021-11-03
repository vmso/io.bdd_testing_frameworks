package platform.manager;

import exceptions.FileNotFound;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.NoSuchSessionException;
import platforms.Android;
import platforms.IOS;
import platforms.MobileSystemSelectable;
import exceptions.UndefinedAppType;
import io.appium.java_client.AppiumDriver;

import java.util.Locale;

import enums.AppType;

public class PlatformManager {
    private static PlatformManager instance;
    private AppiumDriver<MobileElement> driver;

    private PlatformManager() {

    }

    public static PlatformManager getInstances() {
        if (instance == null) {
            instance = new PlatformManager();
        }
        return instance;
    }

    public void createLocalMobileDriver(String capabilitiesFile, String capabilitiesName) throws UndefinedAppType, FileNotFound {
        String platform = System.getProperty("Platform");
        AppType appType = AppType.valueOf(platform.toUpperCase(Locale.ENGLISH));
        MobileSystemSelectable mobileSystemSelectable;
        switch (appType) {
            case IOS -> {
                mobileSystemSelectable = new IOS();
                mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesName);
                driver = mobileSystemSelectable.getLocalDriver();
            }
            case ANDROID -> {
                mobileSystemSelectable = new Android();
                mobileSystemSelectable.setCapabilities(capabilitiesFile, capabilitiesName);
                driver = mobileSystemSelectable.getLocalDriver();
            }
            default -> throw new UndefinedAppType(appType);
        }
    }

    public void quitDriver(){
        try {
            if (PlatformManager.getInstances().getDriver() != null) {
                PlatformManager.getInstances().getDriver().closeApp();
                PlatformManager.getInstances().getDriver().quit();
            }
        }catch (NoSuchSessionException e){
            e.printStackTrace();
        }
    }
    public AppiumDriver getDriver() {
        return driver;
    }

    public void setDriver(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }
}
