package unit.driverManager;

import driver.DriverManager;
import enums.Browser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.lenient;

/**
 * Comprehensive unit tests for DriverManager
 * Focuses on DriverManager's core responsibilities:
 * - Singleton pattern
 * - Driver lifecycle management
 * - State management
 * - Error handling
 * - Thread safety
 * 
 * Browser selection logic is tested separately in BrowserSelectionLogicTest.java
 */
@ExtendWith(MockitoExtension.class)
class DriverManagerComprehensiveTest {
    
    private DriverManager driverManager;
    
    @Mock
    private WebDriver mockDriver;
    
    @BeforeEach
    void setUp() {
        driverManager = DriverManager.getInstance();
        // Clear any existing system properties
        System.clearProperty("browser");
        // Reset browser type to ensure clean state
        driverManager.setBrowsersType(null);
    }
    
    @AfterEach
    void tearDown() {
        if (driverManager != null) {
            driverManager.quitDriver();
        }
        System.clearProperty("browser");
    }
    
    @Test
    void testSingletonPattern() {
        // Test that DriverManager follows singleton pattern
        DriverManager instance1 = DriverManager.getInstance();
        DriverManager instance2 = DriverManager.getInstance();
        
        assertNotNull(instance1, "First instance should not be null");
        assertNotNull(instance2, "Second instance should not be null");
        assertSame(instance1, instance2, "Both instances should be the same");
    }
    
    @Test
    void testCreateLocalDriverWithMockBrowser() {
        // Test local driver creation with mock browser (safe for unit testing)
        System.setProperty("browser", "mock");
        
        assertDoesNotThrow(() -> {
            driverManager.createLocalDriver();
        }, "Creating local driver with mock browser should not throw exception");
        
        assertEquals(Browser.MOCK, driverManager.getBrowsersType(), "Browser type should be MOCK");
        
        // Mock browser returns null, so driver should be null
        WebDriver driver = driverManager.getDriver();
        assertNull(driver, "Mock browser should return null driver");
    }
    
    @Test
    void testCreateLocalDriverInvalidBrowser() {
        // Test handling of invalid browser type
        System.setProperty("browser", "invalid_browser");
        
        assertThrows(IllegalArgumentException.class, () -> {
            driverManager.createLocalDriver();
        }, "Should throw IllegalArgumentException for invalid browser type");
    }
    
    @Test
    void testCreateRemoteDriver() {
        // Test remote driver creation with mock browser
        System.setProperty("browser", "mock");
        
        // Test that remote driver creation with mock browser should not throw exception
        // Mock browser is handled specially and doesn't create real RemoteWebDriver
        assertDoesNotThrow(() -> {
            driverManager.createRemoteDriver("http://invalid-grid-url:4444");
        }, "Mock browser should not throw exception for invalid grid URL");
        
        assertEquals(Browser.MOCK, driverManager.getBrowsersType(), "Browser type should be set for remote driver");
    }
    
    @Test
    void testGetDriverWhenNull() {
        // Test getDriver when driver is null
        WebDriver driver = driverManager.getDriver();
        assertNull(driver, "Driver should be null when not created");
    }
    
    @Test
    void testSetDriver() {
        // Test setDriver method
        driverManager.setDriver(mockDriver);
        
        WebDriver retrievedDriver = driverManager.getDriver();
        assertSame(mockDriver, retrievedDriver, "Retrieved driver should be the same as set driver");
    }
    
    @Test
    void testGetWaitWhenDriverNull() {
        // Test getWait when driver is null
        WebDriverWait wait = driverManager.getWait();
        assertNull(wait, "WebDriverWait should be null when driver is null");
    }
    
    @Test
    void testGetWaitWhenDriverExists() {
        // Test getWait when driver exists
        driverManager.setDriver(mockDriver);
        
        WebDriverWait wait = driverManager.getWait();
        assertNotNull(wait, "WebDriverWait should not be null when driver exists");
    }
    
    @Test
    void testIsDriverActiveWhenNull() {
        // Test isDriverActive when driver is null
        assertFalse(driverManager.isDriverActive(), "Driver should not be active when null");
    }
    
    @Test
    void testIsDriverActiveWhenDriverExists() {
        // Test isDriverActive when driver exists
        driverManager.setDriver(mockDriver);
        
        // Mock the getCurrentUrl method to return a URL (indicating active driver)
        lenient().when(mockDriver.getCurrentUrl()).thenReturn("https://example.com");
        
        assertTrue(driverManager.isDriverActive(), "Driver should be active when it can get current URL");
    }
    
    @Test
    void testIsDriverActiveWhenDriverThrowsException() {
        // Test isDriverActive when driver throws exception
        driverManager.setDriver(mockDriver);
        
        // Mock the getCurrentUrl method to throw exception (indicating inactive driver)
        when(mockDriver.getCurrentUrl()).thenThrow(new RuntimeException("Driver not active"));
        
        assertFalse(driverManager.isDriverActive(), "Driver should not be active when it throws exception");
    }
    
