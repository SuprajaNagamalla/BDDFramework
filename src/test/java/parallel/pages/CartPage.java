package parallel.pages;

import static com.qa.factory.DriverFactory.getDriver;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.qa.util.ElementActions;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import com.qa.util.ScenarioContext;


//import utilities.WaitHelper;

public class CartPage extends ElementActions {

	protected WebDriver driver;
    private static final Logger Log = LoggerHelper.getLogger();

    BasePage basePage=new BasePage(getDriver());
    SoftAssert softAssert = new SoftAssert();
//    ScenarioContext scenarioContext = new ScenarioContext();

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    // *** Element Locators declaration start ***
    public static final String storeChangeWarningMessage = "Your store has changed and this may effect your previous pricing, promotion and inventory availability.";

    @FindBy(xpath="//*[@data-testid='cart-estimates']")
    private WebElement cartEstimate;

    @FindBy(xpath="//*[@data-testid='cart-estimates']//p[contains(text(),'Estimated Cart Total')]")
    private WebElement estimatedCartTotalText;

    @FindBy(xpath="//*[contains(text(),'Estimated Cart Total')]/parent::div//*[local-name()='svg']")
    private WebElement estimatedCartTotalToolTip;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[2]")
    private WebElement cartEstimatePrice;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[3]")
    private WebElement cartEstimatePaymentOption;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[4]")
    private WebElement cartEstimateAsteriskMark;
    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[5]")
    private WebElement cartEstimateSameAsCashPrice;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[6]")
    private WebElement cartEstimateSameAsCashText;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[7]")
    private WebElement remainingCartApprovalText;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[8]")
    private WebElement remainingCartApprovalPrice;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[9]")
    private WebElement remainingCartApprovalPaymentOption;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[10]")
    private WebElement remainingCartApprovalAsteriskMark;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[11]")
    private WebElement remainingCartApprovalSameAsCashPrice;

    @FindBy(xpath="(//*[@data-testid='cart-estimates']//div/p)[12]")
    private WebElement remainingCartApprovalSameAsCashPriceText;

    @FindBy(xpath="//*[contains(text(),'Remaining Cart Approval')]/parent::div//*[local-name()='svg']")
    private WebElement RemainingCartApprovalToolTip;

    @FindBy(xpath="//*[contains(@class, 'remaining-cart-popover-link')]//p[contains(text(), 'Approval Details')]")
    private WebElement getRemainingCartApprovalDetailsLink;

    @FindBy(xpath = "//div[contains(@id,'popover-body')]//div/p[contains(text(), 'Total Approval up to:')]")
    private WebElement totalApprovalUpToText;
    @FindBy(xpath ="//*/p[contains(text(), 'Total Approval up to:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]/p[1]")
    private WebElement totalApprovalUpToPaymentPerWeekPrice;
    @FindBy(xpath ="//*/p[contains(text(), 'Total Approval up to:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]//*[contains(text(), 'per week')]")
    private WebElement totalApprovalUpToPerWeekText;
    @FindBy(xpath ="//*/p[contains(text(), 'Total Approval up to:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]/p[2]")
    private WebElement totalApprovalUpToSameAsCashPrice;
    @FindBy(xpath ="//*/p[contains(text(), 'Total Approval up to:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]//*[contains(text(), 'same as cash')]")
    private WebElement totalApprovalUpToSameAsCashText;
    @FindBy(xpath ="//*/p[contains(text(), 'Estimated Cart Total:')]")
    private WebElement currentRentalAmountPaymentText;
    @FindBy(xpath ="//*/p[contains(text(), 'Estimated Cart Total:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]/p[1]")
    private WebElement currentRentalAmountPaymentPerWeekPrice;
    @FindBy(xpath ="//*/p[contains(text(), 'Estimated Cart Total:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]//*[contains(text(), 'per week')]")
    private WebElement currentRentalAmountPerWeekText;
    @FindBy(xpath ="//*/p[contains(text(), 'Estimated Cart Total:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]/p[2]")
    private WebElement currentRentalAmountSameAsCashPrice;
    @FindBy(xpath ="//*/p[contains(text(), 'Estimated Cart Total:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]//*[contains(text(), 'same as cash')]")
    private WebElement currentRentalAmountSameAsCashText;
    @FindBy(xpath ="//*/p[contains(text(), 'Remaining Approval:')]")
    private WebElement remainingApprovalText;
    @FindBy(xpath ="//*/p[contains(text(), 'Remaining Approval:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]/p[1]")
    private WebElement remainingApprovalPaymentPerWeekPrice;
    @FindBy(xpath ="//*/p[contains(text(), 'Remaining Approval:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]//*[contains(text(), 'per week')]")
    private WebElement remainingApprovalPerWeekText;
    @FindBy(xpath ="//*/p[contains(text(), 'Remaining Approval:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]/p[2]")
    private WebElement remainingApprovalSameAsCashPrice;
    @FindBy(xpath ="//*/p[contains(text(), 'Remaining Approval:')]/parent::div/following-sibling::*[contains(@class,'popover-prices')]//*[contains(text(), 'same as cash')]")
    private WebElement remainingApprovalSameAsCashText;

