package Schedule;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumberReport.html","json:target/report.json"},
        features = "src/test/resources/features",
        glue = "step_definition",
        tags = "@createScheduleTest",
        dryRun = true
)




public class CucumberScheduleRun {
}
