package elements;

import base.GetFileName;
import json.UIProjectJsonReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

import java.util.HashMap;

import static org.mockito.Mockito.*;

class GetByTest {
    private UIProjectJsonReader mockedJsonReader;
    private GetBy getByMocked;
    private Logger log;

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