    @FindBy(xpath ="(//button[@aria-label='Close'])[3]")
    private WebElement approvalDetailsPopoverClose;
    @FindBy(xpath="//h2[contains(@class, 'cart-item-variant-name')]")
    private List<WebElement> listofProductNameInCart;

    @FindBy(xpath = "//*[contains(@class,'remove-cart')]")
    private List<WebElement>  listOfRemoveLink;
    @FindBy(xpath = "//header[contains(text(),'Item selection updated!')]")
    private WebElement itemSelectionUpdated;
    @FindBy(xpath ="//div[contains(@class, 'outOfStock-banner-slot')]/child::*[name()='svg'][1]")
    private WebElement storeChangeWarningInfo;

    @FindBy(xpath ="//div[contains(@class, 'outOfStock-banner-slot')]/child::*[name()='svg'][2]")
    private WebElement storeChangeWarningClose;

    @FindBy(xpath ="//*[@class='inventory-availability']")
    private WebElement storeChangeWarningText;

    @FindBy(xpath ="//button[@aria-label='favorite']")
    private WebElement wishlistButtonOnPDP;

    @FindBy(xpath = "//button[text()='View Saved Items']")
    private WebElement viewSavedItemsCTA;
    @FindBy(xpath="//div[contains(@class, 'wishlist-banner-content')]")
    private WebElement wishlistBanner;

    @FindBy(xpath="//div[contains(@class, 'wishlist-banner-slot')]/child::*[name()='svg'][1]")
    private WebElement wishlistBannerInfo;

    @FindBy(xpath="//div[contains(@class, 'wishlist-banner-slot')]/child::*[name()='svg'][2]")
    private WebElement wishlistBannerClose;

    @FindBy(xpath = "//*[local-name()='svg' and @viewBox='0 0 1024 1024']")
    private WebElement wishlistButton;

    @FindBy(xpath="//div[contains(@class, 'startOrder-banner-slot')]//child::*[name()='svg'][1]")
    private WebElement startOrderBannerInfo;

    @FindBy(xpath="//div[contains(@class, 'startOrder-banner-slot')]/child::*[name()='svg'][1]")
    private WebElement startOrderBannerClose;

    @FindBy(xpath="//div[contains(@class, 'start-order-banner')]")
    private WebElement startOrderBannerText;

    @FindBy(xpath = "//header[contains(text(),'Item already added')] | //header[contains(text(),'Item already added')]")
    private WebElement itemAlreadyAdded;

    @FindBy(xpath = "//header[contains(text(),'Item added to cart!')]")
    private WebElement itemAddedToCart;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShopping;

    @FindBy(xpath = "//button[text()='View cart & order'] | //button[text()='View Cart & Order'] | //button[text()='VIEW CART & ORDER']")
    private WebElement viewCartAndOrder;

    @FindBy(xpath = "//h2/parent::div//a[contains(@class, cart-item-variant-image)]/img")
    private WebElement cartPageProductImage;

    @FindBy(xpath ="//div/p[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'cart')]")
    private WebElement cartButtonOnPDP;

    @FindBy(xpath ="//div/*[local-name()='svg' and @aria-label='cart-shopping']")
    private WebElement emptyCartImage;

    @FindBy(xpath ="//div/p[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no items in cart!')]")
    private WebElement noItemsInCartText;
    @FindBy(xpath ="//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'shop latest deals')]")
    private WebElement shopLatestDealsLink;

    @FindBy(xpath ="//div/p[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'save and access your cart with our instant approval process!')]")
    private WebElement saveAndAccessYourCartText;

    @FindBy(xpath ="//button[contains(@class, 'chakra-button get-approved-create-account-button')] | //button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'get approved & create account')]")
    private WebElement getApprovedAndCreateAccountButton;

    @FindBy(xpath ="//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'sign in to access or save cart')]")
    private WebElement signInToAccessorSaveCart;

    @FindBy(xpath ="//button[@data-index='1']")
    private WebElement savedItems;

    @FindBy(xpath ="//*[@data-testid='empty-wishlist']/*[local-name()='svg']")
    private WebElement emptyWishlistImage;
    @FindBy(xpath ="//div[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'no saved items')]")
    private WebElement noSavedItemsText;
    @FindBy(xpath = "//button[@title='Add To Cart']")
    private WebElement addToCartButton;

    @FindBy(xpath="//h2[contains(@class, 'cart-item-variant-name')]")
    private WebElement productNameInCart;

    //@FindBy(xpath = "//h2[@data-testid='cart-group-in-stock']")
    @FindBy(css="[data-testid='cart-group-in-stock']")
    private WebElement inStockHeaderElement;

    @FindBy(css="[data-testid='cart-group-special-order']")
    private WebElement specialOrderHeaderElement;

    @FindBy(css="[data-testid='cart-group-online-only']")
    private WebElement onlineOnlyHeaderElement;

    @FindBy(xpath = "//*[contains(@class, 'pricing-info')]")
    private WebElement pricingInfoPopup;

    @FindBy(xpath = "//*[contains(@class, 'price-info-text')]")
    private WebElement pricebreakDownInfoText;

    @FindBy(xpath = "//p[text()='Renewal Rate:']/following-sibling::div/strong")
    private WebElement renewalRateValueRentalDetails;

