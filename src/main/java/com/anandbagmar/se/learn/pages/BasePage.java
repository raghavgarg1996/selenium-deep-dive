package com.anandbagmar.se.learn.pages;

import com.anandbagmar.se.learn.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.anandbagmar.se.learn.SessionContext.getContext;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class BasePage {
    protected static final String baseUrl = SessionContext.getBaseUrl();
    protected final WebDriver driver;

    BasePage() {
        driver = getDriver();
    }

    protected void waitFor(int numSeconds) {
        try {
            System.out.printf("Hard sleep / pause of '%d' seconds%n", numSeconds);
            Thread.sleep(numSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected synchronized WebDriver getDriver() {
        TestExecutionContext testExecutionContext = getTestExecutionContext();
        return testExecutionContext.getInnerDriver();
    }

    protected TestExecutionContext getTestExecutionContext() {
        TestExecutionContext testExecutionContext = getContext(Thread.currentThread().getId());
        return testExecutionContext;
    }

    protected void explicitlyWaitFor(ExpectedCondition isTrue) {
        explicitlyWaitFor(isTrue, 2);
    }

    protected void explicitlyWaitFor(ExpectedCondition isTrue, int waitForSeconds) {
        new WebDriverWait(driver, Duration.ofSeconds(waitForSeconds)).until(isTrue);
    }

    protected void scrollElementIntoView(WebElement webElement) {
        //This will scroll the page Horizontally till the element is found
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(false);", webElement);
        explicitlyWaitFor(elementToBeClickable(webElement), 3);
    }
}
