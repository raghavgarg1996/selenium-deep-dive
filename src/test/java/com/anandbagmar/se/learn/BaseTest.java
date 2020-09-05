package com.anandbagmar.se.learn;

import com.anandbagmar.se.learn.pages.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

import static com.anandbagmar.se.learn.SessionContext.addContext;
import static com.anandbagmar.se.learn.SessionContext.getContext;

public abstract class BaseTest {

    @BeforeSuite
    protected void beforeSuite() {
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Before Suite");
        SessionContext.instantiate();
        System.out.println("--------------------------------------------------------------------");
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        System.out.println("--------------------------------------------------------------------");
        String methodName = method.getName();
        System.out.println("Before Method: " + methodName);
        System.out.println("CreateDriver for test: " + methodName);
        String envBrowser = System.getenv("browser");
        String browser = (null == envBrowser) ? "chrome" : envBrowser;
        System.out.println("Running test with browser - " + browser);
        WebDriver driver;
        switch (browser) {
            case "chrome":
                setPathPropertyForChromeDriverFromMachine();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//                options.addArguments("headless");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                setPathPropertyForFirefoxDriverFromMachine();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                driver = new ChromeDriver();
        }
        addContext(Thread.currentThread().getId(), new TestExecutionContext(method.getName(), driver));

        System.out.println("--------------------------------------------------------------------");
    }

    @AfterMethod
    public void tearDown() {
        long threadId = Thread.currentThread().getId();
        TestExecutionContext testExecutionContext = getContext(threadId);
        WebDriver driver = testExecutionContext.getInnerDriver();
        if (null != driver) {
            try {
                driver.close();
                driver.quit();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Driver tearDown complete");
            }
        }
        SessionContext.removeContext(threadId);
    }

    private void setPathPropertyForChromeDriverFromMachine() {
        WebDriverManager.chromedriver().setup();
        String chromeDriverPath = WebDriverManager.chromedriver().getBinaryPath();
        System.out.println("ChromeDriver path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    private void setPathPropertyForFirefoxDriverFromMachine() {
        WebDriverManager.firefoxdriver().setup();
        String firefoxDriverPath = WebDriverManager.firefoxdriver().getBinaryPath();
        System.out.println("FirefoxDriver path: " + firefoxDriverPath);
        System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
    }
}
