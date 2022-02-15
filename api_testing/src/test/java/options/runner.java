package options;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "classpath:features",
        plugin = {"html:reports/cucumber_reports/cucumber-html-report.html", "json:reports/cucumber_reports/cucumber.json", "pretty"}
        ,
        glue = {"imp","base","mutual_methods.imp"},
        monochrome = true

)
public class runner {


}