    @Test
    void testGetBrowsersType() {
        // Test getBrowsersType method
        assertNull(driverManager.getBrowsersType(), "Browser type should be null initially");
        
        // Set browser type without creating driver
        driverManager.setBrowsersType(Browser.MOCK);
        assertEquals(Browser.MOCK, driverManager.getBrowsersType(), "Browser type should be MOCK");
    }
    
    @Test
    void testSetBrowsersType() {
        // Test setBrowsersType method
        driverManager.setBrowsersType(Browser.FIREFOX);
        assertEquals(Browser.FIREFOX, driverManager.getBrowsersType(), "Browser type should be set to FIREFOX");
        
        driverManager.setBrowsersType(Browser.SAFARI);
        assertEquals(Browser.SAFARI, driverManager.getBrowsersType(), "Browser type should be set to SAFARI");
    }
    
    @Test
    void testQuitDriverWhenNull() {
        // Test quitDriver when driver is null
        assertDoesNotThrow(() -> {
            driverManager.quitDriver();
        }, "Quitting null driver should not throw exception");
    }
    
    @Test
    void testQuitDriverWhenDriverExists() {
        // Test quitDriver when driver exists
        driverManager.setDriver(mockDriver);
        
        assertDoesNotThrow(() -> {
            driverManager.quitDriver();
        }, "Quitting existing driver should not throw exception");
        
        assertFalse(driverManager.isDriverActive(), "Driver should not be active after quit");
    }
    
    @Test
    void testQuitDriverWithNoSuchSessionException() {
        // Test quitDriver when driver throws NoSuchSessionException
        driverManager.setDriver(mockDriver);
        
        // Mock quit to throw NoSuchSessionException
        lenient().doThrow(new org.openqa.selenium.NoSuchSessionException("Session not found")).when(mockDriver).quit();
        
        assertDoesNotThrow(() -> {
            driverManager.quitDriver();
        }, "Quitting driver with NoSuchSessionException should not throw exception");
    }
    
    @Test
    void testRefreshDriver() {
        // Test refreshDriver method with mock browser
        System.setProperty("browser", "mock");
        driverManager.createLocalDriver();
        
        assertDoesNotThrow(() -> {
            driverManager.refreshDriver();
        }, "Refreshing driver should not throw exception");
        
        assertEquals(Browser.MOCK, driverManager.getBrowsersType(), "Browser type should remain MOCK after refresh");
    }
    
    @Test
    void testRefreshDriverWhenNull() {
        // Test refreshDriver when driver is null
        // Set browser to mock to avoid starting real browser
        System.setProperty("browser", "mock");
        
        assertDoesNotThrow(() -> {
            driverManager.refreshDriver();
        }, "Refreshing null driver should not throw exception");
    }
    
    @Test
    void testDriverStateManagement() {
        // Test complete driver lifecycle management
        assertNull(driverManager.getDriver(), "Driver should be null initially");
        assertFalse(driverManager.isDriverActive(), "Driver should not be active initially");
        
        // Set driver
        driverManager.setDriver(mockDriver);
        assertSame(mockDriver, driverManager.getDriver(), "Driver should be set");
        
        // Mock active state
        when(mockDriver.getCurrentUrl()).thenReturn("https://example.com");
        assertTrue(driverManager.isDriverActive(), "Driver should be active");
        
        // Quit driver
        driverManager.quitDriver();
        assertFalse(driverManager.isDriverActive(), "Driver should not be active after quit");
    }
    
    @Test
    void testConcurrentAccess() {
        // Test thread safety of DriverManager singleton
        DriverManager[] instances = new DriverManager[10];
        
        // Create multiple threads to access DriverManager
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                instances[index] = DriverManager.getInstance();
            });
        }
        
        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        // Verify all instances are the same (singleton pattern)
        DriverManager firstInstance = instances[0];
        assertNotNull(firstInstance, "First instance should not be null");
        
        for (int i = 1; i < instances.length; i++) {
            assertSame(firstInstance, instances[i], "All instances should be the same (singleton)");
        }
    }
    
    @Test
    void testDriverManagerErrorHandling() {
        // Test error handling in DriverManager
        
        // Test with null driver
        assertDoesNotThrow(() -> {
            driverManager.quitDriver();
        }, "Quitting null driver should not throw exception");
        
        // Test with invalid browser type
        System.setProperty("browser", "invalid_browser");
        assertThrows(IllegalArgumentException.class, () -> {
            driverManager.createLocalDriver();
        }, "Should throw IllegalArgumentException for invalid browser type");
        
        // Test remote driver with invalid URL - mock browser should not throw exception
        System.setProperty("browser", "mock");
        assertDoesNotThrow(() -> {
            driverManager.createRemoteDriver("invalid-url");
        }, "Mock browser should not throw exception for invalid remote URL");
    }
} 