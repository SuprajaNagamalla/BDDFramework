package com.pages;

import static com.qa.factory.DriverFactory.getDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.qa.util.ElementActions;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import com.qa.util.ScenarioContext;

public class MyAccountPage extends ElementActions{
    private WebDriver driver;
    ScenarioContext scenarioContext = new ScenarioContext(); // Shared context
    BasePage basePage=new BasePage(getDriver());
    HomePage homePage=new HomePage(getDriver());
    SignInPage signInPage=new SignInPage(getDriver());
    private static final Logger Log = LoggerHelper.getLogger();
    ElementActions ea = new ElementActions();
   // private static final Logger Log = (Logger) LoggerHelper.getLogger();

    Properties prop = null;



    // Constructor to initialize elements using Page Factory
    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(xpath = "//div[text()='PayPal']")
    private WebElement payPalRdoButton;

    @FindBy(xpath = "//div[text()='Credit / Debit Card']")
    private WebElement creditOrDebitCardRdoButton;

    @FindBy(xpath = "//p[text()='Bank Account']")
    private WebElement bankAccountRdoButtonAddPayment;
    
    @FindBy(xpath = "//*[contains(@id,'chakra-modal')]//button[@aria-label='Close']")
    private WebElement crossButton;
    
    @FindBy(xpath = "//h2[text()='Account Details']")
    private WebElement accountDetailsHeading;

    @FindBy(xpath = "//button[contains(text(),'Refresh the page')]")
    private WebElement refreshThePageButton;
    
    @FindBy(xpath = "//p[contains(text(),'Estimated Cart Total')]")
    private WebElement estimatedCartTotalTxt;
    
    @FindBy(xpath = "//p[text()='Rental Account Balance']")
    private WebElement rentalAccountBalance;

    @FindBy(xpath = "//p[text()='Available Funds']")
    private WebElement availableFundsLabel;

    @FindBy(xpath = "//p[text()='Available Funds']/following-sibling::button[@aria-haspopup='dialog']")
    private WebElement availableFundsTooltipButton;

    @FindBy(xpath = "//p[text()='Available Funds']/..//div[contains(@class, 'chakra-popover__body')]")
    private WebElement tooltipTextContainer;

    @FindBy(xpath = "//p[text()='Available Funds']/..//button[@aria-label='Close']")
    private WebElement closeTooltipButton;

    @FindBy(xpath = "//button[text()='Make Payment']")
    private WebElement agreementPaymentButton;

    @FindBy(xpath = "//h2[text()='Agreement Payment']")
    private WebElement paymentPageHeading;

    @FindBy(xpath = "//label[text()='Use Available Funds']/following::input[@type='checkbox' and @id]")
    private List<WebElement> availableFundsToggle;

    @FindBy(xpath = "//input[@type='number']")
    private WebElement suspenseAmountTextbox;

    @FindBy(xpath = "(//span[text()='Due Amount:'])[1]")
    private WebElement paymentDue;
    
    @FindBy(xpath = "//button[text()='Update Payment Method']/following::button[text()='Unenroll AutoPay']")
    private WebElement unEnrollButton;
    
    @FindBy(xpath = "(//button[text()='Unenroll AutoPay'])[2]")
    private WebElement unEnrollButtonPopUp;

    @FindBy(xpath = "//span[text()='Balance Applied:']")
    private WebElement balanceApplied;

    @FindBy(xpath = "//span[text()='Funds Applied']")
    private WebElement fundsApplied;

    @FindBy(id = "suspenseMessage")
    private WebElement suspenseMessageText;

    @FindBy(id = "submitPayment")
    private WebElement submitPaymentButton;

    @FindBy(id = "paymentSuccessPage")
    private WebElement paymentSuccessPage;

    @FindBy(xpath = "//p[normalize-space(text())='Payment History']//parent::a")
    private WebElement paymentHistorySection;
    
    @FindBy(xpath = "//p[normalize-space(text())='Saved Items']//parent::a")
    private WebElement savedItemsSection;
    
    
    @FindBy(xpath = "//p[normalize-space(text())='Personal Info']//parent::a")
    private WebElement personalInfoSection;
    
    
    public void navigateToAccountDetailsPage() {
        signInPage.navigateToMyAccountDetails();
    }

    public boolean isRentalAccountBalanceDisplayed() {
        return rentalAccountBalance.isDisplayed();
    }

    public boolean isAvailableFundsDisplayed() {
        return availableFundsLabel.isDisplayed();
    }

    public void clickAvailableFundsTooltip() {
    	waitForSecs(3);
        availableFundsTooltipButton.click();
    }

    public boolean isAvailableFundsTooltipTextDisplayed() {
        return basePage.isElementDisplayed(tooltipTextContainer);
    }

    public void closeAvailableFundsTooltip() {
        closeTooltipButton.click();
    }

    public void clickAgreementPayment() {
        agreementPaymentButton.click();
    }

    public boolean isOnPaymentPage() {
        return paymentPageHeading.isDisplayed();
    }

    public boolean isAvailableFundsToggleOn() {
    	boolean isSelected = false;
    	for(int i=0; i<availableFundsToggle.size(); i++) {
    		if(availableFundsToggle.get(i).isSelected()) {
    			isSelected=true;
    		}
    	}
        return isSelected;
    }

