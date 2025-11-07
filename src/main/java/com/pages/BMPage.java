package com.pages;

import com.qa.util.LoggerHelper;
import com.qa.util.ScenarioContext;
import io.cucumber.java.en.Then;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Properties;

import static com.qa.factory.DriverFactory.getDriver;

import java.util.*;

public class BMPage {


    private WebDriver driver;
    ScenarioContext scenarioContext = new ScenarioContext(); // Shared context

    BasePage basePage = new BasePage(getDriver());
    private static final Logger Log = LoggerHelper.getLogger();

    Properties prop = null;

    // Constructor to initialize elements using Page Factory
    public BMPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Username input field
    @FindBy(xpath = "//input[@placeholder='User Name']")
    public WebElement usernameInput;

    // Login button
    @FindBy(xpath = "//input[@id='loginButton_0' and @value='Log in']")
    public WebElement loginButton;

    // Password field
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordField;

    // Login button
    @FindBy(xpath = "//input[@value='Log in' or @type='submit']")
    private WebElement loginButtonPassword;

    //BM Homepage

    @FindBy(xpath = "//*[@data-automation='[BMGlobalNavigation] merchantToolsTabLink']")
    private WebElement merchantToolsLink;

    @FindBy(xpath = "//*//a[contains(text(), 'RentaCenter')]")
    private WebElement rentACenterLink;

    @FindBy(xpath = "//*[@title=\"Products and Catalogs\"]/img")
    private WebElement productsAndCatalogsImage;


    @FindBy(xpath = "//*[@title=\"Products\"]/img")
    private WebElement productsImage;

    @FindBy(xpath = "//*[@title=\"Inventory\"]/img")
    private WebElement inventoryImage;

    @FindBy(xpath = "//td[@class=\"infobox_item_search\"]//a[@class=\"switch_link\" and text()=\"Advanced\"]")
    private WebElement advancedSearch;

    // Locate the select dropdown
    @FindBy(id = "Field1")
    private WebElement extendedAttributeDropdown;

    @FindBy(id = "Field2")
    private WebElement extendedAttributeDropdown2;

    @FindBy(id = "Value1")
    private WebElement extendedAttributeInput;

    @FindBy(id = "Value2")
    private WebElement extendedAttributeInput2;

    @FindBy(id = "SortingAttribute")
    private WebElement sortingAttributeDropDown;

    @FindBy(xpath = "//select[@name='SortingDirection']")
    private WebElement selectSort;

    @FindBy(xpath = "//select[@name='InitialPageSize']")
    private WebElement selectNoOfRowsPerPage; // 10,50,100,1000

    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//button[@name='findParametric']")
    private WebElement findButton;

    // EA products search
    @FindBy(xpath = "//a[normalize-space()='Administration']")
    private WebElement administrationLink;
    @FindBy(xpath = "//h2/span[@title='Operations']")
    private WebElement operationsLink;
    @FindBy(xpath = "//h2/span[@title='OCI Integration']")
    private WebElement ociIntegrationLink;

    @FindBy(xpath = "//button[@value='All']")
    private WebElement allButton;

    @FindBy(xpath = "//button[@value='1000']")
    private WebElement thousandRecordsButton;

    @FindBy(xpath = "//button[@value='100']")
    private WebElement hundredRecordsButton;

    @FindBy(xpath = "//a[normalize-space()='15271']")
    private WebElement eaInventoryIdLink;

    @FindBy(xpath = "//*[normalize-space()='Records']")
    private WebElement recordsTab;

    @FindBy(xpath = "//button[@name='find'] | //button[@name='Find']")
    private WebElement administrationFindButton;

    //Special order
    @FindBy(id = "Operator1")
    private WebElement selectOperator1; //10, 11-does not equal, 16-starts with, 17-contains, 19-does not exist, 20-exist

    @FindBy(id = "Operator2")
    private WebElement selectOperator2;

    @FindBy(xpath = "//a[normalize-space()='Lock']")
    private WebElement lockLink;

    @FindBy(xpath = "//a[normalize-space()='UnLock'] | //a[normalize-space()='Unlock']")
    private WebElement unlockLink;

    @FindBy(xpath = "//span[contains(text(), 'Special Order Checkout')]/ancestor::td/following-sibling::td//select")
    private WebElement specialOrderSelect;

    @FindBy(xpath = "//button[contains(text(), 'Apply')]")
    private WebElement applyButton;

