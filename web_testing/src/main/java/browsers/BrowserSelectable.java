package browsers;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;


public interface BrowserSelectable {
    MutableCapabilities getCapabilities();

    WebDriver getBrowser();

//kullanacagimiz tum classlarda ayni seyi yapmamiz lazim bu sebeple interface olusturup ordan tureticez diger classlari
}
