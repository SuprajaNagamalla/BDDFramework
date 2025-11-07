package parallel.stepdefinitions;

import java.util.Map;

import com.pages.SignInPage;
import org.mortbay.log.Log;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BasePage;
import com.pages.HomePage;
import com.qa.factory.DriverFactory;
import com.qa.util.ExcelReader;
import com.qa.util.ReportHelper;
import com.qa.util.ScenarioContext;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import parallel.pages.CartPage;
import parallel.pages.PdpPage;

public class CartPageSteps {

    private CartPage cartPage = new CartPage(DriverFactory.getDriver());
    private HomePage homePage = new HomePage(DriverFactory.getDriver());
    private PdpPage pdpPage = new PdpPage(DriverFactory.getDriver());
    private BasePage basePage = new BasePage(DriverFactory.getDriver());
    private SignInPage signInPage = new SignInPage(DriverFactory.getDriver());

    SoftAssert softAssert = new SoftAssert();
    ScenarioContext scenarioContext = new ScenarioContext(); // Shared context
    private Map<String, Map<String, String>> excelData;

    @Given("I am reading data from the workbook {string} sheet {string}")
    public void i_am_reading_data_from_excel(String workbookName, String sheetName) {
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

    @Then("I am reading the test data {int} from Cart Workbook sheet name {string} for Test Scenario of {string}")
    public void use_data(int rowNumber, String sheetName, String testName) throws InterruptedException {
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
            String zipcode = rowData.get("Zipcode");
            String productType = rowData.get("Product Type"); //RAC, EA, Ashley
            String productCategory = rowData.get("Product Category"); //home appliance, tiers, jewellery
            String productCondition = rowData.get("Product Condition");//new, used,
            String validateScenario = rowData.get("ValidateScenario");
            String addToCart = rowData.get("Add To Cart");
            String wishlistAccess = rowData.get("Wishlist Access");
            String startOrder = rowData.get("Start Order");
            String availability = rowData.get("Availability");
            String productId2 = rowData.get("Product Id2");
            String updateStoreNo = rowData.get("UpdateStoreNo");


            //Enter zipcode data to find the store and validate zipcode data
            if (!zipcode.isEmpty()) {

                ExtentCucumberAdapter.addTestStepLog("Starting zipcode functionality and validation");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "For given: " + zipcode);
                Log.info("-----------calling zipcode functionality---------");
                homePage.clickOnFindYourStoreButton();
                homePage.enterZipCode(zipcode);
                homePage.clickOnGOButton();
                pdpPage.enterZipCodeInStoreLocatorPopup(zipcode.trim());
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

            if (validateScenario.trim().contains("empty cart data")) {
                ExtentCucumberAdapter.addTestStepLog("Validate cart page is empty after landing on PDP page");
                Log.info("-----------Validate cart page is empty after landing on PDP page---------");
                cartPage.clickOnHeaderCart();
                cartPage.validateEmptyCartData();
                cartPage.validateCartTabCount("0");

            }

            if (validateScenario.trim().contains("empty wishlist data")) {
                ExtentCucumberAdapter.addTestStepLog("Validate cart page Wishlist empty after landing on PDP page");
                Log.info("-----------Validate cart page Wishlist is empty after landing on PDP page---------");
                cartPage.clickOnHeaderCart();
                cartPage.clickOnSavedItemTab();
                cartPage.validateEmptySavedItemsData();
                softAssert.assertTrue(cartPage.validateWishlistItemsTabCount("0"), "Wish list item count not matches with value 0");
            }
            //cartPage.navigateToProductPage(productId);
            //basePage.waitForPageToLoad();

            //cartPage.clickOnViewCartAndOrder();

            if (productType.trim().equalsIgnoreCase("bundle")) {

                scenarioContext.setContext("ProductModel1RentalPaymentPrice", pdpPage.getRentalPaymentPrice().trim());
                scenarioContext.setContext("ProductModel1NoOfPayments", pdpPage.getNumberOfPaymentsValue().getText().trim());
                scenarioContext.setContext("ProductModel1TotalCostToOwn", pdpPage.getTotalCostToOwnValue().getText().trim());
                scenarioContext.setContext("ProductModel1SameAsCashPrice", pdpPage.getSameAsCashPriceValue().getText().trim());
                scenarioContext.setContext("ProductModel1EstimatedDelivery", pdpPage.getEstimatedDeliveryValue().getText().trim());

                for (int i = 1, index=0; i <= 2; i++, index++) {
                    String productNameFromPDPPage = pdpPage.getBundleProductNameByIndex(i).getText();

                    pdpPage.clickOnBundleProductsInventoryByIndex(i);


                    String bundleProductRenewalRateFromPDPPage = pdpPage.getRenewalRateOnAvailableInventoryModal().get(index).getText();
                    String bundleProductNumberOfPaymentsFromPDPPage = pdpPage.getNumberOfPaymentsOnAvailableInventoryModal().get(index).getText();
                    String bundleProductTotalCostToOwnFromPDPPage = pdpPage.getTotalCostToOwnOnAvailableInventoryModal().get(index).getText();
                    String bundleProductSameAsCashPriceFromPDPPage = pdpPage.getSameAsCashPriceOnAvailableInventoryModal().get(0).getText();

                    scenarioContext.setContext("productNameFromPDPPage" + i, productNameFromPDPPage);
                    scenarioContext.setContext("bundleProductRenewalRateFromPDPPage" + i, bundleProductRenewalRateFromPDPPage);
                    scenarioContext.setContext("bundleProductNumberOfPaymentsFromPDPPage" + i, bundleProductNumberOfPaymentsFromPDPPage);
                    scenarioContext.setContext("bundleProductTotalCostToOwnFromPDPPage" + i, bundleProductTotalCostToOwnFromPDPPage);
                    scenarioContext.setContext("bundleProductSameAsCashPriceFromPDPPage" + i, bundleProductSameAsCashPriceFromPDPPage);
                    pdpPage.closeModalOnPDP();

                }
                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Click on View cart and order");
                Log.info("----------------Click on View cart and order-----------");
                cartPage.clickOnViewCartAndOrder();

                String cartItemCount = pdpPage.getCartItemCountText().getText().trim();
                boolean countUpdated = cartItemCount.contains("1");
                ExtentCucumberAdapter.addTestStepLog("Validated Cart Item Count::" + countUpdated);
                Log.info("----------------Validated Cart Item Count-----------" + countUpdated);
                softAssert.assertTrue(countUpdated, "Cart Item Count not updated to proper value::" + cartItemCount);

                cartPage.validateBundleProductsDataInCartPage(scenarioContext);
                ExtentCucumberAdapter.addTestStepLog("Validated Bundle product information in cart page");
                Log.info("----------------Validated Bundle product information in cart page-----------");

                cartPage.validateBundleProductTotalCostValues(scenarioContext);
                ExtentCucumberAdapter.addTestStepLog("Validated Bundle Product Total Cost Values in cart page");
                Log.info("----------------Validated Bundle Product Total Cost Values in cart page-----------");

            }
            //check product type rac or ea to validate PDP and Cart page data is same
            if (productType.trim().equalsIgnoreCase("rac") | productType.trim().equalsIgnoreCase("ea")) {

                scenarioContext.setContext("ProductModel1", pdpPage.getProductModel().getText().trim());
                scenarioContext.setContext("ProductName1", pdpPage.getProductName().getText().trim());
                //System.out.println("pdpPage.getRentalPaymentPrice().trim()::"+pdpPage.getRentalPaymentPrice().trim());
                scenarioContext.setContext("ProductModel1RentalPaymentPrice", pdpPage.getRentalPaymentPrice().trim());
                scenarioContext.setContext("ProductModel1NoOfPayments", pdpPage.getNumberOfPaymentsValue().getText().trim());
                scenarioContext.setContext("ProductModel1TotalCostToOwn", pdpPage.getTotalCostToOwnValue().getText().trim());
                scenarioContext.setContext("ProductModel1SameAsCashPrice", pdpPage.getSameAsCashPriceValue().getText().trim());
                scenarioContext.setContext("ProductModel1EstimatedDelivery", pdpPage.getEstimatedDeliveryValue().getText().trim());

                //to check product details in cart page using static method
                ScenarioContext.setStaticContext("ProductName1", pdpPage.getProductName().getText().trim());


                ExtentCucumberAdapter.addTestStepLog("Added product data to scenario context object");
                Log.info("----------------Added product data to scenario context object-----------");

                cartPage.clickOnAddToCart();
                pdpPage.getCartItemCountText().isDisplayed();

                ExtentCucumberAdapter.addTestStepLog("Click on View cart and order");
                Log.info("----------------Click on View cart and order-----------");
                cartPage.clickOnViewCartAndOrder();

                cartPage.validateCartPageData(scenarioContext);
                ExtentCucumberAdapter.addTestStepLog("Validated Product Total Cost Values in cart page");
                Log.info("----------------Validated Product Total Cost Values in cart page-----------");

                int noOfItems = 1;
                cartPage.validateCartTabCount(noOfItems + "");

                if (availability.trim().equalsIgnoreCase("in stock") | productType.trim().equalsIgnoreCase("ea")) {
                    softAssert.assertTrue(cartPage.getInStockHeaderElement().getText().contains("1"), "In Stock header not contains item count as " + noOfItems);

                }

                if (availability.trim().equalsIgnoreCase("special order")) {
                    softAssert.assertTrue(cartPage.getSpecialOrderHeaderElement().getText().contains("1"), "In Stock header not contains item count as " + noOfItems);
                }

                //trying to add same product again to check SFCC-3419, SFCC-3420, SFCC-4419
                //if producttype is not rac special check the item count in cart if same product added multiple times
                cartPage.navigateToProductPage(productId);
                cartPage.clickOnAddToCart();
                cartPage.validateItemAlreadyAddedAndCartCount(availability.toLowerCase().trim());

            }

            if (startOrder.trim().equalsIgnoreCase("yes")) {
                pdpPage.validateStartOrderFunctionality();
            }

            //Added this condition to check remove item functionality
            if (addToCart.trim().equalsIgnoreCase("yes")) {
                //validating remove item from cart
                cartPage.navigateToProductPage(productId);

                cartPage.clickOnAddToCart();
                pdpPage.getCartItemCountText().isDisplayed();
                ExtentCucumberAdapter.addTestStepLog("Click on View cart and order");
                Log.info("----------------Click on View cart and order-----------");
                cartPage.clickOnViewCartAndOrder();

                if(productCategory.equalsIgnoreCase("Benefit Plus")) {
                    softAssert.assertTrue(cartPage.getSpecialOrderHeaderElement().getText().contains("1"), "In Stock header not contains item count as " + 1);
                }

                cartPage.removeItemFromCart();
                ExtentCucumberAdapter.addTestStepLog("Validated remove item from cart page");
                Log.info("----------------Validated remove item from cart page-----------");

                cartPage.validateEmptyCartData();
                ExtentCucumberAdapter.addTestStepLog("Validated Empty Cart in cart page");
                Log.info("----------------Validated Empty Cart in cart page-----------");

                if(!productId2.isEmpty()) {
                    //validate  start order banner if more than 2 items in save for later page SFCC-4398
                    cartPage.navigateToProductPage(productId);
                    cartPage.clickOnAddToCart();

                    cartPage.navigateToProductPage(productId2.trim());
                    cartPage.clickOnAddToCart();
                    cartPage.clickOnViewCartAndOrder();
                    cartPage.validateStartOrderBanner();
                    ExtentCucumberAdapter.addTestStepLog("Validated StartOrder Banner in cart page");
                    Log.info("----------------Validated StartOrder Banner in cart page-----------");
                }
            }

            //Added this condition to check remove wishlist item functionality
            if (wishlistAccess.trim().equalsIgnoreCase("yes")) {
                //validating remove item from wishlist
                cartPage.navigateToProductPage(productId);
                //to check non benefit plus product cart data
                if(productCategory.equalsIgnoreCase("Benefit Plus")) {
                    scenarioContext.setContext("ProductName1", pdpPage.getProductName().getText().trim());
                    //System.out.println("pdpPage.getRentalPaymentPrice().trim()::"+pdpPage.getRentalPaymentPrice().trim());
                    scenarioContext.setContext("ProductModel1RentalPaymentPrice", pdpPage.getRentalPaymentPrice().trim());
                    //to check product details in cart page using static method
                    ScenarioContext.setStaticContext("ProductName1", pdpPage.getProductName().getText().trim());

                    ExtentCucumberAdapter.addTestStepLog("Click on Wishlist button to PDP page");
                    Log.info("----------------Click on Wishlist button to PDP page-----------");
                    pdpPage.clickOnWishlistButton();

                    ExtentCucumberAdapter.addTestStepLog("Click on View Saved Items button to navigate to saved page");
                    Log.info("----------------Click on View Saved Items button to navigate to saved page-----------");
                    cartPage.clickOnViewSavedItem();

                    cartPage.validateSavedItemPageData();
                    ExtentCucumberAdapter.addTestStepLog("Validated Save item for later page");
                    Log.info("----------------Validated Save item for  cart page-----------");
                    Assert.assertTrue(cartPage.validateProductDetailsInSavedItemPage(), "product name is not displayed in saved cart page");

                    softAssert.assertTrue(cartPage.getCartTabCount().getText().contains("0"), "Cart Tab header not contains item count as " + 0);
                    softAssert.assertTrue(cartPage.getSavedTabCount().getText().contains("1"), "Saved Tab header not contains item count as " + 1);
                    ExtentCucumberAdapter.addTestStepLog("Validated Save item Header count page");
                    Log.info("----------------Validated Save item Header count page-----------");

                    //validating view pricing and availability button functionality SFCC-3373 SFCC-3374 SFCC-4397
                    cartPage.clickOnViewPricingAndAvailability();
                    cartPage.clickOnHeaderCart();
                    cartPage.clickOnSavedItemTab();
                    cartPage.removeItemFromWishlist();
                    ExtentCucumberAdapter.addTestStepLog("Validated Remove item from saved item page");
                    Log.info("----------------Validated remove item from saved item page-----------");

                }else{
                    scenarioContext.setContext("ProductModel1", pdpPage.getProductModel().getText().trim());
                    scenarioContext.setContext("ProductName1", pdpPage.getProductName().getText().trim());
                    //System.out.println("pdpPage.getRentalPaymentPrice().trim()::"+pdpPage.getRentalPaymentPrice().trim());
                    scenarioContext.setContext("ProductModel1RentalPaymentPrice", pdpPage.getRentalPaymentPrice().trim());
                    scenarioContext.setContext("ProductModel1NoOfPayments", pdpPage.getNumberOfPaymentsValue().getText().trim());
                    scenarioContext.setContext("ProductModel1TotalCostToOwn", pdpPage.getTotalCostToOwnValue().getText().trim());
                    scenarioContext.setContext("ProductModel1SameAsCashPrice", pdpPage.getSameAsCashPriceValue().getText().trim());
                    scenarioContext.setContext("ProductModel1EstimatedDelivery", pdpPage.getEstimatedDeliveryValue().getText().trim());
                    //to check product details in cart page using static method
                    ScenarioContext.setStaticContext("ProductName1", pdpPage.getProductName().getText().trim());

                    ExtentCucumberAdapter.addTestStepLog("Added product data to scenario context object");
                    Log.info("----------------Added product data to scenario context object-----------");

                    cartPage.clickOnAddToCart();
                    cartPage.clickOnViewCartAndOrder();

                    //do save for later
                    softAssert.assertTrue(cartPage.getCartTabCount().getText().contains("1"), "Cart Tab header not contains item count as " + 1);

                    basePage.scrollToElement(cartPage.getSaveForLaterButton());
                    cartPage.getSaveForLaterButton().click();
                    cartPage.validateSaveForLaterPopUp();
                    ExtentCucumberAdapter.addTestStepLog("Validated Save item popup window");
                    Log.info("----------------Validated Save item popup window-----------");

                    ExtentCucumberAdapter.addTestStepLog("Click on View Saved Items button to navigate to saved page");
                    Log.info("----------------Click on View Saved Items button to navigate to saved page-----------");
                    cartPage.clickOnViewSavedItem();

                    cartPage.validateSavedItemPageData();
                    ExtentCucumberAdapter.addTestStepLog("Validated Save item for later page");
                    Log.info("----------------Validated Save item for  cart page-----------");
                    Assert.assertTrue(cartPage.validateProductDetailsInSavedItemPage(), "product name is not displayed in saved cart page");

                    softAssert.assertTrue(cartPage.getCartTabCount().getText().contains("0"), "Cart Tab header not contains item count as " + 0);
                    softAssert.assertTrue(cartPage.getSavedTabCount().getText().contains("1"), "Saved Tab header not contains item count as " + 1);
                    ExtentCucumberAdapter.addTestStepLog("Validated Save item Header count page");
                    Log.info("----------------Validated Save item Header count page-----------");


                    //validating view pricing and availability button functionality SFCC-3373 SFCC-3374 SFCC-4397
                    cartPage.clickOnViewPricingAndAvailability();
                    cartPage.clickOnHeaderCart();
                    cartPage.clickOnSavedItemTab();
                    cartPage.removeItemFromWishlist();
                    ExtentCucumberAdapter.addTestStepLog("Validated Remove item from saved item page");
                    Log.info("----------------Validated remove item from saved item page-----------");

                    //confirm cart count is 0 after removing product
                    softAssert.assertTrue(cartPage.getSavedTabCount().getText().contains("0"), "Saved tab count is not 0 after removing item from wishlist");

                    //validate wishlist banner if more than 2 items in save for later page
                    if(!productId2.isEmpty()) {
                        cartPage.navigateToProductPage(productId);
                        cartPage.clickOnWishlistPDPPage();

                        cartPage.navigateToProductPage(productId2.trim());
                        cartPage.clickOnWishlistPDPPage();
                        cartPage.clickOnViewSavedItem();
                        cartPage.validateWishlistBanner();
                    }
                }


            }

            if (validateScenario.trim().toLowerCase().contains("in stock and special")) {
//                cartPage.navigateToProductPage(productId);
                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Click on Add to cart for In Stock product");
                Log.info("----------------Click on Add to cart for In Stock product-----------");
                cartPage.clickOnViewCartAndOrder();
                cartPage.navigateToProductPage(productId2);
                ExtentCucumberAdapter.addTestStepLog("Click on Add to cart for Special Order product");
                Log.info("----------------Click on Add to cart for Special Order product-----------");
                cartPage.clickOnAddToCart();

                ExtentCucumberAdapter.addTestStepLog("Click on View cart and order");
                Log.info("----------------Click on View cart and order-----------");
                cartPage.clickOnViewCartAndOrder();

                ExtentCucumberAdapter.addTestStepLog("Validating In-stock Header value in Cart Page");
                Log.info("----------------Validating In-stock Header value in Cart Page-----------");
                softAssert.assertTrue(cartPage.getInStockHeaderElement().getText().contains("1"), "In Stock header not contains item count as 1");


                ExtentCucumberAdapter.addTestStepLog("Validating Special order Header value in Cart Page");
                Log.info("----------------Validating Special order Header value in Cart Page-----------");
                softAssert.assertTrue(cartPage.getSpecialOrderHeaderElement().getText().contains("1"), "In Stock header not contains item count as 1");

            }

            if (validateScenario.trim().toLowerCase().contains("in stock and bundle")) {
//                cartPage.navigateToProductPage(productId);
                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Click on Add to cart for In Stock product");
                Log.info("----------------Click on Add to cart for In Stock product-----------");
                cartPage.clickOnViewCartAndOrder();
                cartPage.navigateToProductPage(productId2);
                ExtentCucumberAdapter.addTestStepLog("Click on Add to cart for Special Order product");
                Log.info("----------------Click on Add to cart for Special Order product-----------");
                cartPage.clickOnAddToCart();

                ExtentCucumberAdapter.addTestStepLog("Click on View cart and order");
                Log.info("----------------Click on View cart and order-----------");
                cartPage.clickOnViewCartAndOrder();

                softAssert.assertTrue(cartPage.getInStockHeaderElement().getText().contains("2"), "In Stock header not contains item count as 2");
            }

            if (validateScenario.trim().toLowerCase().contains("update store for guest user")) {
//				cartPage.navigateToProductPage(productId);
                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Clicked on Add to cart for In Stock product");
                Log.info("----------------Clicked on Add to cart for In Stock product-----------");


                cartPage.clickOnViewCartAndOrder();
                ExtentCucumberAdapter.addTestStepLog("Clicked on View cart and order");
                Log.info("----------------Clicked on View cart and order-----------");

                ExtentCucumberAdapter.addTestStepLog("update zipcode for given product");
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "For given: " + updateStoreNo);
                Log.info("-----------calling zipcode functionality---------");
                homePage.clickOnFindYourStoreButton();
                homePage.enterZipCode(updateStoreNo);
                homePage.clickOnGOButton();
                pdpPage.enterZipCodeInStoreLocatorPopup(updateStoreNo);
                homePage.clickOnMakeThisMyStoreLink();
                homePage.isPricingForTextDisplayed("Pricing for");

                //cartPage.validateStoreChangeWarningBanner();
                Assert.assertTrue(cartPage.validateStoreChangeWarningBanner(), "Store Change Warning Banner is not displayed");
                ExtentCucumberAdapter.addTestStepLog("Validated Store Change Warning Banner in Cart page");
                Log.info("----------------Store Change Warning Banner in Cart page-----------");

            }

            //SFCC-2786, SFCC-2458, SFCC-2791
            if (validateScenario.trim().toLowerCase().equalsIgnoreCase("guest and signed in user")) {
                ExtentCucumberAdapter.addTestStepLog("Login as guest user and add item to cart");
                Log.info("----------------Login as guest user and add item to cart-----------");

                String ProductNameAsGuestUser = pdpPage.getProductName().getText().trim();

                String zipcodeAsGuestUser = pdpPage.getStorePostalCodeFromPDP();
                ExtentCucumberAdapter.addTestStepLog("Product Zipcode from PDP as Guest User"+zipcodeAsGuestUser);
                Log.info("----------------Product Zipcode from PDP as Guest User"+zipcodeAsGuestUser+"-----------");
                softAssert.assertTrue(zipcodeAsGuestUser.contains(zipcode), "Guest user zipcode not matches with given zipcode::" + zipcode);

                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Clicked on Add to cart for given product");
                Log.info("----------------Clicked on Add to cart for given product-----------");

//                pdpPage.closeModalOnPDP();
//                ExtentCucumberAdapter.addTestStepLog("Clicked on view cart and item model Close button");
//                Log.info("----------------Clicked view cart and item Close button-----------");
                cartPage.clickOnViewCartAndOrder();
                ExtentCucumberAdapter.addTestStepLog("Clicked on View cart and order");
                Log.info("----------------Clicked on View cart and order-----------");

                ExtentCucumberAdapter.addTestStepLog("Now login to given user");
                Log.info("----------------Now login to given user-----------");
                //This user has already one product in the cart
                String emailAddress = rowData.get("EmailAddress");
                String password = rowData.get("Password");
                String signInUserZipCode = rowData.get("SigninUserZipCode");

                ExtentCucumberAdapter.addTestStepLog("The user clicks on Sign In button");
                Log.info("----------------The user clicks on Sign In button----------");
                signInPage.clickOnSignButtonHomePage();

                ExtentCucumberAdapter.addTestStepLog("The user fills the sign in form using data from");
                Log.info("----------------The user fills the sign in form using data from----------");
                signInPage.enterEmailAndPassword(emailAddress, password);
                //to enter OTP details
                ExtentCucumberAdapter.addTestStepLog("The user fills OTP details if appears");
                Log.info("----------------The user fills OTP details if appears---------");
                signInPage.clickOnSignInBtnMyAccountDlg();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Email Address: " + emailAddress);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Password: " + password);

                ExtentCucumberAdapter.addTestStepLog("Clicked on Header Cart after Sign-in");
                Log.info("----------------Clicked on Header Cart after Sign-in-----------");
                cartPage.clickOnHeaderCart();

                String headerCountValue = cartPage.getInStockHeaderElement().getText();
                String number = headerCountValue.replaceAll("\\D+", "");
                int cartCount = Integer.parseInt(number);
                softAssert.assertTrue(cartCount >= 2, "In Stock header not contains item count as 2");


                //validate productName in cart page when multiple products exist in cart SFCC-2786
                ExtentCucumberAdapter.addTestStepLog("Validate ProductName in Cart after Sign-in");
                Log.info("----------------Validate ProductName in Cart after Sign-in-----------");
                cartPage.validateProductNameInCartPage(ProductNameAsGuestUser);

                //validated signed user zipcode on PDP page SFCC-2791
                ExtentCucumberAdapter.addTestStepLog("Click on ProductName in Cart after Sign-in");
                Log.info("----------------Click on ProductName in Cart after Sign-in-----------");
                cartPage.clickOnProductNameInCartPage(ProductNameAsGuestUser);

                //75116
                String zipcodeAfterSignIn = pdpPage.getStorePostalCodeFromPDP();
                ExtentCucumberAdapter.addTestStepLog("Product Zipcode in Cart after Sign-in"+zipcodeAfterSignIn);
                Log.info("----------------Product Zipcode in Cart after Sign-in"+zipcodeAfterSignIn+"-----------");
                softAssert.assertTrue(zipcodeAfterSignIn.equalsIgnoreCase(signInUserZipCode), "Zip code on PDP page of Sign in user not matches with expected value::" + signInUserZipCode+" actual value::"+zipcodeAfterSignIn);

                //Remove product from cart page after validation completed
                ExtentCucumberAdapter.addTestStepLog("Clicked on Header Cart after Sign-in");
                Log.info("----------------Clicked on Header Cart after Sign-in-----------");
                cartPage.clickOnHeaderCart();

                ExtentCucumberAdapter.addTestStepLog("Remove Added Product From Cart Page after Sign-in");
                Log.info("----------------Remove Added Product From Cart Page after Sign-in-----------");
                cartPage.removeAddedProductFromCartPage(ProductNameAsGuestUser);
            }

