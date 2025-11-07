package com.pages;

import static com.qa.factory.DriverFactory.getDriver;
import static com.qa.util.ElementActions.waitForSecs;

import java.io.IOException;
import java.time.Duration;
import java.util.*;

import com.qa.util.ConfigReader;
import io.appium.java_client.remote.SupportsContextSwitching;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
//import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v137.network.Network;
import org.openqa.selenium.devtools.v137.network.model.Headers;
import org.openqa.selenium.devtools.v137.network.model.Response;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.qa.util.ConfigReader;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import com.qa.util.ScenarioContext;

import io.appium.java_client.remote.SupportsContextSwitching;
import org.openqa.selenium.interactions.Actions;

public class CheckOutPage {

    private WebDriver driver;
    ScenarioContext scenarioContext = new ScenarioContext(); // Shared context
    BasePage basePage = new BasePage(getDriver());
    private static final Logger Log = LoggerHelper.getLogger();
    Properties prop = null;

    private static final String SSN_TOOLTIP_HEADING = "Why do you ask for this?";
    private static final String SSN_TOOLTIP_PARAGRAPH_PART1 = "We recommend providing your SSN/ITIN and Date of Birth so we can better verify your identity and speed up your order process. ";
    private static final String SSN_TOOLTIP_PARAGRAPH_PART2 = "to learn how we collect and use information.";
    private static final String PAYFONE_THIRD_PARTY_CONSENT_CONTENT_HEADING = "Third Party Consent";
    private static final String PAYFONE_THIRD_PARTY_CONSENT_CONTENT_PARAGRAPH = "You authorize your wireless operator (AT&T, Sprint, T-Mobile, US Cellular, " +
            "Verizon, or any other branded wireless operator) to use or disclose information related to your wireless subscriber account " +
            "(such as your mobile number, name, address, email, network status, customer type, mobile device identifiers, and other device " +
            "and subscriber status information) to Rent-A-Center or its service providers, which they may use for the duration of your business " +
            "relationship with them, solely to verify your identity and help to prevent fraud.";

    private static final String PAYFONE_MOBILE_TOOLTIP_PART_1 = "Why do we ask for this?";
    private static final String PAYFONE_MOBILE_TOOLTIP_PART_2 = "We'll use your mobile phone number to try to find and prefill your contact info.";

    private static final String PAYFONE_MOBILE_TOOLTIP_PART_3 = "Don't have a mobile phone?";
    private static final String PAYFONE_MOBILE_TOOLTIP_PART_4 = "Don't worry, you can skip this step.";
    private static final String PAYFONE_SSN_TOOLTIP_TEXT = "We'll use the last four digits of your SSN to try to find and prefill your contact info.";

