package parallel.stepdefinitions;

import java.time.Duration;
import java.util.*;

import com.pages.BasePage;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.HomePage;
import com.pages.PlpPage;
import com.qa.factory.DriverFactory;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelUtils;

import static org.junit.Assert.assertEquals;

public class PlpPageSteps {
    private static final Logger Log = LoggerHelper.getLogger();
    //public static WebDriver driver;
    private PlpPage PlpPage = new PlpPage(DriverFactory.getDriver());
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private BasePage basePage = new BasePage(DriverFactory.getDriver());

    ExcelUtils excelUtils = new ExcelUtils();



    @Then("Verify product prices are sorted in {string} order")
    public void verifyProductPricesSorting(String sortingOrder) {
        List<Double> actualPrices = PlpPage.getAllProductPrices();

        // Validate if prices are retrieved
        Assert.assertFalse(actualPrices.isEmpty(), "No product prices found on the page!");

        // Create sorted copies for comparison
        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);

        if (sortingOrder.equalsIgnoreCase("Low to High")) {
            expectedSortedPrices.sort(Double::compareTo);
        } else if (sortingOrder.equalsIgnoreCase("High to Low")) {
            expectedSortedPrices.sort(Comparator.reverseOrder());
        } else {
            throw new IllegalArgumentException("Invalid sorting order provided: " + sortingOrder);
        }

        Log.info("Actual prices: " + actualPrices);
        Log.info("Expected sorted prices (" + sortingOrder + "): " + expectedSortedPrices);

        // Validate sorting order
        Assert.assertEquals(actualPrices, expectedSortedPrices, "Prices are NOT sorted in " + sortingOrder + " order!");

        Log.info("Product prices are sorted correctly in " + sortingOrder + " order.");
    }

//    @Then("Verify product prices are sorted from high to low")
//    public void productPricesShouldBeSortedHighToLow() {
//
//        List<Double> actualPrices = PlpPage.getAllProductPrices();
//        Assert.assertFalse(actualPrices.isEmpty(), "No product prices found or visible on the page");
//        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);
//        expectedSortedPrices.sort(Comparator.reverseOrder());
//        Log.info("Actual prices: " + actualPrices);
//        Log.info("Expected (sorted high to low): " + expectedSortedPrices);
//        Assert.assertEquals(actualPrices, expectedSortedPrices, "Prices are not sorted from high to low");
//        Log.info("Product prices are sorted in descending order and validated successfully.");
//    }

//    @Then("Verify product prices are sorted from low to high")
//    public void productPricesShouldBeSortedLowToHigh() {
//        List<Double> actualPrices = PlpPage.getAllProductPrices();
//
//        List<Double> expectedSortedPrices = new ArrayList<>(actualPrices);
//        expectedSortedPrices.sort(Double::compareTo);
//
//        Assert.assertEquals(actualPrices, expectedSortedPrices, "Prices are not sorted from low to high!");
//
//        Log.info("Product prices are sorted in ascending order and validated successfully.");
//    }

    @Then("Verify {int} products are displayed")
    public void verifyMoreThanExpectedProductsDisplayed(int expectedCount) {
        int actualCount = PlpPage.getVisibleProductCount();
        Assert.assertEquals(actualCount, expectedCount,
                "Expected exactly " + expectedCount + " products, but found: " + actualCount);
    }

