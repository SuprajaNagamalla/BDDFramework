package testrunners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
//@RunWith(Parallelized.class)
@CucumberOptions(
		
		//features="src/test/resources/features",
		features="src/test/resources/parallel",
		//features="Features",
		//glue={"stepdefinitions","AppHooks"},
		glue={"parallel"},
		dryRun=false,
		monochrome=true,
		//plugin= {"pretty","html:test-output"}
		// plugin = {"json:target/cucumber/report.json", "html:target/cucumber/report.html"} //working		
		// plugin = {"json:target/test-results/cucumber.json", "html:target/test-results/cucumber.html"}
		plugin = {
				"pretty",
				"html:test-output/CucumberReport/cucumber-report.html",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"timeline:test-output-thread/",
				},
		//tags= "@sanity or @loginFailed"  //working fine
		//tags= "@sanity, @loginFailed"  //not correct declaration 
		tags= "@regression"
		
	)

public class MyTestRunner_NotInUse {

}
