package options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        snippets = SnippetType.CAMELCASE,
        stepNotifications = true,
        glue={"imp","base","mutual_methods.imp"},
        plugin = {"html:reports/cucumber_reports/cucumber-html-report.html", "json:reports/cucumber_reports/cucumber.json", "pretty"},
        publish=true
)

public class TestRunner {

}
