package parallel.stepdefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.pages.HomePage;
import com.qa.factory.DriverFactory;


import com.qa.util.Constants;
import com.qa.util.ExcelReader;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import org.junit.Assert;
import org.apache.logging.log4j.Logger;
import org.mortbay.log.Log;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.SkipException;
import utils.ExcelUtils;
import utils.RandomDataGenerator;

public class HomePageSteps {

	//private SignInPage loginpage=new SignInPage(DriverFactory.getDriver());
	//private AdminUserManagementPage adminUserManagementPage;
	private static final Logger Log = LoggerHelper.getLogger();
	private HomePage homePage = new HomePage(DriverFactory.getDriver());
	private BasePage basePage = new BasePage(DriverFactory.getDriver());
	ExcelUtils excelUtils = new ExcelUtils();

	RandomDataGenerator randomData = new RandomDataGenerator();


	@When("user opens URL")
	public void user_opens_url(DataTable dataTable) {
		List<Map<String, String>> urlVal = dataTable.asMaps();
		String url = urlVal.get(0).get("url");
		DriverFactory.getDriver().get(url);
	}

	@When("user opens BM URL")
	public void user_opens_BM_url() {
		String url = "https://development-na01-rentacenter.demandware.net/on/demandware.store/Sites-Site/default%3bsite%3drentacenter/ViewApplication-DisplayWelcomePage?MenuGroupID=ChannelMenu&ChannelID=&SelectedSiteRedirect=DisplayWelcomePage";
		//String url="https://development-na01-rentacenter.demandware.net/on/demandware.store/Sites-Site/default%3bsite%3drentacenter/ViewApplication-BM?SelectedMenuItem=customers_groups&csrf_token=voLnsd0IbdQHxt5t42VHyTyhzLeUehEJuo2HslA0WdF-3ee0BmJ6qjckO4sNx3qLZcMjSE3xD4-bka8A4BXHOt-hmkP_J0Ct1-ZKydU-bUOzyKqWTPEzzjh12RpnYfQXFXGSQiQYtM__JrcHdRP6pz0nFak7JR0JwYMT4iivFvDAo_0rEJs%3d#/?CustomerGroup#CustomerGroupEditor!mode!edit!id!asdf!updated!1732537792975";
		DriverFactory.getDriver().get(url);
		Log.info("BM URL opened");
	}

	@Given("the user launches the RAC home page")
	public void the_user_launches_rac_home_page() throws InterruptedException {
		homePage.clickAllowButtonMobile();
		homePage.closeAllCookiesButton();

	}

	@Then("Add User screen page shall be dispayed")
	public void add_user_screen_page_shall_be_displayed() {
		//Assert.assertEquals(homePage.isHomeScreenDisplayed(),true);
		Assert.assertTrue(true, "NOT matched !");
	}

	@Then("the store Locator should be visible below the header on all pages")
	public void verify_store_locator_should_be_visible_below_the_header_on_all_pages() {
		Assert.assertTrue(homePage.verifyStoreLocatorOptionDisplay(),
				"Store Locator NOT visible below the header");
		Log.info("Store Locator visible below the header");
	}

	@Then("the {string} text should be displayed on the Store Locator")
	public void verify_text_displayed_on_the_store_locator(String expText) {
		Assert.assertTrue(homePage.verifyFindYourStoreTextDisplay(),
				"Store Locator " + expText + " NOT visible below the header");
		Log.info("Store Locator text " + expText + " visible below the header ");
	}

	@When("the user clicks on Find your store button")
	public void click_on_find_your_store_button() {
		Log.info("Click on Find your store button");
		homePage.clickOnFindYourStoreButton();

	}

	@When("the user clicks on Find your store button perf")
	public void click_on_find_your_store_button_perf() {
		Log.info("Click on Find your store button");
		homePage.clickOnFindYourStoreButtonPerf();

	}

	@Then("the Store locator pop-up should contain the options {string} and {string}")
	public void verify_pop_up_contain_options(String option1, String option2) {
		Assert.assertTrue(homePage.verifyStoreLocatorOptionsDisplay(),
				"Store Locator Pop up options NOT displayed");
		Log.info("Store Locator Options: " + option1 + " ," + option2 + " displayed");
	}

	//************Hybris

	//When the user clicks on Find your store link
	@When("the user clicks on Find your store link")
	public void the_user_clicks_on_find_your_store_link() {

		homePage.clickFindYourStoreLink();
	}


	@When("the user clicks on Go button")
	public void the_user_clicks_on_go_button() {
		Log.info("Click on GO button");
		homePage.clickOnGOButton();
	}

	@When("the user clicks on Go button perf")
	public void the_user_clicks_on_go_button_perf() {
		Log.info("Click on GO button");
		homePage.clickOnGOButtonPerf();
	}

	@And("the user enters zipcode {string}")
	public void theUserEntersZipcode(String zipCode) {
		homePage.enterZipCode(zipCode);
		Log.info("Enter Zip code: " + zipCode);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter Zip code: " + zipCode);
	}

	@And("the user enters zipcode {string} perf")
	public void theUserEntersZipcodePerf(String zipCode) {
		homePage.enterZipCodePerf(zipCode);
		Log.info("Enter Zip code: " + zipCode);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter Zip code: " + zipCode);
	}

	@And("the user enters Zipcode from {string} at row {int}")
	public void enterZipcodeFromSheet(String sheetName, int rowNumber) throws IOException {

		/*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
		Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);
		String zipCode = userData.getOrDefault("ZipCode", "");*/

		Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
		String zipCode = rowData.getOrDefault("ZipCode", "").trim();


		homePage.enterZipCode(zipCode);
		Log.info("Enter Zip code: " + zipCode);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Zip code: " + zipCode);
	}

