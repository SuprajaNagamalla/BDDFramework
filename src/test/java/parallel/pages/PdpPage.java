package parallel.pages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.qa.util.ElementActions;
import com.qa.util.LoggerHelper;
//import dev.failsafe.internal.util.Assert;
//import jdk.internal.org.jline.terminal.TerminalBuilder;
import com.qa.util.ReportHelper;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;

import static com.qa.factory.DriverFactory.getDriver;


public class PdpPage extends ElementActions {

    protected WebDriver driver;
    private static final Logger Log = LoggerHelper.getLogger();
    BasePage basePage = new BasePage(getDriver());

    SoftAssert softAssert = new SoftAssert();

    private static final String autoPay = "AutoPay:";
    private static final String autoPayText = "Automatically make your regularly scheduled payments.";
    private static final String pausePayments = "Pause Payments:";
    private static final String pausePaymentsText = "Life happens, and we're here to help. Return your item at any time and we'll pause your payments at no penalty. When you're ready, get the product back (or comparable item), and pick up where you left off. You don't lose the money you already paid. Note that we only store your payment history for 2 years, so you'll need to bring your last payment receipt as proof for reinstatement.";
    private static final String payOnline = "Pay Online:";
    private static final String payOnlineText = "Make Payments anywhere, anytime with the Rent-A-Center Mobile App or website.";
    private static final String payByPhone = "Pay by Phone:";
    private static final String payByPhoneText = "Call your local store and pay by phone ($1.99 convenience fee may apply).";
    private static final String payInstore = "Pay In-store:";
    private static final String payInstoreText = "Visit your local store to pay in person.";

    private static final String chooseOwnershipText = "Choose the ownership option that works best for your budget.";
    private static final String option1Header = "Option 1: Small Flexible Payments";
    public static final String SPECIAL_ORDER_DELIVERY_VALUE = "4-6 Weeks";//"1-6 Weeks";

    public static final String OPTION_1_Part1 =
            "Option 1: Small Flexible Payments";
    public static final String OPTION_1_Part2 = "At Rent-A-Center, you renew your rental agreement as you go. We work with you to get the right items at the payment amount and schedule that works for you";
    public static final String OPTION_1_Part3 = "When you make all of the payments listed in the lease agreement, it’s yours. No further obligation, no further payments.";
    public static final String OPTION_1_Part4 = "North Carolina residents must pay an end-of-lease purchase option to obtain ownership.";
    public static final String OPTION_2_part1 ="Option 2: Own it When YOU are Ready\n" +
                    "Want to own it sooner and save? You always have an Early Purchase Option ";
    public static final String OPTION_2_part2 = "that will save you money compared to paying the total cost to own in your lease. When you’re ready to own, simply pay the early purchase price and it’s yours! The sooner you choose, the more you’ll save.";
    public static final String OPTION_2_part3 = "Own it in up to 6 months for the greatest savings. In most states, we offer 6 months “same as cash” as our lowest Early Purchase Option price. Rent-A-Center may offer longer same as cash periods in certain areas. CA, HI, NJ, NY, WV and selected locally owned & operated stores offer 3-6 months same as cash depending on the product. Simply bring in the difference between what you’ve paid in rent (not incl. taxes or optional fees) and the original cash price, plus tax, before the Same as Cash period ends and you’ll own it at the lowest cost. Ask a store or review your agreement for more details.";
    public static final String OPTION_2_part4 = "Standard Early Purchase Option. After the Same as Cash period ends, you still have the option to own the merchandise early and save. Throughout the life of your agreement, the Early Purchase Option provides significant savings off of the remaining total cost to own when you choose to purchase items before the end of your agreement. This savings can be as much as 50% off of the remaining cost to own.";
    public static final String OPTION_2_part5 = "Early Purchase Option requires a payment in addition to regular rental payments.";

    public static final String OPTION_3 =
            "Option 3: Pick up where you left off\n" +
                    "If for some reason you need to pause your payments, simply return the product. When you’re ready, you can get it back (or a comparable item), and pick up where you left off.";

    public static final String GREAT_TOOLTIP_TEXT = "Fully functional, professionally restored pre-rented item. Appearance may show signs of normal, limited use. NO significant markings or damage.";

    public static final String GOOD_TOOLTIP_TEXT = "Fully functional, professionally restored pre-rented item. Appearance may show signs of moderate use, including slight markings or damage associated with previous use.";

    public static final String FAIR_TOOLTIP_TEXT = "Fully functional, professionally restored pre-rented item. Appearance will have obvious signs of moderate to excessive use, including moderate to severe markings or damage associated with previous rentals.";
    /**
     * price break down or renewal data section labels
     **/
    public static final String RENEWAL_LABEL = "Renewal Rate:";
    public static final String RENEWAL_VALUE = "$";
    public static final String PAYMENTS_LABEL = "Number of Payments:";
    public static final String TOTAL_COST_LABEL = "Total Cost To Own:";
    public static final String SAME_AS_CASH_LABEL = "Same as Cash Price:";
    public static final String DELIVERY_LABEL = "FREE Delivery to:";
    public static final String EST_DELIVERY_LABEL = "Estimated Delivery";
    public static final String EST_DELIVERY_VALUE = "1-3 days";
    public static final String GEO_POOL_DELIVERY_VALUE = "3-7 days";
    public static final String EA_ONLINE_ONLY_DELIVERY_VALUE = "2-4 weeks";

    //    public static final String EA_DELIVERY_VALUE = "1-6 weeks";
//    public static final String REDUCTION_TERMS_AND_CONDITIONS_TEXT = "‡Reduction in renewal rate may not reduce Total Cost to Own. Pricing, availability and participation may vary by location. Pricing valid at participating locations in TX.";
    public static final String REDUCTION_TERMS_AND_CONDITIONS_TEXT = "Pricing, availability and participation may vary by location. Pricing valid at participating locations in TX.";
    public  static final String RENTAL_TOOLTIP_TEXT = "Rental plan details are based on the maximum period to ownership for this product. You will pay a lower amount if you take advantage of Same as Cash or Early Purchase options. Prices do not include taxes.";
    // *** Element Locators declaration start ***

    @FindBy(xpath = "//div[contains(@class,'storePostalCode')]/strong | //div[contains(@class,'estimated-delivery')]/*/strong[1]")
    private WebElement storePostalCodeLink;

    @FindBy(xpath = "//header[contains(text(),'Item already added')]")
    private WebElement itemAlreadyAdded;

    @FindBy(xpath = "//*[local-name()='svg' and @viewBox='0 0 1024 1024']")
    private WebElement wishlistButton;

    @FindBy(xpath = "//*[local-name()='svg' and @color='#ed1c24']")
    private WebElement wishListedItem;

    @FindBy(xpath = "//button[text()='View Saved Items']")
    private WebElement viewSavedItemsCTA;
    @FindBy(xpath = "//div/p[contains(text(), 'Select size:')]")
    private WebElement selectSizeSection;

    @FindBy(css = "div.css-18z2gjh button.chakra-button")
    private List<WebElement> sizeGuideButtons;

//    @FindBy(id='PName')
//    private WebElement productName;

    @FindBy(xpath = "//*[contains(@class,'inventory-badge')]")
    private WebElement inventoryBadge;

    @FindBy(xpath = "//a[contains(text(),'Size Guide')]")  // Adjust XPath if needed
    private WebElement sizeGuideLink;

    @FindBy(xpath = "//*[normalize-space()=\"We've saved your item!\"]")
    private WebElement savedYourItemText;

    @FindBy(xpath = "//button[text()='View cart & order'] | //button[text()='View Cart & Order'] | //button[text()='VIEW CART & ORDER']")
    private WebElement viewCartAndOrder;

    @FindBy(xpath = "//header[contains(text(),'Item added to cart!')]")
    private WebElement itemAddedToCart;

    @FindBy(xpath = "//button[text()='Continue Shopping']")
    private WebElement continueShopping;

    @FindBy(css = ".start-order-title")
    private WebElement startYourOrderPageTitle;

    @FindBy(xpath = "//a[contains(text(), \"I don't have a mobile\")] | //a[text()=\"I don't have a mobile number\"]")
    private WebElement iDontHaveMobile;


    @FindBy(xpath = "//section[contains(@id,'chakra-modal')]//button[@aria-label='Close']")
    private WebElement modalClose_SavedYourItem;
    @FindBy(xpath = "//*[@aria-label='Share' and @type='button']")
    private WebElement shareButtonOnPDP;
    @FindBy(xpath = "//h2[text()='You May Also Like']")
    private WebElement youMayAlsoLikeSection;

    @FindBy(xpath = "//h1[text()='RAC Benefits Plus']")
    private WebElement benefitsPlusPageTitle;
    @FindBy(xpath = "//label[text()='Select Store For Pricing and Inventory:']")
    private WebElement selectStoreForPricingAndInventory;
    @FindBy(xpath = "//input[translate(@placeholder, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = 'zip code']")
    private WebElement zipCodeInputField;

    @FindBy(name = "postalCode")
    private WebElement postalCodeInputFieldOnStoreLocatorPopup;

    @FindBy(xpath = "//input[@name='postalCode']/following-sibling::button")
    private WebElement goButtonOnStoreLocatorPopup;

    @FindBy(xpath = "//button[text()='GO'] | //button[text()='Go']")
    private WebElement goButton;
    @FindBy(xpath = "//*[contains(@class,'chakra-modal')]//h2[text()='Store Locator']")
    private WebElement storeLocatorPopup;

    @FindBy(xpath = "//h4[text()='Frequently Asked Questions']")
    private WebElement faqSection;

    @FindBy(xpath = "//button[text()='Start Order']")
    private WebElement startOrderButton;

    @FindBy(xpath = "//button[@title='Add To Cart']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//button[contains(@class, 'cart-link')]//span[contains(@class,'chakra-badge')]")
    private WebElement cartItemCountText;

    @FindBy(xpath = "//div[@class='checkout-accordion']")
    private WebElement checkoutAccordion;
    @FindBy(xpath = "(//div[contains(@class, 'main-pdp-thumbnails-slider')]//img)")
    private WebElement productImage;

    @FindBy(xpath = "//div[contains(@class, 'product-images-wrap')]//img")
    private WebElement bundleProductImage;

    @FindBy(xpath = "//p[text()='weekly']")
    private WebElement defaultWeeklyPaymentOption;
    @FindBy(xpath = " //label//span[text()='Weekly']")
    private WebElement weeklyPaymentOption;
    @FindBy(xpath = "//label//span[text()='Bi-Weekly']")
    private WebElement biWeeklyPaymentOption;
    @FindBy(xpath = "//label//span[text()='Semi-Monthly']")
    private WebElement semiMonthlyPaymentOption;
    @FindBy(xpath = "//label//span[text()='Monthly']")
    private WebElement monthlyPaymentOption;
    @FindBy(xpath = "//h3[contains(text(),'Rental Details')]")
    private WebElement rentalDetailsHeader;

    @FindBy(xpath = "//div[contains(@class ,'pdp-rental-tooltip')]//child::*[name()='svg']")
    private WebElement rentalDetailsToolTip;

    @FindBy(xpath = "//div[contains(@id,'popover-body')]//div[contains(text(), 'Rental plan details')]")
    private WebElement rentalDetailsToolTipData;

    @FindBy(xpath = "//button[contains(@class, 'cart-popover-close-button')]")
    private WebElement rentalDetailsToolTipClose;

    @FindBy(xpath = "//*[text()='View All Inventory']")
    private WebElement viewAllInventory;
    @FindBy(xpath = "//*[text()='Need It Sooner?']")
    private WebElement needItSooner;

    @FindBy(xpath = "//div[contains(@class, 'shop-similar-item')]/strong/a[text()='Shop Similar Items']")
    private WebElement shopSimilarItemLabel;

    @FindBy(xpath = "//*[contains(@class,'priceWrapper')]//*[@class='price']")
    private WebElement rentalPriceTxt;

    @FindBy(id = "sskyplayer")
    private WebElement sundaySkyVideo;

    @FindBy(css = "[data-testid='PName']")
    private WebElement productName;

    @FindBy(xpath = "//*[@data-testid='Brand']/following-sibling::p[contains(text(), 'Model:')]")
    private WebElement productModel;
    @FindBy(xpath = "//span[contains(@class, 'chakra-badge') and contains(@class, 'inventory-badge')][text()='Out of Stock']")
    private WebElement outOfStockBadge;

