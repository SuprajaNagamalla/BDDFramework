package parallel.stepdefinitions;

import java.io.IOException;
import java.util.*;

import com.aventstack.extentreports.Status;
import com.pages.OrderSubmitPage;
import io.cucumber.datatable.DataTable;
import org.apache.commons.math3.linear.DefaultIterativeLinearSolverEvent;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.pages.CheckOutPage;
import com.pages.HomePage;
import com.qa.factory.DriverFactory;
import com.qa.util.Constants;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelUtils;
import utils.RandomDataGenerator;
import utils.RandomDataGeneratorFaker;

import java.util.concurrent.TimeUnit;
import com.pages.SignInPage;

public class CheckOutPageSteps {
    private static final Logger Log = LoggerHelper.getLogger();
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private BasePage basePage = new BasePage(DriverFactory.getDriver());
    private CheckOutPage checkOutPage = new CheckOutPage(DriverFactory.getDriver());
    private OrderSubmitPage orderSubmitPage = new OrderSubmitPage(DriverFactory.getDriver());
    private SignInPage signInPage =new SignInPage(DriverFactory.getDriver());
    ExcelUtils excelUtils = new ExcelUtils();
    RandomDataGenerator randomData = new RandomDataGenerator();
    RandomDataGeneratorFaker randomDataFaker = new RandomDataGeneratorFaker();

    @When("the user fills check out form from {string} at row {int}")
    public void fill_check_out_form_sheet(String sheetName, int rowNumber) throws IOException {
        String firstName = null;
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String firstname = userData.getOrDefault("FirstName", "");
        if(firstname.equals("")){
            firstName = randomDataFaker.getRandomFirstName();
        }else{
            firstName = userData.getOrDefault("FirstName", "");
        }
        String lastName = userData.getOrDefault("LastName", "");
        //String emailAddress = userData.getOrDefault("EmailAddress", "");
        String emailAddress = randomData.getRandomEmail();
        String mobilePhone = userData.getOrDefault("MobilePhone", "");
        String country = userData.getOrDefault("Country", "");
        String address = userData.getOrDefault("Address", "");
        String city = userData.getOrDefault("City", "");
        String state = userData.getOrDefault("State", "");
        String zipCode = userData.getOrDefault("ZipCode", "");

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "First Name: " + firstName + " ,Last Name: " + lastName+" Email: " + emailAddress + " ," + "Mobile:" + mobilePhone);

        checkOutPage.fillCheckoutForm(firstName, lastName, emailAddress, mobilePhone, country,
                address, city, state, zipCode);
        Log.info("First Name: " + firstName + " ,Last Name: " + lastName);
    }

    @When("the user clicks on Continue button Paypal")
    public void click_continue_button_Paypal() {
        Log.info("click continue button");
        checkOutPage.clickContinue();
    }

    @When("the user clicks on Continue button WOF")
    public void click_continue_button_wof_page() {
        Log.info("click continue button WOF");
        checkOutPage.clickContinue();
    }

    @When("the user enters ssn and dob")
    public void enter_ssn_and_dob() {
        //String ssn="364646333";
        Log.info("enter ssn");
        String ssn = randomData.getRandomSSN();
        checkOutPage.enterSSN(ssn);
        String dob = randomData.getRandomDOB();
        checkOutPage.enterDOB(dob);
        Log.info("Enter SSN: " + ssn + " DOB: " + dob);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "SSN: " + ssn);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "DOB: " + dob);

    }

    @When("the user selects agree and continue button")
    public void select_agree_and_continue_button() {
        Log.info("Click Agree and Continue button");
        checkOutPage.clickAgreeAndContinueButton();
    }

    @When("the user clicks on the CONTINUE button on the Congrats popup")
    public void click_on_the_continue_button_on_the_congrats_popup() {
        Log.info("Click Continue Button on Congrats Pop up");
        checkOutPage.clickContinueButtonOnCongratsPopUp();
    }

    @When("the user selects pay schedule {string}")
    public void select_pay_schedule(String paySchedule) {
        checkOutPage.selectPaySchedule(paySchedule);
        Log.info("Select Pay schedule: " + paySchedule);
    }

    @And("the user selects pay schedule from {string} at row {int}")
    public void select_pay_schedule_from_sheet(String sheetName, int rowNumber) throws IOException {
       /* ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
        Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);*/

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String paySchedule = userData.getOrDefault("PaySchedule", "");
        Log.info("Select Pay schedule: " + paySchedule);
        checkOutPage.selectPaySchedule(paySchedule.toLowerCase());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Pay schedule: " + paySchedule);

    }

    @When("the user selects paid next date {string}")
    public void select_paid_next_date(String date) {
        checkOutPage.selectPaidNextDate(date);
        Log.info("Select paid next date: " + date);
    }

    @And("the user selects paid next date as per pay schedule from {string} at row {int}")
    public void select_paid_next_date_from_sheet(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String paySchedule = userData.getOrDefault("PaySchedule", "");
        String date;

        switch (paySchedule.toLowerCase()) {
            case "weekly":
                date = randomData.getDatePlusDays(9); // Weekly = +7 days
                break;

            case "bi-weekly":
                date = randomData.getDatePlusDays(16); // Bi-weekly = +14 days
                break;

            case "semi-monthly":
                date = randomData.getDatePlusDays(17); // Semi-monthly ≈ +15 days
                break;

            case "monthly":
                date = randomData.getDatePlusDays(32); // Monthly = +30 days
                break;

            default:
                throw new IllegalArgumentException("Unsupported pay schedule: " + paySchedule);
        }
        Log.info("Select paid next date: " + date);
        checkOutPage.selectPaidNextDate(date);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Date: " + date);

    }


    @When("the user clicks on the CONTINUE button on the Pay Schedule section")
    public void click_on_the_continue_button_on_the_pay_schedule_section() {
        Log.info("Click Continue Button on Pay schedule section");
        checkOutPage.clickContinueButtonOnPayScheduleSection();
    }

    @When("the user clicks on the Continue button on the Pay Schedule section guest user")
    public void click_on_the_continue_button_on_the_pay_schedule_section_guest_user() {
        Log.info("Click Continue Button on Pay schedule section - Guest user");
        checkOutPage.clickContinueButtonOnPayScheduleSection();
    }

    @When("the user clicks on Continue To Payment button")
    public void click_on_continue_to_payment_button() {
        Log.info("Click On Continue To Payment Button");
        checkOutPage.clickOnContinueToPaymentButton();
    }

    @When("the user clicks on PayPal payment method")
    public void click_on_pay_pal_payment_method() {
        checkOutPage.clickOnPayPalRadioButton();
        Log.info("Click On Pay pal radio Button");
        checkOutPage.clickOnPayPalLogo();
        Log.info("Click On Pay pal Logo");
    }

    @When("the user clicks on Bank Account payment method")
    public void click_on_bank_account_payment_method() {
        Log.info("Click On bank account radio Button");
        checkOutPage.clickOnBankAccountRadioButton();
    }

    @When("the user clicks on Credit Card payment method")
    public void click_on_credit_card_payment_method() {
        Log.info("Click On Credit card radio Button");
        checkOutPage.clickOnCreditCardRadioButton();
    }

    @When("the user clicks on Credit Card payment method if Not Selected")
    public void click_on_credit_card_payment_method_ifNotSelected() {
        Log.info("Click On Credit card radio Button if not selected ");
        checkOutPage.clickOnCreditCardRadioButtonIfnotSelected();
    }

    @When("the user clicks on Venmo payment method")
    public void click_on_venmo_payment_method() {
        Log.info("Click On venmo radio Button");
        checkOutPage.clickOnVenmoRadioButton();
        Log.info("Click On Venmo Logo");
        checkOutPage.clickOnVenmoLogo();
    }

    @When("the user clicks on Apple pay payment method")
    public void click_on_apple_pay_payment_method() {
        Log.info("Click On Apple radio Button");
        checkOutPage.clickOnAppleRadioButton();
        //checkOutPage.clickOnPayPalLogo();
        Log.info("Click On buy with Apple Pay Button");
        checkOutPage.clickOnBuyWithApplePayButton();
    }

    @When("the user clicks on Google pay payment method")
    public void click_on_google_pay_payment_method() {
        checkOutPage.clickOnGooglePayRadioButton();
        Log.info("Click On Google pay radio Button");
        checkOutPage.clickOnBuyWithGPayButton();
        Log.info("Click On buy with Google Pay Button");
    }

    @When("the user clicks on cash app pay payment method")
    public void click_on_cash_app_pay_payment_method() {
        checkOutPage.clickOnCashAppPayRadioButton();
        Log.info("Click On cash app pay radio Button");
        //checkOutPage.clickOnCashAppPayButton();
        //Log.info("Click On Cash App Pay Button");
    }

    @Then("the user should see the Cash App QR code image")
    public void verify_QRCode_Image() {
        Assert.assertTrue(checkOutPage.isQRCodeVisible(), "QR Code is not visible on the page.");
        Log.info("QR code is visible");
    }

    @Then("the user should see the apple scan QR code image")
    public void verify_apple_qrcode_Image() {
        Assert.assertTrue(checkOutPage.isAppleQRCodeVisible(), "Apple QR Code is not visible on the page.");
        Log.info("Apple QR code is visible");
    }

    @Then("the user saves Paypal credentials from {string} at row {int}")
    public void saves_paypal_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {
        /*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
        Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);*/
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String payPalEmail = userData.getOrDefault("PayPalEmail", "");
        String payPalPassword = userData.getOrDefault("PayPalPassword", "");
        checkOutPage.loginPaypalCredentials(payPalEmail, payPalPassword);
        Log.info("Logged in with Pay pal credentials");
        checkOutPage.selectIAgreeToTheCheckBox();
        Log.info("Select I Agree To Check box");
    }


    @When("the user saves bank account details under Add Payment method from {string} at row {int}")
    public void saves_bank_account_credentials_under_add_payment_from_sheet(String sheetName, int rowNumber) throws IOException {
        /*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
        Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);*/
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String AccountType = userData.getOrDefault("AccountType", "");
        String FirstName = userData.getOrDefault("FirstName", "");
        String LastName = userData.getOrDefault("LastName", "");
        String routingNumber = userData.getOrDefault("RoutingNumber", "");
        String accountNumber = userData.getOrDefault("AccountNumber", "");
        String phoneNumber = userData.getOrDefault("PhoneNumber", "");
        Log.info("Save bank account details");
        checkOutPage.saveBankAccountDetailsUnderAddPaymentMethod(AccountType, FirstName, LastName, routingNumber, accountNumber, phoneNumber);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Account Number: " + accountNumber);
    }


    @Then("the user saves Paypal credentials")
    public void save_paypal_credentials() {

        String payPalEmail = Constants.PAYPAL_EMAIL;
        String payPalPassword = Constants.PAYPAL_PASSWORD;
        Log.info("Log in with Pay pal credentials: " + payPalEmail);
        checkOutPage.loginPaypalCredentials(payPalEmail, payPalPassword);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "PayPal Email: " + payPalEmail);
        //checkOutPage.selectIAgreeToTheCheckBox();
        //Log.info("Selected I Agree To Check box");

    }

    @Then("the user saves Venmo credentials from {string} at row {int}")
    public void saves_venmo_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {

        /*Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String venmoEmail = userData.getOrDefault("VenmoEmail", "");
        String venmoPassword = userData.getOrDefault("VenmoPassword", "");*/

        String venmoEmail = Constants.VENMO_EMAIL;
        String venmoPassword = Constants.VENMO_PASSWORD;
        checkOutPage.loginVenmoCredentials(venmoEmail, venmoPassword);
        Log.info("Logged in with venmo credentials");

    }

    @Then("the user saves Venmo credentials")
    public void saves_venmo_credentials() {
        String venmoEmail = Constants.VENMO_EMAIL;
        String venmoPassword = Constants.VENMO_PASSWORD;
        checkOutPage.loginVenmoCredentials(venmoEmail, venmoPassword);
        Log.info("Logged in with venmo credentials: " + venmoEmail + " ," + venmoPassword);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Venmo Email: " + venmoEmail + "Venmo Password " + venmoPassword);
        Assert.assertTrue(checkOutPage.isVenmoSaveSuccessMessageDisplayed(), "Venmo Credentials NOT saved");
        Log.info("Venmo Credentials saved");
    }

    @Then("the user saves Venmo credentials MyAccount")
    public void saves_venmo_credentials_MyAccount() {
        String venmoEmail = Constants.VENMO_EMAIL;
        String venmoPassword = Constants.VENMO_PASSWORD;
        checkOutPage.loginVenmoCredentials(venmoEmail, venmoPassword);
        Log.info("Logged in with venmo credentials: " + venmoEmail + " ," + venmoPassword);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Venmo Email: " + venmoEmail + "Venmo Password " + venmoPassword);
        Assert.assertTrue(checkOutPage.isVenmoSaveSuccessMessageDisplayedMyAccount(), "Venmo Credentials NOT saved");
        Log.info("Venmo Credentials saved");
    }


    @Then("the user saves GPay credentials from {string} at row {int}")
    public void saves_gpay_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String gPayEmail = userData.getOrDefault("GpayEmail", "");
        String gPayPassword = userData.getOrDefault("GpayPassword", "");
        //checkOutPage.loginVenmoCredentials(venmoEmail,venmoPassword);
        checkOutPage.loginGooglePayCredentials(gPayEmail, gPayPassword);
        Log.info("Logged in with Google pay credentials");
    }

    @Then("the user saves GPay credentials")
    public void saves_gpay_credentials() {

        String gPayEmail = Constants.GPAY_EMAIL;
        String gPayPassword = Constants.GPAY_PASSWORD;
        //checkOutPage.loginVenmoCredentials(venmoEmail,venmoPassword);
        checkOutPage.loginGooglePayCredentials(gPayEmail, gPayPassword);
        Log.info("Logged in with Google pay credentials: " + gPayEmail);

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Gpay Email: " + gPayEmail);

    }

    @Then("the user selects I agree checkbox")
    public void select_i_agree_checkbox() {
        Log.info("Select I Agree To the Check box");
        checkOutPage.selectIAgreeToTheCheckBox();
    }

    @Then("the user selects I agree checkbox Venmo")
    public void select_i_agree_checkboxVenmo() {
        checkOutPage.selectIAgreeToTheCheckBoxVenmo();
        Log.info("Select I Agree To the Check box Venmo");
    }

    @Then("the user selects I agree checkbox Paypal")
    public void select_i_agree_checkboxPaypal() {
        checkOutPage.selectIAgreeToTheCheckBoxPaypal();
        Log.info("Select I Agree To the Check box Paypal");
    }

    @Then("the user saves credit or debit card credentials from {string} at row {int}")
    public void saves_credit_or_debit_card_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {
        /*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
        Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);*/

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String ccCardNumber = userData.getOrDefault("CCCardNumber", "");
        String ccExpDate = userData.getOrDefault("CCExpDate", "");
        String ccExpYear = userData.getOrDefault("CCExpYear", "");
        String cvv = userData.getOrDefault("CVV", "");
        checkOutPage.saveCreditOrDebitCardCredentials(ccCardNumber, ccExpDate, ccExpYear, cvv);
        Log.info("Save cc or debit card credentials");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "CC Number: " + ccCardNumber);

    }

    @Then("the user adds payment method credit or debit card credentials in my accounts from {string} at row {int}")
    public void add_payment_method_credit_or_debit_card_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {

        Log.info("Add payment method");
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        //  String cardType = userData.getOrDefault("CardType", "");
        String ccCardNumber = userData.getOrDefault("CCCardNumber", "");
        String ccExpDate = userData.getOrDefault("CCExpDate", "");
        String ccExpYear = userData.getOrDefault("CCExpYear", "");
        String cvv = userData.getOrDefault("CVV", "");

        Log.info("User enters First Name and Last Name in Payment Methods Credit Card section");
        checkOutPage.enterFirstName("John");
        checkOutPage.enterLastName("Doe");

        Log.info("Save cc or debit card credentials");
        checkOutPage.saveCreditOrDebitCardCredentials(ccCardNumber, ccExpDate, ccExpYear, cvv);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "CC Number: " + ccCardNumber);

