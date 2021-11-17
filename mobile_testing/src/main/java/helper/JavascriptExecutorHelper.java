package helper;

import org.openqa.selenium.JavascriptExecutor;
import platform.manager.PlatformManager;

public class JavascriptExecutorHelper {

    protected JavascriptExecutor getJsExecutor() {
        return PlatformManager.getInstances().getDriver();
    }
}
