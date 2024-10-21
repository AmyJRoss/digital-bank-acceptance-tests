package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.SignUpDetails;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.SignUpPage;
import co.wedevx.digitalbank.automation.ui.utils.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpSteps {
    WebDriver driver = Driver.getDriver();
    private SignUpPage signUpPage = new SignUpPage(driver);
    private long nextValueID;

    @Given("the user with email {string} is not in DB")
    public void the_user_with_email_is_not_in_DB(String email) {
        String queryForUserProfile = String.format("delete from user_profile where email_address='%s'", email);
        String queryForUsers = String.format("delete from users where username='%s'", email);
        DBUtils.runSQLUpdateQuery(queryForUserProfile);
        DBUtils.runSQLUpdateQuery(queryForUsers);

        String queryToGetNextValInHabernateSeqTable = "select * from hibernate_sequence";
        List<Map<String, Object>> nextValList = DBUtils.runSQLSelectQuery(queryToGetNextValInHabernateSeqTable);
        nextValueID = Long.parseLong(nextValList.get(0).get("next_val").toString());
    }

    @Given("the user navigates to Digital Bank signup page")
    public void the_user_navigates_to_Digital_Bank_signup_page() {
        driver.get(ConfigReader.getProperty("digitalbank.registrationpageurl"));
        assertEquals("Digital Bank", driver.getTitle(), "Registration page title mismatch");
    }

    @When("the user creates account with following fields")
    public void the_user_creates_account_with_following_fields(List<SignUpDetails> signUpFields) {
        signUpPage.fillSignUpFieldsFromList(signUpFields);
    }

    @Then("the user should be displayed with the message {string}")
    public void the_user_should_be_displayed_with_the_message(String expectedMessage) {
        assertEquals(expectedMessage, signUpPage.getMessage(), "Success message mismatch");
    }

    @Then("the user should see the {string} required field error message {string}")
    public void the_user_should_see_the_required_field_error_message(String fieldWithError, String errorMessage) {
        String actualErrorMessage = signUpPage.getRequiredFieldErrorMessage(fieldWithError);
        assertEquals(errorMessage, actualErrorMessage, "the error message of required " + fieldWithError + " field mismatch");
    }

    @Then("the following user info should be saved in the db")
    public void the_following_user_info_should_be_saved_in_the_db(List<Map<String, String>> expectedUserProfileInfoInDBList) {
        Map<String, String> expectedUserInfoMap = expectedUserProfileInfoInDBList.get(0);
        String queryForUsers = String.format("select * from users where username='%s'", expectedUserInfoMap.get("email"));
        String queryForUserProfile = String.format("select * from user_profile where email_address='%s'", expectedUserInfoMap.get("email"));

        List<Map<String, Object>> actualUserInfoList = DBUtils.runSQLSelectQuery(queryForUsers);
        List<Map<String, Object>> actualUserProfileInfoList = DBUtils.runSQLSelectQuery(queryForUserProfile);

        assertEquals(1, actualUserInfoList.size(), "registration generated unexpected number of users");
        assertEquals(1, actualUserProfileInfoList.size(), "registration generated unexpected number of user profiles");

        Map<String, Object> actualUserInfoMap = actualUserInfoList.get(0);
        Map<String, Object> actualUserProfileInfoMap = actualUserProfileInfoList.get(0);

        //validate user profile table
        assertEquals(expectedUserInfoMap.get("title"), actualUserProfileInfoMap.get("title"), "registration generated wrong title");
        assertEquals(expectedUserInfoMap.get("firstName"), actualUserProfileInfoMap.get("first_name"), "registration generated wrong first name");
        assertEquals(expectedUserInfoMap.get("lastName"), actualUserProfileInfoMap.get("last_name"), "registration generated wrong last name");
        assertEquals(expectedUserInfoMap.get("gender"), actualUserProfileInfoMap.get("gender"), "registration generated wrong gender");

        LocalDateTime actualDob = (LocalDateTime) actualUserProfileInfoMap.get("dob");
        LocalDate actualDobDate = actualDob.toLocalDate();
        String actualDobFormatted = actualDobDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        assertEquals(expectedUserInfoMap.get("dateOfBirth"), actualDobFormatted, "registration generated wrong date of birth");

        assertEquals(expectedUserInfoMap.get("ssn"), actualUserProfileInfoMap.get("ssn"), "registration generated wrong ssn");
        assertEquals(expectedUserInfoMap.get("email"), actualUserProfileInfoMap.get("email_address"), "registration generated wrong email");
        assertEquals(expectedUserInfoMap.get("address"), actualUserProfileInfoMap.get("address"), "registration generated wrong address");
        assertEquals(expectedUserInfoMap.get("locality"), actualUserProfileInfoMap.get("locality"), "registration generated wrong locality");
        assertEquals(expectedUserInfoMap.get("region"), actualUserProfileInfoMap.get("region"), "registration generated wrong region");
        assertEquals(expectedUserInfoMap.get("postalCode"), actualUserProfileInfoMap.get("postal_code"), "registration generated wrong postal code");
        assertEquals(expectedUserInfoMap.get("country"), actualUserProfileInfoMap.get("country"), "registration generated wrong country");
        assertEquals(expectedUserInfoMap.get("homePhone"), actualUserProfileInfoMap.get("home_phone"), "registration generated wrong home phone");

        String expectedMobilePhone = (String) expectedUserInfoMap.get("mobilePhone");
        if (expectedMobilePhone == null) {
            expectedMobilePhone = ""; // Treat null as empty
        }
        assertEquals(expectedMobilePhone, actualUserProfileInfoMap.get("mobile_phone"), "registration generated wrong mobile phone");

        String expectedWorkPhone = (String) expectedUserInfoMap.get("workPhone");
        if (expectedWorkPhone == null) {
            expectedWorkPhone = ""; // Treat null as empty
        }
        assertEquals(expectedWorkPhone, actualUserProfileInfoMap.get("work_phone"), "registration generated wrong work phone");
        assertEquals(nextValueID + 1, actualUserProfileInfoMap.get("id"), "ID mismatch in user profile table");


        //validate user table
        assertEquals(nextValueID, actualUserInfoMap.get("id"), "ID mismatch in users table");
        assertEquals(expectedUserInfoMap.get("accountNonExpired"), String.valueOf(actualUserInfoMap.get("account_non_expired")), "account_non_expired mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("accountNonLocked"), String.valueOf(actualUserInfoMap.get("account_non_locked")), "account_non_locked mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("credentialsNonExpired"), String.valueOf(actualUserInfoMap.get("credentials_non_expired")), "credentials_non_expired mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("enabled"), String.valueOf(actualUserInfoMap.get("enabled")), "enabled mismatch upon registration");
        assertEquals(expectedUserInfoMap.get("email"), actualUserInfoMap.get("username"), "generated wrong username upon registration");
    }
}