    @FindBy(xpath = "//p[contains(@class, 'chakra-text')][text()='Temporarily out of stock']")
    private WebElement tempOutOfStockBadge;

    @FindBy(xpath = "//p[contains(@class, 'chakra-text') and contains(text(), 'currently out of stock')]")
    private WebElement tempOutOfStockBody;

    @FindBy(xpath = "//input[@type='email']")
    private WebElement emailNotifySection;

    @FindBy(xpath = "//*[text()=\"All set! We'll get back to you as soon as product arrives.\"]\n")
    private WebElement msgNotifySection;

    @FindBy(xpath = "//span[@class='chakra-checkbox__control css-cjxbxe']//div[@class='css-0']")
    private WebElement emailMeDealsAndOffersCheckBox;

    @FindBy(xpath = "//p[text()='Renewal Rate:']/following-sibling::div/strong")
    private WebElement renewalRateValueRentalDetails;

    @FindBy(xpath = "//p[text()='Number of Payments:']/following-sibling::div/strong")
    private WebElement noOfPaymentsValueRentalDetails;

    @FindBy(xpath = "//p[text()='Total Cost To Own:']/following-sibling::div/strong")
    private WebElement totalCostValueRentalDetails;

    @FindBy(xpath = "//p[text()='Same as Cash Price:']/following-sibling::div/strong")
    private WebElement sameAsCashPriceValueRentalDetails;

    @FindBy(xpath = "//p[contains(@class, 'chakra-text')][text()='Benefit Plus']")
    private WebElement benefitPlusBadge;

    @FindBy(xpath = "//*[@placeholder='zip code']")
    private WebElement zipCodeInput;

    @FindBy(xpath = "//strong[contains(@class, 'price')]")
    private WebElement renewalPriceBP;

    @FindBy(xpath = "//strong[contains(@class, 'price')]")
    private WebElement renewalFrequency;

    @FindBy(xpath = "//p[text()='FREE Delivery to:']/following-sibling::strong")
    private WebElement zipcodeLinkRentalDetails;

    @FindBy(xpath = "//span[contains(@class, 'chakra-badge') and (contains(text(), 'Out of Stock') or contains(text(), 'Online Only'))]")
    private WebElement inStockInventoryBadge;

    @FindBy(xpath = "//*[@data-testid='Brand']/a")
    private WebElement brandNameLink;

    @FindBy(xpath = "//p[contains(@class, 'chakra-text') and contains(text(), 'weekly')]")
    private WebElement defaultPaymentOption;

    @FindBy(xpath = "//strong[@class='price']")
    private WebElement productPrice;
    @FindBy(xpath = "//div[contains(@class,'priceWrapper')]//*[name()='svg']")
    private WebElement priceOptionChakraIcon;


    @FindBy(xpath = "//p[contains(@class, 'chakra-text') and contains(text(), 'weekly') or contains(text(), 'monthly')]")
    private WebElement durationRentalOption;

    @FindBy(xpath = "//span[contains(@class, 'inventory-badge') and text()='In Stock']")
    private WebElement inventoryAvailableLabel;

    @FindBy(xpath = "//p[contains(., 'Pre-Rented')]//span")
    private WebElement preRentedLabel;

    @FindBy(xpath = "//span[text()='Inventory Item:']/following-sibling::span")
    private WebElement inventoryNumber;

    @FindBy(xpath = "//span[text()='Store:']/following-sibling::span")
    private WebElement storeNumber;
    @FindBy(xpath = "//button[@class='slick-arrow slick-next']")
    private WebElement productArrowNextButton;
    @FindBy(xpath = "//*[contains(@class,'cart-link')]")
    private WebElement cartLinkButton;

    @FindBy(xpath = "//div[contains(@class,'pdp-detail-card-item checked-card')]")
    private WebElement productPriceBreakDownSection;

    @FindBy(xpath = "//select[contains(@class, 'chakra-select')]")
    private WebElement paymentSelector;

    /**
     * Product cost break down elements
     **/

    // Renewal Rate
    @FindBy(xpath = "//*[text()='Renewal Rate:']")
    private WebElement renewalLabel;

    @FindBy(xpath = "//*[text()='Renewal Rate:']/following-sibling::div/strong")
    private WebElement renewalRateValue;

    // Number of Payments
    @FindBy(xpath = "//*[text()='Number of Payments:']")
    private WebElement noOfPaymentsLabel;

    @FindBy(xpath = "//*[text()='Number of Payments:']/following-sibling::div/strong")
    private WebElement numberOfPaymentsValue;

    // Total Cost To Own
    @FindBy(xpath = "//*[text()='Total Cost To Own:']")
    private WebElement totalCostLabel;
    @FindBy(xpath = "//*[text()='Total Cost To Own:']/following-sibling::div/strong")
    private WebElement totalCostToOwn;

    // Same as Cash Price
    @FindBy(xpath = "//*[text()='Same as Cash Price:']")
    private WebElement sameAsCashPriceLabel;
    @FindBy(xpath = "//*[text()='Same as Cash Price:']/following-sibling::div/strong")
    private WebElement sameAsCashPrice;

    // FREE Delivery to:
    @FindBy(xpath = "//*[text()='FREE Delivery to:']")
    private WebElement deliveryLabel;
    @FindBy(xpath = "//*[text()='FREE Delivery to:']/following-sibling::strong")
    private WebElement freeDeliveryToValue;

    // Estimated Delivery
    @FindBy(xpath = "//*[contains(text(), 'Estimated Delivery')]")
    private WebElement estDeliveryLabel;
    @FindBy(xpath = "//*[contains(text(), 'Estimated Delivery')]/following-sibling::div/strong")
    private WebElement estimatedDeliveryValue;
    @FindBy(xpath = "//*[contains(@class, 'infoText ')]")
    private WebElement reductionTermsAndConditionsLabel;

    //static section
    @FindBy(xpath = "//button[@aria-expanded=\"true\"]/div[contains(text(), 'Product Details')]")
    private WebElement productDetailsSectionHeader;
    @FindBy(xpath = "//div[contains(text(), 'Product Details')]//following-sibling::*[name()='svg']")
    private WebElement productDetailsSectionHeaderExpander;

    @FindBy(xpath = "//*[@aria-expanded=\"false\"]/*[contains(text(), 'Specifications')]")
    private WebElement specificationsHeaderLabel;

    @FindBy(xpath = "//div[contains(text(), 'Specifications')]//following-sibling::*[name()='svg']")
    private WebElement specificationsHeaderClosure;

    @FindBy(xpath = "//*[@aria-expanded=\"false\"]/*[contains(text(), 'Easy Payment Options')]")
    private WebElement easyPaymentOptionsLabel;

    @FindBy(xpath = "//div[contains(text(), 'Easy Payment Options')]/following-sibling::*[name()='svg']")
    private WebElement easyPaymentOptionsClouser;

    @FindBy(xpath = "//*[@aria-expanded=\"false\"]/*[contains(text(), 'Ownership Options For You')]")
    private WebElement ownershipOptionsForYouLabel;

    @FindBy(xpath = "//div[contains(text(), 'Ownership Options For You')]/following-sibling::*[name()='svg' ]")
    private WebElement ownershipOptionsForYouClouser;

    @FindBy(xpath = "//div[contains(@class,'easyPaymentOptions-accordion-body')]")
    private WebElement easyPaymentOptionsContent;

    @FindBy(xpath = "//div[contains(@class,'ownershipOptions-accordion-body')]")
    private WebElement ownershipOptionsForYouContent;

    // FAQ header
    @FindBy(xpath = "//h4[text()='Frequently Asked Questions']")
    private WebElement faqHeader;

    //Bundle
    @FindBy(xpath = "//div[contains(@class, 'preBundleProductContainer')]//p[contains(text(), 'Bundle')]")
    private WebElement bundleLabel;

    @FindBy(xpath = "//div[contains(@class, 'preBundleProductContainer')]//p[contains(text(), 'Bundle')]/following-sibling::div/*[name()='svg']")
    private WebElement plusSymbolLabel;

    @FindBy(xpath = "//div[contains(@class, 'preBundleProductContainer')]//p[contains(text(), 'Save Items:')]")
    private WebElement saveItemsLabel;

    //Promo
    @FindBy(xpath = "//div[contains(@class, 'promoTextBanner')]")
    private WebElement promoBanner;

    @FindBy(xpath = "//*[contains(@class,'discounted-priceWrapper')]")
    private WebElement discountPrice;

    @FindBy(xpath = "(//button[text()='GO'] | //button[text()='Go'] | //button[text()='go'])[2]")
    private WebElement gobutton;

    @FindBy(xpath = "//*[@class='strike-through-price']")
    private WebElement strikeThroughProductPriceText;

    @FindBy(xpath = "//p[text()='Renewal Rate:']//parent::div//span")
    private WebElement renewalRateWasPrice;

    @FindBy(xpath = "//p[text()='Total Cost To Own:']//parent::div//span")
    private WebElement totalCostToOwnWasPrice;

    @FindBy(xpath = "//p[text()='Same as Cash Price:']//parent::div//span")
    private WebElement sameAsCashPriceOWasPrice;

    @FindBy(xpath = "//*[contains(@class, 'discount-box-wrapper')]")
    private WebElement discountPriceText;

    @FindBy(xpath = "//div[contains(@id,'popover-body')]//div")
    private WebElement rentalDetailsToolTipText;

    @FindBy(xpath = "//div[contains(@class, 'product-images-wrap')]//img")
    private WebElement productImageView;

    public WebElement getEstimatedDeliveryValue() {
        return estimatedDeliveryValue;
    }

    private String[] paymentDropdownOptions = {"weekly", "bi-weekly", "semi-monthly", "monthly"} ;
    // --- BEGIN: Missing variables from PdpPage-charan.java ---

//    @FindBy(css = ".css-1saondf")
//    private WebElement selectSizeSection;

    public List<WebElement> getEstDeliveryOnAvailableInventoryModal() {
        return estDeliveryOnAvailableInventoryModal;
    }

    public List<WebElement> getRenewalRateOnAvailableInventoryModal() {
        return renewalRateOnAvailableInventoryModal;
    }

    public List<WebElement> getNumberOfPaymentsOnAvailableInventoryModal() {
        return numberOfPaymentsOnAvailableInventoryModal;
    }

    public List<WebElement> getTotalCostToOwnOnAvailableInventoryModal() {
        return totalCostToOwnOnAvailableInventoryModal;
    }

    public List<WebElement> getSameAsCashPriceOnAvailableInventoryModal() {
        return sameAsCashPriceOnAvailableInventoryModal;
    }

    @FindBy(xpath = "//p[contains(text(),'Estimated Delivery')]//parent::div//strong")
    private List<WebElement> estDeliveryOnAvailableInventoryModal;

    @FindBy(xpath = "//p[text()='Renewal Rate:']//parent::div//strong")
    private List<WebElement> renewalRateOnAvailableInventoryModal;

    @FindBy(xpath = "//p[text()='Number of Payments:']//parent::div//strong")
    private List<WebElement> numberOfPaymentsOnAvailableInventoryModal;

    @FindBy(xpath = "//p[text()='Total Cost To Own:']//parent::div//strong")
    private List<WebElement> totalCostToOwnOnAvailableInventoryModal;

    @FindBy(xpath = "//div[contains(@class,'rental-description')]//p[text()='Same as Cash Price:']//parent::div//strong")
    private List<WebElement> sameAsCashPriceOnAvailableInventoryModal;

    @FindBy(xpath = "//button[text()='More Images']")
    private WebElement moreImages;

    @FindBy(xpath = "//p[contains(text(),'Model: ')]")
    private WebElement productModelName;

    @FindBy(xpath = "//p[@data-testid='Brand']//a")
    private WebElement productcategoryHeader;

    @FindBy(xpath = "//span[text()='Clearance']")
    private WebElement label_Clearance;

    @FindBy(css = "input[placeholder='Enter your email']")
    private WebElement enterEmailOnPDP;

    @FindBy(xpath = "//header[contains(@id,'chakra-modal--header')]//h2")
    private WebElement viewAllInventoryModalHeader;

    @FindBy(xpath = "//div[contains(@id,'chakra-modal--body')]//h2")
    private WebElement availableInventoryCount;

