package co.wedevx.digitalbank.automation.ui.utils;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class Driver {
    private static WebDriver driver;

    // Private constructor to prevent instantiation
    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            // Initialize WebDriver if it hasn't been initialized yet
            String browser = ConfigReader.getProperty("browser");
            switch (browser.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "headless":
                    ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
                    ChromeOptions options = getChromeOptions();
                    driver = new ChromeDriver(options);
                    break;
                case "saucelabs":
                    String platform = ConfigReader.getProperty("dbank.saucelabs.platform");
                    String browserType = ConfigReader.getProperty("dbank.saucelabs.browser");
                    String browserVersion = ConfigReader.getProperty("dbank.saucelabs.browser.version");
                    driver = loadSauceLabs(platform,browserType, browserVersion);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(360));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.manage().window().maximize();

        return driver;
    }

    private static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1920,1080");
        options.addArguments("disable-extensions");
        options.setExperimentalOption("useAutomationExtension", false);
        options.addArguments("--proxy-server='direct://'");
        options.addArguments("--proxy-bypass-list=*");
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        return options;
    }

    public static void takeScreenshot(Scenario scenario) {
       // if (scenario.isFailed()) {
            //taking screenshot
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            //adding screenshot to reports
            scenario.attach(screenshot, "image/png", "screenshot");
        //}
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    private static WebDriver loadSauceLabs(String platform, String browser, String browserVersion) {
        String username = ConfigReader.getProperty("dbank.saucelabs.username");
        String accessKey = ConfigReader.getProperty("dbank.saucelabs.accesskey");
        String host = ConfigReader.getProperty("dbank.saucelabs.host");

        String url = "https://"+username+":"+accessKey+"@"+host;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", platform);
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", browserVersion);
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(url), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
}
