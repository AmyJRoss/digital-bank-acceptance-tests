package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.UpdateProfileDetails;
import co.wedevx.digitalbank.automation.ui.pages.UpdateProfilePage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import co.wedevx.digitalbank.automation.ui.utils.MockData;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UpdateProfileSteps {
    WebDriver driver = Driver.getDriver();
    private MockData mocker = new MockData();
    List<UpdateProfileDetails> updateInfo;
    private UpdateProfilePage updateProfilePage = new UpdateProfilePage(driver);

    @Given("the user navigated to the {string} page by hitting profile picture and selecting {string}")
    public void the_user_navigated_to_the_page_by_hitting_profile_picture_and_selecting(String updateProfile, String myProfile) {
        updateProfilePage.goToUpdateProfilePage();
        assertTrue(updateProfilePage.getUpdateProfileSubTitle().contains(updateProfile));
    }

    @When("the user updates the following fields with valid data")
    public void the_user_updates_the_following_fields_with_valid_data(List<UpdateProfileDetails> updateInfo) {
        updateProfilePage.updateProfile(updateInfo);
        updateProfilePage.submitUpdatingProfile();
        this.updateInfo = updateInfo;
    }

    @When("the user updates profile with random data")
    public void the_user_updates_profile_with_random_data() {
        updateProfilePage.goToUpdateProfilePage();
        this.updateInfo = List.of(mocker.generateUpdateData());
        updateProfilePage.updateProfile(updateInfo);
        updateProfilePage.submitUpdatingProfile();
    }

    @Then("the user should see the success message")
    public void the_user_should_see_the_success_message() {
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='sufee-alert alert with-close alert-success alert-dismissible fade show']"));
        assertTrue(successMessage.getText().contains("Profile Updated Successfully"));
    }

    @Then("the user should see updated information")
    public void the_user_should_see_updated_information() {
        UpdateProfileDetails info = updateInfo.get(0);
        if (!info.getTitle().equals("-")) {
            Select selectTitle = new Select(updateProfilePage.getTitleDropdown());
            assertEquals(info.getTitle(), selectTitle.getFirstSelectedOption().getAttribute("value"), "Wrong title displayed");
        }
        if (!info.getFirstName().equals("-")) {
            assertEquals(info.getFirstName(), updateProfilePage.getFirstNameTxtBox().getAttribute("value"), "First name mismatch");
        }
        if (!info.getLastName().equals("-")) {
            assertEquals(info.getLastName(), updateProfilePage.getLastNameTxtBox().getAttribute("value"), "last name mismatch");
        }
        if (!info.getHomePhone().equals("-")) {
            assertEquals(info.getHomePhone(), updateProfilePage.getHomePhoneTxtBox().getAttribute("value"), "home phone mismatch");
        }
        if (!info.getMobilePhone().equals("-")) {
            assertEquals(info.getMobilePhone(), updateProfilePage.getMobilePhoneTxtBox().getAttribute("value"), "mobile phone mismatch");
        }
        if (!info.getWorkPhone().equals("-")) {
            assertEquals(info.getWorkPhone(), updateProfilePage.getWorkPhoneTxtBox().getAttribute("value"), "work phone mismatch");
        }
        if (!info.getAddress().equals("-")) {
            assertEquals(info.getAddress(), updateProfilePage.getAddressTxtBox().getAttribute("value"), "Address mismatch");
        }
        if (!info.getLocality().equals("-")) {
            assertEquals(info.getLocality(), updateProfilePage.getLocalityTxtBox().getAttribute("value"), "Locality mismatch");
        }
        if (!info.getRegion().equals("-")) {
            assertEquals(info.getRegion(), updateProfilePage.getRegionTxtBox().getAttribute("value"), "Region mismatch");
        }
        if (!info.getPostalCode().equals("-")) {
            assertEquals(info.getPostalCode(), updateProfilePage.getPostalCodeTxtBox().getAttribute("value"), "Postal code mismatch");
        }
        if (!info.getCountry().equals("-")) {
            assertEquals(info.getCountry(), updateProfilePage.getCountryTxtBox().getAttribute("value"), "Country mismatch");
        }
        //for the task:
        System.out.println(info.getTitle() + "\n"
                + info.getFirstName() + "\n"
                + info.getLastName() + "\n"
                + info.getHomePhone() + "\n"
                + info.getMobilePhone() + "\n"
                + info.getWorkPhone()+"\n"
                + info.getAddress() + "\n"
                + info.getLocality() + "\n"
                + info.getRegion() + "\n"
                + info.getPostalCode() + "\n"
                + info.getCountry());
    }

}
