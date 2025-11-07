package com.pages;
import static com.qa.factory.DriverFactory.getDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import com.qa.util.ElementActions;
import com.qa.util.LoggerHelper;
import com.qa.util.ScenarioContext;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlpPage extends ElementActions {
    private WebDriver driver;
    ScenarioContext scenarioContext = new ScenarioContext(); // Shared context
    BasePage basePage = new BasePage(getDriver());
    SignInPage signInPage = new SignInPage(getDriver());
    private static final Logger Log = LoggerHelper.getLogger();
    // private static final Logger Log = (Logger) LoggerHelper.getLogger();

    Properties prop = null;

    // Constructor to initialize elements using Page Factory
    public PlpPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Xpaths:

    @FindBy(xpath = "(//label[text()='Fastest Delivery'])[1]")
    private static WebElement FastestDelivery;

    @FindBy(xpath = "(//span[@aria-hidden='true'])[1]")
    private static WebElement fastestDeliveryToggle;

    @FindBy(xpath = "(//div[@class='css-zoser8']//div[@class='css-k008qs']//*[name()='svg'])[2]")
    private static WebElement ListViewToggle;

    @FindBy(xpath = "(//div[@class='css-zoser8']//div[@class='css-k008qs']//*[name()='svg'])[1]")
    private static WebElement GridViewToggle;

    @FindBy(xpath = "//button[text()='Show More']")
    private static WebElement ShowMoreLink;

    @FindBy(xpath = "//span[contains(@class,'items-count')]")
    private static WebElement IndicatorDetails;

    @FindBy(xpath = "//img[@alt='plp marketing banner']")
    private static WebElement MarketingBanner;

    @FindBy(xpath = "(//p[@class='chakra-text css-13ni5uo'])[1]")
    private static WebElement ProductLabel;

    @FindBy(xpath = "(//p[@class='chakra-text isPriceWrapper css-0'])[1]")
    private static WebElement ProductPricing;

    @FindBy(xpath = "//div[@class='refinements-area']")
    private static WebElement FilterSection;

    @FindBy(xpath = "//a[@href='#']")
    private static WebElement Banner;

    @FindBy(xpath = "//p[contains(text(),'From a comfortable new sofa to a lavish bedroom suite, no matter what your furniture needs, Rent-A-Center will help you find the perfect piece of home furniture for your space. We are a one-stop furniture shop with affordable rent-to-own payment plans because at Rent-A-Center, we want to help you get great furniture that fits your budget.')]")
    private static WebElement CategoryDescription;

    @FindBy(xpath = "//button[@id='accordion-button-:r30:']")
    private static WebElement checkbox;

    @FindBy(xpath = "//*[text()='Read More']")
    private static WebElement ReadMore;

    @FindBy(xpath = "//*[text()='Read Less']")
    private static WebElement ReadLess;

    @FindBy(xpath = "//button[text()='Clear All']")
    private static WebElement ClearAllButton;

    @FindBy(xpath = "(//div[@data-testid='product-tile-image'])[1]")
    private static WebElement productImage;

    @FindBy(xpath = "(//div[@class='css-i9gxme'])[1]")
    private static WebElement ProductName;

    @FindBy(xpath = "(//button[@data-testid='wishlist-button'])[1]")
    private static WebElement WishListButton;

    @FindBy(xpath = "//header[contains(@id, 'chakra-modal--header')]")
    private static WebElement Saved_your_item_popup;

    @FindBy(xpath = "//button[text()='View Saved Items']")
    private static WebElement ViewSavedItemsButton;

    @FindBy(xpath = " (//div[@class='chakra-stack product-line-item css-1htzhqk'])[1]")
    private static WebElement saveditems;

    @FindBy(xpath = "//button[@aria-label='Close']")
    private static WebElement cancelbutton;

    @FindBy(xpath = "//div[@class='plp-marketing-banner']")
    private static WebElement marketingbanner;

    @FindBy(xpath = "(//a[text()='Shop All'])[1]")
    private static WebElement shopallbutton;

    @FindBy(xpath = "(//div[@class='css-0'])[57]")
    private WebElement ShopByBrandSection;

    @FindBy(xpath = "//button[@aria-label='Logo']//*[name()='svg']//*[name()='use' and contains(@role,'presentati')]")
    private WebElement locator;


    //Methods
    public List<Double> getAllProductPrices() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//p[@class='chakra-text isPriceWrapper css-0']"))); // Update XPath if needed

        List<Double> prices = new ArrayList<>();

        for (WebElement el : priceElements) {
            String priceText = el.getText().trim();

            if (priceText.isEmpty()) {
                Log.warn("Empty price found, skipping...");
                continue;
            }

            // Extract numeric values
            String numericPrice = priceText.replaceAll("[^\\d.]", "");
            if (!numericPrice.isEmpty()) {
                try {
                    prices.add(Double.parseDouble(numericPrice));
                } catch (NumberFormatException e) {
                    Log.error("Failed to parse price: " + priceText, e);
                }
            } else {
                Log.warn("Non-numeric price text found: " + priceText);
            }
        }

        Log.info("Collected prices: " + prices);
        return prices;
    }