    @FindBy(xpath = "(//header[contains(@id,'chakra-modal--header')]//p)[1]")
    private WebElement pNameOnInventoryModal;

    @FindBy(xpath = "(//p[text()='Delivery for zip code:']//following::p)[1]")
    private WebElement deliveryZipCodeOnInvModal;

    @FindBy(xpath = "//nav[@aria-label='breadcrumb']")
    private WebElement breadCrumbOnPage;

    @FindBy(xpath = "//nav[@aria-label='breadcrumb']//li[3]")
    private WebElement breadCrumbSubCategory;

    @FindBy(xpath = "//nav[@aria-label='breadcrumb']//li[2]")
    private WebElement breadCrumbSubCategoryTiersOrJewellery;

    @FindBy(xpath = "//div[contains(@class,'promoTextBanner')]//p")
    private WebElement promoBannerOnPage;

    @FindBy(xpath = "//p[contains(text(),'Estimated Delivery')]//parent::div//strong")
    private WebElement estDeliveryDateOnPDP;

    @FindBy(xpath = "//p[text()='FREE Delivery to:']//parent::div//strong")
    private WebElement zipCodeOnPDP;

    @FindBy(xpath = "//h2[text()='Related Categories']")
    private WebElement relatedCategories;

    @FindBy(xpath = "//button[contains(@class, 'chakra-modal__close-btn')]/*[name()='svg']")
    private WebElement getSignUpPopupWindowClose;

    @FindBy(xpath = "//p/*[contains(@class, 'saveMoreBtn')]")
    private WebElement saveMoreBtn;

    @FindBy(xpath = "//p/*[contains(@class, 'saveMoreBtn')]/following-sibling::*")
    private WebElement usedProductCondition; //Great or Good or Fair text

    @FindBy(xpath = "//*[contains(@class, 'discounted-promo')]/*[contains(@class, 'content-holder')]/img")
    private WebElement preUsedProductImage;

    @FindBy(xpath = "//*[contains(@class, 'discounted-promo')]/*[contains(@class, 'content-holder')]/img/following-sibling::p")
    private WebElement preUsedProductImageGreatGoodFairText; //Our 100% Worry-Free Guarantee -- Great Image text

    @FindBy(xpath = "//*[contains(@class, 'info-content')]")
    private WebElement preUsedProductInfoContent;

    @FindBy(xpath = "//a[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'learn more')]")
    private WebElement learnMoreLink;

    @FindBy(xpath = "//button[contains(text(), 'Great')]")
    private WebElement usedProductConditionGreatButton;

    @FindBy(xpath = "//button[contains(text(), 'Good')]")
    private WebElement usedProductConditionGoodButton;

    @FindBy(xpath = "//button[contains(text(), 'Fair')]")
    private WebElement usedProductConditionFairButton;

    @FindBy(xpath = "//button[contains(text(), 'Great')]/*[contains(@class, 'pdp-rental-tooltip')]")
    private WebElement usedProductConditionGreatButtonInfo;

    @FindBy(xpath = "//button[contains(text(), 'Good')]/*[contains(@class, 'pdp-rental-tooltip')]")
    private WebElement usedProductConditionGoodButtonInfo;

    @FindBy(xpath = "//button[contains(text(), 'Fair')]/*[contains(@class, 'pdp-rental-tooltip')]")
    private WebElement usedProductConditionFairButtonInfo;

    @FindBy(xpath = "//button[contains(@class, 'cart-popover-close-button')]")
    private WebElement usedProductConditionButtonInfoClose;

    @FindBy(xpath = "//div[contains(@id,'chakra-modal--body')]//h2[contains(text(),'Sign up for')]")
    private WebElement signUpPopupWindow;
    @FindBy(xpath = "//a[contains(text(), \"I don't have a mobile\")]")
    private WebElement doNotHaveMobNumLink;

    @FindBy(xpath = "//section[contains(@id,'chakra-modal')]//button[@aria-label='Close']")
    private WebElement itemCartCloseButton;

    @FindBy(xpath = "//div[@data-testid='product-info-container']//*[contains(text(),'Benefit Plus')]")
    private WebElement benefitPlusProductInfoTitle;

    // --- END: Missing variables from PdpPage-charan.java
    public WebElement getProductName() {
        return productName;
    }

    public WebElement getBundleProductImage() {
        return bundleProductImage;
    }

    public WebElement getWishListedItem() {
        return wishListedItem;
    }

    private WebElement getPDPList(String pdpList) {
        return driver.findElement(By.xpath("//nav[@aria-label='breadcrumb']//li//span[text()='" + pdpList + "']"));
    }

    private WebElement getRenewalFrequencyText(String frequency) {
        return driver.findElement(By.xpath("//p[contains(@class, 'chakra-text') and (text())='" + frequency + "']"));
    }

    private WebElement getDurationElement(String duration) {
        return driver.findElement(By.xpath("//span[normalize-space()='" + duration + "']"));
    }

    private WebElement getSectionElement(String sectionName) {
        return driver.findElement(By.xpath("//div[normalize-space()='" + sectionName + "']"));

    }

    @FindBy(xpath = "//div[@class='slick-slider main-pdp-thumbnails-slider slick-initialized']//div[@class='slick-track']//div[contains(@class,'slick-slide')]")
    private List<WebElement> multipleImageCarousal;

    private WebElement getPriceOptionTxt(String priceOption) {
        return driver.findElement(By.xpath("//div[contains(@class,'priceWrapper')]//*[text()='" + priceOption + "']"));
    }

    private WebElement getRentalPaymentOptions(String rentalPaymentOptions) {
        return driver.findElement(By.xpath("//span[contains(@class,'chakra-radio__label') and  text()='" + rentalPaymentOptions + "']"));
    }

    private WebElement getRentalPaymentOptionRadioButton(String rentalRaidoButton) {
        return driver.findElement(By.xpath("//*[text()='" + rentalRaidoButton + "']//preceding-sibling::span[contains(@class,'chakra-radio__control')]"));
    }

    private WebElement getItemCountInCart(String numberOfItems) {
        return driver.findElement(By.xpath("//*[contains(@class,'cart-link')]//*[contains(@class,'chakra-badge') and text()='" + numberOfItems + "']"));
    }

    public WebElement getProductCondition(String productCondition) {
        return driver.findElement(By.xpath("//div[contains(@class, 'pdp-detail-card-item checked-card')]//label[contains(@class, 'chakra-checkbox')]/input/following::span[contains(text(), '" + productCondition + "')]"));
    }

    private WebElement getProductConditionOption(String productCondition) {
        // return driver.findElement(By.xpath("//div[contains(@class, 'pdp-detail-card-item checked-card')]//label[contains(@class, 'chakra-checkbox')]/input/following::span/*/*[name()='svg']"));
        return driver.findElement(By.xpath("//div[contains(@class, 'pdp-detail-card-item checked-card')]//label[contains(@class, 'chakra-checkbox')]/span/p"));
    }

    public WebElement getProductModel() {
        return productModel;
    }

    public WebElement getBundleProductNameByIndex(int index) {
        return driver.findElement(By.xpath("(//*[contains(@class, 'product-title')])[" + index + "]"));
    }
    private WebElement getBrandNameLinkPDP(String brandName) {
        return driver.findElement(By.xpath("//a[contains(@class, 'chakra-link') and text()='" + brandName + "']"));
    }
    public void clickOnBundleProductsInventoryByIndex(int index) {
        WebElement viewInventory = driver.findElement(By.xpath("(//a[contains(@class, 'viewAllInventory')])[" + index + "]"));
        basePage.jsClick(viewInventory);
        waitForSecs(3);
    }

    public WebElement getCartItemCountText() {
        return cartItemCountText;
    }

    public WebElement getProductModelByIndex(int index) {
        return driver.findElement(By.xpath("(//*[@data-testid='Brand']/following-sibling::p[contains(text(), 'Model:')])[" + index + "]"));
    }

    // *** Element Locators declaration End ***

    // Constructor to initialize elements using Page Factory
    public PdpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // *** page action methods on elements ***

    public void clickOnWishlistButton() {
        waitForSecs(4);
        basePage.scrollToElement(wishlistButton);
        wishlistButton.click();
        waitForSecs(4);
    }

