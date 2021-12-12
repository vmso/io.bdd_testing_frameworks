package helpers;

import base.BaseBrowser;
import base.GetFileName;
import driver.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.jupiter.api.Assertions.*;

class GetAttributeHelperTest {
    private RemoteWebDriver driver;
    private GetAttributeHelper getAttributeHelper;

    @BeforeEach
    void setUp() {
        System.setProperty("browser", "Chrome");
        var base = new BaseBrowser();
        base.setUp();
        driver = DriverManager.getInstances().getDriver();
        GetFileName.getInstance().setFileName("examples");
        driver.get("https://courses.letskodeit.com/practice");
        getAttributeHelper = new GetAttributeHelper();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void getTexWithWait() {
    }

    @Test
    void testGetTexWithWait() {
    }

    @Test
    void testGetTexWithWait1() {
    }

    @Test
    void testGetTexWithWait2() {
    }

    @Test
    void testGetTexWithWait3() {
    }

    @Test
    void testGetTexWithWait4() {
    }

    @Test
    void getTexWithoutWait() {
    }

    @Test
    void getAttributeWithoutWait() {
    }

    @Test
    void getAttributeWithDefaultWait() {
    }

    @Test
    void testGetAttributeWithoutWait() {
    }

    @Test
    void testGetAttributeWithDefaultWait() {
    }

    @Test
    void getCssValueWithoutWait() {
    }

    @Test
    void testGetCssValueWithoutWait() {
    }

    @Test
    void getElementSize() {
    }

    @Test
    void testGetElementSize() {
    }

    @Test
    void getInnerTextOfElement() {
        var text = getAttributeHelper.getInnerTextOfElement(By.cssSelector("[data-uniqid='1621702280245']"));
        assertEquals("Practice Page", text);
    }
}