    public void enterUsername(String username) {
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public void enterPassword(String password) {
        basePage.waitForElementToBeClickable(loginButtonPassword);
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButtonPwd() {
        loginButtonPassword.click();
        basePage.waitForPageToLoad();
    }

    public void navigateToProduct() {

        basePage.waitForElementVisible(merchantToolsLink);
        merchantToolsLink.click();
        basePage.waitForPageToLoad();

        rentACenterLink.click();
        basePage.waitForPageToLoad();

        productsAndCatalogsImage.click();
        basePage.waitForPageToLoad();

        productsImage.click();
        basePage.waitForElementVisible(advancedSearch);

        advancedSearch.click();


    }

    // Method to select "Rac Store Number"
    public void selectRacStoreNumber() {
        extendedAttributeDropdown.click();
        basePage.waitForPageToLoad();
        Select select = new Select(extendedAttributeDropdown);
        select.selectByValue("racStoreNumber");
    }

    public void selectRacRMSNumber() {
        extendedAttributeDropdown2.click();
        basePage.waitForPageToLoad();
        Select select = new Select(extendedAttributeDropdown2);
        select.selectByValue("racRmsNumber");
    }

    public void enterDataInputFiled1(String data) {
        extendedAttributeInput.clear();
        extendedAttributeInput.sendKeys(data);

    }

    public void enterDataInputFiled2(String data) {
        extendedAttributeInput2.clear();
        extendedAttributeInput2.sendKeys(data);
    }

    public void selectSortingDataValue(String sortDataValue) {
        sortingAttributeDropDown.click();
        basePage.waitForPageToLoad();
        Select select = new Select(sortingAttributeDropDown);
        select.selectByValue(sortDataValue.trim());
    }

    public void selectSortAscendingOrDescending(String value) {
        //value=ascending, descending
        Select select = new Select(selectSort);
        select.selectByValue(value.trim());
    }

    public void selectNoOfRowsToDisplay(String value) {
        //value = 10,50,100,1000
        Select select = new Select(selectNoOfRowsPerPage);
        select.selectByValue(value.trim());
    }

    public void clickOnFindProducts() {
        findButton.click();
    }

    public Set<String> getInventoryIdForProduct() {

        // Required statuses to check
        List<String> requiredStatuses = Arrays.asList("Online", "In-Stock", "Searchable", "Categorized");

        // List to store matching IDs
        Set<String> matchingIds = new HashSet<>();

        // Get all table rows excluding the header
        List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));

        //String inventoryId = null;

        for (WebElement row : rows) {
            try {
                // Get all td cells in the current row
                List<WebElement> cells = row.findElements(By.tagName("td"));

                // Check if row has at least enough columns
                if (cells.size() < 11) continue;

                // 2nd column is ID (index 1)
                String inventoryId = cells.get(1).getText().trim();

                // 11th column is Status (index 10)
                WebElement statusCell = cells.get(10);
                List<WebElement> statusImages = statusCell.findElements(By.tagName("img"));

                // Collect status titles
                Set<String> foundStatuses = new HashSet<>();
                for (WebElement img : statusImages) {
                    String title = img.getAttribute("title").trim();
                    if (!title.isEmpty()) {
                        foundStatuses.add(title);
                    }
                }

                // Check if all required statuses are present
                if (foundStatuses.containsAll(requiredStatuses)) {
                    matchingIds.add(inventoryId);
                }
            } catch (Exception e) {
                System.out.println("Skipping a row due to error: " + e.getMessage());
            }
        }
        // Print the matched IDs
        System.out.println("Matching Product IDs with all statuses: " + matchingIds);

        return matchingIds;
    }