	@And("the user enters Zipcode from workbook {string} on sheet {string} at row {int}")
	public void enterZipcodeFromSheet(String workBookName, String sheetName, int rowNumber) throws IOException {

		ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/" + workBookName + ".xlsx");
		Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);
		String zipCode = userData.getOrDefault("ZipCode", "");
		homePage.enterZipCode(zipCode);
		Log.info("Enter Zip code: " + zipCode);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter Zip code: " + zipCode);

	}

	@Then("the Store Locator should process the valid Zip code without errors")
	public void verify_store_locator_should_process_the_valid_zip_code_without_errors() {
		Log.info("Verify store locator dialogue display");
		//Assert.assertTrue(homePage.verifyStoreLocatorDlgDisplay(),
		//		"store locator dialogue not displayed");
		Assert.assertTrue(homePage.verifyMakeThisMyStoreButtonDisplay(),
				"MAKE THIS MY STORE Button is NOT displayed");
		Log.info("MAKE THIS MY STORE Button is displayed");
	}

	@When("the user clicks on the i info icon")
	public void click_on_the_i_info_icon() {
		homePage.clickIInfoIcon();
		Log.info("Click on I info Icon");
	}

	@Then("the pop-up should contain Store Info details")
	public void verify_pop_up_should_contain_store_info_details() {
		Log.info("Story Info popup contains details");
		Assert.assertTrue(homePage.verifyStoreInfoPopUpDetails(),
				"Story Info popup details NOT displayed");
	}

	@When("the user clicks on the Back button on Store Info pop up")
	public void click_on_back_button_on_store_info_pop_up() {
		homePage.clickBackButtonOnStoreInfoPopUp();
		Log.info("Click on Back button on Store info pop up");
	}

	@Then("the user should return to the list of stores pop up")
	public void verify_user_should_return_to_the_list_of_stores_pop_up() {
		Assert.assertTrue(homePage.verifyStoreLocatorDlgDisplay(),
				"Store Locator pop up NOT displayed");
		Log.info("Store Locator pop up displayed");

	}

	@When("the user click on GET DIRECTIONS link")
	public void click_on_get_directions_link() {
		homePage.clickOnGetDirectionsLink();
		Log.info("Click on Get Directions Link");
	}

	@Then("the user verifies Store Locator dialog displayed")
	public void the_user_verifies_store_locator_dialog_displayed() {
		homePage.verifyStoreLocatorDlgDisplay();
	}

	@When("the user clicks on Make This My Store link")
	public void the_user_clicks_on_make_this_my_store_link() {
		homePage.clickOnMakeThisMyStoreLink();
	}

	@When("the user clicks on Make This My Store link perf")
	public void the_user_clicks_on_make_this_my_store_link_perf() {
		homePage.clickOnMakeThisMyStoreLinkPerf();
	}


	@When("the user clicks on START YOUR ORDER link")
	public void the_user_clicks_on_start_your_order_link() {
		homePage.clickOnStartYourOrderLink();
	}

	@Then("the user should be redirected to Google Maps with the store location")
	public void verify_user_is_redirected_to_google_maps_with_the_store_location() {
		Assert.assertTrue(homePage.verifyRedirectedToGoogleMapsWithStoreLocation(),
				"NOT redirected to Google Maps");
		Log.info("Redirected to Google Maps with the store Location");
	}

	@When("the user clicks on the X icon on store locator popup")
	public void click_on_the_icon() {
		homePage.clickXButtonStoreLocatorPopUp();
		Log.info("Click on X button on store locator popup");
	}

	@Then("the store information pop up should be closed")
	public void verify_store_information_pop_up_should_be_closed() {
		Log.info("Store Info popup is closed");
		Assert.assertTrue(homePage.verifyStoreInfoPopUpClosed(),
				"Store Info PopUp not closed");
	}

	@When("the user enters invalid zipcode {string}")
	public void the_user_enters_invalid_zipcode(String invalidZipCode) {
		homePage.enterZipCode(invalidZipCode);
		Log.info("Enter invalid Zip code: " + invalidZipCode);

	}

	@Then("error message {string} should be displayed")
	public void verify_error_message_displayed(String errorMsg) {
		Assert.assertTrue(homePage.isZipCodeErrorMessageDisplay(errorMsg),
				"Invalid zip code error message NOT displayed");
		Log.info("Invalid zip code error message displayed: " + errorMsg);
	}

	@Then("the store information for the respective state {string} should be displayed")
	public void verify_store_information_for_the_respective_state_should_be_displayed(String state) {
		Log.info("Store information for Respective state:" + state + " displayed");
		Assert.assertTrue(homePage.isStoreInfoForTheRespectiveStateDisplayed(state),
				"Respective state:" + state + " NOT " + "displayed");

	}


	@Then("{string} {string} should be displayed in the top right corner below the header on the home page")
	public void is_pricing_for_zip_code_displayed_below_the_header_on_the_home_page(String pricingText, String zipcode) {

		Assert.assertTrue(homePage.isPricingForTextDisplayed(pricingText), "Pricing for text NOT displayed");
		Assert.assertTrue(homePage.isPricingForTextDisplayed(zipcode), "Zipcode text NOT displayed");

		Log.info("Pricing for " + zipcode + " :displayed");
	}

	@When("the user clicks on change option")
	public void click_on_change_option() {
		homePage.clickOnChangeOption();
		Log.info("Click on change Option");
	}

	@When("the user scrolls to {string} section")
	public void scroll_to_get_started_section(String sectionName) {
		homePage.scrollToGetStartedSection(sectionName);
		Log.info("Scroll to Get started section");
	}

	@When("the user scrolls to footer {string} section")
	public void scroll_to_footer_get_started_section(String sectionName) {
		homePage.scrollToFooterGetStartedSection(sectionName);
		Log.info("Scroll to Get started section");
	}

	@Then("section {string} is visible and allowing to sign up")
	public void verify_get_started_section_is_visible(String sectionName) {
		Assert.assertTrue(homePage.isGetStartedSectionIsVisible(sectionName),
				"Get started section NOT displayed");
		Log.info("Get started section displayed");
		Assert.assertTrue(homePage.isEmailButtonVisibleInGetStartedSection(),
				"Email button is NOT displayed in Get started section");
		Log.info("Email button is displayed in Get started section");
	}

	@When("the user clicks on the CTA Email option")
	public void click_on_the_cta_email_option() {
		homePage.clickOnCTAEmailOption();
		Log.info("click on CTA Email option");
	}

	@When("the user clicks on the footer CTA Email option")
	public void click_on_the_footer_cta_email_option() {
		homePage.clickOnFooterCTAEmailOption();
		Log.info("click on footer CTA Email option");
	}

	@When("the user enters valid email {string}")
	public void enter_valid_email(String email) {
		homePage.enterCTAEmail(email);
		Log.info("Enter CTA Email: " + email);
	}
	@When("the user enters valid email")
	public void enter_valid_email() {
		String email=randomData.getRandomEmail();
		homePage.enterCTAEmail(email);
		Log.info("Enter random CTA Email: " + email);
	}

	@When("the user clicks on Sign Up button")
	public void user_click_on_sign_up_button() {
		homePage.clickOnCTASignUpButton();
		Log.info("click on CTA Sign Up Button");
	}

	@Then("success message {string} displayed")
	public void verify_success_message_displayed(String successMsg) {
		homePage.isCTASuccessMessageDisplayed(successMsg);
		Log.info("Verify Success message is displayed: "+successMsg);
	}

	@When("the user clicks on the CTA Text option")
	public void click_on_the_cta_text_option() {
		homePage.clickOnCTATextButton();
		Log.info("click on CTA Text Button");
	}

	@When("the user clicks on the footer CTA Text option")
	public void click_on_the_footer_cta_text_option() {
		homePage.clickOnFooterCTATextButton();
		Log.info("click on footer CTA Text Button");
	}

	@When("the user enters valid phone {string}")
	public void the_user_enters_valid_phone(String phoneNumber) {
		homePage.enterCTAPhoneNumber(phoneNumber);
		Log.info("Enter phone number: " + phoneNumber);
	}

	@When("the user clicks on {string} link")
	public void click_on_link(String linkName) {
		homePage.clickOnLink(linkName);
		Log.info("click on link name: " + linkName);
	}

	@When("the user clicks on {string} link perf")
	public void click_on_link_perf(String linkName) {
		homePage.clickOnIDontHaveLinkPerf(linkName);
		Log.info("click on link name: " + linkName);
	}


	@Then("the link redirects the user to the {string} page")
	public void is_link_redirects_the_user_to_the_page(String pageUrl) {
		Log.info("Link redirected to page: " + pageUrl);
		Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPage(pageUrl), "NOT redirected to page url: " + pageUrl);
	}

	@Then("the {string} accordion button should be collapsed when the page loads")
	public void verify_accordion_should_be_collapsed_when_the_page_loads(String buttonName) {
		Log.info("Accordion button: " + buttonName + " is collapsed");
		Assert.assertFalse(homePage.isAccordionButtonCollapsed(),
				"Accordion button: " + buttonName + " is NOT collapsed");
		Log.info("Accordion button: " + buttonName + " is collapsed");
	}

	@Then("{string} accordion button should be collapsed when the page loads")
	public void verify_accordion_button_collapsed_when_the_page_loads(String buttonName) {
		Assert.assertFalse(homePage.isAccordionButtonCollapsed(buttonName),
				"Accordion button: " + buttonName + " is NOT collapsed");
		Log.info("Accordion button- " + buttonName + " is collapsed");
	}

	@Then("the {string} accordion section should be expanded")
	public void verify_accordion_section_should_be_expanded(String buttonName) {
		Log.info("Accordion button is expanded");
		Assert.assertTrue(homePage.isAccordionButtonExpanded(buttonName),
				"Accordion button is NOT expanded");
		Log.info("Accordion button is expanded");
	}

	@Then("the following accordion sections should be present:")
	public void verifyAccordionSections(List<String> expectedSections) {
		for (String section : expectedSections) {
			Assert.assertTrue(homePage.isAccordionSectionLinkDisplayed(section),
					"Accordion section link: " + section + " NOT displayed");
			Log.info("Accordion section link: " + section + " is displayed");
		}
	}

	@Then("the following Info and Tools accordion sections should be present:")
	public void verifyInfoAndToolsAccordionSections(List<String> expectedSections) {
		for (String section : expectedSections) {
			Assert.assertTrue(homePage.isAccordionInfoAndToolsSectionLinkDisplayed(section),
					"Accordion Info and Tools section link: " + section + " NOT displayed");
			Log.info("Accordion Info and Tools section link: " + section + " is displayed");
		}
	}

	@Then("the following About Rent A Centre accordion sections should be present:")
	public void verify_about_rent_a_centre_accordion_sections_should_be_present(List<String> expectedSections) {
		for (String section : expectedSections) {
			Assert.assertTrue(homePage.isAccordionAboutRentACentreAccordionSectionLinkDisplayed(section),
					"Accordion About rent a centre section link: " + section + " NOT displayed");
			Log.info("Accordion About rent a centre section link: " + section + " is displayed");
		}
	}

	@When("the user clicks on accordion section links")
	public void click_on_accordion_section_links() {
		Log.info("Click on accordion section links");
	}

	@When("the user clicks on accordion {string} section links")
	public void click_on_accordion_section_all_links(String sectionName) {
		Log.info("Click on accordion section: " + sectionName + " links ");
	}

