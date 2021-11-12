package elements;

import driver.DriverManager;
import exceptions.FileNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetElement extends GetBy {


    protected WebElement getMobileElement(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return DriverManager.getInstances().getDriver().findElement(by);
    }

    protected WebElement getMobileElement(By by) {
        return DriverManager.getInstances().getDriver().findElement(by);
    }

    protected List<WebElement> getMobileElements(String jsonKey) throws FileNotFound {
        var by = getByValue(jsonKey);
        return DriverManager.getInstances().getDriver().findElements(by);
    }

    protected List<WebElement> getMobileElements(By by) {
        return DriverManager.getInstances().getDriver().findElements(by);
    }
}
