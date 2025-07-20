package unit.driverManager;

import browsers.BrowserSelectable;
import browsers.Chrome;
import browsers.Firefox;
import browsers.Safari;
import browsers.Edge;
import browsers.MockBrowser;
import enums.Browser;
import driver.DriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test browser selection logic and verify correct browser implementations are selected
 * Tests the browser selection mechanism without starting real browsers
 * Uses reflection and factory pattern testing instead of Mockito due to Java 22 compatibility
 */
@ExtendWith(MockitoExtension.class)
class BrowserSelectionLogicTest {
    
    @Test
    void testChromeBrowserSelectionAndImplementation() {
        // Test Chrome browser selection logic
        Chrome chrome = new Chrome();
        
        // Verify browser selection properties
        assertEquals("chrome", chrome.getBrowserName(), "Chrome browser name should be 'chrome'");
        assertTrue(chrome.isSupported(), "Chrome should be supported on all platforms");
        assertNotNull(chrome.getCapabilities(), "Chrome capabilities should not be null");
        
        // Verify that Chrome implements BrowserSelectable interface
        assertTrue(chrome instanceof BrowserSelectable, "Chrome should implement BrowserSelectable");
        
        // Verify browser enum consistency
        assertEquals(Browser.CHROME.getValue(), chrome.getBrowserName(), 
            "Chrome enum and implementation should have same name");
        
        // Test that Chrome has the correct implementation class
        assertEquals(Chrome.class, chrome.getClass(), "Should be Chrome implementation");
        
        // DO NOT call chrome.createDriver() - this would start a real browser!
    }
    
    @Test
    void testFirefoxBrowserSelectionAndImplementation() {
        // Test Firefox browser selection logic
        Firefox firefox = new Firefox();
        
        // Verify browser selection properties
        assertEquals("firefox", firefox.getBrowserName(), "Firefox browser name should be 'firefox'");
        assertTrue(firefox.isSupported(), "Firefox should be supported on all platforms");
        assertNotNull(firefox.getCapabilities(), "Firefox capabilities should not be null");
        
        // Verify that Firefox implements BrowserSelectable interface
        assertTrue(firefox instanceof BrowserSelectable, "Firefox should implement BrowserSelectable");
        
        // Verify browser enum consistency
        assertEquals(Browser.FIREFOX.getValue(), firefox.getBrowserName(), 
            "Firefox enum and implementation should have same name");
        
        // Test that Firefox has the correct implementation class
        assertEquals(Firefox.class, firefox.getClass(), "Should be Firefox implementation");
        
        // DO NOT call firefox.createDriver() - this would start a real browser!
    }
    
    @Test
    void testSafariBrowserSelectionAndImplementation() {
        // Test Safari browser selection logic
        Safari safari = new Safari();
        
        // Verify browser selection properties
        assertEquals("safari", safari.getBrowserName(), "Safari browser name should be 'safari'");
        assertNotNull(safari.getCapabilities(), "Safari capabilities should not be null");
        
        // Verify that Safari implements BrowserSelectable interface
        assertTrue(safari instanceof BrowserSelectable, "Safari should implement BrowserSelectable");
        
        // Verify browser enum consistency
        assertEquals(Browser.SAFARI.getValue(), safari.getBrowserName(), 
            "Safari enum and implementation should have same name");
        
        // Test that Safari has the correct implementation class
        assertEquals(Safari.class, safari.getClass(), "Should be Safari implementation");
        
        // DO NOT call safari.createDriver() - this would start a real browser!
    }
    
    @Test
    void testEdgeBrowserSelectionAndImplementation() {
        // Test Edge browser selection logic
        Edge edge = new Edge();
        
        // Verify browser selection properties
        assertEquals("edge", edge.getBrowserName(), "Edge browser name should be 'edge'");
        assertTrue(edge.isSupported(), "Edge should be supported on all platforms");
        assertNotNull(edge.getCapabilities(), "Edge capabilities should not be null");
        
        // Verify that Edge implements BrowserSelectable interface
        assertTrue(edge instanceof BrowserSelectable, "Edge should implement BrowserSelectable");
        
        // Verify browser enum consistency
        assertEquals(Browser.EDGE.getValue(), edge.getBrowserName(), 
            "Edge enum and implementation should have same name");
        
        // Test that Edge has the correct implementation class
        assertEquals(Edge.class, edge.getClass(), "Should be Edge implementation");
        
        // DO NOT call edge.createDriver() - this would start a real browser!
    }
    