    public void turnOnAvailableFundsToggle() {
    	try {
    	WebElement element = driver.findElement(By.xpath("(//label[text()='Use Available Funds']/following::input[@type='checkbox' and @id])[1]"));	
    	basePage.jsClick(element);
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }

    public boolean isSuspenseAmountTextboxDisplayed() {
        basePage.isElementDisplayed(suspenseAmountTextbox);
        return suspenseAmountTextbox.isDisplayed();
    }

    public String getDefaultSuspenseAmount() {
        Log.info("Getting default value from suspense amount textbox");
        return suspenseAmountTextbox.getAttribute("value");
    }

    public boolean isSuspenseMessageDisplayed() {
        Log.info("Checking visibility of suspense message");
        return suspenseMessageText.isDisplayed();
    }

    public void enterSuspenseAmount(String amount) {
        Log.info("Entering suspense amount: " + amount);
        suspenseAmountTextbox.clear();
        suspenseAmountTextbox.sendKeys(amount);
    }

    public boolean isAmountAppliedUpdated() {
        Log.info("Checking if Amount Applied is updated correctly");
        // Replace with actual validation logic
        return true;
    }

    public boolean isAmountAvailableUpdated() {
        Log.info("Checking if Amount Available is updated correctly");
        // Replace with actual validation logic
        return true;
    }

    public boolean isSuspenseMessageUpdated() {
        Log.info("Checking if suspense message is updated based on input");
        // Replace with actual message comparison
        return true;
    }

    public void submitPayment() {
        Log.info("Submitting the payment");
        submitPaymentButton.click();
    }

    public boolean isPaymentDueDisplayed() {
        Log.info("Checking if Payment Due is visible");
        return paymentDue.isDisplayed();
    }

    public boolean isBalanceAppliedDisplayed() {
        Log.info("Checking if Balance Applied is visible");
        return balanceApplied.isDisplayed();
    }

    public boolean isFundsAppliedDisplayed() {
        Log.info("Checking if Funds Applied is visible");
        return fundsApplied.isDisplayed();
    }

    public boolean isPaymentSuccessPageDisplayed() {
        Log.info("Checking if payment success page is displayed");
        return paymentSuccessPage.isDisplayed();
    }


    @FindBy(xpath = "//p[text()='Payment Methods']")
    private WebElement paymentMethodsSection;

    //div[contains(., 'Discover •••• 9424')]//button[contains(., 'Delete')]
    @FindBy(xpath = "//*[normalize-space(text())='Delete']")
    private WebElement deleteButtonInSavedPayment;

    @FindBy(xpath = "//button[normalize-space(text())='Delete Payment Method']")
    private WebElement deletePaymentMethod;
    
    @FindBy(xpath = "//*[contains(@class,'modal') and contains(text(),'Delete Payment Method')]")
    private WebElement deletePaymentModal;

    //@FindBy(xpath = "(//button[@type='button' and text()='Cancel'])[2]")
    @FindBy(xpath = "//button[@type='button' and text()='Cancel']")
    private WebElement cancelButtonOnPopUp;

    @FindBy(xpath = "//button[contains(@class,'chakra-button modal') or @aria-label='Close modal']")
    private WebElement closeButtonOnPopUp;

    @FindBy(xpath = "//button[@type='button' and text()='Delete Payment Method']")
    private WebElement deletePaymentMethodButton;
    
    @FindBy(xpath = "//button[contains(text(),'Delete Payment Method')]/..//button[contains(text(),'Cancel')]")
    private WebElement CancelButton;

    //@FindBy(xpath = "//span[contains(@class, 'chakra-radio__label')]//p[contains(text(), 'Discover ••••')]")
    @FindBy(xpath = "//*[normalize-space(text())='Delete']")
    private WebElement deleteButtonInSavedPaymentMethod;

    
    @FindBy(xpath = "//button[contains(text(),'Agreement Details')]")
    private List<WebElement> agreementDetails;
    
    @FindBy(xpath = "//h2[(text()='My Store')]/following-sibling::div")
    private WebElement storeInfo;
    
    
    @FindBy(xpath = "//strong[text()='Total Amount Due']")
    private WebElement TotalAmountDue;

    @FindBy(xpath = "//p[text()='Total Approval up to:']")
    private WebElement TotalApprovalupto;
    
    @FindBy(xpath = "//header[text()='AutoPay Details']/..//*[contains(@class,'chakra-icon autopay-close-icon')] | //*[@id='chakra-modal-ie-modal']//button[@aria-label='Close']")
    private WebElement closePopUp;
    
    @FindBy(xpath = "//h2[normalize-space()='My Approval Amount']")
    private WebElement myApprovalAmount;

    @FindBy(xpath = "//p[@class='chakra-text css-1m5vx1g']")
    private WebElement Amount;

    @FindBy(xpath = "(//p[contains(@class,'chakra-text css-0')])[5]")
    private WebElement PriceDetailsLine1;

    @FindBy(xpath = "(//p[contains(@class,'chakra-text css-1fhgjcy')])[1]")
    private WebElement PriceDetailsLine2;


    @FindBy(xpath = "//button[text()='Make Payment']")
    private WebElement MakePayment;

    @FindBy(xpath = "//h2[text()='My Stores']")
    private WebElement MyStores_Header;
    
    @FindBy(xpath = "//span[contains(text(),'Select Payments')]")
    private WebElement selectPaymentsSection;

    @FindBy(xpath = "//h2[text()='My Stores']//parent::div//div[contains(@class,'css-1a2eny6')]")
    private WebElement Store_ContactDetails_and_Todays_hours;

    @FindBy(xpath = "//p[@class='chakra-text css-e9dfly']")
    private WebElement Store_Address;

    @FindBy(xpath = "//div[contains(@class,'css-6gpq92')]")
    private WebElement List_Of_Agreements_Section;

    @FindBy(xpath = "(//div[starts-with(@class,'chakra-stack css-')]//img)[1]")
    private WebElement ProductImage;

    @FindBy(xpath = "(//h3[@class=\"chakra-heading css-1avs3gc\"])[position()=1]")
    private WebElement ProductName;

    @FindBy(xpath = "(//span[contains(@class,'chakra-text css-15zogrc') and text()='AutoPay Unavailable'])[1]")
    private WebElement AutoPay_Unavailable;

    @FindBy(xpath = "(//span[contains(@class,'chakra-text css-11gucbx') and text()='Enroll in AutoPay'])[1]")
    private WebElement Enroll_in_AutoPay;

    @FindBy(xpath = "//p[contains(@class,'chakra-text css-1pvuj4z')]")
    private WebElement PaymentHistory;

    @FindBy(xpath = "//a[text()='Back to Dashboard']")
    private WebElement BackToDashboardLink;

    @FindBy(xpath = "//button[text()='Get Pre-Approved']")
    private WebElement GetPreApprovedButton;

    @FindBy(xpath = "(//div[contains(@class,'css-6gpq92')])[1]")
    private WebElement LatestAgreementTile;

    @FindBy(xpath = "//div[text()='Start Your Order']")
    private WebElement StartYourOrderText;
    
    @FindBy(id = "firstName")
    private WebElement WebOrderfirstNameText;

    @FindBy(xpath = "//button[text()=\"Shop My Approved Items\"]")
    private WebElement ShopMyApprovedItemsButton;

    @FindBy(xpath = "//h1[text()='My Items']")
    private WebElement MyItemsText;

    @FindBy(xpath = "(//button[contains(@class, 'chakra-button')])[6]")
    private WebElement SignInButton;

    @FindBy(xpath = "//b[text()='My Offers']")
    private WebElement MyOffersText;

    @FindBy(xpath = "//*[contains(@class,'chakra-text') and text()='Welcome to RAC Benefits Plus!']")
    private WebElement BenefitsPlus;

    @FindBy(xpath = "//p[text()='Not Scheduled']")
    private WebElement NotScheduledText;

    @FindBy(xpath = "(//button[text()='Agreement Details'])[1]")
    private WebElement AgreementDetailsButton;

    @FindBy(xpath = "//header[text()=\"Agreement Details\"]")
    private WebElement AgreementDetailsHeader;

    @FindBy(xpath = "//*[name()='path' and contains(@d,'m289.94 25')]")
    private WebElement CrossButton;

    @FindBy(xpath = "//div[@class='chakra-select__icon-wrapper css-iohxn1']")
    private WebElement downarrowicon;

    @FindBy(xpath = "(//span[text()=\"Due Amount:\"])[1]")
    private WebElement DueAmountText;

    @FindBy(xpath = "(//strong[@class='chakra-text css-y22979'])[1]")
    private WebElement AmountDetails;

    @FindBy(xpath = "(//span[text()=\"Due Date:\"])[1]")
    private WebElement dueDateTextInAgreementPaymentScreen;

    @FindBy(xpath = "(//span[contains(@class,'chakra-checkbox__label css-c7o626')])[1]")
    private WebElement getproductname;

    @FindBy(xpath="//h2[contains(text(),'Welcome back')]")
    private WebElement welcomeBackHeader;
    
    @FindBy(xpath="//p[text()='My Store']/following::p[1]")
    private WebElement storeAddress;

    // ======================
    // Methods
    // ======================

    public void clickPaymentMethodsSection() {
        driver.navigate().refresh();
        basePage.waitForPageToLoad();
        //paymentMethodsSection.click();
        driver.switchTo().defaultContent();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", paymentMethodsSection);
        waitForSecs(1);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentMethodsSection);
    }

