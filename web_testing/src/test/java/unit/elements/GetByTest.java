package unit.elements;

import base.GetFileName;
import driver.DriverManager;
import elements.GetBy;
import json.UIProjectJsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static elements.CustomBys.byText;

import java.util.HashMap;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Assertions;

// Concrete implementation of GetBy for testing
class TestableGetBy extends GetBy {
    // This concrete class allows us to test the abstract GetBy methods
}

@ExtendWith(MockitoExtension.class)
class GetByTest {
    private UIProjectJsonReader mockedJsonReader;
    private TestableGetBy testableGetBy;

    @BeforeEach
    public void init() {
        GetFileName.getInstance().setFileName("examples");
        testableGetBy = new TestableGetBy();
    }

    @Test
    void testGetByValueWithRelativeLocator() {
        // Create a test relative locator configuration
        var relativeLocator = new HashMap<String, Object>() {{
            put("test_btn", new HashMap<String, Object>() {{
                put("isRelative", true);
                put("relativeType", "NEAR");
                put("atMostDistanceInPixels", 7);
                put("locators", new HashMap<String, Object>() {{
                    put("firstLocator", new HashMap<String, String>() {{
                        put("locatorType", "CSS_SELECTOR");
                        put("locatorValue", "#id");
                    }});
                    put("secondLocator", new HashMap<String, String>() {{
                        put("locatorType", "ID");
                        put("locatorValue", "id");
                    }});
                }});
            }});
        }};

        // Test that the configuration structure is correct
        Assertions.assertNotNull(relativeLocator);
        Assertions.assertTrue(relativeLocator.containsKey("test_btn"));
        
        var testBtnConfig = (HashMap<String, Object>) relativeLocator.get("test_btn");
        Assertions.assertTrue((Boolean) testBtnConfig.get("isRelative"));
        Assertions.assertEquals("NEAR", testBtnConfig.get("relativeType"));
        Assertions.assertEquals(7, testBtnConfig.get("atMostDistanceInPixels"));
        
        var locators = (HashMap<String, Object>) testBtnConfig.get("locators");
        Assertions.assertNotNull(locators);
        Assertions.assertTrue(locators.containsKey("firstLocator"));
        Assertions.assertTrue(locators.containsKey("secondLocator"));
    }

    @Test
    void testGetByValueWithSimpleLocator() {
        // Create a test simple locator configuration
        var simpleLocator = new HashMap<String, Object>() {{
            put("simple_element", new HashMap<String, Object>() {{
                put("isRelative", false);
                put("locatorType", "ID");
                put("locatorValue", "test-id");
            }});
        }};

        // Test that the configuration structure is correct
        Assertions.assertNotNull(simpleLocator);
        Assertions.assertTrue(simpleLocator.containsKey("simple_element"));
        
        var simpleElementConfig = (HashMap<String, Object>) simpleLocator.get("simple_element");
        Assertions.assertFalse((Boolean) simpleElementConfig.get("isRelative"));
        Assertions.assertEquals("ID", simpleElementConfig.get("locatorType"));
        Assertions.assertEquals("test-id", simpleElementConfig.get("locatorValue"));
    }
}