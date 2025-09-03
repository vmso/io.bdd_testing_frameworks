package unit.elements;

import elements.ElementFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the ElementFinder interface and WebDriverElementFinder implementation.
 */
class ElementFinderTest {

    private MockWebDriver mockWebDriver;
    private TestableWebDriverElementFinder elementFinder;

    @BeforeEach
    void setUp() {
        mockWebDriver = new MockWebDriver();
        elementFinder = new TestableWebDriverElementFinder(mockWebDriver);
    }

    /**
     * Testable implementation of WebDriverElementFinder that accepts a WebDriver instance.
     */
    private static class TestableWebDriverElementFinder implements ElementFinder {
        private final WebDriver webDriver;
        
        public TestableWebDriverElementFinder(WebDriver webDriver) {
            if (webDriver == null) {
                throw new IllegalArgumentException("WebDriver cannot be null");
            }
            this.webDriver = webDriver;
        }
        
        @Override
        public WebElement findElement(By by) {
            if (by == null) {
                throw new IllegalArgumentException("Locator cannot be null");
            }
            return webDriver.findElement(by);
        }
        
        @Override
        public List<WebElement> findElements(By by) {
            if (by == null) {
                throw new IllegalArgumentException("Locator cannot be null");
            }
            return webDriver.findElements(by);
        }
        
        public WebDriver getWebDriver() {
            return webDriver;
        }
    }

    /**
     * Mock WebDriver implementation for testing.
     */
    private static class MockWebDriver implements WebDriver {
        private WebElement mockElement;
        private List<WebElement> mockElements;
        private RuntimeException exceptionToThrow;
        
        public void setMockElement(WebElement element) {
            this.mockElement = element;
        }
        
        public void setMockElements(List<WebElement> elements) {
            this.mockElements = elements;
        }
        
        public void setExceptionToThrow(RuntimeException exception) {
            this.exceptionToThrow = exception;
        }
        
        @Override
        public WebElement findElement(By by) {
            if (exceptionToThrow != null) {
                throw exceptionToThrow;
            }
            return mockElement;
        }
        
        @Override
        public List<WebElement> findElements(By by) {
            if (exceptionToThrow != null) {
                throw exceptionToThrow;
            }
            return mockElements != null ? mockElements : Arrays.asList();
        }
        
        // Other WebDriver methods - return default values
        @Override
        public void get(String url) {}
        
        @Override
        public String getCurrentUrl() { return "http://test.com"; }
        
        @Override
        public String getTitle() { return "Test Page"; }
        
        @Override
        public String getPageSource() { return "<html><body>Test</body></html>"; }
        
        @Override
        public void close() {}
        
        @Override
        public void quit() {}
        
        @Override
        public String getWindowHandle() { return "window-1"; }
        
        @Override
        public Set<String> getWindowHandles() { return Set.of("window-1"); }
        
        @Override
        public org.openqa.selenium.WebDriver.Options manage() { return null; }
        
        @Override
        public org.openqa.selenium.WebDriver.Navigation navigate() { return null; }
        
        @Override
        public org.openqa.selenium.WebDriver.TargetLocator switchTo() { return null; }
    }

    /**
     * Mock WebElement implementation for testing.
     */
    private static class MockWebElement implements WebElement {
        @Override
        public void click() {}
        
        @Override
        public void submit() {}
        
        @Override
        public void sendKeys(CharSequence... keysToSend) {}
        
        @Override
        public void clear() {}
        
        @Override
        public String getTagName() { return "div"; }
        
        @Override
        public String getAttribute(String name) { return "mock-attribute"; }
        
        @Override
        public boolean isSelected() { return false; }
        
        @Override
        public boolean isEnabled() { return true; }
        
        @Override
        public String getText() { return "Mock Element Text"; }
        
        @Override
        public List<WebElement> findElements(By by) { return Arrays.asList(); }
        
        @Override
        public WebElement findElement(By by) { return this; }
        
        @Override
        public boolean isDisplayed() { return true; }
        
        @Override
        public org.openqa.selenium.Point getLocation() { return new org.openqa.selenium.Point(0, 0); }
        
        @Override
        public org.openqa.selenium.Dimension getSize() { return new org.openqa.selenium.Dimension(100, 100); }
        
        @Override
        public org.openqa.selenium.Rectangle getRect() { return new org.openqa.selenium.Rectangle(0, 0, 100, 100); }
        
        @Override
        public String getCssValue(String propertyName) { return "mock-css-value"; }
        
        @Override
        public <X> X getScreenshotAs(org.openqa.selenium.OutputType<X> target) throws org.openqa.selenium.WebDriverException { return null; }
    }

    @Test
    void testWebDriverElementFinder_FindElement() {
        WebElement mockElement = new MockWebElement();
        mockWebDriver.setMockElement(mockElement);
        
        By locator = By.id("test-id");
        WebElement result = elementFinder.findElement(locator);
        
        assertNotNull(result);
        assertEquals(mockElement, result);
    }

    @Test
    void testWebDriverElementFinder_FindElements() {
        WebElement mockElement = new MockWebElement();
        List<WebElement> mockElements = Arrays.asList(mockElement);
        mockWebDriver.setMockElements(mockElements);
        
        By locator = By.cssSelector(".test-class");
        List<WebElement> result = elementFinder.findElements(locator);
        
        assertNotNull(result);
        assertEquals(mockElements, result);
    }

    @Test
    void testWebDriverElementFinder_FindElementThrowsException() {
        mockWebDriver.setExceptionToThrow(new RuntimeException("Element not found"));
        
        By locator = By.id("non-existent");
        
        assertThrows(RuntimeException.class, () -> {
            elementFinder.findElement(locator);
        });
    }

    @Test
    void testWebDriverElementFinder_FindElementsReturnsEmpty() {
        mockWebDriver.setMockElements(Arrays.asList());
        
        By locator = By.cssSelector(".empty");
        List<WebElement> result = elementFinder.findElements(locator);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testWebDriverElementFinder_DifferentLocatorTypes() {
        WebElement mockElement = new MockWebElement();
        mockWebDriver.setMockElement(mockElement);
        
        // Test different locator types
        By[] locators = {
            By.id("test-id"),
            By.cssSelector(".test-class"),
            By.xpath("//div[@class='test']"),
            By.name("test-name"),
            By.className("test-class"),
            By.linkText("Click here"),
            By.partialLinkText("Click"),
            By.tagName("button")
        };
        
        for (By locator : locators) {
            WebElement result = elementFinder.findElement(locator);
            assertNotNull(result);
            assertEquals(mockElement, result);
        }
    }

    @Test
    void testWebDriverElementFinder_NullLocator() {
        assertThrows(IllegalArgumentException.class, () -> {
            elementFinder.findElement(null);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            elementFinder.findElements(null);
        });
    }

    @Test
    void testWebDriverElementFinder_ConstructorWithNullDriver() {
        assertThrows(IllegalArgumentException.class, () -> {
            new TestableWebDriverElementFinder(null);
        });
    }

    @Test
    void testWebDriverElementFinder_GetWebDriver() {
        WebDriver driver = elementFinder.getWebDriver();
        assertNotNull(driver);
        assertEquals(mockWebDriver, driver);
    }
} 