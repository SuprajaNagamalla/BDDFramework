package parallel.stepdefinitions;
import java.time.Duration;
import java.util.*;

import com.pages.BasePage;
import com.pages.ClpPage;
import com.qa.util.Constants;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.HomePage;
import com.qa.factory.DriverFactory;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.ExcelUtils;

//import static com.pages.ClpPage.driver;
import static com.pages.ClpPage.driver;
import static com.qa.util.ElementActions.waitForSecs;
import static org.junit.Assert.assertEquals;

public class CLPPageSteps{
    private static final Logger Log = LoggerHelper.getLogger();
    private ClpPage ClpPage = new ClpPage(DriverFactory.getDriver());
    private HomePage HomePage = new HomePage(DriverFactory.getDriver());
    ExcelUtils excelUtils = new ExcelUtils();
    //private WebDriver driver;

    //WebDriver driver = DriverFactory.getDriver();

    //Methods
    @Then("User verify the Shop by Brand section is displayed")
    public void UserShouldSeeTheShopByBrandSectionIsDisplayed() {
        Assert.assertTrue(ClpPage.IsShopByBrandSectionDisplayed(), "Shop by Brand section is NOT displayed");
        Log.info("Shop by Brand section is displayed");
    }

    @When("the user clicks on a brand logo in the Shop by Brand section")
    public void the_user_click_on_show_more_link() {
        ClpPage.ClickOnBrandLogo();
        waitForSecs(10);
        Log.info("Clicked on brand logo");

    }

    @When("I navigate to L1 category {string} in the menu")
    public void iClickOnTheCategory(String category) throws InterruptedException {
        ClpPage.GetCategory(category);
    }
//    @When("I navigate to {string} category")
//    public void iClickOnTheCategory(String category) throws InterruptedException {
//        WebDriver driver = DriverFactory.getDriver();
//        ClpPage.GetCategory(driver, category);
//    }

//    @Given("the user is redirected to the PLP Page")
//    public void the_user_opens_url() {
//    DriverFactory.getDriver().get(ClpPage.categoryTitle);
//    Log.info("Navigate to URL: "+Constants.categoryTitle);
//    ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"URL: " + Constants.BASE_URL);
//}

//    @Then("verify user is redirected to the PLP Page")
//    public void user_redirected_to_the_redirected_to_the_PLP() {
//        Assert.assertTrue(ClpPage.IsUserRedirectedToThePLP(),"User is NOT redirected to the PLP Page");
//        Log.info("User is redirected to the PLP Page");
//
//    }

    @Then("the user verifies that the Category specific PLP Banner is displayed")
    public void UserShouldSeeTheCategorySpecificPlpBannerIsDisplayed() {
        Assert.assertTrue(ClpPage.IsCategorySpecificPLPBannerIsDisplayed(), "SpecificPLPBanner is NOT displayed");
        Log.info("SpecificPLPBanner is displayed");
    }

    @Then("the user should see the Category Description on the Category Landing page")
    public void UserShouldSeeTheCategoryDescriptionIsDisplayed() {
        Assert.assertTrue(ClpPage.IsCategoryDescriptionDisplayed(), "Category description is NOT displayed");
        Log.info("Category description is displayed");
    }


//    @Then("I verify that a new window is opened")
//    public void i_verify_that_a_new_window_is_opened() {
//        String originalWindow = driver.getWindowHandle();
//        Set<String> windowHandles = driver.getWindowHandles();
//
//        if (windowHandles.size() > 1) {
//            System.out.println("New window opened successfully.");
//        } else {
//            throw new AssertionError("New window did not open.");
//        }
//    }


    @Then("I close the new window and return to the previous window")
    public void i_close_the_new_window_and_return_to_the_previous_window() {
        WebDriver driver = DriverFactory.getDriver(); // Retrieve instance

        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(originalWindow);
    }


    @Then("the user verifies that each category label is displayed on the Category Landing page")
    public void verify_Category_Label() {
        List<WebElement> displayedProducts = ClpPage.getCategoryLabels();
        Assert.assertFalse(displayedProducts.isEmpty(), "each category label is not displayed");
        Log.info("each category label is displayed");
    }

    @Then("the user verifies that subcategories appear on the Category Landing page")
    public void verify_SubCategory_Label() {
        List<WebElement> displayedProducts = ClpPage.getSubCategory();
        Assert.assertFalse(displayedProducts.isEmpty(), "list of subcategories not displayed");
        Log.info("list of subcategories displayed");
    }

    @Then("the user verifies that components for the selected category are displayed on the CLP")
    public void verify_Components() {
        List<WebElement> displayedProducts = ClpPage.getComponents();
        Assert.assertFalse(displayedProducts.isEmpty(), "components for the selected category are not displayed");
        Log.info("components for the selected category are displayed");
    }

    @Then("user verifies l2 category {string} in the subcategory panel")
    public void verifyl2category(String l2Category) {
        Assert.assertTrue(ClpPage.GetL2Category(l2Category),
                "Expected l2 category '" + l2Category + "' does not found");
    }

    @Then("user verifies l3 category {string} in the subcategory panel")
    public void verifyl3category(String l3Category) {
        Assert.assertTrue(ClpPage.GetL3Category(l3Category),
                "Expected l3 category '" + l3Category + "' does not found");
    }

//    @And("the user sets the zoom level to {int}")
//    public void setZoomLevel(int zoomPercentage) {
//        waitForSecs(2);
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("document.body.style.zoom='" + zoomPercentage + "%'");
//
//        System.out.println("Zoom level set to " + zoomPercentage + "%");
//        waitForSecs(3);
//
//    }

    @Then("I verify that a new window is opened and user is redirected to the PLP Page")
    public void verifyNewWindowAndPLPRedirection() {
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        if (windowHandles.size() > 1) {
            System.out.println("New window opened successfully.");
        } else {
            throw new AssertionError("New window did not open.");
        }

        for (String window : windowHandles) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Assert.assertTrue(ClpPage.IsUserRedirectedToThePLP(), "User is NOT redirected to the PLP Page");
        Log.info("User is redirected to the PLP Page");
    }
    @Then("the user clicks on Read More link on CLP")
    public void the_user_click_on_read_more_link() {
        ClpPage.clickOnReadMoreLink();
        Log.info("Clicked on ReadMoreLink");
    }

    @Then("the user clicks on the Read Less link to collapse the details on CLP")
    public void the_user_click_on_read_less_link() {
        ClpPage.clickOnReadLessLink();
        Log.info("Clicked on ReadLessLink");
    }

    @Then("user verify that a new window is opened and user is redirected to the desktop banner")
    public void verifyNewWindowAndDesktopRedirection() {
        String originalWindow = driver.getWindowHandle();
        Set<String> windowHandles = driver.getWindowHandles();

        if (windowHandles.size() > 1) {
            System.out.println("New window opened successfully.");
        } else {
            throw new AssertionError("New window did not open.");
        }

        for (String window : windowHandles) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        Assert.assertTrue(ClpPage.IsUserRedirectedToDesktopBanner(), "User is NOT redirected to the desktop banner");
        Log.info("User is redirected to the desktop banner");
    }
}