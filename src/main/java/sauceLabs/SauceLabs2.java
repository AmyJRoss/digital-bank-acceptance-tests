package sauceLabs;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabs2 {
    public static void main(String[] args) throws MalformedURLException {
        //how to use Sauce Labs?
        //1. get userName and accessKey
        String USERNAME = "oauth-amyorozobay-c0c6d";
        String ACCESSKEY = "51c30dda-65eb-4df4-9790-8f8443d538df";

        //setup url to the hub which is running on saucelabs VMs
        String url = "https://oauth-amyorozobay-c0c6d:51c30dda-65eb-4df4-9790-8f8443d538df@ondemand.eu-central-1.saucelabs.com:443/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Linux");
        capabilities.setCapability("browserName", "firefox");
        capabilities.setCapability("browserVersion", "latest");
        WebDriver driver = new RemoteWebDriver(new URL(url), capabilities);


        driver.get("https://dbank-qa.wedevx.co/bank/login");
        WebElement usernameTxtBox = driver.findElement(By.id("username"));
        WebElement passwordTxtBox = driver.findElement(By.id("password"));
        WebElement submitBtn = driver.findElement(By.id("submit"));
        usernameTxtBox.sendKeys("angie5jolie@gmail.com");
        passwordTxtBox.sendKeys("helloKitty55");
        submitBtn.click();
        WebElement dashboard = driver.findElement(By.id("page-title"));
        System.out.println(dashboard.getText());
        driver.quit();
    }
}
