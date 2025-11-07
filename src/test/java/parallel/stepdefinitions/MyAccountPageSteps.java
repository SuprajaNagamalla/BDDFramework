package parallel.stepdefinitions;

import static com.qa.factory.DriverFactory.getDriver;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.pages.HomePage;
import com.pages.MyAccountPage;
import com.qa.factory.DriverFactory;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelUtils;

public class MyAccountPageSteps {
    private static final Logger Log = LoggerHelper.getLogger();
    private MyAccountPage myAccountPage =new MyAccountPage(DriverFactory.getDriver());
    private HomePage homePage =new HomePage(DriverFactory.getDriver());
    ExcelUtils excelUtils=new ExcelUtils();
    BasePage basePage=new BasePage(getDriver());
    
    @When("the user navigates to Account Details page")
    public void the_user_navigates_to_account_details_page() {
        Log.info("Navigate to my account details page");
        myAccountPage.navigateToAccountDetailsPage();
    }

    @Then("Rental Account Balance is displayed")
    public void rental_account_balance_is_displayed() {
        Log.info("Verifying if Rental Account Balance is displayed");
        Assert.assertTrue(myAccountPage.isRentalAccountBalanceDisplayed(), "Rental Account Balance is not displayed");
    }

    @Then("Available Funds is displayed")
    public void available_funds_is_displayed() {
        Log.info("Verifying if Available Funds is displayed");
        Assert.assertTrue(myAccountPage.isAvailableFundsDisplayed(), "Available Funds is not displayed");
    }

    @When("the user clicks on Available Funds tool tip button")
    public void the_user_clicks_on_available_funds_tool_tip_button() {
        Log.info("Clicking on Available Funds tooltip button");
        myAccountPage.clickAvailableFundsTooltip();
    }

    @Then("Available Funds tool tip text is displayed")
    public void available_funds_tool_tip_text_is_displayed() {
        Log.info("Verifying if Available Funds tooltip text is displayed");
        Assert.assertTrue(myAccountPage.isAvailableFundsTooltipTextDisplayed(), "Tooltip text is not displayed");
    }

    @Then("able to close Available Funds tool tip")
    public void able_to_close_available_funds_tool_tip() {
        Log.info("Closing the Available Funds tooltip");
        myAccountPage.closeAvailableFundsTooltip();
        Log.info("Verifying if Available Funds tooltip is closed");
       // Assert.assertFalse(myAccountPage.isAvailableFundsTooltipTextDisplayed(), "Tooltip text is still visible");
    }

    @When("the user clicks on agreement payment")
    public void the_user_clicks_on_agreement_payment() {
        Log.info("Clicking on the Agreement Payment button");
        myAccountPage.clickAgreementPayment();
    }

    @Then("the user navigated to payment page")
    public void the_user_navigated_to_payment_page() {
        Log.info("Verifying that user is navigated to the Payment page");
        Assert.assertTrue(myAccountPage.isOnPaymentPage(), "User is not on the Payment page");
    }

    @Then("the default state of Available Funds toggle is OFF")
    public void the_default_state_of_available_funds_toggle_is_off() {
        Log.info("Checking if Available Funds toggle is OFF by default");
        Assert.assertFalse(myAccountPage.isAvailableFundsToggleOn(), "Available Funds toggle should be OFF by default");
    }

    @When("the user turns ON the Available funds toggle")
    public void the_user_turns_on_the_available_funds_toggle() {
        Log.info("Turning ON the Available Funds toggle");
        myAccountPage.turnOnAvailableFundsToggle();
    }

    @Then("text box is displayed to enter suspense amount")
    public void text_box_is_displayed_to_enter_suspense_amount() {
        Log.info("Checking if the suspense amount textbox is displayed");
        Assert.assertTrue(myAccountPage.isSuspenseAmountTextboxDisplayed(), "Suspense amount textbox is not displayed");
    }

    @Then("payment due, balance applied, funds applied are displayed on page")
    public void payment_due_balance_applied_funds_applied_are_displayed_on_page() {
        Log.info("Checking if Payment Due, Balance Applied, and Funds Applied sections are displayed");
        Assert.assertTrue(myAccountPage.isPaymentDueDisplayed(), "Payment Due is not displayed");
        Assert.assertTrue(myAccountPage.isBalanceAppliedDisplayed(), "Balance Applied is not displayed");
      //  Assert.assertTrue(myAccountPage.isFundsAppliedDisplayed(), "Funds Applied is not displayed");
    }




