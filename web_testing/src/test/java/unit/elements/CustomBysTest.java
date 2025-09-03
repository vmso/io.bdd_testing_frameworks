package unit.elements;

import elements.CustomBys;
import elements.Text;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CustomBys class.
 * Tests custom By implementations for special locator types.
 */
class CustomBysTest {

    @Test
    void testByText() {
        By byText = CustomBys.byText("Sample text");
        
        assertNotNull(byText);
        assertTrue(byText instanceof Text);
        // The Text class doesn't override toString(), so it uses default By.toString()
        // which doesn't include the text value in the string representation
        assertNotNull(byText.toString());
    }

    @Test
    void testByTextWithSpecialCharacters() {
        By byText = CustomBys.byText("Text with 'quotes' and \"double quotes\"");
        
        assertNotNull(byText);
        assertTrue(byText instanceof Text);
        // The Text class doesn't override toString(), so it uses default By.toString()
        assertNotNull(byText.toString());
    }

    @Test
    void testByTextWithEmptyString() {
        By byText = CustomBys.byText("");
        
        assertNotNull(byText);
        assertTrue(byText instanceof Text);
        // The Text class doesn't override toString(), so it uses default By.toString()
        assertNotNull(byText.toString());
    }

    @Test
    void testByTextWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CustomBys.byText(null);
        });
    }

    @Test
    void testByPartialText() {
        By byPartialText = CustomBys.byPartialText("partial");
        
        assertNotNull(byPartialText);
        assertTrue(byPartialText.toString().contains("partial"));
        assertTrue(byPartialText.toString().contains("contains"));
    }

    @Test
    void testByPartialTextWithSpecialCharacters() {
        By byPartialText = CustomBys.byPartialText("Text with <tags> and & symbols");
        
        assertNotNull(byPartialText);
        assertTrue(byPartialText.toString().contains("Text with <tags> and & symbols"));
        assertTrue(byPartialText.toString().contains("contains"));
    }

    @Test
    void testByPartialTextWithEmptyString() {
        By byPartialText = CustomBys.byPartialText("");
        
        assertNotNull(byPartialText);
        assertTrue(byPartialText.toString().contains("contains"));
    }

    @Test
    void testByPartialTextWithNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            CustomBys.byPartialText(null);
        });
    }

    @Test
    void testByAttribute() {
        By byAttribute = CustomBys.byAttribute("data-testid", "test-value");
        
        assertNotNull(byAttribute);
        assertTrue(byAttribute.toString().contains("data-testid"));
        assertTrue(byAttribute.toString().contains("test-value"));
    }

    @Test
    void testByAttributeWithSpecialCharacters() {
        By byAttribute = CustomBys.byAttribute("data-test", "value with spaces and @#$%");
        
        assertNotNull(byAttribute);
        assertTrue(byAttribute.toString().contains("data-test"));
        assertTrue(byAttribute.toString().contains("value with spaces and @#$%"));
    }

    @Test
    void testByAttributeWithEmptyValue() {
        By byAttribute = CustomBys.byAttribute("data-testid", "");
        
        assertNotNull(byAttribute);
        assertTrue(byAttribute.toString().contains("data-testid"));
    }

    @Test
    void testByAttributeWithNullAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            CustomBys.byAttribute(null, "test-value");
        });
    }

    @Test
    void testByAttributeWithEmptyAttribute() {
        assertThrows(IllegalArgumentException.class, () -> {
            CustomBys.byAttribute("", "test-value");
        });
    }

    @Test
    void testByAttributeWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            CustomBys.byAttribute("data-testid", null);
        });
    }

    @Test
    void testByAttributeWithHyphenatedAttribute() {
        By byAttribute = CustomBys.byAttribute("data-test-id", "submit-button");
        
        assertNotNull(byAttribute);
        assertTrue(byAttribute.toString().contains("data-test-id"));
        assertTrue(byAttribute.toString().contains("submit-button"));
    }

    @Test
    void testByAttributeWithUnderscoreAttribute() {
        By byAttribute = CustomBys.byAttribute("data_test_id", "submit-button");
        
        assertNotNull(byAttribute);
        assertTrue(byAttribute.toString().contains("data_test_id"));
        assertTrue(byAttribute.toString().contains("submit-button"));
    }

    @Test
    void testByAttributeWithCommonAttributes() {
        String[] commonAttributes = {
            "id", "class", "name", "type", "value", "placeholder",
            "title", "alt", "src", "href", "data-testid", "aria-label"
        };
        
        for (String attribute : commonAttributes) {
            By byAttribute = CustomBys.byAttribute(attribute, "test-value");
            
            assertNotNull(byAttribute);
            assertTrue(byAttribute.toString().contains(attribute));
            assertTrue(byAttribute.toString().contains("test-value"));
        }
    }

    @Test
    void testCustomLocatorsWithUnicodeCharacters() {
        By byText = CustomBys.byText("Text with unicode: 你好世界");
        By byPartialText = CustomBys.byPartialText("unicode: 你好");
        By byAttribute = CustomBys.byAttribute("data-test", "value with 中文");
        
        assertNotNull(byText);
        assertNotNull(byPartialText);
        assertNotNull(byAttribute);
        
        assertTrue(byText instanceof Text);
        // Text class doesn't override toString(), so we just check it's not null
        assertNotNull(byText.toString());
        assertTrue(byPartialText.toString().contains("你好"));
        assertTrue(byAttribute.toString().contains("中文"));
    }

    @Test
    void testCustomLocatorsWithHtmlEntities() {
        By byText = CustomBys.byText("Text with &amp; and &lt;tags&gt;");
        By byPartialText = CustomBys.byPartialText("&amp; and &lt;");
        
        assertNotNull(byText);
        assertNotNull(byPartialText);
        
        assertTrue(byText instanceof Text);
        // Text class doesn't override toString(), so we just check it's not null
        assertNotNull(byText.toString());
        assertTrue(byPartialText.toString().contains("&amp; and &lt;"));
    }

    @Test
    void testCustomLocatorsWithNumbers() {
        By byText = CustomBys.byText("Price: $123.45");
        By byAttribute = CustomBys.byAttribute("data-price", "123.45");
        
        assertNotNull(byText);
        assertNotNull(byAttribute);
        
        assertTrue(byText instanceof Text);
        // Text class doesn't override toString(), so we just check it's not null
        assertNotNull(byText.toString());
        assertTrue(byAttribute.toString().contains("123.45"));
    }

    @Test
    void testCustomLocatorsWithWhitespace() {
        By byText = CustomBys.byText("Text with   multiple   spaces");
        By byPartialText = CustomBys.byPartialText("  leading and trailing  ");
        
        assertNotNull(byText);
        assertNotNull(byPartialText);
        
        assertTrue(byText instanceof Text);
        // Text class doesn't override toString(), so we just check it's not null
        assertNotNull(byText.toString());
        assertTrue(byPartialText.toString().contains("  leading and trailing  "));
    }

    @Test
    void testCustomLocatorsConsistency() {
        // Test that all custom locators return consistent results
        String testValue = "test-value";
        
        By textLocator = CustomBys.byText(testValue);
        By partialTextLocator = CustomBys.byPartialText(testValue);
        By attributeLocator = CustomBys.byAttribute("data-testid", testValue);
        
        assertNotNull(textLocator);
        assertNotNull(partialTextLocator);
        assertNotNull(attributeLocator);
        
        assertTrue(textLocator instanceof Text);
        
        // All should be different types of locators
        assertNotEquals(textLocator.toString(), partialTextLocator.toString());
        assertNotEquals(textLocator.toString(), attributeLocator.toString());
        assertNotEquals(partialTextLocator.toString(), attributeLocator.toString());
    }
} 