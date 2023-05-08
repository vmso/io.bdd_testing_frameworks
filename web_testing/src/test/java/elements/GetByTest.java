package elements;

import base.GetFileName;
import driver.DriverManager;
import json.UIProjectJsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static elements.CustomBys.byText;

import java.util.HashMap;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class GetByTest {
    private UIProjectJsonReader mockedJsonReader;
    private GetBy getByMocked;

    @BeforeEach
    public void init() {
        GetFileName.getInstance().setFileName("examples");
        mockedJsonReader = Mockito.mock(UIProjectJsonReader.class);
        getByMocked = Mockito.mock(GetBy.class);
        when(getByMocked.getByValue(anyString())).thenCallRealMethod();
    }

    @Test
    void relativeNearTest() {
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

        when(mockedJsonReader.getJsonAsMapStringObject(anyString(), anyString())).thenReturn(relativeLocator);

        var added = getByMocked.getByValue("test_btn");
    }

}