    @Then("the user should be able to see default \"$0\" is displayed in suspense amount text box")
    public void verifyDefaultSuspenseAmount() {
        Log.info("Verifying default suspense amount is \"$0\"");
        String defaultAmount = myAccountPage.getDefaultSuspenseAmount();
        Assert.assertEquals("0.00", defaultAmount);
    }

    @Then("message is displayed below the text box indicating the amount applied and amount available")
    public void verifySuspenseMessageDisplayed() {
        Log.info("Checking if suspense message is displayed");
        Assert.assertTrue(myAccountPage.isSuspenseMessageDisplayed());
    }



    @When("the user enters suspense amount {string}")
    public void the_user_enters_suspense_amount(String amount) {
        Log.info("User enters suspense amount: $" + amount);
        myAccountPage.enterSuspenseAmount(amount);
    }

    @Then("the amount applied and amount available values get updated according to the entered value")
    public void verifyAmountsAreUpdated() {
        Log.info("Verifying that amount applied and amount available are updated");
        Assert.assertTrue(myAccountPage.isAmountAppliedUpdated());
        Assert.assertTrue(myAccountPage.isAmountAvailableUpdated());
    }

    @Then("the message displayed below the text box updated with new values of amount applied and amount available")
    public void verifyMessageIsUpdated() {
        Log.info("Verifying suspense message is updated based on entered value");
        Assert.assertTrue(myAccountPage.isSuspenseMessageUpdated());
    }

    @When("the user clicks on continue button")
    public void clickContinueButton() {
        Log.info("User clicks on Continue button");
        myAccountPage.clickContinueButton();
    }

    @Then("complete payment page is displayed with payment due, balance applied, funds applied")
    public void verifyCompletePaymentPage() {
        Log.info("Verifying complete payment page shows all expected details");
        Assert.assertTrue(myAccountPage.isPaymentDueDisplayed());
        Assert.assertTrue(myAccountPage.isBalanceAppliedDisplayed());
        Assert.assertTrue(myAccountPage.isFundsAppliedDisplayed());
    }

    @When("the user submits the payment")
    public void submitPayment() {
        Log.info("User clicks Submit Payment");
        myAccountPage.submitPayment();
    }

    @Then("payment successful page is displayed with payment due, balance applied, funds applied")
    public void verifyPaymentSuccessPage() {
        Log.info("Verifying payment success page is displayed with expected values");
        Assert.assertTrue(myAccountPage.isPaymentSuccessPageDisplayed());
        Assert.assertTrue(myAccountPage.isPaymentDueDisplayed());
        Assert.assertTrue(myAccountPage.isBalanceAppliedDisplayed());
        Assert.assertTrue(myAccountPage.isFundsAppliedDisplayed());
    }



    //

    @When("the user clicks on the Payment Methods section")
    public void theUserClicksOnThePaymentMethodsSection() {
        Log.info("Clicking on the Payment Methods section");
        myAccountPage.clickPaymentMethodsSection();

    }
    
    @When("the user clicks on the UnEnroll AutoPay popUp")
    public void theUserClicksOnUnEnrollPopUp() {
        Log.info("Clicking on the un-enroll auto pay pop up");
        myAccountPage.clickUnEnrollButton();

    }
    
    @When("the user clicks the {string} button in My Account Section")
    public void click_the_button(String buttonName) {
        //ExtentCucumberAdapter.addTestStepLog("User clicks the " + buttonName + " button");
        myAccountPage.clickButtonWithText(buttonName);
        Log.info("Click the button: "+buttonName);
    }

    @When("the user clicks the {string} button and un enroll in My Account Section if displayed")
    public void click_auto_pay_enrolled_button_if_displayed(String buttonName) {
        //ExtentCucumberAdapter.addTestStepLog("User clicks the " + buttonName + " button");
        Log.info("If displayed click the : "+buttonName+"un enrolled buttons");
        myAccountPage.clickAutoPayEnrolledAndUnRolledButtonsIfDisplayed();
    }
    
    @When("the user clicks the {string} button in My Account Section if Enrolled")
    public void click_the_buttonUnEnroll(String buttonName) {
        ExtentCucumberAdapter.addTestStepLog("User clicks the " + buttonName + " button");
        myAccountPage.clickButtonWithTextIfDisplayed(buttonName);
        Log.info("Click the Notify button");
    }

