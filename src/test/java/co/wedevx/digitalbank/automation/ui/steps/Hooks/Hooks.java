package co.wedevx.digitalbank.automation.ui.steps.Hooks;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Hooks {
    public Hooks() {
    }

    WebDriver driver = Driver.getDriver();

    @Before("not @Registration")
    public void the_user_is_on_dbank_homepage() {
        driver.get("https://dbank-qa.wedevx.co/bank/login");
    }

    @BeforeAll()
    public static void establishConnectionToDB() {
        DBUtils.establishConnection();
    }

    @After()
    public void tearDown(Scenario scenario) {
        Driver.takeScreenshot(scenario);
        Driver.closeDriver();
    }

    @AfterAll()
    public static void closeConnectionToDB() {
        DBUtils.closeConnection();
    }
}
