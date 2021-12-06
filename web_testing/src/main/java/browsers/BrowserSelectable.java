package browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


public interface BrowserSelectable {
    MutableCapabilities getCapabilities();

    RemoteWebDriver getBrowser();

//kullanacagimiz tum classlarda ayni seyi yapmamiz lazim bu sebeple interface olusturup ordan tureticez diger classlari
}
