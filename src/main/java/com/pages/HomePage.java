package com.pages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.qa.factory.DriverFactory;
import com.qa.util.*;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DecimalFormat;
import java.time.Duration;
import java.util.*;
import java.util.Properties;
import static com.qa.factory.DriverFactory.getDriver;
import static com.qa.util.ElementActions.waitForSecs;

public class HomePage {

	private WebDriver driver;
	ScenarioContext scenarioContext = new ScenarioContext(); // Shared context
	Actions actions = new Actions(DriverFactory.getDriver());

	BasePage basePage = new BasePage(getDriver());
	private static final Logger Log = LoggerHelper.getLogger();
	RandomDataGenerator randomData = new RandomDataGenerator();
	Properties prop = null;

	// Constructor to initialize elements using Page Factory
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//By locators
	@FindBy(xpath = "")
	private WebElement homeElement;

	//@FindBy(xpath = "//input[@name='postalCode']")
	@FindBy(name = "postalCode")
	private WebElement zipCodeInput;

	@FindBy(xpath = "//button[contains(normalize-space(), 'Use My Location')]//span[contains(@class, 'chakra-button__icon')]//*[name()='svg']")
	private WebElement useMyLocationButton;


	@FindBy(xpath = "(//p[contains(@class, 'chakra-text') and normalize-space()='Info'])[1]")
	private WebElement iInfoIcon;

	private WebElement getCategoryPageHeaderText(String pageHeader) {
        return driver.findElement(By.xpath("//div[contains(@class,'chakra-container')]//*[text()='" + pageHeader + "']"));
    }

	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement closeBtnStoreInfoDlg;

	@FindBy(xpath = "(//h2[normalize-space()='Store Info'])[1]")
	private WebElement headerBtnStoreInfoDlg;

	@FindBy(xpath = "//p[contains(@class, 'chakra-text') and contains(@class, 'address')]")
	private WebElement addressStoreInfoDlg;

	@FindBy(xpath = "//p[contains(@class, 'chakra-text') and contains(@class, 'distance')]")
	private WebElement distanceStoreInfoDlg;

	@FindBy(xpath = "(//*[contains(@class, 'chakra-icon')]/following-sibling::*)[3]")
	private WebElement phoneNumberStoreInfoDlg;

	@FindBy(xpath = "(//*[normalize-space()='Store Hours'])[1]")
	private WebElement storeHoursStoreInfoDlg;

	@FindBy(xpath = "(//a[normalize-space()='Get Directions'])[1]")
	private WebElement getDirectionsLinkStoreInfoDlg;

	@FindBy(xpath = "//button[normalize-space()='Back']")
	private WebElement backButtonStoreInfoDlg;


	@FindBy(xpath = "//input[contains(@aria-label, 'Destination')]")
	private WebElement destinationInputGoogleMaps;

	//***** Hybris**************************
	@FindBy(xpath = "(//*[@id='findYourStoreDesktop']/span)[2]")
	private WebElement findYourStoreLink;

	@FindBy(xpath = "//input[@id='enterZipCode_Nav']")
	private WebElement zipCode;

	//@FindBy(xpath = "//button[@id='enterZipCodeGoButton_Nav']")
	@FindBy(xpath = "//button[text()='GO'] | //button[text()='Go'] | //button[text()='go']")
	private WebElement goButton;

	//@FindBy(xpath = "//h2[text()='Store Locator']")
	@FindBy(xpath = "//h2[normalize-space(text())='Store Locator']")
	private WebElement storeLocatorDlg;

	@FindBy(xpath = "//*[normalize-space(text())='Store locator']")
	private WebElement storeLocatorDlg_Remove;


	//@FindBy(xpath = "//button[contains(@class, 'chakra-button location-link')]")
	@FindBy(id = "popover-trigger-:r6:")
	private WebElement findYourStoreBtn;

	@FindBy(xpath = "//p[normalize-space()='Find Your Store']")
	private WebElement findYourStoreText;

	@FindBy(xpath = "(//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'make this my store')and contains(@class,'chakra-button')])[1]")
	private WebElement makeThisMyStoreButton;

	@FindBy(xpath = "//button[normalize-space(text())='MAKE THIS MY STORE' and contains(@class,'chakra-button')] | //button[normalize-space(text())='Make This My Store' and contains(@class,'chakra-button')]")
	//private WebElement makeThisMyStoreButtons;
	private List<WebElement> makeThisMyStoreButtons;

	@FindBy(xpath = "//a[@aria-label='Start Your Order']")
	private WebElement startYourOrderLink;

	@FindBy(xpath = "//button[@id='acceptAllCookieButton']")
	private WebElement acceptAllCookiesBtn;

	@FindBy(xpath = "//button[@aria-label='Close']")
	private WebElement closeXStoreLocator;

	@FindBy(xpath = "//div[contains(text(), 'Sorry, there are no locations in this area')]")
	private WebElement invalidErrorMsgZipCode;

	@FindBy(xpath = "//p[contains(text(), 'Change')]")
	private WebElement changeStoreLocator;

	@FindBy(xpath = "//strong[contains(normalize-space(), 'Get started for just $10')]")
	private WebElement getStartedSection;

	@FindBy(xpath = "//button[@class='chakra-button css-bdijpt'][normalize-space()='Email']")
	private WebElement emailBtnGetStartedSection;

	@FindBy(xpath = "(//button[contains(@class, 'chakra-button')][normalize-space()='Email'])[1]")
	private WebElement emailCTAButton;

	@FindBy(xpath = "(//button[contains(@class, 'chakra-button')][normalize-space()='Email'])[2]")
	private WebElement emailFooterCTAButton;

	@FindBy(xpath = "(//button[contains(@class, 'chakra-button')][normalize-space()='Text'])[1]")
	private WebElement textCTAButton;

	@FindBy(xpath = "(//button[contains(@class, 'chakra-button')][normalize-space()='Text'])[2]")
	private WebElement textFooterCTAButton;

	@FindBy(xpath = "//input[@name='email']")
	private WebElement emailGetStartedInput;

	@FindBy(xpath = "//input[@name='phone']")
	private WebElement textGetStartedInput;


	@FindBy(xpath = "//button[normalize-space()='Sign Up']")
	private WebElement signUpCTAButton;

	@FindBy(xpath = "//p[normalize-space()=concat(\"You're in! Keep an eye out for great deals headed your way!\")]")
	private WebElement thankYouMsgCTA;

	//@FindBy(xpath = "//*[@id='accordion-button-:r1v:']")
	@FindBy(xpath = "//button[.//div[text()='Info & Tools']]")
	private WebElement accordionInfoAndToolsButton;

	//@FindBy(xpath = "//*[@id='accordion-button-:r1t:']")
	@FindBy(xpath = "//button[.//div[text()='About Rent-A-Center']]")
	private WebElement accordionAboutRentACenterButton;

	@FindBy(xpath = "(//*[contains(@id, 'accordion-panel-')]//p//a[normalize-space(text())='FAQs'])[2]")
	private WebElement accordianInfoAndToolsFaqsLink;