            //SFCC-2720, SFCC-2743, SFCC-2784
            if (validateScenario.trim().toLowerCase().contains("guest saved item and signed in user")) {
                ExtentCucumberAdapter.addTestStepLog("guest saved item merged to signed in user cart page saved tab is added");
                Log.info("----------------guest saved item merged to signed in user cart page saved tab is added-----------");

                String ProductNameAsGuestUser = pdpPage.getProductName().getText().trim();

                String zipcodeAsGuestUser = pdpPage.getStorePostalCodeFromPDP();
                softAssert.assertTrue(zipcodeAsGuestUser.contains(zipcode), "Guest user zipcode not matches with given zipcode::" + zipcode);

                cartPage.clickOnWishlistPDPPage();
                ExtentCucumberAdapter.addTestStepLog("Clicked on Add to cart for given product");
                Log.info("----------------Clicked on Add to cart for given product-----------");

                cartPage.clickOnViewSavedItem();
                ExtentCucumberAdapter.addTestStepLog("Clicked on View saved item button");
                Log.info("----------------Clicked on View saved item button-----------");

                ExtentCucumberAdapter.addTestStepLog("Now login to given user");
                Log.info("----------------Now login to given user-----------");
                //This user has already one product in the cart
                String emailAddress = rowData.get("EmailAddress");
                String password = rowData.get("Password");
                String signInUserZipCode = rowData.get("SigninUserZipCode");

                ExtentCucumberAdapter.addTestStepLog("The user clicks on Sign In button");
                Log.info("----------------The user clicks on Sign In button----------");
                signInPage.clickOnSignButtonHomePage();


                ExtentCucumberAdapter.addTestStepLog("The user fills the sign in form using data from");
                Log.info("----------------The user fills the sign in form using data from----------");
                signInPage.enterEmailAndPassword(emailAddress, password);
                //to enter OTP details
                ExtentCucumberAdapter.addTestStepLog("The user fills OTP details if appears");
                Log.info("----------------The user fills OTP details if appears---------");
                signInPage.clickOnSignInBtnMyAccountDlg();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Email Address: " + emailAddress);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Password: " + password);

