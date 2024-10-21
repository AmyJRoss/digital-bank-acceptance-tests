//package co.wedevx.digitalbank.automation.ui.runners;
//
//import io.cucumber.junit.CucumberOptions;
//import io.cucumber.junit.Cucumber;
//import org.junit.runner.RunWith;
//
//@RunWith(Cucumber.class)
//@CucumberOptions(
//        features = "src/test/resources/ui/features",      // Path to your feature files
//        glue = {"co.wedevx.digitalbank.automation.ui.steps"}, // Package containing step definitions
//        tags = "@Smoke",                               // Run only scenarios tagged with @smoke
//        plugin = {
//                "pretty",                                  // Pretty format for console output
//                "html:target/cucumber-reports/smoke-report.html",  // HTML report
//                "json:target/cucumber-reports/smoke-report.json"   // JSON report
//        },
//        monochrome = true,                             // Makes console output more readable
//        dryRun = false                                 // Set to true to check mapping between feature files and step definitions
//
//)
//public class SmokeRunner {
//}