package screenshot;

import com.thoughtworks.gauge.screenshot.CustomScreenshotWriter;
import helper.RandomHelper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import platform.manager.PlatformManager;

import java.io.File;
import java.io.IOException;


public class CustomGaugeScreenShot implements CustomScreenshotWriter {

    @Override
    public String takeScreenshot() {

        var driver = PlatformManager.getInstances().getDriver();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = new RandomHelper().hexCodeOfRandomness(32) + ".png";
        var gaugeScreenShotDir = System.getenv("gauge_reports_dir") + "/html-report/images/";
        File destFile = new File(gaugeScreenShotDir + fileName);
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        var name = destFile.getName().split("/");
        return name[name.length - 1];
    }


}
