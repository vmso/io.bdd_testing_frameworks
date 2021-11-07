package imp;

import com.thoughtworks.gauge.Step;
import exceptions.NullResponse;
import exceptions.NullValue;
import exceptions.WrongFormatException;
import helper.ResponseBodyHelper;
import io.cucumber.java.en.Then;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class CompareImp  {

    private static final String ARE_N0T_EQUALS = "They aren't equals";
    private static final String EQUALS = "They are equals";
    private static final String CONTAINS = "%s isn't contains %s as String";

    @Step({"Get <selector> from response and then compare with <value>, Are they not equals?",
            "Get value with <selector> from response and verify it isn't equal with <value>",
            "Yanıttan <selector> ile değer alın ve <value> ile eşit olmadığını doğrulayın"})
    @Then("Get {string} from response and then compare with {}, Are they not equals?")
    public void dataCompareNotEqualsFromResponse(String selector, Object value) throws NullResponse, WrongFormatException, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);
        Utils utils = new Utils();
        value = utils.parsSameType(value2, value);
        assertNotEquals(value2, value, EQUALS);
    }

    @Step({"Get <selector> from response and then compare with <value>, Are they equals?",
            "Get value with <selector> from response and verify it is equal with <value>",
            "Yanıttan <selector> ile değer alın ve <value> ile eşit olduğunu doğrulayın"})
    @Then("Get {string} from response and then compare with {}, Are they equals?")
    public void dataCompareEqualsFromResponse(String selector, Object value) throws NullResponse, WrongFormatException, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);
        Utils utils = new Utils();
        value = utils.parsSameType(value2, value);
        assertEquals(value, value2, EQUALS);
    }

}