	@FindBy(xpath = "//*[@id='accordion-button-:r1a:']")
	private WebElement accordionMyAccountOnlineBillPayButton;

	@FindBy(xpath = "//p[contains(text(), 'Store changes may affect your current pricing, promotion, and inventory availability.')]")
	private WebElement errorMsgChangeStorePopUp;

	@FindBy(xpath = "//*[contains(@id, \"chakra-modal--body\")]/div/div[2]/div/div[1]/p[2][contains(text(), \"Rent-A-Center doesn't have store locations in Minnesota, but our affiliate company, Home Choice, does.\")]")
	private WebElement errorMsgChangeMinnesotaStorePopUp;


	@FindBy(xpath = "//*[contains(@id, \"chakra-modal--body\")]//p[contains(text(), \"Rent-A-Center doesn't have store locations in Wisconsin, but our affiliate company, Get It Now, does\")]")
	private WebElement errorMsgChangeWisconsinStorePopUp;
	@FindBy(xpath = "//*[@id='footer']//*[@id='accordion-button-:r1f:']")
	private WebElement DealsAndClearance;

	@FindBy(css = "button[aria-label='Logo']")
	private WebElement Raclogo;

	@FindBy(xpath = "//*[@id='footer']//a[text()='Deals']")
	private WebElement Dealsfooter;

	@FindBy(xpath = "//*[@id='footer']//a[text()='Clearance']")
	private WebElement Clearancefooter;

	@FindBy(xpath = "//h2[text()='Shop By Category']")
	private WebElement ShopByCategory;
	@FindBy(xpath = "//h1[contains(text(),'My Items')]")
	private WebElement SuperPLP;
	@FindBy(xpath = "//footer[contains(@class,'footer-wrapper')]")
	private WebElement HomepageFooter;
	@FindBy(className = ".social-links-holder")
	private WebElement HomepageSocialmediaLinks;

	@FindBy(xpath = "//img[@alt='Mobile Banner']")
	private WebElement BannerImage;

	@FindBy(xpath = "//*[contains(@class,'shopByCategoryLayout')]")
	private WebElement ShopByCategoryComponent;

	@FindBy(xpath = "//p[contains(text(),'Get started for just $10')]")
	private WebElement GetStartedComponent;

	@FindBy(xpath = "//p[contains(text(),' When you sign up for notifications now and never miss an offer or discount! ')]")
	private WebElement GetStartedTextComponent;

	@FindBy(xpath = "//*[contains(@class,'howRacWorks-wrapper')]")
	private WebElement HowRacWorksComponent;
	@FindBy(xpath = "//p[text()='Pricing for']//following::p")
	private WebElement SelectedZipcode;

	@FindBy(xpath = "//*[contains(@class,'shopByCategory-wrapper')]//p")
	private List<WebElement> categories_shopByCategory;

	@FindBy(xpath = "//a[@id='cookieSettingButton']")
	private WebElement cookieSettingButton;
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement cookieSaveButton;
	@FindBy(xpath = "//button[text()='Apply for Instant Approval']")
	private WebElement instantApprovalButton;

	@FindBy(xpath = "//*[@aria-label='brand-logo']")
	private WebElement racBrandLogo;

	@FindBy(xpath = "//div[@data-testid='carousel']")
	private WebElement featureBannerCarousel;

	@FindBy(xpath = "(//button[contains(@class,'chakra-button links')])[1]/p")
	private WebElement userFirstNameHeader;

	@FindBy(xpath = "(//select[@id='page_sort']//option)[1]")
	private WebElement sortByAvailability;

	@FindBy(xpath = "//h2[text()='Shop By Category']//following-sibling::a[text()='Shop All']")
	private WebElement shopAllButton;

	// Locating the Accept button using its ID
	@FindBy(id = "acceptAllCookieButton")
	private WebElement acceptCookiesButton;

	@FindBy(id = "search-input")
	private WebElement searchInput;

	@FindBy(xpath = "//*[text()='Frequently Searched']")
	private WebElement frequentlySearchedTxt;

	@FindBy(xpath = "(//div[@data-testid='sf-suggestion'])[2]//div[contains(@class,'suggestion-list-item')]")
	private List<WebElement> searchSuggestionList;

	@FindBy(xpath = "(//div[contains(@class,'shopByCategory')])[2]//a[contains(@class,'chakra-link category-link ')]")
	private List<WebElement> shopByCategoryList;

	//@FindBy(xpath = "((//div[@data-testid='sf-suggestion'])[1]//a)[4]")
	@FindBy(xpath = "(//div[@data-testid='sf-suggestion'])[1]//button//p[normalize-space()='electronics']")
	private WebElement searchSuggestionLink;


	@FindBy(xpath = "//div[contains(@class, 'chakra-accordion__item')]/button[@type='button']")
	private WebElement rentalPaymentsOptionsButton;

	@FindBy(xpath = "(//div[contains(@class,'image-tile-figure text-center')])[7]")
	private WebElement ProductItem;

	@FindBy(xpath = "//h2[text()='Popular Items']")
	private WebElement popularitems;

	@FindBy(xpath = "//div[@data-testid='product-info-container']")
	private WebElement productinfo;

	@FindBy(xpath = "//h1[text()='Featured Deals']")
	private WebElement featureddeals;

	@FindBy(xpath = "(//div[@class='slick-slide slick-active slick-current']//div//p[contains(@class,'chakra-text')])[8]")
	private WebElement pricing;

	@FindBy(xpath = "(//div[contains(@class,'category-text')])[12]")
	private WebElement productdescription;

	@FindBy(xpath = "(//*[@aria-label='Next Slide'])[2]")
	private WebElement rightarrow;

	@FindBy(xpath = "(//div[contains(@class,'featuredDealsSlider')]//div[@class='slick-slider slick-initialized']//*[name()='svg'])[1]")
	private WebElement leftarrow;

	@FindBy(xpath = "(//div[@class='css-eqwipv'])[3]")
	private WebElement item;

	@FindBy(xpath = "//div[@id='c1fc2948bb515f422c7a71bee5']//button[@aria-label='Scroll carousel right']//*[name()='svg']")
	private WebElement rightarrowbutton;

	@FindBy(xpath = "//div[@id='c1fc2948bb515f422c7a71bee5']//button[@aria-label='Scroll carousel left']//*[name()='svg']")
	private WebElement lefttarrowbutton;

	@FindBy(xpath = "//div[5]//div[1]//div[1]//div[1]//figure[1]//div[1]//a[1]//img[1]")
	private WebElement banner;

	@FindBy(xpath = "//button[@aria-label='Go to slide 3']")
	private WebElement navigationdot;

	@FindBy(xpath = "//div[4]//div[1]//div[1]//div[1]//figure[1]//div[1]//a[1]//img[1]")
	private WebElement getbanner;

	@FindBy(xpath = "//h1[text()='Appliances']")
	private WebElement appliances;

