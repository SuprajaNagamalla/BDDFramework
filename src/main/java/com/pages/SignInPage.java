package com.pages;

import com.qa.util.Constants;
import com.qa.util.ElementActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import com.qa.util.LoggerHelper;
import org.apache.logging.log4j.Logger;
//import utils.LoggerHelper;
import static com.qa.factory.DriverFactory.getDriver;


//import utilities.WaitHelper;

public class SignInPage extends ElementActions {
	
	//private WebDriver driver;
    protected WebDriver driver;
    private static final Logger Log = LoggerHelper.getLogger();

    BasePage basePage=new BasePage(getDriver());
    HomePage homePage=new HomePage(getDriver());
    // *** Element Locators declaration start ***
    @FindBy(xpath = "(//button[contains(@class, 'chakra-button')])[6]")
     //  @FindBy(xpath = "//a[@aria-label='Sign In']")
    private WebElement signInButton;

    @FindBy(xpath = "//*[text()='My Account']")
    private WebElement myAccountModalHeader;

    @FindBy(xpath = "//input[@id='loginEmail']")
    private WebElement emailAddress;

    @FindBy(xpath = "//input[@id='loginPassword']")
    private WebElement password;

    @FindBy(id = "activeLoginBtn")
    private WebElement signInBtnMyAccountDlg;

    @FindBy(xpath = "//*[contains(@id,'chakra-modal')]//button[@aria-label='Close']")
    private WebElement crossButton;
    
    @FindBy(xpath = "//button[contains(text(),'KEEP ME SIGNED IN')]")
    private WebElement keepMeSignedIn;
    
    @FindBy(id = "loginEmailError")
    private WebElement signInFailedError;

   // @FindBy(xpath = "//*[text()='Log out']")
    @FindBy(xpath = "//*[text()='Sign Out']")
    private WebElement logoutLink;

    //@FindBy(xpath = "//*[text()='Account Details']")
    @FindBy(xpath = "//*[text()='My Account Dashboard']")
    private WebElement accountDetailsLink;


    @FindBy(xpath = "//*[contains(text(),'Payment Method Expiring in 60 Days')]/../../button")
    private WebElement paymentMethodExpiring;

    @FindBy(xpath = "//p[text()='Pricing for']/following-sibling::p[1]")
    private WebElement zipCodeStoreLocatorText;
   // *** Element Locators declaration End ***

	// Constructor to initialize elements using Page Factory
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // *** page action methods on elements ***

    //page actions
    public String getLoginPageTitle() {
    	return driver.getTitle();
    }
    
    public boolean isSignInButtonDisplayed() {
    	return signInButton.isDisplayed();
    }

    public void clickOnSignButtonHomePage() throws InterruptedException {

        basePage.waitForElementToBeClickable(signInButton);
        Log.info("Click sign In button");
        signInButton.click();
        Thread.sleep(3000);
    }

    public boolean isMyAccountDlgDisplayed() {

        //Switch frame
        switchToFrameMyAccountDialog();
        boolean isDisplayed= myAccountModalHeader.isDisplayed();
        driver.switchTo().defaultContent();
        return isDisplayed;

    }

    public void enterEmailAddress(String email) {
        //Switch frame
        switchToFrameMyAccountDialog();
        /*emailAddress.clear();
        emailAddress.sendKeys(email);*/

        basePage.enterText(emailAddress,email);
        driver.switchTo().defaultContent();
    }
    
    public void enterPassword(String pwd) {
        //Switch frame
        switchToFrameMyAccountDialog();
       /* password.clear();
    	password.sendKeys(pwd);*/
        basePage.enterText(password,pwd);
        driver.switchTo().defaultContent();
    }

    @FindBy(id = "verifyPassCode")
    private WebElement otpInput;

    @FindBy(id = "verifyContinueBtn")
    private WebElement continueButtonVerifyInfo;


