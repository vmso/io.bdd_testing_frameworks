package options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"pretty",
                "json:target/cucumber.json",
                "progress:target/report"
        },
        glue = "com.StepDefinitions",
        monochrome = true
)
public class Runner {


}
