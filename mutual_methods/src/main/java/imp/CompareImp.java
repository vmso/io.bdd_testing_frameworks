package imp;

import com.thoughtworks.gauge.Step;
import exceptions.NullResponse;
import exceptions.NullValue;
import helper.ResponseBodyHelper;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompareImp  {

    private static final String ARE_N0T_EQUALS = "They aren't equals";
    private static final String EQUALS = "They are equals";
    private static final String CONTAINS = "%s isn't contains %s as String";

    private final Logger log = LogManager.getLogger(CompareImp.class);

    @Step({"Get <selector> from response and then compare with <value>, Are they not equals?",
            "Get value with <selector> from response and verify it isn't equal with <value>",
            "Yanıttan <selector> ile değer alın ve <value> ile eşit olmadığını doğrulayın"})
    @Then("Get {string} from response and then compare with {}, Are they not equals?")
    public void dataCompareNotEqualsFromResponse(String selector, Object value) throws NullResponse, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);
        Utils utils = new Utils();
        value = utils.parsSameType(value2, value);
        assertNotEquals(value2, value, ARE_N0T_EQUALS);
        log.info("{} and {} not equals",value,value2);

    }

    @Step({"Get <selector> from response and then compare with <value>, Are they equals?",
            "Get value with <selector> from response and verify it is equal with <value>",
            "Yanıttan <selector> ile değer alın ve <value> ile eşit olduğunu doğrulayın"})
    @Then("Get {string} from response and then compare with {}, Are they equals?")
    public void dataCompareEqualsFromResponse(String selector, Object value) throws NullResponse, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);
        Utils utils = new Utils();
        value = utils.parsSameType(value2, value);
        assertEquals(value, value2, EQUALS);
    }

    @Step("Get <selector> from response and then compare with <value>, is it contains the value?")
    @Then("Get {string} from response and then compare with {string}, is it contains the value?")
    public void dataCompareContainsFromResponse(String selector, String value) throws NullResponse, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        String value2 = String.valueOf(responseBodyHelper.getResponseElement(selector));
        assertTrue(value2.contains(value), CONTAINS);
    }

    @Step("Get <store key> list from response and then, compare list count with <count>, Are they equals?")
    @Then("Get {string} list from response and then compare list count with {int}, are they equals?")
    public void compareListCount(String storeKey, int count){
        var list = (List<?>) Utils.getFromStoreData(storeKey);
        assertEquals(count,list.size());
        //th[contains("Who Initiated Transfer")]/following-sibling::td/li/span
    }

}