	@FindBy(xpath = "(//a[normalize-space()='Store Locator'])[1]")
	private WebElement storelocatorbutton;

	@FindBy(xpath = "(//button[normalize-space(text())='Make This My Store' and contains(@class,'chakra-button')])[1]")
	private WebElement makethismystorebutton;

	@FindBy(xpath = "//*[contains(@id,store-locator-form)]/div/div[contains(text(), \"Sorry, there are no locations in this area\")]")
	private WebElement invalidstorelocatorerrorMsg;

	@FindBy(xpath = "(//div[contains(@class,'chakra-accordion my-offers')]//button)[1]")
	private WebElement myOffersButton;

	@FindBy(xpath = "//div[contains(@class,'StoreLocatorContent')]")
	private WebElement storeLocatorPopupMessage;

	@FindBy(xpath = "//a[contains(text(), \"I don't have a mobile\")]")
	private WebElement doNotHaveMobNumLink;

	//Dynamic xpath declaration
	private WebElement getAccordionSectionLink(String sectionName) {
		//return driver.findElement(By.xpath("//div[@id='accordion-panel-:r1i:']//p//a[normalize-space(text())='" + sectionName + "']"));
		return driver.findElement(By.xpath("//*[contains(@id, 'accordion-panel-')]//p//a[normalize-space(text())='" + sectionName + "']"));
	}

	private WebElement getSectionLink(String sectionName)
	{return driver.findElement(By.xpath("//*[contains(@class,'shopByCategory-wrapper')]//p[text()='" + sectionName+ "']"));}

	private WebElement getFooterAccordionSectionLink(String sectionName) {
		return driver.findElement(By.xpath("//div[contains(@id, 'accordion-panel-')]//p//a[normalize-space(text())='" + sectionName + "']"));
	}

	private WebElement getFooterAccordionButton(String buttonName) {
		//return driver.findElement(By.xpath("//*[contains(@id, 'accordion-button-')]//div[normalize-space(text())='" + buttonName + "']"));
		return driver.findElement(By.xpath("//div[text()='" + buttonName + "']/ancestor::button"));
		//div[text()='My Account - Online Bill Pay']/ancestor::button
	}

	private WebElement getSocialMediaLink(String linkName)
	{return driver.findElement(By.xpath("//*[@id='footer']//a[contains(@href,'" + linkName+ "')]"));}

	private WebElement getSearchSuggestions(String suggestion) {
		return driver.findElement(By.xpath("(//div[@data-testid='sf-suggestion'])[1]//p[normalize-space()='"+suggestion+"']"));
	}
	private WebElement getL1CategoryLink(String L1category) {
		return driver.findElement(By.xpath("//nav[@id='list-menu']//a[contains(@name,'" + L1category + "')]"));
	}
	private WebElement getL2SubCategoriesLink(String L2Category) {
		return driver.findElement(By.xpath("//section[@data-testid='popover-menu']//h5[text()='" + L2Category + "']//parent::a"));
	}
	private WebElement getSpecialCategoriesLink(String specialCategory) {
		return driver.findElement(By.xpath("//section[@data-testid='popover-menu']//h5[text()='" + specialCategory + "']//parent::a"));
	}

	private WebElement getL3CategoriesLink(String L2Category, String L3Category) {
		return driver.findElement(By.xpath("(//h5[text()='"+L2Category+"']//following::ul)[1]//li/a[text()='"+L3Category+"']"));

	}

	private WebElement getShopByCategoriesSectionAllCategoriesLink(String categoryName) {
		return driver.findElement(By.xpath("//p[text()='"+categoryName+"']//ancestor::a[contains(@class,'chakra-link category')]"));
	}

	private WebElement getSectionOnHomePageText(String section) {
		return driver.findElement(By.xpath("//h2[text()='" + section + "']"));
	}

	private WebElement getItemCountInCart(String numberOfItems) {
		return driver.findElement(By.xpath("//*[contains(@class,'cart-link')]//*[contains(@class,'chakra-badge') and text()='" + numberOfItems + "']"));
	}
	private WebElement getAccordion(String accordionName) {
		return driver.findElement(By.xpath("//div[text()='" + accordionName +"']//parent::button"));
	}

	private WebElement getOfferCode(String offerCode) {
		return driver.findElement(By.xpath("(//p[text()='"+offerCode+"']//following::button[text()='Apply'])[1]"));
	}
	//************ Locators declaration End above

