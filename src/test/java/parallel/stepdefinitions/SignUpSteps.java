package parallel.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.pages.HomePage;
import com.pages.SignUpPage;
import com.qa.factory.DriverFactory;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utils.RandomDataGenerator;

import java.util.List;
import java.util.Map;

public class SignUpSteps {
    RandomDataGenerator randomData = new RandomDataGenerator();
    SignUpPage signup = new SignUpPage(DriverFactory.getDriver());
    private static final Logger Log = LoggerHelper.getLogger();
    private HomePage homePage =new HomePage(DriverFactory.getDriver());
    private BasePage basePage =new BasePage(DriverFactory.getDriver());

    private static final String invalidEmail = "user@com";
    private static final String inValidMobile = "123";
    private static final String validMobile = "3136391801";


    @Then("signup text is displayed")
    public void signup_text_is_displayed() {
        Assert.assertTrue(signup.signupTextIsDisplayed());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "signup text is displayed");
    }

    @Then("required text and secure form is displayed")
    public void required_text_and_secure_form_is_displayed() {
        Assert.assertTrue(signup.requiredTextAndSecureFormIsDisplayed());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "required text and secure form is displayed");
    }

    @Then("email address text is displayed with blank input field")
    public void email_address_text_is_displayed_with_blank_input_field() {
        Assert.assertTrue(signup.emailAddressTextIsDisplayedWithBlankInputField());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "email address text is displayed with blank input field");
    }

    @Then("mobile number text is displayed with blank input field")
    public void mobile_number_text_is_displayed_with_blank_input_field() {
        Assert.assertTrue(signup.mobileTextIsDisplayedWithBlankInputField());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "mobile number text is displayed with blank input field");
    }

    @Then("check box for agree to receive deals text is displayed")
    public void check_box_for_agree_to_receive_deals_text_is_displayed() {
        Assert.assertTrue(signup.checkBoxIsDisplayedAndUnchecked());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "check box for agree to receive deals text is displayed");
    }

    @Then("disabled submit button is displayed")
    public void disabled_submit_button_is_displayed() {
        Assert.assertTrue(signup.disabledSubmitButtonIsDisplayed());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "disabled submit button is displayed");
    }

    @Then("{int} links are displayed containing terms and conditions , information,privacy policy")
    public void links_are_displayed_containing_terms_and_conditions_information_privacy_policy(Integer int1) {
        Assert.assertTrue(signup.linksAreDisplayed());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "links are displayed containing terms and conditions , information,privacy policy");
    }

    //Scenario: Valid email and valid mobile number

    @When("the user enter a {string}  in email input field")
    public void the_user_enter_email_input_field(String input) {
        switch (input) {
            case "valid":
                String email = randomData.getRandomEmail();
                signup.enterEmail(email);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "valid email :" + email + "is entered");
                break;
            case "invalid":
                signup.enterEmail(invalidEmail);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "invalid email :" + invalidEmail + "is entered");
                break;
            default:
                ;
        }


    }

    @When("the user enter a {string} in mobile number input field")
    public void the_user_enter_mobile_number_input_field(String input) {
        switch (input) {
            case "valid":
                signup.enterMobileNo(validMobile);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "valid email :" + validMobile + "is entered");
                break;
            case "invalid":
                signup.enterMobileNo(inValidMobile);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "invalid email :" + inValidMobile + "is entered");
                break;
            default:
                ;
        }
    }

    @Then("the user gets {string}")
    public void the_user_gets(String msg) {
        switch (msg) {
            case "success":
                signup.signUpSuccessMsgIsDisplayed();
                Log.info("the use is getting:" + msg);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "success msg is displayed");
                break;
            case "email_error":
                signup.errorMsgForInvalidEmailIsDisplayed();
                Log.info("the use is getting:" + msg);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "error msg for invalid email is displayed");
                break;
            case "mobile_error":
                signup.errorMsgForInvalidMobileIsDisplayed();
                Log.info("the use is getting:" + msg);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "error msg for invalid mobile no is displayed");
                break;
            default:
                ;
        }
    }

    @When("the user click the {string} button")
    public void the_user_click_the_button(String string) {
        signup.clickSubmitButton();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user clicked on submit button");
    }

    @Then("the user redirected corresponding page on same browser tab and stayed")
    public void theUserRedirectedCorrespondingPageOnSameBrowserTabAndStayed(DataTable dataTable) {
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> credentials : credentialsList) {
            String linkName = credentials.get("Link Name");
            String expectedURLFragment = credentials.get("Corresponding Page");
            Log.info("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
            basePage.jsClickLinkByName(linkName);
            Assert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment),"NOT redirected to page url: "+expectedURLFragment);
            ExtentCucumberAdapter.addTestStepLog("Click Link : " + linkName + " is redirected to: " + expectedURLFragment);
            Log.info("Click  Link : " + linkName + " is redirected to: " + expectedURLFragment);
//            homePage.clickOnRACLogo();
//            Log.info("Browser back button navigated");
        }

    }

    @And("the user clicks on agree to receive deals")
    public void theUserClicksOnAgreeToReceiveDeals() {
        signup.checkBoxIsChecked();
    }
}
