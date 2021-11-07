package screenshot;

import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;
import helper.RandomHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import platform.manager.PlatformManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;


public class CustomGaugeScreenShot implements CustomScreenshotWriter {

    @Override
    public String takeScreenshot() {
        var driver = ((TakesScreenshot) PlatformManager.getInstances().getDriver());
        var screenshotFileName = String.format("screenshot-%s.png", UUID.randomUUID());
        try {
            Files.write(Path.of(System.getenv("gauge_screenshots_dir"), screenshotFileName),
                    driver.getScreenshotAs(OutputType.BYTES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenshotFileName;
    }


}
