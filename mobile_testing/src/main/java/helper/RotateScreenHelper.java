package helper;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.ScreenOrientation;
import platform.manager.PlatformManager;

import static org.openqa.selenium.ScreenOrientation.LANDSCAPE;
import static org.openqa.selenium.ScreenOrientation.PORTRAIT;

// todo write for this helper implementation methods
public class RotateScreenHelper {
    MobileDriver driver;

    public RotateScreenHelper() {
        driver = PlatformManager.getInstances().getDriver();
    }

    public void rotateScreen(String orientation) {
        ScreenOrientation rotate = ScreenOrientation.valueOf(orientation);
        switch (rotate) {
            case LANDSCAPE -> driver.rotate(LANDSCAPE);
            case PORTRAIT -> driver.rotate(PORTRAIT);
            default -> throw new IllegalStateException("""
                    "Unexpected value:""" + rotate + """
                     Expected values are LANDSCAPE and PORTRAIT"
                    """);
        }
    }
}