    // Constructor to initialize elements using Page Factory
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "confirmEmail")
    private WebElement confirmEmailField;

    //@FindBy(name = "phone")
    @FindBy(id = "phone")
    private WebElement mobilePhoneField;

    @FindBy(id = "countryCode")
    private WebElement countryDropdown;

    @FindBy(id = "address1")
    private WebElement addressField;

    //@FindBy(name = "city")
    @FindBy(id = "city")
    private WebElement cityField;

    // ***Not generated
    @FindBy(id = "stateCode")
    private WebElement stateDropdown;

    @FindBy(id = "postalCode")
    private WebElement zipCodeField;


    @FindBy(xpath = "//button[contains(text(), 'Continue')]")
    private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(), 'Back to Dashboard')]")
    private WebElement backToDashboard;

    @FindBy(xpath = "(//div[starts-with(@class,'my-accounts-dashboard')]//following::span[4])[1]")
    private WebElement paymentSuccessMsgDashboard;

    @FindBy(name = "ssn")
    private WebElement ssnInputField;

    @FindBy(name = "dob")
    private WebElement dobInputField;

    // @FindBy(xpath ="//button[normalize-space(text())='I AGREE & CONTINUE']")
    @FindBy(xpath = "(//button[text()='I Agree & Continue'])[2]")
    private WebElement agreeAndContinueButton;

    //@FindBy(xpath = "//button[normalize-space(text())='CONTINUE']")
    @FindBy(xpath = "//*[contains(@id,'chakra-modal--body')]//button[text()='Continue']")
    private WebElement continueButtonCongratsPopUp;

    @FindBy(xpath = "(//button[translate(normalize-space(text()), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ') = 'CONTINUE'])[2]")
    private WebElement continueButtonCongratsPopUpRegUser;

    @FindBy(xpath = "//span[contains(@class, 'chakra-radio__label') and contains(text(), 'Weekly')]")
    private WebElement selectPayScheduleRdoBtn;

    @FindBy(xpath = "//input[@type='date' and contains(@class, 'chakra-input')]")
    private WebElement paidNextDate;

    @FindBy(xpath = "(//button[normalize-space(text())='Continue'])")
    private WebElement continueButtonPayScheduleSection;

    @FindBy(xpath = "(//button[normalize-space(text())='Continue'])[2]")
    private WebElement continueButtonPayScheduleSectionGuestUser;

    @FindBy(xpath = "//button[normalize-space(text())='Continue To Payment']")
    private WebElement continueToPaymentButton;

    @FindBy(xpath = "//div[text()='PayPal']")
    private WebElement payPalRdoButton;

    @FindBy(xpath = "//div[text()='Credit / Debit Card']")
    private WebElement creditOrDebitCardRdoButton;

    @FindBy(xpath = "//div[text()='Credit / Debit Card']/ancestor::button")
    private WebElement creditOrDebitRadioButton;

    @FindBy(xpath = "//*[text()='Bank Account']")
    private WebElement bankAccountRdoButtonAddPayment;

    @FindBy(xpath = "//*[contains(@class, 'paypal-logo-paypal') and contains(@class, 'paypal-logo-color-blue')]")
    private WebElement payPalLogo;

    @FindBy(name = "login_email")
    private WebElement emailPayPal;

    @FindBy(name = "btnNext")
    private WebElement payPalNextButton;

    @FindBy(name = "login_password")
    private WebElement passwordPayPal;

    @FindBy(name = "btnLogin")
    private WebElement payPalLoginButton;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])[4]")
    private WebElement iAgreeCheckBoxPayPal;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])[4]")
    private WebElement iAgreeCheckBoxVenmoOrPayPal;

    @FindBy(xpath = "(//button[contains(text(),'VENMO')]/following::label[contains(., 'I agree to the')])[1]/input[@type='checkbox']")
    private WebElement iAgreeCheckBoxVenmo;

    @FindBy(xpath = "//button[contains(text(),'VENMO')]/following::button[contains(text(),'Make Payment')]")
    private WebElement venmoMakePayment;

    @FindBy(xpath = "(//*[contains(text(),'PayPal')]/following::label[contains(., 'I agree to the')])[1]/input[@type='checkbox']")
    private WebElement iAgreeCheckBoxPaypalMyAccount;

    @FindBy(xpath = "//div[contains(text(),'PayPal')]/following::button[contains(text(),'Make Payment')]")
    private WebElement paypalMakePayment;

    //@FindBy(xpath = "(//button[@type='button' and contains(text(), 'Submit & Continue To E-Sign')])[3]")
    //@FindBy(xpath = "//button[@id='submit-button' and normalize-space()='Submit & Continue To E-Sign']")
    @FindBy(xpath = "//button[@id='submit-button' and contains(normalize-space(),'Submit')]")
    private WebElement submitAndContinueToESignButton;


    @FindBy(xpath = "(//button[normalize-space()='Submit & Continue To E-Sign'])[4]")
    private WebElement submitAndContinueToESignButtonPayPal;


    @FindBy(css = "[aria-label='Close']")
    private WebElement closeButton;

    @FindBy(xpath = "//button[text()='Save and Continue']")
    private WebElement saveAndContinueButtonPayPal;

    @FindBy(id = "accountNumber")
    private WebElement cardNumberInput;

    @FindBy(id = "expMonth")
    private WebElement expMonthDropdown;

    @FindBy(id = "expYear")
    private WebElement expYearDropdown;

    @FindBy(id = "cvv")
    private WebElement cvvInputField;

    //@FindBy(xpath = "//form[@id='payment-form']//input[@name='firstName']")
    // @FindBy(xpath = "//input[@id='firstName']")
    @FindBy(xpath = "//form[@id='payment-form']//input[@name='firstName'] | //input[@id='firstName']")
    private WebElement firstNameInput;

    //@FindBy(xpath = "//form[@id='payment-form']//input[@name='lastName']")
    // @FindBy(xpath = "//input[@id='lastName']")
    @FindBy(xpath = "//form[@id='payment-form']//input[@name='lastName'] | //input[@id='lastName']")
    private WebElement lastNameInput;

    @FindBy(xpath = "//label[contains(., 'I agree to the Terms & conditions')]/input[@type='checkbox'] | (//span[contains(text(),'I agree to the')])[1]")
    private WebElement iAgreeCheckBoxCCAddPayment;

    @FindBy(xpath = "(//span[starts-with(text(),'Save card')])[1]")
    private WebElement saveCardCheckboxCCPayment;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])[3]")
    private WebElement iAgreeCheckBoxPayPalAddPayment;

    @FindBy(xpath = "//Strong[contains(text(),'Saved Payment Methods:')]/following::p[1]")
    private WebElement savedCardDetails;

    @FindBy(xpath = "//div[text()='Venmo']")
    private WebElement venmoRdoButton;

    @FindBy(xpath = "//div[text()='Apple Pay']")
    private WebElement applePayRdoButton;

    @FindBy(xpath = "//div[text()='Google Pay']")
    private WebElement googlePayRdoButton;

   // @FindBy(xpath = "//div[text()='Cash App Pay']")
    @FindBy(xpath = "//div[text()='Cash App'] | //div[text()='Cash App Pay']")
    private WebElement cashAppPayRdoButton;

    @FindBy(xpath = "//button[contains(@class, 'chakra-button') and text()='VENMO']")
    private WebElement venmoButton;

    @FindBy(name = "login_email")
    private WebElement emailVenmo;

    @FindBy(xpath = "//span[text()='Log in with password instead']")
    WebElement logInWithPasswordInsteadButton;

    @FindBy(xpath = "//div[@data-status='success' and contains(@class, 'chakra-alert__desc')]")
    WebElement venmoMessageSaveSuccess;

    @FindBy(xpath = " //button[text()='Buy with Apple Pay']")
    private WebElement buyWithApplePayButton;

    @FindBy(id = "gpay-button-online-api-id")
    private WebElement gpayButton;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailInput;

    @FindBy(xpath = "//span[contains(text(),'Next')]/parent::button")
    private WebElement gpayNextButton;

    @FindBy(xpath = "//*[@id='cash-app-pay']")
    private WebElement cashAppPayButton;

    @FindBy(xpath = "//img[@alt='Scan to Pay']")
    private WebElement qrCodeCashPayImage;

    @FindBy(xpath = "//apple-pay-modal[contains(@url, 'https://applepay.cdn-apple.com/')]")
    private WebElement applePayModal;

    @FindBy(xpath = "(//span[contains(text(), 'Save this payment method')])[2]")
    private WebElement saveThisPaymentMethodCheckbox;

    //@FindBy(xpath = "//span[contains(text(), 'Save card and enroll me in AutoPay')]")
    @FindBy(xpath = "//*[contains(text(), 'Save card and enrol me in AutoPay')] | //*[contains(text(), 'Save card and enroll me in AutoPay')]")
    private WebElement saveCardAndEnrollAutoPayLabel;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])[1]")
    private WebElement ccIAgreeCheckboxLabel;

    @FindBy(xpath = "//label[text()='Address']/following-sibling::input")
    private WebElement addressInput;

    @FindBy(xpath = "//label[text()='City']/following-sibling::input")
    private WebElement cityInput;

    @FindBy(xpath = "//span[contains(text(),'Total Payment:')]/following::strong[1]")
    private WebElement totalAmountOnSuccessScreen;

    @FindBy(xpath = "//label[text()='Postal Code']/following-sibling::input")
    private WebElement postalCodeInput;

    @FindBy(xpath = "//label[text()='State']/following-sibling::input")
    private WebElement stateInput;

    @FindBy(xpath = "//button[@type='submit' and text()='Add payment method']")
    private WebElement addPaymentMethodButton;

    @FindBy(xpath = "//button[contains(text(),'Make Payment')] | //button[@id='submit-button']")
    private WebElement makePaymentButton;


    @FindBy(xpath = "//*[contains(text(),'Saved Payment Methods:')]/following::button[contains(text(),'Make Payment')][1]")
    private WebElement savedPaymentMethodCard;

    @FindBy(xpath = "//*[contains(text(), 'ACH •••• 9999')]")
    private WebElement bankAccountLabelAddPayment;


    @FindBy(xpath = "//*[contains(text(), 'Discover •••• ')]")
    private WebElement discoverCardLabel;

    @FindBy(xpath = "//*[contains(text(), 'Visa •••• ')]")
    private WebElement visaCardLabel;

    @FindBy(xpath = "//*[contains(text(), 'Master •••• ')]")
    private WebElement mastercardCardLabel;

    @FindBy(xpath = "//*[contains(text(), 'American Express •••• ')]")
    private WebElement americanExpressLabel;

    @FindBy(xpath = "//*[contains(text(), 'rcuserai testai')]")
    private WebElement payPalLabelSavedPayment;

    @FindBy(xpath = "//*[@id='app-main']//*[contains(text(),'Payment Successful')]")
    private WebElement paymentSuccessMessage;

    @FindBy(xpath = "//p[contains(text(),'Billing Address Same as Delivery')]/preceding::input[@type='checkbox']")
    private WebElement billingAddSameAsDeliveryCheckbox;

    @FindBy(xpath = "//a[normalize-space()='\"+ActionLibrary.getNextDate()+\"']")
    private WebElement nextDateOnPaySchedule;

    //PDP page values

    @FindBy(xpath = "//*[text()='Pricing Info']")
    private WebElement pricingInfoLink_PDP;

    @FindBy(xpath = "//div[contains(text(),'$19.99 per weekly')]")
    WebElement weeklyPriceText;

    @FindBy(xpath = "//div[contains(text(),'Number of Payments')]/following-sibling::div")
    WebElement numberOfPayments;

    @FindBy(xpath = "//div[contains(text(),'Total Cost To Own')]/following-sibling::div")
    WebElement totalCostToOwn;

    @FindBy(xpath = "//div[contains(text(),'Cash Price')]/following-sibling::div")
    WebElement cashPrice;

    @FindBy(xpath = "//div[contains(text(),'Weekly Renewal Payment')]/following-sibling::div[1]")
    WebElement renewalRate;

    @FindBy(xpath = "//div[contains(text(),'Total Due Today')]/following-sibling::div")
    WebElement totalDueToday;

    @FindBy(xpath = "//div[contains(text(),'Next Payment Due:')]/following-sibling::div")
    WebElement nextPaymentDueDate;
    @FindBy(xpath = "//p[contains(text(),'Claim the offer')]")
    private WebElement claimOfferDescription;

    @FindBy(xpath = "//div[contains(text(),'First Payment')]/following-sibling::div")
    private WebElement firstPaymentAmount;

    @FindBy(linkText = "Why this amount?")
    private WebElement whyThisAmountLink;

    @FindBy(xpath = "//div[contains(text(),'Total Due Today')]/following-sibling::div")
    private WebElement totalDueTodayAmount;

    @FindBy(xpath = "//input[@placeholder='Enter your promo code']")
    private WebElement promoCodeInput;

    //@FindBy(xpath = "//button[contains(text(),'Apply') and not(@data-testid='unapply')]")
   @FindBy(xpath = "//input[@placeholder='Enter your promo code']/following-sibling::button")
    private WebElement applyButton;

    @FindBy(xpath = "//div[starts-with(@id, 'popover-trigger') and @aria-haspopup='dialog']")
    private WebElement checkoutOnlineNowToolTip;

    @FindBy(xpath = " //*[contains(@class, 'chakra-text css-5jsmfw')]")
    private WebElement addressText;

    @FindBy(xpath = "//*[contains(@class, 'chakra-text css-cbt')]")
    private WebElement mobileNumberText;

    @FindBy(id = "address2")
    private WebElement aptField;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private WebElement closeXPopUp;

    @FindBy(xpath = "//span[text()='Loading...']")
    private WebElement loadingSpinner;

    @FindBy(xpath = "//p[contains(text(),'Congratulations')]")
    private WebElement congratulationsTitle;
    @FindBy(xpath = "//*[text()='Shop My Approved Items']")
    private WebElement shopMyApprovedItemsButton;
    @FindBy(xpath = "//p[text()=\"you're pre-approved!\"]")
    private WebElement preApproved_Conditional;
    @FindBy(xpath = "//p[text()=\"you're approved!\"]")
    private WebElement approved_Approval;

    @FindBy(xpath = "//button[normalize-space(text())='Reserve & complete in store']")
    private WebElement reserveButtonCongratsPopUp;
    @FindBy(xpath = "//button[normalize-space(text())='Reserve My Item']")
    private WebElement reserveMyProductButton;
    @FindBy(xpath = "//*[text()='Unfortunately, we were unable to approve your request at this time.']")
    private WebElement deniedMessage;

    @FindBy(xpath = "//*[text()='Bank Routing Number']//ancestor::div[@role='group']//input")
    private WebElement bankRoutingNumber_input;

    @FindBy(xpath = "//*[text()='Bank Account Number']//ancestor::div[@role='group']//input")
    private WebElement bankAccountNumber_input;

    @FindBy(xpath = "//button[normalize-space(text())='CONTINUE']")
    private WebElement bavContinue_btn;

    @FindBy(xpath = "//button[text()='I Agree & Continue']")
    private WebElement iAgreeAndContinueButton;

    @FindBy(xpath = "//span[text()='Next']")
    private WebElement nextOnDocuSign;
    @FindBy(xpath = "//button[@aria-label='Finish']")
    private WebElement finishOnDocuSign;
    @FindBy(xpath = "//span[text()='Adopt and Sign']")
    private WebElement adoptAndSign;
    @FindBy(xpath = "//*[text()='Total Due Today:']//following::strong")
    private WebElement totalAmountDue;
    @FindBy(xpath = "//*[text()='Liability Damage Waiver (LDW)']//following::label[contains(@class,'chakra-switch')]")
    private WebElement ldwSliderCheckBox;
    @FindBy(xpath = "//*[text()='RAC_benefits_plus']//following::label[contains(@class,'chakra-switch')]")
    private WebElement bpSliderCheckBox;

    @FindBy(xpath = "//section[contains(@id,'chakra-modal')]")
    private WebElement benefitsModal;
    @FindBy(xpath = "//button[contains(@class,'modal-close-icon')]")
    private WebElement benefitsModalCloseBtn;
    @FindBy(xpath = "//a[text()='Learn More']")
    private WebElement learnMore;

    @FindBy(xpath = "//h3[text()='Choose Optional Benefits']//parent::div//following::span[contains(@class,'chakra-text')]")
    private WebElement text_benefitPlus;

    @FindBy(xpath = "(//*[contains(@class,'chakra-switch__track')])[4]")
    private WebElement bpSliderCheckBoxOnModal;

    @FindBy(xpath = "(//*[contains(@class,'chakra-switch__track')])[3]")
    private WebElement ldwSliderCheckBoxOnModal;
    @FindBy(xpath = "//*[normalize-space()='Sign Agreement Later']")
    private WebElement signAgreementLater;

    @FindBy(xpath = "//*[contains(text(),'Thank you for your order!')]")
    private WebElement agrSuccessMessage;

    @FindBy(xpath = "//button[text()='RESERVE MY PRODUCT']//ancestor::section[contains(@id,'chakra-modal')]")
    private WebElement reserveMyProductSection;

    @FindBy(xpath = "//a[text()='Just submit my contact info']")
    private WebElement submitMyContactInfoLink;

    @FindBy(xpath = "//a[text()='Reserve & Complete In Store']")
    private WebElement reserveCompleteInStoreLink;

    @FindBy(xpath = "//a[text()='Set My Password']")
    private WebElement setMyPasswordBtn;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailAddress_Signin;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'continue')]")
    private WebElement continue_SignIn;

    @FindBy(xpath = "//input[@id='loginPassword']")
    private WebElement loginPasswordBtn;

    @FindBy(xpath = "//input[@id='activeLoginBtn']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'enroll now')]")
    private WebElement enrollNowBtn_BPModal;

    @FindBy(xpath = "//button[normalize-space(text())='I Agree & Continue']")
    private WebElement agreeAndContinueButton_BPStartOrderPage;

    @FindBy(xpath = "//*[normalize-space(text())=\"Congrats, you're eligible for Benefits Program!\"]")
    private WebElement bpModalHeaderOnConfirmationPage;

    @FindBy(xpath = "//p[text()='You Qualify for RAC']")
    private WebElement bpComponentOneOnDashBoard;

    @FindBy(xpath = "//p[text()='Benifits Plus membership!']")
    private WebElement bpComponentTwoOnDashBoard;

    @FindBy(xpath = "//section[@role='dialog']//button[normalize-space(text())='Continue']")
    private WebElement continueButtonCongratsPopUpBAV;

    @FindBy(xpath = "//a[text()='View']")
    private WebElement viewLinkForBP;
    @FindBy(xpath = "(//*[text()='Total Due Today:']//following::p)[1]")
    private WebElement totalDueToday_amountTxt;
    @FindBy(xpath = "//*[contains(@class,'payment-methods-section')]//*[contains(@class,'chakra-accordion__item')]")
    private List<WebElement> listOfPaymentsMethod;

    @FindBy(xpath = "(//button[text()='Continue']/preceding::span[contains(text(), 'I agree to the')])[last()]")
    private WebElement ccIAgreeCheckboxLabelApplePay;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])/following::button[text()='Continue']")
    private WebElement continueButtonApplePay;

    @FindBy(xpath = "//*[contains(@id,'chakra-modal--body')]//button[text()='RESERVE MY PRODUCT' or text()='Reserve My Item']")
    private WebElement reserveMyProductButtonAlmostDoneDlg;

    @FindBy(xpath = "(//span[contains(text(), 'Save this payment method')])[1]")
    private WebElement saveThisPaymentMethodCheckboxCashApp;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])[4]")
    private WebElement cashAppIAgreeCheckboxLabel;

    @FindBy(xpath = "//h2[text()='Reserve Your Product']")
    private WebElement reserveYourProductHeader;
    @FindBy(xpath = "//div[contains(@class,'payment-methods-section ')]//strong[text() = 'Saved Payment Methods:']//following::div[contains(@class,'chakra-radio-group payment-radios')]//label[contains(@class,'chakra-radio')]")
    private List<WebElement> listOfSavedPaymentsMethod;

    @FindBy(xpath = "//div[contains(@class,'payment-methods-section ')]//strong[text() = 'Saved Payment Methods:']//following::label[contains(@class,'chakra-radio')]//input[1]")
    private WebElement radioInputToSavedCC;


    @FindBy(xpath = "(//span[contains(text(), 'Enroll me')]/preceding-sibling::input)[1]")
    private WebElement radioInputToEnrollToAutopay;

    @FindBy(xpath = "(//span[contains(text(), 'I agree to the ')]/preceding-sibling::input)[1]")
    private WebElement radioInputToAgreeToTerms;

    @FindBy(xpath = "(//div[contains(@class,'custom-tooltip')])[2]//span")
    private WebElement ssnToolTipButton;

    @FindBy(xpath = "(//div[contains(@class,'custom-tooltip')])[2]//section[contains(@class,'chakra-popover__content')]/button")
    private WebElement ssnToolTipCloseButton;

    @FindBy(xpath = "(//div[contains(@class,'custom-tooltip')])[2]//section[contains(@class,'chakra-popover__content')]")
    private WebElement ssnToolTipText;

    @FindBy(xpath = "//*[text()='Invalid Date of Birth format']")
    private WebElement invalidDOBErrorMsg;


    @FindBy(xpath = " //section[@role='dialog'][contains(@class,'chakra-modal__content')]")
    private WebElement payfoneThirdPartyConsentPopup;


    @FindBy(xpath = "//div[@class='payfone-third-party-consent-modal']")
    private WebElement payfoneThirdPartyConsentPopupContents;


    @FindBy(xpath = "//button[@aria-label='Close modal']")
    private WebElement payfoneThirdPartyConsentPopupCloseButton;

    @FindBy(xpath = "//*[@aria-label='lock-keyhole']//following::p[text()='Secure Form']")
    private WebElement payfoneSecureFormText;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement payfoneEmailInput;

    @FindBy(xpath = "//input[@id='mobile'][@pattern='[0-9]*']")
    private WebElement payfoneMobileInput;

    @FindBy(xpath = "//input[@id='ssn'][@type='password']")
    private WebElement payfoneSSNInput;

    @FindBy(xpath = "//div[contains(@class,'payfone-tooltip')]")
    private WebElement payfoneMobileToolTipPopup;

    @FindBy(xpath = "//div[@aria-haspopup='dialog']")
    private WebElement payfoneMobileToolTipButton;

    @FindBy(xpath = "//p[text()='Please enter a valid email address.']")
    private WebElement payfoneEmailErrorMsg;

    @FindBy(xpath = "//p[text()='Please enter a valid mobile number.']")
    private WebElement payfoneMobileErrorMsg;

    @FindBy(xpath = "//p[contains(text(),'Please enter the last 4 of your SSN')]")
    private WebElement payfoneSSNErrorMsg;


    @FindBy(xpath = "//p[contains(text(),'Full Name')]//following::p")
    private WebElement wofPrefilledFullNameValue;

    @FindBy(xpath = "//p[contains(text(),'Delivery Address')]//following::p")
    private WebElement wofPrefilledDeliveryAddrValue;

    @FindBy(xpath = "//p[contains(text(),'Mobile')]//following::p")
    private WebElement wofPrefilledMobileNoValue;

    @FindBy(xpath = "//p[contains(text(),'Email')]//following::p")
    private WebElement wofPrefilledEmailValue;


    @FindBy(xpath = "//button[text()='Edit My Information']")
    private WebElement wofPrefilledEditButton;


    @FindBy(xpath = "//button[text()='Yes']")
    private WebElement payfoneYesButton;


    @FindBy(xpath = "//button[text()='No']")
    private WebElement payfoneNoButton;

    @FindBy(xpath = "//p[contains(@class,'payfone-legal-description')]")
    private WebElement payfoneLegalDescription;

    @FindBy(xpath = "//p[text()='Would you like to receive automated marketing texts with deals and offers?']")
    private WebElement payfoneAutomatedMarketingTextsDialogText;

    @FindBy(xpath = "//span[@class='payfone-marketing-msg-no']")
    private WebElement payfoneMarketingMsgOnNoClicked;

    @FindBy(xpath = "//span[@class='payfone-marketing-msg-yes']")
    private WebElement payfoneMarketingMsgOnYesClicked;

    @FindBy(xpath = "(//div[contains(@class,'custom-tooltip')])[2]//div")
    private WebElement payfoneSsnToolTipButton;

    @FindBy(xpath = "//*[contains(@class, 'chakra-container')]//*[contains(text(), 'cash price')]/preceding-sibling::*[contains(text(), '$')]")
    private WebElement butterBarCashPrice;

    @FindBy(xpath = "//*[contains(@class, 'chakra-container')]//*[contains(text(), 'cash price')]")
    private WebElement butterBarCashPriceText;
    @FindBy(xpath = "//*[@class='modified-declined-title']")
    private WebElement title_modifiedDeclinePage;

    @FindBy(xpath = "//*[@class='modified-declined-text']")
    private WebElement subtitle_modifiedDeclinePage;

    @FindBy(xpath = "(//h5[text()='Shop Approved Items']//following::p)[1]")
    private WebElement carousel_ComponentText;

    @FindBy(xpath = "//*[text()='SHOP ALL APPROVED ITEMS']")
    private WebElement shopAllApprovedItems;

    @FindBy(xpath = "//*[contains(@class, 'chakra-container')]//*[contains(text(), \"You're approved to rent!\")]")
    private WebElement butterBarYouAreApprovedToRentText;

    @FindBy(xpath = "//*[contains(@class, 'chakra-container')]//button[contains(text(), \"Shop Approved Items\")]")
    private WebElement butterBarShopApprovedItemsButton;
    @FindBy(xpath = "//*[contains(@class,'billingAddress')]//p")
    private WebElement billingAddressOnPayementPage;

    @FindBy(xpath = "//p[text()='Great choice! You can easily manage AutoPay in your My Account.']")
    private WebElement enrollInAutoPayVerbiage;

    @FindBy(xpath = "//button[@aria-label='Toggle mask']")
    private WebElement bankAccountToggleMask;

    @FindBy(xpath = "(//label[@for='routingNumber']//following::p[2])[1]")
    private WebElement routingNumberErrorMessage;

    @FindBy(xpath = "//p[contains(text(),'See Details')]")
    private WebElement seeDetails_RenewalTermsPage;

    @FindBy(xpath = "//header[text()='Signing Your Agreement']")
    private WebElement signingYourAgreement;

 @FindBy(xpath = "//*[contains(text(),'Rental Details')]//following::div")
 private WebElement rentalDetails_Tooltip;
 @FindBy(xpath = "//p[text()=' Renewal Payment']//following::div")
 private WebElement renewalPayment_Tooltip;
 @FindBy(xpath = "//*[contains(text(),'Purchase Options')]//following::div")
 private WebElement purchaseOptions_Tooltip;

    @FindBy(xpath = "//button[translate(normalize-space(text()), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ') = 'CONTINUE TO PAYMENT']")
    private WebElement continueToPaymentButton_BP;

    @FindBy(xpath = "//button[@aria-label='Close' and contains(@class,'chakra-modal__close-btn')]")
    private WebElement closeBPModalOnConfirmationPage;

    @FindBy(id = "activeLoginBtn")
    private WebElement signInBtnMyAccountDlg;


    @FindBy(xpath = "//*[text()='Routing Number linked to']/strong")
    private WebElement routingNumberLinkedTo;

    @FindBy(xpath = "//a[text()='Verify Bank Account']")
    private WebElement verifyBankAccount;

    private WebElement fieldsOnTooltip_RenewalTerms(String fieldName) {
     return driver.findElement(By.xpath("//h6[text()='"+fieldName+"']"));
    }
    private WebElement applyPromoCode(String promoName) {
        return driver.findElement(By.xpath("//*[@value='"+promoName+"']//parent::label"));
    }

    private WebElement nextDateOnPaySchedule(String nextDate) {
        return driver.findElement(By.xpath("//a[normalize-space()='" + nextDate + "']"));
    }

    //    private WebElement fieldValueOnPricingInfo(String param) {