	public boolean isHomeScreenDisplayed() {

		try {
			if (homeElement.isDisplayed()) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}

	public void scrollToSocialMediaLinksFooter() {
		basePage.scrollToElement(HomepageSocialmediaLinks);
	}


	public void clickFindYourStoreLink() {
		findYourStoreLink.click();
	}

	public void enterZipCode(String zipCodeVal) {
		//zipCode.sendKeys(zipCodeVal);
		waitForSecs(1);
		basePage.waitForElementVisible(zipCodeInput);
		// Click the input field to ensure it's active
		zipCodeInput.click();
		waitForSecs(1);
		/*zipCodeInput.clear();
		waitForSecs(1);*/
		zipCodeInput.sendKeys(Keys.CONTROL + "a"); // Select all text (Ctrl + A)
		zipCodeInput.sendKeys(Keys.BACK_SPACE); // Delete selected text
		waitForSecs(1);
		zipCodeInput.sendKeys(zipCodeVal);
		waitForSecs(2);
	}

	public void enterZipCodePerf(String zipCodeVal) {
		basePage.waitForElementVisible(zipCodeInput);
		// Click the input field to ensure it's active
		zipCodeInput.click();
		zipCodeInput.sendKeys(Keys.CONTROL + "a"); // Select all text (Ctrl + A)
		zipCodeInput.sendKeys(Keys.BACK_SPACE); // Delete selected text
		zipCodeInput.sendKeys(zipCodeVal);
	}


	public void clickOnGOButton() {
		//goButton.click();
		basePage.jsClick(goButton);
		waitForSecs(5);
	}

	public void clickOnGOButtonPerf() {
		//goButton.click();
		basePage.jsClick(goButton);
	}

	public boolean verifyStoreLocatorDlgDisplay() {
		//basePage.waitForElementToBeClickable(makeThisMyStoreButton);
		basePage.waitForElementVisible(storeLocatorDlg);
		return storeLocatorDlg.isDisplayed();
	}

	public boolean verifyMakeThisMyStoreButtonDisplay() {
		basePage.waitForElementToBeClickable(makeThisMyStoreButton);
		return makeThisMyStoreButton.isDisplayed();

	}

	public void clickOnMakeThisMyStoreLink() {
		basePage.scrollToElement(makeThisMyStoreButton);
		basePage.waitForElementToBeClickable(makeThisMyStoreButton);
		//basePage.click(makeThisMyStoreButton);
		basePage.jsClick(makeThisMyStoreButton);
		waitForSecs(5);
//		basePage.refreshPage();
		Log.info("Clicked on Make this My store Button");
	}

	public void clickOnMakeThisMyStoreLinkPerf() {

		DecimalFormat df = new DecimalFormat("#.###");

		//Make this my store results time
		long startTime = System.currentTimeMillis();
		Log.info("Start Time: " + startTime+ " milli Secs");
		Log.info("Click on GO button");
		clickOnGOButtonPerf();
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Click on GO button");
		basePage.waitForElementToBeClickable(makeThisMyStoreButton);
		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		double loadTimeSecs = loadTime/1000.0;
		Log.info("Make this my store results Load Time Secs: " + df.format(loadTimeSecs) + " Secs");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Make this my store results Load Time Secs: " + df.format(loadTimeSecs) + " Secs");

		//Store display on header - time
		startTime = System.currentTimeMillis();
		Log.info("Start Time : " + startTime+ " milli Secs");
		Log.info("Click on Make this my store button");
		basePage.jsClick(makeThisMyStoreButton);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Click on Make this my store button");
		isPricingForTextDisplayed("Pricing for");
		endTime = System.currentTimeMillis();
		loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		loadTimeSecs = loadTime/1000.0;
		Log.info("Make this my store to home page header Load Time Secs: " + df.format(loadTimeSecs) + " Secs");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Make this my store to home page header Load Time Secs: " + df.format(loadTimeSecs) + " Secs");

	}


	public void clickOnStartYourOrderLink() {
		basePage.click(startYourOrderLink);
		Log.info("Clicked on start your order link");
	}

	public void closeAllCookiesButton() {

		/*
		waitForSecs(3);
		basePage.waitForPageToLoad();
		//acceptAllCookiesBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", acceptAllCookiesBtn);
		waitForSecs(1);
		Log.info("Clicked on all cookies button");
		*/
		try {
			waitForSecs(3);
			List<WebElement> acceptAllCookiesBtnList = driver.findElements(By.id("acceptAllCookieButton"));
			if (!acceptAllCookiesBtnList.isEmpty()) {
				WebElement acceptAllCookiesBtn = acceptAllCookiesBtnList.get(0);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", acceptAllCookiesBtn);
				Log.info("Clicked on all cookies button");
			} else {
				Log.info("Accept cookies button is not displayed");
			}
		} catch (Exception e) {
			// Exception ignored intentionally to avoid test failure
		}
	}

	public boolean verifyStoreLocatorOptionDisplay() {
		return findYourStoreBtn.isDisplayed();

	}

	public boolean verifyFindYourStoreTextDisplay() {
		return findYourStoreText.isDisplayed();
	}

	public void clickOnFindYourStoreButton() {
		basePage.waitForElementToBeClickable(findYourStoreBtn);
		findYourStoreBtn.click();
	}

	public void clickOnFindYourStoreButtonPerf() {
		basePage.waitForElementToBeClickable(findYourStoreBtn);


		findYourStoreBtn.click();
	}

	public boolean verifyStoreLocatorOptionsDisplay() {

		basePage.waitForElementVisible(zipCodeInput);
		boolean isZipCodeVisible = zipCodeInput.isDisplayed();
		boolean isMyLocationVisible = useMyLocationButton.isDisplayed();

		return (isZipCodeVisible && isMyLocationVisible);

	}

	public void clickIInfoIcon() {
		basePage.waitForElementToBeClickable(iInfoIcon);
		iInfoIcon.click();
	}

	public boolean verifyStoreInfoPopUpDetails() {

		try {
			basePage.waitForElementToBeClickable(closeBtnStoreInfoDlg);
			headerBtnStoreInfoDlg.isDisplayed();

			//Address
			String address = addressStoreInfoDlg.getText().trim();
			Log.info("Address: " + address);

			//Distance
			String distance = distanceStoreInfoDlg.getText().trim();
			Log.info("distance: " + distance);

			//Phone number
			String phoneNumber = phoneNumberStoreInfoDlg.getText().trim();
			Log.info("phone Number: " + phoneNumber);

			//Store Hours
			String storeHours = storeHoursStoreInfoDlg.getText().trim();
			Log.info("store hours: " + storeHours);

			if (!address.isEmpty() && !distance.isEmpty() && !phoneNumber.isEmpty() &&
					!storeHours.isEmpty() && getDirectionsLinkStoreInfoDlg.isDisplayed()) {
				return true;
			}

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void clickOnGetDirectionsLink() {
		String storeAddress=addressStoreInfoDlg.getText().trim();
		scenarioContext.setContext("STORE_ADDRESS", storeAddress);
		getDirectionsLinkStoreInfoDlg.click();
	}

	public void clickBackButtonOnStoreInfoPopUp() {
		backButtonStoreInfoDlg.click();
	}

	public boolean verifyRedirectedToGoogleMapsWithStoreLocation() {

		String originalWindow = driver.getWindowHandle();
		boolean isGoogleMapVisible;

		//Switch to the new tab
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(originalWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}
		// Verify redirected URL contains Google Maps and store location
		String expectedURL = "https://www.google.com/maps";
		String currentURL = driver.getCurrentUrl();
		String actStoreAddress = (String) scenarioContext.getContext("STORE_ADDRESS");
		String expStoreAddress=destinationInputGoogleMaps.getDomProperty("value");
		Log.info("Store Address: "+expStoreAddress);
        assert currentURL != null;
        //if (currentURL.contains(expectedURL) && actStoreAddress.equalsIgnoreCase(expStoreAddress)) {
		if (currentURL.contains(expectedURL) ) {
			isGoogleMapVisible=true;
		} else {
			isGoogleMapVisible=false;
		}
		// Close the new tab
		driver.close();
		// Switch back to the original window
		driver.switchTo().window(originalWindow);
		return isGoogleMapVisible;
	}


	public void clickXButtonStoreLocatorPopUp() {
		basePage.click(closeXStoreLocator);
		waitForSecs(2);
	}



	public boolean verifyStoreInfoPopUpClosed() {
		waitForSecs(2);
		return basePage.isElementNotDisplayed(closeXStoreLocator);
	}

	public boolean isZipCodeErrorMessageDisplay(String errMsg) {
		basePage.waitForElementVisible(invalidErrorMsgZipCode);
		return basePage.isErrorMessageDisplayed(invalidErrorMsgZipCode,errMsg);
	}

	public boolean isStoreInfoForTheRespectiveStateDisplayed(String expState) {
		//boolean isStateDisplayed = true; // Assume all addresses contain the state
		basePage.waitForElementToBeClickable(makeThisMyStoreButton);
		int countMyStoreButtons = makeThisMyStoreButtons.size();
		//int countMyStoreButtons=driver.findElements(By.xpath("//button[normalize-space(text())" +
		//	"='MAKE THIS MY STORE' and contains(@class,'chakra-button')]")).size();

		//for (int i = 1; i <= countMyStoreButtons; i++) {
		Log.info("Count My Store Buttons:"+countMyStoreButtons);
		if(countMyStoreButtons>5){
			countMyStoreButtons=5;
		}
		for (int i = 1; i <= countMyStoreButtons; i++) {
			// XPath uses 1-based index
			//String actAddress = driver.findElement(By.xpath("//*[@id='chakra-modal--body-:r3:']/div/div[4]/div['"+i+"']/div[2]/div")).getText().trim();
			//String actAddress = driver.findElement(By.xpath("//*[@id='chakra-modal--body-:r3:']/div/div[4]/div[1]/div["+i+"]/div/text()[5][normalize-space()='" + expState + "']")).getText().trim();
			//String actAddress = driver.findElement(By.xpath("//button[text()='Make This My Store']/ancestor::div[@class='css-0']/preceding-sibling::div["+i+"]")).getText().trim();
			String actAddress = driver.findElement(By.xpath("(//button[text()='Make This My Store']/ancestor::div[@class='css-0']/preceding-sibling::div[1])["+i+"]")).getText().trim();


			//String actAddress = driver.findElement(By.xpath("(//button[normalize-space(text())='Make This My Store']/preceding-sibling::*)["+i+"]")).getText().trim();



			Log.info("Act Address:"+actAddress);
			if (!actAddress.contains(expState)) {
				return false; // No need to continue if one fails
			}
		}
		return true;
	}

	public boolean isPricingForTextDisplayed(String actText) {
		return basePage.isTextDisplayed(actText);
	}

	public void clickOnChangeOption() {
		basePage.waitForElementVisible(changeStoreLocator);
		changeStoreLocator.click();
	}

	public void scrollToGetStartedSection(String sectionName) {
		basePage.scrollToElement(getStartedSection);
		waitForSecs(2);
	}


	public void scrollToFooterGetStartedSection(String sectionName) {
		basePage.scrollToElement(emailFooterCTAButton);
		waitForSecs(2);
	}


	public boolean isGetStartedSectionIsVisible(String sectionName) {
		return basePage.isElementDisplayed(getStartedSection);
	}
	public boolean isEmailButtonVisibleInGetStartedSection() {
		//return basePage.isElementDisplayed(emailBtnGetStartedSection);
		return basePage.isElementDisplayed(emailCTAButton);

	}

	public void clickOnCTAEmailOption() {
		basePage.click(emailCTAButton);
	}

	public void enterCTAEmail(String email) {
		basePage.enterText(emailGetStartedInput,email);
	}

	public void clickOnCTASignUpButton() {
		basePage.click(signUpCTAButton);
		waitForSecs(4);
	}

	public void isCTASuccessMessageDisplayed(String successMsg) {
		//basePage.waitForElementVisible(thankYouMsgCTA);
		basePage.isTextDisplayed(successMsg,driver);
	}

	public void clickOnCTATextButton() {
		basePage.click(textCTAButton);
	}

	public void enterCTAPhoneNumber(String phoneNumber) {
		basePage.enterText(textGetStartedInput,phoneNumber);
	}

	public void clickOnLink(String linkName) {
		basePage.clickLinkByText(linkName,driver);
		waitForSecs(3);
	}
	@FindBy(xpath = "//form[@id='payment-form']//input[@name='firstName'] | //input[@id='firstName']")
	private WebElement firstNameInput;

	@FindBy(xpath = "//a[contains(text(), \"I don't have a mobile\")] | //a[text()=\"I don't have a mobile number\"]")
	private WebElement iDontHaveMobLink;


	public void clickOnIDontHaveLinkPerf(String linkName) {

		DecimalFormat df = new DecimalFormat("#.###");
		long startTime = System.currentTimeMillis();
		Log.info("Start Time: " + startTime+ " milli Secs");

		Log.info("I don not have mob number link");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"I don not have mob number link");
		basePage.scrollToElement(iDontHaveMobLink);
		basePage.jsClick(iDontHaveMobLink);
		//basePage.clickLinkByText(linkName,driver);
		basePage.waitForPageToLoadPerf();
		basePage.waitForElementVisible(firstNameInput);
		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		double loadTimeSecs = loadTime/1000.0;
		//Log.info("Get Instant Approval - Contact Delivery info page Time Milli Secs: " + loadTime);
		Log.info("Get Instant Approval - Contact Delivery info page load Time Secs: " + df.format(loadTimeSecs) + " Secs");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Get Instant Approval - Contact Delivery info page load Time Secs: " + df.format(loadTimeSecs) + " Secs");


	}

	public void clickOnLinkName(String linkName) {
		basePage.jsClickLinkByName(linkName);
		waitForSecs(3);
	}



	public boolean isLinkRedirectsToTheOtherPage(String expPageUrl) {
		waitForSecs(8);
		String originalWindow = driver.getWindowHandle();
		boolean isLinkRedirected;
		//Switch to the new tab
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(originalWindow)) {
				driver.switchTo().window(window);
				System.out.println("window switched");
				break;
			}
		}
		String currentURL = driver.getCurrentUrl();
		System.out.println("currentURL: "+currentURL);
		assert currentURL != null;
		if (currentURL.contains(expPageUrl) ) {
			isLinkRedirected=true;
		} else {
			isLinkRedirected=false;
		}
		// Close the new tab
		driver.close();
		// Switch back to the original window
		driver.switchTo().window(originalWindow);
		return isLinkRedirected;
	}