    public Set<String> getProductIdFromSearchResults(int columnNo) {

        // Required statuses to check
        List<String> requiredStatuses = Arrays.asList("Online", "In-Stock", "Searchable", "Categorized");

        // List to store matching IDs
        Set<String> matchingIds = new HashSet<>();
        boolean hasNextPage = true;
        ;
        int count = 0;

        while (count <= 4) {
            //while (hasNextPage) {
            // Get all table rows excluding the header
            List<WebElement> rows = driver.findElements(By.xpath("//form[@name=\"ProductListForm\"]//table//tr"));

            //String inventoryId = null;

            for (WebElement row : rows) {
                try {
                    // Get all td cells in the current row
                    List<WebElement> cells = row.findElements(By.tagName("td"));

                    // Check if row has at least enough columns
                    if (cells.size() < 11) continue;

                    // 2nd column is ID (index 1)
                    String inventoryId = cells.get(columnNo).getText().trim();

                    // 3rd column is Name (index 1)
                    String Name = cells.get(columnNo).getText().trim();

                    // 5th column is RACInventoryType (index 1) to check New or Used product
                    String RACInventoryType = cells.get(columnNo).getText().trim();

                    // 11th column is Status (index 10)
                    WebElement statusCell = cells.get(10);
                    List<WebElement> statusImages = statusCell.findElements(By.tagName("img"));

                    // Collect status titles
                    Set<String> foundStatuses = new HashSet<>();
                    for (WebElement img : statusImages) {
                        String title = img.getAttribute("title").trim();
                        if (!title.isEmpty()) {
                            foundStatuses.add(title);
                        }
                    }

                    // Check if all required statuses are present
                    if (foundStatuses.containsAll(requiredStatuses)) {
                        matchingIds.add(inventoryId);
                        matchingIds.add(Name);
                        matchingIds.add(RACInventoryType);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping a row due to error: " + e.getMessage());
                }
            }
            count++;

            basePage.scrollToBottomOfPage();
            // Check if "Next" button is present and enabled
            List<WebElement> nextElementList = driver.findElements(By.xpath("//button[text()='Next']"));

            if (!nextElementList.isEmpty()) {
                WebElement nextButton = nextElementList.get(0);
                //basePage.scrollToElement(nextButton);
//                basePage.scrollToBottomOfPage();
//                basePage.jsClick(nextButton);
                nextButton.click();
                basePage.waitForPageToLoad();
//

                // Wait for table to load (You should replace with an explicit wait)
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No more pages found. Stopping.");
                hasNextPage = false;
            }
        }
        //to navigate through all search results
//        clickOnNextButton();
        // Print the matched IDs
        System.out.println("Matching Product IDs with all statuses: " + matchingIds);

        return matchingIds;
    }

    public void clickOnNextButton() {
        boolean status = false;
        List<WebElement> nextElementList = driver.findElements(By.xpath("//button[text()='Next']"));
        if (!nextElementList.isEmpty()) {
            basePage.scrollToElement(nextButton);
            nextButton.click();
            status = true;
        } else {
            System.out.println("there are no more pages for given search");
            Log.info("there are no more pages for given search");
        }

    }

    public void navigateToEAProductsPage() {
        basePage.waitForElementVisible(administrationLink);
        administrationLink.click();
        basePage.waitForPageToLoad();

        operationsLink.click();
        basePage.waitForPageToLoad();

        //basePage.jsClick(ociIntegrationLink);
        //basePage.scrollToElement(ociIntegrationLink);
        basePage.scrollToBottomOfPage();
        ociIntegrationLink.click();
        basePage.waitForPageToLoad();

    }

    public void clickOnEAProductLink() {

        basePage.scrollToBottomOfPage();
        allButton.click();
        basePage.waitForPageToLoad();
//        basePage.refreshPage();

        basePage.jsClick(eaInventoryIdLink);
        basePage.waitForPageToLoad();
    }

    public void clickOnRecordsPageFind() {

        basePage.waitForElementVisible(recordsTab);
        recordsTab.click();
        basePage.waitForPageToLoad();

        administrationFindButton.click();
        basePage.waitForPageToLoad();

        basePage.scrollToBottomOfPage();
//        thousandRecordsButton.click();
        hundredRecordsButton.click();
        basePage.waitForPageToLoad();
    }

    public Set<String> getValidProductIDs() {
        Set<String> productIDs = new HashSet<>();

        // Locate all rows inside the table body
        List<WebElement> rows = driver.findElements(By.xpath("//table[@data-automation='inventory-list-table']/tbody/tr"));

        for (WebElement row : rows) {
            try {
                // Get Allocation
                WebElement allocationCell = row.findElement(By.xpath("td[@name='Allocation_value']"));
                String allocationText = allocationCell.getText().trim();
                double allocation = Double.parseDouble(allocationText);

                // Get Name
                WebElement nameCell = row.findElement(By.xpath("td[2]")); // Name is the second column
                String name = nameCell.getText().trim();

                // Get Name
//                WebElement nameCell = row.findElement(By.xpath("td[2]")); // Name is the second column
//                String name = nameCell.getText().trim();

                // If allocation > 0 and name is not "N/A (missing)"
                if (allocation > 0.0 && !name.equalsIgnoreCase("N/A (missing)")) {
                    WebElement productIdCell = row.findElement(By.xpath("td[@name='ProductID_value']"));
                    String productId = productIdCell.getText().trim();
                    productIDs.add(productId);
                }
            } catch (Exception e) {
                // Skip any rows that don't match the expected format
                continue;
            }
        }

        return productIDs;
    }

    public void makeProductAsSpecialOrderFromEASearch(String oneProduct) {
//        basePage.isLinkDisplayed();
        WebElement productLink = basePage.getElementByLinkText(oneProduct);
        basePage.scrollToElement(productLink);
        productLink.click();
        basePage.waitForPageToLoad();

        lockLink.click();
        basePage.waitForPageToLoad();

        basePage.scrollToElement(specialOrderSelect);
        //specialOrderSelect.click();
        Select select = new Select(specialOrderSelect);
        select.selectByValue("false");


        basePage.scrollToBottomOfPage();
        basePage.waitForPageToLoad();
        basePage.jsClick(applyButton);
        //applyButton.click();
        basePage.waitForPageToLoad();

//        basePage.scrollToElement(unlockLink);
//        unlockLink.click();
        driver.navigate().back();
        basePage.waitForPageToLoad();
    }

    public void navigateToInventoryPage() {

        basePage.waitForElementVisible(merchantToolsLink);
        merchantToolsLink.click();
        basePage.waitForPageToLoad();

        rentACenterLink.click();
        basePage.waitForPageToLoad();

        productsAndCatalogsImage.click();
        basePage.waitForPageToLoad();

        inventoryImage.click();
        basePage.waitForPageToLoad();
    }

    public Map<String, ArrayList<String>> getProductIdFromSearchResultsAsMap(int columnNo) {

        // Required statuses to check
        List<String> requiredStatuses = Arrays.asList("Online", "In-Stock", "Searchable", "Categorized");

        // List to store matching IDs
        Map<String, ArrayList<String>> matchingIds = new HashMap<String, ArrayList<String>>();
        ArrayList<String> otherColumnsData = new ArrayList<>();
        boolean hasNextPage = true;
        ;
        int count = 0;

        while (count <= 4) {
            //while (hasNextPage) {
            // Get all table rows excluding the header
            List<WebElement> rows = driver.findElements(By.xpath("//form[@name=\"ProductListForm\"]//table//tr"));

            //String inventoryId = null;

            for (WebElement row : rows) {
                try {
                    // Get all td cells in the current row
                    List<WebElement> cells = row.findElements(By.tagName("td"));

                    // Check if row has at least enough columns
                    if (cells.size() < 11) continue;

                    // 2nd column is ID (index 1)
                    String inventoryId = cells.get(columnNo).getText().trim();

                    // 3rd column is Name (index 1)
                    String Name = cells.get(columnNo).getText().trim();

                    // 5th column is RACInventoryType (index 1) to check New or Used product
                    String RACInventoryType = cells.get(columnNo).getText().trim();

                    // 11th column is Status (index 10)
                    WebElement statusCell = cells.get(10);
                    List<WebElement> statusImages = statusCell.findElements(By.tagName("img"));

                    // Collect status titles
                    Set<String> foundStatuses = new HashSet<>();
                    for (WebElement img : statusImages) {
                        String title = img.getAttribute("title").trim();
                        if (!title.isEmpty()) {
                            foundStatuses.add(title);
                        }
                    }

                    // Check if all required statuses are present
                    if (foundStatuses.containsAll(requiredStatuses)) {
                        otherColumnsData.add(Name);
                        otherColumnsData.add(RACInventoryType);
                        matchingIds.put(inventoryId, otherColumnsData);
                    }
                } catch (Exception e) {
                    System.out.println("Skipping a row due to error: " + e.getMessage());
                }
            }
            count++;

            basePage.scrollToBottomOfPage();
            // Check if "Next" button is present and enabled
            List<WebElement> nextElementList = driver.findElements(By.xpath("//button[text()='Next']"));

            if (!nextElementList.isEmpty()) {
                WebElement nextButton = nextElementList.get(0);
                //basePage.scrollToElement(nextButton);
//                basePage.scrollToBottomOfPage();
//                basePage.jsClick(nextButton);
                nextButton.click();
                basePage.waitForPageToLoad();
//

                // Wait for table to load (You should replace with an explicit wait)
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            } else {
                System.out.println("No more pages found. Stopping.");
                hasNextPage = false;
            }
        }
        //to navigate through all search results
//        clickOnNextButton();
        // Print the matched IDs
        System.out.println("Matching Product IDs with all statuses: " + matchingIds);

        return matchingIds;
    }

}


