package imp;

import com.thoughtworks.gauge.Step;
import helpers.JavascriptHelper;
import io.cucumber.java.en.And;

public class JavaScriptImp extends JavascriptHelper {

    @Step("Execute javascript <script> on browser")
    @And("Execute javascript <script> on browser")
    public void executeJavaScriptScript(String script) {
        executeJavascript(script);
    }
}