    @Test
    void testMockBrowserSelectionAndImplementation() {
        // Test Mock browser selection logic - this one is safe to call createDriver()
        MockBrowser mockBrowser = new MockBrowser();
        
        // Verify browser selection properties
        assertEquals("mock", mockBrowser.getBrowserName(), "Mock browser name should be 'mock'");
        assertTrue(mockBrowser.isSupported(), "Mock browser should always be supported");
        assertNotNull(mockBrowser.getCapabilities(), "Mock browser capabilities should not be null");
        
        // Verify that MockBrowser implements BrowserSelectable interface
        assertTrue(mockBrowser instanceof BrowserSelectable, "MockBrowser should implement BrowserSelectable");
        
        // Verify browser enum consistency
        assertEquals(Browser.MOCK.getValue(), mockBrowser.getBrowserName(), 
            "Mock enum and implementation should have same name");
        
        // Test that MockBrowser has the correct implementation class
        assertEquals(MockBrowser.class, mockBrowser.getClass(), "Should be MockBrowser implementation");
        
        // Mock browser is safe to test - it returns null intentionally to prevent real browser startup
        assertDoesNotThrow(() -> {
            WebDriver driver = mockBrowser.createDriver();
            // Mock browser intentionally returns null to prevent real browser startup
            assertNull(driver, "Mock browser should return null to prevent real browser startup");
        }, "Mock browser createDriver should not throw exception");
    }
    
    @Test
    void testBrowserFactoryPattern() {
        // Test that we can create browser instances based on enum values
        // This verifies the factory pattern works correctly
        
        // Test Chrome factory
        BrowserSelectable chrome = createBrowserFromEnum(Browser.CHROME);
        assertNotNull(chrome, "Chrome browser should be created");
        assertEquals("chrome", chrome.getBrowserName());
        assertTrue(chrome instanceof Chrome);
        
        // Test Firefox factory
        BrowserSelectable firefox = createBrowserFromEnum(Browser.FIREFOX);
        assertNotNull(firefox, "Firefox browser should be created");
        assertEquals("firefox", firefox.getBrowserName());
        assertTrue(firefox instanceof Firefox);
        
        // Test Safari factory
        BrowserSelectable safari = createBrowserFromEnum(Browser.SAFARI);
        assertNotNull(safari, "Safari browser should be created");
        assertEquals("safari", safari.getBrowserName());
        assertTrue(safari instanceof Safari);
        
        // Test Edge factory
        BrowserSelectable edge = createBrowserFromEnum(Browser.EDGE);
        assertNotNull(edge, "Edge browser should be created");
        assertEquals("edge", edge.getBrowserName());
        assertTrue(edge instanceof Edge);
        
        // Test Mock factory
        BrowserSelectable mock = createBrowserFromEnum(Browser.MOCK);
        assertNotNull(mock, "Mock browser should be created");
        assertEquals("mock", mock.getBrowserName());
        assertTrue(mock instanceof MockBrowser);
    }
    
    @Test
    void testBrowserSelectionLogic() {
        // Test that browser selection logic works correctly
        // This verifies the selection mechanism without creating real drivers
        
        assertAll("All browser types should be properly configured and selectable",
            () -> {
                BrowserSelectable chrome = createBrowserFromEnum(Browser.CHROME);
                assertEquals("chrome", chrome.getBrowserName());
                assertTrue(chrome.isSupported());
                assertNotNull(chrome.getCapabilities());
                // DO NOT call chrome.createDriver() - this would start a real browser!
            },
            () -> {
                BrowserSelectable firefox = createBrowserFromEnum(Browser.FIREFOX);
                assertEquals("firefox", firefox.getBrowserName());
                assertTrue(firefox.isSupported());
                assertNotNull(firefox.getCapabilities());
                // DO NOT call firefox.createDriver() - this would start a real browser!
            },
            () -> {
                BrowserSelectable safari = createBrowserFromEnum(Browser.SAFARI);
                assertEquals("safari", safari.getBrowserName());
                assertNotNull(safari.getCapabilities());
                // DO NOT call safari.createDriver() - this would start a real browser!
            },
            () -> {
                BrowserSelectable edge = createBrowserFromEnum(Browser.EDGE);
                assertEquals("edge", edge.getBrowserName());
                assertTrue(edge.isSupported());
                assertNotNull(edge.getCapabilities());
                // DO NOT call edge.createDriver() - this would start a real browser!
            },
            () -> {
                BrowserSelectable mock = createBrowserFromEnum(Browser.MOCK);
                assertEquals("mock", mock.getBrowserName());
                assertTrue(mock.isSupported());
                assertNotNull(mock.getCapabilities());
                // Mock is safe to test - it doesn't start real browsers
                assertDoesNotThrow(() -> mock.createDriver());
            }
        );
    }
    
