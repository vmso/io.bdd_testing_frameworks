package unit.elements;

import elements.ElementFinder;
import elements.LocatorFactory;
import enums.LocatorType;
import exceptions.KeywordNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LocatorFactory class.
 * Tests the factory pattern for creating different types of locators.
 */
class LocatorFactoryTest {

    private MockElementFinder mockElementFinder;
    private LocatorFactory locatorFactory;

    @BeforeEach
    void setUp() {
        mockElementFinder = new MockElementFinder();
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
    void testCreateStandardLocator() {
        Map<String, String> locatorMap = new HashMap<>();
        locatorMap.put("locatorType", "ID");
        locatorMap.put("locatorValue", "test-id");
        
        By locator = LocatorFactory.createStandardLocator(locatorMap, "test-key");
        
        assertNotNull(locator);
        assertEquals("By.id: test-id", locator.toString());
    }

    @Test
    void testCreateStandardLocator_MissingLocatorType() {
        Map<String, String> locatorMap = new HashMap<>();
        locatorMap.put("locatorValue", "test-id");
        // Missing locatorType
        
        assertThrows(KeywordNotFoundException.class, () -> {
            LocatorFactory.createStandardLocator(locatorMap, "test-key");
        });
    }

    @Test
    void testCreateStandardLocator_MissingLocatorValue() {
        Map<String, String> locatorMap = new HashMap<>();
        locatorMap.put("locatorType", "ID");
        // Missing locatorValue
        
        assertThrows(KeywordNotFoundException.class, () -> {
            LocatorFactory.createStandardLocator(locatorMap, "test-key");
        });
    }

    @Test
    void testCreateRelativeLocator() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isRelative", true);
        jsonMap.put("relativeType", "NEAR");
        jsonMap.put("atMostDistanceInPixels", 10);
        jsonMap.put("locators", new HashMap<String, Object>() {{
            put("firstLocator", new HashMap<String, String>() {{
                put("locatorType", "ID");
                put("locatorValue", "first-id");
            }});
            put("secondLocator", new HashMap<String, String>() {{
                put("locatorType", "CSS_SELECTOR");
                put("locatorValue", ".second-class");
            }});
        }});
        
        WebElement mockElement = new MockWebElement();
        mockElementFinder.setMockElement(mockElement);
        
        By locator = LocatorFactory.createRelativeLocator(jsonMap, "test-key", mockElementFinder);
        
        assertNotNull(locator);
        // Relative locator should be created successfully
    }

    @Test
    void testCreateRelativeLocator_MissingLocators() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isRelative", true);
        jsonMap.put("relativeType", "NEAR");
        // Missing locators
        
        assertThrows(KeywordNotFoundException.class, () -> {
            LocatorFactory.createRelativeLocator(jsonMap, "test-key", mockElementFinder);
        });
    }

    @Test
    void testCreateRelativeLocator_MissingFirstLocator() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isRelative", true);
        jsonMap.put("relativeType", "NEAR");
        jsonMap.put("locators", new HashMap<String, Object>() {{
            // Missing firstLocator
            put("secondLocator", new HashMap<String, String>() {{
                put("locatorType", "CSS_SELECTOR");
                put("locatorValue", ".second-class");
            }});
        }});
        
        assertThrows(KeywordNotFoundException.class, () -> {
            LocatorFactory.createRelativeLocator(jsonMap, "test-key", mockElementFinder);
        });
    }

    @Test
    void testCreateRelativeLocator_MissingSecondLocator() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isRelative", true);
        jsonMap.put("relativeType", "NEAR");
        jsonMap.put("locators", new HashMap<String, Object>() {{
            put("firstLocator", new HashMap<String, String>() {{
                put("locatorType", "ID");
                put("locatorValue", "first-id");
            }});
            // Missing secondLocator
        }});
        
        assertThrows(KeywordNotFoundException.class, () -> {
            LocatorFactory.createRelativeLocator(jsonMap, "test-key", mockElementFinder);
        });
    }

    @Test
    void testCreateStandardStrategy() {
        var strategy = LocatorFactory.createStandardStrategy(LocatorType.ID);
        
        assertNotNull(strategy);
        assertEquals("ID", strategy.getStrategyName());
        
        By locator = strategy.createLocator("test-id");
        assertEquals("By.id: test-id", locator.toString());
    }

    @Test
    void testCreateRelativeStrategy() {
        Map<String, Object> relativeParams = new HashMap<>();
        relativeParams.put("relativeType", "NEAR");
        relativeParams.put("atMostDistanceInPixels", 10);
        
        var strategy = LocatorFactory.createRelativeStrategy(relativeParams, "test-key");
        
        assertNotNull(strategy);
        assertEquals("RELATIVE", strategy.getStrategyName());
    }

    @Test
    void testCreateLocator_StandardLocator() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isRelative", false);
        jsonMap.put("locatorType", "ID");
        jsonMap.put("locatorValue", "test-id");
        
        By locator = LocatorFactory.createLocator(jsonMap, "test-key", mockElementFinder);
        
        assertNotNull(locator);
        assertEquals("By.id: test-id", locator.toString());
    }

    @Test
    void testCreateLocator_RelativeLocator() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("isRelative", true);
        jsonMap.put("relativeType", "NEAR");
        jsonMap.put("atMostDistanceInPixels", 10);
        jsonMap.put("locators", new HashMap<String, Object>() {{
            put("firstLocator", new HashMap<String, String>() {{
                put("locatorType", "ID");
                put("locatorValue", "first-id");
            }});
            put("secondLocator", new HashMap<String, String>() {{
                put("locatorType", "CSS_SELECTOR");
                put("locatorValue", ".second-class");
            }});
        }});
        
        WebElement mockElement = new MockWebElement();
        mockElementFinder.setMockElement(mockElement);
        
        By locator = LocatorFactory.createLocator(jsonMap, "test-key", mockElementFinder);
        
        assertNotNull(locator);
        // Relative locator should be created successfully
    }

    @Test
    void testCreateLocator_AllLocatorTypes() {
        // Test all standard locator types
        LocatorType[] types = {
            LocatorType.ID, LocatorType.CSS_SELECTOR, LocatorType.XPATH, 
            LocatorType.NAME, LocatorType.CLASS_NAME, LocatorType.LINK_TEXT,
            LocatorType.PARTIAL_LINK_TEXT, LocatorType.TAG_NAME, LocatorType.TEXT
        };
        
        for (LocatorType type : types) {
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("isRelative", false);
            jsonMap.put("locatorType", type.name());
            jsonMap.put("locatorValue", "test-value");
            
            By locator = LocatorFactory.createLocator(jsonMap, "test-key", mockElementFinder);
            
            assertNotNull(locator, "Locator should not be null for type: " + type);
        }
    }
} 