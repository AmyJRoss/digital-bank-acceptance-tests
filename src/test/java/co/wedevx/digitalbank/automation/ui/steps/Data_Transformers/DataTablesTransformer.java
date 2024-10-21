package co.wedevx.digitalbank.automation.ui.steps.Data_Transformers;

import co.wedevx.digitalbank.automation.ui.models.*;
import io.cucumber.java.DataTableType;

import java.util.Map;

public class DataTablesTransformer {
    @DataTableType
    public Passwords passwordsEntry(Map<String, String> entry) {
        String oldPass = entry.get("oldPass");
        String newPass = entry.get("newPass");
        String confirmPass = entry.get("confirmPass");
        return new Passwords(oldPass, newPass, confirmPass);
    }

    @DataTableType
    public UpdateProfileDetails updateProfileDetails(Map<String, String> entry) {
        String title = entry.get("title");
        String firstName = entry.get("firstName");
        String lastName = entry.get("lastName");
        String homePhone = entry.get("homePhone");
        String mobilePhone = entry.get("mobilePhone");
        String workPhone = entry.get("workPhone");
        String address = entry.get("address");
        String locality = entry.get("locality");
        String region = entry.get("region");
        String postalCode = entry.get("postalCode");
        String country = entry.get("country");
        return new UpdateProfileDetails(title, firstName, lastName, homePhone, mobilePhone, workPhone, address, locality, region, postalCode, country);
    }

    @DataTableType
    public SignUpDetails signUpDetails(Map<String, String> entry) {
        String title = entry.get("title");
        String firstName = entry.get("firstName");
        String lastName = entry.get("lastName");
        String gender = entry.get("gender");
        String dateOfBirth = entry.get("dateOfBirth");
        String ssn = entry.get("ssn");
        String email = entry.get("email");
        String password = entry.get("password");
        String confirmPassword = entry.get("confirmPassword");
        String address = entry.get("address");
        String locality = entry.get("locality");
        String region = entry.get("region");
        String postalCode = entry.get("postalCode");
        String country = entry.get("country");
        String homePhone = entry.get("homePhone");
        String mobilePhone = entry.get("mobilePhone");
        String workPhone = entry.get("workPhone");
        boolean agreeToTerms = Boolean.parseBoolean(entry.get("agreeToTerms"));
        return new SignUpDetails(title, firstName, lastName, gender, dateOfBirth, ssn, email, password, confirmPassword, address, locality, region, postalCode, country, homePhone, mobilePhone, workPhone, agreeToTerms);
    }

    @DataTableType
    public TransactionDetails transactionDetails(Map<String, String> entry) {
        String date = entry.get("date");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble(entry.get("balance"));
        return new TransactionDetails(date, category, description, amount, balance);
    }

    @DataTableType
    public NewCheckingAccountFields newCheckingAccountFieldsEntry(Map<String, String> entry) {
        String accountType = entry.get("accountType");
        String accountOwnership = entry.get("accountOwnership");
        String accountName = entry.get("accountName");
        String initialDeposit = entry.get("initialDeposit");
        return new NewCheckingAccountFields(accountType, accountOwnership, accountName, initialDeposit);
    }

    @DataTableType
    public AccountCard accountCardEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String accountOwnership = entry.get("accountOwnership");
        long accountNumber = Long.parseLong(entry.get("accountNumber"));
        String interestRate = entry.get("interestRate");
        double balance = Double.parseDouble(entry.get("balance"));
        return new AccountCard(accountName, accountType, accountOwnership, accountNumber, interestRate, balance);
    }
}