    @FindBy(xpath = "//p[text()='Number of Payments:']/following-sibling::div/strong")
    private WebElement noOfPaymentsValueRentalDetails;

    @FindBy(xpath = "//p[text()='Total Cost To Own:']/following-sibling::div/strong")
    private WebElement totalCostValueRentalDetails;

    @FindBy(xpath = "//p[text()='Same as Cash Price:']/following-sibling::div/strong")
    private WebElement sameAsCashPriceValueRentalDetails;

    @FindBy(xpath = "//*[contains(@class,'remove-cart')]")
    private WebElement removeLink;
    @FindBy(xpath = "//header[contains(text(), 'Confirm Remove Item')]")
    private WebElement confirmRemoveItemHeader; //common for Wishlist and Add to art

    @FindBy(xpath = "//div//p[contains(text(),'Are you sure you want to remove this item from your cart?')]")
    private WebElement confirmCartRemoveText;
    @FindBy(xpath = "//button[contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'no, keep item')]")
    private WebElement noKeepItemButton; //common for Wishlist and Add to art

    @FindBy(xpath = "//button[contains(translate(@aria-label,'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'),'yes, remove item')]")
    private WebElement yesRemoveItemButton; //common for Wishlist and Add to art

    @FindBy(xpath = "//button[text()='Start Order']")
    private WebElement startOrderButton;

    @FindBy(xpath = "//*[text()='Save for Later'] ")
    private WebElement saveforLaterButton;

    @FindBy(xpath = "//*[text()='Save for Later']/child::*[name()='svg']")
    private WebElement saveforLaterImage;

    @FindBy(xpath = "//button[@aria-label='Close']/child::*[name()='svg']")
    private WebElement closeAnyModalPop; // common for all tooltip, model pop up

    @FindBy(xpath = "//button[@aria-label='Close']/child::*[name()='svg']")
    private List<WebElement> closeAnyModalPopList;

    @FindBy(xpath = "//button[text()='Sign in to access or save cart']")
    private WebElement signInToAccessOrSaveCart;

    @FindBy(xpath = "//*[contains(@class,'remove-wishlist')]")
    private WebElement removeItemFromWishlist;

    @FindBy(xpath = "//header[contains(text(), 'saved your item!')]")
    private WebElement saverForLaterPopupHeader;
    @FindBy(xpath = "//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'view saved items')]")
    private WebElement viewSavedItemButton;

    @FindBy(xpath = "//button[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'continue shopping')]")
    private WebElement continueShoppingButton;