                ExtentCucumberAdapter.addTestStepLog("Clicked on Header Cart after Sign-in");
                Log.info("----------------Clicked on Header Cart after Sign-in-----------");
                cartPage.clickOnHeaderCart();

                ExtentCucumberAdapter.addTestStepLog("Click to Saved Tab in Cart Page after Sign-in");
                Log.info("----------------Click on Saved Tab in Cart Page after Sign-in-----------");
                cartPage.clickOnSavedItemTab();

                //validate productName in cart Save tab page has guest user saved product is displayed
                ExtentCucumberAdapter.addTestStepLog("Validate ProductName in Cart Saved tab after Sign-in");
                Log.info("----------------Validate ProductName in Cart Saved tab after Sign-in-----------");
               softAssert.assertTrue(basePage.isTextDisplayed(ProductNameAsGuestUser),"Product is not found in saved tab after sign in");
            }

            //SFCC-2787,SFCC-4258, SFCC-4259, SFCC-4260, SFCC-4261
            if (validateScenario.trim().equalsIgnoreCase("guest and signed in user has same item in cart")) {

                String ProductNameAsGuestUser = pdpPage.getProductName().getText().trim();

                String zipcodeAsGuestUser = pdpPage.getStorePostalCodeFromPDP();
                softAssert.assertTrue(zipcodeAsGuestUser.contains(zipcode), "Guest user zipcode not matches with given zipcode::" + zipcode);

                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Clicked on Add to cart for given product");
                Log.info("----------------Clicked on Add to cart for given product-----------");

                cartPage.clickOnViewCartAndOrder();
                ExtentCucumberAdapter.addTestStepLog("Clicked on View cart and order");
                Log.info("----------------Clicked on View cart and order-----------");

                ExtentCucumberAdapter.addTestStepLog("Now login to given user");
                Log.info("----------------Now login to given user-----------");
                //This user has already one product in the cart
                String emailAddress = rowData.get("EmailAddress");
                String password = rowData.get("Password");
                String signInUserZipCode = rowData.get("SigninUserZipCode");

                ExtentCucumberAdapter.addTestStepLog("The user clicks on Sign In button");
                Log.info("----------------The user clicks on Sign In button----------");
                signInPage.clickOnSignButtonHomePage();


                ExtentCucumberAdapter.addTestStepLog("The user fills the sign in form using data from");
                Log.info("----------------The user fills the sign in form using data from----------");
                signInPage.enterEmailAndPassword(emailAddress, password);
                //to enter OTP details
                ExtentCucumberAdapter.addTestStepLog("The user fills OTP details if appears");
                Log.info("----------------The user fills OTP details if appears---------");
                signInPage.clickOnSignInBtnMyAccountDlg();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Email Address: " + emailAddress);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Password: " + password);

                ExtentCucumberAdapter.addTestStepLog("Clicked on Header Cart after Sign-in");
                Log.info("----------------Clicked on Header Cart after Sign-in-----------");
                cartPage.clickOnHeaderCart();

                String headerCountValue = cartPage.getInStockHeaderElement().getText();
                String number = headerCountValue.replaceAll("\\D+", "");
                int cartCount = Integer.parseInt(number);
                System.out.println("cart in-stock count::"+cartCount);
                softAssert.assertTrue(cartCount == 1 , "In Stock header not contains item count as 2");

                //validate productName in cart Save tab page has guest user saved product is displayed
                ExtentCucumberAdapter.addTestStepLog("Validate ProductName in Cart tab after Sign-in");
                Log.info("----------------Validate ProductName in Cart tab after Sign-in-----------");
                softAssert.assertTrue(basePage.isTextDisplayed(ProductNameAsGuestUser),"Product is not found in cart page after sign in");

            }

            if (validateScenario.trim().equalsIgnoreCase("Guest and Signin user store no matches")) {
                ExtentCucumberAdapter.addTestStepLog("Login as guest user and add item to cart");
                Log.info("----------------Login as guest user and add item to cart-----------");

                String zipcodeAsGuestUser = pdpPage.getStorePostalCodeFromPDP();
                softAssert.assertTrue(zipcodeAsGuestUser.contains(zipcode), "Guest user zipcode not matches with given zipcode::" + zipcode);

                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Clicked on Add to cart for given product");
                Log.info("----------------Clicked on Add to cart for given product-----------");

                cartPage.clickOnViewCartAndOrder();
                ExtentCucumberAdapter.addTestStepLog("Clicked on View cart and order");
                Log.info("----------------Clicked on View cart and order-----------");

                ExtentCucumberAdapter.addTestStepLog("Now login to given user");
                Log.info("----------------Now login to given user-----------");
                //This user has already one product in the cart
                String emailAddress = rowData.get("EmailAddress");
                String password = rowData.get("Password");
                String signInUserZipCode = rowData.get("SigninUserZipCode");

                ExtentCucumberAdapter.addTestStepLog("The user clicks on Sign In button");
                Log.info("----------------The user clicks on Sign In button----------");
                signInPage.clickOnSignButtonHomePage();

                ExtentCucumberAdapter.addTestStepLog("The user fills the sign in form using data from");
                Log.info("----------------The user fills the sign in form using data from----------");
                signInPage.enterEmailAndPassword(emailAddress, password);
                //to enter OTP details
                ExtentCucumberAdapter.addTestStepLog("The user fills OTP details if appears");
                Log.info("----------------The user fills OTP details if appears---------");
                signInPage.clickOnSignInBtnMyAccountDlg();

                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Email Address: " + emailAddress);
                ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace() + "Password: " + password);

                cartPage.navigateToProductPage(productId);
                String zipcodeAfterSignIn = pdpPage.getStorePostalCodeFromPDP();
                softAssert.assertTrue(zipcodeAsGuestUser.contains(zipcodeAfterSignIn), "Signin User zipcode not matches with Guest user zipcode matches::" + signInUserZipCode);

                //Added to validate below Test cases
                cartPage.clickOnHeaderCart();

                //SFCC-4258
                cartPage.validateEstimatedCartData();
                //SFCC-4259
                cartPage.validateRemainingCartApproval();
                //SFCC-4260
                cartPage.validateRemainingCartApprovalDetailsLinkData();
            }

            if (validateScenario.trim().toLowerCase().contains("multiple special")) {
//                cartPage.navigateToProductPage(productId);
                cartPage.clickOnAddToCart();
                ExtentCucumberAdapter.addTestStepLog("Click on Add to cart for In Stock product");
                Log.info("----------------Click on Add to cart for In Stock product-----------");
                cartPage.clickOnViewCartAndOrder();
                cartPage.navigateToProductPage(productId2);
                ExtentCucumberAdapter.addTestStepLog("Click on Add to cart for Special Order product");
                Log.info("----------------Click on Add to cart for Special Order product-----------");
                cartPage.clickOnAddToCart();

                ExtentCucumberAdapter.addTestStepLog("Click on View cart and order");
                Log.info("----------------Click on View cart and order-----------");
                cartPage.clickOnViewCartAndOrder();

                ExtentCucumberAdapter.addTestStepLog("Validating Special order Header value in Cart Page");
                Log.info("----------------Validating Special order Header value in Cart Page-----------");
                softAssert.assertTrue(cartPage.getSpecialOrderHeaderElement().getText().contains("2"), "In Stock header not contains item count as 2");

            }

            softAssert.assertAll();

        }

    }


}