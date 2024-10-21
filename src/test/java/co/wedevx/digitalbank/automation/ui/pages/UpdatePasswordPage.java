package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.Passwords;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UpdatePasswordPage extends BaseMenuPage {
    WebDriver driver;

    public UpdatePasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "currentPassword")
    private WebElement currentPasswordTxtBox;
    @FindBy(id = "newPassword")
    private WebElement newPasswordTxtBox;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordTxtBox;
    @FindBy(id = "payment-button")
    private WebElement updatePassBtn;
    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']")
    private WebElement successMessage;

    public String getSuccessMessage() {
        return successMessage.getText();
    }

    public void updatePassword(Passwords passwords) {
        if (!passwords.getOldPass().equals("-")) {
            currentPasswordTxtBox.sendKeys(passwords.getOldPass());
        }
        newPasswordTxtBox.sendKeys(passwords.getNewPass());
        confirmPasswordTxtBox.sendKeys(passwords.getConfirmPass());
        updatePassBtn.click();
    }

    public String getNewPassError(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", newPasswordTxtBox)
                + "\n" + newPasswordTxtBox.getAttribute("title");
    }

    public String getConfirmPassError(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", confirmPasswordTxtBox);
    }

    public String getCurrentPassError(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].validationMessage;", currentPasswordTxtBox);

    }
}