//        return driver.findElement(By.xpath("//*[text()='" + param +"']//following-sibling::p"));
//    }
    private WebElement fieldValueOnRT(String param) {
        return driver.findElement(By.xpath("//*[text()='" + param + "']//following::strong"));
    }

    private WebElement promoCodeErrorMessage(String promoErrorMessage) {
        return driver.findElement(By.xpath("//p[text()='" + promoErrorMessage + "']"));
    }

    private WebElement paymentDueDetails(String paymentDue) {
        return driver.findElement(By.xpath("(//*[text()='" + paymentDue + "']//following::p//strong)[1]"));
    }

    private WebElement promoCodeCheckbox(String promoCode) {
        return driver.findElement(By.xpath("//div[contains(@class,'renewal_section')]//input[@value='"+promoCode+"']//parent::label[@data-checked]"));
    }

    // Action methods ******************

    public boolean isCheckedBillingAddCheckbox() {
        boolean isChecked = false;
        if (billingAddSameAsDeliveryCheckbox.isSelected()) {
            isChecked = true;
        }
        return isChecked;
    }

    public void enterAddress(String address) {
        //String displayedAddress=addressText.getText().trim();
//     String[] parts = displayedAddress.split(",");
//     String streetAddress = parts[0].trim();
        addressField.clear();
        addressField.sendKeys(address);
       /*
        //addressField.sendKeys(streetAddress);
        addressField.sendKeys(displayedAddress);
        waitForSecs(3);
        List<WebElement> addressOptions=driver.findElements(By.xpath("//div[contains(@class, 'pac-container')]/div[@class='pac-item']"));
        addressOptions.get(0).click();
        */
        if (ConfigReader.getProperty("browser").equalsIgnoreCase("chrome")) {
            waitForSecs(8);
        } else {
            waitForSecs(15);
        }

        List<WebElement> addressOptions = driver.findElements(By.xpath("//div[@class='pac-item']//span[contains(@class, 'pac-icon-marker')]"));
        Log.info("Address list size: " + addressOptions.size());

        List<WebElement> addressOptions1 = driver.findElements(By.xpath("//div[@class='pac-item']"));
        Log.info("Address list size1: " + addressOptions1.size());

        if (!addressOptions.isEmpty()) {
            basePage.scrollToElement(addressOptions.get(0));
            addressOptions.get(0).click();
        } else {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addressOptions1.get(0));
        }

        waitForSecs(3);
//        //closeXPopUp
//        if(basePage.isElementDisplayedOptional(closeXPopUp)){
//            closeXPopUp.click();
//            //basePage.clickButtonIfDisplayed(closeXPopUp);
//            waitForSecs(2);
//            Log.info("pop clicked - Bug");
//        }
    }

    public void enterCity(String city) {
        if (Objects.requireNonNull(cityField.getDomAttribute("value")).isEmpty()) {
            cityField.clear();
            waitForSecs(2);
            cityField.sendKeys(city);
        }
    }

    public void selectState(String state) {
        Select selectState = new Select(stateDropdown);
        selectState.selectByVisibleText(state);
    }

    public void enterZipCode(String zipCode) {
        if (Objects.requireNonNull(zipCodeField.getDomAttribute("value")).isEmpty()) {
            zipCodeField.clear();
            waitForSecs(2);
            zipCodeField.sendKeys(zipCode);
        }
    }

    public void enterMobilePhoneNumber(String phone) {
        String displayedMobileValue = mobileNumberText.getText().trim();
        //String displayedMobileValue="3136391801";
        mobilePhoneField.clear();
        waitForSecs(1);
        mobilePhoneField.sendKeys(phone);
//        mobilePhoneField.sendKeys(displayedMobileValue);
        waitForSecs(3);
    }

    public void enterEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    private void enterVerifyEmail(String emailAddress) {
        confirmEmailField.clear();
        confirmEmailField.sendKeys(emailAddress);

    }

    private void selectCountry(String country) {

        Select selectCountry = new Select(countryDropdown);
        selectCountry.selectByVisibleText(country);
    }

    public void clickContinue() {
        basePage.waitForElementToBeClickable(continueButton);
        basePage.scrollToElement(continueButton);
//         continueButton.click();
        basePage.jsClick(continueButton);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);
        waitForSecs(7);
    }

    // A method to fill check out form
    public void fillCheckoutForm(String firstName, String lastName, String emailAddress, String mobilePhone,
                                 String country, String address, String city, String state, String zipCode) {
        enterFirstName(firstName);
        Log.info(firstName);

        enterLastName(lastName);
        Log.info(lastName);

        enterEmail(emailAddress);
        Log.info(emailAddress);

        enterVerifyEmail(emailAddress);
        Log.info(emailAddress);

        enterMobilePhoneNumber(mobilePhone);
        Log.info(mobilePhone);

        enterAddress(address);
        Log.info(address);

        selectCountry(country);
        Log.info(country);

        enterCity(city);
        Log.info(city);

        selectState(state);
        Log.info(firstName);

//        if(basePage.isElementDisplayedOptional(closeXPopUp)){
//            closeXPopUp.click();
//            //basePage.clickButtonIfDisplayed(closeXPopUp);
//            waitForSecs(2);
//            Log.info("pop clicked - Bug");
//        }

        enterZipCode(zipCode);
        Log.info(zipCode);
    }


    public void enterSSN(String ssn) {
        try{
            if (continueButton.isDisplayed()) {
                clickContinue();
            }
        }
        catch (NoSuchElementException e) {
            Log.info("Continue button not found.");
        }
        basePage.waitForElementVisible(ssnInputField);
        ssnInputField.clear();
        ssnInputField.sendKeys(ssn);
    }

    public void enterDOB(String dob) {
        dobInputField.clear();
        dobInputField.sendKeys(dob);
    }

    public void clickAgreeAndContinueButton() {
        waitForSecs(10);
        //clickCloseXIfExists();
        //agreeAndContinueButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeAndContinueButton);
        waitForSecs(4);
        basePage.waitForElementInvisible(loadingSpinner);
    }

    public void clickCloseXIfExists() {
        try {
            if (closeButton.isDisplayed()) {
                closeButton.click();
                System.out.println("Close button clicked.");
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Close button not present or not clickable.");
        }
    }
    //


    // Action method
    public void clickContinueButtonOnCongratsPopUp() {
        basePage.waitForElementToBeClickable(continueButtonCongratsPopUp);
        continueButtonCongratsPopUp.click();
    }

    public void clickBAVContinueButtonOnCongratsPopUp() {
        basePage.waitForElementToBeClickable(continueButtonCongratsPopUpBAV);
        continueButtonCongratsPopUpBAV.click();
    }

    private WebElement getPayScheduleElement(String paysSchedule) {
        // return driver.findElement(By.xpath("//span[contains(@class, 'chakra-radio__label') and contains(text(), '" + paysSchedule + "']"));
        return driver.findElement(By.xpath("//span[contains(@class, 'chakra-') and contains(text(), '" + paysSchedule + "')]"));
    }

    public void selectPaySchedule(String paySchedule) {
        waitForSecs(10);
        WebElement element = getPayScheduleElement(paySchedule);
        basePage.waitForElementVisible(element);
        //selectPayScheduleRdoBtn.click();
        // WebElement radioLabel = driver.findElement(By.cssSelector(".chakra-radio__label.css-1f01zpm"));
        //((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectPayScheduleRdoBtn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForSecs(3);
    }

    public void selectPaidNextDate(String date) {


//        // Scroll the element into view
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paidNextDate);
//        waitForSecs(1);
//        // Clear existing value
//        ((JavascriptExecutor) driver).executeScript("arguments[0].value='';", paidNextDate);
//        waitForSecs(1);
//        // Set the new value (format: yyyy-MM-dd)
//        ((JavascriptExecutor) driver).executeScript("arguments[0].value=arguments[1];", paidNextDate, date);

        String brow = ConfigReader.getProperty("browser");

        //Mobile - click paid next date edit
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paidNextDate);
//        waitForSecs(2);

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150);");
        waitForSecs(2);

        if (!brow.contains("ios")) {
            paidNextDate.click();
            waitForSecs(3);
        }

        Log.info("Browser Name: " + brow);
        if (brow.contains("android")) {
            // Casting AndroidDriver to SupportsContextSwitching directly
            SupportsContextSwitching contextSwitchingDriver = (SupportsContextSwitching) driver;
            String currentContext = contextSwitchingDriver.getContext();
            Log.info("Current Context: " + currentContext);
            Log.info("Handles: " + contextSwitchingDriver.getContextHandles());

            if (driver instanceof SupportsContextSwitching) {
                ((SupportsContextSwitching) driver).context("NATIVE_APP");
                Log.info("Context switching supported.");
            } else {
                Log.info("Context switching not supported.");
            }

            waitForSecs(3);
            while (!getDriver().findElements(By.xpath("//android.widget.Button[@text='SET' or @text='Set']")).isEmpty()) {
                getDriver().findElement(By.xpath("//android.widget.Button[@text='SET' or @text='Set']")).click();
                waitForSecs(3);
                Log.info("set button clicked");
            }

            waitForSecs(3);
            ((SupportsContextSwitching) driver).context("CHROMIUM");
            waitForSecs(3);

        } else if (brow.contains("ios")) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -200);");
            waitForSecs(3);

            SupportsContextSwitching contextSwitchingDriver = (SupportsContextSwitching) driver;
            String currentContext = contextSwitchingDriver.getContext();
            System.out.println("Current Context: " + currentContext);
            System.out.println("Handles: " + contextSwitchingDriver.getContextHandles());

            if (driver instanceof SupportsContextSwitching) {
                ((SupportsContextSwitching) driver).context("NATIVE_APP");
                System.out.println("Context switching supported.");
            } else {
                System.out.println("Context switching not supported.");
            }
            //click date - pay schedule
            getDriver().findElement(By.xpath("(//XCUIElementTypeOther[@name='When do you get paid next?'])[2]")).click();
            waitForSecs(5);

            getDriver().findElement(By.xpath("//XCUIElementTypeButton[@name='Done']")).click();
            waitForSecs(3);

            // Get all available context handles
            Set<String> contextNames = contextSwitchingDriver.getContextHandles();

            // Find the last context (assuming it's the last element in the list)
            String lastContext = null;
            for (String contextName : contextNames) {
                lastContext = contextName;
                Log.info("context: " + lastContext);
            }

            ((SupportsContextSwitching) driver).context(lastContext);
            waitForSecs(2);

        } else {
            //browser -desktop
            paidNextDate.clear();
            waitForSecs(3);
            paidNextDate.sendKeys(date);
        }

        /*
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll the element into view
        js.executeScript("arguments[0].scrollIntoView(true);", paidNextDate);
        waitForSecs(3);
        // Set the new date value using JavaScript
        js.executeScript("arguments[0].value=arguments[1];", paidNextDate, date);
        waitForSecs(3);
         */

    }

    public void clickContinueButtonOnPayScheduleSection() {
        //continueButtonPayScheduleSection.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButtonPayScheduleSection);
        waitForSecs(8);
    }

    public void clickContinueButtonOnPayScheduleSectionGuestUser() {
        //continueButtonPayScheduleSection.click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -200);");
        waitForSecs(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButtonPayScheduleSectionGuestUser);
        waitForSecs(8);
    }


    public void clickOnContinueToPaymentButton() {
        waitForSecs(5);
        // continueToPaymentButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueToPaymentButton);
        System.out.println("payment button");
        waitForSecs(10);
    }

    public void clickOnPayPalRadioButton() {
        waitForSecs(10);
        basePage.waitForElementVisible(payPalRdoButton);
        //payPalRdoButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", creditOrDebitCardRdoButton);
        waitForSecs(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payPalRdoButton);
        waitForSecs(4);
    }

    public void clickOnBankAccountRadioButton() {
        waitForSecs(5);
        basePage.waitForElementVisible(bankAccountRdoButtonAddPayment);
        //payPalRdoButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bankAccountRdoButtonAddPayment);

    }

    public void clickOnPayPalLogo() {
        waitForSecs(10);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name, '__zoid__paypal_buttons')]")));
        waitForSecs(4);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payPalLogo);
        waitForSecs(4);
        driver.switchTo().defaultContent();
    }

    public void enterPaypalEmail(String payPalEmail) {
        basePage.waitForElementVisible(emailPayPal);
        emailPayPal.clear();
        emailPayPal.sendKeys(payPalEmail);
    }

    public void clickPayPalNext() {
        payPalNextButton.click();
    }

    public void enterPaypalPassword(String payPalPwd) {
        basePage.waitForElementVisible(passwordPayPal);
        passwordPayPal.clear();
        passwordPayPal.sendKeys(payPalPwd);
    }

    public void clickLoginButtonPayPal() {
        payPalLoginButton.click();
    }

    public void selectIAgreeToTheCheckBox() {
        waitForSecs(2);
//        if(!basePage.isElementDisplayed(saveCardAndEnrollAutoPayLabel)){
//            basePage.waitForElementVisible(iAgreeCheckBoxVenmoOrPayPal);
//            basePage.scrollToElement(iAgreeCheckBoxVenmoOrPayPal);
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxVenmoOrPayPal);
//            driver.switchTo().defaultContent();
//        }else{
//            // ccIAgreeCheckboxLabel.click();
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ccIAgreeCheckboxLabel);
//        }

        // ccIAgreeCheckboxLabel.click();
        List<WebElement> list = driver.findElements(By.xpath("//span[contains(text(), 'I agree to the')]"));