//    @When("the user selects {string} and collapses the filter")
//    public void select_pay_schedule(String category) {
//        PlpPage.selecti(category);
//        Log.info("Select category: " + category );
//    }

    @When("the user selects the following categories and collapses the filters")
    public void user_selects_multiple_categories(io.cucumber.datatable.DataTable dataTable) {
        List<String> categories = dataTable.asList(String.class);
        for (String category : categories) {
            PlpPage.selecti(category);
        }

    }

    @Then("User verify FastestDelivery option is displayed")
    public void UserShouldSeeTheFastestDeliveryIsDisplayed() {
        Assert.assertTrue(PlpPage.IsFastestDeliveryDisplayed(), "FastestDelivery is NOT displayed");
        Log.info("FastestDelivery is displayed");
    }

    @When("the user clicks on Show More link")
    public void the_user_click_on_show_more_link() {
        PlpPage.ClickOnShowMoreLink();
        Log.info("Clicked on show more link");
    }

    @Then("User verify The items count displays the correct number of results shown and the total number of results")
    public void UserShouldSeeTheIndicatorDetailsIsDisplayed() {
        Assert.assertTrue(PlpPage.IsIndicatorDetailsDisplayed(), "Indicator details is NOT displayed");
        Log.info("Indicator details is displayed");
    }

    @Then("User verify Marketing Banner should be displayed")
    public void SeeTheMarketingBannerIsDisplayed() {
        Assert.assertTrue(PlpPage.IsMarketingBannerDisplayed(), "MarketingBanner is NOT displayed");
        Log.info("MarketingBanner is displayed");
    }

    @Then("the user should see the product label and pricing")
    public void SeeTheProductLabelAndPricingIsDisplayed() {
        Assert.assertTrue(PlpPage.IsProductLabelAndPricingDisplayed(), "Product Label And Pricing is NOT displayed");
        Log.info("Product Label And Pricing is displayed");
    }

    @Then("the user should see the filter section on the PLP")
    public void SeeTheFilterSectionIsDisplayed() {
        Assert.assertTrue(PlpPage.IsFilterSectionDisplayed(), "FilterSection is NOT displayed");
        Log.info("FilterSection is displayed");
    }

    @When("the user clicks on list view toggle")
    public void the_user_click_on_list_view_toggle() {
        PlpPage.ClickOnListViewToggle();
        Log.info("Clicked on listview");
    }

    @When("the user clicks on grid view toggle")
    public void the_user_click_on_grid_view_toggle() {
        PlpPage.ClickOnGridViewToggle();
        Log.info("Clicked on gridview");
    }