    public boolean itemIsSavedToCart() {
        if (viewSavedItemsCTA.isDisplayed() &&
                savedYourItemText.isDisplayed() &&
                continueShopping.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean isSelectSizeGuideSectionDisplayed() {
        basePage.waitForElementVisible(selectSizeSection);
        return selectSizeSection.isDisplayed();
    }

    public boolean verifySizeButtonsDisplayed(List<String> expectedSizes) {

        int i = 0;
        for (String size : expectedSizes) {
           /* boolean isSizePresent = sizeButtons.stream().anyMatch(btn -> btn.getText().trim().equals(size));
            if (!isSizePresent) {
                return false;  // If any size is missing, return false
            }*/
            if (!sizeGuideButtons.get(i).getText().equalsIgnoreCase(size)) {
                return false;
            }
            ++i;
        }
        return true; // All sizes found

    }

    public boolean isEachNumberButtonClickableInSizeGuide() {
        boolean isButtonClickable = true;
        for (WebElement button : sizeGuideButtons) {
            if (button.isDisplayed() && button.isEnabled()) { // Check if button is clickable
                button.click();
                waitForSecs(2);
            } else {
                return false;
            }
        }
        return isButtonClickable;
    }

    public boolean isSizeGuidePDFOpenedInNewTab() {
        String pdfUrl = sizeGuideLink.getDomAttribute("href"); // Get the actual PDF link
        System.out.println("PDF URL: " + pdfUrl);
        return pdfUrl.endsWith(".pdf");
    }


    public void closeSavedYourItemModal() {
        modalClose_SavedYourItem.click();
        waitForSecs(2);
    }

    public boolean shareOptionDisplayedOnPDP() {
        if (shareButtonOnPDP.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean youMayAlsoLikeDisplayedOnPDP() {
        basePage.scrollToElement(youMayAlsoLikeSection);
        if (youMayAlsoLikeSection.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean verifyProductLabelDisplayedRight(String productNumber, String labelName) {
        String modifiedUrl = "https://rentacenter-development.mobify-storefront.com/p/" + productNumber;
        driver.get(modifiedUrl);
        waitForSecs(4);
        String actLabel = inventoryBadge.getText();
        System.out.println("inventoryBadge---status---:" + inventoryBadge.getText());
        if (actLabel.equalsIgnoreCase(labelName))
            return true;
        else
            return false;
    }

    public boolean benefitsPlusPageIsDisplayed() {
        if (benefitsPlusPageTitle.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean selectStoreTextDisplayed() {
        if (selectStoreForPricingAndInventory.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean isZipCodeInputFieldIsDisplayed() {
        basePage.waitForElementVisible(zipCodeInputField);
        return zipCodeInputField.isDisplayed();
    }

    public boolean isStoreLocatorPopupIsDisplayed() {
        basePage.waitForElementVisible(storeLocatorPopup);
        return storeLocatorPopup.isDisplayed();
    }

    public boolean isAddToCartButtonIsDisplayed() {
        basePage.waitForElementVisible(addToCartButton);
        return addToCartButton.isDisplayed();
    }

    public void clickAddToCartButton() {
        basePage.waitForElementVisible(addToCartButton);
        basePage.scrollToElement(addToCartButton);
        addToCartButton.click();
    }

    public boolean isStartOrderButtonIsDisplayed() {
        basePage.waitForElementVisible(startOrderButton);
        return startOrderButton.isDisplayed();
    }

    public void clickOnStartOrderButton() {
        basePage.scrollToElement(startOrderButton);
        waitForSecs(2);
        basePage.waitForElementToBeClickable(startOrderButton);
        basePage.click(startOrderButton);
        waitForSecs(3);
        Log.info("Clicked on Start Order Button");
    }

    public boolean isPDPDisplayed(String pdpList) {
        return getPDPList(pdpList).isDisplayed();
    }

    public boolean isProductDetailsTextDisplayed(String actText) {
        return basePage.isTextDisplayed(actText, driver);
    }

    public boolean isProductImageIsDisplayed() {
        waitForSecs(5);
        boolean status =false;
//        basePage.waitForElementVisible(productImage);
        if(!multipleImageCarousal.isEmpty()) {
            basePage.scrollToElement(multipleImageCarousal.get(0));
            if (multipleImageCarousal.size() > 5)
                status = productImage.isDisplayed() && isMultipleImagesAreDisplayed() && moreImages.isDisplayed();
            else
                status = productImage.isDisplayed() && isMultipleImagesAreDisplayed();
        }else{
            productImageView.isDisplayed();
            status =true;
        }
        return  status;
    }

    public boolean verifyCheckoutAccordionIsDisplayed() {
        basePage.waitForElementVisible(checkoutAccordion);
        return checkoutAccordion.isDisplayed();
    }

    public void enterZipcodeAndClickOnGo(String zipCode) {
        zipCodeInputField.sendKeys(zipCode);
        waitForSecs(3);
        basePage.jsClick(goButton);
        waitForSecs(2);
    }

    public boolean faqSectionDisplayed() {
        return faqSection.isDisplayed();
    }

    public boolean itemIsAddedToCart() {

        if (itemAddedToCart.isDisplayed() &&
                viewCartAndOrder.isDisplayed() &&
                continueShopping.isDisplayed())
            return true;
        else
            return false;
    }

    public boolean startYourOrderPageDisplayed() {
        boolean status = false;
        try {
            //basePage.waitForElementVisible(startYourOrderPageTitle);
            List<WebElement> startYourOrderPageTitleElement = driver.findElements(By.cssSelector(".start-order-title"));
            System.out.println("startYourOrderPageTitleElement size::" + startYourOrderPageTitleElement.size());
            System.out.println("startYourOrderPageTitleElement empty::" + startYourOrderPageTitleElement.isEmpty());
            if (startYourOrderPageTitleElement.isEmpty()) {
                System.out.println("startYourOrderPageTitle element is not displayed");
                basePage.scrollToElement(iDontHaveMobile);
                iDontHaveMobile.click();
                waitForSecs(6);
                basePage.scrollToElement(startYourOrderPageTitle);
                status = startYourOrderPageTitle.isDisplayed();
                basePage.navigateBack();
                waitForSecs(6);
            }
        } catch (NoSuchElementException e) {
            System.out.println("startYourOrderPageTitle element is not displayed");
            iDontHaveMobile.click();
            waitForSecs(6);
            status = startYourOrderPageTitle.isDisplayed();
            basePage.navigateBack();
            waitForSecs(6);
        }
        return status;
    }

    public void clickWeeklyPaymentOptionOnPDP() {
        defaultWeeklyPaymentOption.click();
    }

    public boolean allPaymentOptionsAreDispalyedOnPDP() {
        waitForSecs(3);
        return (weeklyPaymentOption.isDisplayed() && biWeeklyPaymentOption.isDisplayed() && semiMonthlyPaymentOption.isDisplayed() && monthlyPaymentOption.isDisplayed());

    }

    public boolean sectionsWithNoValuesNotDisplayed() {
        return (!rentalDetailsHeader.isDisplayed() && !viewAllInventory.isDisplayed() && !needItSooner.isDisplayed());
    }

    public boolean sundaySkyVideoIsDisplayed() {
        basePage.waitForElementVisible(sundaySkyVideo);
        return sundaySkyVideo.isDisplayed();
    }

    public boolean verifyProductLabelDisplayedRight(String labelName) {
        waitForSecs(4);
        String actLabel = inventoryBadge.getText();
        if (actLabel.equalsIgnoreCase(labelName))
            return true;
        else
            return false;
    }

    public boolean productNameDisplayed() {
        return (productName.isDisplayed());
    }

    public boolean isOutOfStockProductDisplayed() {
        return basePage.isElementDisplayed(outOfStockBadge);
    }


    public WebElement getNumberOfPaymentsValue() {
        return numberOfPaymentsValue;
    }


    public WebElement getTotalCostToOwnValue() {
        return totalCostToOwn;
    }

    public WebElement getSameAsCashPriceValue() {
        return sameAsCashPrice;
    }


//charan code
//    public boolean isOutOfStockProductDisplayed() {
//        return basePage.isElementDisplayed(outOfStockBadge)&&!productPrice.getText().matches(".*\\d.*");
//    }

    public boolean isStaticContentTempOOSBlockIsDisplayed() {
        return basePage.isElementDisplayed(tempOutOfStockBadge);
    }

    public boolean isStaticContentTempOOSBlockBodyIsDisplayed() {
        return basePage.isElementDisplayed(tempOutOfStockBadge);
    }

    public void enterEmailAddressInNotifySection(String emailAddress) {
        emailNotifySection.sendKeys(emailAddress);
    }

    public void clickNotifyButton(String buttonName) {
        basePage.clickButtonByName(buttonName);
        waitForSecs(2);
    }

    public boolean isSystemAcceptsEmailAddressNotifySection() {
        //basePage.isTextDisplayed("All set! We'll get back to you as soon as product arrives.",driver);
        basePage.waitForElementVisible(msgNotifySection);
        return basePage.isElementDisplayed(msgNotifySection);

    }

    public boolean isEmailMeDealsAndOffersCheckBoxSelected() {
        return basePage.isElementDisplayed(emailMeDealsAndOffersCheckBox);
    }

    public boolean isRentalDetailsRenewalRateDisplayed(String expVal) {
        String actVal = renewalRateValueRentalDetails.getText().trim();
        return actVal.contains(expVal);
    }

    public boolean isRentalDetailsNoOfPaymentsDisplayed(String expVal) {
        String actVal = noOfPaymentsValueRentalDetails.getText().trim();
        return actVal.contains(expVal);
    }

    public boolean isRentalDetailsTotalCostDisplayed(String expVal) {
        String actVal = totalCostValueRentalDetails.getText().trim();
        return actVal.contains(expVal);
    }

    public boolean isRentalDetailsSameAsCashPriceDisplayed(String expVal) {
        String actVal = sameAsCashPriceValueRentalDetails.getText().trim();
        return actVal.contains(expVal);
    }

    public boolean isBenefitsPlusProductDisplayed() {

        return basePage.isElementDisplayed(benefitPlusBadge);

    }


    public void enterZipCode(String zipCode) {
        zipCodeInput.sendKeys(zipCode);
        waitForSecs(2);
    }

    public void clickOnGOButton() {
        goButton.click();
        waitForSecs(10);
    }

    public boolean isRenewalPriceDisplayed(String expPrice) {
        String actVal = renewalPriceBP.getText().trim();
        return actVal.contains(expPrice);
    }

    public boolean isRenewalFrequencyDisplayed(String frequency) {
        return basePage.isElementDisplayed(getRenewalFrequencyText(frequency));
    }

    public void clickZipcodeHyperlinkRentalDetailsSection() {
        basePage.waitForElementToBeClickable(zipcodeLinkRentalDetails);
        zipcodeLinkRentalDetails.click();
    }

    public boolean isStoreLocatorPopupDisplayed() {
        return basePage.isElementDisplayed(storeLocatorPopup);
    }

    public boolean isNewStoreSelected() {
        return basePage.isElementDisplayed(inStockInventoryBadge);
    }

    public boolean isBrandNameDisplayedAsHyperLink() {
        basePage.scrollToElement(brandNameLink);
        return basePage.isElementDisplayed(brandNameLink);
    }

    public boolean isDefaultPaymentOptionDisplayed(String paymentOption) {
        return defaultPaymentOption.isDisplayed();
    }

    public String getProductPriceDisplayed() {
        return productPrice.getText().trim();
    }

    public double getPriceValue(String duration) {
        //getDurationElement(duration).click();
//        priceOptionChakraIcon.click();
        waitForSecs(3);
        return Double.parseDouble(getProductPriceDisplayed().replace("$", ""));
    }

    public void clickDurationRentalOption() {
        durationRentalOption.click();
    }

    public boolean isProductInventoryAvailable() {
        return basePage.isElementDisplayed(inventoryAvailableLabel);
    }

    public String getPreRentedLabel() {
        return preRentedLabel.getText().trim();
    }

    public void selectSection(String sectionName) {
        getSectionElement(sectionName).click();
    }

    public String getInventoryNumber() {
        return inventoryNumber.getText().trim();
    }

    public String getStoreNumber() {
        return storeNumber.getText().trim();
    }

    public void clickStartOrderButton(String buttonName) {
        basePage.clickButtonByName(buttonName);
    }

    public boolean isMultipleImagesAreDisplayed() {
        waitForSecs(3);
        return multipleImageCarousal.size() > 1;
    }

    public boolean isNavigatedToMultipleImages() {
        try {
            Log.info("Multiple images are displayed in the carousel");
            for (int i = 1; i < multipleImageCarousal.size() - 1; i++) {
                waitForSecs(2);
                productArrowNextButton.click();
                waitForSecs(3);
                driver.findElement(By.xpath("//div[@class='slick-slider main-pdp-thumbnails-slider slick-initialized']//div[@data-index='" + i + "' and @class='slick-slide slick-active slick-current']")).isDisplayed();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void clickOnPriceOptionChakraIcon() {
        waitForSecs(2);
        basePage.waitForElementToBeClickable(priceOptionChakraIcon);
        basePage.click(priceOptionChakraIcon);
        Log.info("Clicked on price option expanded icon button");
    }

    public boolean selectedRentalPaymentOption(String priceOption) {
        return getPriceOptionTxt(priceOption).isDisplayed();
    }

    public boolean isRentalPaymentOptionsDisplayed(String rentalPaymentOptions) {
        return getRentalPaymentOptions(rentalPaymentOptions).isDisplayed();
    }

    public void clickOnRentalPaymentOptionRadioButton(String rentalRaidoButton) {
        getRentalPaymentOptionRadioButton(rentalRaidoButton).click();
        Log.info("Clicked on rental payment option radio button");
    }

    public String getRentalPaymentPrice() {
        return rentalPriceTxt.getText();
    }

    public boolean getNumberOfItemsInCart(String numberOfItems) {
        waitForSecs(3);
        return getItemCountInCart(numberOfItems).isDisplayed();
    }

    public void clickOnCartLinkButton() {
        basePage.waitForElementToBeClickable(cartLinkButton);
        basePage.click(cartLinkButton);
    }

    public void verifyProductDetailsSectionIsDisplayed() {
        basePage.scrollToElement(productDetailsSectionHeader);
        basePage.waitForElementVisible(productDetailsSectionHeader);
        basePage.waitForElementVisible(productDetailsSectionHeaderExpander);
    }

    public void verifyEasyPaymentOptionsSectionIsDisplayed() {
        Log.info("---------verifyEasyPaymentOptionsSectionIsDisplayed method called----------");
        basePage.waitForElementVisible(easyPaymentOptionsLabel);
        basePage.waitForElementVisible(easyPaymentOptionsClouser);
        basePage.scrollToElement(easyPaymentOptionsClouser);
        easyPaymentOptionsClouser.click();
        waitForSecs(3);
        verifyEasyPaymentOptionsContent();
        basePage.scrollToElement(easyPaymentOptionsClouser);
        easyPaymentOptionsClouser.click();
    }

    public void verifyOwnershipOptionsForYouSectionIsDisplayed() {
        Log.info("---------verifyOwnershipOptionsForYouSectionIsDisplayed method called----------");
        basePage.waitForElementVisible(ownershipOptionsForYouLabel);
        basePage.waitForElementVisible(ownershipOptionsForYouClouser);
        basePage.scrollToElement(ownershipOptionsForYouClouser);
        ownershipOptionsForYouClouser.click();
        waitForSecs(3);
        verifyOwnershipOptionsForYouContent();
    }

    public void verifyEasyPaymentOptionsContent() {
        Log.info("---------verifyEasyPaymentOptionsContent method called----------");
        basePage.scrollToElement(easyPaymentOptionsContent);
        System.out.println("easyPaymentOptionsContent::\n" + easyPaymentOptionsContent.getText());
        // Create a SoftAssert object
        //SoftAssert softAssert = new SoftAssert();

        // Perform assertions using SoftAssert
        softAssert.assertEquals(basePage.isTextDisplayed(autoPay), true, autoPay + "text is not displayed");
        softAssert.assertEquals(basePage.isTextDisplayed(autoPayText), true, autoPayText + " is not displayed");

        softAssert.assertEquals(basePage.isTextDisplayed(pausePayments), true, "pausePayments matching text not displayed::"+pausePayments);
//        String pausePaymentsTextWithConcat = basePage.buildSafeXpathText(pausePaymentsText);
        softAssert.assertEquals(basePage.isTextDisplayedWithSpecialChars(pausePaymentsText), true,"pausePaymentsText matching text not displayed::"+pausePaymentsText);

        softAssert.assertEquals(basePage.isTextDisplayed(payOnline), true,"payOnline matching text not displayed::"+payOnline);
        softAssert.assertEquals(basePage.isTextDisplayed(payOnlineText), true, "payOnlineText matching text not displayed::"+payOnlineText);

        softAssert.assertEquals(basePage.isTextDisplayed(payByPhone), true, "payByPhone matching text not displayed::"+payOnlineText);
        softAssert.assertEquals(basePage.isTextDisplayed(payByPhoneText), true, "payByPhoneText matching text not displayed::"+payOnlineText);

        softAssert.assertEquals(basePage.isTextDisplayed(payInstore), true, "payInstore text not displayed::"+payInstore);
        softAssert.assertEquals(basePage.isTextDisplayed(payInstoreText), true, "payInstoreText text not displayed::"+payInstoreText);

        // At the end, call assertAll() to collect all failed assertions
        //softAssert.assertAll();
    }

    public void verifyOwnershipOptionsForYouContent() {

        basePage.scrollToElement(ownershipOptionsForYouContent);

        // Create a SoftAssert object

        System.out.println("ownershipOptionsForYouContent::" + ownershipOptionsForYouContent.getText());
        // Perform assertions using SoftAssert
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(chooseOwnershipText), "text is not displayed in Ownership Options For You Content::" + chooseOwnershipText);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_1_Part1), "text is not displayed in Ownership Options::"+OPTION_1_Part1);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_1_Part2), "text is not displayed in Ownership Options::"+OPTION_1_Part2);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_1_Part3), "text is not displayed in Ownership Options::"+OPTION_1_Part3);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_1_Part4), "text is not displayed in Ownership Options::"+OPTION_1_Part4);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_2_part1), "text is not displayed in Ownership Options::"+OPTION_2_part1);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_2_part2), "text is not displayed in Ownership Options::"+OPTION_2_part2);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_2_part3), "text is not displayed in Ownership Options::"+OPTION_2_part3);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_2_part4), "text is not displayed in Ownership Options::"+OPTION_2_part4);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_2_part5), "text is not displayed in Ownership Options::"+OPTION_2_part5);
        softAssert.assertTrue(ownershipOptionsForYouContent.getText().contains(OPTION_3), "text is not displayed in Ownership Options::"+OPTION_3);