//        ((JavascriptExecutor) driver.executeScript("arguments[0].click();", ccIAgreeCheckboxLabel);
        for (WebElement ele : list) {
            if (ele.isDisplayed())
                basePage.jsClick(ele);
        }
        waitForSecs(2);
    }

    public void selectIAgreeToTheCheckBoxVenmo() {
        waitForSecs(2);
        basePage.waitForElementVisible(iAgreeCheckBoxVenmo);
        basePage.scrollToElement(iAgreeCheckBoxVenmo);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxVenmo);
        driver.switchTo().defaultContent();
        waitForSecs(2);
    }

    public void selectIAgreeToTheCheckBoxPaypal() {
        waitForSecs(2);
        basePage.waitForElementVisible(iAgreeCheckBoxPaypalMyAccount);
        basePage.scrollToElement(iAgreeCheckBoxPaypalMyAccount);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxPaypalMyAccount);
        driver.switchTo().defaultContent();
        waitForSecs(2);
    }

    public void clickSubmitAndContinueToESignButton() {
        // submitAndContinueToESignButton.click();
        waitForSecs(3);
        /*driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name, '__zoid__paypal_buttons')]")));
        waitForSecs(1);
        */
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitAndContinueToESignButton);
        driver.switchTo().defaultContent();
        waitForSecs(15);
    }

    public void loginPaypalCredentials(String payPalEmail, String payPalPassword) {

        waitForSecs(4);
        String originalWindow = driver.getWindowHandle();
        //Switch to the new tab
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        enterPaypalEmail(payPalEmail);
        clickPayPalNext();
        enterPaypalPassword(payPalPassword);
        clickLoginButtonPayPal();
        saveAndContinueButtonPayPal.click();
        // Switch back to the original window
        driver.switchTo().window(originalWindow);
        waitForSecs(5);
    }

