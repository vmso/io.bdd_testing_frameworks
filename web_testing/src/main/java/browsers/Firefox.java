package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.HashMap;

public class Firefox implements BrowserSelectable {

    private GeckoDriverService geckoDriverService;

    @Override
    public MutableCapabilities getCapabilities() {
        FirefoxOptions options = new FirefoxOptions();
        var prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.addArguments("--kiosk");
        options.addArguments("--disable-notifications");
        options.addArguments("--start-fullscreen");
        return options;
    }
//burda neden buildli startli kullandik ??? porttan calistirmak istedigimiz icin mi
    @Override
    public WebDriver getBrowser() {
        WebDriverManager.firefoxdriver().setup();
        geckoDriverService= new GeckoDriverService.Builder()
                .usingAnyFreePort()
                .build();
        try {
            geckoDriverService.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(geckoDriverService.getUrl(),getCapabilities());
    }
}
