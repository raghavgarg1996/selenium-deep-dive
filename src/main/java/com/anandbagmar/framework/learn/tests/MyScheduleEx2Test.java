package com.anandbagmar.framework.learn.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class MyScheduleEx2Test {
    private static final String url = "https://dev.confengine.com";
    private static final By upcomingEventsXpath = By.xpath("//a[text()=\"Upcoming\"]");
    private static final By conferenceNameXpath = By.cssSelector("div[title='Selenium Conf 2022']");
    private static final By viewScheduleXpath = By.xpath("//div/a[@href='/conferences/selenium-conf-2022/schedule']");
    private static final By myScheduleCountId = By.id("my-schedule-count");
    private static final By addSessionToMyScheduleCSS = By.cssSelector("a[data-tooltip='Add to My Schedule']");
    private final static String PROJECT_PATH = System.getProperty("user.dir");

    private final String SCREENSHOT_DIR = PROJECT_PATH + "/build/screenshots/";
    private final By dismissCookieXpath = By.xpath("//a[text()='Got it!']");
    private final By attendEventsXpath = By.xpath("//li[@class='no-hover']//span[text()='Attend Events']");
    private final By cancelLoginCSS = By.id("cancel_login_model");
    WebDriver driver = null;

    @BeforeMethod
    private WebDriver createDriver(Method method) {
        String methodName = method.getName();
        System.out.println("CreateDriver for test: " + methodName);
        String envBrowser = System.getenv("browser");
        String browser = (null == envBrowser) ? "chrome" : envBrowser;
        System.out.println("Running test with browser - " + browser);
        switch(browser) {
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
        driver.manage()
              .timeouts()
              .implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    private String getPathForChromeDriverFromMachine() {
        WebDriverManager webDriverManager = WebDriverManager.getInstance(DriverManagerType.CHROME);
        webDriverManager.setup();
        System.out.println("WebDriverManager - browser version: " + webDriverManager.getDownloadedDriverVersion());
        String chromeDriverPath = webDriverManager.getDownloadedDriverPath();
        System.out.println("WebDriverManager - browser driver path: " + chromeDriverPath);
        return chromeDriverPath;
    }

    private String getPathForFirefoxDriverFromMachine() {
        WebDriverManager webDriverManager = WebDriverManager.getInstance(DriverManagerType.FIREFOX);
        webDriverManager.setup();
        System.out.println("WebDriverManager - browser version: " + webDriverManager.getDownloadedDriverVersion());
        String firefoxDriverPath = webDriverManager.getDownloadedDriverPath();
        System.out.println("WebDriverManager - browser driver path: " + firefoxDriverPath);
        return firefoxDriverPath;
    }

    @AfterMethod
    public void tearDown() {
        if(null != driver) {
            driver.quit();
        }
    }

    @Test
    public void addSessionToMySchedule() {
        driver.get(url);

        WebElement cookieElement = driver.findElement(dismissCookieXpath);
        if(null != cookieElement) {
            cookieElement.click();
        }

        driver.findElement(attendEventsXpath)
              .click();

        driver.findElement(upcomingEventsXpath)
              .click();

        driver.findElement(conferenceNameXpath)
              .click();

        takeScreenshot("conference");

        driver.findElement(viewScheduleXpath)
              .click();

        takeScreenshot("view schedule");

        int initialCount = Integer.parseInt(driver.findElement(myScheduleCountId)
                                                  .getText());
        System.out.println("Initial count = " + initialCount);

        driver.findElement(addSessionToMyScheduleCSS)
              .click();
        takeScreenshot("add session to My Schedule");

        driver.findElement(cancelLoginCSS)
              .click();
        takeScreenshot("cancel login");

        int finalCount = Integer.parseInt(driver.findElement(myScheduleCountId)
                                                .getText());
        System.out.println("Final count = " + finalCount);

        assertThat(finalCount).isEqualTo(initialCount + 1);
    }

    private void takeScreenshot(String screenshotName) {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotFileName = SCREENSHOT_DIR + "/" + screenshotName + ".png";
        System.out.println("Saving screenshot: " + destinationScreenshotFileName);
        try {
            FileUtils.copyFile(screenshotAs, new File(destinationScreenshotFileName));
        } catch(IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void explicitlyWaitForElementToBeClickable(WebDriver driver, By locator, int waitForSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds)).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