     @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'view pricing & availability')]")
    private WebElement viewPricingAvailability;

    //Getters not generated
    @FindBy(xpath = "//*[contains(@aria-label, 'current price')]")
    private WebElement productPriceValue;

    @FindBy(xpath = "//*[contains(@class, 'selected-term-wrapper')]")
    private WebElement productPaymentFrequency;
    @FindBy(xpath = "//button[@role='tab']//div[contains(text(), 'Saved')]")
    private WebElement savedSection;

    @FindBy(xpath = "//a[text()='View Pricing & Availability']")
    private WebElement viewPricingAndAvailabilityLink;

    @FindBy(xpath = "//*[contains(@data-testid, 'sf-wishlist-remove')]")
    private WebElement wishlistRemoveButton;

    @FindBy(xpath = "//div//p[contains(text(), 'Are you sure you want to remove this item from your wishlist?')]")
    private WebElement wishlistRemoveText;

    @FindBy(id="LogInDiv")
    private WebElement MyAccountPopup;

    @FindBy(xpath = "//*[@class='strike-through']/following-sibling::*[1]")
    private WebElement bundleProductRenewalRateStrikeThroughValue;

    @FindBy(xpath ="//*[contains(@class, 'rates-header')]//strong")
    private WebElement bundleProductRenewalRateDiscountValue;

    @FindBy(xpath = "//*[text()='Renewal Rate:']/following-sibling::*[1]")
    private WebElement bundleProductRentalRateValue;

    @FindBy(xpath = "//*[text()='Number of Payments:']/following-sibling::span")
    private WebElement bundleProductNumberOfPaymentsValue;

    @FindBy(xpath = "//*[text()='Total Cost To Own:']/following-sibling::span")
    private WebElement bundleProductTotalCostToOwnValue;

    @FindBy(xpath = "//*[text()='Same as Cash Price:']/following-sibling::span")
    private WebElement bundleProductSameAsCashPriceValue;

    @FindBy(xpath = "//*[contains(@class, 'prebundle-cart')]//p[contains(text(), 'Item Price Details')]")
    private List<WebElement> itemPriceDetailElements;

    @FindBy(xpath = "//*[contains(@class, 'prebundle-cart')]//div/a")
    private List<WebElement> bundleProductNameOnCartPage;

    @FindBy(xpath = "//*[text()='Weekly Payment:']/following-sibling::div/b")
    private WebElement bundleProductWeeklyPayment;

    @FindBy(xpath = "//*[text()='Renewal Payments:']/following-sibling::div/b")
    private WebElement bundleProductRenewalPayments;

    @FindBy(xpath = "//*[text()='Max Total Cost:']/following-sibling::div/b")
    private WebElement bundleProductMaxTotalCost;

    @FindBy(xpath = "//*[text()='Cash Price:']/following-sibling::div/b")
    private WebElement bundleProductCashPrice;

    @FindBy(xpath = "//*[contains(@class, 'rates-delivery')]//button/child::*[name()='svg']")
    private WebElement estimatedDeliveryToolTip;

    @FindBy(xpath = "//*[local-name()='svg' and @aria-label='cart-shopping']/parent::div/span[text()='1']")
    private WebElement headerCartCountAs1WhenItemAddedToCart;

    @FindBy(xpath = "//div[text()='Cart']/span")
    private WebElement cartTabCount;

    @FindBy(xpath = "//div[text()='Saved']/span")
    private WebElement SavedTabCount;

    @FindBy(xpath = "//*[text()='Renewal Rate:']/following-sibling::div/b")
    private WebElement bundleProductTotalRenewalRate;

    @FindBy(xpath = "//*[text()='Number of Payments:']/following-sibling::div/b")
    private WebElement bundleProductTotalNumberOfPayments;

    @FindBy(xpath = "//*[text()='Total Cost To Own:']/following-sibling::div/b")
    private WebElement bundleProductTotalCostToOwn;

    @FindBy(xpath = "//*[text()='Same as Cash Price:']/following-sibling::div/b")
    private WebElement bundleProductTotalSameAsCashPrice;


    // *** Element Locators declaration End ***

	// Constructor to initialize elements using Page Factory
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    // *** page action methods on elements ***

    public WebElement getCartTabCount() {
        return cartTabCount;
    }

    public WebElement getSavedTabCount() {
        return SavedTabCount;
    }
    public WebElement getViewCartAndOrder() {
        return viewCartAndOrder;
    }

    public WebElement getInStockHeaderElement() {
        basePage.scrollToElement(inStockHeaderElement);
        return inStockHeaderElement;
    }

    public WebElement getSpecialOrderHeaderElement() {
        basePage.scrollToElement(specialOrderHeaderElement);
        return specialOrderHeaderElement;
    }

    public WebElement getPricingInfoPopup() {
        return pricingInfoPopup;
    }

    public WebElement getPriceBreakDownPriceInfoText() {
        return pricebreakDownInfoText;
    }

    public WebElement getRenewalRateValueRentalDetails() {
        return renewalRateValueRentalDetails;
    }

    public WebElement getNoOfPaymentsValueRentalDetails() {
        return noOfPaymentsValueRentalDetails;
    }

    public WebElement getTotalCostValueRentalDetails() {
        return totalCostValueRentalDetails;
    }

    public WebElement getSameAsCashPriceValueRentalDetails() {
        return sameAsCashPriceValueRentalDetails;
    }

    public WebElement getRemoveLink() {
        return removeLink;
    }

    public WebElement getConfirmRemoveItemHeader() {
        return confirmRemoveItemHeader;
    }

    public WebElement getConfirmCartRemoveText() {
        return confirmCartRemoveText;
    }

    public WebElement getNoKeepItemButton() {
        return noKeepItemButton;
    }

    public WebElement getYesRemoveItemButton() {
        return yesRemoveItemButton;
    }

    public WebElement getStartOrderButton() {
        return startOrderButton;
    }

    public WebElement getSaveForLaterButton() {
        return saveforLaterButton;
    }

    public WebElement getSaveforLaterImage() {
        return saveforLaterImage;
    }

    public WebElement getCloseAnyModalPop() {
        return closeAnyModalPop;
    }

    public WebElement getSignInToAccessOrSaveCart() {
        return signInToAccessOrSaveCart;
    }

    public WebElement getProductNameInCart() {
        return productNameInCart;
    }

    public WebElement getProductPriceValue() {
        return productPriceValue;
    }

    public WebElement getProductPaymentFrequency() {
        return productPaymentFrequency;
    }

    public WebElement getSavedSection() {
        return savedSection;
    }

    public WebElement getViewPricingAndAvailabilityLink() {
        return viewPricingAndAvailabilityLink;
    }

    public WebElement getWishlistRemoveButton() {
        return wishlistRemoveButton;
    }

    public WebElement getWishlistRemoveText() {
        return wishlistRemoveText;
    }

    public WebElement getMyAccountPopup() {
        return MyAccountPopup;
    }

    //page actions
    public void clickOnViewCartAndOrder(){
        basePage.scrollToElement(viewCartAndOrder);
        viewCartAndOrder.click();
        waitForSecs(3);
    }
    public void validateCartPageData(ScenarioContext scenarioContext){
        String productNameInCartPage = productNameInCart.getText();
        basePage.scrollToElement(pricingInfoPopup);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Product Name in Cart:: "+productNameInCartPage);
        softAssert.assertTrue(cartPageProductImage.isDisplayed(), "Product Image not displayed in Cart Page");
        softAssert.assertTrue(pricingInfoPopup.isDisplayed(), "Price info text is not displayed in Cart Page");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+pricingInfoPopup.getText()+" Link is displayed");
        String pdpPageProductName = (String)(scenarioContext.getContext("ProductName1"));
        softAssert.assertEquals(productNameInCartPage, pdpPageProductName);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Product Name in PDPPage:: "+pdpPageProductName);

        pricingInfoPopup.click();
        waitForSecs(2);
        String rentalRatePDP = (String)(scenarioContext.getContext("ProductModel1RentalPaymentPrice"));
        String noOfPaymentsPDP = (String)(scenarioContext.getContext("ProductModel1NoOfPayments"));
        String totalCostToOwnPDP = (String)(scenarioContext.getContext("ProductModel1TotalCostToOwn"));
        String sameAsCashPricePDP = (String)(scenarioContext.getContext("ProductModel1SameAsCashPrice"));
        System.out.println("rentalRatePDP::"+rentalRatePDP);
        System.out.println("renewalRateValueRentalDetails::"+renewalRateValueRentalDetails);

        softAssert.assertTrue(pricebreakDownInfoText.isDisplayed(), "Pricing break down information pop is not displayed in Cart Page");
        softAssert.assertTrue(renewalRateValueRentalDetails.getText().contains(rentalRatePDP), "Renewal Rate is not displayed in Cart Page");
        softAssert.assertTrue(noOfPaymentsValueRentalDetails.getText().contains(noOfPaymentsPDP), "No Of Payments is not displayed in Cart Page");
        basePage.scrollToElement(totalCostValueRentalDetails);
        softAssert.assertTrue(totalCostValueRentalDetails.getText().contains(totalCostToOwnPDP), "Total Cost To Own is not displayed in Cart Page");
        softAssert.assertTrue(sameAsCashPriceValueRentalDetails.getText().contains(sameAsCashPricePDP), "Same As Cash Price is not displayed in Cart Page");
