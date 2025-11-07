package parallel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import org.apache.logging.log4j.Logger;
import com.qa.util.LoggerHelper;



public class Hooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;
	private static Scenario scenario;
	private static final Logger Log = LoggerHelper.getLogger();


	@Before(order = 0)
	public void beforeScenario(Scenario scenario){
		Hooks.scenario=scenario;
		Log.info("====== Starting Scenario: " + scenario.getName() + " ======");
	}

	public static Scenario getScenario(){
		return scenario;
	}

	@Before(order=1)
	public void getProperty() throws IOException {
//		configReader=new ConfigReader();
//		prop=configReader.init_prop();
		Log.info("Properties loading");
	}
	
	@Before(order=2)
	public void launchBrowser() throws IOException {
		//String browserName=prop.getProperty("browser");
		String browserName=ConfigReader.getProperty("browser");
		//String runMode =prop.getProperty("runMode");
		System.setProperty("browserstack.disable-automate-session", "true");

		//Get values from sys
		driverFactory=new DriverFactory();
		//driver=driverFactory.init_driver(browserName);
		driver=driverFactory.initializeDriver(browserName);
		//driver=driverFactory.initializeDriver(browserName,runMode);
		Log.info("Starting scenario on thread: " + Thread.currentThread().getId());
	}
	
	@After(order=0)
	public void quitBrowser() {
		Log.info("*** Ending scenario on thread: " + Thread.currentThread().getId());
		Log.info("*** Ending Scenario: " + scenario.getName());
		driver.quit();
	}
	
	@After(order=1)
	public void tearDown(Scenario scenario) {
		//boolean takeScreenshotForPassed=Boolean.parseBoolean(prop.getProperty("takeScreenshotForPassed"));
		boolean takeScreenshotForPassed=Boolean.parseBoolean(ConfigReader.getProperty("takeScreenshotForPassed"));

		// Do not take screenshot if scenario is skipped
		if (scenario.getStatus() == Status.SKIPPED) {
			Log.info("Scenario is skipped. Screenshot not taken.");
			return;
		}

		//if (scenario.isFailed()) {
		if (scenario.isFailed() || takeScreenshotForPassed) {

				// Create the "screenshots" directory if it doesn't exist
				File screenshotDir = new File("test-output/screenshots");
				if (!screenshotDir.exists()) {
					screenshotDir.mkdirs();
				}

				// Generate a timestamp for the screenshot name
				String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
				String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9 ]", ""); // Remove special characters except space
				Log.info("Scenario Name: "+scenarioName);
				String screenshotName = scenarioName.replaceAll(" ", "_") + "_" + timestamp + ".png";
				String screenshotPath = screenshotDir + "/" + screenshotName;

				// Capture screenshot
				byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

				// Save the screenshot to the specified path
				try {
					Path path = Paths.get(screenshotPath);
					Files.write(path, sourcePath);
				} catch (IOException e) {
					//e.printStackTrace();
					Log.error("Error while saving the screenshot: ", e);
				}

				// Attach the screenshot to the Cucumber report
				scenario.attach(sourcePath, "image/png", screenshotName);

				// Attach the screenshot to Extent Reports (if using Extent Reports)
				//ExtentCucumberAdapter.addTestStepLog("Scenario failed: " + scenario.getName());
				//ExtentCucumberAdapter.addTestStepLog("Screenshot: <a href='" + screenshotPath + "'><img src='" + screenshotPath + "' height='100' width='100'/></a>");
		}
	}

	//@AfterStep
	public void takeScreenshotAfterEachStep(Scenario scenario) {
		boolean takeScreenshotForEachStep = Boolean.parseBoolean(prop.getProperty("takeScreenshotForEachStep", "false"));

		if (takeScreenshotForEachStep) {
			// Create screenshots directory if it doesn’t exist
			File screenshotDir = new File("test-output/screenshots");
			if (!screenshotDir.exists()) {
				screenshotDir.mkdirs();
			}

			// Generate timestamp and step-based filename
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
			//String screenshotName = scenario.getName().replaceAll(" ", "_") + "_step_" + timestamp + ".png";
			String screenshotName = "step_" + timestamp + ".png";
			String screenshotPath = screenshotDir + "/" + screenshotName;

			try {
				byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				// Save locally
				Files.write(Paths.get(screenshotPath), screenshotBytes);
				// Attach to Cucumber report
				scenario.attach(screenshotBytes, "image/png", screenshotName);
				//Log.info("Screenshot captured for step: " + screenshotName);
			} catch (Exception e) {
				Log.error("Error capturing screenshot after step: ", e);
			}
		}
	}
}


	/*@After(order = 999) // Run this after all other hooks
	public void cleanUpEmbeddedFiles() {
		File outputDir = new File("test-output");
		if (outputDir.exists()) {
			// Delete all files starting with "embedded" and ending with ".png"
			File[] embeddedFiles = outputDir.listFiles((dir, name) -> name.startsWith("embedded") && name.endsWith(".png"));
			if (embeddedFiles != null) {
				for (File file : embeddedFiles) {
					file.delete();
				}
			}
		}
	}*/