//    public static List<Double> getAllProductPrices() {
//        List<WebElement> priceList = driver.findElements(By.xpath("//div[@class='priceWrapper css-2xph3x']"));
//        List<Double> prices = new ArrayList<>();
//
//        for (WebElement el : priceList) {
//            String priceText = el.getText().trim().replaceAll("[^\\d.]", "");
//            if (!priceText.isEmpty()) {
//                prices.add(Double.parseDouble(priceText));
//            }
//        }
//        return prices;
//    }

//    public static List<Double> getAllProductPrices() {
//        // Use explicit wait to ensure price elements are visible before interacting
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        List<WebElement> priceElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='priceWrapper css-2xph3x']")));
//
//        List<Double> prices = new ArrayList<>();
//
//        for (WebElement el : priceElements) {
//            String priceText = el.getText().trim();
//
//            if (priceText.isEmpty()) {
//                Log.warn("Empty price text found for an element, skipping...");
//                continue;
//            }
//
//            // Extract numeric values from price text
//            String numericPrice = priceText.replaceAll("[^\\d.]", "");
//
//            if (!numericPrice.isEmpty()) {
//                try {
//                    prices.add(Double.parseDouble(numericPrice));
//                } catch (NumberFormatException e) {
//                    Log.error("Failed to parse price: " + priceText, e);
//                }
//            } else {
//                Log.warn("Non-numeric price text found: " + priceText);
//            }
//        }
//
//        // Log collected prices for debugging
//        Log.info("Collected prices: " + prices);
//
//        return prices;
//    }