    public void enterEmailAndPassword(String email, String pwd) {
        switchToFrameMyAccountDialog();
        /*emailAddress.clear();
        emailAddress.sendKeys(email);*/
        basePage.enterText(emailAddress,email);
        basePage.enterText(password,pwd);


    }


//    public void clickOnSignInBtnMyAccountDlg() throws InterruptedException {
//        //Switch frame
//       // switchToFrameMyAccountDialog();
//       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//       // highlightElement(signInBtnMyAccountDlg,"red");
//        //highlightElement(signInBtnMyAccountDlg, "orange");
//        //switchToFrameMyAccountDialog();
//        basePage.click(signInBtnMyAccountDlg);
//        // signInButton.click();
//        waitForSecs(3);
//
//        if(basePage.isElementDisplayedOptional(otpInput)){
//            Log.info("Enter OTP and click continue button");
//            otpInput.clear();
//            otpInput.sendKeys(Constants.SIGN_IN_OTP);
//            continueButtonVerifyInfo.click();
//        }
//        driver.switchTo().defaultContent();
//        waitForSecs(2);
//        basePage.waitForPageToLoad();
//
//        //Enable below code when you see a payment expiring pop up
//		/*  if(basePage.isElementDisplayed(paymentMethodExpiring)) {
//		  paymentMethodExpiring.click();
//		  waitForSecs(2);
//		  }*/
//
//
//        //Enable below code if you see signin pop up
//		  if(basePage.isElementDisplayedOptional(crossButton)){
//			  crossButton.click();
//			  waitForSecs(2);
//			  }
//
//
//       /* //driver.switchTo().defaultContent();
//        Log.info("Page title: "+basePage.getPageTitle());
//        //ExtentCucumberAdapter.addTestStepLog("--> Page title child:"+basePage.getPageTitle());*/
//        homePage.closeAllCookiesButton();
//
//        //Refresh - bug
//        driver.navigate().refresh();
//        basePage.waitForPageToLoad();
//        waitForSecs(2);
//        if(basePage.isElementDisplayedOptional(crossButton)){
//			  crossButton.click();
//			  waitForSecs(2);
//			  }
//    }

    public void clickOnSignInBtnMyAccountDlg() throws InterruptedException {
        //Switch frame
        // switchToFrameMyAccountDialog();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // highlightElement(signInBtnMyAccountDlg,"red");
        //highlightElement(signInBtnMyAccountDlg, "orange");
        //switchToFrameMyAccountDialog();
        basePage.click(signInBtnMyAccountDlg);
        // signInButton.click();
        waitForSecs(3);

        if(basePage.isElementDisplayedOptional(otpInput)){
            Log.info("Enter OTP and click continue button");
            otpInput.clear();
            otpInput.sendKeys(Constants.SIGN_IN_OTP);
            continueButtonVerifyInfo.click();
        }
        waitForSecs(10);
        String ssoURL=driver.getCurrentUrl();
        System.out.println(ssoURL);
        //racprod:worryfree123@
        ssoURL=ssoURL.replace("https://","https://racprod:worryfree123@");
        System.out.println(ssoURL);
        driver.get(ssoURL);
        driver.switchTo().defaultContent();
        waitForSecs(2);
        basePage.waitForPageToLoad();

        //Enable below code when you see a payment expiring pop up
        /*  if(basePage.isElementDisplayed(paymentMethodExpiring)) {
          paymentMethodExpiring.click();
          waitForSecs(2);
          }*/


        //Enable below code if you see signin pop up
        if(basePage.isElementDisplayedOptional(crossButton)){
            crossButton.click();
            waitForSecs(2);
        }


     /* //driver.switchTo().defaultContent();
      Log.info("Page title: "+basePage.getPageTitle());
      //ExtentCucumberAdapter.addTestStepLog("--> Page title child:"+basePage.getPageTitle());*/
        homePage.closeAllCookiesButton();

        //Refresh - bug
        driver.navigate().refresh();
        basePage.waitForPageToLoad();
        waitForSecs(2);
        if(basePage.isElementDisplayedOptional(crossButton)){
            crossButton.click();
            waitForSecs(2);
        }
    }
    
