package com.pages;

import com.qa.util.LoggerHelper;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.NoSuchElementException;

import static com.qa.factory.DriverFactory.getDriver;
import static com.qa.util.ElementActions.waitForSecs;

public class BasePage {

    public WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jsExecutor;
    private static final Logger Log = LoggerHelper.getLogger();

    // Constructor to initialize WebDriver and utilities
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        this.jsExecutor = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);  // Initialize @FindBy elements
    }

    // Generic method to click an element
    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    // Generic method to wait for an element to be clickable
    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    // Generic method to enter text
    public void enterText(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element)).clear();
        element.sendKeys(text);
        Log.info("Enter Text: {}", text);
    }


    // Generic method to wait for an element to be visible
    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public void waitForElementInVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }


    // Generic method to scroll to an element
    public void scrollToElement_OLD(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        waitForSecs(2);
    }

    public void scrollToElement(WebElement element) {
        try {
            jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            waitForSecs(2);
        } catch (Exception e) {
            Log.info("Scrolling to element failed: {}", e.getMessage());
        }
    }


    // Generic method to switch to a frame
    public void switchToFrame(WebElement frameElement) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
    }

    // Generic method to get the page title
    public String getPageTitle() {
        return getDriver().getTitle();
    }

    // Generic method to compare error message
    public boolean isErrorMessageDisplayed(WebElement errorMessageElement, String expectedErrorMessage) {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageElement));
            String actualErrorMessage = errorMessageElement.getText().trim();
            return actualErrorMessage.equals(expectedErrorMessage);
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessageDisplayed(WebElement errorMessageElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageElement));
            return errorMessageElement.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    // Generic method to check if an element is displayed
    public boolean isElementDisplayed(WebElement element) {
        boolean flag = false;
        try {
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = element.isDisplayed();
            Log.info("Element displayed: {}", element);
        } catch (Exception e) {
            Log.info("Element NOT displayed: {}", element);
            Log.info(e);
        }
        return flag;
    }
    
    public boolean isElementDisplayedOptional(WebElement element) {
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = element.isDisplayed();
            Log.info("Element displayed: {}", element);
        } catch (Exception e) {
            Log.info("Element NOT displayed: {}", element);
            Log.info(e);
        }
        return flag;
    }

    public boolean isElementNotDisplayed(WebElement element) {
        try {
            return element == null || !element.isDisplayed();
        } catch (Exception e) {
            Log.info("Element is not displayed or not present in the DOM.");
            return true;
        }
    }


    public boolean isTextDisplayed(String expectedText, WebDriver driver) {
        try {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isTextDisplayed(String expectedText) {
        try {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(),'" + expectedText + "')]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickLinkByText(String linkText, WebDriver driver) {
        try {
            WebElement link = driver.findElement(By.xpath("//*[normalize-space()='" + linkText + "']"));
            link.click();
        } catch (Exception e) {
            System.out.println("Failed to click link: " + linkText + ". Error: " + e.getMessage());
        }
    }

    public void clickLinkByText(String linkText) {
        try {
            WebElement link = driver.findElement(By.xpath("//*[normalize-space()='" + linkText + "']"));
            link.click();
            waitForSecs(1);
        } catch (Exception e) {
            System.out.println("Failed to click link: " + linkText + ". Error: " + e.getMessage());
        }
    }
    public void clickLinkByName(String linkText, WebDriver driver) {
        try {
            WebElement link = driver.findElement(By.xpath("//a[normalize-space()='" + linkText + "']"));
            link.click();
        } catch (Exception e) {
            System.out.println("Failed to click link: " + linkText + ". Error: " + e.getMessage());
        }
    }

    public void jsClickLinkByName(String linkText) {
        try {
            WebElement link = driver.findElement(By.xpath("//a[normalize-space()='" + linkText + "']"));
            //link.click();
            jsExecutor.executeScript("arguments[0].click();", link);
            wait(2);
        } catch (Exception e) {
            System.out.println("Failed to click link: " + linkText + ". Error: " + e.getMessage());
        }
    }
    public void jsClickLinkByNameInContainer(String linkText) {
        try {
            WebElement link = driver.findElement(By.xpath("//div[@class='container']//a[normalize-space()='" + linkText + "']"));
            //link.click();
            jsExecutor.executeScript("arguments[0].click();", link);
            wait(2);
        } catch (Exception e) {
            System.out.println("Failed to click link: " + linkText + ". Error: " + e.getMessage());
        }
    }

    public void clickButtonByName(String buttonName) {
        try {
            WebElement button = driver.findElement(By.xpath("//button[text()='" + buttonName + "']"));
            scrollToElement(button);
            button.click();
        } catch (Exception e) {
            System.out.println("Failed to click link: " + buttonName + ". Error: " + e.getMessage());
        }
    }

    public void clickButtonByText(String buttonName) {
        try {
            WebElement button = driver.findElement(By.xpath("//span[text()='" + buttonName + "']"));
            button.click();
        } catch (Exception e) {
            System.out.println("Failed to click link: " + buttonName + ". Error: " + e.getMessage());
        }
    }
    
    public void jsClick(WebElement element) {
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        waitForSecs(1);
        jsExecutor.executeScript("arguments[0].click();", element);
        waitForSecs(1);
    }

    public void jsEnterText(WebElement inputField, String val) {
        // Clear and enter text using JavaScript
        jsExecutor.executeScript("arguments[0].value=arguments[1];", inputField, val);
        waitForSecs(2);
    }

    public boolean isLinkDisplayed(String sectionName) {
        try {
            WebElement element = driver.findElement(By.xpath("//a[contains(text(),'" + sectionName + "')]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementHidden(WebElement element) {
        boolean isHidden;
        if ((boolean) jsExecutor.executeScript("return arguments[0].offsetWidth === 0 || arguments[0].offsetHeight === 0 || window.getComputedStyle(arguments[0]).visibility === 'hidden'", element))
            isHidden = true;
        else {
            isHidden = false;
        }
        return isHidden;
    }

    public void clickAllowButtonOnPermission() {

        // Casting AndroidDriver to SupportsContextSwitching directly
        SupportsContextSwitching contextSwitchingDriver = (SupportsContextSwitching) driver;
        String currentContext = contextSwitchingDriver.getContext();
        System.out.println("Current Context: "+currentContext);
        System.out.println("Handles: "+contextSwitchingDriver.getContextHandles());

        if (driver instanceof SupportsContextSwitching) {
            ((SupportsContextSwitching) driver).context("NATIVE_APP");
            System.out.println("Context switching supported.");
        } else {
            System.out.println("Context switching not supported.");
        }
        // native popup
        waitForSecs(3);
        /*
        while (!getDriver().findElements(By.xpath("//android.widget.Button[@text='Allow this time']")).isEmpty()) {
            getDriver().findElement(By.xpath("//android.widget.Button[@text='Allow this time']")).click();
            System.out.println("1st attempt");
        }*/
        while (!getDriver().findElements(By.xpath("//android.widget.Button[@text='Allow']")).isEmpty()) {
            getDriver().findElement(By.xpath("//android.widget.Button[@text='Allow']")).click();
            System.out.println("1st attempt");
            Log.info("Allow button clicked");
        }

        waitForSecs(3);
        ((SupportsContextSwitching) driver).context("CHROMIUM");
        waitForSecs(3);
        /*//System Popups
        waitForSecs(5);
        Map<String, Object> params = new HashMap<>();
        jsExecutor.executeScript("mobile: acceptAlert", params);
        waitForSecs(2);
        System.out.println("2nd attempt");*/
    }

    public void waitForPageToLoad() {
        Log.info("Waiting for the page to load completely");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
        waitForSecs(4);
    }

    public void waitForPageToLoadPerf() {
        Log.info("Waiting for the page to load completely");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete' ? true : null"));

    }

    public void waitForDialogToDisappear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public boolean pageHeaderContainsText(String headerText) {
        try {
            WebElement element = driver.findElement(By.xpath("//h1[contains(text(), '" + headerText + "')]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("Header Text not displayed on page");
            Log.info("Header Text is not displayed on page", headerText);
            return false;
        }
    }

    public void waitForUrlToContain(String urlText) {
        wait.until(ExpectedConditions.urlContains(urlText));
    }
    public boolean isTextDisplayedWithSpecialChars(String expectedText) {
        try {
            WebElement element = driver.findElement(By.xpath("//*[contains(text(),\"" + expectedText + "\")]"));
            return element.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public String buildSafeXpathText(String text) {
        if (!text.contains("'")) {
            // No apostrophe — wrap with single quotes safely
            return "'" + text + "'";
        } else {
            // Has apostrophes — break into pieces and use concat()
            String[] parts = text.split("'");
            StringBuilder xpathBuilder = new StringBuilder("concat(");
            for (int i = 0; i < parts.length; i++) {
                xpathBuilder.append("\"").append(parts[i]).append("\"");
                if (i < parts.length - 1) {
                    xpathBuilder.append(", \"'\", ");
                }
            }
            xpathBuilder.append(")");
            return xpathBuilder.toString();
        }
    }

    public static String getNextDate() {
        String nextDate="";
        String nextDate_complete = "";
        try {
            Calendar today = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            today.add(Calendar.DAY_OF_YEAR, 1);
            nextDate_complete = format.format(today.getTime());
            String nextDaySplit[]=nextDate_complete.split("/");
            nextDate = nextDaySplit[2];
            int i = Integer.parseInt(nextDate);
            if(i<10)
            {
                i=i/1;
                nextDate=String.valueOf(i);
            }
            else
            {
                nextDate = nextDaySplit[2];
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return nextDate;
    }

    public void clickButtonIfDisplayed(WebElement element) {

            try {
               // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(9));
                if (element.isDisplayed()) {
                    element.click();
                    System.out.println("Button clicked");
                } else {
                    System.out.println("Button is not displayed");
                }
            } catch (NoSuchElementException | ElementNotInteractableException e) {
                // Button not found or not interactable – handle silently
                System.out.println("Button not found or not interactable. Skipping click.");
            }
    }
    public void waitForElementInvisible(WebElement element) {
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    // Generic method to switch to a default content
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public void clearTextAndEnterValueInInputField(WebElement element,String inputValue)
    {
        waitForElementToBeClickable(element);
        element.click(); // Ensure focus
        element.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        element.sendKeys(inputValue);
        element.sendKeys("\t");
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }

    //BM application used these methods
    public void scrollToBottomOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public WebElement getElementByLinkText(String text){
        return driver.findElement(By.xpath("//a[contains(text(), '"+text+"')]"));
    }

    public void acceptAllCookies() {
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
    }
}