//        softAssert.assertAll();
    }

    public void validateFAQsUsingMap(Map<String, String> faqMap) {
        for (Map.Entry<String, String> entry : faqMap.entrySet()) {
            String question = entry.getKey();
            String expectedAnswer = entry.getValue();

            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Faq question:  " + question);
            ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + "Answer: " + expectedAnswer);

            String safeXpathQuestion = basePage.buildSafeXpathText(question);

            // XPath to find the question button and click it
            String questionXpath = "//button//div[text()=" + safeXpathQuestion + "]";
            WebElement questionElement = driver.findElement(By.xpath(questionXpath));
            basePage.scrollToElement(questionElement);
            questionElement.click();
            waitForSecs(3);

            // XPath to find the answer panel following the button
            String answerXpath = "//button//div[text()=" + safeXpathQuestion + "]/ancestor::button/following-sibling::div//div";
            WebElement answerElement = driver.findElement(By.xpath(answerXpath));

            String actualAnswer = answerElement.getText().trim();

            System.out.println("❓ Question: " + safeXpathQuestion);
            System.out.println("✅ Expected Answer: " + expectedAnswer);
            System.out.println("📄 Actual Answer: " + actualAnswer);

            if (!actualAnswer.equals(expectedAnswer)) {
                throw new AssertionError("Mismatch for question: " + question);
            }
            //restoring to previous state
            questionElement.click();
            waitForSecs(2);
            basePage.scrollToElement(productName);

        }
    }

    public void FAQValidationWithHashMap() {
        basePage.scrollToElement(faqHeader);
        faqHeader.isDisplayed();

        Map<String, String> faqMap = new HashMap<>();
        faqMap.put(
                "Do I need a good credit score to rent from Rent-A-Center?",
                "No. We consider many different factors in reviewing your application and regularly approve customers with less than perfect credit history."
        );
        faqMap.put(
                "Does Rent-A-Center report to the credit bureau?",
                "Rent-A-Center does not report your payment history to the credit bureaus. We know life happens so, we allow you to return your products at any time without further obligation."
        );

        faqMap.put(
                "What information will I need to rent from Rent-A-Center?",
                "All you need is some standard information:\n" +
                        "Personal Info: Name, date of birth and contact info\n" +
                        "Income Info: Verifiable source of income\n" +
                        "Residence Info: Contact info for your landlord/mortgage company\n" +
                        "2-4 References: At least two references should be relatives with separate addresses"
        );

        faqMap.put(
                "What is Rent-A-Center's return policy?",
                "At any time, you can contact your store to schedule a return of your product or return the merchandise in person and pause your payments. When you're ready, you can come back, reinstate your agreement to get the same or comparable item, and pick up your payments right where you left off."
        );

        faqMap.put(
                "How much do I need to start an agreement at Rent-A-Center?",
                "In most states, $10 is all you need to get started at Rent-A-Center.* Starting amounts higher in some markets. Customers in NY, HI will also pay a processing fee of $10 ($18 in CA). Contact your store for details."
        );
        validateFAQsUsingMap(faqMap);
    }

    public void priceBreakdownOfProduct() {
        getProductPriceDisplayed();
    }

    public void validatePaymentDetailsStaticData() {

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + rentalDetailsHeader.getText() + " Label is Displayed");
        validateRentalToolTipFunctionality();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + rentalDetailsToolTipData.getText() + " Text is Displayed");


        assertContainsText(renewalLabel, RENEWAL_LABEL);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + RENEWAL_LABEL + " Text is Displayed");
        //assertText("//p[text()='Renewal Rate:']/following-sibling::div/strong", RENEWAL_VALUE);

        assertContainsText(noOfPaymentsLabel, PAYMENTS_LABEL);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + PAYMENTS_LABEL + " Text is Displayed");
        // assertText("//p[text()='Number of Payments:']/following-sibling::div/strong", );


        assertContainsText(totalCostLabel, TOTAL_COST_LABEL);
//        assertText("//p[text()='Total Cost To Own:']/following-sibling::div/strong", COST_VALUE);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + TOTAL_COST_LABEL + " Text is Displayed");

        assertContainsText(sameAsCashPriceLabel, SAME_AS_CASH_LABEL);
//        assertText("//p[text()='Same as Cash Price:']/following-sibling::div/strong", CASH_VALUE);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + SAME_AS_CASH_LABEL + " Text is Displayed");

        assertContainsText(deliveryLabel, DELIVERY_LABEL);
//        assertText("//p[text()='FREE Delivery to:']/following-sibling::strong", DELIVERY_ZIP);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + DELIVERY_LABEL + " Text is Displayed");

        assertContainsText(estDeliveryLabel, EST_DELIVERY_LABEL);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + EST_DELIVERY_LABEL + " Text is Displayed");
        basePage.isTextDisplayed(EST_DELIVERY_VALUE);
