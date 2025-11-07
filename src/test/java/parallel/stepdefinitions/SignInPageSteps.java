package parallel.stepdefinitions;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.SignInPage;
import com.qa.factory.DriverFactory;
import com.qa.util.Constants;
import com.qa.util.ExcelReader;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelUtils;


//import static org.testng.Assert.assertTrue;
//import static org.testng.Assert.*;

public class SignInPageSteps {
	private static final Logger Log = LoggerHelper.getLogger();
	private SignInPage signInPage =new SignInPage(DriverFactory.getDriver());
	ExcelUtils excelUtils=new ExcelUtils();

	protected JavascriptExecutor jsExecutor;
	
	//@Given("the user opens URL {string}")
	@Given("the user opens URL")
	public void the_user_opens_url() {
		DriverFactory.getDriver().get(Constants.BASE_URL);

		Log.info("Navigate to URL: "+Constants.BASE_URL);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"URL: " + Constants.BASE_URL);
	}
	
	@When("user fills the form from given sheetname {string} and rownumber {int}")
	public void user_fills_the_form_from_given_sheetname_and_rownumber(String sheetName, Integer rowNumber) throws IOException {
	  
		// Initialize Excel utility to read from the file (update the path to your Excel file)
		ExcelReader excel = new ExcelReader("./src/test/resources/Login.xlsx", sheetName);

        // Get email and password from the specified row in the Excel sheet
        String email = excel.getCellData(rowNumber, 0);      // Assuming email is in the first column
        String password = excel.getCellData(rowNumber, 1);   // Assuming password is in the second column

		signInPage.enterEmailAddress(email);
		signInPage.enterPassword(password);
		
        // Close Excel file after reading
        excel.close();
        
	}
	
	@When("the user clicks on Sign In button")
	public void the_user_clicks_on_SignIn() throws InterruptedException {
		Log.info("Sign In Button clicked");
		signInPage.clickOnSignButtonHomePage();
		Log.info("Sign In Button clicked");
		//ExtentCucumberAdapter.addTestStepLog("--> Sign In Button clicked");
	}

	@Then("the My Account dialog is displayed")
	public void the_my_account_dialog_is_displayed() {
		Assert.assertTrue(signInPage.isMyAccountDlgDisplayed(),"My Account dlg NOT displayed");
	}

	@When("the user enters the email address {string} and password {string} in the My Account dialog")
	public void enter_email_address_and_password_in_my_account_dialog(String email, String password) {
		signInPage.enterEmailAddress(email);
		signInPage.enterPassword(password);
	}

	@When("the user clicks on Sign In button in the My Account dialog")
	public void click_on_sign_in_button_in_the_my_account_dialog() throws InterruptedException {
		Log.info("Click Sign In button in my account dialog");
		signInPage.clickOnSignInBtnMyAccountDlg();
	}
	
	@When("the user clicks on Sign In button for MyAccount")
	public void click_on_sign_in_for_MyAccount() throws InterruptedException {
		Log.info("Click Sign In button to go to MyAccount");
		signInPage.clickOnSignInBtnMyAccount();
	}
	
	@Then("the user verifies that Sign In is successful")
	public void verify_sign_in_is_successful() {
		Assert.assertTrue(signInPage.isLoginSuccessful(), "Sign in verification failed!");
		Log.info("Sign In is successful");
		Log.info("click home page logo after sign in");
		signInPage.clickHomePageLogo();
	}

	@Then("the user verifies pricing for store locator displayed")
	public void the_user_verifies_pricing_for_store_locator_displayed() {
		String zipcodeStoreLocator=signInPage.getPricingForZipCodeStoreLocator();
		Assert.assertTrue((!zipcodeStoreLocator.isEmpty()), "Sign in verification failed!");
		Log.info("Pricing for store locator: "+zipcodeStoreLocator+" displayed");
	}

	@Then("page Title should be {string}")
	public void page_title_should_be(String expTitleName) {
		String title= signInPage.getLoginPageTitle();
        Log.info("Title of the page: {}", title);
		Assert.assertEquals(title, expTitleName, "Title does not match!");
	}
	
	@When("the user clicks on log out link")
	public void the_user_clicks_on_log_out_link() {
		//loginpage.clickLogoutLink();
	}
	
	@Then("close browser")
	public void close_browser() {
	   
	}




	@Given("the user launches the hybris home page")
	public void the_user_launches_hybris_home_page() {

	}

	@Then("skip execution if the iteration status is {string} in workbook {string} on sheet {string} at row {int}")
	public void skip_execution_if_the_iteration_status_is_in_workbook_on_sheet_at_row(String status, String workBookName, String sheetName, Integer rowNumber) throws IOException {
		ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/"+workBookName+".xlsx");
		Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);
		String executionFlag=userData.getOrDefault("Execute", "");
		if(executionFlag.equalsIgnoreCase("No")){
			//excelReader.close();
			// Mark Cucumber scenario as skipped
			//Assume.assumeTrue(false);
			ExtentCucumberAdapter.addTestStepLog("Skipping current scenario execution because Execute = No in data sheet.");
			//throw new SkipException("Skipping current example row based on Execute column.");
			throw new SkipException("Skipping scenario execution because Execute is set to 'No' in Excel for Row: " + rowNumber);
		}

	}

	@Then("the user skips execution if the iteration status is {string} in sheet {string} at row {int}")
	public void skip_execution_if_the_iteration_status_is_in_sheet_at_row(String status, String sheetName, Integer rowNumber) throws IOException {

		// Split to extract Excel file and sheet name
		/*
		String[] excelNameParts = sheetName.split("-", 2);
		if (excelNameParts.length < 2) {
			throw new IllegalArgumentException("Invalid sheet format. Expected format: <ExcelFileName>-<SheetName>");
		}

		String excelWBName = excelNameParts[0].trim();
		String excelSheetName = excelNameParts[1].trim();

		ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/"+excelWBName+".xlsx");
		Map<String, String> userData = excelReader.getRowData(excelSheetName, rowNumber);
		String executionFlag=userData.getOrDefault("Execute", "");
		*/

		Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
		String executionFlag = rowData.getOrDefault("Execute", "").trim();

		if(executionFlag.equalsIgnoreCase("No")){
			// Mark Cucumber scenario as skipped
			//Assume.assumeTrue(false);
			ExtentCucumberAdapter.addTestStepLog("Skipping current scenario execution because Execute = "+status+" in data sheet.");
			throw new SkipException("Skipping scenario execution because Execute is set to 'No' in Excel for Row: " + rowNumber);
		}

	}


	@When("the user fills the sign in form using data from {string} at row {int}")
	public void the_user_fills_the_sign_in_form_using_data_from_at_row(String sheetName, int rowNumber) throws IOException {
		/*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Login.xlsx");
		//InputStream file = getClass().getClassLoader().getResourceAsStream("Login.xlsx");*/

		Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);

		String emailAddress = userData.getOrDefault("EmailAddress", "");
		String password = userData.getOrDefault("Password", "");
		signInPage.enterEmailAndPassword(emailAddress,password);
		//signInPage.enterEmailAndPassword(userData.get("EmailAddress"),userData.get("Password"));
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Email Address: " + emailAddress);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Password: " + password);

	}

	@When("the user fills the sign in form")
	public void fill_the_sign_in_form() {

		String emailAddress=Constants.SIGN_IN_EMAIL_ADDRESS;
		String password = Constants.SIGN_IN_PASSWORD;
	//	String otp=Constants.SIGN_IN_OTP;
		signInPage.enterEmailAndPassword(emailAddress,password);
		//signInPage.enterEmailAndPassword(userData.get("EmailAddress"),userData.get("Password"));
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Email Address: " + emailAddress);
		//ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Password: " + password);

	}

	@Then("the user should see the error message {string} on My Account Dialog")
	public void verifyErrorMessageOnMyAccountDialog(String expectedErrorMessage) {
		String actErrorMessage = signInPage.getErrorMessageDisplayedOnMyAccountDialog(expectedErrorMessage);
		/*Assert.assertEquals(actErrorMessage,expectedErrorMessage,
				"❌ Error Message: "+expectedErrorMessage+" NOT displayed");
        Log.info("✅ Error message: {} is displayed: ", expectedErrorMessage);*/

		Assert.assertEquals(actErrorMessage,expectedErrorMessage,
				"Error Message: "+expectedErrorMessage+" NOT displayed");
		Log.info("Error message: {} is displayed: ", expectedErrorMessage);

		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Expected error message: " + expectedErrorMessage);
	}

	@When("the user sign in sheet {string} at row {int}")
	public void fill_check_out_form_Paypal(String sheetName, int rowNumber) throws IOException, InterruptedException {
		Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
		String signIn = rowData.getOrDefault("SignIn", "");
		String username = rowData.getOrDefault("UserName", "");
		String password = rowData.getOrDefault("Password", "");
		System.out.println("UserName: "+username+" Password: "+password);
		if(signIn.equalsIgnoreCase("Yes")){
			signInPage.clickOnSignButtonHomePage();
			Assert.assertTrue(signInPage.isMyAccountDlgDisplayed(),"My Account dlg NOT displayed");
			signInPage.enterEmailAddress(username);
			signInPage.enterPassword(password);
			signInPage.clickOnSignInBtnMyAccountDlg();
			ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"UserName: " + username);
			ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Password: " + password);
		}else{
			Log.info("Guest user");
		}
	}

	@Then("the user able to click on RAC logo")
	public void the_user_able_to_click_on_rac_logo() {
		Log.info("click home page RAC logo after sign in");
		signInPage.clickHomePageLogo();
	}





}