//        //Billing Address
//        String address = userData.getOrDefault("Address", "");
//        String city = userData.getOrDefault("City", "");
//        String postalCode = userData.getOrDefault("PostalCode", "");
//        String state = userData.getOrDefault("State", "");
//        Log.info("Add billing address");
//        checkOutPage.addBillingAddress(address, city, postalCode, state);

        Log.info("Select I agree check box");
        checkOutPage.selectIAgreeCheckBoxCCAddPaymentMethod();

        Log.info("Clicking on 'Add payment method' button.");
        checkOutPage.clickAddPaymentMethodButton();

       /* Log.info("Checking "+cardType+" card is displayed in saved payment methods.");
        checkOutPage.isDiscoverCardDisplayed();*/
    }

    @Then("the user adds payment method credit card credentials in my accounts from {string} at row {int}")
    public void add_payment_method_credit_card_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String ccCardNumber = userData.getOrDefault("CCCardNumber", "");
        String ccExpDate = userData.getOrDefault("CCExpDate", "");
        String ccExpYear = userData.getOrDefault("CCExpYear", "");
        String cvv = userData.getOrDefault("CVV", "");
        Log.info("User enters First Name and Last Name in Payment Methods Credit Card section");
        checkOutPage.enterFirstName("John");
        checkOutPage.enterLastName("Doe");
        Log.info("Save cc or debit card credentials");
        checkOutPage.saveCreditOrDebitCardCredentials(ccCardNumber, ccExpDate, ccExpYear, cvv);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "CC Number: " + ccCardNumber);

        //Billing Address
        String address = userData.getOrDefault("Address", "");
        String city = userData.getOrDefault("City", "");
        String postalCode = userData.getOrDefault("PostalCode", "");
        String state = userData.getOrDefault("State", "");

        Log.info("Add billing address");
        if (!checkOutPage.isCheckedBillingAddCheckbox()) {
            checkOutPage.addBillingAddress(address, city, postalCode, state);
        }

        Log.info("Select I agree check box");
        checkOutPage.selectIAgreeCheckBoxCCAddPaymentMethod();

        Log.info("Clicking on Make Payment Button");
        checkOutPage.clickMakePaymentButton();
        Assert.assertTrue(checkOutPage.isPaymentSuccessfullDisplayed());
    }

    @Then("the user adds payment method credit card credentials in my accounts and save card from {string} at row {int}")
    public void add_payment_method_credit_card_andSaveCard_from_sheet(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String ccCardNumber = userData.getOrDefault("CCCardNumber", "");
        String ccExpDate = userData.getOrDefault("CCExpDate", "");
        String ccExpYear = userData.getOrDefault("CCExpYear", "");
        String cvv = userData.getOrDefault("CVV", "");
        Log.info("User enters First Name and Last Name in Payment Methods Credit Card section");
        checkOutPage.enterFirstName("John");
        checkOutPage.enterLastName("Doe");
        Log.info("Save cc or debit card credentials");
        checkOutPage.saveCreditOrDebitCardCredentials(ccCardNumber, ccExpDate, ccExpYear, cvv);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "CC Number: " + ccCardNumber);

        //Billing Address
        String address = userData.getOrDefault("Address", "");
        String city = userData.getOrDefault("City", "");
        String postalCode = userData.getOrDefault("PostalCode", "");
        String state = userData.getOrDefault("State", "");

        Log.info("Add billing address");
        if (!checkOutPage.isCheckedBillingAddCheckbox()) {
            checkOutPage.addBillingAddress(address, city, postalCode, state);
        }

        Log.info("Save Card Checkbox");
        checkOutPage.saveCardCheckBoxCCAddPaymentMethod();

        Log.info("Select I agree check box");
        checkOutPage.selectIAgreeCheckBoxCCAddPaymentMethod();

        Log.info("Clicking on Make Payment Button");
        checkOutPage.clickMakePaymentButton();
        Assert.assertTrue(checkOutPage.isPaymentSuccessfullDisplayed());
    }

    @Then("validate payment successful screen details")
    public void validatePaymentScreen() {
        String amount = checkOutPage.validatePaymentSuccessfulScreen();
        Assert.assertTrue(!amount.equals("0"), "Validated the payment amount is not zero");
    }

    @Then("Validate back to dashboard link is Displayed")
    public void validateBackToDashboard() {
        Assert.assertTrue(checkOutPage.backToDashboardLink(), "Back to dashboard link is displayed");
    }

    @Then("Validate payment success Message on Dashboard")
    public void paymentSuccessMsgDashboard() {
        Assert.assertTrue(checkOutPage.paymentSuccessMsgDashboard(), "Payment success msg is displayed");
    }

    @Then("the user makes payment with the saved card and validate payment success message from {string} at row {int}")
    public void makePaymentWithSavedCCFromSheet(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        //Get values from sheet
        String cardSaved = userData.getOrDefault("CardType", "");

        if (checkOutPage.isSavedCardDisplayed()) {
            Log.info("Saved Card displayed: " + cardSaved);
        }

        Log.info("Select I agree check box");
        checkOutPage.selectIAgreeCheckBoxCCAddPaymentMethod();

        Log.info("Clicking on Make Payment Button");
        checkOutPage.clickMakePaymentButtonSavedCard();
        Assert.assertTrue(checkOutPage.isPaymentSuccessfullDisplayed());
    }

    @Then("the user clicks on make payment button")
    public void makePayment() {
        Log.info("Clicking on Make Payment Button");
        checkOutPage.clickMakePaymentButton();
        Assert.assertTrue(checkOutPage.isPaymentSuccessfullDisplayed());
    }

    @Then("the user clicks on make payment button for Venmo")
    public void makePaymentVenmo() {
        Log.info("Clicking on Make Payment Button for Venmo");
        checkOutPage.clickMakePaymentButtonVenmo();
        Assert.assertTrue(checkOutPage.isPaymentSuccessfullDisplayed());
    }

    @Then("the user clicks on make payment button for PayPal")
    public void makePaymentPayPal() {
        Log.info("Clicking on Make Payment Button for PayPal");
        checkOutPage.clickMakePaymentButtonPayPal();
        Assert.assertTrue(checkOutPage.isPaymentSuccessfullDisplayed());
    }

    @Then("credit card is saved under Payment Methods section as per the sheet {string} at row {int}")
    public void credit_card_saved_under_payment_methods_section_as_per_the_sheet(String sheetName, int rowNumber) throws IOException {
    	try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        //Get values from sheet
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String cardType = userData.getOrDefault("CardType", "");
        String ccCardNumber = userData.getOrDefault("CCCardNumber", "");
        Log.info("Checking " + cardType + " card is displayed in saved payment methods");

        switch (cardType.toLowerCase()) {
            case "discover":
                Assert.assertTrue(checkOutPage.isDiscoverCardDisplayed(), "Discover Card is not displayed in saved methods");
                break;
            case "visa":
                Assert.assertTrue(checkOutPage.isVisaCardDisplayed(), "Visa Card is not displayed in saved methods");
                break;
            case "master":
                Assert.assertTrue(checkOutPage.isMasterCardDisplayed(), "Master Card is not displayed in saved methods");
                break;
            case "american express":
                Assert.assertTrue(checkOutPage.isAmericanExpressCardCardDisplayed(), "American Express Card is not displayed in saved methods");
                break;
            default:
                Assert.fail("Unsupported Card Type: " + cardType);
        }

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Type: " + cardType + " , CC Number: " + ccCardNumber);
    }

    @When("the user saves the payment type credentials from sheet {string} at row {int}")
    public void saves_payment_type_credentials_from_sheet(String sheetName, int rowNumber) throws IOException {

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String paymentType = userData.getOrDefault("PaymentType", "");
        Log.info("Select Payment Type: " + paymentType);

        switch (paymentType.toLowerCase()) {
            case "paypal":
                click_on_pay_pal_payment_method();
                save_paypal_credentials();
                break;
            case "venmo":
                click_on_venmo_payment_method();
                saves_venmo_credentials();
                break;
            case "creditcard":
                Log.info("Click On Credit card radio Button");
                //checkOutPage.clickOnCreditCardRadioButton();
                click_on_credit_card_payment_method();
                savesCreditOrDebitCardDetails();
                break;
            case "cashapp":
                //Log.info("Click On Credit card radio Button");
                //checkOutPage.clickOnCreditCardRadioButton();
                Log.info("Click On Cash app radio and cash app Button");
                click_on_cash_app_payment_method();
                Log.info("Clicked on cash app");
                break;
            case "applepay":
                Log.info("Apple pay");
                click_on_apple_pay_payment_method();
                break;
            case "googlepay":
                click_on_google_pay_payment_method();
                // saves_gpay_credentials_from_sheet();
                break;
        }
    }


    @Then("the user saves credit or debit card credentials")
    public void saves_credit_or_debit_card_credentials() {

        /*String ccNumber =Constants.CC_NUMBER;
        String ccExpDate =Constants.CC_EXP_DATE ;
        String ccExpYear =Constants.CC_EXP_YEAR ;
        String cvv =Constants.CC_CVV;
        checkOutPage.saveCreditOrDebitCardCredentials(ccNumber,ccExpDate,ccExpYear,cvv);
        Log.info("Save cc or debit card credentials: "+ccNumber);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"CC Number: " + ccNumber);*/

        savesCreditOrDebitCardDetails();

    }

    @When("the user clicks Submit And Continue To E Sign Button")
    public void click_submit_and_continue_to_e_sign_button() {
        Log.info("Click Submit and Continue TO E Sign Button");
        checkOutPage.clickSubmitAndContinueToESignButton();
    }


    @Then("the user clicks Submit And Continue To E Sign Button {string}")
    public void the_user_clicks_submit_and_continue_to_e_sign_button(String paymentType) {
        Log.info("Click Submit and Continue TO E Sign Button: " + paymentType);
        checkOutPage.clickSubmitAndContinueToESignButton(paymentType);
    }

    @Then("the order confirmation success message should be displayed")
    public void verify_order_confirmation_success_message_should_be_displayed() {
        Assert.assertTrue(checkOutPage.verifyOrderConfirmationMessageDisplayed(),
                "Order confirmation message NOT displayed");
        Log.info("Order confirmation message displayed");
    }

    @Then("venmo payment details success message displayed")
    public void venmo_payment_details_success_message_displayed() {

    }

    // *** Reusable methods
    public void savesCreditOrDebitCardDetails() {
        String ccNumber = Constants.CC_NUMBER;
        String ccExpDate = Constants.CC_EXP_DATE;
        String ccExpYear = Constants.CC_EXP_YEAR;
        String cvv = Constants.CC_CVV;
        checkOutPage.saveCreditOrDebitCardCredentials(ccNumber, ccExpDate, ccExpYear, cvv);
        Log.info("Save cc or debit card credentials: " + ccNumber);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "CC Number: " + ccNumber);
    }

    @When("the user selects Save this payment method check box")
    public void userSelectsSaveThisPaymentMethodCheckBox() {
        Log.info("Selecting 'Save this payment method' checkbox");
        checkOutPage.selectSaveThisPaymentMethodCheckbox();
    }

    @When("the user selects the {string} checkbox in My Account PayPal payment")
    public void select_the_checkbox_in_my_account_pay_pal_payment(String chkBoxName) {
        checkOutPage.selectIAgreeCheckBoxPaypalAddPayMentsMyAccounts();
        Log.info("Select Check box " + chkBoxName);
    }

    @Then("the PayPal payment method is saved under the Payment Methods section")
    public void verify_payment_method_is_saved_under_the_payment_methods_section() {
        Log.info("Checking paypal is displayed in saved payment methods");
        Assert.assertTrue(checkOutPage.isPayPalMethodDisplayed(), "PayPal not displayed in saved methods");

    }

    @Then("the bank account method is saved under the Payment Methods section")
    public void verify_bank_account_method_is_saved_under_the_payment_methods_section() {

        Log.info("Checking bank account is displayed in saved payment methods");
        Assert.assertTrue(checkOutPage.isBankAccountDisplayed(), "bank account not displayed in saved methods");

    }


    @When("the user clicks on Enroll in AutoPay button in account details")
    public void click_on_enroll_in_auto_pay_button() {
        Log.info("click enroll in Auto pay button");
        checkOutPage.clickEnrollInAutoPayButton();
    }

    @When("the user clicks I agree to the check box enroll auto pay")
    public void click_i_agree_to_the_check_box_enroll_auto_pay() {
        Log.info("Select check box I agree enroll auto pay");
        checkOutPage.selectIAgreeToTheCheckBoxEnrollAutoPay();
    }

    @When("the user clicks I agree to the check box enroll auto pay paypal")
    public void click_i_agree_to_the_check_box_enroll_auto_pay_paypal() {
        Log.info("Select check box I agree enroll auto pay");
        checkOutPage.selectIAgreeToTheCheckBoxEnrollAutoPayPaypal();
    }

    @When("the user clicks the {string} button under saved payment methods")
    public void the_user_clicks_the_enroll_in_auto_pay_button_under_saved_payment_methods(String buttonName) {
        Log.info("click button Name: " + buttonName);
        checkOutPage.clickEnrollInAutoPayButtonUnderSavedPaymentMethods();
    }

    @Then("{string} text is displayed")
    public void text_is_displayed(String actText) {
        Log.info("Text: " + actText);
        Assert.assertTrue(basePage.isTextDisplayed(actText), "Displayed text: " + actText);
    }

    @When("the user selects docusign check box I sign")
    public void userSelectsDocusignCheckBox() {
        Assert.assertFalse(checkOutPage.isServiceFailureDisplayedCheckout(), "Payment failed service failure displayed");
        Log.info("Selecting the DocuSign checkbox: 'I sign'");
        checkOutPage.clickISignCheckbox();
    }

    @Then("the user verifies continue button is displayed under docusign section")
    public void verifyContinueButtonDisplayedUnderDocuSignSection() {
        Log.info("Verifying Continue button is displayed under DocuSign section");
        Assert.assertTrue(checkOutPage.isContinueButtonUnderDocuSignDisplayed(), "Continue button under DocuSign is not displayed");
    }

    @When("the user clicks on the I Agree and Continue button")
    public void click_on_the_i_agree_continue_button() {
        Log.info("Click button: I Agree and Continue");
        checkOutPage.clickIAgreeAndContinueButton();
    }


    @When("the user clicks continue button under docusign section")
    public void userClicksContinueUnderDocusignSection() {
        Log.info("Clicking the Continue button under DocuSign section");
        checkOutPage.clickContinueDocusignButton();
    }

    @Then("button {string} is displayed")
    public void verifyButtonIsDisplayed(String buttonText) {
        Log.info("Verifying button '" + buttonText + "' is displayed");
        Assert.assertTrue(checkOutPage.isButtonDisplayed(buttonText));
    }

    /*@Then("the order confirmation success message should be displayed")
    public void verifyOrderConfirmationMessage() {
        Log.info("Verifying order confirmation success message is displayed");
        Assert.assertTrue(checkOutPage.isOrderSuccessMessageDisplayed());
    }*/

    @And("agreement number is generated")
    public void verifyAgreementNumberGenerated() {
        Log.info("Verifying agreement number is generated");
        checkOutPage.isAgreementNumberDisplayed();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Agreement Number: " + checkOutPage.extractAgreementNumber());
    }

    @Then("Reserve Your Product screen shall be displayed")
    public void reserve_your_product_screen_shall_be_displayed() {
        Log.info("Reservation header screen is displayed");
        Assert.assertTrue(checkOutPage.isReservationHeaderDisplayed(),
                "Reservation header screen is not displayed");
    }

    @Then("Claim Offer screen shall be displayed")
    public void claim_offer_screen_shall_be_displayed() {
        Log.info("Claim offer header screen is displayed");
        Assert.assertTrue(checkOutPage.isClaimOfferDisplayed(),
                "Claim offer header screen is not displayed");
    }

    @Then("the promo box is displayed with the applicable promo details")
    public void the_promo_box_is_displayed_with_the_applicable_promo_details() {
        Assert.assertTrue(checkOutPage.isPromoCodeDisplayed(),
                "promo code is not displayed");
        Log.info("Promo code is displayed");
    }

    @Then("the promo code is not displayed on the screen")
    public void the_promo_code_is_not_displayed_on_the_screen() {

        Assert.assertFalse(checkOutPage.isPromoCodeDisplayed(),
                "promo code is displayed on the screen");
        Log.info("Promo code is not displayed");
    }

    @When("the user clicks on {string} link under Bank verification section")
    public void the_user_clicks_on_link_under_bank_verification_section(String linkName) {
        Log.info("Click on link name: " + linkName);
        basePage.jsClickLinkByName(linkName);
        Log.info("Clicked on link name: " + linkName);
    }

    @When("the user clicks on {string} link name")
    public void click_on_link(String linkName) {
        Log.info("click on link name: " + linkName);
        homePage.clickOnLinkName(linkName);
    }

    @Then("Bank Verification screen is displayed")
    public void bank_verification_screen_is_displayed() {
        Assert.assertTrue(checkOutPage.isInstantBankVerificationDisplayed(),
                "Bank Verification screen is not displayed");
        Log.info("Bank Verification screen is displayed");
    }

    @Then("Checkout Online Now tool tip displayed")
    public void checkout_online_now_tool_tip_displayed() {
        checkOutPage.isCheckoutOnlineNowToolTiDisplayed();
        Assert.assertTrue(checkOutPage.isCheckoutOnlineNowToolTiDisplayed(),
                "Checkout Online Now tool tip NOT displayed");
        Log.info("Checkout Online Now tool tip displayed");
    }

    @When("the user selects next available date on payschedule page")
    public void theUserSelectsNextAvailableDateOnPayschedulePage() {
        checkOutPage.openCalenderOnPayschedule();
        checkOutPage.selectNextAvailablePaymentDate();
        Log.info("Selected paid next date");
    }


    @Then("verify pricing and renewal rate calculations on renewal terms page")
    public void verifyPricingAndRenewalRateCalculationsOnRenewalTermsPage() {
        checkOutPage.waitForLoadingSpinnerToDisappear();
        checkOutPage.clickPricingInfoOnPDP();
        Assert.assertTrue(checkOutPage.verifySameAsCashAndTotalCostAreSame(), "SameAsCashAndTotalCost are not matched");
    }

    @When("the user clicks on Cash app payment method")
    public void click_on_cash_app_payment_method() {
        Log.info("Click On Cash app radio Button");
        checkOutPage.clickOnCashAppPayRadioButton();
        Log.info("Click On Cash app pay Button");
        checkOutPage.clickOnCashAppPayButton();
    }

    @When("the user clicks on Continue button")
    public void click_continue_button() {
        checkOutPage.clickContinue();
    }

    @And("the user clicks on {string} link on payfone page")
    public void theUserClicksOnLinkOnPayfonePage(String arg0) {
//        ToDo
    }

    @When("the user verifies Benefit plus product details display")
    public void the_user_verifies_benefit_plus_product_display() {
        Assert.assertTrue(checkOutPage.isBenefitPlusProductDisplayed(), "Benefit Plus product " +
                "is NOT displayed");
        Log.info("Benefit Plus product displayed");
    }

    @Then("verify approval message for {string}")
    public void verifyApprovalMessageFor(String userType) {
        System.out.println("Validating approval for: " + userType);
        Assert.assertTrue(checkOutPage.verifyApprovalMessage(userType), "Approval message is not as expected");
    }


    @When("the user clicks on Apply for Instant Approval button")
    public void theUserClicksOnApplyForInstantApprovalButton() {
        homePage.applyInstantApproval();
    }

    @When("the user clicks on Apply for Instant Approval button perf")
    public void theUserClicksOnApplyForInstantApprovalButtonPerf() {
        homePage.applyInstantApprovalPerf();
    }

    @When("the user clicks on the Congrats popup from {string} at row {int}")
    public void click_on_the_congrats_popup(String sheetName, int rowNumber) throws IOException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String approval = rowData.getOrDefault("Approval", "").trim();
        if (approval.equalsIgnoreCase("BAV Checkout Approval")) {
            checkOutPage.clickBAVContinueButtonOnCongratsPopUp();
            Log.info("Clicked on BAV Continue Button on Congrats Pop up");
        } else if (approval.equalsIgnoreCase("Checkout Approval")) {
            checkOutPage.clickContinueButtonOnCongratsPopUp();
            Log.info("Clicked on Continue Button on Congrats Pop up");
        } else if (approval.equalsIgnoreCase("Conditional checkout Approval")) {
            checkOutPage.clickOnReserveButtonOnCongratsPopUp();
            Log.info("Clicked on Reserve Button on Congrats Pop up");
        } else if (approval.equalsIgnoreCase("Reservation")) {
            checkOutPage.clickOnReserveMyProductButtonOnCongratsPopUp();
            Log.info("Clicked on Reserve My Product Button on Congrats Pop up");
        } else if (approval.equalsIgnoreCase("ClaimOffer")) {
            checkOutPage.clickOnReserveMyProductButtonOnCongratsPopUp();
            Log.info("Clicked on Reserve My Product Button on Congrats Pop up");
        } else if (approval.equalsIgnoreCase("Denied")) {
            Assert.assertTrue(orderSubmitPage.isDeniedMessageIsDisplayed(),
                    "Denied message is NOT displayed");
            Log.info("Denied message is displayed");
        }
    }

    @When("the user enters BAV from {string} at row {int}")
    public void enterBankVerificationDetailsFromSheet(String sheetName, int rowNumber) throws IOException, InterruptedException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String bankRoutingNumber = rowData.getOrDefault("BankRoutingNumber", "").trim();
        String bankAccountNumber = rowData.getOrDefault("BankAccountNumber", "").trim();
        String bav = rowData.getOrDefault("BAV", "").trim();
        if (bav.equalsIgnoreCase("Yes")) {
            checkOutPage.enterBankVerificationDetails(bankRoutingNumber, bankAccountNumber);
            Log.info("Enter bank routing number: " + bankRoutingNumber);
            Log.info("Enter bank account number: " + bankAccountNumber);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter bank routing number: " + bankRoutingNumber);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter bank account number: " + bankAccountNumber);
        }
    }

    @Then("verify approved section is displayed from {string} at row {int}")
    public void verify_approved_payment_is_displayed(String sheetName, int rowNumber) throws IOException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String approved = rowData.getOrDefault("Approved", "").trim();
        if (approved.isEmpty()) {
            Log.info("Approved payment section verification is not required");
        } else {
            Assert.assertTrue(checkOutPage.isPaymentSectionIsDisplayed(approved),
                    "Approved payment section is: " + approved + " NOT displayed");
            Log.info("Approved payment section: " + approved + " is displayed");
        }
    }


    @Then("the user Esign document and click on finish")
    public void theUserEsignDocumentAndClickOnFinish() {
        checkOutPage.clickonNextOnDocuSign();
        checkOutPage.esignDocumentWithAllSignatures();
        checkOutPage.clickonFinishOnDocuSign();
        basePage.switchToDefaultContent();
    }

    @Then("the user apply promo and verify Total Due from {string} at row {int}")
    public void the_user_apply_promo_and_verify_total_due_from_at_row(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String promoCode = userData.getOrDefault("PromoCode", "");
        String totalDue = userData.getOrDefault("TotalDue", "");

        checkOutPage.applyPromo(promoCode);
        Assert.assertTrue(checkOutPage.verifyTotalDueAfterApplyingPromo(promoCode, totalDue), "Total amount due is not reduced as expected ");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "promo: " + promoCode);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Total Due after applying promo: " + totalDue);
    }

    @Then("the user verify ldw and bp not displayed on renewal terms page")
    public void theUserVerifyLdwAndBpNotDisplayedOnRenewalTermsPage() {
        Assert.assertTrue(checkOutPage.verifyLdwAndBpNotDisplayed(), "LDW or BP or both displayed which is not expected ");
    }

    @Then("the user verify benefits modal auto displayed when scrolled to Continue CTA")
    public void theUserVerifyBenefitsModalAutoDisplayedWhenScrolledToContinueCTA() {
        checkOutPage.scrollToContinuePaymentBtn();
        Assert.assertTrue(checkOutPage.verifyBenefitsModalIsDisplayed(), "Benefits modal is not auto displayed");
        checkOutPage.closeBenefitsModal();
        // toggle on LDW
        checkOutPage.toggleLDW();
        //toggle on BP
        checkOutPage.toggleBenfitsPlus();

        Assert.assertTrue(checkOutPage.verifyLDWToggledOn(), "LDW is not selected");
        Assert.assertTrue(checkOutPage.verifyBPToggledOn(), "Bp is not selected");

        //toggle off LDW
        checkOutPage.toggleLDW();

        //check LDW and BP toggled off
        Assert.assertTrue(checkOutPage.verifyLDWToggledoff(), "LDW is selected");
        Assert.assertTrue(checkOutPage.verifyBenfitsPlusToggledoff(), "Bp is selected");
    }

    @Then("the user verify store specific features for the georgia store wrt ldw and bp")
    public void theUserVerifyStoreSpecificFeaturesForTheGeorgiaStoreWrtLdwAndBp() {
//       checkOutPage.waitForLoadingSpinnerToDisappear();
        //check LDW and BP toggled off on page load
        Assert.assertTrue(checkOutPage.verifyLDWToggledoff(), "LDW is selected");
        Assert.assertTrue(checkOutPage.verifyBenfitsPlusToggledoff(), "Bp is selected");

        checkOutPage.toggleLDW();
        Assert.assertTrue(checkOutPage.verifyLDWToggledOn(), "LDW is not selected");
        Assert.assertTrue(checkOutPage.verifyBenfitsPlusToggledoff(), "Bp is selected");

        checkOutPage.toggleLDW();
        checkOutPage.toggleBenfitsPlus();
        Assert.assertTrue(checkOutPage.verifyLDWToggledOn(), "LDW is not selected");
        Assert.assertTrue(checkOutPage.verifyBPToggledOn(), "Bp is not selected");

        //Verify benefit plus unlock test
//        Assert.assertTrue(checkOutPage.verifyBenfitsPlusUnlocktextIsDisplayed("Unlock access to loads of benefits and discounts (Only available with LDW)"),
//                "unlock Text is not displayed");

        //Verfiy clicking on Learn more, opens up a modal
        checkOutPage.clickLearnMore();
        checkOutPage.verifyBenefitsModalIsDisplayed();

        //check LDW and BP toggled On on modal
        Assert.assertTrue(checkOutPage.verifyLDWToggledOnOnModal(), "LDW is not selected");
        Assert.assertTrue(checkOutPage.verifyBPToggledOnOnModal(), "Bp is not selected");

        //Toggle off BP
        checkOutPage.toggleBenfitsPlusOnModal();

        //check LDW  on and BP toggled off
        Assert.assertTrue(checkOutPage.verifyLDWToggledoffOnModal(), "LDW is not selected");
        Assert.assertTrue(checkOutPage.verifyBenfitsPlusToggledoffOnModal(), "Bp is selected");

        checkOutPage.closeBenefitsModal();

        //check LDW on and BP toggled off Page
        Assert.assertTrue(checkOutPage.verifyLDWToggledOn(), "LDW is not selected");
        Assert.assertTrue(checkOutPage.verifyBenfitsPlusToggledoff(), "Bp is selected");


    }

    @Then("the user clicks on Sign Agreement Later on docu sign page")
    public void theUserClicksOnSignAgreementLaterOnDocuSignPage() {
        checkOutPage.clickSignAgreementLater();
    }

    @Then("verify reserve my product section is displayed")
    public void verify_reserve_my_product_section_is_displayed() throws IOException {
        Assert.assertTrue(checkOutPage.isReserveMyProductSectionIsDisplayed(),
                "Reserve my product section is NOT displayed");
        Log.info("Reserve my product section is displayed");
    }

    @When("the user clicks on submit my contact info link")
    public void click_on_submit_my_contact_info_link() {
        checkOutPage.clickOnSubmitMyContactInfoLink();
        Log.info("Clicked On submit my contact info link");
    }

    @When("the user click on reserve & complete in store link")
    public void click_on_reserve_complete_in_store_link() {
        checkOutPage.clickOnReserveCompleteInStoreLink();
        Log.info("Clicked On reserve complete in store link");
    }

    @Then("the user gets correlation id")
    public void the_user_gets_gets_correlation_id() {

        Set<String> correlationIDs = checkOutPage.getCorrelationIDFromChromeDevTools();
        for (String id : correlationIDs) {
            Log.info("Correlation ID: " + id);
        }
        //Log.info("Correlation id: "+correlationID);
        //ExtentCucumberAdapter.addTestStepLog("Correlation ID: "+correlationID);
    }

    @Then("the user gets correlation id {string}")
    public void the_user_gets_correlation_id(String paymentType) {
        Set<String> correlationIDs = checkOutPage.getCorrelationIDFromChromeDevTools(paymentType);
        for (String id : correlationIDs) {
            Log.info(paymentType + "After return Correlation ID: " + id);
            ExtentCucumberAdapter.addTestStepLog(paymentType + "After return Correlation ID: " + id);
        }
    }

    @When("the user click Set My Password button on confirmation page")
    public void theUserClickSetMyPasswordButtonOnConfirmationPage() {
        checkOutPage.clickSetMyPasswordButton();
    }

    @Then("the user login to account after password setup")
    public void theUserLoginToAccountAfterPasswordSetup() throws InterruptedException {
        String email = checkOutPage.fetchEmailIdUsed();
        System.out.println(email);
        checkOutPage.enterPasswordAndContinue();
        checkOutPage.wait(5000);
        signInPage.enterEmailAndPassword(email,"Test@1234");
        wait(5000);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Email Address: " + email);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Password: " + "Test@1234");
        signInPage.clickOnSignInBtnMyAccountDlg();

    }

    @And("the user toggle benefit plus checkbox")
    public void theUserToggleBenefitplusCheckbox() {
        checkOutPage.toggleBenfitsPlus();
    }

    @When("the user clicks on Enroll Now on BP modal")
    public void theUserClicksOnEnrollNowOnBPModal() {
        checkOutPage.clickEnrollNowBtnOnBPmodal();
    }

    @And("the user clicks on the I Agree and Continue button On BP Start order page")
    public void theUserClicksOnTheIAgreeAndContinueButtonOnBPStartOrderPage() {
        Log.info("Click button: I Agree and Continue on bp start order page");
        checkOutPage.clickIAgreeAndContinueButton_BpStartOrderPage();
    }

    @When("the user close the BP modal on checkout confirmation page")
    public void theUserCloseTheBPModalOnCheckoutConfirmationPage() {
        Assert.assertTrue(checkOutPage.verifyBPModalIsDisplayed(), "BP modal is not displayed");
        checkOutPage.closeBenefitsModal_CheckoutConfirmationPage();
    }

    @And("the user verify BP component on Dashboard and click on view")
    public void theUserVerifyBPComponentOnDashboardAndClickOnView() {
        Assert.assertTrue(checkOutPage.verifyBPComponentIsDisplayed(), "BP component is not displayed");
        checkOutPage.clickViewOnBpComponent();

    }

    @Then("the user should allow to reserve the product for {string}")
    public void verify_reserve_the_product_total_amount(String expTotalDueAmount) {
        Assert.assertEquals(checkOutPage.getTotalDueToday(), expTotalDueAmount,
                "Reserve the product total NOT matched");
        Log.info("Reserve the product total matched");
    }

    @Then("The refundable deposit is displayed with the correct high-risk label and amount {string}")
    public void the_refundable_deposit_is_displayed_with_the_correct_high_risk_label_and_amount(String refDepositAmount) {
        Assert.assertEquals(checkOutPage.getTotalDueToday(), refDepositAmount,
                "Refundable Deposit Amount NOT matched");
        Log.info("Refundable Deposit Amount matched: " + refDepositAmount);
    }


    //Author: Nidhi (added on 28-05-2025)

    @Then("the user apply text enabled promo code and verify Total Due from {string} at row {int}")

    public void the_user_apply_text_enabled_promo_code_and_verify_total_due(String sheetName, int rowNumber) throws IOException {

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);

        String promoCode = userData.getOrDefault("PromoCode", "");

        String totalDue = userData.getOrDefault("TotalDue", "");

        checkOutPage.enterPromoCode(promoCode);

        checkOutPage.clickApplyButton();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "promo: " + promoCode);

        Assert.assertTrue(checkOutPage.verifyTotalDueAfterApplyingPromo(promoCode, totalDue), "Total amount due is not reduced as expected ");

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Total Due after applying promo: " + totalDue);

    }

