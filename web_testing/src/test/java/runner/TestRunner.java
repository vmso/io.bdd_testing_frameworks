package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        stepNotifications = true,
        glue = {"imp", "base", "mutual_methods.imp"},
        plugin = {
                "pretty",
                "html:reports/cucumber-reports/cucumber-html-report.html",
                "json:reports/cucumber-reports/cucumber.json",
                "junit:reports/cucumber-reports/cucumber.xml",
                "rerun:reports/cucumber-reports/rerun.txt"
        },
        monochrome = true,
        dryRun = false,
        publish = true
)
public class TestRunner {
    // Modern Cucumber configuration for web testing
}
