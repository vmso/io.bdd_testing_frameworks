package driver;

import browsers.*;
import enums.Browsers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.StoreApiInfo;

import java.util.Locale;

public class DriverManager {

    private static DriverManager instances = null;
    private BrowserSelectable browserSelectable;

    private Browsers browsersType;

    private Logger log = LogManager.getLogger(DriverManager.class);

    private DriverManager() {

    }

    public static DriverManager getInstances() {
        if (instances == null) {
            instances = new DriverManager();
        }
        return instances;
    }


    public void createLocalDriver() {
        String browserName = System.getProperty("browser");
        Browsers browserType = Browsers.valueOf(browserName.toUpperCase(Locale.ROOT));
        setBrowsersType(browserType);
        switch (browserType) {
            case OPERA -> {
                browserSelectable = new Opera();
                setDriver(browserSelectable.getBrowser());
                getDriver().manage().window().maximize();
            }
            case EDGE -> {
                browserSelectable = new Edge();
                setDriver(browserSelectable.getBrowser());
            }
            case SAFARI -> {
                browserSelectable = new Safari();
                setDriver(browserSelectable.getBrowser());
                getDriver().manage().window().maximize();
            }
            case FIREFOX -> {
                browserSelectable = new Firefox();
                setDriver(browserSelectable.getBrowser());
            }
            case CHROME -> {
                browserSelectable = new Chrome();
                setDriver(browserSelectable.getBrowser());
            }
            default -> throw new IllegalArgumentException(String.format("%s undefined type of browser", browserType));

        }
    }


    public void quitDriver() {
        try {
            if (getDriver() != null) {
                getDriver().close();
                getDriver().quit();
            }
        } catch (NoSuchSessionException e) {
            log.warn("Driver already closed");
        }
    }

    public RemoteWebDriver getDriver() {
        return ((RemoteWebDriver) StoreApiInfo.get("driver"));
    }

    public void setDriver(RemoteWebDriver driver) {
        StoreApiInfo.put("driver",driver);
    }

    public Browsers getBrowsersType() {
        return browsersType;
    }

    public void setBrowsersType(Browsers browsers) {
        this.browsersType = browsers;
    }

}
