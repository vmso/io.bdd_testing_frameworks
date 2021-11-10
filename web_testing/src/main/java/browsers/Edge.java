package browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.HashMap;

public class Edge implements BrowserSelectable {

    private EdgeDriverService EdgeDriverService;
    @Override
    public MutableCapabilities getCapabilities() {
        EdgeOptions options = new EdgeOptions();
        var prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        return options;
    }

    @Override
    public WebDriver getBrowser()  {
        WebDriverManager.edgedriver().setup();
        EdgeDriverService= new EdgeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        try {
            EdgeDriverService.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new RemoteWebDriver(EdgeDriverService.getUrl(),getCapabilities());
    }
}

