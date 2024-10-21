package co.wedevx.digitalbank.automation.ui.models;

public class NewCheckingAccountFields {
    private String accountType;
    private String accountOwnership;
    private String accountName;
    private String initialDeposit;

    public NewCheckingAccountFields(String accountType, String accountOwnership, String accountName, String initialDeposit) {
        this.accountType = accountType;
        this.accountOwnership = accountOwnership;
        this.accountName = accountName;
        this.initialDeposit = initialDeposit;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountOwnership() {
        return accountOwnership;
    }

    public void setAccountOwnership(String accountOwnership) {
        this.accountOwnership = accountOwnership;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getInitialDeposit() {
        return initialDeposit;
    }

}
