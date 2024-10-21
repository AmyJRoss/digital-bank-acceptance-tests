package co.wedevx.digitalbank.automation.ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * WebDriver Helper Extensions is designed to simplify Java based Selenium/WebDriver tests.
 * It is built on top of Selenium/WebDriver to make your test more readable,
 * reusable and maintainable by providing easy handling browsers and advance identifiers.
 */
public class BrowserHelper {
    private WebDriver driver;

    // Constructor to initialize the WebDriver instance
    public BrowserHelper(WebDriver driver) {
        this.driver = driver;
    }

    //wait until the element is visible
    public WebElement waitForVisibilityOfElement(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    //wait until the element is clickable and click on it
    public WebElement waitUntilElementClickableAndClick(WebElement element, int timeToWaitInSec) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeToWaitInSec));
        WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));
        clickableElement.click();
        return clickableElement;
    }

    //fluent wait for presence of an element by xpath
    public WebElement fluentWaitForElementPresenceByXpath(String xpathLocator, int timeToWaitInSec) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeToWaitInSec)).
                pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathLocator)));
    }

    //scroll into view
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
        //"true" for the top of view point, "false" for bottom
    }

    // Method to click an element based on its text content
    public WebElement clickElementWithText(String text) {
        try {
            // Use normalize-space to handle extra spaces and wait until the element is clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[normalize-space(text())='" + text + "']")
            ));

            // Click the element
            element.click();
            return element;

        } catch (Exception e) {
            System.out.println("Error finding/clicking the element with text: " + text + ". Error: " + e.getMessage());
            return null;
        }
    }

    //send keys to txt box input
    public void fillTextInput(WebElement txtBoxElement, String keysToSend) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true)", txtBoxElement);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(txtBoxElement));
            wait.until(ExpectedConditions.elementToBeClickable(txtBoxElement));
            if (txtBoxElement.isEnabled() && txtBoxElement.isDisplayed()) {
                txtBoxElement.clear();
                txtBoxElement.sendKeys(keysToSend);
            }
        } catch (Exception e) {
            System.out.println("Error interacting with the input element: " + e.getMessage());
        }
    }

    //get text of element by xpath
    public String getTextOfElementByXpath(String xpath) {
        WebElement element = driver.findElement(By.xpath(xpath));
        return element.getText();
    }
}