//    public static List<Double> getAllProductPrices() {
//        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@class='priceWrapper css-2xph3x']"));
//        List<Double> prices = new ArrayList<>();
//        for (WebElement el : priceElements) {
//            String priceText = el.getText().trim();
//            if (priceText.isEmpty()) {
//                Log.warn("Empty price text found for an element, skipping...");
//                continue;
//            }
//            String numericPrice = priceText.replaceAll("[^\\d.]", "");
//            if (!numericPrice.isEmpty()) {
//                try {
//                    prices.add(Double.parseDouble(numericPrice));
//                } catch (NumberFormatException e) {
//                    Log.error("Failed to parse price: " + priceText, e);
//                }
//            } else {
//                Log.warn("Non-numeric price text found: {}", priceText);
//            }
//        }
//        Log.info("Collected prices: " + prices);
//        return prices;
//    }


    public int getVisibleProductCount() {
        List<WebElement> productPrices = driver.findElements(By.xpath("//div[contains(@class, 'productTileImage')]"));
        return productPrices.size();
    }

    private WebElement getcategory(String category) {
        return driver.findElement(By.xpath("//p[contains(text(),'" + category + "')]"));
    }

    public void selecti(String category) {
        waitForSecs(5);
        WebElement element = getcategory(category);
        basePage.waitForElementVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        waitForSecs(3);
        Log.info("Selected category: " + category);
        waitForSecs(2);

        // Collapse the 'Style' filter
        WebElement styleDropdown = getcategory(category);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", styleDropdown);
        Log.info("Collapsed the Style filter");
        waitForSecs(1);
    }

    public boolean IsFastestDeliveryDisplayed() {
        waitForSecs(2);
        basePage.waitForElementVisible(FastestDelivery);
        return FastestDelivery.isDisplayed();
    }

    public void ClickOnShowMoreLink() {
        basePage.scrollToElement(ShowMoreLink);
        basePage.waitForElementToBeClickable(ShowMoreLink);
        basePage.click(ShowMoreLink);
        waitForSecs(5);
    }

    public boolean IsIndicatorDetailsDisplayed() {
        basePage.waitForElementVisible(IndicatorDetails);
        return IndicatorDetails.isDisplayed();
    }

    public boolean IsMarketingBannerDisplayed() {
        basePage.scrollToElement(marketingbanner);
        basePage.waitForElementVisible(MarketingBanner);
        return MarketingBanner.isDisplayed();
    }

    public boolean IsProductLabelAndPricingDisplayed() {
        try {
            basePage.waitForElementVisible(ProductLabel);
            basePage.waitForElementVisible(ProductPricing);

            boolean isLabelDisplayed = ProductLabel.isDisplayed();
            boolean isPricingDisplayed = ProductPricing.isDisplayed();

            return isLabelDisplayed && isPricingDisplayed;
        } catch (NoSuchElementException | TimeoutException e) {
            return false;
        }
    }

    public boolean IsFilterSectionDisplayed() {
        basePage.waitForElementVisible(FilterSection);
        return FilterSection.isDisplayed();
    }

    public void ClickOnListViewToggle() {
        basePage.waitForElementToBeClickable(ListViewToggle);
        basePage.click(ListViewToggle);
        waitForSecs(1);
    }

    public void ClickOnGridViewToggle() {
        basePage.waitForElementToBeClickable(GridViewToggle);
        basePage.click(GridViewToggle);
        waitForSecs(1);
    }

    private WebElement getPageTitleElement(String titleofpage) {
        return driver.findElement(By.xpath("//h1[normalize-space(text())='" + titleofpage + "']//parent::div"));
    }

    private WebElement getBreadcrumb(String Breadcrumb) {
        return driver.findElement(By.xpath("//a[normalize-space(text())='" + Breadcrumb + "']//parent::li"));
    }

    private WebElement Breadcrumb(String Breadcrumbnavigation) {
        return driver.findElement(By.xpath("//h1[contains(text(),'" + Breadcrumbnavigation + "')]"));
    }


//    public static boolean verifyTitleOfThePageMatchesTheSelectedCategory(String expectedCategory, String expectedTitle) {
//        String selectedCategory = getL2SubCategoriesLink(expectedCategory).getText().trim();
//        String pageTitle = getPageTitleElement(expectedTitle).getText().trim();
//
//        boolean isMatch = selectedCategory.equalsIgnoreCase(pageTitle);
//        Log.info("Selected Category: " + selectedCategory + ", Page Title: " + pageTitle + ", Match: " + isMatch);
//
//        return isMatch;
//    }

    public boolean verifyPageTitle(String expectedTitle) {
        String actualTitle = getPageTitleElement(expectedTitle).getText().trim();

        boolean isMatch = actualTitle.equalsIgnoreCase(expectedTitle);
        Log.info("Expected Title: " + expectedTitle + ", Actual Title: " + actualTitle + ", Match: " + isMatch);

        return isMatch;
    }

    public boolean IsBannerDisplayed() {
        basePage.waitForElementVisible(Banner);
        return Banner.isDisplayed();
    }

    public void clickBreadcrumb(String name) {
        WebElement breadcrumb = getBreadcrumb(name);
        basePage.scrollToElement(breadcrumb);
        basePage.waitForElementToBeClickable(breadcrumb);
        breadcrumb.click();
        Log.info("Clicked breadcrumb: " + name);
    }

    public boolean IsUserRedirected(String name) {
        WebElement breadcrumb = Breadcrumb(name);
        basePage.waitForElementVisible(breadcrumb);
        return breadcrumb.isDisplayed();
    }

    public void unselectCategory() {
        basePage.waitForElementToBeClickable(checkbox);
        checkbox.click();
        Log.info("Clicked");
    }

    public boolean IsCategoryDescriptionSectionDisplayed() {
        basePage.waitForElementVisible(CategoryDescription);
        return CategoryDescription.isDisplayed();
    }

    public void ClickOnReadMoreLink() {
        basePage.scrollToElement(ReadMore);
        basePage.waitForElementToBeClickable(ReadMore);
        ReadMore.click();
        Log.info("Clicked read more button");
    }

    public void ClickOnReadLessLink() {
        basePage.waitForElementToBeClickable(ReadLess);
        ReadLess.click();
        Log.info("Clicked ReadLess button");
    }