//    Author: Nidhi (added on 28-05-2025)

    @And("the user verify list of payment methods available on Promo code from {string} at row {int}")

    public void the_user_verify_list_of_payment_methods_available_on_promo_code(String sheetName, int rowNumber) throws IOException {

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);

        String promoCode = userData.getOrDefault("PromoCode", "");

        int availablePaymentMethods = checkOutPage.getListOfItemsInPaymentMethods();

        Log.info("availablePaymentMethods:" + availablePaymentMethods);

        if (promoCode.equalsIgnoreCase("0PENNY")||promoCode.equalsIgnoreCase("0PAYS")) {

            Assert.assertEquals(availablePaymentMethods, 1, "invalid for zero amount payment");

        }

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "list of payment methods items " + availablePaymentMethods);

    }

    @When("the user clicks on the CONTINUE button on the Congrats popup Reg user")
    public void click_on_the_continue_button_on_the_congrats_popup_reg_user() {
        Log.info("Click Continue Button on Congrats Pop up reg user");
        checkOutPage.clickContinueButtonOnCongratsPopUpRegUser();
    }

    @Then("the user selects I agree checkbox Reg user")
    public void select_i_agree_checkbox_reg_user() {
        Log.info("Select I Agree To the Check box reg user");
        checkOutPage.selectIAgreeToTheCheckBoxRegUser();
    }

    @Then("verify pricing and renewal rate calculations on renewal terms page from {string} at row {int}")
    public void verify_pricing_and_renewal_rate_calculations_on_renewal_terms_page_from_at_row(String sheetName, Integer rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String paySchedule = userData.getOrDefault("PaySchedule", "");
        checkOutPage.waitForLoadingSpinnerToDisappear();
        checkOutPage.clickPricingInfoOnPDP();
        Assert.assertTrue(checkOutPage.verifySameAsCashAndTotalCostAreSame(), "SameAsCashAndTotalCost are not matched");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "SameAsCashAndTotalCost are not matched");

        Assert.assertTrue(checkOutPage.verifyRequiredFieldsAreDisplayedOnRenewalTermsPage(paySchedule), "Field values, Number of Payments,Payment Frequency,Early Purchase Option Not displayed on page as expected");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Field values, Number of Payments,Payment Frequency,Early Purchase Option Not displayed on page as expected");
    }

    @Then("the user verify no promos displayed on renewal terms page")
    public void theUserVerifyNoPromosDisplayedOnRenewalTermsPage() {
        Assert.assertTrue(checkOutPage.verifyNoPromosDisplayedOnPage(), "One or more Promos displayed In Renewal terms Page");

    }

    @When("the user clicks on the CONTINUE button on the Congrats popup if displayed")
    public void click_on_the_continue_button_on_the_congrats_popup_if_displayed() {
        Log.info("Click Continue button on Congrats Pop up if displayed");
        checkOutPage.clickContinueButtonOnCongratsPopUpIfDisplayed();
    }

    @Then("the user selects I agree checkbox under Apple pay section")
    public void select_i_agree_checkbox_apple_pay() {
        Log.info("Select I Agree To the Check box apple pay");
        checkOutPage.selectIAgreeToTheCheckBoxApplePay();
    }

    @Then("verify spinner is displayed while page is loading")
    public void verifySpinnerIsDisplayedWhilePageIsLoading() {
        checkOutPage.verifyLoadingSpinnerIsDisplayed();
    }

    @Then("the user selects I agree checkbox cash app")
    public void select_i_agree_checkbox_cash_app() {
        Log.info("Select I Agree To the Check box cash app");
        checkOutPage.selectIAgreeToTheCheckBoxCashApp();
    }

    @When("the user selects Save this payment method check box cash app")
    public void userSelectsSaveThisPaymentMethodCheckBoxCashApp() {
        Log.info("Selecting 'Save this payment method' checkbox cash app");
        checkOutPage.selectSaveThisPaymentMethodCheckboxCashApp();
    }

    @Then("Save card and enroll me in AutoPay check box shall not be displayed")
    public void save_card_and_enroll_me_in_auto_pay_check_box_shall_not_be_displayed() {
        Log.info("enroll me in AutoPay check box is not displayed");
        Assert.assertFalse(checkOutPage.isEnrollMeInAutoPayCheckBoxDisplayed(), "enroll me in AutoPay check box displayed");
        Log.info("enroll me in AutoPay check box is not displayed");
    }

    @Then("Reservation screen shall be displayed")
    public void reservation_screen_shall_be_displayed() {
        Log.info("Reservation header screen is displayed");
        Assert.assertTrue(checkOutPage.isReserveYourProductHeaderDisplayed(),
                "Reservation header screen is not displayed");
    }

    @When("the user clicks RESERVE MY PRODUCT button if displayed on almost done dialog")
    public void click_on_the_continue_button_on_the_reserve_my_product_button_if_displayed() {
        Log.info("Click RESERVE MY PRODUCT button on Congrats Pop up if displayed");
        checkOutPage.clickReserveMyProductIfDisplayed();
    }

    @Then("the user clicks Continue button under apple payment section")
    public void click_continue_button_apple_pay() {
        Log.info("click continue button apple pay");
        checkOutPage.clickContinueButtonApplePay();
    }

    @When("the user clicks the {string} button in the Cash App payment method section")
    public void the_user_clicks_the_button_in_the_cash_app_payment_method_section(String buttonName) {
        Log.info("click button: " + buttonName);
        basePage.clickButtonByName(buttonName);
    }

