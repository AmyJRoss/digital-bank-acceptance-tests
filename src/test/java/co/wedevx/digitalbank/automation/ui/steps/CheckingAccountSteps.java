package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountFields;
import co.wedevx.digitalbank.automation.ui.models.TransactionDetails;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import javax.swing.text.View;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckingAccountSteps {
    WebDriver driver = Driver.getDriver();
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingPage = new ViewCheckingAccountPage(driver);
    private NewCheckingAccountFields fields;


    @When("the user clicks {string} while creating a new checking account with the following data")
    public void the_user_clicks_while_creating_a_new_checking_account_with_the_following_data
            (String submitOrReset, List<NewCheckingAccountFields> newCheckingAccountFields) {
        createCheckingPage.createNewChecking(newCheckingAccountFields, submitOrReset);
        this.fields = newCheckingAccountFields.get(0);
    }

    @Then("the user should see the green {string} message")
    public void the_user_should_see_green_message(String expectedConfirmationMessage) {
        expectedConfirmationMessage += " new " + fields.getAccountType() + " account named " + fields.getAccountName();
        assertEquals(expectedConfirmationMessage, viewCheckingPage.getActualCreateAccountConfirmationMessage(), "Confirmation message mismatch");
    }

    @Then("the user should see newly added account card with following details")
    public void the_user_should_see_newly_added_account_card_with_following_details
            (List<AccountCard> accountCardList) {
        Map<String, String> actualResultMap = viewCheckingPage.getNewlyAddedCheckingAccountInfoMap();
        AccountCard expectedResult = accountCardList.get(0);
        assertEquals(expectedResult.getAccountName(), actualResultMap.get("accountName"), "Account name does not match");
        assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("accountType"), "Account type does not match");
        assertEquals("Ownership: " + expectedResult.getAccountOwnership(), actualResultMap.get("accountOwnership"), "Account ownership does not match");
        assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("interestRate"), "Interest Rate does not match");
        String expectedBalance = String.format("%.2f", expectedResult.getBalance());
        assertEquals("Balance: $" + expectedBalance, actualResultMap.get("balance"), "Balance does not match");
    }

    @Then("the user should see the following transactions details")
    public void the_user_should_see_the_following_transactions_details
            (List<TransactionDetails> transactionDetailsList) {
        Map<String, String> actualResultMap = viewCheckingPage.getNewlyAddedCheckingAccountTransactionInfoMap();
        TransactionDetails transaction = transactionDetailsList.get(0);
        assertEquals(transaction.getCategory(), actualResultMap.get("category"), "Category mismatch");
        assertEquals(transaction.getDescription(), actualResultMap.get("description"), "Description mismatch");
        assertEquals(transaction.getAmount(), Double.parseDouble(actualResultMap.get("amount")), "Amount mismatch");
        assertEquals(transaction.getBalance(), Double.parseDouble(actualResultMap.get("balance")), "Balance mismatch");
    }

    @Then("an error message should be displayed including {double} {string}")
    public void anErrorMessageShouldBeDisplayedIncludingInitialDepositLessThanDollars(double depositLess25, String
            errorMessageExpected) {
        String expectedM = createCheckingPage.getErrorMessageDeposit();
        assertTrue(expectedM.contains(errorMessageExpected), "Error message mismatch");
        assertTrue(expectedM.contains(Double.toString(depositLess25)), "Error message does not contain the amount");
    }

    @Then("the user should see an {string} on {string} field")
    public void the_user_should_see_an_error_message_on_string_field(String errorMessage, String field) {
        String actualErrorM = createCheckingPage.getActualErrorMessageOfField(field);
        assertEquals(errorMessage, actualErrorM, "Error message mismatch on field " + field);
    }

    @Then("the user should see all fields reset to default")
    public void theUserShouldSeeAllFieldsResetToDefault() {
        createCheckingPage.checkAllFieldsAreDisabled();
    }
}

