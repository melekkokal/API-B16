package Homeworks.Homework1.runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features/Slackk.feature",
        glue="SlackHomework",
        dryRun = false,
        plugin = {"pretty", "html:target/uiReport.html", "rerun:target/uiFailedTest.txt"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class Runner {

}