//        assertText("//p[text()='Estimated Delivery:']/following-sibling::div/strong", EST_DELIVERY_VALUE);

        viewAllInventory.isDisplayed();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + viewAllInventory.getText() + " Text is Displayed");

        basePage.isTextDisplayed(REDUCTION_TERMS_AND_CONDITIONS_TEXT);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + REDUCTION_TERMS_AND_CONDITIONS_TEXT + " Text is Displayed");
    }

    public void validateProductPriceRentalPaymentCalculations(String paymentFrequency) {
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Rental Payment Calculations:");
        //int totalCostOfItem = totalCostToOwn.getText().trim().split("$")[1].;
        System.out.println("totalCostToOwn from UI::"+totalCostToOwn.getText());
        double totalCostOfItem = Double.parseDouble(totalCostToOwn.getText().trim().replaceAll("[^\\d.]", ""));


        System.out.println("renewalRateValue from UI::" + renewalRateValue.getText()); //$19.99 per week*
        String valueWithFreq = renewalRateValue.getText().trim().split("\\$")[1]; //19.99 per week*
        double renewalRate = Double.parseDouble(valueWithFreq.trim().split(" ")[0]); //19.99
        double numericValueRenewalRate = Double.parseDouble(renewalRateValue.getText().trim().replaceAll("[^\\d.]", "")); //19.99
        System.out.println("numericValueRenewalRate::" + numericValueRenewalRate);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + " Renewal Rate Value with Payment Frequency::" + valueWithFreq);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + " No. of Payments :" + numberOfPaymentsValue.getText());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + " Total cost to own :" + totalCostToOwn.getText());
        double  numberOfPaymentsValueFromUI = Double.parseDouble(numberOfPaymentsValue.getText());
        double weeklyPayTotalCost = 0.00;
        double biweeklyPayTotalCost = 0.00;
        double semiMonthlyPayTotalCost = 0.00;
        double monthlyPayTotalCost = 0.00;
        double NoOfPaymentsByCal = totalCostOfItem / numericValueRenewalRate;

        System.out.println("NoOfPaymentsByCal ::" + NoOfPaymentsByCal);
        int noOfpayments = 0;

        noOfpayments = (NoOfPaymentsByCal - Math.floor(NoOfPaymentsByCal) > 0.1) ?
                (int) Math.ceil(NoOfPaymentsByCal) : (int) Math.floor(NoOfPaymentsByCal);

        double weeklyPayTotalCostCal = NoOfPaymentsByCal * renewalRate;
        BigDecimal bd = new BigDecimal(weeklyPayTotalCostCal);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        weeklyPayTotalCost = bd.doubleValue();
        // Format the number as currency (without currency symbol)
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);


        System.out.println("calculated " + paymentFrequency + " PayTotalCost::" + weeklyPayTotalCost);
        if(NoOfPaymentsByCal==numberOfPaymentsValueFromUI) {
            assertContainsText(numberOfPaymentsValue, formatter.format(noOfpayments));
        }else{
            System.out.println("numberOfPaymentsValueFromUI::"+numberOfPaymentsValueFromUI);
            System.out.println("NoOfPaymentsByCal::"+NoOfPaymentsByCal);
        }
        assertContainsText(renewalRateValue, "$" + formatter.format(renewalRate));
        //assertContainsText( renewalRateValue,"$"+renewalRate+" per week");

        String formattedtotalCostToOwnPrice = formatter.format(weeklyPayTotalCost);
        System.out.println("Formatted price: " + formattedtotalCostToOwnPrice);

        assertContainsText(totalCostToOwn, "$" + formattedtotalCostToOwnPrice);

    }


    private void assertContainsText(WebElement element, String expectedText) {
        //WebElement element = driver.findElement(By.xpath(xpath));
        String actual = element.getText().trim();
        System.out.println("Verifying: " + expectedText + " → Found: " + actual);
        if (!actual.contains(expectedText)) {
            throw new AssertionError("Mismatch!\nExpected: " + expectedText + "\nActual: " + actual);
        }
    }

    public void validateProductPriceBreakdownData_Old(String paymentFrequency) {
        //to select payment frequency
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + " Rental Payment Frequency selected: " + paymentFrequency);
        if (!paymentFrequency.equalsIgnoreCase("weekly")) {
            priceOptionChakraIcon.click();
            waitForSecs(2);
            switch (paymentFrequency.toLowerCase()) {
                case "bi-weekly":
                    biWeeklyPaymentOption.click();
                    waitForSecs(2);
                    break;
                case "semi-monthly":
                    semiMonthlyPaymentOption.click();
                    waitForSecs(2);
                    break;
                case "monthly":
                    monthlyPaymentOption.click();
                    waitForSecs(2);
                    break;
                default:
                    System.out.println("There is no matching value found for payment frequency, user entered payment frequency value::" + paymentFrequency);
            }
        }

        validateProductPriceRentalPaymentCalculations(paymentFrequency);
    }

    public void verifyBundleData() {
        softAssert.assertTrue(bundleLabel.isDisplayed(), "bundleLabel element is not displayed" + bundleLabel.getText());
        softAssert.assertTrue(plusSymbolLabel.isDisplayed(), "plusSymbolLabel element is not displayed" + plusSymbolLabel.getText());
        softAssert.assertTrue(saveItemsLabel.isDisplayed(), "saveItemsLabel element is not displayed" + saveItemsLabel.getText());
        List<WebElement> productNameElements = driver.findElements(By.xpath("//h5[contains(@class, 'product-title')]"));
        List<WebElement> prodImageElements = driver.findElements(By.xpath("//*[contains(@class, 'prod-img-wrap')]/img"));
        List<WebElement> viewAllInventoryElements = driver.findElements(By.xpath("//a[text()='View All Inventory']"));
        int productNameElementsCount = productNameElements.size();
        int prodImageElementsCount = prodImageElements.size();
        int viewAllInventoryElementsCount = viewAllInventoryElements.size();

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Product Names Displayed Count" + productNameElementsCount);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Product Images Displayed Count" + prodImageElementsCount);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "View All Inventory Count" + viewAllInventoryElementsCount);
        boolean bundleDataStatus = productNameElementsCount >= 2 && prodImageElementsCount >= 2 && viewAllInventoryElementsCount >= 2;

        softAssert.assertTrue(bundleDataStatus, "Expected bundle products data is not displayed");
    }

    public void verifyPromoBannerData() {
        softAssert.assertTrue(promoBanner.isDisplayed(), "promo banner text is not displayed::" + promoBanner.getText());
    }

    public void validateInventorySource(String inventorySource) {

        if (inventorySource.trim().equalsIgnoreCase("home store")) {
            softAssert.assertTrue(basePage.isTextDisplayed(EST_DELIVERY_VALUE), "delivery value is not having home store deliver::" + EST_DELIVERY_VALUE);
        } else {
            softAssert.assertTrue(basePage.isTextDisplayed(GEO_POOL_DELIVERY_VALUE), "delivery value is not having geo pool: " + GEO_POOL_DELIVERY_VALUE);
        }

    }

    public void validateAllSoftAssertions() {
        softAssert.assertAll();
    }

    public void verifyProductNameContainsSize(String size) {
        softAssert.assertTrue(productName.getText().contains(size), "Selected size is not displayed in product name");
    }

    public List<WebElement> getSizeGuideButtons() {
        return sizeGuideButtons;
    }

    public void validateJewelleryProduct() {
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Size Guide Section is displayed");
        softAssert.assertTrue(isSelectSizeGuideSectionDisplayed(), "Select Size Guide Section NOT displayed");

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Size buttons are displayed");
        List<String> expectedSizes = Arrays.asList("5", "6.5", "7", "7.5", "8");
        softAssert.assertTrue(verifySizeButtonsDisplayed(expectedSizes),
                "Some size buttons are missing!");
        basePage.jsClick(getSizeGuideButtons().get(2));

        waitForSecs(3);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating selected Size 7 is displayed in Product Name");
        verifyProductNameContainsSize("Size 7");
    }

    //-----------charan code
    public void closeModalOnPDP() {
        modalClose_SavedYourItem.click();
        waitForSecs(2);
    }

    public boolean productNameAndPriceAndModelNameDisplayed() {
        return (productName.isDisplayed() && productName.getText() != "" && productModelName.getText().length() > 8
                && productPrice.getText().matches(".*\\d.*")) && productcategoryHeader.getText() != ""
                && productPrice.getText().contains("$");
    }

    public boolean clearanceLabelIsDisplayed() {
        return label_Clearance.isDisplayed();
    }

    public boolean enterTextInEmailFieldOnPDP() {
        waitForSecs(1);
        enterEmailOnPDP.sendKeys("test@yopmail.com");
        return true;
    }

    public boolean validateAllSetMessage() {
        return basePage.driver.getPageSource().contains("All set! We'll get back to you as soon as product arrives.");
    }

    public boolean breadCrumbDisplayedOnPage() {
        return breadCrumbOnPage.isDisplayed();
    }

    public boolean promoBannerDisplayedOnPage() {
        return (promoBannerOnPage.isDisplayed() && promoBannerOnPage != null && !promoBannerOnPage.getText().isEmpty());
    }

    public String estDeliveryDateOnPDP() {
        return (estDeliveryDateOnPDP.getText());
    }

    public String productNameonPDP() {
        return (productName.getText());
    }

    public String zipcodeOnPDP() {
        return zipCodeOnPDP.getText();
    }

    public boolean availableInventoryHeaderDisplayed() {
        waitForSecs(2);
        return viewAllInventoryModalHeader.getText().contains("Available Inventory");
    }

    public boolean validateInventoryCount() {
        String[] parts = availableInventoryCount.getText().split("\\(");
        if (parts.length > 1) {
            String invCount = parts[1].split("\\)")[0];
            if (Integer.parseInt(invCount) > 10)
                return false;
        }
        return true;
    }

    public boolean matchProductNames(String productNameOnPDP) {
        return pNameOnInventoryModal.getText().equalsIgnoreCase(productNameOnPDP);
    }

    public boolean matchZipCode(String zipCodeOnPDP) {
        return deliveryZipCodeOnInvModal.getText().equalsIgnoreCase(zipCodeOnPDP);
    }

    public boolean verifyEstimatedDeliveryForAllInventory(String estDeliveryDateOnPDP) {
        int count = 0;
        for (WebElement actEstDeliveryDate : estDeliveryOnAvailableInventoryModal) {
            if (!actEstDeliveryDate.getText().equalsIgnoreCase(estDeliveryDateOnPDP))
                count++;
        }
        return count == 0;
    }

    public boolean verifyRenewalRateDisplayedOnInvModalForAllProducts() {
        int count = 0;
        for (WebElement renewalrateOnProduct : renewalRateOnAvailableInventoryModal) {
            if (renewalrateOnProduct.getText() == null || renewalrateOnProduct.getText().isEmpty())
                count++;
        }
        return count == 0;
    }

    public boolean verifyNumberOfPaymentsDisplayedOnInvModalForAllProducts() {
        int count = 0;
        for (WebElement numberofPayments : numberOfPaymentsOnAvailableInventoryModal) {
            if (numberofPayments.getText() == null || numberofPayments.getText().isEmpty())
                count++;
        }
        return count == 0;
    }

    public boolean verifyTotalCostToOwnOnInvModalForAllProducts() {
        int count = 0;
        for (WebElement totalCostToOwn : totalCostToOwnOnAvailableInventoryModal) {
            if (totalCostToOwn.getText() == null || totalCostToOwn.getText().isEmpty())
                count++;
        }
        return count == 0;
    }

    public boolean verifySameAsCashPriceDisplayedOnInvModalForAllProducts() {
        int count = 0;
        for (WebElement sameAsCashPrice : sameAsCashPriceOnAvailableInventoryModal) {
            if (sameAsCashPrice.getText() == null || sameAsCashPrice.getText().isEmpty())
                count++;
        }
        return count == 0;
    }

    public void validateClearanceLabel() {
        softAssert.assertTrue(clearanceLabelIsDisplayed(), "Clearance label is not displayed");
        Log.info("Clearance label is displayed");
    }

    //Charan methods  end here

    public void validateViewAllInventoryData() {
        // SoftAssert softAssert = new SoftAssert();
        String productNameOnPDP = productNameonPDP();
        String estDeliveryDateOnPDP = estDeliveryDateOnPDP();
        String zipCodeOnPDP = zipcodeOnPDP();
//        basePage.clickLinkByText("View All Inventory", DriverFactory.getDriver());
        basePage.scrollToElement(viewAllInventory);
        viewAllInventory.click();

        softAssert.assertTrue(availableInventoryHeaderDisplayed(), "Available Inventory header is not displayed");
        ExtentCucumberAdapter.addTestStepLog("Available Inventory header is displayed");

        softAssert.assertTrue(validateInventoryCount(), "Inventory count is more than 10");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Available Inventory count is more than 10 ::" + validateInventoryCount());

        softAssert.assertTrue(matchProductNames(productNameOnPDP), "Product Name is not matching");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Product Name is::" + productNameOnPDP);

        softAssert.assertTrue(matchZipCode(zipCodeOnPDP), "ZipCode is not matching");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Zipcode value is::" + zipCodeOnPDP);

        softAssert.assertTrue(verifyEstimatedDeliveryForAllInventory(estDeliveryDateOnPDP), "Estimated delivery is not same for all inventories");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Estimated delivery is same");

        softAssert.assertTrue(verifyRenewalRateDisplayedOnInvModalForAllProducts(), "Renewal Rate is not displayed on available Inventory modal for all inventories");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Renewal Rate is same");

        softAssert.assertTrue(verifyNumberOfPaymentsDisplayedOnInvModalForAllProducts(), "number of payments not displayed on available Inventory modal for all inventories");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "number of payments displayed for all inventory items");

        softAssert.assertTrue(verifyTotalCostToOwnOnInvModalForAllProducts(), "Total cost to own not displayed on available Inventory modal for all inventories");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Total cost to own is displayed for all inventory items");

        softAssert.assertTrue(verifySameAsCashPriceDisplayedOnInvModalForAllProducts(), "same as cash price not displayed on available Inventory modal for all inventories");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "same as cash price displayed for all inventory items");

        closeModalOnPDP();
        // softAssert.assertAll();
    }

    public void validateBreadCrumbIsDisplayed() {
        System.out.println("breadCrumbOnPage::" + breadCrumbOnPage.getText());

        softAssert.assertTrue(breadCrumbDisplayedOnPage(), "bread crumb is not displayed for the given product");

    }

    public boolean relatedCategoryIsDisplayedOnPage() {
        return (relatedCategories.isDisplayed());
    }

    public boolean addToCartAndStartOrderNotDisplayed() {
        try {
            List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[@title='Add To Cart']"));
            List<WebElement> startOrderButtons = driver.findElements(By.xpath("//button[text()='Start Order']"));

            return addToCartButtons.isEmpty() && startOrderButtons.isEmpty();

        } catch (NoSuchElementException e) {
            return true;
        }
    }

    public void validateStartOrderFunctionality() {
        softAssert.assertTrue(isStartOrderButtonIsDisplayed(), "Start order button is NOT displayed");
        clickOnStartOrderButton();
        softAssert.assertTrue(startYourOrderPageDisplayed(), "Start your order page is not displayed");
        basePage.navigateBack();
        waitForSecs(6);
    }

    public boolean priceIsDisplayed() {
        return productPrice.isDisplayed();
    }

    public void validateAddToCartFunctionality() {
        clickAddToCartButton();
        waitForSecs(6);
        cartItemCountText.isDisplayed();
        boolean cartContainsValue = cartItemCountText.getText().contains("1");
        softAssert.assertTrue(cartContainsValue, "Cart count value is displayed");
        //item added to cart popup validation
        softAssert.assertTrue(itemAddedToCart.isDisplayed(), "Element with Expected message is not displayed"+itemAddedToCart.getText());/// defect-SFCC-9378
        softAssert.assertTrue(viewCartAndOrder.isDisplayed(), "viewCartAndOrder Element not displyed");
        softAssert.assertTrue(continueShopping.isDisplayed(), "continueShopping Element not displyed");
        closeModalOnPDP();

        //to check Continue button functionality
        clickAddToCartButton();
        waitForSecs(4);
        continueShopping.click();
        waitForSecs(4);
    }

    public void validateAddToCartShouldNotDisplayForOutOfStock() {
        softAssert.assertTrue(addToCartAndStartOrderNotDisplayed(), "add to cart and start order buttons not displayed on oos");
    }

    public void validateRelatedCategoriesSection() {
        basePage.scrollToElement(relatedCategories);
        softAssert.assertTrue(relatedCategoryIsDisplayedOnPage(), "related categories section with list of items not displayed on PDP");
    }

    public void verifyProductImagesAreDisplayed() {
        softAssert.assertTrue(isProductImageIsDisplayed(), "product image is NOT displayed");
    }

    public void validateBrandNameProductNameModelPrice() {
        softAssert.assertTrue(isBrandNameDisplayedAsHyperLink(), "Brand name: is NOT displayed as hyper link");
        softAssert.assertTrue(productNameAndPriceAndModelNameDisplayed(), "product name or model number not displayed");
    }

    public void validateAvailabilityOfProduct(String availability) {
        if(availability.equalsIgnoreCase("in stock")){
            availability = availability+" In Your Store";
        }
        softAssert.assertTrue(verifyProductLabelDisplayedRight(availability),
                "product availability is NOT as: " + availability);
        //validate out of stock section
        if (availability.equalsIgnoreCase("out of stock")) {
            softAssert.assertTrue(isOutOfStockProductDisplayed(), "Out of stock NOT displayed");
        }
    }

    public void validateProductTagType(String productTagType) {
        if (productTagType.trim().equalsIgnoreCase("clearance"))
            softAssert.assertTrue(clearanceLabelIsDisplayed(), "Clearance label is not displayed");
    }

    public void validateProductCondition(String productCondition) {
        basePage.scrollToElement(productPrice);
        softAssert.assertTrue(getProductCondition(productCondition).isDisplayed(),
                "product is NOT displayed productCondition as: " + productCondition);
    }

    public void validateWishlistFunctionality() {
        clickOnWishlistButton();
        softAssert.assertTrue(itemIsSavedToCart(), "Item is saved to cart");

        closeSavedYourItemModal();
        softAssert.assertTrue(getWishListedItem().isDisplayed(), "After click on wishlist, item is wish listed");

        clickOnWishlistButton(); //to remove item from wishlist

        continueShopping.click();
        waitForSecs(2);

    }

    public void verifySignupPopupWindowIsDisplayed() {
        Log.info("--------verify signUpPopupWindow is displayed--------");
        try {
            signUpPopupWindow.isDisplayed();
            getSignUpPopupWindowClose.click();
            waitForSecs(2);
        } catch (NoSuchElementException nse) {
            Log.info("--------signUpPopupWindow is not displayed--------");
        }
    }

    public void validateEmailNotify() {
        enterTextInEmailFieldOnPDP();
        clickNotifyButton("Notify");
        softAssert.assertTrue(validateAllSetMessage(), "All set message not displayed on PDP section");
    }

    public void validateOutOfStockProductData() {
        softAssert.assertTrue(isBrandNameDisplayedAsHyperLink(), "Brand name: is NOT displayed as hyper link");
        softAssert.assertTrue(productNameDisplayed(), "Product name: is NOT displayed in PDP page");
        softAssert.assertTrue(getProductModel().isDisplayed(), "Brand model: is NOT displayed in PDP page");
        verifyProductImagesAreDisplayed();
    }

    public void validateProductRentalDropdownSelectionFunctionality_old(String rentalPaymentFrequency) {

        //here no need to pass string value
        softAssert.assertTrue(isDefaultPaymentOptionDisplayed(rentalPaymentFrequency), "Default payment option is " +
                rentalPaymentFrequency + "NOT displayed");
        double weeklyPrice = getPriceValue("Weekly");
        softAssert.assertTrue(weeklyPrice > 0, "Weekly Price not displayed");
        Log.info("Weekly Price: " + weeklyPrice + " displayed");

        //Bi-Weekly
        //clickDurationRentalOption();
        priceOptionChakraIcon.click();
        waitForSecs(3);
        biWeeklyPaymentOption.click();
        waitForSecs(3);
        Log.info("Click duration Rental option");
        double biWeeklyPrice = getPriceValue("Bi-Weekly");
        softAssert.assertTrue(biWeeklyPrice > weeklyPrice, "Bi-Weekly Price not displayed");
        Log.info("Bi-Weekly Price: " + biWeeklyPrice + " displayed");

        //Semi Monthly
        //clickDurationRentalOption();
        priceOptionChakraIcon.click();
        waitForSecs(3);
        semiMonthlyPaymentOption.click();
        waitForSecs(3);
        Log.info("Click duration Rental option");
        double semiMonthlyPrice = getPriceValue("Semi-Monthly");
        softAssert.assertTrue(semiMonthlyPrice > biWeeklyPrice, "Semi-Monthly Price not displayed");
        Log.info("Semi Monthly Price: " + semiMonthlyPrice + " displayed");

        //Monthly
//        clickDurationRentalOption();
        priceOptionChakraIcon.click();
        waitForSecs(3);
        monthlyPaymentOption.click();
        waitForSecs(3);
        Log.info("Click duration Rental option");
        double monthlyPrice = getPriceValue("Monthly");
        softAssert.assertTrue(monthlyPrice > semiMonthlyPrice, "Weekly Price not displayed");
        Log.info("Monthly Price: " + monthlyPrice + " displayed");

        isProductInventoryAvailable();
    }

    public void validateNeedItSoonerDisplayed() {
        Log.info("--------Need it sooner text is displayed--------");
        basePage.waitForElementVisible(needItSooner);
        basePage.scrollToElement(needItSooner);
        softAssert.assertTrue(needItSooner.isDisplayed(), "Need it sooner text is NOT displayed");
        driver.navigate().refresh();
    }


    public void validateShopSimilarItemDisplayed() {
        Log.info("--------Shop Similar Item link is displayed--------");
        softAssert.assertTrue(shopSimilarItemLabel.isDisplayed(), "Shop Similar Item link is NOT displayed");
    }

    public void validateShopSimilarItemFunctionality() {
        Log.info("--------Shop Similar Item Functionality check--------");
        softAssert.assertTrue(shopSimilarItemLabel.isDisplayed(), "Shop Similar Item link is NOT displayed");
        String subCategoryText = getBreadcrumbSubcategoryText();//breadCrumbSubCategory.getText().trim();
        basePage.scrollToElement(shopSimilarItemLabel);
        shopSimilarItemLabel.click();
        waitForSecs(4);

        String updatedLinkText = subCategoryText.trim().toLowerCase().replace(" ","-");
        basePage.waitForUrlToContain(updatedLinkText);

        Log.info("--------Shop Similar Item SubCategory--------" + subCategoryText);

        //softAssert.assertTrue(basePage.isTextDisplayed(subCategoryText), "Page is not navigated to similar item category ::"+subCategoryText);
        softAssert.assertTrue(basePage.pageHeaderContainsText(subCategoryText), "Page is not navigated to similar item category ::" + subCategoryText);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + "Page is navigated to similar item category: " + subCategoryText);
        basePage.navigateBack();
    }

    public void validateShopSimilarItemFunctionalityForTiersOrJewellery() {
        Log.info("--------Shop Similar Item Functionality check--------");
        basePage.scrollToElement(shopSimilarItemLabel);
        softAssert.assertTrue(shopSimilarItemLabel.isDisplayed(), "Shop Similar Item link is NOT displayed");
        String subCategoryText = breadCrumbSubCategoryTiersOrJewellery.getText();
        shopSimilarItemLabel.click();
        waitForSecs(2);
        Log.info("--------Shop Similar Item SubCategory--------" + subCategoryText);
//        softAssert.assertTrue(basePage.isTextDisplayed(subCategoryText), "Page is not navigated to similar item category ::"+subCategoryText);
        softAssert.assertTrue(basePage.pageHeaderContainsText(subCategoryText), "Page is not navigated to similar item category ::" + subCategoryText);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + "Page is navigated to similar item category: " + subCategoryText);
        basePage.navigateBack();
    }

    public void validateDeliveryDataAndZipCode(String zipcode, String deliveryDays) {
        Log.info("--------Validating Delivery days" + deliveryDays + "And ZipCode--------" + zipcode);
        softAssert.assertTrue(zipCodeOnPDP.getText().contains(zipcode), "ZipCode is not matching");
        softAssert.assertTrue(basePage.isTextDisplayed(deliveryDays), "Delivery days value is not matching");
    }

    public void validateBrandName(String brandName) {
        Log.info("--------Validating brandName--------" + brandName);
        softAssert.assertTrue(brandNameLink.getText().toLowerCase().contains(brandName.toLowerCase()), "brandName is not matching");
        // softAssert.assertTrue(basePage.isTextDisplayed(SPECIAL_ORDER_DELIVERY_VALUE),"Special order delivery value is not matching");
    }

    public void verifyProductNameContains(String name) {
        softAssert.assertTrue(productName.getText().contains(name), "Product Name as " + name + " not displayed in product name");
    }

    public void validateNotifyEmailFunctionality() {
        enterTextInEmailFieldOnPDP();
        clickNotifyButton("Notify");
        softAssert.assertTrue(validateAllSetMessage(), "All set message not displayed on PDP section");
        Log.info("--------All set message displayed on PDP section---------");
    }

    public void validateSizeGuideLinkFunctionality() {
        softAssert.assertTrue(isSizeGuidePDFOpenedInNewTab(), "PDF NOT opened in new tab");
    }

    public void validateProductNameAndPriceAndModelNameDisplayed() {
        softAssert.assertTrue(productNameAndPriceAndModelNameDisplayed(), "product name or model number not displayed");
    }

    public void validateAddToCartAndStartOrderNotDisplayed() {
        softAssert.assertTrue(addToCartAndStartOrderNotDisplayed(),
                "add to cart and start order buttons displayed on oos");
    }

    public void validateBundleProductImage() {
        softAssert.assertTrue(bundleProductImage.isDisplayed(),
                "image is not displayed for Bundle product on PDP page");
    }

    public void enterZipCodeInStoreLocatorPopup(String zipCode) {
        postalCodeInputFieldOnStoreLocatorPopup.click();
        postalCodeInputFieldOnStoreLocatorPopup.sendKeys(zipCode);
        goButtonOnStoreLocatorPopup.click();
        waitForSecs(3);
    }

    public void validateRentalToolTipFunctionality() {
        basePage.scrollToElement(rentalDetailsHeader);
        rentalDetailsHeader.isDisplayed();
        rentalDetailsToolTip.isDisplayed();
        rentalDetailsToolTip.click();
        waitForSecs(2);
        rentalDetailsToolTipData.isDisplayed();
        softAssert.assertTrue(rentalDetailsToolTipText.getText().contains(RENTAL_TOOLTIP_TEXT), "");
        rentalDetailsToolTipClose.click();
    }

    public void validatePreUsedProductData() {
        basePage.scrollToElement(preRentedLabel);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + preRentedLabel.getText());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + usedProductCondition.getText());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + preUsedProductInfoContent.getText());
        softAssert.assertTrue(saveMoreBtn.isDisplayed(), "Save More button Label is not displayed");
        softAssert.assertTrue(preRentedLabel.isDisplayed(), "Pre-Rented Label is not displayed");
        softAssert.assertTrue(usedProductCondition.isDisplayed(), "Used product condition is not displayed");
        softAssert.assertTrue(preUsedProductImage.isDisplayed(), "Pre-Rented Image Condition is not displayed");
        softAssert.assertTrue(preUsedProductImageGreatGoodFairText.isDisplayed(), "Great Good Fair Image text is displayed");
        softAssert.assertTrue(preUsedProductInfoContent.isDisplayed(), "Pre-Rented product Information is not displayed");
        softAssert.assertTrue(learnMoreLink.isDisplayed(), "Learn More link is not displayed");

