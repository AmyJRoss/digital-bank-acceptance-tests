package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.Passwords;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.UpdatePasswordPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePasswordSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private UpdatePasswordPage updatePasswordPage = new UpdatePasswordPage(driver);
    Passwords passwords;
    private String username;

    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password) {
        loginPage.login(username, password);
        this.username = username;
    }

    @Given("the user navigated to the {string} page")
    public void the_user_navigated_to_the_page(String changePass) {
        updatePasswordPage.goToUpdatePasswordPage();
        WebElement updatePasswordHead = driver.findElement(By.xpath("//div[@class='card-header bg-flat-color-1']"));
        assertTrue(updatePasswordHead.getText().contains(changePass));
    }

    @When("the user updates the password with")
    public void the_user_updates_the_password_with(List<Passwords> passwords) {
        Passwords pass = passwords.get(0);
        updatePasswordPage.updatePassword(pass);
        //for other steps
        this.passwords = passwords.get(0);
    }

    @Then("success message should be displayed")
    public void success_message_should_be_displayed() {
        assertTrue(updatePasswordPage.getSuccessMessage().contains("Password Updated Successfully"));
    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        String expectedResult;
        String actualMessage;
        if (passwords.getOldPass().equals("-")) {
            //empty current pass
            expectedResult = "Please fill out this field.";
            actualMessage = updatePasswordPage.getCurrentPassError(driver);
        } else if (passwords.getNewPass().equals(passwords.getConfirmPass())) {
            //incorrect format
            expectedResult = "Please match the requested format.\nPassword must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters";
            actualMessage = updatePasswordPage.getNewPassError(driver);
        } else {
            //do not match
            expectedResult = "Passwords Do Not Match";
            actualMessage = updatePasswordPage.getConfirmPassError(driver);
        }
        assertEquals(expectedResult, actualMessage, "Error message mismatch");
    }

    @Then("the user is able to sign in with {string} credentials")
    public void the_user_is_able_to_sign_in_with_old_credentials(String oldOrNew) {
        WebElement profileIcon = driver.findElement(By.xpath("//img[@class='user-avatar rounded-circle']"));
        profileIcon.click();
        WebElement logoutBtn = driver.findElement(By.xpath("//a[text()='Logout']"));
        logoutBtn.click();
        if (oldOrNew.equalsIgnoreCase("old")) {
            loginPage.loginAndRemember(username, passwords.getOldPass());
        } else if (oldOrNew.equalsIgnoreCase("new")) {
            loginPage.loginAndRemember(username, passwords.getNewPass());
        }
        //validate url
        String expectedUrl = "https://dbank-qa.wedevx.co/bank/home";
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl, "User is not on homepage dashboard");
    }

}
