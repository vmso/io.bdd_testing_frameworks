package helper;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import platform.manager.PlatformManager;

public class TouchHelper extends GetElementHelper {

    protected TouchAction<?> getTouchAction() {
        return new TouchAction<>(PlatformManager.getInstances().getDriver());
    }

    protected void performsMultiAction(TouchAction<?> action1, TouchAction<?> action2) {
        new MultiTouchAction(PlatformManager.getInstances().getDriver())
                .add(action1)
                .add(action2)
                .perform();
    }
}
