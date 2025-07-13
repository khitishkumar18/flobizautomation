package com.automationframework.base;
import com.automationframework.pages.loginPage;
import com.automationframework.utilities.ReadConfig;
import com.automationframework.utilities.ScreenshotUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.IOException;
import java.time.Duration;

public class BaseClass {

    ReadConfig readConfig = new ReadConfig();
    String url = readConfig.getKey("baseUrl");
    String browser = readConfig.getKey("browser");
    public String defaultOtp = readConfig.getKey("default_otp");
    public ScreenshotUtil su = new ScreenshotUtil();
    public loginPage lp;

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static Logger logger;

    public BaseClass() throws IOException {
    }

    @BeforeMethod
    public void setup() throws InterruptedException {
        logger = LogManager.getLogger("TestAutomationFramework");

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;

            case "msedge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;

            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();

        logger.info("Browser launched");
        getDriver().get(url);
        logger.info("URL opened before test method: " + url);
        lp = new loginPage(getDriver());
        lp.openLoginOrRegisterPopup();
        lp.switchToLoginFrame();
    }


    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            logger.info("Browser closed");
            driver.remove();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
