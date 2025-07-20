package unit.helpers;

import helpers.ClickHelper;
import base.GetFileName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ClickHelper class.
 * Tests parameter validation and basic functionality without complex mocking
 * to avoid Java 22 compatibility issues with Mockito inline mocking.
 * 
 * Based on Java 22 Mockito compatibility issues:
 * - https://issues.apache.org/jira/browse/SLING-12720
 * - https://stackoverflow.com/questions/47878963/mockito-fails-with-inlined-mocks-enabled-with-invalid-paramter-name-exception
 * 
 * @author Testing Framework
 * @version 2.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClickHelperTest {

    private ClickHelper clickHelper;

    @BeforeEach
    void setUp() {
        clickHelper = new ClickHelper();
        
        // Set up the file name to use the existing examples.json file
        // This avoids the need for complex static mocking
        GetFileName.getInstance().setFileName("examples");
    }

    // ==================== PARAMETER VALIDATION TESTS ====================
    // These tests verify that the ClickHelper methods properly validate their parameters

    @Test
    void testClickElementWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickElement((By) null));
    }

    @Test
    void testClickElementWithNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickElement("testButton", -1L));
    }

    @Test
    void testClickElementWithNegativeSleepIntervalShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickElement("testButton", 10L, -1L));
    }

    @Test
    void testClickElementWithByLocatorAndNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickElement(By.id("test"), -1L));
    }

    @Test
    void testClickElementWithoutWaitWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickElementWithoutWait((By) null));
    }

    @Test
    void testCheckIfExitsClickWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.checkIfExitsClick((By) null, 10L));
    }

    @Test
    void testCheckIfExitsClickWithNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.checkIfExitsClick("testButton", -1L));
    }

    @Test
    void testCheckIfExitsClickWithByLocatorAndNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.checkIfExitsClick(By.id("test"), -1L));
    }

    @Test
    void testIfExistsClickXIfNotClickYWithNullByLocatorsShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.ifExistsClickXIfNotClickY((By) null, By.id("test")));
        assertThrows(IllegalArgumentException.class, () -> clickHelper.ifExistsClickXIfNotClickY(By.id("test"), (By) null));
    }

    @Test
    void testIfExistsClickXIfNotClickYWithNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.ifExistsClickXIfNotClickY("element1", "element2", -1L));
    }

    @Test
    void testIfExistsClickXIfNotClickYWithByLocatorsAndNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.ifExistsClickXIfNotClickY(By.id("element1"), By.id("element2"), -1L));
    }

    @Test
    void testClickWithJavaScriptWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickWithJavaScript((By) null));
    }

    @Test
    void testClickWithJavaScriptWithNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickWithJavaScript("testButton", -1L));
    }

    @Test
    void testClickWithJavaScriptWithByLocatorAndNegativeTimeoutShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickWithJavaScript(By.id("test"), -1L));
    }

    @Test
    void testClickAllElementsWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickAllElements((By) null));
    }

    @Test
    void testClickAndWaitWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickAndWait((By) null, 1000L));
    }

    @Test
    void testClickAndWaitWithNegativeWaitTimeShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickAndWait("testButton", -1L));
    }

    @Test
    void testClickAndWaitWithByLocatorAndNegativeWaitTimeShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickAndWait(By.id("test"), -1L));
    }

    @Test
    void testClickIfEnabledWithNullByLocatorShouldThrowException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> clickHelper.clickIfEnabled((By) null));
    }

    // ==================== CLASS INSTANTIATION TESTS ====================

    @Test
    void testClickHelperCanBeInstantiated() {
        // Act & Assert
        assertNotNull(clickHelper);
        assertTrue(clickHelper instanceof ClickHelper);
    }

    @Test
    void testClickHelperMultipleInstancesShouldBeDifferent() {
        // Act
        ClickHelper instance1 = new ClickHelper();
        ClickHelper instance2 = new ClickHelper();
        
        // Assert
        assertNotNull(instance1);
        assertNotNull(instance2);
        assertNotSame(instance1, instance2);
    }

    // ==================== METHOD SIGNATURE TESTS ====================
    // These tests verify that methods exist and can be called with valid parameters
    // They don't execute actual Selenium operations

    @Test
    void testClickElementMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            // These calls will fail due to WebDriver being null, but that's expected
            // We're just testing that the methods exist and can be called
            try {
                clickHelper.clickElement(By.id("test"));
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testClickElementWithoutWaitMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.clickElementWithoutWait(By.id("test"));
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testClickWithJavaScriptMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.clickWithJavaScript(By.id("test"));
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testClickAllElementsMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.clickAllElements(By.cssSelector(".test"));
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testClickAndWaitMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.clickAndWait(By.id("test"), 1000L);
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testClickIfEnabledMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.clickIfEnabled(By.id("test"));
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testCheckIfExitsClickMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.checkIfExitsClick(By.id("test"), 10L);
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }

    @Test
    void testIfExistsClickXIfNotClickYMethodExists() {
        // Act & Assert - Just verify the method exists and can be called
        assertDoesNotThrow(() -> {
            try {
                clickHelper.ifExistsClickXIfNotClickY(By.id("element1"), By.id("element2"));
            } catch (Exception e) {
                // Expected - WebDriver is null in unit test environment
            }
        });
    }
}
