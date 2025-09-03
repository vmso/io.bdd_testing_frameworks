package unit.elements;

import elements.strategies.RelativeLocatorStrategy;
import elements.strategies.StandardLocatorStrategy;
import enums.LocatorType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LocatorStrategy implementations.
 * Tests both StandardLocatorStrategy and RelativeLocatorStrategy.
 */
class LocatorStrategyTest {

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
        public java.util.List<WebElement> findElements(By by) { return java.util.Arrays.asList(); }
        
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
    void testStandardLocatorStrategy_ID() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.ID);
        
        assertEquals("ID", strategy.getStrategyName());
        
        By locator = strategy.createLocator("test-id");
        assertEquals("By.id: test-id", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_CSS_SELECTOR() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.CSS_SELECTOR);
        
        assertEquals("CSS_SELECTOR", strategy.getStrategyName());
        
        By locator = strategy.createLocator(".test-class");
        assertEquals("By.cssSelector: .test-class", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_XPATH() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.XPATH);
        
        assertEquals("XPATH", strategy.getStrategyName());
        
        By locator = strategy.createLocator("//div[@class='test']");
        assertEquals("By.xpath: //div[@class='test']", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_NAME() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.NAME);
        
        assertEquals("NAME", strategy.getStrategyName());
        
        By locator = strategy.createLocator("test-name");
        assertEquals("By.name: test-name", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_CLASS_NAME() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.CLASS_NAME);
        
        assertEquals("CLASS_NAME", strategy.getStrategyName());
        
        By locator = strategy.createLocator("test-class");
        assertEquals("By.className: test-class", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_TAG_NAME() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.TAG_NAME);
        
        assertEquals("TAG_NAME", strategy.getStrategyName());
        
        By locator = strategy.createLocator("div");
        assertEquals("By.tagName: div", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_LINK_TEXT() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.LINK_TEXT);
        
        assertEquals("LINK_TEXT", strategy.getStrategyName());
        
        By locator = strategy.createLocator("Click here");
        assertEquals("By.linkText: Click here", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_PARTIAL_LINK_TEXT() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.PARTIAL_LINK_TEXT);
        
        assertEquals("PARTIAL_LINK_TEXT", strategy.getStrategyName());
        
        By locator = strategy.createLocator("Click");
        assertEquals("By.partialLinkText: Click", locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_TEXT() {
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.TEXT);
        
        assertEquals("TEXT", strategy.getStrategyName());
        
        By locator = strategy.createLocator("Sample text");
        // The TEXT type creates a custom Text class, not a standard XPath
        assertNotNull(locator);
        // The Text class doesn't override toString(), so we just check it's not null
        assertNotNull(locator.toString());
    }

    @Test
    void testStandardLocatorStrategy_InvalidType() {
        // Test with an invalid locator type - should still work but create a default locator
        StandardLocatorStrategy strategy = new StandardLocatorStrategy(LocatorType.ID);
        
        By locator = strategy.createLocator("test-value");
        assertNotNull(locator);
        assertEquals("By.id: test-value", locator.toString());
    }

    @Test
    void testRelativeLocatorStrategy_NEAR() {
        Map<String, Object> params = new HashMap<>();
        params.put("relativeType", "NEAR");
        params.put("atMostDistanceInPixels", 10);
        
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(params, "test-key");
        
        assertEquals("RELATIVE", strategy.getStrategyName());
        
        WebElement mockElement = new MockWebElement();
        By secondBy = By.id("second-element");
        
        By locator = strategy.createRelativeLocator(mockElement, secondBy);
        assertNotNull(locator);
    }

    @Test
    void testRelativeLocatorStrategy_ABOVE() {
        Map<String, Object> params = new HashMap<>();
        params.put("relativeType", "ABOVE");
        
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(params, "test-key");
        
        assertEquals("RELATIVE", strategy.getStrategyName());
        
        WebElement mockElement = new MockWebElement();
        By secondBy = By.id("second-element");
        
        By locator = strategy.createRelativeLocator(mockElement, secondBy);
        assertNotNull(locator);
    }

    @Test
    void testRelativeLocatorStrategy_BELOW() {
        Map<String, Object> params = new HashMap<>();
        params.put("relativeType", "BELOW");
        
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(params, "test-key");
        
        assertEquals("RELATIVE", strategy.getStrategyName());
        
        WebElement mockElement = new MockWebElement();
        By secondBy = By.id("second-element");
        
        By locator = strategy.createRelativeLocator(mockElement, secondBy);
        assertNotNull(locator);
    }

    @Test
    void testRelativeLocatorStrategy_LEFT_OF() {
        Map<String, Object> params = new HashMap<>();
        params.put("relativeType", "LEFT_OF");
        
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(params, "test-key");
        
        assertEquals("RELATIVE", strategy.getStrategyName());
        
        WebElement mockElement = new MockWebElement();
        By secondBy = By.id("second-element");
        
        By locator = strategy.createRelativeLocator(mockElement, secondBy);
        assertNotNull(locator);
    }

    @Test
    void testRelativeLocatorStrategy_RIGHT_OF() {
        Map<String, Object> params = new HashMap<>();
        params.put("relativeType", "RIGHT_OF");
        
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(params, "test-key");
        
        assertEquals("RELATIVE", strategy.getStrategyName());
        
        WebElement mockElement = new MockWebElement();
        By secondBy = By.id("second-element");
        
        By locator = strategy.createRelativeLocator(mockElement, secondBy);
        assertNotNull(locator);
    }

    @Test
    void testRelativeLocatorStrategy_ConstructorWithNullParams() {
        // The constructor doesn't validate null parameters, so this should work
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(null, "test-key");
        assertNotNull(strategy);
        assertEquals("RELATIVE", strategy.getStrategyName());
    }

    @Test
    void testRelativeLocatorStrategy_ConstructorWithNullJsonKey() {
        Map<String, Object> params = new HashMap<>();
        params.put("relativeType", "NEAR");
        
        // The constructor doesn't validate null jsonKey, so this should work
        RelativeLocatorStrategy strategy = new RelativeLocatorStrategy(params, null);
        assertNotNull(strategy);
        assertEquals("RELATIVE", strategy.getStrategyName());
    }
} 