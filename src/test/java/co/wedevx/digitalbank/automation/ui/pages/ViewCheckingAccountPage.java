package co.wedevx.digitalbank.automation.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewCheckingAccountPage extends BaseMenuPage {

    public ViewCheckingAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "new-account-msg")
    private WebElement confirmAlert;
    @FindBy(xpath = "//div[@id='firstRow']/div")
    private List<WebElement> allFirstRowDivs;
    @FindBy(xpath = "//table[@id='transactionTable']/tbody/tr")
    WebElement firstRowOfTransactions;
    @FindBy(id="firstRow")
    private WebElement firstRowCards;
    public Map<String, String> getNewlyAddedCheckingAccountInfoMap() {
        WebElement lastAccountCard = allFirstRowDivs.get(allFirstRowDivs.size() - 1);
        String actualResult = lastAccountCard.getText();
        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("accountName", actualResult.substring(0, actualResult.indexOf("\n")).trim());
        actualResultMap.put("accountType", actualResult.substring(actualResult.indexOf("Account:"), actualResult.indexOf("Ownership:")).trim());
        actualResultMap.put("accountOwnership", actualResult.substring(actualResult.indexOf("Ownership:"), actualResult.indexOf("Account Number:")).trim());
        actualResultMap.put("accountNumber", actualResult.substring(actualResult.indexOf("Account Number:"), actualResult.indexOf("Interest Rate:")).trim());
        actualResultMap.put("interestRate", actualResult.substring(actualResult.indexOf("Interest Rate:"), actualResult.indexOf("Balance")).trim());
        actualResultMap.put("balance", actualResult.substring(actualResult.indexOf("Balance:")).trim());
        return actualResultMap;
    }

    public Map<String, String> getNewlyAddedCheckingAccountTransactionInfoMap() {
        Actions actions = new Actions(driver);
        actions.moveToElement(firstRowOfTransactions).perform();
        List<WebElement> firstRowColumns = firstRowOfTransactions.findElements(By.xpath("td"));
        Map<String, String> actualResultMap = new HashMap<>();
        actualResultMap.put("category", firstRowColumns.get(1).getText());
        actualResultMap.put("description", firstRowColumns.get(2).getText());
        actualResultMap.put("amount", firstRowColumns.get(3).getText().substring(1));
        actualResultMap.put("balance", firstRowColumns.get(4).getText().substring(1));
        return actualResultMap;
    }

    public String getActualCreateAccountConfirmationMessage() {
        return confirmAlert.getText();
    }

}
