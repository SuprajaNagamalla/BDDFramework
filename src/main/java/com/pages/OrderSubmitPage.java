package com.pages;

import com.qa.util.LoggerHelper;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.factory.DriverFactory.getDriver;

public class OrderSubmitPage {
    private WebDriver driver;

    BasePage basePage = new BasePage(getDriver());
    private static final Logger Log = LoggerHelper.getLogger();

    // Constructor to initialize elements using Page Factory
    public OrderSubmitPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[contains(@class,'chakra-heading') and text()='Thank you for your order!']")
    private WebElement orderConfirmMessage;

    @FindBy(xpath = "//*[text()=\"Here's your reservation number: \"]/span")
    private WebElement orderReserveNumber;

    @FindBy(xpath = "//*[text()='Unfortunately, we were unable to approve your request at this time.']")
    private WebElement deniedMessage;

    @FindBy(xpath = "//*[contains(@class,'chakra-button') and text()='View']//preceding-sibling::p")
    private WebElement remainingCartMessage;

    @FindBy(xpath = "//*[contains(@class,'chakra-button') and text()='View']")
    private WebElement cartViewButton;

    public boolean isOrderConfirmMessageIsDisplayed() {
        basePage.waitForElementVisible(orderConfirmMessage);
        return orderConfirmMessage.isDisplayed();
    }

    public boolean isOrderReserveNumberIsDisplayed() {
        basePage.waitForElementVisible(orderReserveNumber);
        return orderReserveNumber.isDisplayed();
    }

    public boolean isDeniedMessageIsDisplayed() {
        basePage.waitForElementVisible(deniedMessage);
        return deniedMessage.isDisplayed();
    }

    public boolean isOrderConfirmMessageTextDisplayed(String actText) {
        return basePage.isTextDisplayed(actText, driver);
    }

    public void clickOnCartViewButton(){
        basePage.waitForElementToBeClickable(cartViewButton);
        cartViewButton.click();
    }

    public String verifyRemainingCartMessage() {
        basePage.waitForElementVisible(remainingCartMessage);
        return remainingCartMessage.getText();
    }
}
