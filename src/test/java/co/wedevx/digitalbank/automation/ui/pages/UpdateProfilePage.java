package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.UpdateProfileDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class UpdateProfilePage extends BaseMenuPage{
    public UpdateProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    private WebElement titleDropdown;
    @FindBy(id = "firstName")
    private WebElement firstNameTxtBox;
    @FindBy(id = "lastName")
    private WebElement lastNameTxtBox;
    @FindBy(id = "homePhone")
    private WebElement homePhoneTxtBox;
    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTxtBox;
    @FindBy(id = "workPhone")
    private WebElement workPhoneTxtBox;
    @FindBy(id = "address")
    private WebElement addressTxtBox;
    @FindBy(id = "locality")
    private WebElement localityTxtBox;
    @FindBy(id = "region")
    private WebElement regionTxtBox;
    @FindBy(id = "postalCode")
    private WebElement postalCodeTxtBox;
    @FindBy(id = "country")
    private WebElement countryTxtBox;
    @FindBy(xpath = "//button[text()=' Submit']")
    private WebElement submitBtn;
    @FindBy(xpath = "//button[text()=' Reset']")
    private WebElement resetBtn;
    @FindBy(xpath = "//div[@class='card-header bg-flat-color-1']")
    private WebElement updatePrH;


    public String getUpdateProfileSubTitle() {
        return updatePrH.getText();
    }
    public void updateProfile(List<UpdateProfileDetails> updateInfo) {
        UpdateProfileDetails info = updateInfo.get(0);
        Select selectTitle = new Select(titleDropdown);
        if (!info.getTitle().equals("-")) selectTitle.selectByValue(info.getTitle());
        if (!info.getFirstName().equals("-")) {
            firstNameTxtBox.clear();
            firstNameTxtBox.sendKeys(info.getFirstName());
        }
        if (!info.getLastName().equals("-")) {
            lastNameTxtBox.clear();
            lastNameTxtBox.sendKeys(info.getLastName());
        }
        if (!info.getHomePhone().equals("-")) {
            homePhoneTxtBox.clear();
            homePhoneTxtBox.sendKeys(info.getHomePhone());
        }
        if (!info.getMobilePhone().equals("-")) {
            mobilePhoneTxtBox.clear();
            mobilePhoneTxtBox.sendKeys(info.getMobilePhone());
        }
        if (!info.getWorkPhone().equals("-")) {
            workPhoneTxtBox.clear();
            workPhoneTxtBox.sendKeys(info.getWorkPhone());
        }
        if (!info.getAddress().equals("-")) {
            addressTxtBox.clear();
            addressTxtBox.sendKeys(info.getAddress());
        }
        if (!info.getLocality().equals("-")) {
            localityTxtBox.clear();
            localityTxtBox.sendKeys(info.getLocality());
        }
        if (!info.getRegion().equals("-")) {
            regionTxtBox.clear();
            regionTxtBox.sendKeys(info.getRegion());
        }
        if (!info.getPostalCode().equals("-")) {
            postalCodeTxtBox.clear();
            postalCodeTxtBox.sendKeys(info.getPostalCode());
        }
        if (!info.getCountry().equals("-")) {
            countryTxtBox.clear();
            countryTxtBox.sendKeys(info.getCountry());
        }
    }
    public void submitUpdatingProfile() {
        submitBtn.click();
    }
    public void resetUpdatingProfile() {
        resetBtn.click();
    }

    public WebElement getTitleDropdown() {
        return titleDropdown;
    }

    public WebElement getFirstNameTxtBox() {
        return firstNameTxtBox;
    }

    public WebElement getLastNameTxtBox() {
        return lastNameTxtBox;
    }

    public WebElement getHomePhoneTxtBox() {
        return homePhoneTxtBox;
    }

    public WebElement getMobilePhoneTxtBox() {
        return mobilePhoneTxtBox;
    }

    public WebElement getWorkPhoneTxtBox() {
        return workPhoneTxtBox;
    }

    public WebElement getAddressTxtBox() {
        return addressTxtBox;
    }

    public WebElement getLocalityTxtBox() {
        return localityTxtBox;
    }

    public WebElement getRegionTxtBox() {
        return regionTxtBox;
    }

    public WebElement getPostalCodeTxtBox() {
        return postalCodeTxtBox;
    }

    public WebElement getCountryTxtBox() {
        return countryTxtBox;
    }
}
