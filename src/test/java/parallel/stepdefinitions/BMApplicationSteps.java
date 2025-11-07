package parallel.stepdefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.pages.BMPage;
import com.qa.factory.DriverFactory;
import com.qa.util.ExcelWriter;
import com.qa.util.LoggerHelper;
import com.qa.util.ReportHelper;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import utils.ExcelUtils;

import java.io.IOException;
import java.util.*;

public class BMApplicationSteps {

    private static final Logger Log = LoggerHelper.getLogger();
    private BMPage bmPage =new BMPage(DriverFactory.getDriver());
    ExcelUtils excelUtils=new ExcelUtils();


    @When("the user fills the BM Login form using data from {string} at row {int}")
    public void the_user_fills_the_BM_login_in_form_using_data_from_at_row(String sheetName, int rowNumber) throws IOException {
		/*ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/Login.xlsx");
		//InputStream file = getClass().getClassLoader().getResourceAsStream("Login.xlsx");*/

        Map<String, String> userData = excelUtils.fetchExcelRowData(sheetName, rowNumber);
        String userName = userData.getOrDefault("UserName", "");
        String password = userData.getOrDefault("Password", "");
        bmPage.enterUsername(userName);
        bmPage.clickLogin();
        bmPage.enterPassword(password);
        bmPage.clickLoginButtonPwd();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"Email Address: " + userName);
        Log.info("Enter BM login credential and click button");

    }
    @Then("navigate to RAC products page")
    public void navigate_to_RAC_product_page(){
        bmPage.navigateToProduct();
    }

    @Then("select RAC store no {string}")
    public void select_RAC_store_no(String storeNo){
        bmPage.selectRacStoreNumber();
        bmPage.enterDataInputFiled1(storeNo.trim());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"select RAC store no: " + storeNo);
        Log.info("select RAC store no:"+storeNo);

    }

    @Then("select RAC RMS no {string}")
    public void select_RAC_RMS_no(String rmsNo){
        bmPage.selectRacRMSNumber();
        bmPage.enterDataInputFiled2(rmsNo.trim());
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"select RAC RMS no: " + rmsNo);
        Log.info("select RAC RMS no:"+rmsNo);
    }
    @Then("enter sorting data for product with attributes {string}, {string}, {string}")
    public void select_inStock_product_with_attributes(String sortingAttributeValue, String sort, String noOfRows) {
        bmPage.selectSortingDataValue(sortingAttributeValue);
        bmPage.selectSortAscendingOrDescending(sort.toLowerCase());
        bmPage.selectNoOfRowsToDisplay(noOfRows);
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"enter sorting data for product with attributes:sortingAttributeValue " + sortingAttributeValue+" sort: "+sort+" noOfRows: "+noOfRows);
        Log.info("enter sorting data for product with attributes:sortingAttributeValue " + sortingAttributeValue+" sort: "+sort+" noOfRows: "+noOfRows);
    }

    @Then("click on find data for given search values")
    public void click_on_find_data_for_given_search_values() {
        bmPage.clickOnFindProducts();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"click on find data for given search values");
        Log.info("click on find data for given search values");
    }

    @Then("get the inventoryId from search results")
    public void get_the_inventoryId_from_search_results(){
        bmPage.getInventoryIdForProduct();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"get the inventoryId from search results");
        Log.info("get the inventoryId from search results");
    }

    @Then("get the productIDs from search results")
    public void get_the_productIDs_from_search_results(){
        Set<String> productIDs = bmPage.getProductIdFromSearchResults(5);
        ExcelWriter.writeProductListToExcel(productIDs, "./src/test/resources/BMProductList.xlsx", "InStockProducts");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"get the productIDs from search results:: "+productIDs);
        Log.info("get the productIDs from search results:: "+productIDs);
    }

    @Then("navigate to Administration page for EA products")
    public void navigate_to_Administration_page_for_EA_products(){
        bmPage.navigateToEAProductsPage();
        bmPage.clickOnEAProductLink();
    }

    @Then("navigate to Inventory page")
    public void navigate_to_Inventory_page(){
        bmPage.navigateToInventoryPage();
        bmPage.clickOnEAProductLink();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"navigate to Inventory page");
        Log.info("navigate to Inventory page");
    }

    @Then("navigate to EA products Records Page Search")
    public void navigate_to_EA_products_Records_Page_Search(){
        bmPage.clickOnRecordsPageFind();
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"navigate to EA products Records Page Search");
        Log.info("navigate to EA products Records Page Search");
    }

    @Then("get the productIDs from EA Records search results")
    public void get_the_productIDs_from_EA_records_search_results(){
        Set<String> productIDs = bmPage.getValidProductIDs();
        ExcelWriter.writeProductListToExcel(productIDs, "./src/test/resources/BMProductListEAProducts.xlsx", "EAProducts");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"get the productIDs from EA Records search results:: "+productIDs);
        Log.info("get the productIDs from EA Records search results:: "+productIDs);
    }

    @Then("make EA product as Special Order product")
    public void make_EA_product_as_Special_Order_product(){
        Set<String> productIDs = bmPage.getValidProductIDs();
        List<String> myList = new ArrayList<>(productIDs);
        //making 2 products as Special product by selecting 11th and 12th product from EA products data list
        String productID = myList.get(myList.size() - 11);
        System.out.println("productID::"+productID);
        bmPage.makeProductAsSpecialOrderFromEASearch(productID);
        Set<String> specialProducts = new HashSet<>();
        //ExcelWriter.writeProductListToExcel(productID, "./src/test/resources/BMProductList.xlsx", "SpecialProducts");

        String productID2 = myList.get(myList.size() - 12);
        System.out.println("productID2::"+productID2);
        //bmPage.makeProductAsSpecialOrderFromEASearch(productID2);
        specialProducts.add(productID);
        specialProducts.add(productID2);

        ExcelWriter.writeProductListToExcel(specialProducts, "./src/test/resources/BMProductListSpecialProducts.xlsx", "SpecialProducts");
        ExtentCucumberAdapter.addTestStepLog(ReportHelper.addSpace()+"make EA product as Special Order product:: "+specialProducts);
        Log.info("make EA product as Special Order product:: "+specialProducts);
    }


}