	public void clickOnFooterCTAEmailOption() {
		basePage.click(emailFooterCTAButton);
	}

	public void clickOnFooterCTATextButton() {
		basePage.click(textFooterCTAButton);
	}

	public boolean isAccordionButtonCollapsed() {
		return Boolean.parseBoolean(accordionInfoAndToolsButton.getDomAttribute("aria-expanded"));
	}

	public boolean isAccordionButtonCollapsed(String buttonName) {
		return Boolean.parseBoolean(getFooterAccordionButton(buttonName).getDomAttribute("aria-expanded"));
	}

	public boolean isAccordionButtonExpanded() {
		//String val=accordionInfoAndToolsButton.getDomProperty("aria-expanded");
		//String val1=accordionInfoAndToolsButton.getDomAttribute("aria-expanded");
		return Boolean.parseBoolean(accordionInfoAndToolsButton.getDomAttribute("aria-expanded"));
		//return b;
	}

	public boolean isAccordionButtonExpanded(String buttonName) {
		return Boolean.parseBoolean(getFooterAccordionButton(buttonName).getDomAttribute("aria-expanded"));
	}

	public boolean isAccordionSectionLinkDisplayed(String sectionName) {
		//return getAccordionSectionLink(sectionName).isDisplayed();
		return getFooterAccordionSectionLink(sectionName).isDisplayed();
	}

