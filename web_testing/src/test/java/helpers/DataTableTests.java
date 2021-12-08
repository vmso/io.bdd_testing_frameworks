package helpers;

import base.BaseBrowser;
import base.GetFileName;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTableTests {
    private RemoteWebDriver driver;

    @BeforeEach
    public void setDriver() {
        System.setProperty("browser", "Chrome");
        var base = new BaseBrowser();
        base.setUp();
        driver = DriverManager.getInstances().getDriver();
        GetFileName.getInstance().setFileName("examples");
    }

    @Test
    public void dataTableTest() {
        driver.get("https://courses.letskodeit.com/practice");
        var tableHelper = new DataTableHelper();
        var table1 = tableHelper.getTableAsMapList(By.cssSelector("td"), "Author", "Course", "Price");
        var table2 = tableHelper.getTableAsMapList(By.cssSelector("td"), By.cssSelector("th"));
        var table3 = tableHelper.getTableAsMapList("rowLocator", "Author", "Course", "Price");
        var table4 = tableHelper.getTableAsMapList("rowLocator", "headerLocator");

        assertEquals(table1, table2);
        assertEquals(table2, table3);
        assertEquals(table3, table4);
    }


    @Test
    public void findMax() {

        driver.get("http://demo.guru99.com/test/web-table-element.php");

        var cellSize = driver.findElements(By.cssSelector("td")).size();
        var prevCloses = new ArrayList<String>();
        for (int i = 3; i <= cellSize; i += 5) {
            var prevClose = driver.findElement(By.xpath("(//td)[" + i + "]")).getText();
            prevCloses.add(prevClose);
        }

        var currentPrices = new ArrayList<String>();
        for (int i = 4; i <= cellSize; i += 5) {
            var currentPrice = driver.findElement(By.xpath("(//td)[" + i + "]")).getText();
            currentPrices.add(currentPrice);
        }

        var changes = new ArrayList<String>();
        for (int i = 5; i <= cellSize; i += 5) {
            var change = driver.findElement(By.xpath("(//td)[" + i + "]")).getText();
            change = change.replaceAll("^[a-zA-Z:+-]", "").strip();
            changes.add(change);
        }

        double prevMax = findMaxDouble(prevCloses);
        double currMax = findMaxDouble(currentPrices);
        double changeMax = findMaxDouble(changes);

        double prevMin = findMinDouble(prevCloses);
        double currMin = findMinDouble(currentPrices);
        double changeMin = findMinDouble(changes);

        System.out.printf("Prev max is %,.2f%n", prevMax);
        System.out.printf("Current max is %,.2f%n", currMax);
        System.out.printf("Change max is %%%,.1f percent%n", changeMax);

        System.out.printf("Prev min is %,.2f%n", prevMin);
        System.out.printf("Current min is %,.2f%n", currMin);
        System.out.printf("Change min is %%%,.1f percent%n", changeMin);
    }

    private double findMaxDouble(ArrayList<String> list) {
        double max = 0;
        for (var priceText : list) {
            var price = Double.parseDouble(priceText);
            if (price > max)
                max = price;
        }
        return max;
    }

    private double findMinDouble(ArrayList<String> list) {
        double min = 99999999;
        for (var priceText : list) {
            var price = Double.parseDouble(priceText);
            if (price < min)
                min = price;
        }
        return min;
    }


    @AfterEach
    public void tearDown() {
        DriverManager.getInstances().quitDriver();
    }
}