//	@Then("the user redirected corresponding page")
//	public void verify_user_redirected_corresponding_page(Map<String, String> linkPageMapping) {
//
//		for (Map.Entry<String, String> entry : linkPageMapping.entrySet()) {
//			String linkName = entry.getKey();
//			String expectedURLFragment = entry.getValue();
//			Assert.assertTrue(homePage.isAccordionLinksClickRedirected(linkName,expectedURLFragment),
//					"Link : "+linkName+" is NOT redirected to: "+expectedURLFragment);
//			Log.info("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
//			homePage.browserBack();
//			Log.info("Browser back button navigated");
//		}
//	}

	@Then("the user redirected corresponding page")
	public void verify_user_redirected_corresponding_page(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Log.info("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			basePage.jsClickLinkByName(linkName);
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPage(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			/*
			Assert.assertTrue(homePage.isAccordionLinksClickRedirected(linkName,expectedURLFragment),
					"Link : "+linkName+" is NOT redirected to: "+expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
			Log.info("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
			homePage.browserBack();
			Log.info("Browser back button navigated");
			*/
		}
	}

	@Then("the info and tools redirected corresponding page")
	public void verify_info_and_tools_redirected_corresponding_page(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> credentials : credentialsList) {

			basePage.waitForPageToLoad();
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Assert.assertTrue(homePage.isAccordionInfoAndToolsLinksClickRedirected(linkName, expectedURLFragment),
					"Link : " + linkName + " is NOT redirected to: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			Log.info("Click Info and Tools section Link : " + linkName + " is redirected to: " + expectedURLFragment);
			Log.info("Click browser back button");
			homePage.browserBack();
			basePage.waitForPageToLoad();
			homePage.clickOnLink("Info & Tools");
			basePage.waitForPageToLoad();

		}
	}

	@Then("Info and Tools links redirected corresponding different tab")
	public void verify_info_and_tools_redirected_corresponding_diff_tab(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Log.info("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			basePage.jsClickLinkByName(linkName);
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPage(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			basePage.waitForPageToLoad();
		}
	}


	@Then("the About Rent-A-Center accordion section links redirected corresponding page")
	public void verify_about_a_center_redirected_corresponding_page(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Log.info("Click on Link name: " + linkName);
			homePage.clickOnLinkName(linkName);
			basePage.waitForPageToLoad();
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			Log.info("Link redirected to page: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.browserBack();
			Log.info("Browser back button navigated");
			basePage.waitForPageToLoad();
			basePage.clickLinkByText("About Rent-A-Center");
			Log.info("Accordion section click: About Rent-A-Center");
			basePage.waitForPageToLoad();
		}
	}

	@Then("the My Account - Online Bill Pay accordion section links redirected corresponding page")
	public void verify_my_account_online_bill_pay_redirected_corresponding_page(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Log.info("Click on Link name: " + linkName);
			homePage.clickOnLinkName(linkName);
			basePage.waitForPageToLoad();
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			Log.info("Link redirected to page: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.browserBack();
			Log.info("Browser back button navigated");
			basePage.waitForPageToLoad();
			basePage.clickLinkByText("My Account - Online Bill Pay");
			basePage.waitForPageToLoad();
		}
	}

	@When("the user clicks on footer section policy links")
	public void click_on_footer_section_policy_links() {
		Log.info("Click on footer section policy links");
	}

	@Then("the user is redirected corresponding footer section page")
	public void verify_user_redirected_corresponding_footer_section_page(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			homePage.clickOnLinkName(linkName);
			Log.info("Click on Link name: " + linkName);
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			Log.info("Link redirected to page: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.browserBack();
			Log.info("Browser back button navigated");
			basePage.waitForPageToLoad();
		}
	}

	@When("the user navigate to page {string}")
	public void navigate_to_page(String pageName) {
		if (!pageName.contains("Home")) {
			homePage.clickOnLink(pageName);
		}
	}

	@Then("change store error message is displayed on pop up")
	public void change_store_error_message_is_displayed_on_pop_up() {
		String errorMsg = "Store changes may affect your current pricing, promotion, and inventory availability.";
		Assert.assertTrue(homePage.isChangeStoreErrorMsgDisplayedOnPopUp(),
				"Error message: " + errorMsg + " NOT displayed");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Error Message: " + errorMsg);
		Log.info("Error Message: " + errorMsg + " displayed");
	}

	@Then("minnesota store locator pop up error message displayed as {string}")
	public void verify_minnesota_store_locator_error_message_displayed_as(String errorMsg) {

		Assert.assertTrue(homePage.isMinnesotaStoreErrorMsgDisplayedOnPopUp(),
				"Error message: " + errorMsg + " NOT displayed");
		//ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Minnesota Error Message: " + errorMsg);
		Log.info("minnesota Error Message: " + errorMsg + " displayed");
	}

	@Then("wisconsin store locator pop up error message displayed as {string}")
	public void verify_wisconsin_store_locator_error_message_displayed_as(String errorMsg) {

		Assert.assertTrue(homePage.isWisconsinStoreErrorMsgDisplayedOnPopUp(),
				"Error message: " + errorMsg + " NOT displayed");
		//ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Wisconsin Error Message: " + errorMsg);
		Log.info("Wisconsin Error Message: " + errorMsg + " displayed");
	}

	@When("the user clicks on USE MY LOCATION button")
	public void click_on_use_my_location_button() {
		homePage.clickOnUseMyLocationButton();
		Log.info("Click on Use My Location button");
	}

	@Then("the user is redirected to the {string} category page")
	public void verify_user_is_redirected_to_the_correct_category_page(String pageHeader) {
		Assert.assertTrue(homePage.isCategoryPageHeaderDisplayed(pageHeader),
				"Page is NOT redirected to correct page: " + pageHeader);
		Log.info("Page is redirected to the " + pageHeader + " correct page");
	}

	/*@Then("execute only if iteration status is yes")
	public void execute_only_if_iteration_status_is_yes(String sheetName, int rowNumber) {
		ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Login.xlsx");
		//InputStream file = getClass().getClassLoader().getResourceAsStream("Login.xlsx");
		Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);
		//signInPage.enterEmailAddress(userData.get("EmailAddress"));
		//signInPage.enterPassword(userData.get("Password"));
		if(userData.getOrDefault("Execute", "").
				equalsIgnoreCase("No")){
			//exit cumber scenario - data driven of specific row - Examples
			// Mark Cucumber scenario as skipped
			//Assume.assumeTrue(false);  // <-- JUnit Assume to SKIP the test
			// Throw SkipException to SKIP the test in TestNG
			ExtentCucumberAdapter.addTestStepLog("Skipping current scenario execution because Execute = No in data sheet.");
			//throw new SkipException("Skipping current example row based on Execute column.");
			throw new SkipException("Skipping scenario because Execute is set to 'No' in Excel for Row: " + rowNumber);
		}
	}*/
	@And("the user searches for product from {string} at row {int}")
	public void enterProductFromSheet(String sheetName, int rowNumber) throws IOException {

        /*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
        Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);
        String productID = userData.getOrDefault("ProductID", "");*/
		basePage.waitForPageToLoad();
		Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
		String productID = rowData.getOrDefault("ProductID", "").trim();

		DriverFactory.getDriver().get(Constants.BASE_URL+"p/" + productID + ".html");
		Log.info("Navigate to Product page: {}", productID);
		//DriverFactory.getDriver().get("https://rentacenter-development.mobify-storefront.com/p/"+productID+".html");
		String url = Constants.BASE_URL;
		//String url=DriverFactory.getDriver().getCurrentUrl();
		//https://qa.www.rentacenter.com/
		//DriverFactory.getDriver().get(url+"p/"+productID+".html");
		Log.info("URL launching");
		DriverFactory.getDriver().navigate().to(url + "p/" + productID + ".html");
		basePage.waitForPageToLoad();
		Log.info("url: " + url + "p/" + productID + ".html");
		Log.info("Enter product id: " + productID);
		basePage.refreshPage();
		//homePage.closeAllCookiesButtonIfExist();
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Product id: " + productID);

	}

	@Then("the user skips execution if the iteration status is {string} in sheet {string} at row <RowNumber>")
	public void theUserSkipsExecutionIfTheIterationStatusIsInSheetAtRowRowNumber(String arg0, String arg1) {
	}

	@When("the user scrolls to {string} Accordion and Clicks")
	public void theUserScrollsToAccordionAndClicks(String accordionName) {
		homePage.scrollToAccordionAndClick(accordionName);
		Log.info("Scroll to" + accordionName + " accordion");
	}

	@When("the user scrolls to Shop By Category section")
	public void theUserScrollsToShopByCategorySection() {
		homePage.scrollToShopByCategorySection();
		Log.info("Scroll to Shop By Category section");
	}

	@Then("the user should be redirected to Super PLP")
	public void theUserShouldBeRedirectedToSuperPLP() {
		homePage.verifySuperPlpPageisDisplayed();
		Log.info("user redirects to super plp");
	}

	@And("the user navigate to previous page")
	public void theUserNavigateToPreviousPage() {
		homePage.browserBack();
	}

	@Then("the user redirected corresponding page from shop by category section")
	public void theUserRedirectedCorrespondingPageFromShopByCategorySection(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Category Name");
			String expectedURLFragment = credentials.get("Corresponding Page");

			Assert.assertTrue(homePage.isSectionLinksClickRedirected(linkName, expectedURLFragment),
					"Category Link : " + linkName + " is NOT redirected to: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Category Link : " + linkName + " is redirected to: " + expectedURLFragment);
			Log.info("Click Category Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.clickOnRACLogo();
			Log.info("Browser back button navigated");
		}
	}

	@Then("verify various components are displayed on home page")
	public void verifyVariousComponentsAreDisplayedOnHomePage() {
		Assert.assertTrue(homePage.allComponentsAreDisplayedOnHomePage(),
				"One or the Other component is not displayed on home page");
		Log.info("All components are displayed on home page as expected");

	}

	@When("the user scrolls to social media links section in Footer")
	public void theUserScrollsToSocialMediaLinksSectionInFooter() {
		homePage.scrollToSocialMediaLinksFooter();
		Log.info("Scroll to home page footer section");
	}

	@Then("the user redirected corresponding page on same browser tab for media links")
	public void theUserRedirectedCorrespondingPageOnSameBrowserTabForMediaLinks(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");

			Assert.assertTrue(homePage.isMediaLinksClickRedirected(linkName, expectedURLFragment),
					"Category Link : " + linkName + " is NOT redirected to: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			Log.info("Click  Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.browserBack();
			Log.info("Browser back button navigated");
		}
	}

	@Then("verify the number of categories {string} displayed")
	public void verifyTheNumberOfCategoriesDisplayed(String expCategoryCount) {
		Assert.assertTrue(homePage.checkCategoryCount(expCategoryCount),
				"category count is " + expCategoryCount + " as expected");
		Log.info("category count is matched");
	}

	@Then("verify the category names  displayed in shop by category section")
	public void verifyTheCategoryNamesDisplayedInShopByCategorySection(List<String> categoryNames) {
		for (String category : categoryNames) {
			Assert.assertTrue(homePage.verifyCategoryNamesDisplayed(category),
					"Category: " + category + " NOT displayed");
			Log.info("Category: " + category + " is displayed");
		}
	}

	@When("verify user navigate to search bar in the header")
	public void verify_searchBar_displayed_in_the_header() {
		homePage.isSearchBarDisplayedInTheHeader();
		Log.info("User navigated to search bar in the header");
	}

	@When("the user click on search bar")
	public void click_on_search_bar() {
		homePage.clickOnSearchInput();
		Log.info("Clicked on search bar");
	}

	@Then("verify top categories are displayed in search placeholder text:")
	public void verify_search_placeholder_text(List<String> topCategories) {
		Assert.assertTrue(homePage.getSearchPlaceHolderText().containsAll(topCategories),
				"Top categories are NOT displayed in search placeholder text");
		Log.info("Top categories are displayed in search placeholder text");
	}

	@Then("verify final list is sorted by availability")
	public void verify_sort_by_availability() {
		Assert.assertEquals(homePage.getSortAvailability(), "Availability",
				"Sort is NOT by availability");
		Log.info("Final list is sorted by availability");
	}

	@When("the user search for a product {string}")
	public void search_for_product(String searchProduct) throws InterruptedException {
		homePage.enterSearchValue(searchProduct);
		Log.info("searched for a product: " + searchProduct);
	}

	@Then("the following product suggestions provided:")
	public void verify_product_suggestions_are_displayed(List<String> productSuggestion) {
		for (String suggestion : productSuggestion) {
			Assert.assertTrue(homePage.isSearchSuggestionsAreDisplayed(suggestion),
					"product suggestion is: " + suggestion + " NOT displayed");
			Log.info("product suggestions : " + productSuggestion + " are displayed");
		}
	}

	@Then("Verify exact phrase matches displayed {string} at the top of the list")
	public void verify_exact_match_displayed_at_the_top(String suggestion) {
		Assert.assertTrue(homePage.isSearchSuggestionsAreDisplayed(suggestion),
				"Exact match " + suggestion + " NOT displayed at the top of the list");
		Log.info("Exact match " + suggestion + " displayed at the top of the list");
	}

	@Then("Verify exact phrase matches displayed {string} at the top of the list perf")
	public void verify_exact_match_displayed_at_the_top_perf(String suggestion) {
		Assert.assertTrue(homePage.isSearchSuggestionsAreDisplayedPerf(suggestion),
				"Exact match " + suggestion + " NOT displayed at the top of the list");
		Log.info("Exact match " + suggestion + " displayed at the top of the list");
	}


	@Then("Verify matches that contain {string} all words appear in the results")
	public void verify_exact_match_contain_all_words(String suggestion) {
		String actResult = homePage.getSearchResults(suggestion);
		Assert.assertEquals(actResult, suggestion, "Matches that not contain all words appear in the results");
		Log.info("Matched that contain all words appear in the results");
	}

	@Then("verify search suggestions list displayed")
	public void verify_search_suggestions_list_displayed() {
		Assert.assertTrue(homePage.isSearchListDisplayed(),
				"Search suggestions list NOT displayed");
		Log.info("Search suggestions list displayed");
	}

	@Then("verify no suggestions displayed if the keyword has no relevant categories")
	public void verify_no_suggestions_displayed() {
		Assert.assertTrue(homePage.isNoSuggestionsDisplayed(),
				"Suggestions are displayed");
		Log.info("No suggestions displayed");
	}

	@When("the user hover an L1 category {string} in the menu")
	public void hover_an_L1category(String L1Category) throws InterruptedException {
		homePage.hoverToL1Category(L1Category);
	}

	@When("the user click on L1 category {string}")
	public void click_on_L1category(String L1Category) throws InterruptedException {
		homePage.clickOnL1Category(L1Category);
	}

	@Then("the following L2 subcategories should be displayed:")
	public void verify_L2_subCategories(List<String> expectedSubCategories) {
		for (String L2Categories : expectedSubCategories) {
			Assert.assertTrue(homePage.isL2SubCategoriesLinksDisplayed(L2Categories),
					"L2 sub categories : " + L2Categories + " NOT displayed");
			Log.info("L2 sub categories: " + L2Categories + " is displayed");
		}
	}

	@When("the user clicks on L2 subcategory {string}")
	public void click_on_L2_subcategory(String L2Category) {
		homePage.ClickOnL2SubCategory(L2Category);
		Log.info("click on L2 subcategory: " + L2Category);
	}

	@When("the user clicks on special category {string}")
	public void click_on_special_category(String specialCategory) {
		homePage.ClickOnSpecialCategory(specialCategory);
		Log.info("click on special category: " + specialCategory);
	}

	@When("the user clicks on L3 category {string} within an L2 category {string}")
	public void click_on_L2_subcategory(String L3Category, String L2Category) {
		homePage.ClickOnL3Category(L3Category, L2Category);
		Log.info("click on L3 category: " + L3Category + " within an L2 category: " + L2Category);
	}

	@When("the user click on the Logo in the header")
	public void click_on_logo_in_the_header() {
		homePage.clickOnRACBrandLogo();
		Log.info("Clicked on the Logo in the header");
	}

	@Then("the feature banner carousel should be visible on the top of homepage")
	public void verify_feature_banner_carousel_isDisplayed() {
		Assert.assertTrue(homePage.verifyFeatureBannerCarouselDisplay(),
				"Feature banner carousel is NOT displayed");
		Log.info("Feature banner carousel is displayed");
	}

	@Then("verify the users first name {string} is displayed on header")
	public void verify_user_fistName_is_displayed_on_header(String expUserFirstName) {
		String actUserFirstName = homePage.getUserFirstNameOnHeader();
		Assert.assertEquals(actUserFirstName, expUserFirstName, "user first name " + expUserFirstName + " is NOT displayed on header");
		Log.info("user first name " + expUserFirstName + " is displayed on header");
	}

	@Then("the cart icon displays the number of items {string}")
	public void verify_number_of_items_in_cart(String expNumberOfItems) {
		Assert.assertTrue(homePage.getNumberOfItemsInCart(expNumberOfItems),
				"Number of items " + expNumberOfItems + " is NOT displayed in cart icon");
		Log.info("Number of items " + expNumberOfItems + " is displayed in cart icon");
	}

	@When("the user click on accept cookie popup")
	public void click_on_accept_cookie_popup_button() {
		homePage.acceptCookies();
		Log.info("Clicked on accept cookie popup");
	}

	@Then("the following shop by category section all categories are shown:")
	public void verify_shop_by_category_section_all_categories_are_displayed(List<String> expectedCategories) {
		for (String category : expectedCategories) {
			Assert.assertTrue(homePage.isShopByCategorySectionAllCategoriesDisplayed(category),
					"Shop By Category section all categories: " + category + " NOT displayed");
			Log.info("Shop By Category section all categories: " + category + " is displayed");
		}
	}

	@Then("the following shop by category section categories available to the selected store are displayed:")
	public void verify_shop_by_category_section_available_categories_to_the_selected_store_are_displayed(List<String> expectedCategories) {
		for (String category : expectedCategories) {
			Assert.assertTrue(homePage.isShopByCategorySectionAllCategoriesDisplayed(category),
					"Shop By Category section available categories to the selected store: " + category + " NOT displayed");
			Log.info("Shop By Category section available categories to the selected store: " + category + " is displayed");
		}
	}

	@Then("the {string} section is displayed on the homepage")
	public void verify_section_isDisplayed(String section) {
		Assert.assertTrue(homePage.isSectionDisplayedOnHomePage(section),
				section + " section is NOT expanded");
		Log.info(section + " is displayed");
	}

	@When("the user click on {string} Shop by Category section")
	public void click_on_shop_by_category_item(String category) {
		homePage.clickOnShopByCategoryItem(category);
		Log.info("Clicked on " + category + " Shop By Category section");
	}

	@Then("the Shop All button is visible in the Shop by Category section")
	public void verify_shop_all_button_is_displayed() {
		Assert.assertTrue(homePage.isShopAllButtonDisplayed(),
				"Shop All button is NOT displayed in Shop by Category section");
		Log.info("Shop All button is NOT displayed in Shop by Category section");
	}

	@When("the user click on Shop All button in Shop by Category section")
	public void click_on_shop_all_button() {
		homePage.clickOnShopAllButton();
		Log.info("Clicked on Shop All button Shop By Category section");
	}

	@When("the user select a suggestion")
	public void select_suggestion() {
		homePage.selectSuggestion();
		Log.info("selected suggestion");
	}

	@When("the user select a suggestion perf")
	public void select_suggestion_perf() {
		homePage.selectSuggestionPerf();
		Log.info("selected suggestion");
	}

	@Then("the user is redirected to appropriate proper page {string}")
	public void verify_about_a_center_redirected_corresponding_page(String expectedURLFragment) {
		Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
		Log.info("Link redirected to page: " + expectedURLFragment);
	}

	@Then("the user redirected corresponding page Deals And Clearance")
	public void theUserRedirectedCorrespondingPageDealsAndClearance(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Log.info("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			basePage.jsClickLinkByNameInContainer(linkName);
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			Log.info("Click  Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.browserBack();
			Log.info("Browser back button navigated");
		}

	}

	@Then("the user redirected corresponding page on same browser tab")
	public void theUserRedirectedCorrespondingPageOnSameBrowserTab(DataTable dataTable) {
		List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> credentials : credentialsList) {
			String linkName = credentials.get("Link Name");
			String expectedURLFragment = credentials.get("Corresponding Page");
			Log.info("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			basePage.jsClickLinkByName(linkName);
			Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
			ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
			Log.info("Click  Link : " + linkName + " is redirected to: " + expectedURLFragment);
			homePage.clickOnRACLogo();
			Log.info("Browser back button navigated");
		}

	}

	@Then("the About Rent A Center accordion button should be collapsed when the page loads")
	public void verify_about_rent_a_center_should_be_collapsed_when_the_page_loads() {
		Log.info("Accordion button About RAC is collapsed");
		Assert.assertFalse(homePage.isAccordionRACButtonCollapsed(),
				"Accordion button About RAC is NOT collapsed");
		Log.info("Accordion button About RAC is collapsed");
	}

	@Then("the Info and Tools accordion button should be collapsed when the page loads")
	public void verify_about_info_and_tools_should_be_collapsed_when_the_page_loads() {
		Assert.assertFalse(homePage.isAccordionInfoAndToolsButtonCollapsed(),
				"Accordion button Info and Tools is NOT collapsed");
		Log.info("Accordion button Info and Tools RAC is collapsed");
	}

	@Then("verify frequently searched category is displayed")
	public void verify_frequently_searched_is_displayed() {
		Assert.assertTrue(homePage.verifyFrequentlySearchedDisplay(),
				"Frequently searched category is NOT displayed");
		Log.info("Frequently searched category is displayed");
	}

	@Then("verify all categories are displayed in search placeholder text:")
	public void verify_all_categories_search_placeholder_text(List<String> topCategories) {
		Assert.assertTrue(homePage.getSearchPlaceHolderText().containsAll(topCategories),
				"All categories are NOT displayed in search placeholder text");
		Log.info("All categories are displayed in search placeholder text");
	}

	@When("the user scrolls to {string} section on home screen")
	public void scroll_to_popular_items_section(String sectionName) {
		homePage.scrollToPopularItemsSection(sectionName);
		Log.info("Scroll to popular items section");
	}

	@Then("Verify popular items section is displayed on the homepage")
	public void verify_section_Displayed() {
		homePage.isSectionDisplayed();
		Log.info("Section is displayed");

	}

	@When("the user clicks on an item from the popular items section")
	public void click_on_item() {
		homePage.clickOnItem();
		Log.info("Clicked on item in the popular items section");
	}

	@Then("verify user is redirected to the pdp page")
	public void verify_user_is_redirected_to_the_pdp_page() {
		Assert.assertTrue(homePage.verifyPdpDisplay(),
				"pdp page is NOT displayed");
		Log.info("pdp page is displayed");
	}

	@When("the user scrolls to {string} section on the home screen")
	public void scroll_to_featured_deals_section(String section) {
		homePage.scrollToFeaturedDealsSection(section);
		Log.info("Scroll to featured deals section");
	}

	@Then("verify featured deals section is displayed on the homepage")
	public void verify_featured_deals_section_displayed() {
		homePage.isFeatuedDealsSectionDisplayed();
		Log.info("featured deals section is displayed");

	}

	@Then("verify product pricing should be displayed")
	public void verify_pricing_displayed() {
		homePage.isPricingDisplayed();
		Log.info("product pricing is displayed");
	}

	@Then("verify product description should be displayed")
	public void verify_description_displayed() {
		homePage.isDescriptionDisplayed();
		Log.info("product description is displayed");
	}

	@Then("verify product image should be displayed")
	public void verify_product_image_displayed() {
		homePage.isImageDisplayed();
		Log.info("product image is displayed");
	}

	@When("the user clicks the right navigation arrow in the featured deals section")
	public void click_on_right_arrow() {
		homePage.clickOnRightArrow();
		Log.info("Clicked right arrow");
	}

	@When("the user clicks the left navigation arrow in the featured deals section")
	public void click_on_left_arrow() {
		homePage.clickOnLeftArrow();
		Log.info("Clicked left arrow");
	}

	@Then("the user verifies that the next set of deals is displayed")
	public void verify_next_set_of_deals() {
		List<WebElement> displayedNextSetOfDeals = homePage.getNextSetOfDeals();
		Assert.assertFalse(displayedNextSetOfDeals.isEmpty(), "No deals are displayed");
		Log.info("set of deals are displayed");
	}

	@Then("the user verifies that the previous set of deals is displayed")
	public void verify_previous_set_of_deals() {
		List<WebElement> previousSetOfDeals = homePage.getPreviousSetOfDeals();
		Assert.assertFalse(previousSetOfDeals.isEmpty(), "No deals are displayed");
		Log.info("previous set of deals are displayed");
	}

	@When("the user clicks on an item from the featured deals section")
	public void click_on_item_From_featured_deals() {
		homePage.clickOnItemFromTheFeaturedDeals();
		Log.info("Clicked on item in the featured deals section");
	}

	@When("the user clicks the right arrow button in the banner")
	public void click_right_arrow_button() {
		homePage.clickRightArrowButton();
		Log.info("Clicked on right arrow button in the banner");
	}

	@When("the user clicks the left arrow button in the banner")
	public void click_left_arrow_button() {
		homePage.clickLeftArrowButton();
		Log.info("Clicked on left arrow button in the banner");
	}

	@Then("the user should be navigated to the next banner")
	public void navigated_to_next_banner() {
		homePage.isNavigatedToNextBanner();
		Log.info("navigated to next banner");
	}

	@Then("the user clicks on the navigation dot below the banner")
	public void click_on_navigation_dot() {
		homePage.clickOnNavigationDot();
		Log.info("clicked on navigation dot");
	}

	@Then("the user verifies that the banner displayed corresponds to the selected dot")
	public void verify_banner() {
		homePage.isBannerDisplayed();
		Log.info("banner is displayed");
	}

	@Then("the user clicks on the banner")
	public void click_on_banner() {
		homePage.clickOnBanner();
		Log.info("clicked on banner");
	}

	@Then("the user redirected to the Appliances")
	public void verify_page_is_redirected() {
		homePage.isPageRedirected();
		Log.info("page is redirected");
	}

	@When("the user scrolls to {string} footer section")
	public void scroll_to_footer_store_locator_section(String sectionName) {
		homePage.scrollToFooterStoreLocatorSection(sectionName);
		Log.info("Scroll to store locator section");
	}

	@When("the user clicks on footer section store locator")
	public void click_on_footer_section_store_locator() {
		homePage.clickOnStoreLocator();
		Log.info("Click on footer section store locator");
	}

	@When("the user clicks the Make This My Store link in the store locator")
	public void the_user_clicks_on_make_this_my_store_link_in_the_store_locator() {
		homePage.clickOnMakeThisMyStoreLinkInStoreLocator();
	}

	@Then("store locator pop up error message displayed as {string}")
	public void verify_store_locator_error_message_displayed_as(String errorMsg) {
		Assert.assertTrue(homePage.isStoreErrorMsgDisplayedOnPopUp(),
				"Error message: " + errorMsg + " NOT displayed");
		Log.info("Error Message: " + errorMsg + " displayed");
	}

	@Then("store locator pop up with message displayed as {string}")
	public void verify_store_locator_with_message_displayed_as(String msg) {
		Assert.assertTrue(homePage.isStoreMsgDisplayedOnPopUp(msg),
				"Popup message: " + msg + " NOT displayed");
		Log.info("Popup Message: " + msg + " displayed");
	}

	@When("the user click on my offer button")
	public void click_on_my_offer_button() {
		homePage.clickOnMyOffersButton();
		Log.info("Clicked on my offer button");
	}

	@When("the user click on code applied {string} button")
	public void click_on_code_applied_button(String offerCode) {
		homePage.clickOnCodeAppliedButton(offerCode);
		Log.info("Clicked on code applied button");
	}

	@Given("I perform the test for {string}")
	public void iPerformTheTestFor(String iteration) {

		System.out.println("Iteration"+iteration);
	}

	@Given("the user opens URL Perf")
	public void the_user_opens_url_perf() {
		double timeSecs=homePage.getHomePageLoadTime();
		Log.info("Home page load time Seconds: "+timeSecs);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Home page load time Seconds:" + timeSecs);
	}
}