package imp;

import com.thoughtworks.gauge.Step;
import helper.AppRemoveHelper;
import io.cucumber.java.en.Given;

public class AppRemoveImp extends AppRemoveHelper {

    @Given("Remove {string} app")
    @Step("Remove <appName> app")
    public void removeApp(String appName) {
        checkIsAppInstalledAndRemove(appName);
    }

    @Given("Install {string} app")
    @Step("Install <string> app")
    public void installAppFromRecourse(String appName) {
        installApp(appName);
    }

    @Given("Check if {string} exists, remove it if it does and then reinstall from {string}")
    @Step("Check if <app name> exists, remove it if it does and then reinstall from <filePath>")
    public void checkIfExistsRemoveAndInstall(String appName,String filePAth) {
        checkIsAppInstalledAndRemove(appName);
        installApp(filePAth);
        launchApp();
    }
}
