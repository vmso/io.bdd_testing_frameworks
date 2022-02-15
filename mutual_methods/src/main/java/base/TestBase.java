package base;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.ExecutionContext;
import configuration.Configuration;
import helper.SlackHelper;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestBase {

    private final SlackHelper slackHelper;

    public TestBase() {
        var slackMessageString = Configuration.getInstance().getStringValueOfProp("slack_message");
        var slackMessage = Boolean.parseBoolean(slackMessageString);
        slackHelper = new SlackHelper(slackMessage);
    }

    @BeforeSuite
    public void beforeSuit() {
        SlackHelper.setStartDate();
    }

    @AfterSuite
    public void afterSuit() {
        slackHelper.sendSlackMessage();
    }

    @AfterScenario
    public void afterScenario(ExecutionContext context) {
        slackHelper.updateStatus(context);
    }

}
