package com.anandbagmar.se.learn.pages;

import com.anandbagmar.se.learn.TestExecutionContext;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static com.anandbagmar.se.learn.SessionContext.getContext;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class BasePage {
    protected static final String baseUrl = "https://dev.confengine.com";
    private static String screenshotsDir = System.getenv("screenshotsDir");
    protected final WebDriver driver;

    BasePage() {
        driver = getDriver();
        screenshotsDir = (null == screenshotsDir) ? "reports/screenshots" : screenshotsDir;
    }

    protected void waitFor(int numSeconds) {
        try {
            System.out.printf("Hard sleep / pause of '%d' seconds%n", numSeconds);
            Thread.sleep(numSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void takeScreenshot(String screenshotName) {
        TestExecutionContext testExecutionContext = getContext(Thread.currentThread().getId());
        int screenshotCounter = testExecutionContext.getScreenshotCounter();
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotName = screenshotName.replaceAll(" ", "").replaceAll("'", "");
        String destinationScreenshotFileName = screenshotsDir + "/" + screenshotCounter + "-" + testExecutionContext.getTestName() + "-" + screenshotName + ".png";
        System.out.println("Saving screenshot: " + destinationScreenshotFileName);
        try {
            FileUtils.copyFile(screenshotAs, new File(destinationScreenshotFileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected synchronized WebDriver getDriver() {
        TestExecutionContext testExecutionContext = getContext(Thread.currentThread().getId());
        System.out.println("Returning Driver for TestName: " + testExecutionContext.getTestName());
        return testExecutionContext.getInnerDriver();
    }

    protected void explicitlyWaitFor(ExpectedCondition isTrue) {
        explicitlyWaitFor(isTrue, 2);
    }

    protected void explicitlyWaitFor(ExpectedCondition isTrue, int waitForSeconds) {
        long duration = Duration.ofSeconds(waitForSeconds).getSeconds();
        new WebDriverWait(driver, duration).until(isTrue);
    }

    protected void scrollElementIntoView(WebElement webElement) {
        //This will scroll the page Horizontally till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", webElement);
        explicitlyWaitFor(elementToBeClickable(webElement), 3);
    }
}
