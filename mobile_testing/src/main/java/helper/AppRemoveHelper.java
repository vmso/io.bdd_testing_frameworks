package helper;

import platform.manager.PlatformManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppRemoveHelper {

    private final Logger log = LogManager.getLogger(AppRemoveHelper.class);

    protected boolean checkIsAppInstalled(String appName) {
        return PlatformManager.getInstances().getDriver().isAppInstalled(appName);
    }

    protected void checkIsAppInstalledAndRemove(String appName) {
        if (checkIsAppInstalled(appName))
            PlatformManager.getInstances().getDriver().removeApp(appName);
        log.info("App {} removed", appName);
    }

    protected void installApp(String appPath) {
        PlatformManager.getInstances().getDriver().installApp(appPath);
        log.info("App installed from {}",appPath);

    }

}