//    public boolean verifyOrderConfirmationMessageDisplayed() {
//        return basePage.isTextDisplayed("Thank you for your order!");
//    }

    public void clickOnCreditCardRadioButton() {
        System.out.println("Remove");
        waitForSecs(5);
        basePage.waitForElementVisible(creditOrDebitCardRdoButton);
        //creditOrDebitCardRdoButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", creditOrDebitCardRdoButton);

    }

    public void clickOnCreditCardRadioButtonIfnotSelected() {
        waitForSecs(5);
        basePage.waitForElementVisible(creditOrDebitRadioButton);
        String value = driver.findElement(By.xpath("//div[text()='Credit / Debit Card']/ancestor::button")).getAttribute("aria-expanded");
        if (!value.equals("true")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", creditOrDebitCardRdoButton);
        }
    }

    public void saveCreditOrDebitCardCredentials(String ccNumber, String ccExpDate, String ccExpYear, String cvv) {

        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name, 'eProtect-iframe')]")));
        cardNumberInput.clear();
        cardNumberInput.sendKeys(ccNumber);
        Log.info("Selected credit card number: " + ccNumber);
        Select select = new Select(expMonthDropdown);
        select.selectByVisibleText(ccExpDate);
        Log.info("Selected expiration month: " + ccExpDate);
        Select selectYearElem = new Select(expYearDropdown);
        selectYearElem.selectByVisibleText(ccExpYear); // e.g., "2025", "2026", etc.
        Log.info("Selected expiration year: " + ccExpYear);
        cvvInputField.clear();
        cvvInputField.sendKeys(cvv);
        Log.info("Entered CVV: " + cvv);
        waitForSecs(5);

        driver.switchTo().defaultContent();

    }


    public void clickOnVenmoRadioButton() {
        waitForSecs(4);
        basePage.waitForElementVisible(venmoRdoButton);
        //creditOrDebitCardRdoButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", venmoRdoButton);
    }


    public void clickOnVenmoLogo() {
        waitForSecs(2);
        basePage.scrollToElement(venmoButton);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", venmoButton);
        waitForSecs(7);
        driver.switchTo().defaultContent();
    }

    public void loginVenmoCredentials(String venmoEmail, String venmoPassword) {

        waitForSecs(4);
        String originalWindow = driver.getWindowHandle();
        //Switch to the new tab
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        enterVenmoEmail(venmoEmail);
        clickPayPalNext();
        logInWithPasswordInsteadButton.click();
        waitForSecs(2);
        enterPaypalPassword(venmoPassword);
        clickLoginButtonPayPal();
        waitForSecs(5);
        //saveAndContinueButtonPayPal.click();
        // Switch back to the original window
        driver.switchTo().window(originalWindow);
        waitForSecs(10);
        //Scroll to Element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", venmoButton);
        waitForSecs(2);

    }

    private void enterVenmoEmail(String venmoEmail) {
        basePage.waitForElementVisible(emailVenmo);
        emailVenmo.clear();
        emailVenmo.sendKeys(venmoEmail);
    }

    public boolean isVenmoSaveSuccessMessageDisplayed() {

        waitForSecs(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", venmoMessageSaveSuccess);

        return venmoMessageSaveSuccess.isDisplayed() &&
                venmoMessageSaveSuccess.getText().trim().contains("paypal");

    }

    public boolean isVenmoSaveSuccessMessageDisplayedMyAccount() {
        waitForSecs(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", venmoMessageSaveSuccess);
        waitForSecs(2);
        return venmoMessageSaveSuccess.isDisplayed();

    }


    public void clickOnAppleRadioButton() {
        waitForSecs(5);
        basePage.waitForElementVisible(applePayRdoButton);
        //creditOrDebitCardRdoButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", applePayRdoButton);
    }

    public void clickOnBuyWithApplePayButton() {
        basePage.waitForElementToBeClickable(buyWithApplePayButton);
        // buyWithApplePayButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", buyWithApplePayButton);
        //WIP
        waitForSecs(20);
    }

    public void clickOnGooglePayRadioButton() {
        basePage.waitForElementVisible(googlePayRdoButton);
        //creditOrDebitCardRdoButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", googlePayRdoButton);

    }

    public void clickOnBuyWithGPayButton() {
        //gpayButton.click();
        basePage.waitForElementVisible(gpayButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", gpayButton);
    }

    public void loginGooglePayCredentials(String googleEmail, String googlePassword) {

        waitForSecs(4);
        String originalWindow = driver.getWindowHandle();
        //Switch to the new tab
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        emailInput.sendKeys(googleEmail);
        gpayNextButton.click();
        waitForSecs(5);
        driver.switchTo().window(originalWindow);
        waitForSecs(10);

    }


    public void clickOnCashAppPayRadioButton() {
        basePage.waitForElementVisible(cashAppPayRdoButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cashAppPayRdoButton);
        waitForSecs(3);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cashAppPayRdoButton);
        waitForSecs(15);
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -150);");
        waitForSecs(2);

    }

    public void clickOnCashAppPayButton() {

        waitForSecs(10);
        cashAppPayButton.click();
        waitForSecs(10);

        String browser = ConfigReader.getProperty("browser");

        if (browser.contains("bs") || browser.contains("android")) {
            //Mobile - Click on Approve button
            getDriver().findElement(By.xpath("//button[contains(@class, 'RoundedButton_button') and text()='Approve']")).click();
            Log.info("cash app - Approve clicked");
            waitForSecs(8);

            //button[contains(@class, 'RoundedButton_button') and text()='Approve']
            WebElement doneButton = getDriver().findElement(By.xpath("//button[text()='Done']"));
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", doneButton);
            waitForSecs(12);
            Log.info("cash app - Done clicked");
        } else {

            waitForSecs(10);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement closeButton = (WebElement) js.executeScript(
                    "return document.querySelector('#cash-app-pay')?.shadowRoot?.querySelector('button[class*=\"dialog-close-button\"]');"
            );

            if (closeButton != null) {
                js.executeScript("arguments[0].click();", closeButton);
                waitForSecs(3);
                System.out.println("Close button clicked.");
            } else {

                System.out.println("Close button not found.");
                Actions actions = new Actions(driver);
                actions.sendKeys(Keys.ESCAPE).perform();
                waitForSecs(3);
                List<WebElement> closeButton1 = driver.findElements(By.xpath("//button[contains(@data-testid, 'dialog-close-button')]"));
                if (!closeButton1.isEmpty()) {
                    closeButton1.get(0).click();
                    System.out.println("Close button clicked.");
                }
            }


//            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//            WebElement shadowHost = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#cash-app-pay")));
//
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            WebElement shadowRoot = (WebElement) js.executeScript("return arguments[0].shadowRoot", shadowHost);
//
//            if (shadowRoot != null) {
//                WebElement scannerImage = (WebElement) js.executeScript(
//                        "return arguments[0].querySelector('img[alt=\"Scan to Pay\"]')", shadowRoot);
//
//                if (scannerImage != null) {
//                    Boolean isDisplayed = (Boolean) js.executeScript(
//                            "return arguments[0].offsetWidth > 0 && arguments[0].offsetHeight > 0", scannerImage);
//                    System.out.println("Scanner image displayed: " + isDisplayed);
//                } else {
//                    System.out.println("Scanner image not found inside shadow root.");
//                }
//            } else {
//                System.out.println("Shadow root is null.");
//            }


        }

    }

    public boolean isQRCodeVisible() {
        //De scoped
        // boolean visible = qrCodeImage.isDisplayed();
        //Log.info("QR Code is visible: " + visible);
        waitForSecs(2);
        return true;
    }

    public boolean isAppleQRCodeVisible() {
        waitForSecs(5);
        basePage.waitForElementVisible(applePayModal);
        return applePayModal.isDisplayed();
    }

    public void selectSaveThisPaymentMethodCheckboxCashApp() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveThisPaymentMethodCheckboxCashApp);
        waitForSecs(1);
    }


    public void selectSaveThisPaymentMethodCheckbox() {

       /* List<WebElement> iframes = driver.findElements(By.xpath("//iframe[contains(@name, 'vantiv-payframe')]"));
        if (!iframes.isEmpty()) {
            driver.switchTo().frame(iframes.get(0));
            Log.info("Switched to 'vantiv-payframe' iframe successfully.");
        } else {
            //Log.warn("'vantiv-payframe' iframe not found.");
        }*/


        waitForSecs(2);
        if (basePage.isElementDisplayed(saveCardAndEnrollAutoPayLabel)) {
            //do nothing, already check box selected by default
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveCardAndEnrollAutoPayLabel);
            Log.info("Remove");
        } else {
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveThisPaymentMethodCheckbox);
            //basePage.waitForElementVisible(saveThisPaymentMethodCheckbox);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveThisPaymentMethodCheckbox);
            waitForSecs(1);
            //saveThisPaymentMethodCheckbox.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveThisPaymentMethodCheckbox);
            waitForSecs(1);
        }


    }

    @FindBy(xpath = "//*[text()='Billing Address Same as Delivery']")
    private WebElement billingAddressSameAsDeliveryCheckboxLabel;


    public void addBillingAddress(String address, String city, String postalCode, String state) {
        basePage.scrollToElement(billingAddressSameAsDeliveryCheckboxLabel);
        billingAddressSameAsDeliveryCheckboxLabel.click();
        waitForSecs(2);

        addressInput.clear();
        addressInput.sendKeys(address);

        cityInput.clear();
        cityInput.sendKeys(city);

        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);

        stateInput.clear();
        stateInput.sendKeys(state);
    }

    public void clickAddPaymentMethodButton() throws IOException {
        addPaymentMethodButton.click();
      /*  waitForSecs(5);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destination = new File("test-output/screenshots/" +timestamp + ".png");
        FileUtils.copyFile(screenshot, destination);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "test-output/screenshots/" +timestamp + ".png"); */
        waitForSecs(20);
    }

    public void clickMakePaymentButton() {
        Log.info("Clicking on Make Payment button.");
        basePage.scrollToElement(makePaymentButton);
        makePaymentButton.click();
        waitForSecs(30);
        basePage.scrollToElement(paymentSuccessMessage);
        basePage.waitForElementVisible(paymentSuccessMessage);
    }

    public void clickMakePaymentButtonSavedCard() {
        Log.info("Clicking on Make Payment button for Saved Card.");
        basePage.scrollToElement(savedPaymentMethodCard);
        savedPaymentMethodCard.click();
        waitForSecs(30);
        basePage.scrollToElement(paymentSuccessMessage);
        basePage.waitForElementVisible(paymentSuccessMessage);
    }

    public void clickMakePaymentButtonVenmo() {
        Log.info("Clicking on Make Payment button Venmo.");
        basePage.scrollToElement(venmoMakePayment);
        venmoMakePayment.click();
        waitForSecs(30);
        basePage.scrollToElement(paymentSuccessMessage);
        basePage.waitForElementVisible(paymentSuccessMessage);
    }

    public void clickMakePaymentButtonPayPal() {
        Log.info("Clicking on Make Payment button PayPal.");
        basePage.scrollToElement(paypalMakePayment);
        waitForSecs(2);
        paypalMakePayment.click();
        waitForSecs(30);
        basePage.scrollToElement(paymentSuccessMessage);
        basePage.waitForElementVisible(paymentSuccessMessage);
    }


    public boolean isPaymentSuccessfullDisplayed() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                paymentSuccessMessage);
        return paymentSuccessMessage.isDisplayed();
    }

    public String validatePaymentSuccessfulScreen() {
        return totalAmountOnSuccessScreen.getText();
    }

    public boolean backToDashboardLink() {
        return backToDashboard.isDisplayed();
    }

    public boolean paymentSuccessMsgDashboard() {
        basePage.scrollToElement(backToDashboard);
        waitForSecs(1);
        backToDashboard.click();
        waitForSecs(4);
        basePage.waitForElementVisible(paymentSuccessMsgDashboard);
        return paymentSuccessMsgDashboard.isDisplayed();
    }

    public boolean isBankAccountDisplayed() {
        waitForSecs(8);
        basePage.waitForElementVisible(bankAccountLabelAddPayment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                bankAccountLabelAddPayment);
        waitForSecs(2);
        return bankAccountLabelAddPayment.isDisplayed();
    }

    public boolean isDiscoverCardDisplayed() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", discoverCardLabel);
        waitForSecs(1);
        return discoverCardLabel.isDisplayed();
    }

    public boolean isVisaCardDisplayed() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", visaCardLabel);
        return visaCardLabel.isDisplayed();
    }

    public boolean isMasterCardDisplayed() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", mastercardCardLabel);
        return mastercardCardLabel.isDisplayed();
    }

    public boolean isAmericanExpressCardCardDisplayed() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", americanExpressLabel);
        return americanExpressLabel.isDisplayed();
    }

    public boolean isPayPalMethodDisplayed() {
        waitForSecs(5);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", payPalLabelSavedPayment);
        return payPalLabelSavedPayment.isDisplayed();
    }


    public void enterFirstName(String firstName) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
    }

    public boolean isSavedCardDisplayed() {
        return savedCardDetails.isDisplayed();
    }

    public void enterLastName(String lastName) {
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
    }

    public void selectIAgreeCheckBoxCCAddPaymentMethod() {
        // iAgreeCheckBoxCCAddPayment.click();
        // ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", iAgreeCheckBoxCCAddPayment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", ccIAgreeCheckboxLabel);
        waitForSecs(1);
        // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxCCAddPayment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ccIAgreeCheckboxLabel);
        waitForSecs(1);
    }

    public void saveCardCheckBoxCCAddPaymentMethod() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", saveCardCheckboxCCPayment);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveCardCheckboxCCPayment);
        waitForSecs(1);
    }


    public void selectIAgreeCheckBoxPaypalAddPayMentsMyAccounts() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", iAgreeCheckBoxPayPalAddPayment);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxPayPalAddPayment);
        waitForSecs(1);
    }


    @FindBy(xpath = "//select[contains(@id, 'field-')]")
    private WebElement accountTypeDropdown;


    @FindBy(xpath = "//input[starts-with(@id, 'field-') and @placeholder='Enter first name']")
    private WebElement firstNameTextBoxBankAccount;


    @FindBy(xpath = "//input[starts-with(@id, 'field-') and @placeholder='Enter last name']")
    private WebElement lastNameTextBoxBankAccount;


    @FindBy(xpath = "//input[starts-with(@id, 'field-') and @placeholder='Enter 9-digit routing number']")
    private WebElement routingNumberTextBox;


    @FindBy(xpath = "//input[starts-with(@id, 'field-') and @placeholder='Enter account number']")
    private WebElement accountNumberTextBox;


    @FindBy(xpath = "//input[starts-with(@id, 'field-') and @inputmode='tel']")
    private WebElement phoneNumberTextBox;


    @FindBy(xpath = "(//*[contains(text(), 'I agree to the Terms & Conditions and authorize')])[1]")
    private WebElement termsAndConditionsCheckboxBankAccountAddPayment;


    @FindBy(xpath = "//button[contains(text(), 'Add Bank Account')]")
    private WebElement addBankAccountButtonAddPayments;


    public void saveBankAccountDetailsUnderAddPaymentMethod(
            String accountType, String firstName, String lastName, String routingNumber, String accountNumber, String phoneNumber) {

        Select select = new Select(accountTypeDropdown);
        select.selectByVisibleText(accountType);

        firstNameTextBoxBankAccount.sendKeys(firstName);
        lastNameTextBoxBankAccount.sendKeys(lastName);
        routingNumberTextBox.sendKeys(routingNumber);
        accountNumberTextBox.sendKeys(accountNumber);
        phoneNumberTextBox.sendKeys(phoneNumber);
//        if (!billingSameAsProfileCheckbox.isSelected()) {
//            billingSameAsProfileCheckbox.click();
//        }
//        basePage.scrollToElement(termsAndConditionsCheckboxBankAccountAddPayment);
//        termsAndConditionsCheckboxBankAccountAddPayment.click();

        List<WebElement> elements = driver.findElements(By.xpath("//*[contains(text(), 'I agree to the')]"));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                element.click();
                waitForSecs(2);
                break; // Click only the first visible element
            }
        }

        //basePage.scrollToElement(addBankAccountButtonAddPayments);
        //addBankAccountButtonAddPayments.click();
        List<WebElement> elementsAddPaymentMethod = driver.findElements(By.xpath("//button[contains(text(), 'Add payment method')]"));
        for (WebElement element : elementsAddPaymentMethod) {
            if (element.isDisplayed()) {
                element.click();
                waitForSecs(2);
                break; // Click only the first visible element
            }
        }
        waitForSecs(3);
    }

    @FindBy(xpath = "//span[contains(@class, 'chakra-text') and text()='Enroll in AutoPay']")
    private WebElement enrollInAutoPayButton;


    @FindBy(xpath = "(//span[contains(text(), 'I agree to the')])[1]")
    private WebElement iAgreeCheckBoxInEnrollInAutoPay;


    @FindBy(xpath = "//button[contains(@type, 'button') and text()='ENROLL IN AUTOPAY']")
    private WebElement enrollInAutoPayBtnSavedPaymentMethods;

    public void clickEnrollInAutoPayButton() {
        basePage.scrollToElement(enrollInAutoPayButton);
        enrollInAutoPayButton.click();
    }

    public void selectIAgreeToTheCheckBoxEnrollAutoPay() {
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                iAgreeCheckBoxInEnrollInAutoPay);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxInEnrollInAutoPay);
        waitForSecs(1);

    }

    public void selectIAgreeToTheCheckBoxEnrollAutoPayPaypal() {
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});",
                iAgreeCheckBoxPayPalAddPayment);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", iAgreeCheckBoxPayPalAddPayment);
        waitForSecs(1);

    }


    public void clickEnrollInAutoPayButtonUnderSavedPaymentMethods() {

        enrollInAutoPayBtnSavedPaymentMethods.click();

    }

    @FindBy(xpath = "//span[@data-qa='ersd-agree-checkbox-label-text']/span")
    private WebElement iSignCheckbox;

    @FindBy(xpath = "//span[contains(text(),'Continue')]")
    private WebElement continueButtonDocusign;

    @FindBy(xpath = "//button[contains(text(),'Sign Agreement Later')]")
    private WebElement signAgreementLaterButton;

    @FindBy(xpath = "//div[contains(text(),'Order Confirmation') or contains(text(),'successfully placed')]")
    private WebElement orderSuccessMessage;

    @FindBy(xpath = "//p[contains(text(),'Your agreement number:')]//span")
    private WebElement agreementNumber;

    @FindBy(xpath = "//button[@data-qa='ersd-modal-agree']")
    private WebElement continueButtonDocusign_Iagree;

    public void clickISignCheckbox() {
        waitForSecs(15);
        String curURL = driver.getCurrentUrl();
        Log.info("URL: " + curURL);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "current url: " + curURL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[@id='docusign-iframe']")));
        //  driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='docusign-iframe']")));
        waitForSecs(1);
        basePage.waitForElementVisible(iSignCheckbox);
        iSignCheckbox.click();
        waitForSecs(2);
    }

    public void clickContinueDocusignButton() {
        basePage.scrollToElement(continueButtonDocusign_Iagree);
        continueButtonDocusign_Iagree.click();
//        driver.switchTo().defaultContent();
    }

    public boolean isButtonDisplayed(String buttonText) {
        return driver.findElement(By.xpath("//button[text()='" + buttonText + "']")).isDisplayed();
    }

    public boolean isOrderSuccessMessageDisplayed() {
        return orderSuccessMessage.isDisplayed();
    }

    public boolean isAgreementNumberDisplayed() {
        if (agreementNumber.getText() != null && !agreementNumber.getText().isEmpty())
            return true;
        else
            return false;
    }

    public boolean isContinueButtonUnderDocuSignDisplayed() {
        boolean isDisplayed = basePage.isElementDisplayed(continueButtonDocusign);
        driver.switchTo().defaultContent();
        return isDisplayed;
    }

    @FindBy(xpath = "//p[contains(text(),'Instant Bank Verification')]")
    private WebElement instantBankVerificationTitle;

    @FindBy(xpath = "//input[@placeholder='Bank Routing Number']")
    private WebElement bankRoutingNumberInput;

    @FindBy(xpath = "//input[@placeholder='Bank Account Number']")
    private WebElement bankAccountNumberInput;

    //  @FindBy(xpath = "//button[contains(text(),'CONTINUE')]")
    // private WebElement continueButton;

    @FindBy(xpath = "//a[contains(text(),'Reservation & Complete in store')]")
    private WebElement reservationLink;

    // Optional: Tooltip icons (if you want to verify or click them)
    @FindBy(xpath = "(//span[@class='tooltip-icon'])[1]")
    private WebElement routingNumberTooltip;

    @FindBy(xpath = "(//span[@class='tooltip-icon'])[2]")
    private WebElement accountNumberTooltip;

    public boolean isInstantBankVerificationDisplayed() {

        basePage.waitForElementVisible(instantBankVerificationTitle);
        return instantBankVerificationTitle.isDisplayed();
    }

    public void enterBankRoutingNumber(String routingNumber) {
        bankRoutingNumberInput.clear();
        bankRoutingNumberInput.sendKeys(routingNumber);
    }

    public void enterBankAccountNumber(String accountNumber) {
        bankAccountNumberInput.clear();
        bankAccountNumberInput.sendKeys(accountNumber);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void clickOnBavReservationLink() {
        basePage.waitForElementToBeClickable(reservationLink);
        waitForSecs(10);
        reservationLink.click();
    }

    @FindBy(xpath = "//*[text()='Reservation']")
    private WebElement reservationHeader;

    @FindBy(xpath = "//h2[text()='Claim Offer']")
    private WebElement claimOfferHeader;

    @FindBy(xpath = "//h3[contains(text(),'Checkout Online Now')]")
    private WebElement checkoutOnlineNowHeader;

    @FindBy(xpath = "//div[contains(text(),'RECOMMENDED')]")
    private WebElement recommendedBadge;

    @FindBy(xpath = "//h3[contains(text(),'Want a lower deposit?')]")
    private WebElement lowerDepositHeader;

    @FindBy(xpath = "//a[text()='Verify Bank Account']")
    private WebElement verifyBankAccountLink;

    @FindBy(xpath = "//p[contains(text(),\"We'll hold your product up to 3 days\")]")
    private WebElement checkoutDescriptionText;

    public boolean isReservationHeaderDisplayed() {
        return reservationHeader.isDisplayed();
    }

    public boolean isClaimOfferDisplayed() {
        return claimOfferHeader.isDisplayed();
    }

    public boolean isCheckoutOnlineNowHeaderVisible() {
        return checkoutOnlineNowHeader.isDisplayed();
    }

    public boolean isRecommendedBadgeVisible() {
        return recommendedBadge.isDisplayed();
    }

    public boolean isLowerDepositSectionVisible() {
        return lowerDepositHeader.isDisplayed();
    }

    public boolean isVerifyBankAccountLinkVisible() {
        return verifyBankAccountLink.isDisplayed();
    }

    public String getCheckoutDescriptionText() {
        return checkoutDescriptionText.getText();
    }

    public void clickVerifyBankAccountLink() {
        verifyBankAccountLink.click();
    }


    public boolean isClaimOfferHeaderVisible() {
        return claimOfferHeader.isDisplayed();
    }

    public String getClaimOfferDescription() {
        return claimOfferDescription.getText();
    }

    public String getFirstPaymentAmount() {
        return firstPaymentAmount.getText();
    }

    public void clickWhyThisAmountLink() {
        whyThisAmountLink.click();
    }

    public String getTotalDueTodayAmount() {
        return totalDueTodayAmount.getText();
    }

    public void enterPromoCode(String code) {
        waitForSecs(10);
        basePage.waitForElementVisible(promoCodeInput);
        promoCodeInput.clear();
        promoCodeInput.sendKeys(code);
        waitForSecs(2);
    }

    public void clickApplyButton() {
        basePage.waitForElementToBeClickable(applyButton);
        applyButton.click();
        basePage.waitForPageToLoad();
    }


    public boolean isCheckoutOnlineNowToolTiDisplayed() {
        checkoutOnlineNowToolTip.click();
        waitForSecs(1);
        boolean isToolTipTextDisplayed = !getClaimOfferDescription().isEmpty();
        clickCloseXIfExists();
        waitForSecs(1);
        return isToolTipTextDisplayed;
    }


    public boolean isApplyButtonEnabled() {
        return applyButton.isEnabled();
    }

    public void selectNextAvailablePaymentDate() {
        nextDateOnPaySchedule(basePage.getNextDate()).click();
    }

    public void openCalenderOnPayschedule() {
        paidNextDate.click();
    }

    public static boolean valuesAreEqual(double a, double b) {
        final double EPSILON = 1e-6;
        return Math.abs(a - b) < EPSILON;
    }

    public boolean verifySameAsCashAndTotalCostAreSame() {
        //Pricing info section
        String totalCostToOwn = fieldValueOnRT("Total Cost To Own:").getText();
        String sameAsCashPrice = fieldValueOnRT("Same as Cash Price:").getText();
        //renewal terms screen
        String totalCostToOwnOnRT = fieldValueOnRT("Full Term Cost:").getText();
        String sameAsCashPriceOnRT = fieldValueOnRT("Same As Cash:").getText();
        return (totalCostToOwn.replace("$", "").equalsIgnoreCase(totalCostToOwnOnRT)) &&
                sameAsCashPrice.replace("$", "").equalsIgnoreCase(sameAsCashPriceOnRT);

    }

    public boolean isBenefitPlusProductDisplayed() {
        boolean isDisplayed;
        if (driver.getCurrentUrl().contains("BPlus-unable-to-checkout")) {
            isDisplayed = false;
        } else {
            isDisplayed = true;
        }
        return isDisplayed;
    }

    public void clickSubmitAndContinueToESignButton(String paymentType) {
        // submitAndContinueToESignButton.click();
        /*driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@name, '__zoid__paypal_buttons')]")));
        waitForSecs(1);
        */
        waitForSecs(3);
        if (paymentType.equalsIgnoreCase("CreditCard")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitAndContinueToESignButton);
        } else if (paymentType.equalsIgnoreCase("paypal")) {
            List<WebElement> list = driver.findElements(By.xpath("//button[normalize-space()='Submit & Continue To E-Sign']"));
//        ((JavascriptExecutor) driver.executeScript("arguments[0].click();", ccIAgreeCheckboxLabel);
            for (WebElement ele : list) {
                if (ele.isDisplayed())
                    basePage.jsClick(ele);
            }
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitAndContinueToESignButtonPayPal);
        } else if (paymentType.equalsIgnoreCase("venmo")) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitAndContinueToESignButtonPayPal);
        }
        waitForSecs(2);
        driver.switchTo().defaultContent();
        waitForSecs(10);
    }

    public boolean verifyApprovalMessage(String userType) {
        basePage.waitForElementInvisible(loadingSpinner);
        basePage.waitForElementVisible(congratulationsTitle);
        if (userType.equalsIgnoreCase("Approved"))
            return (approved_Approval.isDisplayed() && congratulationsTitle.isDisplayed() && shopMyApprovedItemsButton.isDisplayed());
        else
            return (preApproved_Conditional.isDisplayed() && congratulationsTitle.isDisplayed() && shopMyApprovedItemsButton.isDisplayed());

    }


    public void clickOnReserveButtonOnCongratsPopUp() {
        waitForSecs(3);
        basePage.waitForElementToBeClickable(reserveButtonCongratsPopUp);
        reserveButtonCongratsPopUp.click();
    }

    public void clickOnReserveMyProductButtonOnCongratsPopUp() {
        basePage.waitForElementToBeClickable(reserveMyProductButton);
        reserveMyProductButton.click();
    }

    public boolean isDeniedMessageIsDisplayed() {
        basePage.waitForElementVisible(deniedMessage);
        return deniedMessage.isDisplayed();
    }

    public void enterBankVerificationDetails(String routingNumber, String accountNumber) throws InterruptedException {
        basePage.waitForElementVisible(bankRoutingNumber_input);
        waitForSecs(5);
        bankRoutingNumber_input.click();
        bankRoutingNumber_input.sendKeys(routingNumber);
        bankAccountNumber_input.sendKeys(accountNumber);
        basePage.waitForElementToBeClickable(bavContinue_btn);
        waitForSecs(5);
        bavContinue_btn.click();
    }

    public boolean isPaymentSectionIsDisplayed(String sectionName) {
        waitForSecs(5);
        return getPaymentSection(sectionName).isDisplayed();
    }

    private WebElement getPaymentSection(String sectionName) {
        return driver.findElement(By.xpath("//div[@class='checkout-accordion']//h2[contains(text(),'" + sectionName + "')]//parent::button"));
    }

    public boolean isServiceFailureDisplayedCheckout() {
        waitForSecs(15);
        String curURL = driver.getCurrentUrl();
        if (curURL.contains("failure")) {
            Log.info("service Failure displayed: " + curURL);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "current url: " + curURL);
            return true;
        } else {
            return false;
        }
    }

    public void clickonFinishOnDocuSign() {
        basePage.scrollToElement(finishOnDocuSign);
        finishOnDocuSign.click();
    }

    public void applyPromo(String promoCode) {
        waitForSecs(10);
        basePage.scrollToElement(applyPromoCode(promoCode));
        applyPromoCode(promoCode).click();
    }

    public boolean verifyTotalDueAfterApplyingPromo(String promoCode, String totalDue) {
        waitForSecs(2);
        basePage.waitForElementInvisible(loadingSpinner);
        basePage.waitForElementVisible(totalAmountDue);

        if (promoCode.equalsIgnoreCase("$.01 Pays Your First Week"))
            return (totalAmountDue.getText().equalsIgnoreCase("0.01"));
        else
            return (totalAmountDue.getText().equalsIgnoreCase(totalDue));

        //add else if condition for other promos based on calculation
    }

    public boolean verifyLdwAndBpNotDisplayed() {
        try {
            if (ldwSliderCheckBox.isDisplayed() || bpSliderCheckBox.isDisplayed())
                return false;
        } catch (NoSuchElementException e) {
            return true;

        }
        return false;
    }

    public void scrollToContinuePaymentBtn() {
        basePage.scrollToElement(continueToPaymentButton);
    }

    public boolean verifyBenefitsModalIsDisplayed() {
        return (benefitsModal.isDisplayed());
    }

    public void closeBenefitsModal() {
        waitForSecs(8);
        benefitsModalCloseBtn.click();
    }

    public void toggleLDW() {
        basePage.scrollToElement(ldwSliderCheckBox);
        ldwSliderCheckBox.click();
        basePage.waitForElementInvisible(loadingSpinner);
    }

    public void toggleBenfitsPlus() {
        basePage.scrollToElement(bpSliderCheckBox);
        bpSliderCheckBox.click();
        basePage.waitForElementInvisible(loadingSpinner);
    }

    public boolean verifyLDWToggledOn() {
        String dataChecked = ldwSliderCheckBox.getAttribute("data-checked");
        if (dataChecked != null)
            return true;
        else
            return false;
    }

    public boolean verifyBPToggledOn() {
        String dataChecked = bpSliderCheckBox.getAttribute("data-checked");
        if (dataChecked != null)
            return true;
        else
            return false;
    }

    public boolean verifyLDWToggledoff() {
        String dataChecked = ldwSliderCheckBox.getAttribute("data-checked");
        if (dataChecked != null)
            return false;
        else
            return true;
    }

    public boolean verifyBenfitsPlusToggledoff() {
        String dataChecked = bpSliderCheckBox.getAttribute("data-checked");
        if (dataChecked != null)
            return false;
        else
            return true;
    }


    public boolean verifyBenfitsPlusUnlocktextIsDisplayed(String expText_benefitPlus) {
        return (text_benefitPlus.getText().contains(expText_benefitPlus));
    }

    public void clickLearnMore() {
        learnMore.click();
    }

    public void toggleBenfitsPlusOnModal() {
        bpSliderCheckBoxOnModal.click();
    }

    public void toggleLDWOnModal() {
        ldwSliderCheckBoxOnModal.click();
    }

    public boolean verifyLDWToggledOnOnModal() {
        return (ldwSliderCheckBoxOnModal.isEnabled());
    }

    public boolean verifyBPToggledOnOnModal() {
        return (bpSliderCheckBoxOnModal.isEnabled());
    }

    public boolean verifyLDWToggledoffOnModal() {
        return (!ldwSliderCheckBoxOnModal.isSelected());
    }

    public boolean verifyBenfitsPlusToggledoffOnModal() {
        return (!bpSliderCheckBoxOnModal.isSelected());
    }

    public void clickSignAgreementLater() {
        waitForSecs(5);
        basePage.waitForElementInvisible(loadingSpinner);
        waitForSecs(30);
        basePage.waitForElementVisible(signAgreementLaterButton);
        basePage.jsClick(signAgreementLaterButton);
//        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",signAgreementLater);
    }

    public boolean verifyOrderConfirmationMessageDisplayed() {
        basePage.waitForElementVisible(agrSuccessMessage);
        return (agrSuccessMessage.isDisplayed());
    }

    public boolean isReserveMyProductSectionIsDisplayed() {
        basePage.waitForElementVisible(reserveMyProductSection);
        return reserveMyProductSection.isDisplayed();
    }

    public void clickOnSubmitMyContactInfoLink() {
        basePage.waitForElementToBeClickable(submitMyContactInfoLink);
        submitMyContactInfoLink.click();
    }

    public void clickOnReserveCompleteInStoreLink() {
        basePage.waitForElementToBeClickable(reserveCompleteInStoreLink);
        waitForSecs(10);
        reserveCompleteInStoreLink.click();
    }

    public boolean toggleChk(WebElement label) {
        String dataChecked = label.getAttribute("data-checked");
        if (dataChecked != null)
            return true;
        else
            return false;
    }


    public String getCorrelationIDFromChromeDevTools_working() {

        AtomicReference<String> correlationID = new AtomicReference<>();
        AtomicBoolean isCaptured = new AtomicBoolean(false);

        waitForSecs(20);
        ChromeDriver driverChrome = (ChromeDriver) this.driver;
        DevTools devTools = driverChrome.getDevTools();
        devTools.createSession();
        Log.info("Created Session");

        // Enable Network tracking
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {

            if (isCaptured.get()) {
                return; // skip further processing
            }

            Response response = responseReceived.getResponse();
            Headers headers = response.getHeaders();

            /*
            Log.info("Response: "+response);
            Log.info("headers: "+headers);
             */

            // Check if the header contains correlation ID
            if (headers.containsKey("correlation-id")) {
                // correlationID=headers.get("correlation-id");
                String headerValue = (String) headers.get("correlation-id");
                Log.info("headerValue: " + headerValue);
                correlationID.set(headerValue);
                //correlationID.set((String) headers.get("correlation-id"));
                if (!(headerValue).contains("-")) {
                    isCaptured.set(true); // Mark as captured
                }
                Log.info("Correlation ID ********** " + correlationID.get());
                //Log.info("Correlation ID **********-2 "+headers.get("correlation-id"));
            }


        });

        // Wait for correlation ID to be captured, max 10 seconds
        int retries = 200; // 100 x 100ms = 20 seconds
        while (!isCaptured.get() && retries-- > 0) {
            try {
                Thread.sleep(100); // wait 100ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        Log.info("Final Correlation ID returned: " + correlationID.get());
        return correlationID.get();

        /*
        // Wait to give the listener time to capture correlation ID
        waitForSecs(5);
        Log.info("Before return: "+correlationID.get());
        return correlationID.get();
        */

    }


    public Set<String> getCorrelationIDFromChromeDevTools() {

        // AtomicReference<String> correlationID = new AtomicReference<>();
        Set<String> correlationIDs = new CopyOnWriteArraySet<>();
        AtomicBoolean isCaptured = new AtomicBoolean(false);

        waitForSecs(20);
        ChromeDriver driverChrome = (ChromeDriver) this.driver;
        DevTools devTools = driverChrome.getDevTools();
        devTools.createSession();
        Log.info("Created DevTools Session");

        // Enable Network tracking
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {

            Response response = responseReceived.getResponse();
            Headers headers = response.getHeaders();
            Object idObj = headers.get("correlation-id");
            if (idObj != null) {
                String id = idObj.toString();
                if (!correlationIDs.contains(id)) {
                    correlationIDs.add(id);
                    Log.info("Captured new Correlation ID ********* : " + id);
                    isCaptured.set(true); // Capture flag for exit
                }
            }

        });

        // Wait for correlation ID to be captured, max 10 seconds
        /*
        int retries = 20; // 20 secs
        while (!isCaptured.get() && retries-- > 0) {
           waitForSecs(1);
        }
         */
        waitForSecs(20);
        // Wait for correlation ID to be captured, max 10 seconds
//        int retries = 200; // 100 x 100ms = 20 seconds
//        while (!isCaptured.get() && retries-- > 0) {
//            try {
//                Thread.sleep(100); // wait 100ms
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//                break;
//            }
//        }

        for (String id : correlationIDs) {
            Log.info("Final Collected inside Correlation ID: " + id);
        }
        return correlationIDs;
    }

    public String extractAgreementNumber() {
        waitForSecs(4);
        return agreementNumber.getText();
    }

    public void esignDocumentWithAllSignatures() {
        waitForSecs(5);
        List<WebElement> signHere = driver.findElements(By.xpath("//*[contains(@class,'signature-tab-content')]"));
        for (int i = 0; i < 1; i++) {
            basePage.click(signHere.get(i));
            waitForSecs(2);
            if (basePage.isElementDisplayed(adoptAndSign)) {
                basePage.scrollToElement(adoptAndSign);
                waitForSecs(4);
                basePage.jsClick(adoptAndSign);
                waitForSecs(3);
            }
        }
        //Signing all the place holders
        for (int j = 1; j < signHere.size(); j++) {
            basePage.scrollToElement(signHere.get(j));
            basePage.jsClick(signHere.get(j));
        }
    }

    public void clickIAgreeAndContinueButton() {
        basePage.waitForElementToBeClickable(iAgreeAndContinueButton);
        basePage.jsClick(iAgreeAndContinueButton);
    }

    public void clickonNextOnDocuSign() {
        basePage.waitForElementVisible(nextOnDocuSign);
        basePage.scrollToElement(nextOnDocuSign);
        basePage.jsClick(nextOnDocuSign);
    }

    public Set<String> getCorrelationIDFromChromeDevTools(String paymentType) {

        // AtomicReference<String> correlationID = new AtomicReference<>();
        Set<String> correlationIDs = new CopyOnWriteArraySet<>();
        AtomicBoolean isCaptured = new AtomicBoolean(false);
        waitForSecs(10);

//        if (driver instanceof ChromeDriver) {
//            ChromeDriver chromeDriver = (ChromeDriver) driver;
//            // Use Chrome DevTools logic
//        } else {
//            System.out.println("DevTools access is not supported on this driver instance: " + driver.getClass());
//            // Optional: throw an exception or skip DevTools logic
//        }

        ChromeDriver driverChrome = (ChromeDriver) this.driver;
        DevTools devTools = driverChrome.getDevTools();
        devTools.createSession();
        Log.info("DevTools Session: " + paymentType);

        // Enable Network tracking
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            Headers headers = response.getHeaders();
            Object idObj = headers.get("correlation-id");
            if (idObj != null) {
                String id = idObj.toString();
                if (!correlationIDs.contains(id)) {
                    correlationIDs.add(id);
                    Log.info("Payment Type: " + paymentType + " - Correlation ID *** : " + id);
                    isCaptured.set(true); // Capture flag for exit
                }
            }
        });

        // Wait up to 30 seconds for correlation ID to be captured
        int retries = 300; // 300 x 100ms = 30 seconds
        while (!isCaptured.get() && retries-- > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        /*
        waitForSecs(30);
        // Final short wait to allow async writes to complete
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        */
        for (String id : correlationIDs) {
            Log.info("Payment type: " + paymentType + "before Correlation IDs: " + id);
        }
        return correlationIDs;
    }

    public void clickSetMyPasswordButton() {
        basePage.scrollToElement(setMyPasswordBtn);
        basePage.click(setMyPasswordBtn);
    }

    public String fetchEmailIdUsed() {
        return emailAddress_Signin.getAttribute("value");
    }

    public void enterPasswordAndContinue() {
        basePage.scrollToElement(passwordInput);
        passwordInput.sendKeys("Test@1234");
        basePage.jsClick(continue_SignIn);
    }

    public void enterEmailPasswordAndContinue(String email) {
        waitForSecs(3);
//        basePage.waitForElementVisible("MyAccountsetup page");
        basePage.scrollToElement(emailInput);
        emailInput.sendKeys(email);
        loginPasswordBtn.sendKeys("Test@1234");
        basePage.jsClick(loginButton);
    }

    public void clickEnrollNowBtnOnBPmodal() {
        basePage.scrollToElement(enrollNowBtn_BPModal);
        basePage.jsClick(enrollNowBtn_BPModal);
    }

    public void clickIAgreeAndContinueButton_BpStartOrderPage() {
        basePage.scrollToElement(agreeAndContinueButton_BPStartOrderPage);
        agreeAndContinueButton_BPStartOrderPage.click();

    }

    public boolean verifyBPModalIsDisplayed() {
        return bpModalHeaderOnConfirmationPage.isDisplayed();
    }

    public void closeBenefitsModal_CheckoutConfirmationPage() {
        waitForSecs(3);
        basePage.jsClick(closeBPModalOnConfirmationPage);
    }

    public boolean verifyBPComponentIsDisplayed() {
        return bpComponentOneOnDashBoard.isDisplayed() && bpComponentTwoOnDashBoard.isDisplayed();
    }

    public void clickViewOnBpComponent() {
        basePage.scrollToElement(viewLinkForBP);
        basePage.jsClick(viewLinkForBP);

    }

    public String getTotalDueToday() {
        waitForSecs(10);
        basePage.waitForElementVisible(totalDueToday_amountTxt);
        return totalDueToday_amountTxt.getText();
    }

    public void clickPricingInfoOnPDP() {
        waitForSecs(8);
        basePage.scrollToElement(pricingInfoLink_PDP);
        pricingInfoLink_PDP.click();
        waitForSecs(1);

    }

    public void waitForLoadingSpinnerToDisappear() {
        if (loadingSpinner.isDisplayed()) {
            basePage.waitForElementInvisible(loadingSpinner);
        }
    }

    public int getListOfItemsInPaymentMethods() {
        waitForSecs(5);
        return listOfPaymentsMethod.size();
    }


    public boolean isPromoCodeDisplayed() {
        return basePage.isElementDisplayedOptional(promoCodeInput);
    }

    public void clickContinueButtonOnCongratsPopUpRegUser() {
        basePage.waitForElementToBeClickable(continueButtonCongratsPopUpRegUser);
        continueButtonCongratsPopUpRegUser.click();
    }

    public void selectIAgreeToTheCheckBoxRegUser() {
        waitForSecs(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ccIAgreeCheckboxLabel);
        waitForSecs(2);
        driver.switchTo().defaultContent();
        waitForSecs(1);
    }

    public boolean verifyRequiredFieldsAreDisplayedOnRenewalTermsPage(String paySchedule) {

        String numberOfPayments = fieldValueOnRT("Number of Payments:").getText();
        String paymentFrequency = fieldValueOnRT("Payment Frequency").getText();
        String earlyPurchaseOption = fieldValueOnRT("Early Purchase Option:").getText();
        if (!numberOfPayments.trim().isEmpty() &&
                (paymentFrequency.equalsIgnoreCase(paySchedule)) &&
                earlyPurchaseOption.equalsIgnoreCase("See Agreement"))
            return true;
        else
            return false;

    }

    public void clickContinueButtonOnCongratsPopUpIfDisplayed() {
        waitForSecs(5);
        if (basePage.isElementDisplayedOptional(continueButtonCongratsPopUp)) {
            continueButtonCongratsPopUp.click();
        }
    }

    public void selectIAgreeToTheCheckBoxApplePay() {
        // ccIAgreeCheckboxLabel.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ccIAgreeCheckboxLabelApplePay);
        waitForSecs(2);
    }

    public void clickContinueButtonApplePay() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButtonApplePay);
        waitForSecs(2);
    }

    public void selectIAgreeToTheCheckBoxCashApp() {
        waitForSecs(2);
        // ccIAgreeCheckboxLabel.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cashAppIAgreeCheckboxLabel);
        waitForSecs(2);
    }

    public boolean isReserveYourProductHeaderDisplayed() {
        return reserveYourProductHeader.isDisplayed();
    }

    public void clickReserveMyProductIfDisplayed() {
        waitForSecs(5);
        if (basePage.isElementDisplayedOptional(reserveMyProductButtonAlmostDoneDlg)) {
            reserveMyProductButtonAlmostDoneDlg.click();
        }
    }

    public boolean isEnrollMeInAutoPayCheckBoxDisplayed() {
        return basePage.isElementDisplayedOptional(saveCardAndEnrollAutoPayLabel);
    }

    @FindBy(xpath = "//div[contains(@class,'payment')]//div[@class='chakra-stack css-1811skr']")
    private List<WebElement> Allpromocodes;

    public boolean verifyTotalDueAfterApplyingDesiredPromo(String promoCode, String totalDue, String ExpectedPromoName, String ExpectedTotalDue) {
        waitForSecs(1);
        List<String> promonames = Allpromocodes.stream()
                .flatMap(x -> x.findElements(By.xpath(".//div//span[contains(@class,'chakra-text')]")).stream())
                .map(WebElement::getText)
                .collect(Collectors.toList());
        for (String s : promonames) {
            Log.info("promos that are displayed are: " + s);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "promo that are displayed: " + s);
        }
        Boolean flagForpromo = promonames.stream().anyMatch(x -> x.contains(ExpectedPromoName));
        if (flagForpromo)
            return (totalAmountDue.getText().contains(ExpectedTotalDue));
        else
            return (totalAmountDue.getText().contains(totalDue));

        //add else if condition for other promos based on calculation
    }

    public String getListOfPaymentMethodsDisplayed() {
        String listofpayment = null;
        if (0 != getListOfItemsInPaymentMethods()) {
            listofpayment = listOfPaymentsMethod.stream().map(x -> x.findElement(By.xpath(".//div[@class='css-ils4e4']")).getText()).collect(Collectors.joining(", "));
        }
        return listofpayment;
    }

    // @FindBy(xpath="//p[contains(text(),'reservation number:')]//span")
    @FindBy(xpath = "//h2[contains(text(),'Thank you for your order!')]/ancestor::div[1]//span | //p[contains(text(),'reservation number:')]//span")
    private WebElement reservationNumber;

    public boolean isReservationNumberDisplayed() {
        waitForSecs(10);
        if (reservationNumber.getText() != null && !reservationNumber.getText().isEmpty())
            return true;
        else
            return false;
    }

    public String extractReservationNumber() {
        return reservationNumber.getText().trim();
    }


    public boolean verifyOnlyCCIsDisplayed() {
        for (WebElement item : listOfPaymentsMethod) {
            WebElement divs = item.findElement(By.xpath(".//div"));
            String text = divs.getText().trim().toLowerCase().replaceAll("\\s+", " ");
            if (text.contains("credit / debit card")) {
                return true;
            }
        }
        return false;
    }

    public Map.Entry<Boolean, List<String>> verifyOnlyCCSavedMethodsIsDisplayed() {
        basePage.waitForPageToLoad();
        waitForSecs(3);
        List<String> savedMethodsList = new ArrayList<>();
        boolean isOnlySavedCCDisplayed = true;
        int mycount = listOfSavedPaymentsMethod.size();
        Log.info("list of saved payments methods :" + mycount);
        for (WebElement element : listOfSavedPaymentsMethod) {
            WebElement divs = element.findElement(By.xpath(".//p"));
            Log.info("listOfSavedMethods:" + divs.getText());
            savedMethodsList.add(divs.getText());
            String text = divs.getText().trim().toLowerCase().replaceAll("\\s+", " ");
            if (!((text.contains("discover")) | (text.contains("visa")) | (text.contains("master")) | (text.contains("american")))) {
                isOnlySavedCCDisplayed = false;
            }
        }
        return new AbstractMap.SimpleEntry<>(isOnlySavedCCDisplayed, savedMethodsList);
    }

    public void selectSavedCCPaymentMethod() {
//        radioInputToSavedCC.click();
        waitForSecs(4);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioInputToSavedCC);

    }

    public void selectAgreeToTermsAndEnrollToAutopay() {
        basePage.waitForElementVisible(radioInputToEnrollToAutopay);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioInputToEnrollToAutopay);
        basePage.waitForElementVisible(radioInputToAgreeToTerms);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioInputToAgreeToTerms);
        waitForSecs(2);
    }

    public void clickOnSSNTooltipButton() {
        basePage.waitForElementVisible(ssnToolTipButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ssnToolTipButton);
    }

    public boolean clickOnSSNTooltipCloseButton() {
        basePage.waitForElementVisible(ssnToolTipCloseButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", ssnToolTipCloseButton);
        waitForSecs(3);
        boolean isTooltipClosed;
        try {
            isTooltipClosed = !ssnToolTipCloseButton.isDisplayed();
        } catch (NoSuchElementException e) {
            isTooltipClosed = true; // Element removed from DOM
        }
        return isTooltipClosed;

    }

    public boolean verifySSNToolTipContents() {
        waitForSecs(4);
        WebElement headingTextElement = ssnToolTipText.findElement(By.xpath(".//h6"));
        WebElement paragraphTextElement = ssnToolTipText.findElement(By.xpath(".//p"));
        String headingText = headingTextElement.getText();
        Log.info(headingText);
        String paragraphText = paragraphTextElement.getText();
        Log.info(paragraphText);
        return (headingText.equals(SSN_TOOLTIP_HEADING)) & (paragraphText.contains(SSN_TOOLTIP_PARAGRAPH_PART1));
    }

    public boolean testNumericInputIsAcceptedAndMasked() {
        String validSSN = "123456";
        enterSSN(validSSN);
        String value = ssnInputField.getAttribute("value");
        String type = ssnInputField.getAttribute("type");
        return (value.equals(validSSN) && type.equals("password"));

    }

    public boolean testAlphabeticInputIsRejected() {
        enterSSN("abc");
        String value = ssnInputField.getAttribute("value");
        return (value != null && value.isEmpty());
    }

    public boolean testSpecialCharactersAreRejected() {
        enterSSN("@#$%");
        String value = ssnInputField.getAttribute("value");
        return (value != null && value.isEmpty());
    }

    public boolean validateHyphenInsertionDOBFormat() {
        enterDOB("01021990");
        String value = dobInputField.getAttribute("value");
        return (value != null && value.equals("01-02-1990"));
    }

    public boolean testInvalidFormatMsgDisplayedOnWrongDOBFormat() {
        enterDOB("19900102");
        waitForSecs(2);
        return invalidDOBErrorMsg.isDisplayed();
    }


    public boolean testConsentFormLinkIsDisplayed() {
        basePage.waitForPageToLoad();
        return basePage.isLinkDisplayed("Third Party Terms");
    }


    public boolean testPopupAppearsOnClick() {
        waitForSecs(2);
        basePage.waitForElementVisible(payfoneThirdPartyConsentPopup);
        return payfoneThirdPartyConsentPopup.isDisplayed();

    }


    public boolean testPopupContentIsCorrect() {
        waitForSecs(4);
        WebElement headingTextElement = payfoneThirdPartyConsentPopupContents.findElement(By.xpath(".//h2"));
        WebElement paragraphTextElement = payfoneThirdPartyConsentPopupContents.findElement(By.xpath(".//p"));
        String headingText = headingTextElement.getText();
        Log.info(headingText);
        String paragraphText = paragraphTextElement.getText();
        Log.info(paragraphText);
        return (headingText.equals(PAYFONE_THIRD_PARTY_CONSENT_CONTENT_HEADING)) & (paragraphText.contains(PAYFONE_THIRD_PARTY_CONSENT_CONTENT_PARAGRAPH));
    }


    public boolean testClosePopupWithXButton() {
        basePage.waitForElementVisible(payfoneThirdPartyConsentPopupCloseButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payfoneThirdPartyConsentPopupCloseButton);
        waitForSecs(3);
        // Check if popup is closed
        boolean isPopupClosed;
        try {
            isPopupClosed = !payfoneThirdPartyConsentPopup.isDisplayed();
        } catch (NoSuchElementException e) {
            isPopupClosed = true; // Element removed from DOM
        }
        basePage.switchToDefaultContent();
        return isPopupClosed;
    }

    public boolean testSecureFormTextIsDisplayed() {
        waitForSecs(2);
        basePage.waitForElementVisible(payfoneSecureFormText);
        return payfoneSecureFormText.isDisplayed();
    }

    public boolean verifyNoPromosDisplayedOnPage() {
        if (!driver.getPageSource().contains("Promo Code (Choose one or enter your own)")
                && !driver.getPageSource().contains("Promo:"))
            return true;

        else return false;
    }

    public boolean verifyLoadingSpinnerIsDisplayed() {
        return loadingSpinner.isDisplayed();
    }

    public boolean verifyErrorMessageDisplayedForTheInvalidPromoCode(String errorMessage) {
        return basePage.isTextDisplayedWithSpecialChars(errorMessage);
    }

    public boolean testEmailInPayfone(String input, String inputValue) {
        if (input.equalsIgnoreCase("valid")) {
            basePage.clearTextAndEnterValueInInputField(payfoneEmailInput, inputValue);
            waitForSecs(3);
            return true;
        } else {
            basePage.scrollToElement(payfoneEmailInput);
            basePage.enterText(payfoneEmailInput, inputValue);
            payfoneEmailInput.sendKeys("\t");
            waitForSecs(3);
            return payfoneEmailErrorMsg.isDisplayed();
        }
    }

    public boolean testMobileInPayfone(String input, String inputValue) {
        if (input.equalsIgnoreCase("valid")) {
            basePage.scrollToElement(payfoneMobileInput);
            basePage.clearTextAndEnterValueInInputField(payfoneMobileInput, inputValue);
            waitForSecs(3);
            basePage.waitForElementInvisible(loadingSpinner);
            String mobileInput = payfoneMobileInput.getAttribute("value");
            return mobileInput.matches("\\(\\d{3}\\) \\d{3}-\\d{4}");
        } else {
            basePage.enterText(payfoneMobileInput, inputValue);
            payfoneMobileInput.sendKeys("\t");
            return payfoneMobileErrorMsg.isDisplayed();
        }
    }

    public boolean testSSNInPayfone(String input, String inputValue) {
        if (input.equalsIgnoreCase("valid")) {
            basePage.clearTextAndEnterValueInInputField(payfoneSSNInput, inputValue);
            waitForSecs(3);
            return true;
        } else {
            basePage.scrollToElement(payfoneSSNInput);
            basePage.enterText(payfoneSSNInput, inputValue);
            payfoneSSNInput.sendKeys("\t");
            waitForSecs(3);
            return payfoneSSNErrorMsg.isDisplayed();
        }
    }

    public void clickOnMobileTooltipButton() {
        basePage.waitForElementVisible(payfoneMobileToolTipButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payfoneMobileToolTipButton);

    }

    public void clickOnMobileTooltipCloseButton() {
        basePage.waitForElementVisible(closeXPopUp);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeXPopUp);
        waitForSecs(3);
    }

    public boolean verifyMobileToolTipContents() {
        waitForSecs(2);

        return basePage.isTextDisplayedWithSpecialChars(PAYFONE_MOBILE_TOOLTIP_PART_1) &
                basePage.isTextDisplayedWithSpecialChars(PAYFONE_MOBILE_TOOLTIP_PART_2) &
                basePage.isTextDisplayedWithSpecialChars(PAYFONE_MOBILE_TOOLTIP_PART_3) &
                basePage.isTextDisplayedWithSpecialChars(PAYFONE_MOBILE_TOOLTIP_PART_4);
    }

    public boolean isContinueButtonEnabled() {
        basePage.scrollToElement(continueButton);
        return true;
    }

    public boolean isLastNameMatches(String lastName) {
        return wofPrefilledFullNameValue.getText().contains(lastName);
    }

    public boolean isMobileNoMatches(String mobile) {
        return wofPrefilledMobileNoValue.getText().equals(mobile);
    }

    public boolean isZipcodeMatches(String zipcode) {
        String addressText = wofPrefilledDeliveryAddrValue.getText();
        return addressText.contains(zipcode);
    }

    public boolean isEmailMatches(String email) {
        basePage.waitForElementVisible(wofPrefilledEmailValue);
        String addressText = wofPrefilledEmailValue.getText();
        return addressText.contains(email);
    }

    public void clickOnEditInformation() {
        basePage.scrollToElement(wofPrefilledEditButton);
        wofPrefilledEditButton.click();
    }

    public boolean isEmailFieldDisabledForEdit() {
        basePage.waitForElementVisible(confirmEmailField);
        return !confirmEmailField.isEnabled();

    }

    public boolean isPayfoneFormFieldsDisplayed() {
        basePage.waitForElementVisible(payfoneMobileInput);
        return payfoneSSNInput.isDisplayed() && payfoneMobileInput.isDisplayed() && payfoneEmailInput.isDisplayed();
    }

    public boolean isPayfoneDefaultLegalDescriptionIsDisplayed() {
        return payfoneLegalDescription.isDisplayed();
    }

    public boolean isPayfoneMarketingMsgForYesIsDisplayed() {
        return payfoneMarketingMsgOnYesClicked.isDisplayed();
    }

    public boolean isPayfoneMarketingMsgForNoIsDisplayed() {
        return payfoneMarketingMsgOnNoClicked.isDisplayed();
    }