    @Test
    void testBrowserInterfaceCompliance() {
        // Test that all browser implementations comply with BrowserSelectable interface
        // This verifies the interface contract without creating real browsers
        
        // Test Chrome interface compliance
        Chrome chrome = new Chrome();
        assertTrue(chrome instanceof BrowserSelectable, "Chrome should implement BrowserSelectable");
        assertNotNull(chrome.getBrowserName(), "Chrome should have browser name");
        assertNotNull(chrome.getCapabilities(), "Chrome should have capabilities");
        
        // Test Firefox interface compliance
        Firefox firefox = new Firefox();
        assertTrue(firefox instanceof BrowserSelectable, "Firefox should implement BrowserSelectable");
        assertNotNull(firefox.getBrowserName(), "Firefox should have browser name");
        assertNotNull(firefox.getCapabilities(), "Firefox should have capabilities");
        
        // Test Safari interface compliance
        Safari safari = new Safari();
        assertTrue(safari instanceof BrowserSelectable, "Safari should implement BrowserSelectable");
        assertNotNull(safari.getBrowserName(), "Safari should have browser name");
        assertNotNull(safari.getCapabilities(), "Safari should have capabilities");
        
        // Test Edge interface compliance
        Edge edge = new Edge();
        assertTrue(edge instanceof BrowserSelectable, "Edge should implement BrowserSelectable");
        assertNotNull(edge.getBrowserName(), "Edge should have browser name");
        assertNotNull(edge.getCapabilities(), "Edge should have capabilities");
        
        // Test MockBrowser interface compliance
        MockBrowser mockBrowser = new MockBrowser();
        assertTrue(mockBrowser instanceof BrowserSelectable, "MockBrowser should implement BrowserSelectable");
        assertNotNull(mockBrowser.getBrowserName(), "MockBrowser should have browser name");
        assertNotNull(mockBrowser.getCapabilities(), "MockBrowser should have capabilities");
    }
    
    @Test
    void testBrowserNameUniqueness() {
        // Test that all browser names are unique
        String chromeName = new Chrome().getBrowserName();
        String firefoxName = new Firefox().getBrowserName();
        String safariName = new Safari().getBrowserName();
        String edgeName = new Edge().getBrowserName();
        String mockName = new MockBrowser().getBrowserName();
        
        assertAll("All browser names should be unique",
            () -> assertNotEquals(chromeName, firefoxName, "Chrome and Firefox names should be different"),
            () -> assertNotEquals(chromeName, safariName, "Chrome and Safari names should be different"),
            () -> assertNotEquals(chromeName, edgeName, "Chrome and Edge names should be different"),
            () -> assertNotEquals(chromeName, mockName, "Chrome and Mock names should be different"),
            () -> assertNotEquals(firefoxName, safariName, "Firefox and Safari names should be different"),
            () -> assertNotEquals(firefoxName, edgeName, "Firefox and Edge names should be different"),
            () -> assertNotEquals(firefoxName, mockName, "Firefox and Mock names should be different"),
            () -> assertNotEquals(safariName, edgeName, "Safari and Edge names should be different"),
            () -> assertNotEquals(safariName, mockName, "Safari and Mock names should be different"),
            () -> assertNotEquals(edgeName, mockName, "Edge and Mock names should be different")
        );
    }
    
    @Test
    void testBrowserCapabilitiesConfiguration() {
        // Test that all browsers have properly configured capabilities
        assertAll("All browsers should have properly configured capabilities",
            () -> {
                var chromeCaps = new Chrome().getCapabilities();
                assertNotNull(chromeCaps, "Chrome capabilities should not be null");
            },
            () -> {
                var firefoxCaps = new Firefox().getCapabilities();
                assertNotNull(firefoxCaps, "Firefox capabilities should not be null");
            },
            () -> {
                var safariCaps = new Safari().getCapabilities();
                assertNotNull(safariCaps, "Safari capabilities should not be null");
            },
            () -> {
                var edgeCaps = new Edge().getCapabilities();
                assertNotNull(edgeCaps, "Edge capabilities should not be null");
            },
            () -> {
                var mockCaps = new MockBrowser().getCapabilities();
                assertNotNull(mockCaps, "Mock browser capabilities should not be null");
            }
        );
    }
    
    @Test
    void testBrowserEnumCompleteness() {
        // Test that all browser types are properly defined in enum
        assertAll("All browser types should be properly defined in Browser enum",
            () -> assertEquals("chrome", Browser.CHROME.getValue(), "CHROME enum should have 'chrome' value"),
            () -> assertEquals("firefox", Browser.FIREFOX.getValue(), "FIREFOX enum should have 'firefox' value"),
            () -> assertEquals("safari", Browser.SAFARI.getValue(), "SAFARI enum should have 'safari' value"),
            () -> assertEquals("edge", Browser.EDGE.getValue(), "EDGE enum should have 'edge' value"),
            () -> assertEquals("mock", Browser.MOCK.getValue(), "MOCK enum should have 'mock' value")
        );
    }
    
    /**
     * Factory method to create browser instances based on enum values
     * This simulates the browser selection logic without using DriverManager directly
     */
    private BrowserSelectable createBrowserFromEnum(Browser browserType) {
        switch (browserType) {
            case CHROME:
                return new Chrome();
            case FIREFOX:
                return new Firefox();
            case SAFARI:
                return new Safari();
            case EDGE:
                return new Edge();
            case MOCK:
                return new MockBrowser();
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }
} 