//        basePage.isTextDisplayed( (String)(scenarioContext.getContext("ProductModel1EstimatedDelivery")));
        basePage.scrollToElement(closeAnyModalPop);
        closeAnyModalPop.click();
        waitForSecs(2);

        basePage.scrollToElement(removeLink);
        softAssert.assertTrue(removeLink.isDisplayed(), "Remove Link is not displayed in Cart Page");
        softAssert.assertTrue(saveforLaterButton.isDisplayed(), "Save For Later button is not displayed in Cart Page");
        softAssert.assertTrue(saveforLaterImage.isDisplayed(), "Save For Later Image is not displayed in Cart Page");
        softAssert.assertTrue(startOrderButton.isDisplayed(), "Start Order Button is not displayed in Cart Page");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Buttons:: "+removeLink.getText() +":: "+saveforLaterButton.getText());
    }

    public void clickOnAddToCart(){
        basePage.waitForElementVisible(addToCartButton);
        basePage.scrollToElement(addToCartButton);
        addToCartButton.click();
        waitForSecs(3);
    }
    public void validateBundleProductsDataInCartPage(ScenarioContext scenarioContext){
        for(int i=1;i<=2;i++) {
            basePage.scrollToElement(bundleProductNameOnCartPage.get(i-1));
            String cartPageBundleProductName = bundleProductNameOnCartPage.get(i-1).getText();
            itemPriceDetailElements.get(i-1).click();
            waitForSecs(3);
            String pdpPageBundleProductName = (String)scenarioContext.getContext("productNameFromPDPPage" + i);
            softAssert.assertEquals(cartPageBundleProductName,pdpPageBundleProductName);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Pre-Bundle Product Name:: "+cartPageBundleProductName);
            //commented below code as there is application issue
            String bundleProductRenewalRateFromPDPPage = (String)scenarioContext.getContext("bundleProductRenewalRateFromPDPPage" + i);
            String bundleProductRenewalRate = bundleProductRentalRateValue.getText();
            softAssert.assertTrue(bundleProductRenewalRate.contains(bundleProductRenewalRateFromPDPPage), "Bundle Product Renewal Rate From PDPPage not matches on Cart Page");
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Pre-Bundle Renewal Rate:: "+bundleProductRenewalRate);

            String bundleProductNumberOfPaymentsFromPDPPage = (String)scenarioContext.getContext("bundleProductNumberOfPaymentsFromPDPPage" + i);
            String bundleProductNumberOfPayments = bundleProductNumberOfPaymentsValue.getText();
            softAssert.assertTrue(bundleProductNumberOfPayments.contains(bundleProductNumberOfPaymentsFromPDPPage), "Bundle Product Number Of Payments PDPPage not matches on Cart Page");
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Pre-Bundle Number Of Payments :: "+bundleProductNumberOfPayments);

            String bundleProductTotalCostToOwnFromPDPPage = (String)scenarioContext.getContext("bundleProductTotalCostToOwnFromPDPPage" + i);
            String bundleProductTotalCostToOwn = bundleProductTotalCostToOwnValue.getText();
            softAssert.assertTrue(bundleProductTotalCostToOwn.contains(bundleProductTotalCostToOwnFromPDPPage), "Bundle Product Total Cost To Own PDPPage not matches on Cart Page");
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Pre-Bundle Total Cost To Own :: "+bundleProductTotalCostToOwn);

            String bundleProductSameAsCashPriceFromPDPPage = (String)scenarioContext.getContext("bundleProductSameAsCashPriceFromPDPPage" + i);
            String bundleProductSameAsCashPrice = bundleProductSameAsCashPriceValue.getText();
            softAssert.assertTrue(bundleProductSameAsCashPrice.contains(bundleProductSameAsCashPriceFromPDPPage), "Bundle Product Same As Cash Price PDPPage not matches on Cart Page");
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Pre-Bundle SameAsCashPrice :: "+bundleProductNumberOfPayments);

            basePage.scrollToElement(closeAnyModalPop);
            closeAnyModalPopList.get(i-1).click();
            waitForSecs(2);

            basePage.scrollToElement(removeLink);
            softAssert.assertTrue(removeLink.isDisplayed(), "Remove Link is not displayed in Cart Page");
            softAssert.assertTrue(saveforLaterButton.isDisplayed(), "Save For Later button is not displayed in Cart Page");
            softAssert.assertTrue(saveforLaterImage.isDisplayed(), "Save For Later Image is not displayed in Cart Page");
            softAssert.assertTrue(startOrderButton.isDisplayed(), "Start Order Button is not displayed in Cart Page");
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Cart page Buttons:: "+removeLink.getText() +":: "+saveforLaterButton.getText());

        }

    }

    public void validateBundleProductTotalCostValues(ScenarioContext scenarioContext){
        //SFCC-9242 -defect exist
        String rentalRatePDP = (String)(scenarioContext.getContext("ProductModel1RentalPaymentPrice"));
        String noOfPaymentsPDP = (String)(scenarioContext.getContext("ProductModel1NoOfPayments"));
        String totalCostToOwnPDP = (String)(scenarioContext.getContext("ProductModel1TotalCostToOwn"));
        String sameAsCashPricePDP = (String)(scenarioContext.getContext("ProductModel1SameAsCashPrice"));
        System.out.println("rentalRatePDP::"+rentalRatePDP);

        basePage.scrollToElement(bundleProductTotalRenewalRate);
        System.out.println("renewalRateValueRentalDetails::"+bundleProductTotalRenewalRate.getText());

        softAssert.assertTrue(bundleProductTotalRenewalRate.getText().contains(rentalRatePDP), "Renewal Rate is not displayed in Cart Page");
        softAssert.assertTrue(bundleProductTotalNumberOfPayments.getText().contains(noOfPaymentsPDP), "No Of Payments is not displayed in Cart Page");
        softAssert.assertTrue(bundleProductTotalCostToOwn.getText().contains(totalCostToOwnPDP), "Total Cost To Own is not displayed in Cart Page");
        softAssert.assertTrue(bundleProductTotalSameAsCashPrice.getText().contains(sameAsCashPricePDP), "Same As Cash Price is not displayed in Cart Page");


        //bundle tool tip
        String estimatedDeliveryValue = (String)(scenarioContext.getContext("ProductModel1EstimatedDelivery"));
        basePage.isTextDisplayed(estimatedDeliveryValue);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Estimated Delivery Value:: "+estimatedDeliveryValue);
        basePage.scrollToElement(estimatedDeliveryToolTip);
        estimatedDeliveryToolTip.click();
        waitForSecs(2);
        basePage.isTextDisplayed("Longest delivery is selected among bundle Products");
//        closeAnyModalPop.click();

        //SFCC-3271, SFCC-2570 SFCC-2732, SFCC-3271
        basePage.scrollToElement(bundleProductNameOnCartPage.get(0));
        bundleProductNameOnCartPage.get(0).click();
        basePage.waitForPageToLoad();
        waitForSecs(5);
        softAssert.assertTrue(addToCartButton.isDisplayed(),"Add to cart button is not displayed and page is not navigated to PDP page ");
        //SFCC-3271
        softAssert.assertTrue(headerCartCountAs1WhenItemAddedToCart.isDisplayed(), "Header Cart Count as 1 is not displayed in PDP page");
    }

    public void clickOnStartOrder(){
        basePage.scrollToElement(startOrderButton);
        getStartOrderButton().click();
        waitForSecs(4);
    }

    public void clickOnHeaderCart(){
        basePage.scrollToElement(cartButtonOnPDP);
        cartButtonOnPDP.click();
        basePage.waitForPageToLoad();
        waitForSecs(2);
    }

    public void validateEmptyCartData(){
        emptyCartImage.isDisplayed();
        noItemsInCartText.isDisplayed();
        shopLatestDealsLink.isDisplayed();
        saveAndAccessYourCartText.isDisplayed();
        getApprovedAndCreateAccountButton.isDisplayed();
        signInToAccessOrSaveCart.isDisplayed();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Validate Empty Cart Data is displayed properly");
        Log.info("Validate Empty Cart Data is displayed properly");
    }

    public void clickOnSavedItemTab(){
        savedItems.click();
    }

    public void validateEmptySavedItemsData(){
        emptyWishlistImage.isDisplayed();
        noSavedItemsText.isDisplayed();
        shopLatestDealsLink.isDisplayed();
        signInToAccessOrSaveCart.isDisplayed();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Validate Empty Wishlist Data is displayed properly");
        Log.info("Validate Empty Wishlist Data is displayed properly");
    }

    public boolean validateCartTabCount(String itemCount){
     WebElement  element = driver.findElement(By.xpath("//button[@data-index='0']/div/span[normalize-space()='("+itemCount+")']"));
     return element.isDisplayed();
    }

    public boolean validateWishlistItemsTabCount(String itemCount){
        WebElement  element = driver.findElement(By.xpath("//button[@data-index='1']/div/span[normalize-space()='("+itemCount+")']"));
        return element.isDisplayed();
    }

    public void navigateToProductPage(String productId){
        driver.navigate().to("https://qa.www.rentacenter.com/p/"+productId);
    }

    public void validateSaveForLaterPopUp(){
        basePage.scrollToElement(saverForLaterPopupHeader);
        saverForLaterPopupHeader.isDisplayed();
        viewSavedItemButton.isDisplayed();
        continueShoppingButton.isDisplayed();
    }

    public void clickOnViewSavedItem(){
        basePage.scrollToElement(viewSavedItemButton);
        viewSavedItemButton.click();
        waitForSecs(3);
    }

    public void validateSavedItemPageData(){
        basePage.scrollToElement(removeItemFromWishlist);
        removeItemFromWishlist.isDisplayed();
        viewPricingAvailability.isDisplayed();
        waitForSecs(2);
    }

    public boolean validateProductDetailsInSavedItemPage(){
       String productNameFromPDP = (String)ScenarioContext.getStaticContext("ProductName1");
       WebElement productNameElement = driver.findElement(By.xpath("//h2/a"));
        return productNameElement.getText().contains(productNameFromPDP);
    }

    public void clickOnViewPricingAndAvailability(){
        viewPricingAvailability.click();
    }

    public void removeItemFromWishlist(){
        basePage.waitForElementVisible(removeItemFromWishlist);
        basePage.scrollToElement(removeItemFromWishlist);
        removeItemFromWishlist.click();
        waitForSecs(2);
        basePage.waitForElementVisible(confirmRemoveItemHeader);
        confirmRemoveItemHeader.isDisplayed();
        wishlistRemoveText.isDisplayed();
        noKeepItemButton.isDisplayed();

        //click on no keep Item Button to check page is not changed
        noKeepItemButton.click();
        waitForSecs(2);

        //now remove the item from saved list
        basePage.waitForElementVisible(removeItemFromWishlist);
        removeItemFromWishlist.click();
        waitForSecs(2);
        //click on Yes, remove item Button
        yesRemoveItemButton.click();
        waitForSecs(2);

    }
    public void removeItemFromCart(){
        basePage.waitForElementVisible(removeLink);
        basePage.scrollToElement(removeLink);

        removeLink.click();
        waitForSecs(2);
        basePage.waitForElementVisible(confirmRemoveItemHeader);
        confirmRemoveItemHeader.isDisplayed();
        confirmCartRemoveText.isDisplayed();
        noKeepItemButton.isDisplayed();

        //click on no keep Item Button to check page is not changed
        noKeepItemButton.click();
        waitForSecs(2);

        //validate start order message is not displayed SFCC-3342
        basePage.isElementNotDisplayed(startOrderBannerText);

        basePage.waitForElementVisible(removeLink);
        removeLink.click();
        waitForSecs(2);
        //now remove the item from cart page
        basePage.waitForElementVisible(yesRemoveItemButton);
        yesRemoveItemButton.click();
        waitForSecs(2);

        //confirm cart count is 0 after removing product
        softAssert.assertTrue(getCartTabCount().getText().contains("0"), "Cart tab count is not 0 after removing item from cart");
    }

    public void clickOnWishlistPDPPage(){
        basePage.scrollToElement(wishlistButtonOnPDP);
        wishlistButtonOnPDP.click();
        waitForSecs(3);
    }

    public void clickOnViewSavedItemsButton(){
        basePage.scrollToElement(viewSavedItemsCTA);
        viewSavedItemsCTA.click();
        waitForSecs(2);
    }

    public void validateWishlistBanner(){
        basePage.scrollToElement(wishlistBannerInfo);
        wishlistBannerInfo.isDisplayed();
        wishlistBanner.getText().contains("Click \"View Pricing & Availability\" to see current product info like pricing, availability and condition.");
        wishlistBannerClose.isDisplayed();
        waitForSecs(2);
    }

    public void validateStartOrderBanner(){
        basePage.scrollToElement(startOrderBannerInfo);
        startOrderBannerInfo.isDisplayed();
        startOrderBannerText.getText().contains("Click \"Start Order\" to complete checkout and apply a promo on each Item.");
        basePage.scrollToElement(startOrderBannerInfo);
        startOrderBannerClose.isDisplayed();
        waitForSecs(2);
    }

    public boolean validateStoreChangeWarningBanner(){
        waitForSecs(5);
        basePage.scrollToElement(storeChangeWarningInfo);
        return  storeChangeWarningInfo.isDisplayed() &&
                storeChangeWarningText.getText().contains(storeChangeWarningMessage) &&
                storeChangeWarningClose.isDisplayed();
    }

    public boolean storeChangeWarningInfoDisplayed() {
        waitForSecs(5);
        basePage.waitForElementVisible(storeChangeWarningInfo);
        return storeChangeWarningInfo.isDisplayed();
    }

    public void validateItemAlreadyAddedAndCartCount(String availability) {
        if (!availability.contains("special")){
            softAssert.assertTrue(itemAlreadyAdded.isDisplayed(), "Element with Expected message is not displayed" + itemAlreadyAdded.getText());/// defect-SFCC-9378
            clickOnViewCartAndOrder();
            String headerCountValue = getInStockHeaderElement().getText();
            String number = headerCountValue.replaceAll("\\D+", "");
            int cartCount = Integer.parseInt(number);
            softAssert.assertTrue(cartCount==1, "In Stock header not contains item count as 1");
            }else{
            //softAssert.assertTrue(itemSelectionUpdated.isDisplayed(), "Element with Expected message is not displayed" + itemSelectionUpdated.getText());/// defect-SFCC-9378
            softAssert.assertTrue(itemAddedToCart.isDisplayed(), "Element with Expected message is not displayed" + itemAddedToCart.getText());/// defect-SFCC-9378
            clickOnViewCartAndOrder();
            String headerCountValue = getSpecialOrderHeaderElement().getText();
            String number = headerCountValue.replaceAll("\\D+", "");
            int cartCount = Integer.parseInt(number);
            softAssert.assertTrue(cartCount>1, "Special order item count should be more than 1 but found value"+cartCount);
        }
    }

    public boolean validateProductNameInCartPage(String guestUserProductName){
        boolean status =false;
        for(WebElement productName: listofProductNameInCart){
            if(productName.getText().contains(guestUserProductName)) {
                status = true;
                break;
            }
        };
       return status;
    }

    public void clickOnProductNameInCartPage(String guestUserProductName){

//        for(WebElement productName: listofProductNameInCart){
        int elementFoundCount = 0;
        for(int i=0;i<listofProductNameInCart.size();i++){
            if(listofProductNameInCart.get(i).getText().contains(guestUserProductName)) {
                basePage.scrollToElement(listofProductNameInCart.get(i));
                listofProductNameInCart.get(i).click();
                elementFoundCount = i;
                waitForSecs(5);
                break;
            }
        };
        System.out.println("elementFoundCount on cart page::"+elementFoundCount);

    }

    public void removeAddedProductFromCartPage(String guestUserProductName){
        int elementFoundCount = 0;
        for(int i=0;i<listofProductNameInCart.size();i++){
            if(listofProductNameInCart.get(i).getText().contains(guestUserProductName)) {
                elementFoundCount = i;
                waitForSecs(5);
                break;
            }
        };
        System.out.println("elementFoundCount on cart page to remove::"+elementFoundCount);

        listOfRemoveLink.get(elementFoundCount).click();
        //now remove the item from cart page
        basePage.waitForElementVisible(yesRemoveItemButton);
        yesRemoveItemButton.click();
        waitForSecs(2);
    }

    public void validateEstimatedCartData(){
        basePage.scrollToElement(cartEstimatePrice);
        cartEstimate.isDisplayed();
        estimatedCartTotalText.isDisplayed();
        estimatedCartTotalToolTip.isDisplayed();
        cartEstimatePrice.isDisplayed();
        cartEstimatePaymentOption.isDisplayed();
        cartEstimateAsteriskMark.isDisplayed();
        cartEstimateSameAsCashPrice.isDisplayed();
        cartEstimateSameAsCashText.isDisplayed();
    }

    public void validateRemainingCartApproval(){
        basePage.scrollToElement(remainingCartApprovalText);
        remainingCartApprovalText.isDisplayed();
        estimatedCartTotalToolTip.isDisplayed();
        remainingCartApprovalPrice.isDisplayed();
        remainingCartApprovalPaymentOption.isDisplayed();
        cartEstimatePaymentOption.isDisplayed();
        remainingCartApprovalAsteriskMark.isDisplayed();
        remainingCartApprovalSameAsCashPrice.isDisplayed();
        remainingCartApprovalSameAsCashPriceText.isDisplayed();
    }

    public void validateRemainingCartApprovalDetailsLinkData() {
        basePage.scrollToElement(getRemainingCartApprovalDetailsLink);
        getRemainingCartApprovalDetailsLink.click();
        waitForSecs(2);
        basePage.scrollToElement(totalApprovalUpToText);
        totalApprovalUpToText.isDisplayed();
        totalApprovalUpToPaymentPerWeekPrice.isDisplayed();
        totalApprovalUpToPerWeekText.isDisplayed();
        totalApprovalUpToSameAsCashPrice.isDisplayed();
        totalApprovalUpToSameAsCashText.isDisplayed();

        basePage.scrollToElement(currentRentalAmountPaymentText);
        currentRentalAmountPaymentText.isDisplayed();
        currentRentalAmountPaymentPerWeekPrice.isDisplayed();
        currentRentalAmountPerWeekText.isDisplayed();
        currentRentalAmountSameAsCashPrice.isDisplayed();
        currentRentalAmountSameAsCashText.isDisplayed();

        basePage.scrollToElement(remainingApprovalText);
        remainingApprovalText.isDisplayed();
        remainingApprovalPaymentPerWeekPrice.isDisplayed();
        remainingApprovalPerWeekText.isDisplayed();
        remainingApprovalSameAsCashPrice.isDisplayed();
        remainingApprovalSameAsCashText.isDisplayed();

//        basePage.scrollToElement(approvalDetailsPopoverClose);
//        approvalDetailsPopoverClose.click();
//        waitForSecs(2);

//        basePage.scrollToElement(closeAnyModalPop);
//        closeAnyModalPop.click();
//        waitForSecs(2);
    }
}