    public void clickOnSignInBtnMyAccount() throws InterruptedException {
        basePage.click(signInBtnMyAccountDlg);
        waitForSecs(3); 
        if(basePage.isElementDisplayedOptional(otpInput)){
            Log.info("Enter OTP and click continue button");
            otpInput.clear();
            otpInput.sendKeys(Constants.SIGN_IN_OTP);
            continueButtonVerifyInfo.click();
        }
        driver.switchTo().defaultContent();
        waitForSecs(2); 
        basePage.waitForPageToLoad();
        
        //Enable below code if you see signin pop up 
		if(basePage.isElementDisplayedOptional(crossButton)){ 
			  crossButton.click();
			  waitForSecs(2); 
		}
		
//		if(basePage.isElementDisplayedOptional(keepMeSignedIn)){ 
//			  keepMeSignedIn.click();
//			  waitForSecs(2); 
//		}
    }

    @FindBy(xpath = "//*[@aria-label='Open account menu']")
    private WebElement myAccountMenu;

    @FindBy(xpath = "//button[@aria-label='Logo']")
    private WebElement logoHomePage;

    public boolean isLoginSuccessful() {
        //basePage.waitForPageToLoad();
        driver.switchTo().defaultContent();
       // basePage.waitForElementToBeClickable(closeButtonSignUpForAutoPay);
        /*if(basePage.isElementDisplayedOptional(closeButtonSignUpForAutoPay)){
            Log.info("click close button Sign Up for AutoPay");
            closeButtonSignUpForAutoPay.click();
        }*/
        waitForSecs(5);
        basePage.waitForPageToLoad();
        basePage.waitForElementVisible(myAccountMenu);
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountMenu).perform();
        waitForSecs(2);
        return basePage.isElementDisplayed(logoutLink);

    }
    public void navigateToMyAccountDetails() {
        driver.switchTo().defaultContent();
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions actions = new Actions(driver);
        actions.moveToElement(myAccountMenu).perform();
        waitForSecs(2);
        accountDetailsLink.click();
        //Account Details
    }

    @FindBy(xpath = "//button[@aria-label='Close' and contains(@class, 'chakra-modal__close-btn')]")
    private WebElement closeButtonSignUpForAutoPay;

    @FindBy(xpath = "//*[@id='acceptAllCookieButton']")
    private WebElement acceptAllCookiesBtn;

    public void clickHomePageLogo() {
        basePage.waitForPageToLoad();
        basePage.waitForElementToBeClickable(logoHomePage);
        //logoHomePage.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoHomePage);
        waitForSecs(2);
        basePage.waitForElementToBeClickable(logoHomePage);

        homePage.closeAllCookiesButton();


        /*
        if(basePage.isElementDisplayedOptional(acceptAllCookiesBtn)){
            System.out.println("2nd time cookies displayed");
            //acceptAllCookiesBtn.click();
            basePage.jsClick(acceptAllCookiesBtn);
            //basePage.clickButtonIfDisplayed(closeXPopUp);
            waitForSecs(2);
        }*/

    }
    

    //*** page reusable methods ****
    public void switchToFrameMyAccountDialog() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement iframeElement = wait.until(ExpectedConditions
            .presenceOfElementLocated(By.xpath("//iframe[@title='Sign In']")));

        //WebElement iframeElement = wait.until(ExpectedConditions
          //      .presenceOfElementLocated(By.xpath("//iframe[@id='signInIframe']")));
        waitForSecs(1);
        driver.switchTo().frame(iframeElement);
        //driver.switchTo().frame("(//iframe[@title='Sign In'])[1]");
        System.out.println("frame switched");
    }

    public boolean isErrorMessageDisplayedOnMyAccountDialog(String expectedErrorMessage) {
        //Switch frame
        switchToFrameMyAccountDialog();
        boolean isErrorDisplayed = basePage.isErrorMessageDisplayed(signInFailedError, expectedErrorMessage);
        driver.switchTo().defaultContent();
        return isErrorDisplayed;
    }

    public String getErrorMessageDisplayedOnMyAccountDialog(String expectedErrorMessage) {
        //Switch to frame
        //switchToFrameMyAccountDialog();
        //driver.switchTo().frame(0);
        String actErrorMessage= basePage.getErrorMessageDisplayed(signInFailedError);
        driver.switchTo().defaultContent();
        return actErrorMessage;
    }

    public String getPricingForZipCodeStoreLocator() {
       driver.switchTo().defaultContent();
        basePage.waitForPageToLoad();
       basePage.waitForElementToBeClickable(logoHomePage);
       return zipCodeStoreLocatorText.getText().trim();
    }
}
