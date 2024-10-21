package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountFields;
import co.wedevx.digitalbank.automation.ui.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreateCheckingPage extends BaseMenuPage {
    public CreateCheckingPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "name")
    private WebElement webAccountName;
    @FindBy(id = "openingBalance")
    private WebElement webInitialDeposit;
    @FindBy(id = "Standard Checking")
    private WebElement standardChecking;
    @FindBy(id = "Interest Checking")
    private WebElement interestChecking;
    @FindBy(id = "Individual")
    private WebElement individualOwnership;
    @FindBy(id = "Joint")
    private WebElement jointOwnership;
    @FindBy(id = "newCheckingSubmit")
    private WebElement submitNewAccountCreationBtn;
    @FindBy(id = "newCheckingReset")
    private WebElement resetNewAccountCreationBtn;
    @FindBy(id = "new-account-error-msg")
    private WebElement actualErrorMessageDeposit;

    public void createNewChecking(List<NewCheckingAccountFields> newCheckingAccountFields, String submitOrReset) {
        NewCheckingAccountFields field = newCheckingAccountFields.get(0);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click the Checking Button
        wait.until(ExpectedConditions.elementToBeClickable(checkingBtn)).click();

        // Click the New Checking Button and wait for the correct URL
        wait.until(ExpectedConditions.elementToBeClickable(newCheckingBtn)).click();
        wait.until(ExpectedConditions.urlToBe(ConfigReader.getProperty("digitalbank.createnewcheckingurl")));

        // Account type
        if (field.getAccountType() != null) {
            if (field.getAccountType().equalsIgnoreCase("Standard Checking")) {
                wait.until(ExpectedConditions.elementToBeClickable(standardChecking)).click();
            } else if (field.getAccountType().equalsIgnoreCase("Interest Checking")) {
                wait.until(ExpectedConditions.elementToBeClickable(interestChecking)).click();
            } else {
                throw new NoSuchElementException("Invalid checking account type option.");
            }
        }

        // Account ownership
        if (field.getAccountOwnership() != null) {
            if (field.getAccountOwnership().equalsIgnoreCase("Individual")) {
                wait.until(ExpectedConditions.elementToBeClickable(individualOwnership)).click();
            } else if (field.getAccountOwnership().equalsIgnoreCase("Joint")) {
                wait.until(ExpectedConditions.elementToBeClickable(jointOwnership)).click();
            } else {
                throw new NoSuchElementException("Invalid checking account ownership option.");
            }
        }

        // Account name
        if (field.getAccountName() != null) {
            wait.until(ExpectedConditions.visibilityOf(webAccountName)).sendKeys(field.getAccountName());
        }

        // Initial deposit
        if (field.getInitialDeposit() != null) {
            wait.until(ExpectedConditions.visibilityOf(webInitialDeposit)).sendKeys(field.getInitialDeposit());
        }

        // Submit or Reset the form
        if (submitOrReset.equalsIgnoreCase("Submit")) {
            wait.until(ExpectedConditions.elementToBeClickable(submitNewAccountCreationBtn)).click();
        } else if (submitOrReset.equalsIgnoreCase("Reset")) {
            wait.until(ExpectedConditions.elementToBeClickable(resetNewAccountCreationBtn)).click();
        }
    }

    public String getErrorMessageDeposit() {
        return actualErrorMessageDeposit.getText();
    }

    public String getActualErrorMessageOfField(String field) {
        WebElement emptyField = driver.findElement(By.id(field));
//    String actualResult = (String) ((JavascriptExecutor) driver)
//            .executeScript("return arguments[0].validationMessage;", emptyField);
        return emptyField.getAttribute("validationMessage");
    }

    public void checkAllFieldsAreDisabled() {
        //radio buttons disabled
        assertFalse(standardChecking.isSelected(), "Standard Checking enabled");
        assertFalse(interestChecking.isSelected(), "Interest Checking enabled");
        assertFalse(individualOwnership.isSelected(), "Individual enabled");
        assertFalse(jointOwnership.isSelected(), "Joint enabled");
        //txt boxes type=text empty
        assertTrue(webAccountName.getAttribute("value").isEmpty(), "Account name not empty");
        assertTrue(webInitialDeposit.getAttribute("value").isEmpty(), "Initial Deposit not empty");
    }
}