//    @Then("verify the selected category {string} matches the page title {string}")
//    public void verifySelectedCategoryMatchesPageTitle(String expectedCategory, String expectedTitle) {
//        Assert.assertTrue(
//                PlpPage.verifyTitleOfThePageMatchesTheSelectedCategory(expectedCategory, expectedTitle),
//                "Expected category '" + expectedCategory + "' and page title '" + expectedTitle + "' do not match.");
//    }

    @Then("verify the page title is {string}")
    public void verifyPageTitleIs(String expectedTitle) {
        Assert.assertTrue(
                PlpPage.verifyPageTitle(expectedTitle),
                "Expected page title '" + expectedTitle + "' does not match the actual page title."
        );
    }
    @Then("verify banner is displayed at the top of the page")
    public void SeeTheBannerIsDisplayed() {
        Assert.assertTrue(PlpPage.IsBannerDisplayed(), "banner is NOT displayed");
        Log.info("banner is displayed");
    }
    @When("I click on the {string} breadcrumb")
    public void iClickOnTheBreadcrumb(String breadcrumbName) {
        PlpPage.clickBreadcrumb(breadcrumbName);
    }

    @Then("the user is redirected to the {string} category")
    public void user_redirected_to_the_redirected_to_the_PLP(String breadcrumb) {
        Assert.assertTrue(PlpPage.IsUserRedirected(breadcrumb),"User is NOT redirected to the PLP");
        Log.info("User is redirected to the PLP");
    }

    @When("the user unselects")
    public void unselect_pay_schedule() {
        PlpPage.unselectCategory();
        Log.info("unSelect category:");
    }

    @Then("the user should see the Category Description section on the PLP")
    public void SeeTheCategoryDescriptionSectionIsDisplayed() {
        Assert.assertTrue(PlpPage.IsCategoryDescriptionSectionDisplayed(), "CategoryDescription is NOT displayed");
        Log.info("CategoryDescription is displayed");
    }

    @Then("the user clicks on Read More link")
    public void the_user_click_on_read_more_link() {
        PlpPage.ClickOnReadMoreLink();
        Log.info("Clicked on ReadMoreLink");
    }

    @Then("the user clicks on the Read Less link to collapse the details")
    public void the_user_click_on_read_less_link() {
        PlpPage.ClickOnReadLessLink();
        Log.info("Clicked on ReadLessLink");
    }

    @When("the user chooses {string} from the Rental Payment Options category")
    public void select_the_option(String option) {
        PlpPage.selectOption(option);
        Log.info("Selected: " + option);
    }

    @Then("the user verifies that {string} displayed on all product cards")
    public void i_should_see_displayed_on_all_product_cards(String expectedFrequency) {
        List<WebElement> frequencyTexts = PlpPage.getAllFrequencyTexts();

        for (WebElement textElement : frequencyTexts) {
            String actualText = textElement.getText().toLowerCase().trim();
            Assert.assertTrue(
                    actualText.contains(expectedFrequency.toLowerCase()),
                    "Expected frequency '" + expectedFrequency + "' not found in: " + actualText);
        }
    }

    @When("the user selects {string} from the list of categories")
    public void select_the_option_from_the_list_of_categories(String category) {
        PlpPage.selectOptionFromListOfCategories(category);
        Log.info("Selected option: " + category);
    }

    @When("the user selects option {string} as a filter")
    public void select(String option) {
        PlpPage.GetOption(option);
        Log.info("option Selected: " + option);
    }

    @Then("the user applying the {string} filter should update the product list accordingly")
    public void theUserSelectsFromRentalOptions(String subOption) {
        int actualCount = PlpPage.selectSubOptionAndGetProductCount(subOption);
        Log.info("Number of products displayed after selecting '" + subOption + "': " + actualCount);
        Assert.assertTrue(actualCount > 0,
                "No products were displayed after selecting suboption: " + subOption);
    }

    @When("the user clicks the CLEAR ALL button")
    public void the_user_clicks_the_clear_all_button() {
        PlpPage.ClickOnClearAllButton();
        Log.info("Clicked on ClearAllButton");
    }

    @Then("the list of products should reset to display all available items without any filters")
    public void verifyProductsAreDisplayed() {
        int actualCount = PlpPage.getProductCount();
        Assert.assertTrue(actualCount > 0, "Expected products to be displayed after clearing filters, but none were found.");
    }

    @When("the user applies a price range filter from ${int} to ${int}")
    public void applyPriceFilter(int min, int max) {
        PlpPage.setPriceRange(min, max);
    }

    @Then("only products with prices between ${int} and ${int} should be displayed")
    public void verifyProductsPriceRange(int min, int max) {
        List<Double> productPrices = PlpPage.getDisplayedPrices();
        for (double price : productPrices) {
            Assert.assertTrue(price >= min && price <= max,
                    "Product with price $" + price + " is outside the selected range $" + min + " - $" + max);
        }
    }

    @When("User selects {string} from the dropdown")
    public void selectOption(String option) {
        PlpPage.selectOptionByVisibleText(option);
    }


    @Then("The user verify product image is displayed")
    public void verify_ProductImage_is_Displayed() {
        Assert.assertTrue(PlpPage.getProductImages(),
                "Product image is NOT displayed");
        Log.info("product image is displayed");
    }

    @Then("The user verifies that Fastest Delivery toggle is OFF by default")
    public void verify_FastestDelivery_Toggle() {
        Assert.assertTrue(PlpPage.isFastestDeliveryToggleOff(), " Fastest Delivery toggle is ON by default");
        Log.info(" Fastest Delivery toggle is OFF by default.");
    }

    @Then("The user enables the Fastest Delivery toggle")
    public void enable_FastestDelivery_Toggle() {
        PlpPage.turnOnFastestDeliveryToggle();
        Log.info("Fastest Delivery toggle is turned ON successfully.");
    }

    @Then("The user verifies that the Fastest Delivery product list is displayed")
    public void verify_FastestDelivery_Products_Updated() {
        List<WebElement> displayedProducts = PlpPage.getFastestDeliveryProducts();
        Assert.assertFalse(displayedProducts.isEmpty(), "No products are displayed for Fastest Delivery option.");
        Log.info("Fastest Delivery product list is correctly updated.");
    }

    @Then("The user verify product name is displayed in the plp")
    public void UserShouldSeeTheProductNameIsDisplayed() {
        Assert.assertTrue(PlpPage.IsProductNameIsDisplayed(), "Product Name is NOT displayed");
        Log.info("Product Name details is displayed");
    }

    @When("user click on marketing banner")
    public void the_user_click_on_marketing_banner() {
        PlpPage.ClickOnMarketingBanner();
        Log.info("Clicked on marketing banner");
    }

    @When("the user adds a product to the wishlist")
    public void the_user_click_on_wishlist_icon() {
        PlpPage.ClickOnWishListButton();
        Log.info("Clicked on wishlist icon");
    }

    @Then("the user verifies that the We've saved your item! popup is displayed")
    public void UserShouldSeeThePopupIsDisplayed() {
        Assert.assertTrue(PlpPage.IsPopupDisplayed(), "We've saved your item! Popup is NOT displayed");
        Log.info("We've saved your item! popup is displayed");
    }

    @Then("the user verifies that the item is saved in the wishlist")
    public void UserShouldSeeTheSavedItemIsDisplayed() {
        Assert.assertTrue(PlpPage.GetSavedItems(), "saved item is NOT displayed");
        Log.info("saved item is displayed");
    }

    @Then("the user removes a product from the wishlist")
    public void the_user_click_on_wishlist_button() {
        PlpPage.ClickOnWishListButton();
        Log.info("Clicked on wishlist button");
    }

    @Then("the user verifies that the We've removed your item! popup is displayed")
    public void UserShouldSeeTheRemovedPopupIsDisplayed() {
        Assert.assertTrue(PlpPage.IsRemovedItemPopupDisplayed(), "We've removed your item! Popup is NOT displayed");
        Log.info("We've removed your item! popup is displayed");
    }

    @When("user verifies L2 subcategory {string}")
    public void verify_on_L2_subcategory(String L2Category) {
        PlpPage.GetL2SubCategory(L2Category);
        Log.info("verify L2 subcategory: "+L2Category);
    }

    @Then("The user verifies that {string} option is checked by default")
    public void verify_DefaultDeliveryOption_Checked(String deliveryOption) {
        Assert.assertTrue(PlpPage.isDeliveryOptionChecked(deliveryOption),
                deliveryOption + " delivery option is NOT checked by default");
        Log.info("Verified: " + deliveryOption + " delivery option is checked by default.");
    }

    @When("user click on shop all button in shop by category section")
    public void click_on_shop_all_button() {
        PlpPage.ClickOnShopAllButton();
        Log.info("Clicked on shop all button");
    }

    @When("the user scrolls to the {string} section")
    public void scroll_to_section(String sectionName) {
        PlpPage.scrollToSection(sectionName);
        Log.info("Scroll to "+sectionName);
    }

    @Then("I move the cursor to the element with locator")
    public void moveCursorToElement() {
        PlpPage.moveCursorToElement();

    }

    @Then("the user verifies that product badges are displayed on each product tile in the PLP")
    public void verify_product_badges() {
        List<WebElement> badges = PlpPage.getProductBadges();
        Assert.assertFalse(badges.isEmpty(), "No product badges are displayed on product tile");
        Log.info("product badges are displayed on each product tile");
    }

    @Then("the user checks the items in my approval range")
    public void theUserSelectsItemsInMyApprovalRange() {
        PlpPage.selectItemsInMyApprovalRange();
        Log.info("Checked 'Items In My Approval Range'");
    }

    @Then("the user verifies the price Range Up to dollar")
    public void theUserVerifiesPriceRangeUpToDollar() {
        PlpPage.verifyPriceRangeUpToDollar();
    }

    @Then("the user verifies the items in my approval range option is not displayed")
    public void theUserVerifiesItemsInMyApprovalRangeOptionIsNotDisplayed() {
        PlpPage.verifyItemsInMyApprovalRangeOptionNotDisplayed();
    }

    @Then("the user verifies that price range up to dollar is not visible when approval range is unchecked")
    public void verifyPriceRangeHiddenWhenApprovalRangeUnchecked() {
        PlpPage.verifyApprovalRangeUnchecked();
    }

    @Then("the user unchecks the items in my approval range")
    public void theUserUnchecksItemsInMyApprovalRange() {
        PlpPage.unselectItemsInMyApprovalRange();
        Log.info("Unchecked 'Items In My Approval Range'");
    }
}