    @When("the user clicks on the Delete button in the Saved Payment Methods section")
    public void theUserClicksOnDeleteButtonInSavedPaymentMethods() {
        Log.info("Clicking on the Delete button in Saved Payment Methods section");
        myAccountPage.clickDeleteButtonInSavedPaymentMethods();
    }
    
    @When("the user clicks on the Delete button in the Saved Payment Methods section if card Present")
    public void theUserClicksOnDeleteButtonInSavedPaymentMethodsifCardExists() {
        Log.info("Clicking on the Delete button in Saved Payment Methods section if card present");
        myAccountPage.deleteSavedCardIfPresent();
    }

    @Then("the Delete Payment Method pop-up modal is displayed")
    public void deletePaymentMethodPopupIsDisplayed() {
        Log.info("Verifying the Delete Payment Method pop-up modal is displayed");
        Assert.assertTrue(myAccountPage.isDeletePopUpIsDisplayed(),"Delete Payment Method pop-up modal is " +
                "NOT displayed");
    }

    @When("the user clicks on the Cancel button in the pop-up")
    public void theUserClicksOnCancelButtonInPopup()  {
        Log.info("Clicking on the Cancel button in the pop-up");
        myAccountPage.clickCancelButtonOnPopUp();
    }

    @Then("the payment method should not be deleted")
    public void paymentMethodShouldNotBeDeleted() {
        Log.info("Verifying that the payment method is not deleted");
        Assert.assertTrue(myAccountPage.isPaymentMethodNotDeleted(),"Payment methods deleted");
    }

    @When("the user clicks on the 'X' \\(close) button on the pop-up")
    public void theUserClicksOnXButtonOnPopup() {
        Log.info("Clicking on the 'X' (close) button on the pop-up");
        myAccountPage.clickCloseButtonOnPopUp();
    }

    @Then("the pop-up modal should be closed")
    public void popupModalShouldBeClosed() {
        Log.info("Verifying the pop-up modal is closed");
        myAccountPage.verifyPopUpIsClosed();
    }

    @When("the user clicks on the Delete Payment Method button in the pop-up")
    public void theUserClicksOnDeletePaymentMethodButtonInPopup() {
        Log.info("Clicking on the Delete Payment Method button in the pop-up");
        myAccountPage.clickConfirmDeletePaymentMethod();
    }
    
    @When("validate clicking on cancel button doesnt delete payment method")
    public void theUserClicksOnCancelPaymentMethodButtonInPopup() {
        Log.info("Clicking on the Cancel button in the pop-up");
        Assert.assertTrue(myAccountPage.clickCancelButton()," Payment method is displayed");
        
    }

    @Then("the payment method should be deleted")
    public void paymentMethodShouldBeDeleted() {
        Log.info("Verifying that the payment method is deleted");
        Assert.assertFalse(myAccountPage.isPaymentMethodDeleted(),"Payment Method not deleted");
    }

    @When("the user refreshes the page")
    public void theUserRefreshesThePage() {
        Log.info("Refreshing the page");
        myAccountPage.refreshPage();
    }

    @When("the user clicks on Back to Dashboard button")
    public void click_on_back_to_dashboard_button() {
        Log.info("Click on Back to Dashboard button");
        myAccountPage.clickBackToDashboardButton();
    }

    @Then("AutoPay enrollment is successful")
    public void verify_auto_pay_enrollment_success() {
        Log.info("Verifying that AutoPay enrollment is successful");
        Assert.assertTrue(myAccountPage.isAutoPayEnrollmentSuccessDisplayed(),
                "AutoPay enrollment is not successful");
    }

    @Then("the system displays a message with instructions to delete an auto-enrolled payment method")
    public void the_system_displays_a_message_with_instructions_to_delete_an_auto_enrolled_payment_method() {
        Log.info("Verify instruction to delete auto enrolled payment method");
        Assert.assertTrue(myAccountPage.isInstructionDisplayedToDeleteAutoEnrolledPaymentMethod(),
                "AutoPay enrollment instruction not displayed");
    }


    @Then("the following quick link components should be displayed:")
    public void verify_quickLink_components(List<String> expectedQuickLinkComponents) {
        for (String components : expectedQuickLinkComponents) {
            Assert.assertTrue(myAccountPage.isQuickLinkComponentsDisplayed(components),
                    "Quick link components are : "+components+" NOT displayed");
            Log.info("Quick link components are {} displayed", components);
        }
    }

