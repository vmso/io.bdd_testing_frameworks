package elements;

import base.BaseBrowser;
import driver.DriverManager;
import helpers.DataTableHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataTableTests {
    private WebDriver driver;

    @BeforeEach
    public void setDriver() {
        System.setProperty("browser", "Chrome");
        var base = new BaseBrowser();
        base.setUp();
        driver = DriverManager.getInstances().getDriver();
    }

    @Test
    public void dataTableTest() {
        driver.get("https://courses.letskodeit.com/practice");

        var tableHelper = new DataTableHelper();

        var table1 = tableHelper.getTableAsMapList(By.cssSelector("td"), "Author", "Course", "Price");
        var table2 = tableHelper.getTableAsMapList(By.cssSelector("td"), By.cssSelector("th"));
        assertEquals(table1, table2);
    }

    @AfterEach
    public void tearDown() {
        DriverManager.getInstances().quitDriver();
    }
}
