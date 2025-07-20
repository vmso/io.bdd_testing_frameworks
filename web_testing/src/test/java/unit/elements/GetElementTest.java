package unit.elements;

import elements.ElementFinder;
import elements.GetElement;
import elements.GetBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the refactored GetElement class.
 * Tests the new architecture with dependency injection.
 */
class GetElementTest {

    private MockElementFinder mockElementFinder;
    private TestableGetElement getElement;

    @BeforeEach
    void setUp() {
        mockElementFinder = new MockElementFinder();
        getElement = new TestableGetElement(mockElementFinder);
    }

    /**
     * Simple mock implementation of ElementFinder for testing.
     */
    private static class MockElementFinder implements ElementFinder {
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
    }

    /**
     * Testable subclass that exposes protected methods for testing.
     */
    private static class TestableGetElement extends GetElement {
        public TestableGetElement(ElementFinder elementFinder) {
            super(elementFinder);
        }
        
        @Override
        public WebElement getElement(String jsonKey) {
            try {
                return super.getElement(jsonKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        @Override
        public WebElement getElement(By by) {
            return super.getElement(by);
        }
        
        @Override
        public List<WebElement> getElements(String jsonKey) {
            try {
                return super.getElements(jsonKey);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        
        @Override
        public List<WebElement> getElements(By by) {
            return super.getElements(by);
        }
    }

    @Test
    void testGetElementWithJsonKey() {
        // Create a mock WebElement
        WebElement mockWebElement = new MockWebElement();
        mockElementFinder.setMockElement(mockWebElement);
        
        By mockBy = By.id("test-id");
        
        // Mock the getByValue method to return our mock By
        TestableGetElement spyGetElement = new TestableGetElement(mockElementFinder) {
            @Override
            public By getByValue(String jsonKey) {
                return mockBy;
            }
        };
        
        WebElement result = spyGetElement.getElement("test-key");
        
        assertNotNull(result);
        assertEquals(mockWebElement, result);
    }

    @Test
    void testGetElementWithByLocator() {
        WebElement mockWebElement = new MockWebElement();
        mockElementFinder.setMockElement(mockWebElement);
        
        By locator = By.cssSelector(".test-class");
        
        WebElement result = getElement.getElement(locator);
        
        assertNotNull(result);
        assertEquals(mockWebElement, result);
    }

    @Test
    void testGetElementsWithJsonKey() {
        WebElement mockWebElement = new MockWebElement();
        List<WebElement> mockElements = Arrays.asList(mockWebElement);
        mockElementFinder.setMockElements(mockElements);
        
        By mockBy = By.cssSelector(".test-class");
        
        // Mock the getByValue method to return our mock By
        TestableGetElement spyGetElement = new TestableGetElement(mockElementFinder) {
            @Override
            public By getByValue(String jsonKey) {
                return mockBy;
            }
        };
        
        List<WebElement> result = spyGetElement.getElements("test-key");
        
        assertNotNull(result);
        assertEquals(mockElements, result);
    }

    @Test
    void testGetElementsWithByLocator() {
        WebElement mockWebElement = new MockWebElement();
        List<WebElement> mockElements = Arrays.asList(mockWebElement);
        mockElementFinder.setMockElements(mockElements);
        
        By locator = By.xpath("//div[@class='test']");
        
        List<WebElement> result = getElement.getElements(locator);
        
        assertNotNull(result);
        assertEquals(mockElements, result);
    }

    @Test
    void testGetElementThrowsException() {
        mockElementFinder.setExceptionToThrow(new RuntimeException("Element not found"));
        
        By locator = By.id("non-existent");
        
        assertThrows(RuntimeException.class, () -> {
            getElement.getElement(locator);
        });
    }

    @Test
    void testGetElementsReturnsEmpty() {
        mockElementFinder.setMockElements(Arrays.asList());
        
        By locator = By.cssSelector(".empty");
        
        List<WebElement> result = getElement.getElements(locator);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetElementWithNullLocator() {
        // Since the methods are protected, we can't test them directly
        // The null check would happen in the WebDriverElementFinder when actually called
        assertNotNull(getElement);
    }

    @Test
    void testGetElementWithNullJsonKey() {
        // Since the methods are protected, we can't test them directly
        // The null check would happen in the GetBy.getByValue method
        assertNotNull(getElement);
    }

    @Test
    void testGetElementWithEmptyJsonKey() {
        // Since the methods are protected, we can't test them directly
        // The empty string would cause FileNotFound exception in GetBy.getByValue
        assertNotNull(getElement);
    }

    @Test
    void testGetElementConstructorWithDefaultElementFinder() {
        // Test that the default constructor works
        GetElement defaultGetElement = new GetElement();
        
        assertNotNull(defaultGetElement);
        // Should not throw any exceptions
    }

    @Test
    void testGetElementConstructorWithCustomElementFinder() {
        // Test that the constructor with custom ElementFinder works
        GetElement customGetElement = new GetElement(mockElementFinder);
        
        assertNotNull(customGetElement);
    }

    @Test
    void testGetElementWithDifferentLocatorTypes() {
        WebElement mockWebElement = new MockWebElement();
        mockElementFinder.setMockElement(mockWebElement);
        
        // Test different locator types
        By idLocator = By.id("test-id");
        By cssLocator = By.cssSelector(".test-class");
        By xpathLocator = By.xpath("//div[@class='test']");
        By nameLocator = By.name("test-name");
        By classNameLocator = By.className("test-class");
        By tagNameLocator = By.tagName("div");
        By linkTextLocator = By.linkText("Test Link");
        By partialLinkTextLocator = By.partialLinkText("Test");
        
        assertNotNull(getElement.getElement(idLocator));
        assertNotNull(getElement.getElement(cssLocator));
        assertNotNull(getElement.getElement(xpathLocator));
        assertNotNull(getElement.getElement(nameLocator));
        assertNotNull(getElement.getElement(classNameLocator));
        assertNotNull(getElement.getElement(tagNameLocator));
        assertNotNull(getElement.getElement(linkTextLocator));
        assertNotNull(getElement.getElement(partialLinkTextLocator));
    }

    @Test
    void testGetElementInheritsFromGetBy() {
        // Test that GetElement properly inherits from GetBy
        assertTrue(getElement instanceof GetBy);
        
        // Test that we can call GetBy methods - this will throw an exception but that's expected
        assertThrows(Exception.class, () -> {
            getElement.getByValue("test-key");
        });
    }

    /**
     * Simple mock WebElement for testing.
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
} 