package parallel.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.HomePage;
import org.testng.Assert;
import parallel.pages.PdpPage;
import com.qa.factory.DriverFactory;
import com.pages.BasePage;
import com.qa.util.ExcelReader;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static parallel.pages.PdpPage.*;

public class PdpPageSteps {
    private static final Logger Log = LoggerHelper.getLogger();
    private PdpPage pdpPage = new PdpPage(DriverFactory.getDriver());
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private BasePage basePage = new BasePage(DriverFactory.getDriver());
    ExcelUtils excelUtils = new ExcelUtils();

    private static String tiresProductDetailsData = "A touring all-season tire for coupes, sedans and minivans. Combines comfort with low noise, reduced rolling resistance, long life and four-season traction.";
    SoftAssert softAssert = new SoftAssert();
    private Map<String, Map<String, String>> excelData;

    @And("the user clicks on Wishlist button")
    public void theUserClicksOnWishlistButton() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on Wishlist button");
        pdpPage.clickOnWishlistButton();
        Log.info("Clicked on wishlist button on PDP");
    }

    @Then("verify the item is saved to cart")
    public void verifyTheItemIsSavedToCart() {
        ExtentCucumberAdapter.addTestStepLog("Item is saved to cart");
        softAssert.assertTrue(pdpPage.itemIsSavedToCart(),
                "Item is saved to cart");
        Log.info("Item is saved to cart");
    }

    @Then("verify the item is added to cart")
    public void verifyTheItemIsAddedToCart() {
        ExtentCucumberAdapter.addTestStepLog("Item is added to cart");
        softAssert.assertTrue(pdpPage.itemIsAddedToCart(),
                "Item is added to cart");
        Log.info("Item is added to cart");
    }

    @When("the user pick a Jewelry ring product")
    public void pick_a_jewelry_ring_product() {
        ExtentCucumberAdapter.addTestStepLog("Navigating to Jewelry ring product page");
        DriverFactory.getDriver().get("https://rentacenter-development.mobify-storefront.com/product/200088461");
        Log.info("Navigate to Jewelry ring Product page");
    }

    @When("the user navigates to PDP page")
    public void navigate_to_pdp_page() {
        ExtentCucumberAdapter.addTestStepLog("the user navigates to PDP page");
    }

    @Then("size guide session should be displayed")
    public void verify_size_guide_session_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Size guide session is displayed");
        softAssert.assertTrue(pdpPage.isSelectSizeGuideSectionDisplayed(),
                "Select Size Guide Section NOT displayed");
        Log.info("Select Size Guide Section displayed");
    }

    @Then("the numbers should be visible in the size guide")
    public void the_numbers_should_be_visible_in_the_size_guide() {
        ExtentCucumberAdapter.addTestStepLog("The numbers should be visible in the size guide");
        List<String> expectedSizes = Arrays.asList("5", "6.5", "7", "7.5", "8");
        softAssert.assertTrue(pdpPage.verifySizeButtonsDisplayed(expectedSizes),
                "Some size buttons are missing!");
        Log.info("Size buttons are displayed");
    }

    @Then("each number button is clickable in the size guide")
    public void verify_each_number_button_is_clickable_in_the_size_guide() {
        ExtentCucumberAdapter.addTestStepLog("each number button is clickable in the size guide");
        softAssert.assertTrue(pdpPage.isEachNumberButtonClickableInSizeGuide(),
                "Some buttons are NOT clickable!");
        Log.info("Buttons are clickable");
    }

    @Then("size guide PDF should be opened in new tab")
    public void verify_size_guide_pdf_should_be_opened_in_new_tab() {
        ExtentCucumberAdapter.addTestStepLog("size guide PDF should be opened in new tab");
        softAssert.assertTrue(pdpPage.isSizeGuidePDFOpenedInNewTab(),
                "PDF NOT opened in new tab");
        Log.info("PDF opened in new tab");
    }

    @And("the user clicks on saved your item modal close button")
    public void theUserClicksOnSavedYourItemModalCloseButton() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on saved your item modal close button");
        pdpPage.closeSavedYourItemModal();
    }

    @Then("verify product share option is displayed on pdp")
    public void verifyProductShareOptionIsDisplayedOnPdp() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product share option is displayed on PDP");
        softAssert.assertTrue(pdpPage.shareOptionDisplayedOnPDP(), "Share option displayed on PDP");
        Log.info("Share option displayed on PDP");
    }

    @Then("verify you may also like section is displayed")
    public void verifyYouMayAlsoLikeSectionIsDisplayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying 'You may also like' section is displayed on PDP");
        softAssert.assertTrue(pdpPage.youMayAlsoLikeDisplayedOnPDP(), "You may also like section displayed on PDP");
        Log.info("You may also like section displayed on PDP");
    }

    @And("the user search for product and verify availability on pdp")
    public void theUserSearchForProductAndVerifyAvailabilityOnPdp(DataTable dataTable) {
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> credentials : credentialsList) {
            String productNumber = credentials.get("Product Code");
            String labelName = credentials.get("Label Name");
            ExtentCucumberAdapter.addTestStepLog("Verifying product label: " + labelName + " for product: " + productNumber);
            softAssert.assertTrue(pdpPage.verifyProductLabelDisplayedRight(productNumber, labelName),
                    "product:  " + productNumber + " is NOT displayed as: " + labelName);
            ExtentCucumberAdapter.addTestStepLog("product : " + productNumber + " is displayed as:  " + labelName);
            org.mortbay.log.Log.info("product : " + productNumber + " is displayed as:  " + labelName);
            org.mortbay.log.Log.info("product displayed as expected on PDP");
        }
    }

    @Then("the user is redirected to benefits plus page")
    public void theUserIsRedirectedToBenefitsPlusPage() {
        ExtentCucumberAdapter.addTestStepLog("Verifying redirection to Benefits Plus page");
        softAssert.assertTrue(pdpPage.benefitsPlusPageIsDisplayed(),
                "Benefits plus page is not displayed");
        Log.info("Benefits plus page is displayed");
    }

    @Then("select store text displayed when no store is selected")
    public void selectStoreTextDisplayedWhenNoStoreIsSelected() {
        ExtentCucumberAdapter.addTestStepLog("Verifying 'Select store' text is displayed when no store is selected");
        softAssert.assertTrue(pdpPage.selectStoreTextDisplayed(), "Select store text  is not displayed");
        Log.info("Select store text  is displayed");
    }

    @Then("the zip code input field should be visible")
    public void verify_zip_code_should_be_visible() {
        ExtentCucumberAdapter.addTestStepLog("Verifying zip code input field is visible");
        softAssert.assertTrue(pdpPage.isZipCodeInputFieldIsDisplayed(), "Zip code input field is NOT displayed");
        Log.info("Zip code input field is displayed");
    }

    @When("the user clicks on start order button")
    public void the_user_clicks_on_start_order_button() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on Start Order button");
        pdpPage.clickOnStartOrderButton();
        Log.info("Click on Start Order button");
    }

    @Then("the store locator popup should be displayed")
    public void verify_store_locator_popup_should_be_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Store Locator popup is displayed");
        softAssert.assertTrue(pdpPage.isStoreLocatorPopupIsDisplayed(),
                "Store locator popup is NOT displayed");
        Log.info("Store locator popup is displayed");
    }

    @Then("the start order button should be displayed")
    public void verify_start_order_button_should_be_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Start Order button is displayed");
        softAssert.assertTrue(pdpPage.isStartOrderButtonIsDisplayed(),
                "Start order button is NOT displayed");
        Log.info("Start order button is displayed");
    }

    @Then("the add to cart button should be displayed")
    public void verify_add_to_cart_should_be_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Add to Cart button is displayed");
        softAssert.assertTrue(pdpPage.isAddToCartButtonIsDisplayed(),
                "Add to cart button is NOT displayed");
        Log.info("dd to cart buttonp is displayed");
    }

    @Then("the {string} PDP should load successfully")
    public void verify_pdp_should_be_displayed(String category) {
        ExtentCucumberAdapter.addTestStepLog("Verifying PDP is loaded successfully for category: " + category);
        softAssert.assertTrue(pdpPage.isPDPDisplayed(category),
                category + " is NOT loaded successfully");
        Log.info(category + " is loaded successfully");
    }

    @Then("the user should be taken to the Payphone static page")
    public void verify_pdp_should_be_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying redirection to the Payphone static page");
        softAssert.assertTrue(pdpPage.verifyCheckoutAccordionIsDisplayed(),
                "The user is NOT redirected to Payphone static page");
        Log.info("The user is redirected to Payphone static page");
    }

    @Then("the correct tire product details should be displayed {string}")
    public void is_pricing_for_zip_code_displayed_below_the_header_on_the_home_page(String productDetails) {
        ExtentCucumberAdapter.addTestStepLog("Verifying correct tire product details are displayed: " + productDetails);
        softAssert.assertTrue(pdpPage.isProductDetailsTextDisplayed(productDetails), "correct product details NOT displayed");
        Log.info("correct product details " + productDetails + " :displayed");
    }

    @Then("verify product image is displayed")
    public void verifyProductImageIsDisplayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product image is displayed");
        softAssert.assertTrue(pdpPage.isProductImageIsDisplayed(), "product image is NOT displayed");
        Log.info("product image is displayed");
    }

    @When("the user searches for product {string}")
    public void search_for_product(String productID) {
        ExtentCucumberAdapter.addTestStepLog("Searching for product ID: " + productID);
        DriverFactory.getDriver().get("https://rentacenter-development.mobify-storefront.com/product/" + productID);
        Log.info("Navigate to Product page: {}", productID);
    }

    @When("the user enter zipcode {string} and click on Go")
    public void theUserEnterZipcodeAndClickOnGo(String zipCode) {
        ExtentCucumberAdapter.addTestStepLog("Entering zipcode: " + zipCode + " and clicking Go");
        pdpPage.enterZipcodeAndClickOnGo(zipCode);
        Log.info("entered zipcode and clicked Go button");
    }

    @Then("FAQ section is displayed on BP pdp")
    public void faqSectionIsDisplayedOnBPPdp() {
        ExtentCucumberAdapter.addTestStepLog("Verifying FAQ section is displayed on BP PDP");
        softAssert.assertTrue(pdpPage.faqSectionDisplayed(), "FAQ section is not displayed on bp pdp");
        Log.info("FAQ's section is displayed on bp pdp");
    }

    @And("the user clicks on add to cart button")
    public void theUserClicksOnAddToCartButton() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on Add to Cart button");
        pdpPage.clickAddToCartButton();
        Log.info("clicked on add to cart button");
    }

    @Then("start your order page is displayed")
    public void startYourOrderPageIsDisplayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Start Your Order page is displayed");
        softAssert.assertTrue(pdpPage.startYourOrderPageDisplayed(), "Start your order page is not displayed");
        Log.info("Start your order page is displayed");
    }

    @Then("verify all rental payment options are displayed on PDP")
    public void verifyAllRentalPaymentOptionsAreDisplayedOnPDP() {
        ExtentCucumberAdapter.addTestStepLog("Verifying all rental payment options are displayed on PDP");
        pdpPage.clickWeeklyPaymentOptionOnPDP();
        Log.info("clicked on weekly payment option");
        softAssert.assertTrue(pdpPage.allPaymentOptionsAreDispalyedOnPDP(), "One or more payment options are not displayed");
        Log.info("All payment options are  displayed");
    }

    @Then("verify sections with no values not displayed on BP PDP")
    public void verifySectionsWithNoValuesNotDisplayedOnBPPDP() {
        ExtentCucumberAdapter.addTestStepLog("Verifying that sections with no values are not displayed on BP PDP");
        softAssert.assertTrue(pdpPage.sectionsWithNoValuesNotDisplayed(), "One or more sections with no values displayed");
        Log.info("One or more sections with no values not displayed");
    }

    @Then("verify sundaysky video is displayed on BP PDP")
    public void verifySundayskyVideoIsDisplayedOnBPPDP() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Sundaysky video is displayed on BP PDP");
        softAssert.assertTrue(pdpPage.sundaySkyVideoIsDisplayed(), "sunday sky video is not displayed");
        Log.info("sunday sky video is not displayed");
    }

    @Then("the user should see product name on pdp")
    public void theUserShouldSeeProductNameOnPdp() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product name is displayed on PDP");
        softAssert.assertTrue(pdpPage.productNameDisplayed(), "product name not displayed");
        Log.info("product name  is displayed");
    }

    @Then("the user should see product image on pdp")
    public void theUserShouldSeeProductImageOnPdp() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product image is displayed on PDP");
    }

    @Then("the product label should be displayed as {string} on pdp")
    public void theProductLabelShouldBeDisplayedAsOnPdp(String labelName) {
        ExtentCucumberAdapter.addTestStepLog("Verifying product label is displayed as: " + labelName);
        softAssert.assertTrue(pdpPage.verifyProductLabelDisplayedRight(labelName),
                "product is NOT displayed as: " + labelName);
    }

    @Then("the user is navigated to the PDP page showing the product as out of stock")
    public void navigate_to_the_pdp_page_showing_the_product_as_out_of_stock() {
        ExtentCucumberAdapter.addTestStepLog("Verifying PDP page shows the product as out of stock");
        softAssert.assertTrue(pdpPage.isOutOfStockProductDisplayed(),
                "Out of stock NOT displayed");
        Log.info("Product displayed as Out of Stock ");
    }

    @Then("the user is navigated to the PDP Benefits Plus page")
    public void navigate_to_the_pdp_benefits_plus_page() {
        ExtentCucumberAdapter.addTestStepLog("Verifying PDP Benefits Plus page is displayed");
        softAssert.assertTrue(pdpPage.isBenefitsPlusProductDisplayed(),
                "Benefits Plus product NOT displayed");
        Log.info("Benefits plus product is displayed ");
    }

    @Then("the static content block is displayed on the PDP")
    public void verify_static_content_block_is_displayed_on_the_pdp() {
        ExtentCucumberAdapter.addTestStepLog("Verifying static content block is displayed on PDP");
        softAssert.assertTrue(pdpPage.isStaticContentTempOOSBlockIsDisplayed(),
                "Temp Out of stock header NOT displayed");
        softAssert.assertTrue(pdpPage.isStaticContentTempOOSBlockBodyIsDisplayed(),
                "Temp Out of stock body NOT displayed");
        Log.info("Temp Out of stock Header and Body is displayed");
    }

    @When("the user enters the email address {string} into the Notify section")
    public void enter_the_email_address_into_the_notify_section(String emailAddress) {
        ExtentCucumberAdapter.addTestStepLog("Entering email address into Notify section: " + emailAddress);
        pdpPage.enterEmailAddressInNotifySection(emailAddress);
        Log.info("Enter email: " + emailAddress);
    }

    @When("the user clicks the {string} button")
    public void click_the_button(String buttonName) {
        // ExtentCucumberAdapter.addTestStepLog("User clicks the " + buttonName + " button");
        Log.info("Click the button:" + buttonName);
        pdpPage.clickNotifyButton(buttonName);
    }

    @When("the user clicks on the {string} button")
    public void click_on_the_button(String buttonName) {
        basePage.clickButtonByName(buttonName);
        Log.info("Click button:" + buttonName);
    }


    @Then("the system should accept the email address")
    public void verify_system_should_accept_the_email_address() {
        ExtentCucumberAdapter.addTestStepLog("Verifying system accepts the email address");
        softAssert.assertTrue(pdpPage.isSystemAcceptsEmailAddressNotifySection(),
                "System NOT accepts email address");
        Log.info("System accepted email address");
    }

    @Then("check the state of the Email me deals and offers checkbox")
    public void check_the_state_of_the_email_me_deals_and_offers_checkbox() {
        ExtentCucumberAdapter.addTestStepLog("Checking state of 'Email me deals and offers' checkbox");
        softAssert.assertTrue(pdpPage.isEmailMeDealsAndOffersCheckBoxSelected(),
                "Email me deals and offers checkbox NOT selected by default");
        Log.info("Email me deals and offers checkbox selected by default");
    }

    @Then("the user is redirected to the PLP page of the selected subcategory {string}")
    public void verify_user_is_redirected_to_the_plp_page_of_the_selected_subcategory(String expectedURLFragment) {
        ExtentCucumberAdapter.addTestStepLog("Verifying redirection to PLP page for subcategory: " + expectedURLFragment);
        softAssert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
        Log.info("Link redirected to page: " + expectedURLFragment);
        homePage.browserBack();
        Log.info("Browser back button navigated");
    }

    @Then("the Rental Details Renewal Rate section is displayed with a value {string}")
    public void verify_rental_details_renewal_rate_section_is_displayed_with_a_value(String val) {
        ExtentCucumberAdapter.addTestStepLog("Verifying Rental Details Renewal Rate section displays value: " + val);
        softAssert.assertTrue(pdpPage.isRentalDetailsRenewalRateDisplayed(val),
                "Rate section val: " + val + " NOT displayed");
        Log.info("Rate section val: " + val + " is displayed");
    }

    @Then("the Rental Details Number of Payments section is displayed with a value {string}")
    public void verify_rental_details_number_of_payments_section_is_displayed_with_a_value(String val) {
        ExtentCucumberAdapter.addTestStepLog("Verifying Rental Details Number of Payments section displays value: " + val);
        softAssert.assertTrue(pdpPage.isRentalDetailsNoOfPaymentsDisplayed(val),
                "Number of Payments section val: " + val + " NOT displayed");
        Log.info("Number of Payments section val: " + val + " is displayed");
    }

    @Then("the Rental Details Total Cost To Own section is displayed with a value {string}")
    public void verify_rental_details_total_cost_to_own_section_is_displayed_with_a_value(String val) {
        ExtentCucumberAdapter.addTestStepLog("Verifying Rental Details Total Cost To Own section displays value: " + val);
        softAssert.assertTrue(pdpPage.isRentalDetailsTotalCostDisplayed(val),
                "Total Cost section val: " + val + " NOT displayed");
        Log.info("Total Cost section val: " + val + " is displayed");
    }

    @Then("the Rental Details Same as Cash Price section is displayed with a value {string}")
    public void verify_rental_details_same_as_cash_price_section_is_displayed_with_a_value(String val) {
        ExtentCucumberAdapter.addTestStepLog("Verifying Rental Details Same as Cash Price section displays value: " + val);
        softAssert.assertTrue(pdpPage.isRentalDetailsSameAsCashPriceDisplayed(val),
                "Same as Cash Price section val: " + val + " NOT displayed");
        Log.info("Same as Cash Price section val: " + val + " is displayed");
    }

    @When("the user enters the zipcode {string} on the PDP page")
    public void enter_the_zipcode_on_the_pdp_page(String zipCode) {
        ExtentCucumberAdapter.addTestStepLog("User enters the zipcode on PDP page: " + zipCode);
        pdpPage.enterZipCode(zipCode);
        Log.info("Enter Zip code: " + zipCode);
    }

    @When("the user clicks on Go button on the PDP page")
    public void the_user_clicks_on_go_button() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on Go button on PDP page");
        pdpPage.clickOnGOButton();
        Log.info("Click on GO button");
    }

    @Then("renewal pricing {string} {string} should be updated according to the selected store")
    public void verify_pricing_should_be_updated_according_to_the_selected_store(String price, String frequency) {
        ExtentCucumberAdapter.addTestStepLog("Verifying renewal pricing and frequency are updated to: " + price + " " + frequency);
        softAssert.assertTrue(pdpPage.isRenewalPriceDisplayed(price), "Renewal price NOT displayed");
        Log.info("Renewal price: " + price + " displayed");

        softAssert.assertTrue(pdpPage.isRenewalFrequencyDisplayed(frequency), "Renewal frequency NOT displayed");
        Log.info("Renewal frequency: " + frequency + " displayed");
    }

    @When("the user clicks the zip code hyperlink available in the Rental Details section")
    public void click_the_zip_code_hyperlink_available_in_the_rental_details_section() {
        ExtentCucumberAdapter.addTestStepLog("User clicks the zip code hyperlink in the Rental Details section");
        pdpPage.clickZipcodeHyperlinkRentalDetailsSection();
        Log.info("Click the zip code hyper link available in the rental details section");
    }

    @Then("the Store Locator pop-up should open")
    public void verify_store_locator_pop_up_should_open() {
        ExtentCucumberAdapter.addTestStepLog("Verifying the Store Locator pop-up is displayed");
        softAssert.assertTrue(pdpPage.isStoreLocatorPopupDisplayed(), "Store Locator Pop-up is NOT displayed");
        Log.info("Store Locator Pop-up is  displayed");
    }

    @Then("the user should be able to select a new store from the pop-up")
    public void verify_able_to_select_a_store_from_the_pop_up() {
        ExtentCucumberAdapter.addTestStepLog("Verifying user can select a new store from the pop-up");
        softAssert.assertTrue(pdpPage.isNewStoreSelected(), "New store NOT selected");
    }

    @Then("Brand Name {string} is displayed as a hyperlink")
    public void verify_brand_name_is_displayed_as_a_hyperlink(String brandName) {
        ExtentCucumberAdapter.addTestStepLog("Verifying Brand Name is displayed as hyperlink: " + brandName);
        softAssert.assertTrue(pdpPage.isBrandNameDisplayedAsHyperLink(), "Brand name: " + brandName + " NOT displayed as hyper link");
        Log.info("Brand Name: " + brandName + " is displayed as hyper link");
    }

    @Then("the user is redirected to the corresponding Brand PLP {string}")
    public void verify_user_is_redirected_to_the_corresponding_brand_plp(String expectedURLFragment) {
        ExtentCucumberAdapter.addTestStepLog("Verifying redirection to Brand PLP: " + expectedURLFragment);
        softAssert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab(expectedURLFragment), "NOT redirected to page url: " + expectedURLFragment);
        Log.info("Link redirected to page: " + expectedURLFragment);
        homePage.browserBack();
        Log.info("Browser back button navigated");
    }

    @Then("the default rental payment option {string} is selected")
    public void verify_default_rental_payment_option_is_selected(String paymentOption) {
        ExtentCucumberAdapter.addTestStepLog("Verifying default rental payment option is selected: " + paymentOption);
        softAssert.assertTrue(pdpPage.isDefaultPaymentOptionDisplayed(paymentOption), "Default payment option is " + paymentOption + "NOT displayed");
        Log.info("Default payment option: " + paymentOption + " is selected");
    }

    @Then("corresponding pricing is displayed")
    public void corresponding_pricing_is_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying corresponding pricing is displayed");
        String productPrice = pdpPage.getProductPriceDisplayed();
        softAssert.assertFalse(productPrice.isEmpty(), "Product Price: " + productPrice + " NOT displayed");
        Log.info("Product price: " + productPrice + " is displayed");
    }

    @When("the user changes the rental payment option Weekly,Bi-Weekly,Semi-Monthly,Monthly")
    public void the_user_changes_the_rental_payment_option_weekly_bi_weekly_semi_monthly_monthly() {
        ExtentCucumberAdapter.addTestStepLog("User changes rental payment option through all available frequencies");
        Log.info("the user changes the rental payment option Weekly,Bi-Weekly,Semi-Monthly,Monthly");
    }

    @Then("the pricing updates dynamically based on the selected rental")
    public void the_pricing_updates_dynamically_based_on_the_selected_rental() {
        ExtentCucumberAdapter.addTestStepLog("Verifying pricing updates dynamically based on selected rental option");
        double weeklyPrice = pdpPage.getPriceValue("Weekly");
        softAssert.assertTrue(weeklyPrice > 0, "Weekly Price not displayed");
        Log.info("Weekly Price: " + weeklyPrice + " displayed");

        pdpPage.clickDurationRentalOption();
        Log.info("Click duration Rental option");
        double biWeeklyPrice = pdpPage.getPriceValue("Bi-Weekly");
        softAssert.assertTrue(biWeeklyPrice > weeklyPrice, "Bi-Weekly Price not displayed");
        Log.info("Bi-Weekly Price: " + biWeeklyPrice + " displayed");

        pdpPage.clickDurationRentalOption();
        Log.info("Click duration Rental option");
        double semiMonthlyPrice = pdpPage.getPriceValue("Semi-Monthly");
        softAssert.assertTrue(semiMonthlyPrice > biWeeklyPrice, "Semi-Monthly Price not displayed");
        Log.info("Semi Monthly Price: " + semiMonthlyPrice + " displayed");

        pdpPage.clickDurationRentalOption();
        Log.info("Click duration Rental option");
        double monthlyPrice = pdpPage.getPriceValue("Monthly");
        softAssert.assertTrue(monthlyPrice > semiMonthlyPrice, "Weekly Price not displayed");
        Log.info("Monthly Price: " + monthlyPrice + " displayed");
    }

    @Then("product inventory stock shall be displayed as {string}")
    public void verify_product_inventory_stock_availability_display(String stockAvailability) {
        ExtentCucumberAdapter.addTestStepLog("Verifying product inventory stock availability is displayed as: " + stockAvailability);
        softAssert.assertTrue(pdpPage.isProductInventoryAvailable(), "Product inventory: " + stockAvailability + " NOT displayed");
        Log.info("Product Inventory " + stockAvailability + " available");
    }

    @Then("the product shall display the Pre-Rented label")
    public void verify_product_shall_display_the_pre_rented_label() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product displays the Pre-Rented label");
        String preRentedLabel = pdpPage.getPreRentedLabel();
        softAssert.assertTrue(preRentedLabel.length() > 0, "Pre Rented Label: " + preRentedLabel + " NOT displayed");
        Log.info("Pre-Rented label " + preRentedLabel + " displayed");
    }

    @When("the user selects the {string} section")
    public void the_user_selects_the_tab(String sectionName) {
        ExtentCucumberAdapter.addTestStepLog("User selects the section: " + sectionName);
        pdpPage.selectSection(sectionName);
        Log.info("Select section: " + sectionName);
    }

    @Then("the inventory number and store number shall be updated under the Specifications section")
    public void verify_inventory_number_and_store_number_shall_be_updated_under_the_specifications_section() {
        ExtentCucumberAdapter.addTestStepLog("Verifying inventory number and store number under Specifications section");
        String inventoryNumber = pdpPage.getInventoryNumber();
        String storeNumber = pdpPage.getStoreNumber();
        softAssert.assertTrue(!inventoryNumber.isEmpty() && !storeNumber.isEmpty(),
                "Store and Inventory Numbers NOT displayed");
        Log.info("Store Number: " + storeNumber + " and Inventory Number: " + inventoryNumber + "displayed");
    }

    @Then("user redirected to payfone or prefill page")
    public void user_redirected_to_payfone_or_prefill_page() {
        ExtentCucumberAdapter.addTestStepLog("Verifying redirection to payfone or prefill page");
        softAssert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab("payfone"), "NOT redirected to page url: payfone");
        Log.info("Link redirected to page: payfone");
        homePage.browserBack();
        Log.info("Browser back button navigated");
    }

    @Then("the user verify product image is displayed")
    public void verify_product_image_is_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product image is displayed");
        softAssert.assertTrue(pdpPage.isProductImageIsDisplayed(), "product image is NOT displayed");
        Log.info("product image is displayed");
    }

    @Then("the user verify multiple images are displayed")
    public void verify_multiple_images_are_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying multiple product images are displayed");
        softAssert.assertTrue(pdpPage.isMultipleImagesAreDisplayed(), "multiple images are NOT displayed");
        Log.info("multiple images are displayed");
    }

    @Then("the user verify navigation between images works correctly")
    public void verify_navigation_between_images_works_correctly() {
        ExtentCucumberAdapter.addTestStepLog("Verifying navigation between product images works correctly");
        softAssert.assertTrue(pdpPage.isNavigatedToMultipleImages(), "images are NOT navigating correctly");
        Log.info("images are navigating correctly");
    }

    @Then("the {string} rental payment option is selected")
    public void verify_selected_rental_payment_option(String priceOption) {
        ExtentCucumberAdapter.addTestStepLog("Verifying selected rental payment option is: " + priceOption);
        softAssert.assertTrue(pdpPage.selectedRentalPaymentOption(priceOption), priceOption + " rental payment option is NOT selected");
        Log.info(priceOption + " rental payment option is selected");
    }

    @When("the user click on the down arrow next to the rental payment option")
    public void click_on_the_down_arrow_to_the_rental_payment_option() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on the down arrow for rental payment options");
        pdpPage.clickOnPriceOptionChakraIcon();
        Log.info("Clicked on rental payment option drop arrow button");
    }

    @Then("the following list of rental payment options are displayed:")
    public void verify_list_of_rental_payment_options_are_displayed(List<String> expectedRentalOptions) {
        ExtentCucumberAdapter.addTestStepLog("Verifying list of rental payment options are displayed");
        for (String rentalOptions : expectedRentalOptions) {
            softAssert.assertTrue(pdpPage.isRentalPaymentOptionsDisplayed(rentalOptions),
                    "Rental payment option is " + rentalOptions + " NOT displayed");
            Log.info("Rental payment option " + rentalOptions + " is displayed");
        }
    }

    @Then("the following selected rental option product rates are updated accordingly")
    public void verify_selected_product_rates_are_updated(DataTable dataTable) {
        ExtentCucumberAdapter.addTestStepLog("Verifying selected rental option product rates are updated");
        List<Map<String, String>> credentialsList = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> credentials : credentialsList) {
            String expRentalOption = credentials.get("RentalOptions");
            String expRentalPrice = credentials.get("RentalPrice");
            pdpPage.clickOnPriceOptionChakraIcon();
            pdpPage.clickOnRentalPaymentOptionRadioButton(expRentalOption);
            String actRentalPrice = pdpPage.getRentalPaymentPrice();
            Log.info(expRentalOption + " rental price " + actRentalPrice);
            softAssert.assertEquals(actRentalPrice, expRentalPrice,
                    expRentalOption + " Rental option rates " + actRentalPrice + " are NOT updated");
            Log.info(expRentalOption + " Rental option rates " + actRentalPrice + " are updated");
        }
    }

    @When("the user click on cart link button")
    public void the_user_click_on_cart_link_button() {
        ExtentCucumberAdapter.addTestStepLog("User clicks on cart link button");
        pdpPage.clickOnCartLinkButton();
        Log.info("Clicked on cart link button");
    }

    @Then("verify ProductDetails section is displayed")
    public void verify_ProductDetails_section_is_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying ProductDetails section is displayed");
        Log.info("Verify ProductDetails section");
        pdpPage.verifyProductDetailsSectionIsDisplayed();
    }

    @Then("verify Specifications section is displayed")
    public void verify_specifications_section_is_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Specifications section is displayed");
        Log.info("Verify Specifications section");
        pdpPage.verifySpecificationSectionIsDisplayed();
    }

    @Then("verify Easy Payment Options section is displayed")
    public void verify_EasyPaymentOptions_section_is_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Easy Payment Options section is displayed");
        Log.info("Verify Easy Payment Options section");
        pdpPage.verifyEasyPaymentOptionsSectionIsDisplayed();
    }

    @Then("verify Ownership Options For You section is displayed")
    public void verify_OwnershipOptionsForYou_section_is_displayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Ownership Options For You section is displayed");
        Log.info("Verify Ownership Options For You section");
        pdpPage.verifyOwnershipOptionsForYouSectionIsDisplayed();
    }

    @Then("verify FAQ Section data")
    public void verify_FAQ_Section_Data() {
        ExtentCucumberAdapter.addTestStepLog("Verifying FAQ Section data");
        Log.info("Verify FAQ Section Data");
        pdpPage.FAQValidationWithHashMap();
        pdpPage.FAQValidationWithHashMap();
    }

    @Then("verify price breakdown of product")
    public void verify_price_breakdown_of_product() {
        ExtentCucumberAdapter.addTestStepLog("Verifying price breakdown of product");
        Log.info("verify price breakdown of product");
        pdpPage.validateProductPriceBreakdownData("weekly");
        //pdpPage.validateProductPriceBreakdownData("semi-monthly");
    }

    @Given("I read data from the workbook {string} sheet {string}")
    public void i_read_data_from_excel(String workbookName, String sheetName) {
        //ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Reading data from workbook: " + workbookName + ", sheet: " + sheetName);
        Log.info("---------------I read data from the workbook---------step called--------");
        String filePath = "src/test/resources/testdata/" + workbookName + ".xlsx"; // Adjust path as needed
        excelData = ExcelReader.readExcelData(filePath, sheetName);
        for (String testCaseKey : excelData.keySet()) {
            System.out.println("row Number to read==> " + testCaseKey);

            Map<String, String> rowData = excelData.get(testCaseKey);
            System.out.println("rowData: " + rowData);
        }
    }

    @Then("I am reading the test data {int} from workbook sheet name {string} for Test Scenario of {string}")
    public void use_data(int rowNumber, String sheetName, String testName) {
//        ExtentCucumberAdapter.addTestStepLog("Reading row " + rowNumber + " from sheet: " + sheetName);
        Log.info("---------------I am reading the row data " + rowNumber + "from workbook sheet name" + sheetName + "---------step called--------");

        System.out.println("-------------------sheetName::" + sheetName);
        System.out.println("-------------------rowNumber::" + rowNumber);
        //int actualRowNoToRead = rowNo+1; //considering first row a header row
        Map<String, String> userData = excelData.get(rowNumber + "");

        String executionFlag = userData.getOrDefault("Execute", "");
        if (executionFlag.equalsIgnoreCase("No")) {
            // Mark Cucumber scenario as skipped
            ExtentCucumberAdapter.addTestStepLog("Skipping current scenario execution because Execute = No in data sheet.");
            throw new SkipException("Skipping scenario execution because Execute is set to 'No' in Excel for Row: " + rowNumber);
        } else {
            Map<String, String> rowData = excelData.get(rowNumber + "");

            // Example usage
            String productId = rowData.get("Product Id");
            String zipcode = rowData.get("Zipcode").trim();
            String productType = rowData.get("Product Type"); //RAC, EA, Ashley
            String productCategory = rowData.get("Product Category"); //home appliance, tiers, jewellery
            String productCondition = rowData.get("Product Condition");//new, used,
            String productName = rowData.get("ProductName");
            String productData = rowData.get("ProductData");
            String productBreakDown = rowData.get("Product Break Down");
            String rentalPaymentFrequency = rowData.get("Rental Payment Frequency");
            String startOrder = rowData.get("Start Order");
            String availability = rowData.get("Availability"); //In Stock, out of stock, Special Order, Online only
            String wishlistAccess = rowData.get("Wishlist Access");
            String shareLink = rowData.get("Share Link");
            String staticData = rowData.get("StaticData"); //Product details, specifications, easy payment options, ownership in
            String faqData = rowData.get("FAQData");
            String jiraUserStory = rowData.get("Jira User Story");
            String tcName = rowData.get("TC Name");
            String promoBanner = rowData.get("Promo Banner");
            String priceTagType = rowData.get("Price Tag Type");
            String inventorySource = rowData.get("Inventory Source");
            String viewInventory = rowData.get("View Inventory");
            String breadCrumb = rowData.get("Bread Crumb");
            String addToCart = rowData.get("Add To Cart");
            String relatedCategories = rowData.get("Related Categories");
            String shopSimilarItems = rowData.get("Shop Similar Items");
            String discount = rowData.get("Discount");
            String youMayAlsoLike = rowData.get("YouMayAlsoLike");
            String needItSooner = rowData.get("NeedItSooner");
            String rentalPaymentTooltips = rowData.get("RentalPaymentTooltips");
            String updateStoreZipCode = rowData.get("UpdateStoreZipCode").trim();
            String wasIsPricing = rowData.get("WasIs Pricing").trim();
            String brandNameAsLink = rowData.get("BrandNameAsLink").trim();
            // Optional: Print for verification
            System.out.println("Product ID: " + productId);
            System.out.println("Zipcode: " + zipcode);
            System.out.println("Product Name: " + productName);
            System.out.println("Product Data: " + productData);
            System.out.println("Product Type: " + productType);
            System.out.println("Product Category: " + productCategory);
            System.out.println("Product Condition : " + productCondition);
            System.out.println("Product Break Down: " + productBreakDown);
            System.out.println("Rental Payment Frequency: " + rentalPaymentFrequency);
            System.out.println("Start Order: " + startOrder);
            System.out.println("Availability: " + availability);
            System.out.println("Static Data: " + staticData);
            System.out.println("FAQ Data: " + faqData);
            System.out.println("Jira User Story: " + jiraUserStory);
            System.out.println("Test Case Name: " + tcName);
            System.out.println("Promo Banner: " + promoBanner);
            System.out.println("Price Tag Type: " + priceTagType);
            System.out.println("Product Condition: " + productCondition);
            System.out.println("Share Link: " + shareLink);
            System.out.println("Inventory Source : " + inventorySource);
            System.out.println("View Inventory: " + viewInventory);
            System.out.println("Bread Crumb: " + breadCrumb);
            System.out.println("Add To Cart: " + addToCart);
            System.out.println("Related Categories: " + relatedCategories);
            System.out.println("Shop Similar Items: " + shopSimilarItems);
            System.out.println("Discount: " + discount);
            System.out.println("YouMayAlsoLike: " + youMayAlsoLike);
            System.out.println("NeedItSooner: " + needItSooner);
            System.out.println("RentalPaymentTooltips: " + rentalPaymentTooltips);
            System.out.println("UpdateStoreZipCode: " + updateStoreZipCode);
            System.out.println("wasIsPricing::"+wasIsPricing);
            System.out.println("brandNameAsLink::"+brandNameAsLink);

            //Enter zipcode data to find the store and validate zipcode data
            if (!zipcode.isEmpty()) {

                ExtentCucumberAdapter.addTestStepLog("Starting zipcode functionality and validation");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "For given: " + zipcode);
                Log.info("-----------calling zipcode functionality---------");
                homePage.clickOnFindYourStoreButton();
                homePage.enterZipCode(zipcode);
                homePage.clickOnGOButton();
                homePage.clickOnMakeThisMyStoreLink();

                softAssert.assertTrue(homePage.isPricingForTextDisplayed("Pricing for"), "Pricing for text NOT displayed");
                softAssert.assertTrue(homePage.isPricingForTextDisplayed(zipcode), "Zipcode text NOT displayed");
                softAssert.assertTrue(homePage.selectedZipcodeIsDisplayed(zipcode), "selected zipcode is not populated");
                ExtentCucumberAdapter.addTestStepLog("zipcode data validation completed");
            }

            if (!productId.isEmpty()) {
                Log.info("-----------searching product with productId---------");
                ExtentCucumberAdapter.addTestStepLog("Navigation to PDP page entry step");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Searching product with productId::\n" + productId);
                homePage.searchProduct(productId);
                ExtentCucumberAdapter.addTestStepLog("PDP page is displayed after product search");
            } else {
                ExtentCucumberAdapter.addTestStepLog("productId should not be empty, please enter valid productId");
                Log.info("-----------searching product with productId failed---------");
                System.out.println("productId Data: " + productId);
                softAssert.fail("productId should not be empty, please enter valid productId");
                throw new SkipException("Skipping scenario execution because productId " + productId + "to search is empty in Excel for Row: " + rowNumber);
            }

            //if product category is Tires check this
            if (productCategory.trim().equalsIgnoreCase("tires")) {
                ExtentCucumberAdapter.addTestStepLog("Validating functionality for product category: Tiers");
                Log.info("----------calling tires functionality------");

                //bread crumb have tiers
                ExtentCucumberAdapter.addTestStepLog("Product Category " + productCategory + "is Displayed");
                softAssert.assertTrue(pdpPage.isPDPDisplayed(productCategory.trim()), productCategory.trim() + " is NOT loaded successfully");

                ExtentCucumberAdapter.addTestStepLog("Validating Product Details section has below text: ");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + ReportHelper.addSpace() + tiresProductDetailsData);
                softAssert.assertTrue(pdpPage.isProductDetailsTextDisplayed(tiresProductDetailsData), "correct product details NOT displayed");
                pdpPage.validateProductPriceIsNotDisplayed();

                ExtentCucumberAdapter.addTestStepLog("Add to cart button is NOT displayed: ");
                softAssert.assertTrue(pdpPage.isAddToCartButtonIsDisplayed(), "Add to cart button is NOT displayed");

//                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Validating ShopSimilarItems Link");
//                pdpPage.validateShopSimilarItemFunctionalityForTiersOrJewellery();

                ExtentCucumberAdapter.addTestStepLog("Start order button is NOT displayed: ");
                softAssert.assertTrue(pdpPage.isStartOrderButtonIsDisplayed(), "Start order button is NOT displayed");

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Entering the ZipCode: " + "79761");
                pdpPage.enterZipcodeAndClickOnGo("79761");
                pdpPage.isStoreLocatorPopupDisplayed();
                pdpPage.enterZipCodeInStoreLocatorPopup("79761");
                homePage.clickOnMakeThisMyStoreLink();
//              softAssert.assertTrue(homePage.selectedZipcodeIsDisplayed(zipcode),"selected zipcode is not populated");

                Log.info("----------------Link redirection to page: payfone----------------");
                ExtentCucumberAdapter.addTestStepLog("Link redirection to page: payfone ");
                softAssert.assertTrue(homePage.isLinkRedirectsToTheOtherPageSameBrowserTab("payfone"), "NOT redirected to page url: payfone");

                Log.info("----------------Browser back button navigated----------------");
                ExtentCucumberAdapter.addTestStepLog("Browser back button navigated ");
                basePage.navigateBack();
                pdpPage.validateProductPriceIsNotDisplayed();
//                homePage.browserBack();
                ExtentCucumberAdapter.addTestStepLog("Redirected to Payfone and validated breadcrumb");
            }

            if (productCategory.trim().equalsIgnoreCase("benefit plus")) {
                ExtentCucumberAdapter.addTestStepLog("Validating Benefit Plus page details");
                Log.info("----------calling benefit plus functionality------");
                softAssert.assertTrue(pdpPage.benefitsPlusPageIsDisplayed(), "Benefit plus page is not displayed");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "select store text is not displayed");
                softAssert.assertTrue(pdpPage.selectStoreTextDisplayed(), "select store text is not displayed on PDP page");

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Entering the ZipCode: " + zipcode);
                pdpPage.enterZipcodeAndClickOnGo("79761");
                pdpPage.isStoreLocatorPopupDisplayed();
                pdpPage.enterZipCodeInStoreLocatorPopup("79761");
                homePage.clickOnMakeThisMyStoreLink();

                Log.info("-----------validating Product Name and Price ---------");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "validating product name contains " + "Benefit Plus");
                pdpPage.verifyProductNameContains("Benefit Plus");

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "validating product price is displayed");
                softAssert.assertTrue(pdpPage.priceIsDisplayed(), "price is not displayed for benefit plus page");

                Log.info("----------------click on weekly payment option----------------");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "click on payment options");