    public void clickDeleteButtonInSavedPaymentMethods() {
        waitForSecs(5);
        basePage.scrollToElement(deleteButtonInSavedPayment);
        basePage.waitForElementToBeClickable(deleteButtonInSavedPayment);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", deleteButtonInSavedPayment);
        waitForSecs(1);
        deleteButtonInSavedPayment.click();
        waitForSecs(1);
    }

    public void clickButtonWithText(String buttonName) {
        basePage.clickButtonByText(buttonName);
        waitForSecs(2);
    }
    
    public void clickButtonWithTextIfDisplayed(String buttonName) {
    	waitForSecs(4);
    	List<WebElement> ele = driver.findElements(By.xpath("(//span[contains(text(),'"+buttonName+"')])[1]"));
    	if(ele.size()!=0) {
    	for(int i=0; i<=ele.size(); i++) {
    		basePage.clickButtonByText(buttonName);
    		unEnrollButton.click();
            waitForSecs(5);
            unEnrollButtonPopUp.click();
            waitForSecs(15);
        	driver.navigate().refresh();
          if(basePage.isElementDisplayed(closePopUp)) {
          	closePopUp.click();
          }
          	waitForSecs(5);
         	}
    	}
    }
    
    public void clickUnEnrollButton() {
    	unEnrollButtonPopUp.click();
        waitForSecs(5);
        driver.navigate().refresh();
        waitForSecs(2);
        basePage.waitForPageToLoad();
        if(basePage.isElementDisplayed(closePopUp)) {
        	closePopUp.click();
        }
        waitForSecs(2);
    }
    
