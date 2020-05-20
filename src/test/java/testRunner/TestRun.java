package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".//Features/",
        glue = "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty",
                "html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml"},
        tags ={"@sanity"}
        )
public class TestRun {
}

/* How to define multiple feature file

 features = ".//Features/Customers.feature",
 features = {".//Features/Login.feature",".//Features/Customers.feature"},
 features = ".//Features" // all features will be executed
        glue = "stepDefinitions", // all step definitions files will be executed
        dryRun = false, //true - to check stepdefinition matches feature scenarios
        monochrome = true,
        plugin = {"pretty",
                "html:test-output"},
       tags={"@sanity"} //only sanity will be executed
       tags ={"@sanity,@regression"} //the scenario which has sanity and the scenario has regression both will be executed
       tags ={"@sanity","@regression"} //the scenario with has both sanity and regression tags executed
 */