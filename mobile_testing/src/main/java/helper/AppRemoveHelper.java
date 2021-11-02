package helper;

import app.manneger.AppManager;
import imp.AppRemoveImp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppRemoveHelper {

    private final Logger log = LogManager.getLogger(AppRemoveHelper.class);

    protected boolean checkIsAppInstalled(String appName) {
        return AppManager.getInstance().getDriver().isAppInstalled(appName);
    }

    protected void checkIsAppInstalledAndRemove(String appName) {
        if (checkIsAppInstalled(appName))
            AppManager.getInstance().getDriver().removeApp(appName);
        log.info("App {} removed", appName);
    }

    protected void installApp(String appPath) {
        AppManager.getInstance().getDriver().installApp(appPath);
        log.info("App installed from {}",appPath);

    }

}
