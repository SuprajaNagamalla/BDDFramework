package com.qa.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.factory.DriverFactory.getDriver;

public class ElementActions {

    protected WebDriver driver=getDriver();

    //color - red,orange,etc
    public void highlightElement(WebElement element, String color) throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid " + color + "'", element);
        Thread.sleep(2000);
    }

    public static void waitForSecs(int secs) {
        try {
            Thread.sleep(secs * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
        }
    }



}
