package com.anandbagmar.se.learn;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;

public class MyScheduleEx2Test {
    WebDriver driver = null;
    private static final String url = "https://dev.confengine.com";
    private static final By upcomingLocator = By.xpath("//a[text()=\"Upcoming\"]");
    private static final By conferenceNameLocator = By.cssSelector("img[title='Selenium Conf 2020']");
    private static final By viewScheduleLocator = By.xpath("//div/a[@href='/selenium-conf-2020/schedule']");
    private static final By myScheduleCountLocator = By.id("my-schedule-count");
    private static final By addSessionToMyScheduleLocator = By.cssSelector("a[data-tooltip='Add to My Schedule']");
    private static final String screenshotsDir = System.getenv("screenshotsDir");

//    @BeforeMethod
    private WebDriver createDriver(Method method) {
        String methodName = method.getName();
        System.out.println("CreateDriver for test: " + methodName);
        String envBrowser = System.getenv("browser");
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

//    @AfterMethod
    public void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }

//    @Test
    public void addSessionToMySchedule() {
        driver.get(url);

        driver.findElement(upcomingLocator).click();
        driver.findElement(conferenceNameLocator).click();

        takeScreenshot("conference");

        driver.findElement(viewScheduleLocator).click();

        takeScreenshot("view schedule");

        int initialCount = Integer.parseInt(driver.findElement(myScheduleCountLocator).getText());
        System.out.println("Initial count = " + initialCount);

        driver.findElement(addSessionToMyScheduleLocator).click();
        takeScreenshot("add session to My Schedule");

        driver.findElement(By.id("cancel_login_model")).click();
        takeScreenshot("cancel login");

        int finalCount = Integer.parseInt(driver.findElement(myScheduleCountLocator).getText());
        System.out.println("Final count = " + finalCount);

        Assert.assertEquals(finalCount, initialCount+1);
    }

    private void takeScreenshot(String screenshotName) {
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotFileName = screenshotsDir + "/" + screenshotName + ".png";
        System.out.println("Saving screenshot: " + destinationScreenshotFileName);
        try {
            FileUtils.copyFile(screenshotAs, new File(destinationScreenshotFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String getPathForChromeDriverFromMachine() {
        WebDriverManager.chromedriver().setup();
        String chromeDriverPath = WebDriverManager.chromedriver().getDownloadedDriverPath();
        System.out.println("ChromeDriver path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        return chromeDriverPath;
    }

    private String getPathForFirefoxDriverFromMachine() {
        WebDriverManager.firefoxdriver().setup();
        String firefoxDriverPath = WebDriverManager.firefoxdriver().getDownloadedDriverPath();
        System.out.println("FirefoxDriver path: " + firefoxDriverPath);
        System.setProperty("webdriver.firefox.driver", firefoxDriverPath);
        return firefoxDriverPath;
    }

    private void explicitlyWaitForElementToBeClickable(WebDriver driver, By locator, int waitForSeconds) {
        long duration = Duration.ofSeconds(waitForSeconds).getSeconds();
        new WebDriverWait(driver, duration).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