    @Then("the user clicks on Account Details in My Account Section")
    public void user_clicks_on_MyAccountDetails() throws InterruptedException{
        myAccountPage.AccountDetails();
    }
    
    
    @Then("the user clicks on select Payments and validate all checkboxes are autochecked")
    public void clickOnSelectPayments() {
    	Assert.assertTrue(myAccountPage.selectPayments(), "All the check boxes are checked when clicked on select payments text");
    }
    
    @Then("the user is redirected to the agreement details screen")
    public void user_redirected_to_the_redirected_to_the_agreement_details_screen() {
        Assert.assertTrue(myAccountPage.IsUserRedirectedToTheAgreementDetailsScreen(),"User is NOT redirected to the agreement details screen");
        Log.info("User is redirected to the agreement details screen");

    }
    
    @Then("Verify List Of Agreements section is displayed")
    public void verify_List_Of_Agreements_Section() {
            Assert.assertTrue(myAccountPage.isListOfAgreementsSectionDisplayed(),
                    "list of agreements section is NOT displayed");
            Log.info("list of agreements section is displayed");
    }
    
    @Then("user clicks on AgreementDetails link and validate StoreDetails")
    public void verify_Store_UnderAgreementDetails() {
            Assert.assertTrue(myAccountPage.validateStoreUnderAgrDetails(),
                    "list of stores is NOT displayed");
            Log.info("list of stores is displayed");
    }
    
    @Then("the user validate myStore address is displayed once logged in")
    public void verify_Store_Address_isDisplayed() {
            Assert.assertFalse(myAccountPage.verifyStoreAddress(),
                    "store address is NOT displayed");
            Log.info("store address is displayed");
    }
    
    @Then("the user validates payment methods displayed")
    public void verify_paymentMethods_forMultiStore() {
            Assert.assertEquals(myAccountPage.verifyPaymentMethods(),2,
                    "payment methods are greater than 2");
            Log.info("payment methods are greater than 2");
    }
    
    
    @Then("verify the {string} text is displayed on header")
    public void verify_user_details_are_displayed_on_header(String expUserDetails) {
        Assert.assertTrue(myAccountPage.gettheuserdetails(expUserDetails),
                "User details "+expUserDetails+" are NOT displayed on header");
        Log.info("user details "+expUserDetails+" are displayed on header");
    }

    @When("the user clicks on Make Payment button")
    public void the_user_click_on_MakePayment_link_button() {
        myAccountPage.clickOnMakePaymentLinkButton();
        Log.info("Clicked on make payment button");
    }
    
    @Then("Verify Total Approval Details is displayed")
    public void UserShouldSeeTheApprovalDetails() {
        Assert.assertTrue(myAccountPage.ApprovalDetailsIsDisplayed(),"approval details are Not displayed");
        Log.info("Approval details is Displayed");
    }
    
    @When("Verify My Approval Amount component header is displayed")
    public void Verify_My_Approval_Amount_is_displayed() {
        Assert.assertTrue(myAccountPage.isMyApprovalAmountDisplayed(), "my approval amount is NOT displayed");
        Log.info("My Approval Amount Displayed");
    }
    