//    public static void unselectCategory(String category) {
//        waitForSecs(3);
//        WebElement element = getcategory(category);
//        BasePage.waitForElementVisible(element);
//
//        WebElement checkbox = element.findElement(By.xpath("//button[@id='accordion-button-:r30:']"));
//
//        if (checkbox.isSelected()) {
//            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
//            Log.info("Unselected category: " + category);
//        } else {
//            Log.info("Category was not selected: " + category);
//        }
//
//        waitForSecs(2);
//    }

    public void selectOption(String option) {
        WebElement paymentOption = driver.findElement(
                By.xpath("//p[contains(text(),'" + option + "')]")
        );
        basePage.waitForElementVisible(paymentOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentOption);
        Log.info("Clicked option: " + option);
        waitForSecs(2);
    }

    public List<WebElement> getAllFrequencyTexts() {
        return driver.findElements(By.xpath("//div[@class='css-1qowzy3']//div//a//div//div//div//p[1]//span"));
    }

    public void selectOptionFromListOfCategories(String option) {
        WebElement paymentOption = driver.findElement(
                By.xpath("//p[contains(text(),'" + option + "')]")
        );
        basePage.waitForElementVisible(paymentOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentOption);
        Log.info("Clicked: " + option);
        waitForSecs(2);
    }

    public void GetOption(String option) {
        WebElement paymentOption = driver.findElement(
                By.xpath("//p[contains(text(),'Price Range:')]")
        );
        basePage.waitForElementVisible(paymentOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentOption);
        Log.info("Clicked suboption: " + option);
        waitForSecs(2);
    }

    public int selectSubOptionAndGetProductCount(String option) {
        WebElement paymentOption = driver.findElement(
                By.xpath("//span[contains(text(),'" + option + "')]"));
        basePage.waitForElementVisible(paymentOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paymentOption);
        Log.info("Clicked option from list of categories " + option);
        waitForSecs(2);

        List<WebElement> productPrices = driver.findElements(
                By.xpath("//div[contains(@class, 'productTileImage')]")
        );
        return productPrices.size();
    }

    public void ClickOnClearAllButton() {
        basePage.scrollToElement(ClearAllButton);
        basePage.waitForElementToBeClickable(ClearAllButton);
        basePage.click(ClearAllButton);
        waitForSecs(1);
    }

    public int getProductCount() {
        List<WebElement> productPrices = driver.findElements(By.xpath("//div[contains(@class, 'productTileImage')]"));
        return productPrices.size();
    }

    public void setPriceRange(int min, int max) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);

        WebElement fromInput = driver.findElement(By.xpath("(//input[@type='text' and contains(@class, 'chakra-input')])[1]"));
        WebElement toInput = driver.findElement(By.xpath("(//input[@type='text' and contains(@class, 'chakra-input')])[2]"));

        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", fromInput, String.valueOf(min));
        fromInput.sendKeys(Keys.TAB);

        js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", toInput, String.valueOf(max));
        //toInput.sendKeys(Keys.TAB);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement applyButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply']")));

        applyButton.click();
        waitForSecs(8);
    }

    public List<Double> getDisplayedPrices() {
        List<WebElement> priceElements = driver.findElements(
                //By.xpath("//span[contains(text(),'per weekly') or contains(text(),'per week')]")
                By.xpath("//p[@class='chakra-text isPriceWrapper css-0']")

        );
        List<Double> prices = new ArrayList<>();
        for (WebElement priceElement : priceElements) {
            String text = priceElement.getText().replaceAll("[^\\d.]", "");
            if (!text.isEmpty()) {
                prices.add(Double.parseDouble(text));
            }
        }
        return prices;
    }
    public void selectOptionByVisibleText(String visibleText) {
        WebElement dropdown = driver.findElement(By.id("page_sort"));
        //WebElement dropdown = driver.findElement(By.xpath("//option[contains(text(),'" + visibleText + "')])"));
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
        System.out.println("Selected option: " + visibleText);
        waitForSecs(2);
    }

    public boolean getProductImages() {
        basePage.waitForElementVisible(productImage);
        return productImage.isDisplayed();
    }
    public boolean isFastestDeliveryToggleOff() {
        basePage.waitForElementVisible(fastestDeliveryToggle);
        System.out.println("Toggle state at page load: " + fastestDeliveryToggle.isSelected());
        return !fastestDeliveryToggle.isSelected();
    }

    public void turnOnFastestDeliveryToggle() {
        basePage.waitForElementVisible(fastestDeliveryToggle);
        if (!fastestDeliveryToggle.isSelected()) {
            fastestDeliveryToggle.click();
            Log.info(" Fastest Delivery toggle is now ON.");
        } else {
            Log.info(" Fastest Delivery toggle was already ON.");
        }
    }

    public List<WebElement> getFastestDeliveryProducts() {
        return driver.findElements(By.xpath("//div[contains(@class, 'productTileImage')]"));
    }

    public boolean IsProductNameIsDisplayed() {
        basePage.waitForElementVisible(ProductName);
        return ProductName.isDisplayed();
    }

    public void ClickOnMarketingBanner() {
        basePage.scrollToElement(marketingbanner);
        basePage.waitForElementToBeClickable(marketingbanner);
        basePage.click(marketingbanner);
        waitForSecs(2);
    }

    public void ClickOnWishListButton() {
        basePage.waitForElementToBeClickable(WishListButton);
        basePage.click(WishListButton);
        waitForSecs(2);
    }

    public boolean IsPopupDisplayed() {
        basePage.waitForElementVisible(Saved_your_item_popup);
        return Saved_your_item_popup.isDisplayed();
    }

    public boolean GetSavedItems() {
        basePage.waitForElementToBeClickable(ViewSavedItemsButton);
        basePage.click(ViewSavedItemsButton);
        waitForSecs(2);
        basePage.waitForElementVisible(saveditems);
        return saveditems.isDisplayed();
    }

    public boolean IsRemovedItemPopupDisplayed() {
        basePage.waitForElementVisible(Saved_your_item_popup);
        waitForSecs(2);
        basePage.waitForElementToBeClickable(cancelbutton);
        basePage.click(cancelbutton);
        //return Saved_your_item_popup.isDisplayed();
        return true;
    }

    private WebElement getL2SubCategoriesLink(String L2Category) {
        return driver.findElement(By.xpath("//section[@data-testid='popover-menu']//h5[text()='" + L2Category + "']//parent::a"));
    }

    public void GetL2SubCategory(String L2Category) {
//        getL2SubCategoriesLink(L2Category).isDisplayed();
        waitForSecs(2);
        basePage.waitForElementVisible(getL2SubCategoriesLink(L2Category));
        //return L2Category.isDisplayed();

    }


    public boolean isDeliveryOptionChecked(String deliveryOption) {
        WebElement checkbox = driver.findElement(By.xpath("//span[contains(text(),'" + deliveryOption + "')]/ancestor::label//input[@type='checkbox']"));
        basePage.waitForElementVisible(checkbox);
        boolean selected = checkbox.isSelected();
        System.out.println("Toggle state at page load: '" + deliveryOption + "': " + selected);
        return selected;
    }

    public void ClickOnShopAllButton() {
        basePage.scrollToElement(shopallbutton);
        basePage.waitForElementToBeClickable(shopallbutton);
        basePage.click(shopallbutton);
        waitForSecs(2);
    }

    public void scrollToSection(String sectionName) {
        basePage.scrollToElement(ShopByBrandSection);
        waitForSecs(2);
    }

    public void moveCursorToElement() {
        WebElement element = driver.findElement(By.xpath("//button[@aria-label='Logo']//*[name()='svg']//*[name()='use' and contains(@role,'presentati')]"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public List<WebElement> getProductBadges() {
        return driver.findElements(By.xpath("//span[text()='Clearance']//parent::div"));
    }

    public void selectItemsInMyApprovalRange() {
        WebElement approvalCheckbox = driver.findElement(
                By.xpath("//span[contains(., 'Items In My Approval Range')]/preceding-sibling::input[@type='checkbox']")
        );
        basePage.waitForElementVisible(approvalCheckbox);
        if (!approvalCheckbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approvalCheckbox);
            Log.info("Checked 'Items In My Approval Range'");
        } else {
            Log.info("'Items In My Approval Range' is already checked");
        }
        waitForSecs(2);
    }

    public void verifyPriceRangeUpToDollar() {
        WebElement priceRangeLabel = driver.findElement(By.xpath("//span[contains(text(),'Up to $')]"));
        basePage.waitForElementVisible(priceRangeLabel);
        String actualText = priceRangeLabel.getText().trim();
        String[] parts = actualText.split("\\d");
        String pricePrefix = parts[0].trim();
        if (pricePrefix.equals("Up to $")) {
            Log.info("Verified price range is " + pricePrefix);
        } else {
            throw new AssertionError("Price range label did not match expected" + pricePrefix);
        }
    }

    public void verifyItemsInMyApprovalRangeOptionNotDisplayed() {
        List<WebElement> approvalOption = driver.findElements(
                By.xpath("//label[contains(., 'Items In My Approval Range')]")
        );
        if (approvalOption.isEmpty()) {
            Log.info("'Items In My Approval Range' option is NOT present in the DOM — as expected.");
        } else if (!approvalOption.get(0).isDisplayed()) {
            Log.info("'Items In My Approval Range' is present but NOT visible — acceptable.");
        } else {
            throw new AssertionError("'Items In My Approval Range' option IS visible, but it should NOT be.");
        }
    }

    public void verifyApprovalRangeUnchecked() {
        WebElement approvalCheckbox = driver.findElement(
                By.xpath("//span[contains(., 'Items In My Approval Range')]/preceding-sibling::input[@type='checkbox']")
        );
        basePage.waitForElementVisible(approvalCheckbox);
        boolean isChecked = approvalCheckbox.isSelected();
        if (!isChecked) {
            List<WebElement> priceRange = driver.findElements(
                    By.xpath("//p[contains(., 'Price Range:')]//span[contains(text(), 'Up to $')]"));
            if (priceRange.isEmpty() || !priceRange.get(0).isDisplayed()) {
                Log.info("As expected, 'Price Range: Up to $' is NOT visible when approval range is unchecked.");
            } else {
                throw new AssertionError("'Price Range: Up to $' IS visible even though approval range is unchecked.");}
        } else {
            Log.info("'Items In My Approval Range' is checked, so skipping this validation.");
        }
    }

    public void unselectItemsInMyApprovalRange() {
        WebElement approvalCheckbox = driver.findElement(
                By.xpath("//span[contains(., 'Items In My Approval Range')]/preceding-sibling::input[@type='checkbox']")
        );
        basePage.waitForElementVisible(approvalCheckbox);

        if (approvalCheckbox.isSelected()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", approvalCheckbox);
            Log.info("Unchecked 'Items In My Approval Range'");
        } else {
            Log.info("'Items In My Approval Range' is already unchecked");
        }

        waitForSecs(2);
    }

}