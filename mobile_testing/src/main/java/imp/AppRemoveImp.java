package imp;

import com.thoughtworks.gauge.Step;
import helper.AppRemoveHelper;
import helper.AppHelper;
import io.cucumber.java.en.Given;

public class AppRemoveImp extends AppRemoveHelper {
    private final AppHelper appHelper = new AppHelper();

    @Given("Remove {string} app")
    @Step("Remove <appName> app")
    public void removeApp(String appName) {
        removeApp(appName); // Only removal is supported in AppRemoveHelper
    }

    // The following methods are obsolete and require new implementation for install/launch logic
    // If you need to install or launch, use AppHelper directly as shown below:
    //
    // public void installAppFromResource(String appPath) {
    //     appHelper.installApp(appPath);
    // }
    //
    // public void checkIfExistsRemoveAndInstall(String appName, String filePath) {
    //     removeApp(appName);
    //     appHelper.installApp(filePath);
    //     appHelper.activateApp(appName); // or appHelper.terminateApp/appHelper.activateApp as needed
    // }
}