//    public boolean isPayfoneAutomatedMarketingDialogIsDisplayed()
//    {
//        return payfoneAutomatedMarketingTextsDialogText.isDisplayed();
//    }


    public void clickPayfoneYesButton() {
        basePage.scrollToElement(payfoneYesButton);
        payfoneYesButton.click();
    }

    public void clickPayfoneNoButton() {
        basePage.scrollToElement(payfoneNoButton);
        payfoneNoButton.click();
    }

    public boolean verifySSNToolTipContentsFromPayfone() {
        waitForSecs(2);
        return basePage.isTextDisplayedWithSpecialChars(PAYFONE_SSN_TOOLTIP_TEXT);
    }

    public void clickOnPayfoneSSNTooltipButton() {
        basePage.waitForElementVisible(payfoneSsnToolTipButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payfoneSsnToolTipButton);
    }

    public boolean promoCodeErrorMessageIsDisplayed(String errorMessage) {
        waitForSecs(2);
        return promoCodeErrorMessage(errorMessage).isDisplayed();
    }

    public boolean verifyPaymentDetailsAreDisplayed(String PaymentDue) {
        waitForSecs(3);
        return paymentDueDetails(PaymentDue).isDisplayed();
    }

    public String paymentDuePrice(String paymentDue) {
        waitForSecs(3);
        return paymentDueDetails(paymentDue).getText();
    }


    public boolean allFieldsEmptyOnPayefoneOnPageLoad() {
        basePage.waitForElementVisible(payfoneMobileInput);
        return payfoneSSNInput.getText().isEmpty() && payfoneMobileInput.getText().isEmpty() && payfoneEmailInput.getText().isEmpty();
    }

    public boolean validateButterBarContent() {
        waitForSecs(2);
        basePage.waitForElementVisible(butterBarCashPriceText);
        return butterBarCashPrice.isDisplayed() &
                butterBarCashPriceText.isDisplayed() &
//                basePage.isTextDisplayedWithSpecialChars("4,000.00") &
                butterBarYouAreApprovedToRentText.isDisplayed() &
                butterBarShopApprovedItemsButton.isDisplayed();

    }


    public boolean verifyNavigationToModifiedDeclinedPage(String expectedPageTitle, String expectedPageSubtitle) {
        return title_modifiedDeclinePage.getText().equalsIgnoreCase(expectedPageTitle) && subtitle_modifiedDeclinePage.getText().equalsIgnoreCase(expectedPageSubtitle);
    }

    public boolean verifyCarouselComponentOnModifiedDeclinePage() {
//        carousel_ComponentText.getText().equalsIgnoreCase(expcarouselComponentText) &&
        return shopAllApprovedItems.isDisplayed();

    }

    public boolean verifyBillingAddressSelectedByDefaultAndAutoPopulated(String expAddress) {
        System.out.println(billingAddressOnPayementPage.getText());
        return billingAddressSameAsDeliveryCheckboxLabel.isSelected() && billingAddressOnPayementPage.getText().equalsIgnoreCase(expAddress);
    }

    public boolean verifyEnrollInAutoPayIsSelectedAndVerbiageDisplayed() {
//        saveCardAndEnrollAutoPayLabel.isSelected() &&
        return enrollInAutoPayVerbiage.isDisplayed();

    }

 public void enterBankRoutingAccountNumber(String routingNumber, String accountNumber) throws InterruptedException {
  basePage.waitForElementVisible(bankRoutingNumber_input);
  waitForSecs(5);
  bankRoutingNumber_input.click();
  bankRoutingNumber_input.sendKeys(routingNumber);
  bankAccountNumber_input.sendKeys(accountNumber);
 }

 public void clickBankAccountToggleMask() {
  basePage.waitForElementToBeClickable(bankAccountToggleMask);
  bankAccountToggleMask.click();
 }

 public String getBankAccountAttributeValue() {
  basePage.waitForElementVisible(bankAccountNumber_input);
     return bankAccountNumber_input.getAttribute("value");
 }

    public boolean verifyBillingAddressAutoPopulated(String expAddress) {
        return billingAddressOnPayementPage.getText().equalsIgnoreCase(expAddress);
    }

    public boolean verifyBankRoutingErrorMessage(String routingErrorMessage) {
        return routingNumberErrorMessage.getText().equalsIgnoreCase(routingErrorMessage);
    }

 public void clickOnSeeDetailsLinkOnRenewalTermsPage() {
     basePage.waitForPageToLoad();
     basePage.jsClick(seeDetails_RenewalTermsPage);
 }

 public boolean verifySigningYourAgreementInfoBox() {
      return signingYourAgreement.isDisplayed();
 }

 public void clickOnRentalDetailsToolTipOnRenewalTermsPage() {
     waitForSecs(2);
     basePage.scrollToElement(rentalDetails_Tooltip);
     basePage.jsClick(rentalDetails_Tooltip);
 }

 public void clickOnRenewalPaymentToolTipOnRenewalTermsPage() {
      waitForSecs(2);
      basePage.scrollToElement(renewalPayment_Tooltip);
      basePage.jsClick(renewalPayment_Tooltip);
 }

 public void clickOnPurchaseOptionsToolTipOnRenewalTermsPage() {
      waitForSecs(2);
      basePage.scrollToElement(purchaseOptions_Tooltip);
      basePage.jsClick(purchaseOptions_Tooltip);
 }

 public boolean verifyToolTipContentForRentalDetails() {
     return fieldsOnTooltip_RenewalTerms("Payment Frequency").isDisplayed() && fieldsOnTooltip_RenewalTerms("Number of Payments").isDisplayed();

 }

 public boolean verifyToolTipContentForRenewalPayment() {
  return fieldsOnTooltip_RenewalTerms("Liability Damage Waiver (LDW)").isDisplayed() && fieldsOnTooltip_RenewalTerms("RAC Benefits Plus").isDisplayed();
 }

 public boolean verifyToolTipContentForPurchaseOptions() {
  return fieldsOnTooltip_RenewalTerms("Same As Cash").isDisplayed() && fieldsOnTooltip_RenewalTerms("Early Purchase Option").isDisplayed() && fieldsOnTooltip_RenewalTerms("Full Term Cost").isDisplayed();
 }

    public void clickOnSignInBtnMyAccount() {
        basePage.jsClick(signInBtnMyAccountDlg);
        waitForSecs(8);
    }

    public void clickOnContinueToPaymentButton_BP() {
        waitForSecs(5);
        // continueToPaymentButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueToPaymentButton_BP);
        System.out.println("payment button");
        waitForSecs(10);
    }
    public boolean verifyBankRoutingBankName(String bankRoutingLinkedName) {
        return routingNumberLinkedTo.getText().equalsIgnoreCase(bankRoutingLinkedName);
    }

    public boolean verifyPromoCodeCheckboxIsChecked(String promoCode){
        waitForSecs(10);
        return promoCodeCheckbox(promoCode).isDisplayed();
    }

    public boolean verifyBankAccountLink() {
        return verifyBankAccount.isDisplayed();
    }

}