	public boolean isAccordionInfoAndToolsSectionLinkDisplayed(String sectionName) {
		if(sectionName.equalsIgnoreCase("faqs")){
			((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 50);");
			waitForSecs(2);
			basePage.isElementDisplayed(accordianInfoAndToolsFaqsLink);
			return true;
		}else{
			return getAccordionSectionLink(sectionName).isDisplayed();
			//return getFooterAccordionSectionLink(sectionName).isDisplayed();
		}
	}

	public boolean isAccordionInfoAndToolsLinksClickRedirected(String linkName, String expectedURLFragment) {

		//getAccordionSectionLink(linkName).click();
		WebElement wel=getAccordionSectionLink(linkName);
		basePage.jsClick(wel);
		waitForSecs(10);
		new WebDriverWait(driver, Duration.ofSeconds(15))
				.until(ExpectedConditions.urlContains(expectedURLFragment));
		// Verify the URL contains the expected fragment
		String actualURL = driver.getCurrentUrl();
		assert actualURL != null;
		return actualURL.contains(expectedURLFragment);
	}

	public boolean isAccordionLinksClickRedirected(String linkName, String expectedURLFragment) {

		//getAccordionSectionLink(linkName).click();
		if(getFooterAccordionSectionLink(linkName).isDisplayed()){
			getFooterAccordionSectionLink(linkName).click();
		}
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.urlContains(expectedURLFragment));
		// Verify the URL contains the expected fragment
		String actualURL = driver.getCurrentUrl();
        assert actualURL != null;
        return actualURL.contains(expectedURLFragment);
	}

	public void browserBack(){
		driver.navigate().back();
		basePage.waitForPageToLoad();
		waitForSecs(5);
	}


	public boolean isAccordionAboutRentACentreAccordionSectionLinkDisplayed(String sectionName) {
		return getFooterAccordionSectionLink(sectionName).isDisplayed();
	}

	public boolean isLinkRedirectsToTheOtherPageSameBrowserTab(String expPageUrl) {
		waitForSecs(8);
		String currentURL = driver.getCurrentUrl();
		assert currentURL != null;
		//driver.navigate().back();
        return currentURL.contains(expPageUrl);
    }

	public boolean isChangeStoreErrorMsgDisplayedOnPopUp() {
		basePage.scrollToElement(errorMsgChangeStorePopUp);
		basePage.waitForElementVisible(errorMsgChangeStorePopUp);
		return errorMsgChangeStorePopUp.isDisplayed();
	}

	public boolean isMinnesotaStoreErrorMsgDisplayedOnPopUp() {
		basePage.waitForElementVisible(errorMsgChangeMinnesotaStorePopUp);
		return errorMsgChangeMinnesotaStorePopUp.isDisplayed();
	}

	public boolean isWisconsinStoreErrorMsgDisplayedOnPopUp() {
		basePage.waitForElementVisible(errorMsgChangeWisconsinStorePopUp);
		return errorMsgChangeWisconsinStorePopUp.isDisplayed();
	}

	public void clickOnUseMyLocationButton() {
		useMyLocationButton.click();
	}

	public boolean isCategoryPageHeaderDisplayed(String pageHeader) {
        return getCategoryPageHeaderText(pageHeader).isDisplayed();
    }
	
    public void clickAllowButtonMobile() {

//		ConfigReader configReader;
//		configReader=new ConfigReader();
//		try {
//			prop=configReader.init_prop();
//			//String prop1=ConfigReader.getProperty("browser");
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
//		String brow=prop.getProperty("browser");

		String brow=ConfigReader.getProperty("browser");

		System.out.println("Browser Name: "+brow);
		if(brow.contains("android")){
			basePage.clickAllowButtonOnPermission();
		}

    }

