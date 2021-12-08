package helpers;

import base.BaseBrowser;
import base.GetFileName;
import driver.DriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SwitchHelperTest {
    private RemoteWebDriver driver;
    private SwitchHelper switchHelper;

    @BeforeAll
    void setUp() {
        System.setProperty("browser", "Chrome");
        var base = new BaseBrowser();
        base.setUp();
        driver = DriverManager.getInstances().getDriver();
        GetFileName.getInstance().setFileName("examples");
        driver.get("https://courses.letskodeit.com/practice");
        switchHelper = new SwitchHelper();
    }

    @BeforeEach
    void setBrowser() {
        DriverManager.getInstances().getDriver().get("https://courses.letskodeit.com/practice");
    }

    @AfterAll
    void tearDown() {
        driver.quit();
    }

    @Test
    void switchToWindowTest() {
        new ClickHelper().clickElement(By.id("openwindow"));
        switchHelper.switchToWindow();
        assertEquals("https://courses.letskodeit.com/courses", driver.getCurrentUrl());
        driver.close();
        switchHelper.switchToDefaultWindow();
    }

    @Test
    void switchToNewWindowTest() {
        var windowHandlesSize = driver.getWindowHandles().size();
        switchHelper.switchToNewWindow();
        var newWindowHandlesSize = driver.getWindowHandles().size();
        assertEquals(windowHandlesSize + 1, newWindowHandlesSize);
        assertEquals("about:blank", driver.getCurrentUrl());
        driver.close();
        switchHelper.switchToDefaultWindow();
    }

    @Test
    void switchToNewTabTest() {
        var windowHandlesSize = driver.getWindowHandles().size();
        switchHelper.switchToNewTab();
        var newWindowHandlesSize = driver.getWindowHandles().size();
        assertEquals(windowHandlesSize + 1, newWindowHandlesSize);
        assertEquals("about:blank", driver.getCurrentUrl());
        driver.close();
        switchHelper.switchToDefaultWindow();
    }

    @Test
    void switchToDefaultWindowTest() {
        new ClickHelper().clickElement(By.id("openwindow"));
        switchHelper.switchToWindow();
        assertEquals("https://courses.letskodeit.com/courses", driver.getCurrentUrl());
        driver.close();
        switchHelper.switchToDefaultWindow();
        assertNotEquals("https://courses.letskodeit.com/courses", driver.getCurrentUrl());
        assertEquals("https://courses.letskodeit.com/practice", driver.getCurrentUrl());
    }

    @Test
    void switchToIframeByIndexTest() {
        switchHelper.switchToIframe(0);
        var isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertTrue(isPresent);
    }

    @Test
    void switchToIframeByElementTest() {
        switchHelper.switchToIframe(By.id("courses-iframe"));
        var isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertTrue(isPresent);
    }

    @Test
    void switchToIframeByNameTest() {
        switchHelper.switchToIframe("iframe-name");
        var isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertTrue(isPresent);
    }

    @Test
    void switchToDefaultContentTest() {
        switchHelper.switchToIframe("iframe-name");
        var isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertTrue(isPresent);
        switchHelper.switchToDefaultContent();
        isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertFalse(isPresent);
        isPresent = new PresenceHelper().isPresence(By.id("mousehover"), 5);
        assertTrue(isPresent);
    }

    @Test
    void switchToParentContentTest() {
        switchHelper.switchToIframe("iframe-name");
        var isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertTrue(isPresent);
        switchHelper.switchToParentIframe();
        isPresent = new PresenceHelper().isPresence(By.name("categories"), 5);
        assertFalse(isPresent);
        isPresent = new PresenceHelper().isPresence(By.id("mousehover"), 5);
        assertTrue(isPresent);
    }

    @Test
    void switchToAlertAndDismissTest() {
        new ClickHelper().clickElement(By.id("alertbtn"));
        var alert = driver.switchTo().alert();
        assertNotNull(alert);
        switchHelper.switchToAlertAndDismiss();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    void switchToAlertAndAcceptTest() {
        new ClickHelper().clickElement(By.id("confirmbtn"));
        var alert = driver.switchTo().alert();
        assertNotNull(alert);
        switchHelper.switchToAlertAndAccept();
        assertThrows(NoAlertPresentException.class, () -> driver.switchTo().alert());
    }

    @Test
    void switchToAlertAndGetTextTest() {
        new ClickHelper().clickElement(By.id("alertbtn"));
        var text = switchHelper.switchToAlertAndGetText();
        assertEquals("Hello , share this practice page and share your knowledge", text);
        switchHelper.switchToAlertAndAccept();
    }


    @Test
    void sendTabKeyToActiveElementTest() {
        new SendKeysHelper().sendKeys(By.id("name"), "Admin");
        var idOfElementBefore = driver.switchTo().activeElement().getAttribute("id");
        switchHelper.sendTabKeyToActiveElement();
        var idOfElementAfter = driver.switchTo().activeElement().getAttribute("id");
        assertNotEquals(idOfElementAfter, idOfElementBefore);
    }

    @Test
    void sendKeysToActiveElementTest() {
        new ClickHelper().clickElement(By.id("show-textbox"));
        switchHelper.sendTabKeyToActiveElement();
        switchHelper.sendKeysToActiveElement("Test");
        var activeElm = driver.switchTo().activeElement();
        var javascriptHelper = new JavascriptHelper();
        var script = "arguments[0].value";
        var value = String.valueOf(javascriptHelper.getJavascriptResult(script, activeElm));
        assertNotEquals("Test", value);
    }

    @Test
    void clickOnActiveElementTest() {
    }

    @Test
    void clearActiveElementTest() {
    }

    @Test
    void getTextOfActiveElementTest() {
    }

    @Test
    void switchToActiveElmAndSendEnterKeyTest() {
    }

    @Test
    void getAttributeOfActiveElementTest() {
    }

    @Test
    void getCssValueOfActiveElementTest() {
    }
}