//        softAssert.assertTrue(usedProductConditionGreatButton.isDisplayed(), "Used product Great button is not displayed");
//        softAssert.assertTrue(usedProductConditionGoodButton.isDisplayed(), "Used product Good button is not displayed");
        softAssert.assertTrue(usedProductConditionFairButton.isDisplayed(), "Used product Fair button is not displayed");

//        basePage.scrollToElement(usedProductConditionGreatButtonInfo);
//        softAssert.assertTrue(usedProductConditionGreatButtonInfo.isDisplayed(), "Used product Great button Information label is not displayed");
//        usedProductConditionGoodButtonInfo.click();
//        waitForSecs(3);
//        softAssert.assertTrue(basePage.isTextDisplayed(GREAT_TOOLTIP_TEXT), "Great tooltip information is not displayed");
//        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + GREAT_TOOLTIP_TEXT);
//        usedProductConditionButtonInfoClose.click();
//        waitForSecs(2);
//        ExtentCucumberAdapter.addTestStepLog("Used product Great Tooltip Information is displayed");
//
//        basePage.scrollToElement(usedProductConditionGoodButtonInfo);
//        softAssert.assertTrue(usedProductConditionGoodButtonInfo.isDisplayed(), "Used product Good button Information label is not displayed");
//        usedProductConditionGoodButtonInfo.click();
//        waitForSecs(2);
//        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + GOOD_TOOLTIP_TEXT);
//        softAssert.assertTrue(basePage.isTextDisplayed(GOOD_TOOLTIP_TEXT), "Good tooltip information is not displayed");
//        usedProductConditionButtonInfoClose.click();
//        waitForSecs(2);
//        ExtentCucumberAdapter.addTestStepLog("Used product Good Tooltip Information is displayed");

        basePage.scrollToElement(usedProductConditionFairButtonInfo);
        softAssert.assertTrue(usedProductConditionFairButtonInfo.isDisplayed(), "Used product Fair button Information label is not displayed");
        usedProductConditionFairButtonInfo.click();
        waitForSecs(2);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + FAIR_TOOLTIP_TEXT);
        softAssert.assertTrue(basePage.isTextDisplayed(FAIR_TOOLTIP_TEXT), "Fair tooltip information is not displayed");
        usedProductConditionButtonInfoClose.click();
        waitForSecs(2);
        ExtentCucumberAdapter.addTestStepLog("Used product Fair Tooltip Information is displayed");

    }

    public void validatePriceDisplayedForProduct() {
        softAssert.assertTrue(discountPrice.isDisplayed(), "Discount price for product is not displayed");
    }

    public void validateProductPriceIsNotDisplayed() {
        List<WebElement> priceElement = driver.findElements(By.xpath("//strong[@class='price']"));
        softAssert.assertTrue(priceElement.isEmpty(), "Price element is displayed for Tire Product");
        //softAssert.assertTrue(priceElement.size()==0, "Price element is displayed for Tire Product");
    }
    public boolean isBrandNameDisplayedAsHyperLink(String brandNameLink) {
        return getBrandNameLinkPDP(brandNameLink).isDisplayed();
    }
    public void clickOnDoNotHaveMobNumLink() {
        basePage.waitForElementToBeClickable(doNotHaveMobNumLink);
        doNotHaveMobNumLink.click();
    }

    public boolean isBenefitsPlusProductInfoTitleDisplayed() {
        return basePage.isElementDisplayed(benefitPlusProductInfoTitle);
    }
    public void verifySpecificationSectionIsDisplayed() {
        basePage.waitForElementVisible(specificationsHeaderLabel);
        basePage.waitForElementVisible(specificationsHeaderClosure);
        basePage.scrollToElement(specificationsHeaderLabel);
        //basePage.scrollToElement(specificationsHeaderClosure);
        //specificationsHeaderClosure.click();
        basePage.jsClick(specificationsHeaderLabel);
        waitForSecs(3);

        // Perform assertions using SoftAssert
        softAssert.assertEquals(basePage.isTextDisplayed("RMS:"), true, autoPay + "text is not displayed");
        softAssert.assertEquals(basePage.isTextDisplayed("Inventory Item:"), true);
        softAssert.assertEquals(basePage.isTextDisplayed("Store:"), true);
        basePage.scrollToElement(specificationsHeaderClosure);
        specificationsHeaderClosure.click();
        waitForSecs(3);

    }
    public void validateProductRentalDropdownSelectionFunctionality(String rentalPaymentFrequency) {

        //here no need to pass string value
//        basePage.scrollToElement(defaultPaymentOption);
//        softAssert.assertTrue(isDefaultPaymentOptionDisplayed(rentalPaymentFrequency), "Default payment option is " +
//                rentalPaymentFrequency + "NOT displayed");
//        double weeklyPrice = getPriceValue("Weekly");
//        softAssert.assertTrue(weeklyPrice > 0, "Weekly Price not displayed");
//        Log.info("Weekly Price: " + weeklyPrice + " displayed");
//
//        //Bi-Weekly
//        //clickDurationRentalOption();
//        priceOptionChakraIcon.click();
//        waitForSecs(3);
//        biWeeklyPaymentOption.click();
//        waitForSecs(3);
//        Log.info("Click duration Rental option");
//        double biWeeklyPrice = getPriceValue("Bi-Weekly");
//        softAssert.assertTrue(biWeeklyPrice > weeklyPrice, "Bi-Weekly Price not displayed");
//        Log.info("Bi-Weekly Price: " + biWeeklyPrice + " displayed");
//
//        //Semi Monthly
//        priceOptionChakraIcon.click();
//        waitForSecs(3);
//        semiMonthlyPaymentOption.click();
//        waitForSecs(3);
//        Log.info("Click duration Rental option");
//        double semiMonthlyPrice = getPriceValue("Semi-Monthly");
//        softAssert.assertTrue(semiMonthlyPrice > biWeeklyPrice, "Semi-Monthly Price not displayed");
//        Log.info("Semi Monthly Price: " + semiMonthlyPrice + " displayed");
//
//        //Monthly
//        priceOptionChakraIcon.click();
//        waitForSecs(3);
//        monthlyPaymentOption.click();
//        waitForSecs(3);
//        Log.info("Click duration Rental option");
//        double monthlyPrice = getPriceValue("Monthly");
//        softAssert.assertTrue(monthlyPrice > semiMonthlyPrice, "Weekly Price not displayed");
//        Log.info("Monthly Price: " + monthlyPrice + " displayed");

        //"//div[contains(@class, 'payment-options-select')]//select"
        basePage.scrollToElement(paymentSelector);
        paymentSelector.isDisplayed();
        softAssert.assertTrue(basePage.isTextDisplayed("per week"), "Default payment option is " +
                rentalPaymentFrequency + "NOT displayed");
        double weeklyPrice = getPriceValue("Weekly");
        softAssert.assertTrue(weeklyPrice > 0, "Weekly Price not displayed");
        Log.info("Weekly Price: " + weeklyPrice + " displayed");

        Log.info("Click duration Rental option bi-weekly");
        pyamentOptionSelector(paymentDropdownOptions[1]);
        waitForSecs(3);
        double biWeeklyPrice = getPriceValue("Bi-Weekly");
        softAssert.assertTrue(biWeeklyPrice > weeklyPrice, "Bi-Weekly Price not displayed");
        Log.info("Bi-Weekly Price: " + biWeeklyPrice + " displayed");

        Log.info("Click duration Rental option semi-monthly");
        pyamentOptionSelector(paymentDropdownOptions[2]);
        waitForSecs(3);
        double semiMonthlyPrice = getPriceValue("Semi-Monthly");
        softAssert.assertTrue(semiMonthlyPrice > biWeeklyPrice, "Semi-Monthly Price not displayed");
        Log.info("Semi Monthly Price: " + semiMonthlyPrice + " displayed");

        Log.info("Click duration Rental option monthly");
        pyamentOptionSelector(paymentDropdownOptions[3]);
        waitForSecs(3);
        double monthlyPrice = getPriceValue("Monthly");
        softAssert.assertTrue(monthlyPrice > semiMonthlyPrice, "Weekly Price not displayed");
        Log.info("Monthly Price: " + monthlyPrice + " displayed");
    }
    public void pyamentOptionSelector(String optionValue){
        // paymentSelector.click();
        //option values //weekly, bi-weekly, semi-monthly, monthly
        Select select = new Select(paymentSelector);
        select.selectByValue(optionValue.trim());
        waitForSecs(2);
    }
    public String getBreadcrumbSubcategoryText(){
        List<WebElement> breadCrumbSubCategoryList = driver.findElements(By.xpath("//nav[@aria-label='breadcrumb']//li"));
        int size = breadCrumbSubCategoryList.size()-1;
        String lastCategoryText = breadCrumbSubCategoryList.get(size).getText();
        System.out.println("Last category text in breadcrumb::"+lastCategoryText);
        return lastCategoryText;
    }
    public void validateProductPriceBreakdownData(String paymentFrequency) {

        //to select payment frequency

        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + " Rental Payment Frequency selected: " + paymentFrequency);

        if (!paymentFrequency.equalsIgnoreCase("weekly")) {

            //paymentSelector.click();

            waitForSecs(2);

            switch (paymentFrequency.toLowerCase()) {

                case "bi-weekly":

                    pyamentOptionSelector(paymentDropdownOptions[1]);

                    waitForSecs(2);

                    break;

                case "semi-monthly":

                    pyamentOptionSelector(paymentDropdownOptions[2]);

                    waitForSecs(2);

                    break;

                case "monthly":

                    pyamentOptionSelector(paymentDropdownOptions[3]);

                    waitForSecs(2);

                    break;

                default:

                    System.out.println("There is no matching value found for payment frequency, user entered payment frequency value::" + paymentFrequency);
            }
        }
        validateProductPriceRentalPaymentCalculations(paymentFrequency);

    }
    public void clickOnItemCartCloseButton() {
        basePage.waitForElementToBeClickable(itemCartCloseButton);
        basePage.click(itemCartCloseButton);
    }

    public void clickOnStorePostalCodeLink(){
        waitForSecs(5);
        basePage.scrollToElement(storePostalCodeLink);
        storePostalCodeLink.click();
        waitForSecs(2);
    }

    public void verifyUpdatedStoreNoDisplayed(String updatedZipCode){
        waitForSecs(5);
        basePage.scrollToElement(storePostalCodeLink);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() +"Updated store zip code no displayed on PDP page:"+updatedZipCode);
        softAssert.assertTrue( storePostalCodeLink.getText().contains(updatedZipCode), "Updated store zip code no not displayed on pdp page:"+updatedZipCode);
    }

    public String getStorePostalCodeFromPDP(){
        waitForSecs(7);
        basePage.waitForPageToLoad();
        basePage.scrollToElement(storePostalCodeLink);
        return storePostalCodeLink.getText().trim();
    }

    public void clickOnGO() {
        basePage.jsClick(gobutton);
        waitForSecs(5);
    }

    public void validateIsWasPricingOfProduct(){
        basePage.scrollToElement(strikeThroughProductPriceText);
        softAssert.assertTrue(strikeThroughProductPriceText.getText().contains("$"), "Product price text not contains $");
        softAssert.assertTrue(strikeThroughProductPriceText.getText().contains("per week"), "Product price text not contains per week");
        double productWasPrice = Double.parseDouble(strikeThroughProductPriceText.getText().trim().replaceAll("[^\\d.]", "")); //19.99
        softAssert.assertTrue(productWasPrice>0, "Was price should be displayed and must be greater than 0");

        basePage.scrollToElement(renewalRateWasPrice);
        softAssert.assertTrue(renewalRateWasPrice.getText().contains("$"),"Renewal rate was price text not contains $"); //$19.99 weekly*
        double renewalRateWasPriceFromUI = Double.parseDouble(renewalRateWasPrice.getText().trim().replaceAll("[^\\d.]", "")); //19.99
        softAssert.assertTrue(renewalRateWasPriceFromUI>0, "RenewalRate Was price should be displayed and must be greater than 0");

        basePage.scrollToElement(totalCostToOwnWasPrice);
        softAssert.assertTrue(totalCostToOwnWasPrice.getText().contains("$"),"TotalCost To Own WasPrice text not contains $"); ////$19.99 weekly*
        double totalCostToOwnWasPriceFromUI = Double.parseDouble(totalCostToOwnWasPrice.getText().trim().replaceAll("[^\\d.]", "")); //19.99
        softAssert.assertTrue(totalCostToOwnWasPriceFromUI>0, "TotalCost To Own Was price should be displayed and must be greater than 0");

        basePage.scrollToElement(sameAsCashPriceOWasPrice);
        softAssert.assertTrue(sameAsCashPriceOWasPrice.getText().contains("$"),"Same As Cash Price was price text not contains $"); //$19.99
        double sameAsCashPriceOWasPriceFromUI = Double.parseDouble(sameAsCashPriceOWasPrice.getText().trim().replaceAll("[^\\d.]", "")); //19.99
        softAssert.assertTrue(sameAsCashPriceOWasPriceFromUI>0, "Same As Cash Price Was price should be displayed and must be greater than 0");

        String discountTextValue = discountPriceText.getText();
        softAssert.assertTrue(discountTextValue.contains("-"), "discount price not contains - symbol");
        int discountTextValuePriceFromUI = Integer.parseInt(discountTextValue.replaceAll("[^\\d.]", ""));
        softAssert.assertTrue(discountTextValue.contains("%"), "discount price not contains % symbol");
        softAssert.assertTrue(discountTextValuePriceFromUI>0, "discount price not having value >0");
    }

    public void brandNameLinkValidation(){
        basePage.scrollToElement(brandNameLink);
        brandNameLink.click();
        waitForSecs(5);
        basePage.waitForPageToLoad();
        basePage.isTextDisplayed(brandNameLink.getText().split(" ")[0]);
        basePage.navigateBack();
        basePage.waitForPageToLoad();
    }
}