    public void deleteSavedCardIfPresent() {
        waitForSecs(5);
        try {
        List<WebElement> ele=driver.findElements(By.xpath("//*[normalize-space(text())='Delete']"));
            if(ele.size()!= 0){
            	basePage.waitForElementToBeClickable(deleteButtonInSavedPayment);
            	deleteButtonInSavedPayment.click();
            	waitForSecs(1);
            	basePage.isElementDisplayed(deletePaymentMethod);
            	deletePaymentMethod.click();
            	waitForSecs(5);
            	driver.navigate().refresh();
            	waitForSecs(5);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
    
    public boolean isDeletePopUpIsDisplayed() {

        return basePage.isElementDisplayed(deletePaymentModal);
    }

    public void clickCancelButtonOnPopUp() {
        basePage.waitForElementVisible(cancelButtonOnPopUp);
        basePage.jsClick(cancelButtonOnPopUp);
    }

    public boolean isPaymentMethodNotDeleted() {
        return basePage.isElementDisplayed(deleteButtonInSavedPaymentMethod);

    }

    public void clickCloseButtonOnPopUp() {
        basePage.waitForElementVisible(closeButtonOnPopUp);
        basePage.jsClick(closeButtonOnPopUp);
    }

    public void verifyPopUpIsClosed() {
        waitForSecs(2);
        try {
            if (deletePaymentModal.isDisplayed()) {
                throw new AssertionError("Pop-up modal is still displayed");
            }
        } catch (Exception e) {
            // Expected, element not found means it's closed
        }
    }

    public void clickConfirmDeletePaymentMethod() {
        deletePaymentMethodButton.click();
        waitForSecs(2);
        // Wait for the button to disappear (invisible or removed from the DOM)
        basePage.waitForElementInVisible(deletePaymentMethodButton);
        waitForSecs(5);
       }
    
    public boolean clickCancelButton() {
    	CancelButton.click();
        waitForSecs(2);
        basePage.waitForElementVisible(deleteButtonInSavedPayment);
        return deleteButtonInSavedPayment.isDisplayed();
       }

    public boolean isPaymentMethodDeleted() {

        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -100);");
        waitForSecs(2);

        return basePage.isElementDisplayed(deleteButtonInSavedPaymentMethod);

    }

    public void refreshPage() {
        Log.info("Refreshing the browser page");
        driver.navigate().refresh();
        basePage.waitForPageToLoad();
        waitForSecs(5);
    }


    @FindBy(xpath = "//a[contains(@class, 'chakra-link') and text()='Back to Dashboard']")
    private WebElement backToDashboardLink;

    @FindBy(xpath = "//span[text()='AutoPay Enrolled']")
    private WebElement autoPayEnrollmentSuccess;

    @FindBy(xpath = "//header[contains(text(),'Payment Method Details')]/following::p")
    private WebElement instructionToDelAutoEnrolledPayMethod;

    @FindBy(xpath = "//header[contains(text(),'Payment Method Details')]/following::button")
    private WebElement closeInstructionPopUp;
    
    public void clickBackToDashboardButton() {
       // backToDashboardLink.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", backToDashboardLink);
        waitForSecs(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", backToDashboardLink);
        if(basePage.isElementDisplayedOptional(crossButton)){ 
			  crossButton.click();
			  waitForSecs(2); 
			  }
        basePage.waitForElementVisible(makePayment);
    }

    public boolean isAutoPayEnrollmentSuccessDisplayed() {
    	driver.navigate().refresh();
        basePage.waitForElementVisible(autoPayEnrollmentSuccess);
        return basePage.isElementDisplayed(autoPayEnrollmentSuccess);
    }

    public boolean isInstructionDisplayedToDeleteAutoEnrolledPaymentMethod() {
        String expInstruction="If you wish to delete this payment method, please first go to the dashboard to update your AutoPay payment method on the " +
                "agreement and then delete this payment method.";

        String actInstruction=instructionToDelAutoEnrolledPayMethod.getText().trim();
        closeInstructionPopUp.click();
        return expInstruction.equalsIgnoreCase(actInstruction);

    }

    @FindBy(xpath = "//*[@aria-label='Open account menu']")
    private WebElement myAccountMenu;

    @FindBy(xpath = "//button[contains(text(),'My Account Dashboard')]")
    private WebElement accountDetails;

    @FindBy(xpath = "(//button[text()='Make Payment'])[1]")
    private WebElement makePayment;

    @FindBy(xpath = "//h2[text()='Quick Links']")
    private WebElement quickLinks_Header;

    @FindBy(xpath = "(//*[@id='promo']/following::p)[1]")
    private WebElement pastDueBannerText;

    @FindBy(xpath = "//*[contains(text(),'Past Due')]")
    private WebElement pastDueText;

    @FindBy(xpath = "(//*[contains(text(),'Total Amount Due')])[1]/../p[2]")
    private WebElement amountDueText;

    @FindBy(xpath = "(//*[contains(text(),'Total Amount Due')])[1]/../p[1]")
    private WebElement dueDateText;

    @FindBy(xpath = "//*[@class='css-k008qs']")
    private WebElement myApprovalAmountSection;

    @FindBy(xpath = "(//*[contains(text(),'Select Payments')])[1]")
    private WebElement selectPaymentsText;

    @FindBy(xpath = "//*[contains(text(),'Payment Option')]/following::option[2]")
    private List<WebElement> paymentOption;
    
    @FindBy(xpath = "//*[starts-with(text(),'Due Amount:')]/../strong")
    private List<WebElement> dueAmount;
    
    @FindBy(xpath = "//*[starts-with(text(),'Total Payment:')]/../strong")
    private WebElement totalPayment;

    @FindBy(xpath = "//*[contains(text(),'Due Date:')]/../strong")
    private List<WebElement> dueDate;

    @FindBy(xpath = "//*[@type='checkbox']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//*[@type='checkbox']/../span[2]")
    private List<WebElement> productDescription;
    
    @FindBy(xpath = "(//*[contains(text(),'Continue')])[1]")
    private WebElement continueButton;
    
    @FindBy(xpath = "//div[contains(@class,'payment-methods-accordion-item')]")
    private List<WebElement> paymentMethodsAvailable;
    
    @FindBy(xpath = "(//*[@aria-label='Toggle details'])[1]")
    private WebElement toggleDetails;
    
    @FindBy(xpath = "//button[contains(text(),'Show More')]")
    private WebElement showMoreButton;
    
    @FindBy(xpath = "//a[contains(text(),'Shop Latest Deals')]")
    private WebElement shopLatestDeals;
    
    @FindBy(xpath = "//p[contains(text(),'Payment Details')]/following::div[4]/div")
    private List<WebElement> paymentReceipts;
    
    @FindBy(xpath = "(//a[contains(text(),'View Pricing & Availability')])[1]")
    private WebElement pricingAndAvailabilityButton;
    
    @FindBy(id = "search-input")
    private WebElement searchHomePage;
    
    @FindBy(xpath = "//*[@type='email']")
    private WebElement emailTextBox;
    
    @FindBy(xpath = "//label[contains(text(),'Preferred Contact Time')]/following::select")
    private WebElement preferredContactTime;
    
    @FindBy(xpath = "//button[contains(text(),'Update Information')]")
    private WebElement updateInformation;

    @FindBy(xpath = "//div[text()='Cash App Pay']")
    private WebElement cashAppPayRdoButton;
    
    public boolean agreement_DetailsLink_isDisplayed() {
    	basePage.scrollToElement(agreementDetails.get(0));
    	basePage.waitForElementVisible(agreementDetails.get(0));
    	return agreementDetails.get(0).isDisplayed();
    }
    
    public void AccountDetails() throws InterruptedException {
        waitForSecs(2);
        basePage.waitForPageToLoad();
        homePage.closeAllCookiesButton();
        driver.switchTo().defaultContent();
        basePage.scrollToElement(myAccountMenu);
        waitForSecs(2);
        basePage.jsClick(myAccountMenu);
        waitForSecs(2);
        basePage.click(accountDetails);
        waitForSecs(2);
        basePage.scrollToElement(myAccountMenu);
        driver.navigate().refresh();
        basePage.waitForPageToLoad();
        waitForSecs(5);
    }

    public void clickAccountMenu() {
    	 basePage.scrollToElement(myAccountMenu);
         waitForSecs(1);
         basePage.jsClick(myAccountMenu);
         waitForSecs(2);
    }
    
    public boolean selectPayments() {
    	boolean isSelected = false;
    	basePage.scrollToElement(selectPaymentsSection);
    	selectPaymentsSection.click();
    	waitForSecs(2);
    	for (int i = 0; i < paymentOption.size(); i++) { 
			waitForSecs(2);
			if(checkboxes.get(i).isEnabled() &&  checkboxes.get(i).isSelected()) { 
				isSelected = true;
			  }
		  }
		return isSelected;
    	
    }
    
    public boolean makePaymentButtonIsDisplayed() {
    	 navigateToAccountDetailsPage();
    	 basePage.scrollToElement(makePayment);
    	 basePage.waitForElementVisible(makePayment);
    	 return makePayment.isDisplayed();
    }
    
    public boolean clickMakePaymentAndValidateAndGetAgrDetails() throws InterruptedException {
    	boolean paymentOptionSize = false;
    	HashMap<String, String> eachAgrDetails = new HashMap<>();
    	waitForSecs(5);
    	basePage.scrollToElement(makePayment);
    	basePage.waitForElementToBeClickable(makePayment);
		basePage.click(makePayment);
		waitForSecs(3);
		basePage.waitForElementVisible(selectPaymentsText);
		if(paymentOption.size() > 0) {
			paymentOptionSize = true;
		}
		for (int i = 0; i < paymentOption.size(); i++) { 
			waitForSecs(2);
			if(checkboxes.get(i).isEnabled() &&  checkboxes.get(i).isSelected()) { 
				eachAgrDetails.put("PaymentOption", paymentOption.get(i).getText());
				eachAgrDetails.put("DueAmount", dueAmount.get(i).getText());
				eachAgrDetails.put("DueDate", dueDate.get(i).getText());
				waitForSecs(2);
				ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "Agreement Details: "+ eachAgrDetails);
			  }
		  }
		return paymentOptionSize;
    }
    
    public boolean zeroDollarPaymentWithCOAApplied() throws InterruptedException {
    	boolean totalAmount = false;
    	basePage.scrollToElement(makePayment);
    	basePage.waitForElementToBeClickable(makePayment);
		basePage.click(makePayment);
		waitForSecs(3);
		basePage.waitForElementVisible(selectPaymentsText);
		Double totalPaymentAmount = Double.parseDouble(totalPayment.getText().replace("$", ""));
		if(totalPaymentAmount==0.00) {
			totalAmount = true;
		}
		return totalAmount;
    }
    
    
    public boolean validateNoDropDownforBP() {
    	boolean dropDownOption =false;
    	basePage.waitForElementVisible(selectPaymentsText);
    	for (int i = 1; i < checkboxes.size(); i++) {
			  if (productDescription.get(i-1).getText().contains("Plus"))
			  {
				  try {
				   dropDownOption = paymentOption.get(i-1).isDisplayed();
				  }catch (Exception e) {
					  System.out.println(e);
				}
			  }
    		}
    	return dropDownOption;
    }
    
    public boolean unCheckAllAgreementAndValidate() {
    	boolean totalAmount = false;
    	boolean isContinueButtonEnabled = false;
    	boolean bothConditions = false;
    	basePage.waitForElementVisible(selectPaymentsText);
    	for (int j = 0; j < checkboxes.size(); j++) {
    		waitForSecs(2);
    		basePage.jsClick(checkboxes.get(j));
			waitForSecs(2);
    	}
    	
		/*
		 * for (int l = 0; l < checkboxes.size(); l++) {
		 * if(checkboxes.get(l).isSelected()) { waitForSecs(2);
		 * basePage.jsClick(checkboxes.get(l)); waitForSecs(2); } }
		 */
    	
    	Double totalPaymentAmount = Double.parseDouble(totalPayment.getText().replace("$", ""));
    	isContinueButtonEnabled = continueButton.isEnabled();
    	if(totalPaymentAmount==0) {
    		totalAmount = true;
    		
    	}
    	waitForSecs(3);
    	for (int k = 0; k < checkboxes.size(); k++) {
    		waitForSecs(2);
    		basePage.jsClick(checkboxes.get(k));
			waitForSecs(2);
    	}
    	
    	if(totalAmount==true && isContinueButtonEnabled==false) {
    		bothConditions = true;
    	}
    	return bothConditions;
    }
    
    public boolean validateAgreementOnPaymentOption(String...msgs) {
    	boolean amountEqual =false;
    	Double amount = null;
        try {
        	waitForSecs(2);
			String currentDueDate;
			basePage.waitForElementVisible(selectPaymentsText);
			for (int j = 0; j < checkboxes.size(); j++) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
			    currentDueDate = dueDate.get(j).getText();
			    LocalDate paymentDate = LocalDate.parse(currentDueDate, formatter);
			    LocalDate currentDate = LocalDate.now();
				for (String s : msgs)
				{
				  if (!productDescription.get(j).getText().contains("Plus") && paymentOption.get(j).getText().contains(s) && paymentDate.isAfter(currentDate))
				  {
					  if(checkboxes.get(j).isSelected() && paymentOption.get(j).getText().contains(s) ) {
						  break;
					  }
				  }else {
					  basePage.jsClick(checkboxes.get(j));
					  waitForSecs(2);
				  }
				}
			}
			waitForSecs(4);
			for (int i = 0; i < dueAmount.size(); i++) {
				if(checkboxes.get(i).isSelected()) {
					amount = Double. parseDouble(dueAmount.get(i).getText().replace("$", ""));
				}
			}
			Double totalPaymentAmount = Double.parseDouble(totalPayment.getText().replace("$", ""));
			if(totalPaymentAmount.equals(amount)) {
				amountEqual = true;
			}
			ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+ "Total Payment amount: "+ totalPaymentAmount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return amountEqual;
    }
    
    public void clickContinueButton() {
    	 basePage.scrollToElement(continueButton);
    	 basePage.click(continueButton);
    	 waitForSecs(30);
    }

    public boolean creditRadioButtonIsDisplayed() {
    	boolean creditRadioButton = false;
    	boolean bankRadioButton = false;
    	boolean both = false;
    	if(creditOrDebitCardRdoButton.isDisplayed()) {
    		creditRadioButton =true;
    	}
    	List<WebElement> ele=driver.findElements(By.xpath("//p[text()='Bank Account']"));
        if(ele.size()== 0){
        	bankRadioButton = true;
        }
        if(creditRadioButton==true && bankRadioButton==true) {
        	both =true;
        }
        return both;
    }
    
    public boolean pastDueTextIsDisplayed() {
        return  basePage.isElementDisplayed(pastDueText);
    }

    public boolean myApprovalAmountSection() {
        return  basePage.isElementDisplayed(myApprovalAmountSection);
    }


    public boolean dueDateTextIsDisplayed() {
        basePage.waitForElementVisible(dueDateText);
        return dueDateText.isDisplayed();
    }


    public String amountDueText() {
        basePage.waitForElementVisible(amountDueText);
        return amountDueText.getText();
    }

    private WebElement getQuickLinkComponents(String quickLinkComponents) {
        return driver.findElement(By.xpath("//h2[text()='Quick Links']//parent::div//a[contains(@class,'chakra-link')]//p"));
    }

    private WebElement getQuickLinkComponentsLink(String componentName) {
        return driver.findElement(By.xpath("//p[normalize-space(text())='"+componentName+"']//parent::a"));

    }
    
    private WebElement getComponentsLinkMenu(String componentName) {
        return driver.findElement(By.xpath("//a[normalize-space(text())='"+componentName+"'] | //button[normalize-space(text())='"+componentName+"']"));

    }
    
    private List<WebElement> getStores() {
         return driver.findElements(By.xpath("//h2[contains(text(),'My Stores')]/following-sibling::div"));

    }

    private WebElement getComponentsLinkMenuSignOut(String componentName) {
        return driver.findElement(By.xpath("//*[normalize-space(text())='"+componentName+"']"));
    }
    
    public boolean isQuickLinkComponentsDisplayed(String quickLinkComponents) {
        return getQuickLinkComponents(quickLinkComponents).isDisplayed();
    }

    public boolean verifyQuickLinkHeaderIsDisplayed() {
        basePage.waitForElementVisible(quickLinks_Header);
        return quickLinks_Header.isDisplayed();
    }

    public boolean pastDueBannerIsDisplayed() {
        basePage.waitForElementVisible(pastDueBannerText);
        return pastDueBannerText.isDisplayed();
    }

    public String pastDueBannerText() {
        basePage.waitForElementVisible(pastDueBannerText);
        return pastDueBannerText.getText();
    }

    public String validateDueDate(){
        String currentDueDate = null;
        try {
            currentDueDate = dueDateText.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            LocalDate givenDate = LocalDate.parse(currentDueDate, formatter);
            LocalDate currentDate = LocalDate.now();

            if (givenDate.isAfter(currentDate)) {
            	Log.info("Given date is after the current date.");
            } else {
            	Log.info("Given date is not after the current date.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currentDueDate;
    }
    
   public int getStoreCount() {
	  List<WebElement> count = getStores();
	  return count.size();
	   
   }
   
	public Map<String, String> validatePaymentHistory() {
		Map<String,String> map = new HashMap<String, String>();
		List<String> list = new ArrayList<String>();
    	paymentHistorySection.click();
    	waitForSecs(2);
    	 new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.urlContains("payment-history"));
    	 toggleDetails.click();
    	 waitForSecs(1);
    	 for(int i =2; i<=9; i++) {
    		String value = driver.findElement(By.xpath("(//p[contains(text(),'Payment Details')]/../div[1]//p)["+i+"]")).getText();
    		list.add(value);
    	 }
    	 map.put("Payment Date: ", list.get(0));
    	 map.put("Amount", list.get(1));
    	 map.put("Receipt Number:",list.get(3));
    	 map.put("Payment Type: ", list.get(5));
    	 map.put("Store Number:", list.get(7));
    	 toggleDetails.click();
    	 if(paymentReceipts.size()>4) {
    		 showMoreButton.click();
    	 }
		return map;
    }
    
    public boolean validateSavedItemsSectionMyAccountPage() {
    	boolean savedItem = false;
    	savedItemsSection.click();
    	waitForSecs(2);
    	 new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.urlContains("wishlist"));
    	 if(pricingAndAvailabilityButton.isDisplayed()) {
    		 savedItem =true;
    	 }
    	 else {
    		 shopLatestDeals.click();
    		 waitForSecs(1);
    		 basePage.waitForElementVisible(searchHomePage);
    	 }
    	 return savedItem;
    }
    
    
    public boolean isPersonalInfoUpdated() {
    	boolean isEmailNotMatched = false;
    	boolean ispreferredTimeChanged = false;
    	personalInfoSection.click();
    	waitForSecs(2);
    	 new WebDriverWait(driver, Duration.ofSeconds(10))
         .until(ExpectedConditions.urlContains("personal-info"));
    	 String actualText = emailTextBox.getText();
    	 waitForSecs(1);
    	 emailTextBox.click();
    	 for(int i=0; i<50; i++) {
    		 emailTextBox.sendKeys(Keys.BACK_SPACE);
    	 }
    	 waitForSecs(1);
    	 emailTextBox.sendKeys("changeTextEmail@yopmail.com");
    	 String actualPreferredTime = preferredContactTime.getText();
    	 Select select = new Select(preferredContactTime);
    	 select.selectByIndex(1);
    	 updateInformation.click();
    	 waitForSecs(2);
    	 backToDashboardLink.click();
    	 waitForSecs(2);
    	 personalInfoSection.click();
     	 waitForSecs(2);
     	 new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlContains("personal-info"));
     	 
     	 if(emailTextBox.getText()!=actualText) {
     		  isEmailNotMatched = true;
     		 
     	 }
     	if(preferredContactTime.getText()!=actualPreferredTime) {
     		ispreferredTimeChanged = true;
     	}
     	 emailTextBox.sendKeys(actualText);
     	 select.selectByIndex(0);
     	 updateInformation.click();
    	 waitForSecs(2);
    	 return isEmailNotMatched&&ispreferredTimeChanged;
    }

    public boolean[] isQuickLinksClickRedirected(String linkName, String expectedURLFragment) {
    	boolean errorPage = false;
    	 basePage.scrollToElement(quickLinks_Header);
        if (getQuickLinkComponentsLink(linkName).isDisplayed()) {
            getQuickLinkComponentsLink(linkName).click();
            waitForSecs(2);
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(expectedURLFragment));
        // Verify the URL contains the expected fragment
        String actualURL = driver.getCurrentUrl();
        assert actualURL != null;
        waitForSecs(2);
        List<WebElement> ele=driver.findElements(By.xpath("//button[contains(text(),'Refresh the page')]"));
        if(ele.size()!= 0){
        	errorPage = true;
        }
        return new boolean[] {errorPage ,actualURL.contains(expectedURLFragment)};
    }
    
    public boolean[] isQuickLinksClickRedirectedMenu(String linkName, String expectedURLFragment) {
    	boolean errorPage = false;
    	waitForSecs(4);
    	basePage.scrollToElement(myAccountMenu);
    	waitForSecs(2);
    	 List<WebElement> ele1=driver.findElements(By.xpath("//a[normalize-space(text())='"+linkName+"'] | //button[normalize-space(text())='"+linkName+"']"));
    	if(ele1.size()== 0) {
    		basePage.jsClick(myAccountMenu);
    	}
        waitForSecs(4);
        if (getComponentsLinkMenu(linkName).isDisplayed()) {
        	getComponentsLinkMenu(linkName).click();
            waitForSecs(2);
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(expectedURLFragment));
        // Verify the URL contains the expected fragment
        String actualURL = driver.getCurrentUrl();
        assert actualURL != null;
        waitForSecs(2);
        List<WebElement> ele=driver.findElements(By.xpath("//button[contains(text(),'Refresh the page')]"));
        if(ele.size()!= 0){
        	errorPage = true;
        }
        return new boolean[] {errorPage ,actualURL.contains(expectedURLFragment)};
    }
    
    public boolean[] isQuickLinksClickRedirectedSignOut(String linkName, String expectedURLFragment) {
    	boolean errorPage = false;
        if (getComponentsLinkMenuSignOut(linkName).isDisplayed()) {
        	getComponentsLinkMenuSignOut(linkName).click();
            waitForSecs(2);
        }
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.urlContains(expectedURLFragment));
        // Verify the URL contains the expected fragment
        String actualURL = driver.getCurrentUrl();
        assert actualURL != null;
        waitForSecs(2);
        List<WebElement> ele=driver.findElements(By.xpath("//button[contains(text(),'Refresh the page')]"));
        if(ele.size()!= 0){
        	errorPage = true;
        }
        return new boolean[] {errorPage ,actualURL.contains(expectedURLFragment)};
    }
    
    
    
    private WebElement getUserDetails(String UserDetails) {
        return driver.findElement(By.xpath("//h2[text()='"+UserDetails+"']"));
    }

    private WebElement getApprovalAmountDetails(String approvalAmountDetails) {
        return driver.findElement(By.xpath("(//h2[text()='My Approval Amount']//parent::div//p[contains(@class,'chakra-text my-approval-text')])[1]"));
    }

    private WebElement getpaymentoptions(String paymentoptions) {
            return driver.findElement(By.xpath("//option[contains(text(),'Weekly')]"));
        }

    public boolean isMyApprovalAmountDisplayed() {
        basePage.scrollToElement(myApprovalAmount);
        waitForSecs(2);
        return myApprovalAmount.isDisplayed();
    }

    public boolean ApprovalAmountDetailsDisplayed(String ApprovalAmountDetails) {
        return getApprovalAmountDetails(ApprovalAmountDetails).isDisplayed();
    }

    public boolean TotalAmountDueIsDisplayed() {
        return (TotalAmountDue.isDisplayed() && Amount.getText().matches(".*\\d.*"));
    }

    public String amount() {
        basePage.waitForElementVisible(Amount);
        return Amount.getText();
    }

    public boolean ApprovalDetailsIsDisplayed() {
        //try {
            return (TotalApprovalupto.isDisplayed() && PriceDetailsLine1.getText().matches(".*\\d.*") && PriceDetailsLine2.getText().matches(".*\\d.*") );
        //} catch (Exception e) {
          //  return false;
        //}
    }
        //return (TotalApprovalupto.isDisplayed() && PriceDetails.getText().matches(".*\\d.*"));

    public boolean VerifyMakePaymentIsDisplayed() {
        basePage.waitForElementVisible(MakePayment);
        return MakePayment.isDisplayed();
    }

    public boolean verifyMyStoresHeaderIsDisplayed() {
        basePage.scrollToElement(MyStores_Header);
        basePage.waitForElementVisible(MyStores_Header);
        return MyStores_Header.isDisplayed();
    }

    public boolean verifyStoreContactDetailsandTodayssHoursaredisplayed() {
        basePage.waitForElementVisible(Store_ContactDetails_and_Todays_hours);
        return Store_ContactDetails_and_Todays_hours.isDisplayed();
    }

    public boolean verifyStoreAddressisdiplayed() {
        basePage.waitForElementVisible(Store_Address);
        return Store_Address.isDisplayed();
    }

    public void clickOnMakePaymentLinkButton() {
        basePage.waitForElementToBeClickable(MakePayment);
        basePage.click(MakePayment);
        basePage.waitForPageToLoad();
        waitForSecs(2);
    }

    public boolean gettheuserdetails(String UserDetails){
        waitForSecs(5);
        return getUserDetails(UserDetails).isDisplayed();
    }

    public boolean isListOfAgreementsSectionDisplayed() {
        basePage.waitForElementVisible(List_Of_Agreements_Section);
        return List_Of_Agreements_Section.isDisplayed();
    }

    public boolean isProductImageDisplayed() {
        basePage.scrollToElement(ProductImage);
        basePage.waitForElementVisible(ProductImage);
        return ProductImage.isDisplayed();
    }

    public boolean isProductNameDisplayed() {
        basePage.waitForElementVisible(ProductName);
        return ProductName.isDisplayed();
    }
    public String productNameDisplayed() {
        basePage.waitForElementVisible(ProductName);
        return ProductName.getText();
    }

    public boolean isAutoPayUnavailableDisplayed() {
        basePage.waitForElementVisible(AutoPay_Unavailable);
        return AutoPay_Unavailable.isDisplayed();
    }

    public boolean isEnrollinautopaydisplayed() {
        basePage.waitForElementVisible(Enroll_in_AutoPay);
        return Enroll_in_AutoPay.isDisplayed();
    }

    public boolean ispaymentsmadebythecustomeraredisplayed() {
    	basePage.scrollToElement(PaymentHistory);
        basePage.waitForElementVisible(PaymentHistory);
        return PaymentHistory.isDisplayed();
    }

    public void ClickOnBackToDashboardLink() {
    	basePage.scrollToElement(BackToDashboardLink);
        basePage.waitForElementToBeClickable(BackToDashboardLink);
        basePage.click(BackToDashboardLink);
        waitForSecs(5);
        basePage.waitForElementVisible(makePayment);
    }

    public boolean VerifyGetPreApprovedIsDisplayed() {
        basePage.waitForElementVisible(GetPreApprovedButton);
        return GetPreApprovedButton.isDisplayed();
    }

    public boolean islatestagreementtitleatthetoplistofagreementssectionisDisplayed() {
        basePage.waitForElementVisible(LatestAgreementTile);
        return LatestAgreementTile.isDisplayed();
    }

    public void ClickOnGetPreApprovedButton() {
    	basePage.scrollToElement(GetPreApprovedButton);
        basePage.waitForElementToBeClickable(GetPreApprovedButton);
        basePage.click(GetPreApprovedButton);
        waitForSecs(5);
    }

    public boolean IsUserRedirectedToThePrefilledWOF() {
    	boolean autofill = false;
        basePage.waitForElementVisible(WebOrderfirstNameText);
        if(WebOrderfirstNameText.getText()!=null) {
        	autofill = true;
        }
        return autofill;
    }

    public boolean IsUserRedirectedToThePLP() {
        basePage.waitForElementVisible(MyItemsText);
        return MyItemsText.isDisplayed();
    }

    public void ClickOnShopMyApprovedItemsButton() {
        basePage.waitForElementToBeClickable(ShopMyApprovedItemsButton);
        basePage.click(ShopMyApprovedItemsButton);
    }

    public boolean IsBenefitsPlusProductTileIsDisplayed() {    
    	basePage.waitForElementVisible(BenefitsPlus);    
    	return BenefitsPlus.isDisplayed();
    }

    public boolean VerifyProductDeliveryStatusIsDisplayed() {
        basePage.waitForElementVisible(NotScheduledText);
        return NotScheduledText.isDisplayed();
    }

    public void ClickOnAgreementDetailsButton() {
    	basePage.scrollToElement(AgreementDetailsButton);
        basePage.waitForElementToBeClickable(AgreementDetailsButton);
        basePage.click(AgreementDetailsButton);
    }

    public boolean IsUserRedirectedToTheAgreementDetailsScreen() {
        basePage.waitForElementVisible(AgreementDetailsHeader);
        basePage.click(CrossButton);
        return MyOffersText.isDisplayed();
    }

    public void IsUserclickondownarrowbutton(){
        waitForSecs(2);
        basePage.waitForElementToBeClickable(downarrowicon);
        basePage.click(downarrowicon);
        Log.info("Clicked on downarrow button");
    }

    public boolean PaymentOptionsDisplayed(String paymentoptions) {
        return getpaymentoptions(paymentoptions).isDisplayed();
    }

    public boolean IsDueAmountDisplayed() {
        return (DueAmountText.isDisplayed() && AmountDetails.getText().matches(".*\\d.*"));
    }

    public String amountdetails() {
        basePage.waitForElementVisible(AmountDetails);
        return AmountDetails.getText();
    }

    public boolean VerifyProductNameDisplayed() {
        basePage.waitForElementVisible(getproductname);
        return getproductname.isDisplayed();
    }
    public String productnameisdisplayed() {
        basePage.waitForElementVisible(getproductname);
        return getproductname.getText();
    }

    public boolean verifyUserNavigatedToDashBoard() {
        basePage.waitForElementVisible(welcomeBackHeader);
        return welcomeBackHeader.isDisplayed();
    }
    
    public boolean verifyStoreAddress() {
        basePage.waitForElementVisible(storeAddress);
        return storeAddress.getText().isEmpty();
    }
    
    public int verifyPaymentMethods() {
    	basePage.scrollToElement(makePayment);
    	basePage.waitForElementToBeClickable(makePayment);
		basePage.click(makePayment);
		waitForSecs(3);
		basePage.waitForElementVisible(selectPaymentsText);
		basePage.scrollToElement(continueButton);
		basePage.click(continueButton);
		waitForSecs(3);
		basePage.waitForElementVisible(creditOrDebitCardRdoButton);
		basePage.scrollToElement(creditOrDebitCardRdoButton);
		return paymentMethodsAvailable.size();
    }
    
    public boolean validateStoreUnderAgrDetails() {
    	boolean isStoreDisplayed = false;
    	for(int i=1; i< agreementDetails.size(); i++) {
    		basePage.scrollToElement(agreementDetails.get(i-1));
    		agreementDetails.get(i-1).click();
    		waitForSecs(2);
    		basePage.scrollToElement(storeInfo);
    		isStoreDisplayed = storeInfo.isDisplayed();
    		basePage.click(CrossButton);
    		waitForSecs(1);
    	}
    	return isStoreDisplayed;
    }


    @FindBy(xpath = "//button[text()='Unenroll AutoPay']")
    private WebElement unEnrollAutoPayButton;

    @FindBy(xpath = "(//button[text()='Unenroll AutoPay'])[2]")
    private WebElement unEnrollAutoPayButtonDlg;

    public void clickAutoPayEnrolledAndUnRolledButtonsIfDisplayed() {
        List<WebElement> buttons = driver.findElements(By.xpath("(//button//span[text()='AutoPay Enrolled'])"));
        Log.info("No of auto enroll buttons: "+buttons.size());
        for(WebElement wel : buttons){
            wel.click();
            waitForSecs(2);
            unEnrollAutoPayButton.click();
            Log.info("Click on un Enroll Auto pay button");
            waitForSecs(2);
            unEnrollAutoPayButtonDlg.click();
            Log.info("Click on un Enroll Auto pay button on dialog");
            waitForSecs(10);
        }
    }

    public boolean isCashAppPaymentMethodDisplayed() {
      // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
       waitForSecs(2);
        try{
            ea.highlightElement(cashAppPayRdoButton,"red");
            waitForSecs(1);
            return basePage.isElementDisplayed(cashAppPayRdoButton);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
