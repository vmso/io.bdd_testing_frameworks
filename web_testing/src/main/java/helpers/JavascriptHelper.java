package helpers;

import driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;

public class JavascriptHelper {

    public void executeJavascript(String script) {
        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript(script);
    }

    public void executeJavascript(String script, Object... objects) {
        ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript(script, objects);
    }

    public Object getJavascriptResult(String script, Object... objects) {
        return ((JavascriptExecutor) DriverManager.getInstance().getDriver()).executeScript(script, objects);
    }
}