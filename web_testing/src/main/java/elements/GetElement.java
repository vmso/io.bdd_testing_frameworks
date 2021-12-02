package elements;

import driver.DriverManager;
import exceptions.FileNotFound;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GetElement extends GetBy {


    protected WebElement getElement(String jsonKey) {
        var by = getByValue(jsonKey);
        return DriverManager.getInstances().getDriver().findElement(by);
    }

    protected WebElement getElement(By by) {
        return DriverManager.getInstances().getDriver().findElement(by);
    }

    protected List<WebElement> getElements(String jsonKey) {
        var by = getByValue(jsonKey);
        return DriverManager.getInstances().getDriver().findElements(by);
    }

    protected List<WebElement> getElements(By by) {
        return DriverManager.getInstances().getDriver().findElements(by);
    }
}