//    @When("the User clicks on the Reserve My Product button on Reservation Popup")
//    public void click_on_the_Reserve_My_Product_Button_reservation_popup(){
//        Log.info("click on the Reserve My Product button on Reservation Popup");
//        checkOutPage.clickReservationButton();
//    }


    @When("the user apply text enabled promo code and verify Total Due Amount from {string} at row {int}")

    public void the_user_apply_text_enabled_promo_code_and_verify_total_dueAmount(String sheetName, int rowNumber) throws IOException {

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String promoCode = userData.getOrDefault("PromoCode", "");
        String totalDue = userData.getOrDefault("TotalDue", "");
        String expectedPromoName = userData.getOrDefault("ExpectedPromoName", "");
        String expectedTotalDue = userData.getOrDefault("ExpectedTotalDue", "");
        Log.info("Enter promo code: " + promoCode);
        checkOutPage.enterPromoCode(promoCode);
        Log.info("Click apply button");
        checkOutPage.clickApplyButton();
        Assert.assertTrue(checkOutPage.verifyTotalDueAfterApplyingDesiredPromo(promoCode, totalDue, expectedPromoName, expectedTotalDue), "Total amount due is not reduced as expected ");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Promo: " + promoCode);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Total Due after applying promo: " + totalDue);
    }

    @Then("the user verify list of All payment methods available on Promo code from {string} at row {int}")

    public void the_user_verify_list_of_All_payment_methods_available_on_promo_code(String sheetName, int rowNumber) throws IOException {

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String promoCode = userData.getOrDefault("PromoCode", "");

        int availablePaymentMethods = checkOutPage.getListOfItemsInPaymentMethods();
        String totalPaymentsDisplayed = checkOutPage.getListOfPaymentMethodsDisplayed();
        Log.info("availablePaymentMethods:" + availablePaymentMethods + " payment type displayed :" + totalPaymentsDisplayed);

        if (promoCode.equalsIgnoreCase("0PENNY")) {
            Assert.assertEquals(availablePaymentMethods, 1, "invalid for zero amount payment");
        }
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Number of payment methods items displayed: "
                + availablePaymentMethods + " and Names of Payment " + totalPaymentsDisplayed);
    }

    @And("Reservation number is generated")
    public void verifyReservationNumberGenerated() {

        Assert.assertTrue(checkOutPage.isReservationNumberDisplayed(), "Reservation Number is not displayed");
        String reservationNumber = checkOutPage.extractReservationNumber();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Reservation Number: " + reservationNumber);
        Log.info("Verifying reservation number: " + reservationNumber);
    }


    @And("the user verify list of saved payment methods available on Promo code from {string} at row {int}")
    public void theUserVerifyListOfSavedPaymentMethodsAvailableOnPromoCodeFromAtRowRowNumber(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String promoCode = userData.getOrDefault("PromoCode", "");
        Map.Entry<Boolean, List<String>> result = checkOutPage.verifyOnlyCCSavedMethodsIsDisplayed();
        boolean isOnlySavedCCMethodsDisplayed = result.getKey();
        List<String> listOfSavedMethodsDisplayed = result.getValue();

        if (promoCode.equalsIgnoreCase("0PENNY")) {
            Assert.assertTrue(isOnlySavedCCMethodsDisplayed, " cc payment method is not displayed");
        }
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "list of saved payment methods items: " + listOfSavedMethodsDisplayed);

    }

    @And("the user selects one of the saved cc payment methods")
    public void theUserSelectsOneOfTheSavedCcPaymentMethods() {
        checkOutPage.selectSavedCCPaymentMethod();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user selects one of the saved cc payment methods ");

    }

    @And("the user ticks on agree to Terms and  Enroll to Autopay")
    public void theUserTicksOnAgreeToTermsAndEnrollToAutopay() {
        checkOutPage.selectAgreeToTermsAndEnrollToAutopay();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user ticks on agree to Terms and  Enroll to Autopay ");

    }

    @When("the user clicks on ssn tooltip button in Identification verification dialog")
    public void theUserClicksOnSsnTooltipButtonInIdentificationVerificationDialog() {
        checkOutPage.clickOnSSNTooltipButton();
        Log.info("the user clicks on ssn tooltip button in Identification verification dialog");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user clicks on ssn tooltip button in Identification verification dialog ");

    }

    @Then("the tooltip opens and configured information is displayed")
    public void theTooltipOpensAndConfiguredInformationIsDisplayed() {
        Assert.assertTrue(checkOutPage.verifySSNToolTipContents(), "tooltip contents are not equal");
        Log.info("the tooltip opens and configured information is displayed");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the tooltip opens and configured information is displayed ");
    }

    @Then("the user clicks on X button to close the tooltip")
    public void theUserClicksOnXButtonToCloseTheTooltip() {
        Assert.assertTrue(checkOutPage.clickOnSSNTooltipCloseButton(), "Tooltip is not getting Closed");
        Log.info("the user clicks on X button to close the tooltip");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "tooltip closed on clicking X button");
    }

    @Then("the user validates the ssn input field for different inputs")
    public void theUserValidatesTheSsnInputFieldForDifferentInputs() {
        Assert.assertTrue(checkOutPage.testAlphabeticInputIsRejected(), "Invalid :Aphabetic input is accepted");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Invalid Input:Aphabets are not accepted ");
        Assert.assertTrue(checkOutPage.testSpecialCharactersAreRejected(), "Invalid :Special characters input is accepted");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Invalid Input: Special characters are not accepted ");
        Assert.assertTrue(checkOutPage.testNumericInputIsAcceptedAndMasked(), "Valid: valid input content is not accepted or masked properly ");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Valid Input: Numeric values are accepted and masked ");
    }

    @Then("the user validates the DOB input field for different inputs")
    public void theUserValidatesTheDOBInputFieldForDifferentInputs() {
        Assert.assertTrue(checkOutPage.validateHyphenInsertionDOBFormat(), "Invalid :Hyphen is not inserted automatically");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Valid Input:Hyphen is inserted automatically for valid DOB input");
        Assert.assertTrue(checkOutPage.testInvalidFormatMsgDisplayedOnWrongDOBFormat(), "Invalid :Wrong date format accepted");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Invalid Input: error msg for invalid DOB format is displayed ");

    }

    @Then("the Third Party Consent hyperlink is visible on the Payphone page under Legal Verbiage")
    public void theThirdPartyConsentHyperlinkIsVisibleOnThePayphonePageUnderLegalVerbiage() {
        Assert.assertTrue(checkOutPage.testConsentFormLinkIsDisplayed(), "Consent link is not visible");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the Third Party Consent hyperlink is visible on the Payphone page under Legal Verbiage");
    }


    @And("the pop-up contains the correct information when opened from the hyperlink")
    public void thePopUpContainsTheCorrectInformationWhenOpenedFromTheHyperlink() {
        Assert.assertTrue(checkOutPage.testPopupContentIsCorrect(), "the Consent popup consent is different");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the pop-up contains the correct information when opened from the hyperlink");
    }

    @Then("the user clicks on X button to close the popup.")
    public void theUserClicksOnXButtonToCloseThePopup() {
        Assert.assertTrue(checkOutPage.testClosePopupWithXButton(), "the Consent popup is not closed on X button");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user clicks on X button to close the popup");
    }


    @And("third party consent dialog is displayed")
    public void thirdPartyConsentDialogIsDisplayed() {
        Assert.assertTrue(checkOutPage.testPopupAppearsOnClick(), "the consent popup didn't appear");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "third party consent dialog is displayed");
    }

    @Then("the {string} Label is displayed")
    public void theLabelIsDisplayed(String label) {
        Assert.assertTrue(checkOutPage.testSecureFormTextIsDisplayed(), "the Secure Form text is not displayed");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + label + " is displayed");
    }


    @And("the user validates the mobile input")
    public void theUserValidatesTheMobileInput(DataTable dataTable) {
        List<Map<String, String>> inputList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : inputList) {
            String inputType = row.get("Input");
            String inputValue = row.get("Input Value");
            Assert.assertTrue(checkOutPage.testMobileInPayfone(inputType, inputValue), inputType + " Mobile input type with value " + inputValue + " is not giving desired output");
            if (inputType.equalsIgnoreCase("valid"))
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + inputType + " Mobile input type with value " + inputValue + " gives inline green tick");
            else
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + inputType + " Mobile input type with value " + inputValue + " gives error msg");
        }
    }


    @And("the user validates the email input")
    public void theUserValidatesTheEmailInput(DataTable dataTable) {
        List<Map<String, String>> inputList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : inputList) {
            String inputType = row.get("Input");
            String inputValue = row.get("Input Value");
            Assert.assertTrue(checkOutPage.testEmailInPayfone(inputType, inputValue), inputType + " Email input type with value " + inputValue + " is not giving desired output");
            if (inputType.equalsIgnoreCase("valid"))
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + inputType + " Email input type with value " + inputValue + " gives inline green tick");
            else
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + inputType + " Email input type with value " + inputValue + " gives error msg");

        }
    }

    @Then("the user validates SSN input field accepts only {int} digits")
    public void theUserValidatesSSNInputFieldAcceptsOnlyDigits(int arg0, DataTable dataTable) {
        List<Map<String, String>> inputList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : inputList) {
            String inputType = row.get("Input");
            String inputValue = row.get("Input Value");
            Assert.assertTrue(checkOutPage.testSSNInPayfone(inputType, inputValue), inputType + " SSN input type with value " + inputValue + " is not giving desired output");
            if (inputType.equalsIgnoreCase("valid"))
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + inputType + " SSN input type with value " + inputValue + " gives inline green tick");
            else
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + inputType + " SSN input type with value " + inputValue + " gives error msg");
        }
    }

    @Then("Then the user clicks on mobile tooltip and validates the text")
    public void thenTheUserClicksOnMobileTooltipAndValidatesTheText() {
        checkOutPage.clickOnMobileTooltipButton();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user opens the tooltip using tooltip button");
        Assert.assertTrue(checkOutPage.verifyMobileToolTipContents(), "Mobile tooltip contents have changed");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Mobile tooltip have valid contents");
        checkOutPage.clickOnMobileTooltipCloseButton();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user closes the tooltip using X button");

    }

    @Then("the {string} button is disabled as fields are required")
    public void theButtonIsDisabledAsFieldsAreRequired(String button) {
        Assert.assertFalse(checkOutPage.isContinueButtonEnabled(), "Continue button is not disabled");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + button + " is disabled as email,mobile,ssn are required fields ");
    }

    @Then("the {string} button is enabled as fields have valid inputs")
    public void theButtonIsEnabledAsFieldsHaveValidInputs(String button) {
        Assert.assertTrue(checkOutPage.isContinueButtonEnabled(), "Continue button is not enabled");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + button + " is enabled as email,mobile,ssn have valid inputs ");
    }


    @And("the WOF have prefilled user data from {string} at row {int}")
    public void theWOFHavePrefilledUserData(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String lastName = userData.getOrDefault("LastName", "");
        String mobilePhone = userData.getOrDefault("MobilePhone", "");
        String zipCode = userData.getOrDefault("ZipCode", "");
        Assert.assertTrue(checkOutPage.isLastNameMatches(lastName), "last name is not matching");
        Assert.assertTrue(checkOutPage.isMobileNoMatches(mobilePhone), "mobile no is not matching");
        Assert.assertTrue(checkOutPage.isZipcodeMatches(zipCode), "zipcode is not matching");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "correct prefilled user data is displayed.Some fields like");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Last name: " + lastName);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "mobile:" + mobilePhone);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "zipcode:" + zipCode);
    }

    @Then("the prefilled email in wof is same as email from {string} at row {int}")
    public void theUserIsAbleToSeePrefilledEmailInWof(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String emailAddress = userData.getOrDefault("EmailAddress", "");
        Assert.assertTrue(checkOutPage.isEmailMatches(emailAddress), "email does not match with sign in user");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the prefilled email in wof is same as email of sign in user :" + emailAddress);
    }

    @Then("the user clicks on {string}")
    public void theUserClicksOn(String arg0) {
        checkOutPage.clickOnEditInformation();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the user clicks on Edit My Information button");

    }

    @Then("the prefilled value of email is disabled for edit")
    public void thePrefilledValueOfEmailIsDisabledForEdit() {
        Assert.assertTrue(checkOutPage.isEmailFieldDisabledForEdit(), "Email is not disabled for edit");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "the email field is disabled for edit");
    }

    @Then("the user apply text enabled invalid promo code and verify error message from {string} at row {int}")
    public void the_user_apply_text_enabled_invalid_promo_code_and_verify_error_message_from_at_row(String sheetName, int rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);

        String promoCode = userData.getOrDefault("InvalidPromoCode", "");
        String errorMessage = userData.getOrDefault("errorMessage", "");

        checkOutPage.enterPromoCode(promoCode);
        checkOutPage.clickApplyButton();

        Assert.assertTrue(checkOutPage.verifyErrorMessageDisplayedForTheInvalidPromoCode(errorMessage),"Error message for the invalid promo is not displayed as expected");

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Error message for the invalid promo is displayed as expected");
    }

    @When("the user apply {string} from list from {string} at row {int}")
    public void apply_promo_code(String promoType, String sheetName, int rowNumber) throws IOException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String promoCode = rowData.getOrDefault(promoType, "").trim();
        checkOutPage.applyPromo(promoCode);
        Log.info("Applied promo code "+promoCode);
    }

    @Then("verify promo code error message {string} is displayed")
    public void verify_promo_code_error_message_is_displayed(String errorMessage) {
        Assert.assertTrue(checkOutPage.promoCodeErrorMessageIsDisplayed(errorMessage),
                "Promo code error message is NOT displayed");
        Log.info("Promo code error message is displayed");
    }

    @When("the user enter and apply promo code from {string} at row {int}")
    public void enter_promo_apply_code(String sheetName, int rowNumber) throws IOException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String promoCode = rowData.getOrDefault("PromoCode", "").trim();
        checkOutPage.enterPromoCode(promoCode);
        checkOutPage.clickApplyButton();
        Log.info("Enter and applied promo code "+promoCode);
    }


    @And("the form fields Email, mobile and ssn are displayed")
    public void theFormFieldsEmailMobileAndSsnAreDisplayed() {
        Assert.assertTrue(checkOutPage.isPayfoneFormFieldsDisplayed(),"form fields are missing");
    }

    @And("the disclaimer with default state contents is displayed")
    public void theDisclaimerWithDefaultStateContentsIsDisplayed() {
       checkOutPage.isPayfoneDefaultLegalDescriptionIsDisplayed();

    }

    @When("the user clicks on {string} option")
    public void theUserClicksOnOption(String option) {
            if(option.equals("Yes"))
                checkOutPage.clickPayfoneYesButton();
            else if(option.equals("No"))
                checkOutPage.clickPayfoneNoButton();
    }

    @And("{string} is displayed {string}")
    public void isDisplayed(String text, String value) {
        boolean isDisplayed = Boolean.parseBoolean(value);
        if (text.contains("Would you like to receive automated marketing texts"))
        {
            if(isDisplayed)
            {
             Assert.assertTrue(basePage.isTextDisplayed(text),"");
            }
            else
            {
                Assert.assertFalse(basePage.isTextDisplayed(text),"");
            }
        }
    }

    @Then("the marketing msg for {string} is displayed")
    public void theMarketingMsgForIsDisplayed(String option) {
        if(option.equals("Yes"))
        {
            Assert.assertTrue(checkOutPage.isPayfoneMarketingMsgForYesIsDisplayed(),"the Marketing Msg for Yes is not displayed");
        }
        else if(option.equals("No"))
        {
            Assert.assertTrue(checkOutPage.isPayfoneMarketingMsgForNoIsDisplayed(),"the Marketing Msg for No is not displayed");
        }
    }


    @Then("the tooltip opens and configured information for payfone ssn is displayed")
    public void theTooltipOpensAndConfiguredInformationForPayfoneSsnIsDisplayed() {
        Assert.assertTrue(checkOutPage.verifySSNToolTipContentsFromPayfone(),"ssn tool tip contents are different");
    }

    @When("the user clicks on payfone ssn tooltip button")
    public void theUserClicksOnPayfoneSsnTooltipButton() {
        checkOutPage.clickOnPayfoneSSNTooltipButton();
    }

    @Then("verify payment due {string} amount is displayed")
    public void verify_payment_due_is_displayed(String paymentDue) {
        Assert.assertTrue(checkOutPage.verifyPaymentDetailsAreDisplayed(paymentDue),
                "Payment due "+paymentDue+" is NOT displayed");
        Log.info("Payment due "+paymentDue+" is displayed");
    }

    @Then("verify totalDue amount after applying promo code")
    public void verify_totalDue_amount() {
        String refundableAmount = checkOutPage.paymentDuePrice("Refundable Deposit:");
        String refundableAmount_sub = refundableAmount.substring(1);
        double refundableAmount_parse = Double.parseDouble(refundableAmount_sub);
        String promoAmount = checkOutPage.paymentDuePrice("Promo");
        String promoAmount_sub = promoAmount.substring(2);
        double promoAmount_parse = Double.parseDouble(promoAmount_sub);
        double totalDue_price = refundableAmount_parse - promoAmount_parse;
        String totalDue = String.format("%2f", totalDue_price);
        String totalDueAmount_act = checkOutPage.paymentDuePrice("Total Due Today:");
        String totalDueAmount_exp = "$"+totalDue;
        Assert.assertEquals(totalDueAmount_act,totalDueAmount_exp,
                "Total due amount is NOT matched with applied promo code");
        Log.info("Total due amount is matched with applied promo code");
    }

    @Then("the user verify list of All payment methods available on Desired Promo code from {string} at row {int}")

    public void the_user_verify_list_of_All_payment_methods_available_on_Desired_promo_code(String sheetName, int rowNumber) throws IOException {

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String expectedDueAmount= userData.getOrDefault("TotalDue","");
        int availablePaymentMethods = checkOutPage.getListOfItemsInPaymentMethods();
        String totalPaymentsDisplayed = checkOutPage.getListOfPaymentMethodsDisplayed();
        Log.info("AvailablePaymentMethods:" + availablePaymentMethods + " payment type displayed :" + totalPaymentsDisplayed);

        if (expectedDueAmount.equals("0")) {
            Assert.assertEquals(availablePaymentMethods, 1, "Invalid payments types are available for zero amount payment");
        }
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Number of payment methods items displayed: "
                + availablePaymentMethods + " and Names of Payment " + totalPaymentsDisplayed);
    }

    @Then("the user verify all Payfone flow field are empty on intial page load")
    public void theUserVerifyAllPayfoneFlowFieldAreEmptyOnIntialPageLoad() {
        Assert.assertTrue(checkOutPage.allFieldsEmptyOnPayefoneOnPageLoad(),"form fields are already filled with data");
    }

    @Then("verify butter bar is displayed at the top")
    public void verifyButterBarIsDisplayedAtTheTop() {
        //displyed
        //afte close
        //not displayed
        Assert.assertTrue(checkOutPage.validateButterBarContent(), "Butter Bar Content is not Displayed on the page");
    }

    @Then("the user verify navigation to modified declined page from {string} at row {int}")
    public void theUserVerifyNavigationToModifiedDeclinedPageFromAtRowRowNumber(String sheetName, int rowNumber) throws IOException {
        basePage.waitForPageToLoad();
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String expectedPageTitle= userData.getOrDefault("PageTitle","");
        String expectedPageSubtitle= userData.getOrDefault("PageSubtitle","");
        Assert.assertTrue(checkOutPage.verifyNavigationToModifiedDeclinedPage(expectedPageTitle,expectedPageSubtitle),"Page title or Subtitle not matched for modified declined page");

    }

    @And("the user verify CarouselComponent On Modified Declined Page from {string} at row {int}")
    public void theUserVerifyCarouselComponentOnModifiedDeclinedPageFromAtRowRowNumber(String sheetName, int rowNumber) throws IOException {
            Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
//            String Expcarousel_ComponentText = rowData.getOrDefault("CarouselComponentText", "").trim();
            Assert.assertTrue(checkOutPage.verifyCarouselComponentOnModifiedDeclinePage(),"shop approved items not displayed");


    }

    @Then("the user verify Billing Address Same As Delivery And Auto Selected from sheet {string} at row {int}")
    public void the_user_verify_billing_address_same_as_delivery_and_auto_selected_from_sheet_at_row(String sheetName, Integer rowNumber) throws IOException {
        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String expAddress= userData.getOrDefault("Address","");
        Assert.assertTrue(checkOutPage.verifyBillingAddressAutoPopulated(expAddress),"shipping address is not matching");
    }


    @Then("the user verify enroll in Autopay checkbox pre selected and Verbiage displayed")
    public void theUserVerifyEnrollInAutopayCheckboxPreSelectedAndVerbiageDisplayed() {
        Assert.assertTrue(checkOutPage.verifyEnrollInAutoPayIsSelectedAndVerbiageDisplayed(),"Checkbox not selected by default or Verbiage is not displayed or not as expected");

    }

    @Then("verify store refundable amount {string} is displayed")
    public void verify_store_refundable_amount(String expRefundableAmount) {
        String actRefundableAmount = checkOutPage.paymentDuePrice("Refundable Deposit:");
        Assert.assertEquals(actRefundableAmount,expRefundableAmount,
                "Store refundable amount "+expRefundableAmount+" is NOT displayed");
        Log.info("Store refundable amount "+expRefundableAmount+" is displayed");
    }

    @When("the user enters BAV routing and account number from {string} at row {int}")
    public void enterBankRoutingAccountNumberFromSheet(String sheetName, int rowNumber) throws IOException, InterruptedException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String bankRoutingNumber = rowData.getOrDefault("BankRoutingNumber", "").trim();
        String bankAccountNumber = rowData.getOrDefault("BankAccountNumber", "").trim();
        String bav = rowData.getOrDefault("BAV", "").trim();
        if (bav.equalsIgnoreCase("Yes")) {
            checkOutPage.enterBankRoutingAccountNumber(bankRoutingNumber, bankAccountNumber);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter bank routing number: " + bankRoutingNumber);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Enter bank account number: " + bankAccountNumber);
        }
    }

    @Then("verify bank account number value {string}")
    public void verify_bank_account_number_value(String expBankAccountNumberValue) {
        String actBankAccountNumberValue = checkOutPage.getBankAccountAttributeValue();
        Assert.assertEquals(actBankAccountNumberValue,expBankAccountNumberValue,
                "Bank account number value "+expBankAccountNumberValue+" is NOT displayed");
        Log.info("Bank account number value "+expBankAccountNumberValue+" is displayed");
    }

    @When("the user clicks on bank account toggle mask")
    public void theUserClicksOnBankAccountToggleMask() {
        checkOutPage.clickBankAccountToggleMask();
        Log.info("the user clicked on bank account toggle mask");
    }

    @Then("the user verify non-blacklisted routing number error message")
    public void the_user_verify_non_blacklisted_routing_number_error_message() {
        Assert.assertTrue(checkOutPage.verifyBankRoutingErrorMessage("We can't verify this bank, please choose another orreserve your itemand complete checkout in store."),"Bank routing number error message is not matching");
    }


    @And("the user clicks on Continue To Payment button in BP flow")
    public void theUserClicksOnContinueToPaymentButtonInBPFlow() {
        Log.info("Click On Continue To Payment Button");
        checkOutPage.clickOnContinueToPaymentButton_BP();
    }

    @Then("verify total due amount match with {string}")
    public void verifyTotalDueAmountMatchWithStoreLevelRefundable(String approvalType) {
        String actRefundableAmount = null;
        if(approvalType.equals("Reservation")){
            actRefundableAmount = checkOutPage.paymentDuePrice("Refundable Deposit:");
        }else if(approvalType.equals("Claim Offer")){
            actRefundableAmount = checkOutPage.paymentDuePrice("First Payment:");
        }
        String actTotalDueAmount = checkOutPage.paymentDuePrice("Total Due Today:");
        Assert.assertEquals(actRefundableAmount,actTotalDueAmount,
                "Total due today: "+actRefundableAmount+" is NOT matched with store level refundable amount");
        Log.info("Total due today: "+actTotalDueAmount+" is matched with store level refundable amount");
    }

    @Then("the user verify bank routing linked to {string}")
    public void the_user_verify_bank_routing_linked_name(String bankRoutingLinkedName) {
        Assert.assertTrue(checkOutPage.verifyBankRoutingBankName(bankRoutingLinkedName),"Bank routing linked name not matched");
    }

    @Then("the user verify BAV prompt message")
    public void bavPromptMessageIsDisplayed(){
        List<String> actBavPromptMessage = new ArrayList<>();
        Collections.addAll(actBavPromptMessage, "•  The lowest initial payment","•  Immediate online checkout","•  The best available promo");
        List<WebElement> expBavPromptMessage = DriverFactory.getDriver().findElements(By.xpath("//div[@class='online-checkout-section css-0']//ul[@role='list']//li"));
        for(int i=0; i<expBavPromptMessage.size();i++){
            String actBavMessage = actBavPromptMessage.get(i);
            String expBavMessage = expBavPromptMessage.get(i).getText();
            Assert.assertEquals(actBavMessage,expBavMessage,"BAV prompt message is NOT displayed");
        }
    }

    @Then("verify my offers applied promo code is applied {string}")
    public void verify_promo_code_checkbox_isChecked(String promoCode) throws IOException {
        Assert.assertTrue(checkOutPage.verifyPromoCodeCheckboxIsChecked(promoCode),
                "Applied promo code checkbox is NOT checked");
        Log.info("Applied promo code checkbox is checked");
    }

    @When("the user clicks on reservation link on BAV section")
    public void click_on_reservation_link_on_BAV_section() {
        checkOutPage.clickOnBavReservationLink();
        Log.info("Clicked on reservation and complete in store link in BAV section");
    }

    @Then("verify promo rate after applying promo code from {string} at row {int}")
    public void verify_promo_rate(String sheetName, int rowNumber) throws IOException {
        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String actPromoAmount = checkOutPage.paymentDuePrice("Promo");
        String expPromoRate = rowData.getOrDefault("PromoRate", "").trim();
        Assert.assertEquals(actPromoAmount,expPromoRate,
                "Promo rate is NOT matched with applied promo code");
        Log.info("Promo rate is matched with applied promo code");
    }

    @Then("verify bank account link is displayed")
    public void verify_bank_account_link_is_displayed() throws IOException {
        Assert.assertTrue(checkOutPage.verifyBankAccountLink(),
                "Bank account link is NOT displayed");
        Log.info("Bank account link is displayed");
    }

}

