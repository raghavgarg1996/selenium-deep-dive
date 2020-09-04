package com.anandbagmar.se.learn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.time.Duration;

public class MyScheduleTest {
    WebDriver driver = null;
    private static final String url = "https://dev.confengine.com";
    private static final By upcomingLocator = By.xpath("//a[text()=\"Upcoming\"]");
    private static final By conferenceNameLocator = By.cssSelector("img[title='Selenium Conf 2020']");
    private static final By viewScheduleLocator = By.xpath("//div/a[@href='/selenium-conf-2020/schedule']");
    private static final By myScheduleCountLocator = By.id("my-schedule-count");
    private static final By addSessionToMyScheduleLocator = By.cssSelector("a[data-tooltip='Add to My Schedule']");

    @BeforeMethod
    private WebDriver createDriver(Method method) {
        String methodName = method.getName();
        System.out.println("CreateDriver for test: " + methodName);
        String envBrowser = System.getenv("browser");
        System.out.println("Browser env: " + envBrowser);
        String browser = (null == envBrowser) ? "chrome" : envBrowser;
        System.out.println("Running test with browser - " + browser);
        switch (browser) {
            case "chrome":
                getPathForChromeDriverFromMachine();
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//                options.addArguments("headless");
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                getPathForFirefoxDriverFromMachine();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                driver = new ChromeDriver();
        }
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

    @Test
    public void addSessionToMySchedule() {
        driver.get(url);

        driver.findElement(upcomingLocator).click();
        driver.findElement(conferenceNameLocator).click();
        driver.findElement(viewScheduleLocator).click();

        String initialCount = driver.findElement(myScheduleCountLocator).getText();
        System.out.println("Initial count = " + initialCount);

        driver.findElement(addSessionToMyScheduleLocator).click();
        driver.findElement(By.id("cancel_login_model")).click();

        String finalCount = driver.findElement(myScheduleCountLocator).getText();
        System.out.println("Final count = " + finalCount);
    }

    private String getPathForChromeDriverFromMachine() {
        WebDriverManager.chromedriver().setup();
        String chromeDriverPath = WebDriverManager.chromedriver().getBinaryPath();
        System.out.println("ChromeDriver path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return chromeDriverPath;
    }

    private String getPathForFirefoxDriverFromMachine() {
        WebDriverManager.firefoxdriver().setup();
        String firefoxDriverPath = WebDriverManager.firefoxdriver().getBinaryPath();
        System.out.println("FirefoxDriver path: " + firefoxDriverPath);
        System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
        return firefoxDriverPath;
    }

    private void explicitlyWaitForElementToBeClickable(WebDriver driver, By locator, int waitForSeconds) {
        long duration = Duration.ofSeconds(waitForSeconds).getSeconds();
        new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