    @Then("the following My Approval Amount details should be displayed:")
    public void verify_approvalAmount_details(List<String> expectedapprovalamountdetails) {
        for (String details : expectedapprovalamountdetails) {
            Assert.assertTrue(myAccountPage.ApprovalAmountDetailsDisplayed(details),
                    "approval amount details are : "+details+" NOT displayed");
            Log.info("approval amount details are "+details+" displayed");
        }
    }
    
    
    @Then("Verify total amount due text along with amount is displayed")
    public void theUserShouldSeeTotalAmountDueTextAlongWithAmount() {
        Assert.assertTrue(myAccountPage.TotalAmountDueIsDisplayed(),"due amount is NOT displayed");
        Log.info("Amount Due is Displayed");
        Log.info("total amount due{}", myAccountPage.amount());
       ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "amount : "+ myAccountPage.amount());
    }
    
    
    @Then("Verify Make Payment is displayed")
    public void UserShouldSeeTheMakePaymentIsDisplayed() {
        Assert.assertTrue(myAccountPage.VerifyMakePaymentIsDisplayed(),"Make Payment is NOT displayed");
        Log.info("Make Payment is displayed");
    }
    
    @Then("verify My Stores component header is displayed")
    public void verify_MyStores_header_is_displayed(){
        Assert.assertTrue(myAccountPage.verifyMyStoresHeaderIsDisplayed(),
                "My Stores component header is NOT displayed");
        Log.info("My Stores component header is displayed");
    }
    
    @Then("User verify Store Contact details and Today's hours are displayed in my store section")
    public void verify_ContactDetails_and_TodayHours_are_displayed(){
        Assert.assertTrue(myAccountPage.verifyStoreContactDetailsandTodayssHoursaredisplayed(),
                "verify Store Contact details and Today's hours are NOT displayed");
        Log.info("Store Contact details and Today's hours are displayed");
    }
    
    
    @Then("User verify Store Address is displayed in my store section")
    public void verify_Store_Address_is_displayed(){
        Assert.assertTrue(myAccountPage.verifyStoreAddressisdiplayed(),
                "verify Store address is NOT displayed");
        Log.info("Store address is displayed");
    }
    
    
    @Then("The user verify product image is displayed in the agreement section")
    public void verify_ProductImage_is_Displayed() {
        Assert.assertTrue(myAccountPage.isProductImageDisplayed(),
                "Product image is NOT displayed");
        Log.info("product image is displayed");
    }
    
    
    @Then("The user verify product Name is displayed in the agreement section")
    public void verify_ProductName_is_Displayed() {
        Assert.assertTrue(myAccountPage.isProductNameDisplayed(),
                "Product Name is NOT displayed");
        Log.info("product Name is displayed");
        Log.info("product name is displayed{}", myAccountPage.productNameDisplayed());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "product name : "+ myAccountPage.productNameDisplayed());

    }
    
    @When("the user clicks on BackToDashboard link")
    public void the_user_click_on_BackToDashbord_link() {
        myAccountPage.ClickOnBackToDashboardLink();
        Log.info("Clicked on back to dashboard link");
    }
    
    @Then("Verify that either the AutoPay Unavailable option or the Enroll in AutoPay option is displayed in the agreement section")
    public void verifyAutoPayUnavailableOrEnrolled() {
        if (myAccountPage.isEnrollinautopaydisplayed()) {
            Log.info("AutoPay Enrolled is displayed. Skipping AutoPay Unavailable check.");
        } else {
            Assert.assertTrue(myAccountPage.isAutoPayUnavailableDisplayed(),
                    "AutoPay Unavailable is NOT displayed");
            Log.info("AutoPay Unavailable is displayed");
        }
    }
    
    @Then("Verify Enroll in AutoPay option is displayed in the agreement section")
    public void verifyAutoPayEnrollOptionIsDisplayed() {
    	Assert.assertTrue(myAccountPage.isEnrollinautopaydisplayed(),"Enroll in autopay is displayed");
    }
    
    @Then("The user verifies that the payments made by the customer are displayed in the agreement section")
    public void verifyHowMuchPaymentIsAlreadyDoneByCustomer() {
        Assert.assertTrue(myAccountPage.ispaymentsmadebythecustomeraredisplayed(),
                "Payment History is NOT displayed");
        Log.info("Payment History is displayed");
    }
    
    @Then("The user see the latest agreement tile at the topList Of Agreements section is displayed")
    public void verify_the_latest_agreement_tile_at_top_list_of_Agreements_Section() {
        Assert.assertTrue(myAccountPage.islatestagreementtitleatthetoplistofagreementssectionisDisplayed(),
                "latest agreement tile at the top list of agreements section is NOT displayed");
        Log.info("latest agreement tile at the top list of agreements section is displayed");
    }

    @Then("Verify Get Pre-Approved button is displayed")
    public void UserShouldSeeTheGetPreApprovedButtonIsDisplayed() {
        Assert.assertTrue(myAccountPage.VerifyGetPreApprovedIsDisplayed(),"Get pre-approved button is NOT displayed");
        Log.info("Get Pre-Approved button is displayed");
    }
    
    
    @When("the user clicks on Get Pre-Approved button")
    public void the_user_click_on_GetPre_Approved_button() {
        myAccountPage.ClickOnGetPreApprovedButton();
        Log.info("Clicked on Get Pre-Approved button");
    }
    
    @Then("the user is redirected to the Prefilled web order form")
    public void user_redirected_to_the_prefilled_web_order_form() {
        Assert.assertTrue(myAccountPage.IsUserRedirectedToThePrefilledWOF(),"User is NOT redirected to the Prefilled WOF and Autofilled");
        Log.info("User is redirected to the Prefilled WOF");
    }
    
    
    @When("the user clicks on Shop My Approved Items button")
    public void the_user_click_on_shop_my_approved_items_button() {
        myAccountPage.ClickOnShopMyApprovedItemsButton();
        Log.info("Clicked on shop my approved items button");
    }
    
    @Then("the user is redirected to the PLP")
    public void user_redirected_to_the_redirected_to_the_PLP() {
        Assert.assertTrue(myAccountPage.IsUserRedirectedToThePLP(),"User is NOT redirected to the PLP");
        Log.info("User is redirected to the PLP");

    }
    
    @Then("Verify Benefits plus product tile section is displayed")
    public void UserShouldSeeTheBenefitsPlusProductTileIssectionIsDisplay() {    
    	Assert.assertTrue(myAccountPage.IsBenefitsPlusProductTileIsDisplayed(),"Benefits Plus tile is NOT displayed");    
    	Log.info("Benefits Plus tile is displayed");
    	}
    
    @Then("the user verify the Product delivery status is displayed")
    public void UserShouldSeeTheProductDeliveryStatusIsDisplay() {
        Assert.assertTrue(myAccountPage.VerifyProductDeliveryStatusIsDisplayed(),"delivery status is NOT displayed");
        Log.info("delivery status is displayed ")   ;
    }
    
    @When("the user clicks on agreement details button")
    public void the_user_click_on_agreement_details_button() {
        myAccountPage.ClickOnAgreementDetailsButton();
        Log.info("Clicked on agreement details button");
    }
    
    @Then("validate agreement based on payment option sent")
    public void validateAgrBasedOnPaymentOptionSelected(DataTable dataTable){
    	String paymentOptions1 = null;
    	String paymentOptions2 = null;
    	List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
    	for (Map<String, String> credentials : credentialsList) {
    		if(credentials.size()==1) {
    			 paymentOptions1 = credentials.get("paymentOption1");
    			 boolean value = myAccountPage.validateAgreementOnPaymentOption(paymentOptions1);
    			 Assert.assertTrue(value, "Payment amount is Equal to total amount");
    		}
    		if(credentials.size()==2) {
    			 paymentOptions1 = credentials.get("paymentOption1");
    	         paymentOptions2 = credentials.get("paymentOption2");
    	         boolean value = myAccountPage.validateAgreementOnPaymentOption(paymentOptions1, paymentOptions2);
    	         Assert.assertTrue(value, "Payment amount is Equal to total amount");
    		}
    	}
    }

    @Then("verify quick links component header is displayed")
    public void verify_quick_link_header_is_displayed(){
        Assert.assertTrue(myAccountPage.verifyQuickLinkHeaderIsDisplayed(),
                "Quick links component header is NOT displayed");
        Log.info("Quick links component header is displayed");
    }

    @Then("verify makePayment button and agreement Details link is displayed")
    public void verify_makePayment_button_is_displayed(){
        Assert.assertTrue(myAccountPage.makePaymentButtonIsDisplayed(), "Make Payment button is NOT displayed");
        Assert.assertTrue(myAccountPage.agreement_DetailsLink_isDisplayed(), "Agreement Details Link is NOT displayed");
        Log.info("MakePayment button is displayed");
    }
    
    @Then("verify Clicking on MakePayment button displays all Agreements and get Agreement Details")
    public void verify_All_Agreements_Displayed() throws InterruptedException{
    	boolean paymentOptionSize = myAccountPage.clickMakePaymentAndValidateAndGetAgrDetails();
    	Assert.assertTrue(paymentOptionSize, "Agreement Size is greater than Zero");
    }
    
    @Then("verify Total Amount is zero when COA is applied")
    public void verify_ZeroDollar_Payment() throws InterruptedException{
    	Assert.assertTrue(myAccountPage.zeroDollarPaymentWithCOAApplied(),"Total payment amount is zero");
    }
    
    @Then("verify no dropdown option displays for benefits plus")
    public void verify_noDropDownforBP_Displayed() throws InterruptedException{
    	boolean dropDownOption = myAccountPage.validateNoDropDownforBP();
    	Assert.assertFalse(dropDownOption, "DropDown Option is not displayed");
    }
    
    @Then("Uncheck all agreement and verify continue button and total payment")
    public void verify_continueButton_And_TotalPayment() throws InterruptedException{
    	boolean continueAndTotalPayment = myAccountPage.unCheckAllAgreementAndValidate();
    	Assert.assertTrue(continueAndTotalPayment, "Continue button is disabled and Total payment is Zero");
    }
    
    @Then("user Clicks on continue button to make payment")
    public void clickContinueButtonToMakePayment() throws InterruptedException{
    	myAccountPage.clickContinueButton();
    }
    
    @Then("validate only CreditCard radio button is displayed")
    public void validateCreditCardButtonDisplayed() {
        Log.info("validate credit card radio button is displayed");
        Assert.assertTrue(myAccountPage.creditRadioButtonIsDisplayed(),"Only Credit Radio button is Displayed");
    }
    
    @Then("verify past due date banner is displayed")
    public void verify_pastduedate_banner_is_displayed(){
        Assert.assertTrue(myAccountPage.pastDueBannerIsDisplayed(),
                "Past Due date banner is NOT displayed for past due customer");
        Log.info("Past Due date banner is displayed for past due customer{}", myAccountPage.pastDueBannerText());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "Past Due Banner text is displayed: "+ myAccountPage.pastDueBannerText());
    }
    
    @Then("verify past due text is displayed and value not equal to Zero")
    public void verify_pastduetext_is_displayed(){
        Assert.assertTrue(myAccountPage.pastDueTextIsDisplayed(),
                "Past Due text is NOT displayed for past due customer");
        Log.info("Past Due text is displayed for past due customer{}", myAccountPage.pastDueTextIsDisplayed());
        Assert.assertNotEquals(myAccountPage.amountDueText(), "0", "Total Amount Due is 0 as expected");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "Past Due Amount is : "+ myAccountPage.amountDueText());
    }
    
    @Then("validate current Due Date is less than todays Date")
    public void verify_currentDueDate_ForPayment(){
        Assert.assertTrue(myAccountPage.dueDateTextIsDisplayed(),
                "current Due Date text is NOT displayed for past due customer");
        Log.info("Due Date Text is displayed for past due customer{}", myAccountPage.dueDateTextIsDisplayed());
       String dueDate =  myAccountPage.validateDueDate();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "Current Due Date is : "+ dueDate);
    }

    @Then("verify my approval amount section")
    public void verify_myapproval_amountSection(){
        Assert.assertTrue(myAccountPage.myApprovalAmountSection(),
                "My approval amount section is NOT displayed");
        Log.info("My approval amount section is displayed{}", myAccountPage.myApprovalAmountSection());
    }
    
    @Then("the user verifies multiple stores displayed under MyStores")
    public void verify_MultipleStores_Under_MyStores(){
        Assert.assertEquals(myAccountPage.getStoreCount(),2,
                "Multiple stores are not displayed");
        Log.info("Multiple stores are not displayed{}", myAccountPage.getStoreCount());
    }
    
    
    @Then("the following payment options should be displayed:")
    public void verify_payment_options(List<String> expectedpaymentoptions) {
        for (String options : expectedpaymentoptions) {
            Assert.assertTrue(myAccountPage.PaymentOptionsDisplayed(options),
                    "payment : "+options+" NOT displayed");
            Log.info("payment"+options+" displayed");
        }
    }
    
    @When("the user click on the down arrow to choose the payment option")
    public void click_on_the_down_arrow_to_select_the_payment_option() {
        myAccountPage.IsUserclickondownarrowbutton();
        Log.info("Clicked on down arrow to select the payment option");
    }

    

    @Then("Validate Payment History")
    public void validatePaymentHistoryDetails() {
    	Map<String, String> value = myAccountPage.validatePaymentHistory();
    	for(int i =0; i< value.size() ; i++) {
    		Assert.assertTrue(value.get("Amount")!= "0", "validated Payment amount is Not zero");
   		 	Log.info("Payment history details", value);
    	}
    	ExtentCucumberAdapter.addTestStepLog("Payment History Values : " + value);
    }

    @Then("Validate Personal Info Section")
    public void validatePersonalInformationSection() {
    	boolean isPersonalInfoUpdated = myAccountPage.isPersonalInfoUpdated();
    	   Assert.assertTrue(isPersonalInfoUpdated, "Personal info section is not updated");
           Log.info("Personal info Section is updated", myAccountPage.isPersonalInfoUpdated());
    }
    
    @Then("Validate Saved Items Section")
    public void validateSavedItemsSection() {
    	myAccountPage.validateSavedItemsSectionMyAccountPage();
    }
    
    @Then("verify quick link components redirected to corresponding page")
    public void verify_user_redirected_corresponding_page(DataTable dataTable) {
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> credentials : credentialsList) {
            String linkName = credentials.get("Link Name");
            String expectedURLFragment = credentials.get("Corresponding Page");
            boolean[] results = myAccountPage.isQuickLinksClickRedirected(linkName,expectedURLFragment);
            Assert.assertFalse(results[0]);
            Assert.assertTrue(results[1],
                    "Link : "+linkName+" is NOT redirected to: "+expectedURLFragment);
            ExtentCucumberAdapter.addTestStepLog("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
            Log.info("Click Link : {} is redirected to: {}", linkName, expectedURLFragment);
            homePage.browserBack();
            Log.info("Browser back button navigated");
        }
    }
    
    @Then("verify menu components redirected to corresponding page")
    public void verify_user_redirected_menuLinks(DataTable dataTable) {
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
        myAccountPage.clickOnMakePaymentLinkButton();
        for (Map<String, String> credentials : credentialsList) {
            String linkName = credentials.get("Link Name");
            String expectedURLFragment = credentials.get("Corresponding Page");
            boolean[] results = myAccountPage.isQuickLinksClickRedirectedMenu(linkName,expectedURLFragment);
            Assert.assertFalse(results[0]);
            Assert.assertTrue(results[1],
                    "Link : "+linkName+" is NOT redirected to: "+expectedURLFragment);
            ExtentCucumberAdapter.addTestStepLog("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
            Log.info("Click Link : {} is redirected to: {}", linkName, expectedURLFragment);
            homePage.browserBack();
            basePage.waitForPageToLoad();
            Log.info("Browser back button navigated");
        }
    }
    
    @Then("verify menu components redirected to corresponding page signout")
    public void verify_user_redirected_signout(DataTable dataTable) {
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> credentials : credentialsList) {
            String linkName = credentials.get("Link Name");
            String expectedURLFragment = credentials.get("Corresponding Page");
            boolean[] results = myAccountPage.isQuickLinksClickRedirectedSignOut(linkName,expectedURLFragment);
            Assert.assertFalse(results[0]);
            Assert.assertTrue(results[1],
                    "Link : "+linkName+" is NOT redirected to: "+expectedURLFragment);
            ExtentCucumberAdapter.addTestStepLog("Click Link : "+linkName+" is redirected to: "+expectedURLFragment);
            Log.info("Click Link : {} is redirected to: {}", linkName, expectedURLFragment);
        }
    }
    
    @Then("Verify due amount text along with amount is displayed in Agreement Payment screen")
    public void theUserShouldSeeDueAmountTextAlongWithAmount() {
        Assert.assertTrue(myAccountPage.IsDueAmountDisplayed(),"due amount is NOT displayed");
        Log.info("Due amount is Displayed");
        Log.info("Amount details{}", myAccountPage.amountdetails());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "Amount details : "+ myAccountPage.amountdetails());
    }
    
    @Then("The user verify product Name is displayed in the agreement payment screen")
    public void ProductName_is_Displayed() {
        Assert.assertTrue(myAccountPage.VerifyProductNameDisplayed(),
                "Product Name is NOT displayed");
        Log.info("product name displayed");
        Log.info("name of product{}", myAccountPage.productnameisdisplayed());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "product name: "+ myAccountPage.productnameisdisplayed());

    }

    @Then("verify user is navigated to MyAccount Dashboard")
    public void verifyUserIsNavigatedToMyAccountDashboard() {
        Assert.assertTrue(myAccountPage.verifyUserNavigatedToDashBoard(),"My Account dashboard is NOT displayed");
    }

    @Then("verify cash app method is displayed in my accounts")
    public void verify_cash_app_method_is_displayed_in_my_accounts() {
        Log.info("Cash app is displayed in my accounts");
        Assert.assertTrue(myAccountPage.isCashAppPaymentMethodDisplayed(),"Cash app not displayed in my accounts");
        Log.info("Cash app is displayed in my accounts");
    }
}