//                pdpPage.pyamentOptionSelector("Bi-weekly");
//                pdpPage.pyamentOptionSelector("semi-monthly");
//                pdpPage.pyamentOptionSelector("monthly");

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "validating sunday sky video status ");
                softAssert.assertTrue(pdpPage.sundaySkyVideoIsDisplayed(), "sunday sky video is not displayed");

                ExtentCucumberAdapter.addTestStepLog("Benefit Plus validation completed with pricing and video check");
            }

            if (addToCart.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating add to cart functionality");
                Log.info("----------------add to cart functionality validation-----------");
                pdpPage.validateAddToCartFunctionality();
            }

            if (productData.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating product data including brand name and image");
                Log.info("----------------Brand Name, product name, price, model number image is displayed-------------");
                pdpPage.validateBrandNameProductNameModelPrice();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating product images are displayed");
                Log.info("---------verify product images are displayed ----------");
                if (productType.trim().equalsIgnoreCase("bundle")) {
                    ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating product images for Bundle product displayed ");
                    Log.info("---------verify product images for Bundle product displayed ----------");
                    pdpPage.validateBundleProductImage();
                    pdpPage.validateProductRentalDropdownSelectionFunctionality("weekly");
                } else {
                    pdpPage.verifyProductImagesAreDisplayed();
                }
            }

            if (!availability.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Validating product availability: " + availability);
                Log.info("----------------validating availability data -----------------");
                pdpPage.validateAvailabilityOfProduct(availability);
            }

            if (availability.equalsIgnoreCase("special order") && !productType.trim().toLowerCase().contains("ea")) {
                ExtentCucumberAdapter.addTestStepLog("Validating special zipcode " + zipcode + " order delivery details" + SPECIAL_ORDER_DELIVERY_VALUE);
                Log.info("----------------validating special order zipcode and delivery date data -----------------");
                pdpPage.validateDeliveryDataAndZipCode(zipcode, SPECIAL_ORDER_DELIVERY_VALUE);
            }

            //updated method to check ea product contains brand name from product type data
            if (productType.trim().toLowerCase().contains("ea")) {
                ExtentCucumberAdapter.addTestStepLog("Validating EA product details");
                Log.info("----------------validating EA product zipcode and delivery date values -----------------");

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating brand name contains" + productType.split("-")[1]);
                pdpPage.validateBrandName(productType.split("-")[1].trim());

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Need It Sooner Text");
                pdpPage.validateNeedItSoonerDisplayed();

                ExtentCucumberAdapter.addTestStepLog("Validating EA product delivery details:");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + EA_ONLINE_ONLY_DELIVERY_VALUE);
                pdpPage.validateDeliveryDataAndZipCode(zipcode, EA_ONLINE_ONLY_DELIVERY_VALUE);
            }

            if (shopSimilarItems.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating ShopSimilarItems Link");
                Log.info("----------------Validating Shop Similar Items Link Functionality-----------------");

                if (productCategory.trim().equalsIgnoreCase("jewellery"))
                    pdpPage.validateShopSimilarItemFunctionalityForTiersOrJewellery();
                else
                    pdpPage.validateShopSimilarItemFunctionality();
            }

            if (productType.trim().equalsIgnoreCase("ashley")) {
                ExtentCucumberAdapter.addTestStepLog("Validating Ashley product brand Name displayed as: " + productType.trim());
                Log.info("----------------validating ashley product brand Name -----------------");
                pdpPage.validateBrandName(productType.trim());
            }

            if (!priceTagType.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Validating product tag type: " + priceTagType);
                Log.info("----------------validating priceTagType data -----------------");
                //"clearance"
                pdpPage.validateProductTagType(priceTagType);
            }

            if (productCondition.equalsIgnoreCase("new")) {
                ExtentCucumberAdapter.addTestStepLog("Validating product condition: " + productCondition);
                Log.info("----------------validating Product Condition as New or Used -----------------");
                pdpPage.validateProductCondition(productCondition.trim());
            }

            if (productCondition.equalsIgnoreCase("Pre-Rented")) {
                ExtentCucumberAdapter.addTestStepLog("Validating Pre-Rented product condition: " + productCondition);
                Log.info("----------------validating Product Condition as New or Used -----------------");
                pdpPage.validatePreUsedProductData();
            }

            if (promoBanner.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating promo banner is displayed");
                Log.info("-------------Validating promo banner functionality------------");
                pdpPage.verifyPromoBannerData();
            }

            if (productType.equalsIgnoreCase("bundle")) {
                ExtentCucumberAdapter.addTestStepLog("Validating bundle product functionality");
                Log.info("-------------Validating Bundle functionality------------");
                pdpPage.verifyBundleData();
            }

            if (wishlistAccess.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating wishlist functionality");
                Log.info("-------------Validating wishlist functionality------------");
                pdpPage.validateWishlistFunctionality();
            }

            if (shareLink.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating share link functionality");
                Log.info("-------------Validating share link data------------");
                softAssert.assertTrue(pdpPage.shareOptionDisplayedOnPDP(), "Share option displayed on PDP");
            }

            if (productCategory.equalsIgnoreCase("Jewellery")) {
                ExtentCucumberAdapter.addTestStepLog("Validating Jewellery product category");
                //ring product is checked
                Log.info("-------------Validating productCategory jewellery data------------");
                pdpPage.validateJewelleryProduct();
            }

            //to validate home store or Geo pool data
            if (!inventorySource.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Validating inventory source data: " + inventorySource);
                Log.info("-------------Validating inventory source data------------");
                pdpPage.validateInventorySource(inventorySource);
            }

            if (viewInventory.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating view inventory data");
                Log.info("-------------Validating viewInventory functionality ------------");
                pdpPage.validateViewAllInventoryData();
            }

            if (availability.trim().equalsIgnoreCase("out of stock")) {
                ExtentCucumberAdapter.addTestStepLog("Validating out-of-stock product behavior");
                Log.info("-------------Validating add to cart functionality not displayed for out of stock product ------------");
                pdpPage.validateOutOfStockProductData();
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Email Notification");
                pdpPage.validateEmailNotify();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Add to cart and start order buttons not Displayed");
                pdpPage.validateAddToCartShouldNotDisplayForOutOfStock();
            }
            if (breadCrumb.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating breadcrumb display");
                Log.info("-------------Validating breadCrumb data for given product ------------");
                pdpPage.validateBreadCrumbIsDisplayed();
            }

            if (needItSooner.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating 'Need It Sooner' text displayed");
                pdpPage.validateNeedItSoonerDisplayed();
            }

            if (rentalPaymentTooltips.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Product Price Breakdown Static Data");
                pdpPage.validatePaymentDetailsStaticData();
            }

            if (!productBreakDown.equalsIgnoreCase("no") && !productBreakDown.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Validating product price breakdown functionality");
                Log.info("-------------Validating product price Break Down functionality for given product ------------");

//                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Validating 'Shop Similar Item' displayed");
//                pdpPage.validateShopSimilarItemFunctionality();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Product Rental Dropdown Selection Functionality");
                pdpPage.validateProductRentalDropdownSelectionFunctionality(rentalPaymentFrequency);

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Product Price Breakdown calculations");
                pdpPage.validateProductPriceBreakdownData(rentalPaymentFrequency);

            }

            if (!updateStoreZipCode.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Validating Store Zipcode update on PDP page");
                Log.info("-------------Validating Store Zipcode update on PDP page ------------");
                pdpPage.clickOnStorePostalCodeLink();
                pdpPage.isStoreLocatorPopupDisplayed();
                pdpPage.enterZipCodeInStoreLocatorPopup(updateStoreZipCode);
                homePage.clickOnMakeThisMyStoreLink();
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validating Updated store zip code on Blue Banner page:" + updateStoreZipCode);
                softAssert.assertTrue(homePage.isPricingForTextDisplayed(updateStoreZipCode), "Zipcode text NOT displayed");

            }

            //To validate Product details, Specification, OwnershipOptionsForYou,EasyPaymentOptions section
            if (staticData.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating static data sections: Product Details, Specifications, Easy Payments, Ownership Options");
                Log.info("-------------Validating product details, specifications, easy payment options, ownership options functionality for given product ------------");
                pdpPage.verifyProductDetailsSectionIsDisplayed();
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validation of Product Details Completed");

                pdpPage.verifySpecificationSectionIsDisplayed();
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validation of Specifications Completed");

                pdpPage.verifyEasyPaymentOptionsSectionIsDisplayed();
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validation of Easy Payment Options Completed");

                pdpPage.verifyOwnershipOptionsForYouSectionIsDisplayed();
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Validation of Ownership Options For YouSection Completed");

            }

            if (!startOrder.trim().equalsIgnoreCase("no") && !startOrder.isEmpty()) {
                ExtentCucumberAdapter.addTestStepLog("Validating Start Order functionality");
                Log.info("-------------Validating startOrder functionality for given product ------------");
                pdpPage.validateStartOrderFunctionality();
            }

            //to validate FAQ section
            if (faqData.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating FAQ section");
                Log.info("-------------Validating faqData section functionality for given product ------------");
                pdpPage.FAQValidationWithHashMap();
            }

            if (discount.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating Discount price data");
                Log.info("-------------Validating Discount price section displayed in PDP page ------------");
                basePage.refreshPage();
                pdpPage.validatePriceDisplayedForProduct();
            }

            if (youMayAlsoLike.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Verifying 'You may also like' section is displayed on PDP");
                softAssert.assertTrue(pdpPage.youMayAlsoLikeDisplayedOnPDP(), "You may also like section displayed on PDP");
                Log.info("You may also like section displayed on PDP");
            }

            if (relatedCategories.trim().equalsIgnoreCase("yes")) {
                ExtentCucumberAdapter.addTestStepLog("Validating Related Categories section display");
                Log.info("-------------Validating Related Categories Section functionality ------------");
                pdpPage.validateRelatedCategoriesSection();
            }

            if(!wasIsPricing.isEmpty()) {
                pdpPage.validateIsWasPricingOfProduct();
            }

            if (availability.equalsIgnoreCase("in stock nearby")) {
                ExtentCucumberAdapter.addTestStepLog("Validating special zipcode " + zipcode + " order delivery details" + GEO_POOL_DELIVERY_VALUE);
                Log.info("----------------validating special order zipcode and delivery date data -----------------");
                pdpPage.validateDeliveryDataAndZipCode(zipcode, GEO_POOL_DELIVERY_VALUE);
            }

            //SFCC-2206
            if(brandNameAsLink.equalsIgnoreCase("yes")){
                pdpPage.brandNameLinkValidation();
            }

            pdpPage.validateAllSoftAssertions();


        }
    }

    //---------------- charan steps-------------
    @Then("verify the size guide link functionality On PDP")
    public void verifyTheSizeGuideLinkFunctionalityOnPDP() {
        ExtentCucumberAdapter.addTestStepLog("Verifying size guide link opens PDF in new tab");
        Log.info("----------- verify PDF opened in new tab----------");
        pdpPage.validateSizeGuideLinkFunctionality();
    }

    @Then("verify product images displayed")
    public void verifyProductImagesDisplayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product images are displayed");
        Log.info("---------verify product images are displayed ----------");
        pdpPage.verifyProductImagesAreDisplayed();
    }

    @And("the user verify the wishlist button functionality")
    public void theUserVerifyTheWishlistButtonFunctionality() {
        ExtentCucumberAdapter.addTestStepLog("Verifying wishlist button functionality");
        pdpPage.clickOnWishlistButton();
        Log.info("Clicked on wishlist button on PDP");
        softAssert.assertTrue(pdpPage.itemIsSavedToCart(), "Item is saved to cart");
        Log.info("Item is saved to cart");
        pdpPage.closeModalOnPDP();
        Log.info("close saved to cart modal");
        pdpPage.clickOnWishlistButton();
    }

    @Then("the user should see product name price and model number on pdp")
    public void theUserShouldSeeProductNamePriceAndModelNumberOnPdp() {
        ExtentCucumberAdapter.addTestStepLog("Verifying product name, price, and model number are displayed");
        Log.info("----------verify product name or model number is displayed---------");
        pdpPage.validateProductNameAndPriceAndModelNameDisplayed();
    }

    @Then("verify clearance label is displayed for the product")
    public void verifyClearanceLabelIsDisplayedForTheProduct() {
        ExtentCucumberAdapter.addTestStepLog("Verifying clearance label is displayed");
        Log.info("---------------verify clearance label is displayed for the product-------------");
        pdpPage.validateClearanceLabel();
    }

    @Then("the user enter email to get notified for the deals and offers")
    public void theUserEnterEmailToGetNotifiedForTheDealsAndOffers() {
        ExtentCucumberAdapter.addTestStepLog("User enters email to get notified for deals and offers");
        Log.info("---------------the user enter email to get notified for the deals and offers-------------");
        pdpPage.validateNotifyEmailFunctionality();
    }

    @Then("verify view all inventory component on PDP")
    public void verifyViewAllInventoryComponentOnPDP() {
        ExtentCucumberAdapter.addTestStepLog("Verifying View All Inventory component on PDP");
        Log.info("---------------verify view all inventory component on PDP-------------");
        pdpPage.validateViewAllInventoryData();
    }

    @Then("verify breadcrumb is displayed on page")
    public void verifyBreadcrumbIsDisplayedOnPage() {
        ExtentCucumberAdapter.addTestStepLog("Verifying breadcrumb is displayed on page");
        Log.info("-----------verify breadcrumb is displayed on page---------");
        pdpPage.breadCrumbDisplayedOnPage();
    }

    @Then("verify promo banner is displayed on page")
    public void verifyPromoBannerIsDisplayedOnPage() {
        ExtentCucumberAdapter.addTestStepLog("Verifying promo banner is displayed on page");
        Log.info("-----------verify promo banner is displayed on page---------");
        pdpPage.promoBannerDisplayedOnPage();
    }

    @Then("verify related categories section is displayed")
    public void verifyRelatedCategoriesSectionIsDisplayed() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Related Categories section is displayed");
        Log.info("-----------related categories section with list of items displayed on PDP---------");
        pdpPage.validateRelatedCategoriesSection();
    }

    @Then("verify add to cart and start order buttons not displayed on oos")
    public void verifyAddToCartAndStartOrderNotDisplayedForOOS() {
        ExtentCucumberAdapter.addTestStepLog("Verifying Add to Cart and Start Order buttons are not displayed for out-of-stock items");
        Log.info("------verify add to cart and start order buttons displayed on oos---");
        pdpPage.validateAddToCartAndStartOrderNotDisplayed();
    }

    @Then("Brand Name is displayed as a hyperlink from {string} at row {int}")
    public void verify_brand_name_is_displayed_as_a_hyperlink_from_sheet(String sheetName, int rowNumber) throws IOException, IOException {
        /*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Payments.xlsx");
        Map<String, String> userData = excelReader.getRowData(sheetName, rowNumber);
        String brandName = userData.getOrDefault("BrandName", "");*/

        Map<String, String> rowData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String brandName = rowData.getOrDefault("BrandName", "").trim();

        Assert.assertTrue(pdpPage.isBrandNameDisplayedAsHyperLink(brandName), "Brand name: " + brandName + " NOT displayed as hyper link");
        Log.info("Brand Name: " + brandName + " is displayed as hyper link");
    }

    @When("the user clicks on Start Order button")
    public void the_user_click_on_start_order_button() {
        Log.info("Click Start order button");
        pdpPage.clickStartOrderButton("Start Order");
    }

    @When("the user clicks on {string} link on pdp")
    public void click_on_link_pdp(String linkName) {
        Log.info("Click on link name: " + linkName);
        pdpPage.clickOnDoNotHaveMobNumLink();
    }

    @Then("the product type {string} is displayed")
    public void the_product_type_bp_is_displayed(String productType) {
        Log.info("Product type: " + productType + " is displayed ");
        Assert.assertTrue(basePage.isTextDisplayed(productType),
                "Product type: " + productType + " is NOT displayed");
        Assert.assertTrue(pdpPage.isBenefitsPlusProductInfoTitleDisplayed(),
                "Product info title : " + productType + " is displayed");
    }


    @When("the user clicks on item cart section close button")
    public void the_user_clicks_on_item_cart_close_button() {
        pdpPage.clickOnItemCartCloseButton();
        Log.info("Clicked on item cart section close button");
    }

    @When("the user pick a {string} product")
    public void pick_a_tire_product(String productID) {
        DriverFactory.getDriver().get("https://rentacenter-development.mobify-storefront.com/us/en/p/" + productID);
        Log.info("Navigate to " + productID + " Product page");
    }

    @When("the user clicks the 'Go' button on the PDP")
    public void the_user_clicks_on_go_button_on_the_PDP() {
        Log.info("Click on GO button ");
        pdpPage.clickOnGO();
    }

}

