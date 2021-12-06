package helpers;

import base.BaseBrowser;
import base.GetFileName;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

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
        assertEquals(table2,table3);
        assertEquals(table3,table4);
    }

    @AfterEach
    public void tearDown() {
        DriverManager.getInstances().quitDriver();
    }
}
