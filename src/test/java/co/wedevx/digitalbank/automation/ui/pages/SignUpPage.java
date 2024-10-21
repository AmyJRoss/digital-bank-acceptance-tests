package co.wedevx.digitalbank.automation.ui.pages;

import co.wedevx.digitalbank.automation.ui.models.SignUpDetails;
import co.wedevx.digitalbank.automation.ui.utils.MockData;
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

public class SignUpPage extends BasePage {
    private MockData mocker = new MockData();

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "title")
    private WebElement titleDropdown;
    @FindBy(id = "firstName")
    private WebElement firstNameTxtBox;
    @FindBy(id = "lastName")
    private WebElement lastNameTxtBox;
    @FindBy(xpath = "//input[@value='M']")
    private WebElement genderM;
    @FindBy(xpath = "//input[@value='F']")
    private WebElement genderF;
    @FindBy(id = "dob")
    private WebElement dateOfBirthTxtBox;
    @FindBy(id = "ssn")
    private WebElement socialSecurityNumberTxtBox;
    @FindBy(id = "emailAddress")
    private WebElement emailAddressTxtBox;
    @FindBy(id = "password")
    private WebElement passwordTxtBox;
    @FindBy(id = "confirmPassword")
    private WebElement confirmPassTxtBox;
    @FindBy(xpath = "//button[text()='Next']")
    private WebElement nextBtn;

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
    @FindBy(id = "homePhone")
    private WebElement homePhoneTxtBox;
    @FindBy(id = "mobilePhone")
    private WebElement mobilePhoneTxtBox;
    @FindBy(id = "workPhone")
    private WebElement workPhoneTxtBox;
    @FindBy(id = "agree-terms")
    private WebElement agreeToTerms;
    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerBtn;
    @FindBy(xpath = "//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']/span[2]")
    private WebElement successMessage;

    public void fillSignUpFieldsFromList(List<SignUpDetails> signUpFields) {
        SignUpDetails info = signUpFields.get(0);
        Select selectTitle = new Select(titleDropdown);
        if (info.getTitle() != null) {
            selectTitle.selectByValue(info.getTitle());
        }

        if (info.getFirstName() != null) {
            firstNameTxtBox.sendKeys(info.getFirstName());
        }

        if (info.getLastName() != null) {
            lastNameTxtBox.sendKeys(info.getLastName());
        }

        if (info.getGender() != null) {
            if (info.getGender().equalsIgnoreCase("F")) genderF.click();
            else if (info.getGender().equalsIgnoreCase("M")) genderM.click();
        }

        if (info.getDateOfBirth() != null) {
            dateOfBirthTxtBox.sendKeys(info.getDateOfBirth());
        }

        if (info.getSsn() != null) {
            socialSecurityNumberTxtBox.sendKeys(info.getSsn());
        }

        if (info.getEmail() != null) {
            emailAddressTxtBox.sendKeys(info.getEmail());
        }

        if (info.getPassword() != null) {
            passwordTxtBox.sendKeys(info.getPassword());
        }

        if (info.getConfirmPassword() != null) {
            confirmPassTxtBox.sendKeys(info.getPassword());
        }

        nextBtn.click();
        if (addressTxtBox.isDisplayed()) {

            if (info.getAddress() != null) {
                addressTxtBox.sendKeys(info.getAddress());
            }

            if (info.getLocality() != null) {
                localityTxtBox.sendKeys(info.getLocality());
            }

            if (info.getRegion() != null) {
                regionTxtBox.sendKeys(info.getRegion());
            }

            if (info.getPostalCode() != null) {
                postalCodeTxtBox.sendKeys(info.getPostalCode());
            }

            if (info.getCountry() != null) {
                countryTxtBox.sendKeys(info.getCountry());
            }

            if (info.getHomePhone() != null) {
                homePhoneTxtBox.sendKeys(info.getHomePhone());
            }
            if (info.getMobilePhone() != null) {
                mobilePhoneTxtBox.sendKeys(info.getMobilePhone());
            }
            if (info.getWorkPhone() != null) {
                workPhoneTxtBox.sendKeys(info.getWorkPhone());
            }

            if (info.isAgreeToTerms()) agreeToTerms.click();

            registerBtn.click();
        }
    }

    public String getMessage() {
        return successMessage.getText();
    }

    public String getRequiredFieldErrorMessage(String fieldName) {
        switch (fieldName.toLowerCase()) {
            case "title":
                return titleDropdown.getAttribute("validationMessage");
            case "firstname":
                return firstNameTxtBox.getAttribute("validationMessage");
            case "lastname":
                return lastNameTxtBox.getAttribute("validationMessage");
            case "gender":
                return genderM.getAttribute("validationMessage");
            case "dateofbirth":
                return dateOfBirthTxtBox.getAttribute("validationMessage");
            case "ssn":
                return socialSecurityNumberTxtBox.getAttribute("validationMessage");
            case "email":
                return emailAddressTxtBox.getAttribute("validationMessage");
            case "password":
                return passwordTxtBox.getAttribute("validationMessage");
            case "confirmpassword":
                return confirmPassTxtBox.getAttribute("validationMessage");
            case "address":
                return addressTxtBox.getAttribute("validationMessage");
            case "locality":
                return localityTxtBox.getAttribute("validationMessage");
            case "region":
                return regionTxtBox.getAttribute("validationMessage");
            case "postalcode":
                return postalCodeTxtBox.getAttribute("validationMessage");
            case "country":
                return countryTxtBox.getAttribute("validationMessage");
            case "homephone":
                return homePhoneTxtBox.getAttribute("validationMessage");
            case "agreeToTerms":
                return agreeToTerms.getAttribute("validationMessage");
            default:
                return null;
        }
    }
}
