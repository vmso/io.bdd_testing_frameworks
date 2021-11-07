package helper;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.JavascriptExecutor;
import platform.manager.PlatformManager;

public class JavascriptHelper {
    private MobileDriver driver;

    protected JavascriptHelper() {
        driver = PlatformManager.getInstances().getDriver();
    }

    public void executeJavascript(String script, Object... objects) {
        if (objects.length > 0)
            ((JavascriptExecutor) driver).executeScript(script, objects[0]);
        else
            ((JavascriptExecutor) driver).executeScript(script);
    }
}
