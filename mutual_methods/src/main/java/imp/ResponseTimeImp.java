package imp;

import com.thoughtworks.gauge.Step;
import exceptions.NullResponse;
import helper.ResponseTimeHelper;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opentest4j.AssertionFailedError;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseTimeImp extends ResponseTimeHelper {
    private final Logger log = LogManager.getLogger(ResponseTimeImp.class);

    @Step({"Get response time as milliseconds and compare it, is it less then <milliseconds>?",
            "Response zamanını getir ve karşılaştır, <milli saniye> milisaniyeden düşük mü?'"})
    public void checkResponseTimeAsMillis(Integer milliSeconds) throws NullResponse {
        Long responseTime = getRequestTimeInMillis();

        try {
            assertTrue(milliSeconds > responseTime);
            log.info("Response time is less then {} milliseconds", milliSeconds);
        } catch (AssertionFailedError e) {
            log.error("Response time isn't less then {} milliseconds", milliSeconds);
            throw e;
        }
    }

    @Step({"Get response time as seconds and compare it, is it less then <seconds>?",
            "Response zamanını getir ve karşılaştır, <saniye> saniyeden düşük mü?'"})
    public void checkResponseTimeAsSeconds(Integer seconds) throws NullResponse {
        Long responseTime = getRequestTimeInSecond();

        try {
            assertTrue(seconds > responseTime);
            log.info("Response time is less then {} seconds", seconds);
        } catch (AssertionFailedError e) {
            log.error("Response time isn't less then {} seconds", seconds);
            throw e;
        }
    }
}
