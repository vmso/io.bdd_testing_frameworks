package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.HashMap;

public class Chrome implements BrowserSelectable {

    private ChromeDriverService chromeDriverService;

    @Override
    public MutableCapabilities getCapabilities() {
        ChromeOptions options = new ChromeOptions();
        var prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.addArguments("--kiosk");
        options.addArguments("--log-level=3");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        options.addArguments("--disable-logging");
        return options;
    }

    @Override
    public RemoteWebDriver getBrowser()  {
        //kendisi otomatik olarak indiriyor exe olayi icinde oluyor
        WebDriverManager.chromedriver().setup();
        chromeDriverService = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        try {
            chromeDriverService.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(chromeDriverService.getUrl(), getCapabilities());
    }
}