	public void searchProduct(String productCode) {
		String modifiedUrl = driver.getCurrentUrl() + "p/"+productCode;
		Log.info("page is navigated to this product url..."+modifiedUrl);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"page is navigated to this product url..."+modifiedUrl);
		driver.get(modifiedUrl);
	}
	public boolean selectedZipcodeIsDisplayed(String zipCode) {
		if(SelectedZipcode.getText().contains(zipCode))
			return true;
		else
			return false;
	}

	public boolean checkCategoryCount(String expCategoryCount) {
		int ExpCount = Integer.parseInt(expCategoryCount);
		if(categories_shopByCategory.size() ==ExpCount)
			return true;
		else
			return false;
	}

	public boolean verifyCategoryNamesDisplayed(String categoryName) {
		int count =0;
		for (WebElement catName:categories_shopByCategory ) {
			if (catName.getText().equalsIgnoreCase(categoryName))
				count++;
		}
		if(count==1)
			return true;
		else
			return false;
	}
	public boolean expectedTextDisplayed(String actText) {
		waitForSecs(2);
		return basePage.isTextDisplayed(actText,driver);
	}

	public void scrollToAccordionAndClick(String accordionName){
		basePage.scrollToElement(getAccordion(accordionName));
		basePage.click(getAccordion(accordionName));
	}
	public void clickOnDealsfooter() {
		Dealsfooter.click();
	}

	public void clickOnClearancefooter() {
		Clearancefooter.click();
	}

	public void clickOnRACLogo() {
		Raclogo.click();
	}

	public void acceptCookies() {
		cookieSettingButton.click();
		waitForSecs(2);
		cookieSaveButton.click();
	}

	public void scrollToShopByCategorySection() {
		basePage.scrollToElement(ShopByCategory);
	}
	public void scrollToFooter() {
		basePage.scrollToElement(HomepageFooter);
	}

	public void verifySuperPlpPageisDisplayed() {
		basePage.isElementDisplayed(SuperPLP);
	}

	public boolean isSectionLinksClickRedirected(String linkName, String expectedURLFragment) {

		basePage.jsClick(getSectionLink(linkName));
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.urlContains(expectedURLFragment));
		// Verify the URL contains the expected fragment
		String actualURL = driver.getCurrentUrl();
		assert actualURL != null;
		return actualURL.contains(expectedURLFragment);
	}
	public boolean allComponentsAreDisplayedOnHomePage() {
		if(
//				BannerImage.isDisplayed() &&
				ShopByCategoryComponent.isDisplayed() &&
				GetStartedComponent.isDisplayed() &&
				GetStartedTextComponent.isDisplayed() &&
				HowRacWorksComponent.isDisplayed())
			return true;
		else
			return false;

	}
	public boolean isMediaLinksClickRedirected(String linkName, String expectedURLFragment) {
		basePage.scrollToElement(getSocialMediaLink(linkName));
		getSocialMediaLink(linkName).click();
		new WebDriverWait(driver, Duration.ofSeconds(5))
				.until(ExpectedConditions.urlContains(expectedURLFragment));
		// Verify the URL contains the expected fragment
		String actualURL = driver.getCurrentUrl();
		assert actualURL != null;
		return actualURL.contains(expectedURLFragment);
	}

	public void applyInstantApproval() {
		basePage.scrollToElement(instantApprovalButton);
		instantApprovalButton.click();
	}

	public void applyInstantApprovalPerf() {
		basePage.scrollToElement(instantApprovalButton);

		DecimalFormat df = new DecimalFormat("#.###");
		long startTime = System.currentTimeMillis();
		Log.info("Start Time: " + startTime+ " milli Secs");

		instantApprovalButton.click();
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Click Instant approval button");

		basePage.waitForPageToLoadPerf();
		basePage.waitForElementToBeClickable(doNotHaveMobNumLink);

		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		double loadTimeSecs = loadTime/1000.0;
		Log.info("Instant Approval page Time Secs: " + df.format(loadTimeSecs) + " Secs");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Instant Approval page Time Secs: " + df.format(loadTimeSecs) + " Secs");
	}

	public void closeAllCookiesButtonIfExist() {
		basePage.waitForElementToBeClickable(acceptAllCookiesBtn);
		if(basePage.isElementDisplayedOptional(acceptAllCookiesBtn)){
			System.out.println("2nd time cookies displayed");
			//acceptAllCookiesBtn.click();
			basePage.jsClick(acceptAllCookiesBtn);
			//basePage.clickButtonIfDisplayed(closeXPopUp);
			waitForSecs(2);
		}
	}

	public void isSearchBarDisplayedInTheHeader() {
		basePage.waitForElementVisible(searchInput);
		searchInput.isDisplayed();
	}

	public void clickOnSearchInput() {
		basePage.waitForElementToBeClickable(searchInput);
		basePage.click(searchInput);
	}

	public void enterSearchValue(String searchValue) throws InterruptedException {
		basePage.waitForElementVisible(searchInput);
		searchInput.click();
		searchInput.clear();
		searchInput.sendKeys(searchValue);
	}

	public boolean isSearchSuggestionsAreDisplayed(String suggestion) {
		waitForSecs(3);
		return getSearchSuggestions(suggestion).isDisplayed();
	}

	public boolean isSearchSuggestionsAreDisplayedPerf(String suggestion) {
		basePage.waitForElementVisible(getSearchSuggestions(suggestion));
		return getSearchSuggestions(suggestion).isDisplayed();
	}

	public String getSearchResults(String suggestion) {
		waitForSecs(3);
		return getSearchSuggestions(suggestion).getText();
	}
	public void selectSuggestion() {
		waitForSecs(5);
		basePage.waitForElementToBeClickable(searchSuggestionLink);
		searchSuggestionLink.click();
		waitForSecs(3);
	}

	public void selectSuggestionPerf() {
		basePage.waitForElementToBeClickable(searchSuggestionLink);
		DecimalFormat df = new DecimalFormat("#.###");
		long startTime = System.currentTimeMillis();
		Log.info("Start Time: " + startTime+ " milli Secs");
		searchSuggestionLink.click();
		waitForSecs(2);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Click search suggestion");
		basePage.waitForPageToLoadPerf();
		//basePage.waitForElementVisible(rentalPaymentsOptionsButton);
		basePage.waitForElementVisible(By.xpath("//div[contains(@class, 'chakra-accordion__item')]/button[@type='button']"));
		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		double loadTimeSecs = loadTime/1000.0;
		Log.info("Search results page Load Time Load Time Secs: " + df.format(loadTimeSecs) + " Secs");
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Search results page Load Time Secs: " + df.format(loadTimeSecs) + " Secs");

//		// Base on timing API
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
//		long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
//		double loadAPITimeSecs = (loadEventEnd - navigationStart) /1000.0;
//		Log.info("Search results page API load time secs: " +loadAPITimeSecs);
//		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Search results page API load time secs: " +loadAPITimeSecs);

	}

	public boolean isSearchListDisplayed() {
		waitForSecs(3);
		return !searchSuggestionList.isEmpty();
	}

	public boolean isNoSuggestionsDisplayed() {
		waitForSecs(3);
		return searchSuggestionList.isEmpty();
	}

	public List<String> getSearchPlaceHolderText(){
		List<String> attributeValues = new ArrayList<>();
		for (int i = 0; i < shopByCategoryList.size(); i++) {
			String value = searchInput.getAttribute("placeholder");
			System.out.println("Attribute value at " + (i * 5) + "s: " + value);
			attributeValues.add(value);
			waitForSecs(5);
		}
		return attributeValues;
	}

	public String getSortAvailability(){
		waitForSecs(2);
		return sortByAvailability.getText();
	}

	public void hoverToL1Category(String L1Category) throws InterruptedException {
		actions.moveToElement(getL1CategoryLink(L1Category)).build().perform();
		waitForSecs(3);
	}

	public void clickOnL1Category(String L1Category) throws InterruptedException {
		basePage.click(getL1CategoryLink(L1Category));
		Log.info("Clicked on L1 category");
	}

	public boolean isL2SubCategoriesLinksDisplayed(String L2Category) {
		return getL2SubCategoriesLink(L2Category).isDisplayed();
	}

	public void ClickOnL2SubCategory(String L2Category) {
		waitForSecs(2);
		getL2SubCategoriesLink(L2Category).click();
	}

	public void ClickOnSpecialCategory(String specialCategory) {
		getSpecialCategoriesLink(specialCategory).click();
	}

	public void ClickOnL3Category(String L3Category, String L2Category) {
		waitForSecs(2);
		getL3CategoriesLink(L3Category, L2Category).click();
	}

	public void clickOnRACBrandLogo() {
		basePage.waitForElementToBeClickable(racBrandLogo);
		basePage.click(racBrandLogo);
	}

	public boolean verifyFeatureBannerCarouselDisplay() {
		basePage.waitForElementVisible(featureBannerCarousel);
		return featureBannerCarousel.isDisplayed();
	}

	public String getUserFirstNameOnHeader() {
		waitForSecs(5);
		return userFirstNameHeader.getText();
	}

	public boolean getNumberOfItemsInCart(String numberOfItems) {
		waitForSecs(3);
		return getItemCountInCart(numberOfItems).isDisplayed();
	}

	public boolean isShopByCategorySectionAllCategoriesDisplayed(String categoryName) {
		return getShopByCategoriesSectionAllCategoriesLink(categoryName).isDisplayed();
	}

	public boolean isSectionDisplayedOnHomePage(String section) {
		return getSectionOnHomePageText(section).isDisplayed();
	}

	public void clickOnShopByCategoryItem(String category) {
		waitForSecs(2);
		basePage.scrollToElement(shopAllButton);
		getShopByCategoriesSectionAllCategoriesLink(category).click();
	}

	public boolean isShopAllButtonDisplayed() {
		return shopAllButton.isDisplayed();
	}

	public void clickOnShopAllButton() {
		basePage.waitForElementToBeClickable(shopAllButton);
		basePage.click(shopAllButton);
	}

	public boolean isAccordionRACButtonCollapsed() {
		//return Boolean.parseBoolean(getFooterAccordionButton(buttonName).getDomAttribute("aria-expanded"));
		return Boolean.parseBoolean(accordionAboutRentACenterButton.getDomAttribute("aria-expanded"));
	}

	public boolean isAccordionInfoAndToolsButtonCollapsed() {
		//return Boolean.parseBoolean(getFooterAccordionButton(buttonName).getDomAttribute("aria-expanded"));
		return Boolean.parseBoolean(accordionInfoAndToolsButton.getDomAttribute("aria-expanded"));
	}


	public boolean verifyFrequentlySearchedDisplay() {
		basePage.waitForElementVisible(frequentlySearchedTxt);
		return frequentlySearchedTxt.isDisplayed();
	}

	public void scrollToPopularItemsSection(String sectionName) {
		basePage.scrollToElement(popularitems);
		waitForSecs(2);
	}

	public void isSectionDisplayed() {
		basePage.waitForElementVisible(popularitems);
		popularitems.isDisplayed();
	}

	public void clickOnItem() {
		basePage.waitForElementToBeClickable(ProductItem);
		basePage.click(ProductItem);
	}

	public boolean verifyPdpDisplay() {
		basePage.waitForElementVisible(productinfo);
		return productinfo.isDisplayed();
	}

	public void scrollToFeaturedDealsSection(String sectionName) {
		basePage.scrollToElement(featureddeals);
		waitForSecs(2);
	}

	public void isFeatuedDealsSectionDisplayed() {
		basePage.scrollToElement(featureddeals);
		basePage.waitForElementVisible(featureddeals);
		featureddeals.isDisplayed();
	}

	public void isPricingDisplayed() {
		basePage.waitForElementVisible(pricing);
		pricing.isDisplayed();
	}

	public void isDescriptionDisplayed() {
		basePage.waitForElementVisible(productdescription);
		productdescription.isDisplayed();
	}

	public void isImageDisplayed() {
		basePage.waitForElementVisible(productdescription);
		productdescription.isDisplayed();
	}

	public void clickOnRightArrow() {
		basePage.waitForElementToBeClickable(rightarrow);
		basePage.click(rightarrow);
	}

	public void clickOnLeftArrow() {
		basePage.waitForElementToBeClickable(leftarrow);
		leftarrow.isDisplayed();
	}

	public List<WebElement> getNextSetOfDeals() {
		return driver.findElements(By.xpath("//div[@class='featuredDealsSlider css-1u9gfme']//div[@class='slick-slider slick-initialized']//div[@class='css-eqwipv']"));
	}

	public List<WebElement> getPreviousSetOfDeals() {
		return driver.findElements(By.xpath("//div[@class='featuredDealsSlider css-1u9gfme']//div[@class='slick-slider slick-initialized']//div[@class='css-eqwipv']"));
	}

	public void clickOnItemFromTheFeaturedDeals() {
		basePage.waitForElementToBeClickable(item);
		basePage.click(item);
	}

	public void clickRightArrowButton() {
		basePage.waitForElementToBeClickable(rightarrowbutton);
		basePage.click(rightarrowbutton);
	}

	public void clickLeftArrowButton() {
		basePage.waitForElementToBeClickable(lefttarrowbutton);
		basePage.click(lefttarrowbutton);
	}

	public void isNavigatedToNextBanner() {
		basePage.waitForElementVisible(banner);
		banner.isDisplayed();
	}

	public void clickOnNavigationDot() {
		basePage.waitForElementToBeClickable(navigationdot);
		basePage.click(navigationdot);
	}

	public void isBannerDisplayed() {
		basePage.waitForElementVisible(getbanner);
		getbanner.isDisplayed();
	}

	public void clickOnBanner() {
		basePage.waitForElementToBeClickable(banner);
		basePage.click(banner);
	}

	public void isPageRedirected() {
		basePage.waitForElementVisible(appliances);
		appliances.isDisplayed();
	}

	public void scrollToFooterStoreLocatorSection(String sectionName) {
		basePage.scrollToElement(storelocatorbutton);
		waitForSecs(2);
	}

	public void clickOnStoreLocator() {
		basePage.waitForElementToBeClickable(storelocatorbutton);
		basePage.click(storelocatorbutton);
	}

	public void clickOnMakeThisMyStoreLinkInStoreLocator() {
		basePage.waitForElementToBeClickable(makethismystorebutton);
		basePage.click(makethismystorebutton);
		waitForSecs(3);
	}

	public boolean isStoreErrorMsgDisplayedOnPopUp() {
		basePage.waitForElementVisible(invalidstorelocatorerrorMsg);
		return invalidstorelocatorerrorMsg.isDisplayed();
	}

	public boolean isStoreMsgDisplayedOnPopUp(String text) {
		basePage.waitForElementVisible(storeLocatorPopupMessage);
		return storeLocatorPopupMessage.getText().contains(text);
	}

	public void clickOnMyOffersButton() {
		basePage.waitForElementToBeClickable(myOffersButton);
		basePage.click(myOffersButton);
	}

	public void clickOnCodeAppliedButton(String offerCode) {
		waitForSecs(3);
		getOfferCode(offerCode).click();
	}

    public double getHomePageLoadTime1() {

		DecimalFormat df = new DecimalFormat("#.###");
		long startTime = System.currentTimeMillis();

		Log.info("Start Time: " + startTime+ " milli Secs");


		try {

			List<WebElement> acceptAllCookiesBtnList = driver.findElements(By.id("acceptAllCookieButton"));
			if (!acceptAllCookiesBtnList.isEmpty()) {
				WebElement acceptAllCookiesBtn = acceptAllCookiesBtnList.get(0);
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].click();", acceptAllCookiesBtn);
				Log.info("Clicked on all cookies button");
			} else {
				Log.info("Accept cookies button is not displayed");
			}
		} catch (Exception e) {
			// Exception ignored intentionally to avoid test failure
		}

		basePage.waitForPageToLoad();

		// Wait until the page is fully loaded
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("return document.readyState").equals("complete");

		basePage.waitForPageToLoadPerf();

		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		double loadTimeSecs = loadTime/1000.0;

		Log.info("Simple Load Time Milli Secs: " + loadTime);
		Log.info("Simple Load Time Secs: " + df.format(loadTimeSecs) + " Secs");


		return loadTimeSecs;
	}

	public double getHomePageLoadTime() {

		DecimalFormat df = new DecimalFormat("#.###");
		long startTime = System.currentTimeMillis();
		Log.info("Start Time: " + startTime+ " milli Secs");

		DriverFactory.getDriver().get(Constants.BASE_URL);
		Log.info("Navigate to URL: "+Constants.BASE_URL);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"URL: " + Constants.BASE_URL);

		basePage.acceptAllCookies();
		basePage.waitForPageToLoadPerf();


		long endTime = System.currentTimeMillis();
		long loadTime = endTime - startTime;
		Log.info("End Time: " + endTime+ " milli Secs");
		double loadTimeSecs = loadTime/1000.0;
		Log.info("Simple Load Time Milli Secs: " + loadTime);
		Log.info("Simple Load Time Secs: " + df.format(loadTimeSecs) + " Secs");

		// Base on timing API
		JavascriptExecutor js = (JavascriptExecutor) driver;
		long navigationStart = (Long) js.executeScript("return window.performance.timing.navigationStart;");
		long loadEventEnd = (Long) js.executeScript("return window.performance.timing.loadEventEnd;");
		double loadAPITimeSecs = (loadEventEnd - navigationStart) /1000.0;

		Log.info("Navigation timing API load time secs: " +loadAPITimeSecs);
		ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Navigation timing API load time secs: " +loadAPITimeSecs);

		return loadTimeSecs;
	}

}