package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseMenuPage extends BasePage {
    public BaseMenuPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "checking-menu")
    protected WebElement checkingBtn;
    @FindBy(id = "new-checking-menu-item")
    protected WebElement newCheckingBtn;
    @FindBy(id = "view-checking-menu-item")
    protected WebElement viewCheckingBtn;
    @FindBy(id = "home-menu-item")
    protected WebElement homeBtn;
    @FindBy(id = "savings-menu")
    protected WebElement savingsBtn;
    @FindBy(id = "view-savings-menu-item")
    protected WebElement viewSavingsBtn;
    @FindBy(id = "new-savings-menu-item")
    protected WebElement newSavingsBtn;
    @FindBy(id = "external-accounts-menu")
    protected WebElement externalBtn;
    @FindBy(id = "link-external-menu-item")
    protected WebElement linkExternalBtn;
    @FindBy(id = "view-external-menu-item")
    protected WebElement viewExternalBtn;
    @FindBy(id = "deposit-menu-item")
    protected WebElement depositBtn;
    @FindBy(id = "withdraw-menu-item")
    protected WebElement withdrawBtn;
    @FindBy(id = "transfer-menu-item")
    protected WebElement transferBtn;
    @FindBy(id = "visa-transfer-menu-item")
    protected WebElement visTransferBtn;
    @FindBy(id = "menuToggle")
    protected WebElement menuToggleBtn;
    @FindBy(id = "searchLocations")
    protected WebElement searchBtn;
    @FindBy(id = "notification")
    protected WebElement notificationsBtn;
    @FindBy(id = "message")
    protected WebElement messagesBtn;
    @FindBy(id = "aboutLink")
    protected WebElement aboutBtn;
    @FindBy(id = "language")
    protected WebElement languageBtn;
    @FindBy(xpath = "//img[@class='user-avatar rounded-circle']")
    protected WebElement profileIcon;
    @FindBy(xpath = "//a[text()='My Profile']")
    protected WebElement myProfileBtn;
    @FindBy(xpath="//a[text()= 'Change Password']")
    protected WebElement changePasswordBtn;

    public void goToUpdateProfilePage() {
        profileIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(myProfileBtn)).click();
    }

    public void goToUpdatePasswordPage(){
        profileIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(changePasswordBtn)).click();
    }
    public void goToHomePage() {
        homeBtn.click();
    }
}
