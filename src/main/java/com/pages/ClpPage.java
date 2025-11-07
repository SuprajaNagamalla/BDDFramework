package com.pages;

import static com.qa.factory.DriverFactory.getDriver;

import java.util.List;
import java.util.Properties;

import io.cucumber.java.en.And;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import com.qa.util.ElementActions;
import com.qa.util.LoggerHelper;
import com.qa.util.ScenarioContext;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClpPage extends ElementActions {
    public static WebDriver driver;
    ScenarioContext scenarioContext = new ScenarioContext(); // Shared context
    BasePage basePage = new BasePage(getDriver());
    SignInPage signInPage = new SignInPage(getDriver());
    private static final Logger Log = LoggerHelper.getLogger();
    // private static final Logger Log = (Logger) LoggerHelper.getLogger();

    Properties prop = null;

    // Constructor to initialize elements using Page Factory
    public ClpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Xpaths

    @FindBy(xpath = "//div[@class='css-1lpwp7v']")
    private WebElement ShopByBrandSection;

    @FindBy(xpath = "//img[@alt='Image 3']")
    private WebElement BrandLogo;

    @FindBy(xpath = "//h1[@class='chakra-text css-1k3cauw']")
    private WebElement categoryTitle;

    @FindBy(xpath = "//p[contains(text(),'From a comfortable new sofa to a lavish bedroom su')]")
    private WebElement CategoryDescription;

    @FindBy(xpath = "//div[@class='react-target']//div[3]//div[1]//div[1]//div[1]//figure[1]//div[1]//a[1]//img[1]")
    private WebElement CategorySpecificPLPBanner;

    @FindBy(xpath = "//h2[text()='Filters']")
    private WebElement CategoryName;

    @FindBy(xpath = "//div[@class='header-middle-content css-1c8ux76']")
    private WebElement somewhereinapplication;

    @FindBy(xpath = "//span[text()='Read More']")
    private static WebElement ReadMore;

    @FindBy(xpath = "//p[text()='Read Less']")
    private static WebElement ReadLess;

    @FindBy(xpath = "//div[@id='04ddea8a8d3663762cc27b9b26']//img[@alt='Desktop Banner']")
    private static WebElement DesktopBanner;

    private WebElement getCategory(String category) {
        return driver.findElement(By.xpath("//nav[@id='list-menu']//a[contains(@name,'" + category + "')]"));
    }

    private WebElement getl2category(String l2category) {
        return driver.findElement(By.xpath("//h4[text()='" + l2category + "']"));
    }

    private WebElement getl3category(String l3category) {
        return driver.findElement(By.xpath("//p[text()='" + l3category + "']"));
    }

    //Methods

    public boolean IsShopByBrandSectionDisplayed() {
        waitForSecs(2);
        basePage.scrollToElement(ShopByBrandSection);
        basePage.waitForElementVisible(ShopByBrandSection);
        return ShopByBrandSection.isDisplayed();
    }

    public void ClickOnBrandLogo() {
        waitForSecs(2);
        basePage.scrollToElement(ShopByBrandSection);
        basePage.waitForElementToBeClickable(BrandLogo);
        basePage.click(BrandLogo);
        waitForSecs(5);
    }

    public void GetCategory(String category) throws InterruptedException {
        Actions actions = new Actions(ClpPage.driver);
        actions.moveToElement(getCategory(category)).build().perform();
        WebElement Category = getCategory(category);
        basePage.waitForElementToBeClickable(Category);
        Category.click();
        Log.info("Clicked Category: " + category);
    }

    public boolean IsCategorySpecificPLPBannerIsDisplayed() {
        basePage.waitForElementVisible(CategorySpecificPLPBanner);
        return CategorySpecificPLPBanner.isDisplayed();

    }

    public boolean IsCategoryDescriptionDisplayed() {
        basePage.waitForElementVisible(CategoryDescription);
        return CategoryDescription.isDisplayed();
    }

    public List<WebElement> getSubCategory() {
        return driver.findElements(By.xpath("//h4[@class='chakra-heading css-1bgfzxz']"));
    }

    public List<WebElement> getCategoryLabels() {
        waitForSecs(2);
        return driver.findElements(By.xpath("//p[@class='chakra-text css-jyf5r0']"));
    }

    public List<WebElement> getComponents() {
        return driver.findElements(By.xpath("//div[@class='css-10jo3dw']"));
    }

    public boolean GetL2Category(String l2) {
        WebElement l2category = getl2category(l2);
        basePage.waitForElementVisible(l2category);
        Log.info("l2 category is displayed: " + l2);
        return true;
    }

    public boolean GetL3Category(String l3) {
        WebElement l3category = getl3category(l3);
        basePage.waitForElementVisible(l3category);
        Log.info("l3 category is displayed: " + l3);
        return true;
    }

    public boolean IsUserRedirectedToThePLP() {
        basePage.waitForElementVisible(CategoryName);
        return CategoryName.isDisplayed();
    }

    public void clickOnReadMoreLink() {
        basePage.scrollToElement(ReadMore);
        basePage.waitForElementToBeClickable(ReadMore);
        ReadMore.click();
        Log.info("Clicked read more button");
    }

    public void clickOnReadLessLink() {
        basePage.waitForElementToBeClickable(ReadLess);
        ReadLess.click();
        Log.info("Clicked ReadLess button");
    }

    public boolean IsUserRedirectedToDesktopBanner() {
        basePage.waitForElementVisible(DesktopBanner);
        return DesktopBanner.isDisplayed();
    }
}