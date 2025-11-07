package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static com.qa.util.ElementActions.waitForSecs;

public class SignUpPage extends BasePage {

    public SignUpPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//h1[text() = 'Sign up for the Latest Deals']")
    private WebElement signupText;


    @FindBy(xpath = "//span[text()='Required']")
    private WebElement requiredText;

    @FindBy(xpath = "//p[text()='Secure Form']")
    private WebElement secureFormText;


    //(//*[starts-with(@id, 'field-') and substring(@id, string-length(@id) - string-length('-label') + 1) = '-label'])[1]
    @FindBy(xpath = "//input[@name = 'email']/preceding::label")
    private WebElement emailText;

    @FindBy(xpath = "//input[@name = 'phone']/preceding::label")
    private WebElement mobileText;

    @FindBy(xpath = "//input[@name = 'optIn'][@type = 'checkbox']")
    private WebElement checkboxOfAgree;

    @FindBy(xpath = "//button[text() = 'Submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//input[@name = 'email']")
    private WebElement emailInput;

    @FindBy(xpath = " //input[@name = 'phone']")
    private WebElement mobileNoInput;

    //*[@id="app-main"]/div[2]/div[2]/div/div[3]/form/div/p[1] - alternative locator
    @FindBy(xpath = "//div[text() = 'Please enter a valid email address.']")

    private WebElement invalidEmailErrorMsg;

    @FindBy(xpath = "//div[text() = 'Please enter a valid mobile number.']")
    private WebElement invalidMobileErrorMsg;

    @FindBy(xpath = "//a[text() = 'SMS Terms and Conditions']")
    private WebElement termsAndConditionsLink;

    @FindBy(xpath = "//p//a[text() = 'Privacy Policy']")
    private WebElement privacyPolicyLink;


    @FindBy(xpath = " //p//a[text() = 'click here']")
    private WebElement clickHereLink;

    @FindBy(xpath = "//*[contains(text() ,\"Sign Up Successful.\")]")
    private WebElement signupSuccessfulMsg;

    public boolean signupTextIsDisplayed() {
        return signupText.isDisplayed();
    }

    public boolean requiredTextAndSecureFormIsDisplayed() {
        return (requiredText.isDisplayed() & secureFormText.isDisplayed());
    }

    public boolean emailAddressTextIsDisplayedWithBlankInputField() {
        return (emailInput.getText().isEmpty() & emailText.isDisplayed());
    }

    public boolean mobileTextIsDisplayedWithBlankInputField() {
        return (mobileNoInput.getText().isEmpty() & mobileText.isDisplayed());

    }

    public boolean checkBoxIsDisplayedAndUnchecked() {
        return (checkboxOfAgree.isDisplayed() & (!checkboxOfAgree.isSelected()));
    }

    public boolean disabledSubmitButtonIsDisplayed() {
        return (submitButton.isDisplayed() & (!submitButton.isEnabled()));

    }

    public boolean linksAreDisplayed() {
        return ((termsAndConditionsLink.isEnabled()) & (clickHereLink.isEnabled()) & (privacyPolicyLink.isEnabled()));
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterMobileNo(String mobileNo) {

        for (char digit : mobileNo.toCharArray()) {
            mobileNoInput.sendKeys(Character.toString(digit));
             // small delay to mimic human typing
        }
        waitForSecs(3);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void signUpSuccessMsgIsDisplayed() {
        waitForPageToLoad();
        waitForElementVisible(signupSuccessfulMsg);
        signupSuccessfulMsg.isDisplayed();

    }

    public void errorMsgForInvalidEmailIsDisplayed() {
        invalidEmailErrorMsg.isDisplayed();
    }

    public void errorMsgForInvalidMobileIsDisplayed() {
        invalidMobileErrorMsg.isDisplayed();
    }

    public void checkBoxIsChecked() {
         jsClick(checkboxOfAgree);
    }

}
