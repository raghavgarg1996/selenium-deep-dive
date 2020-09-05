package com.anandbagmar.se.learn;

import org.openqa.selenium.WebDriver;

public class TestExecutionContext {
    private final WebDriver innerDriver;
    private final String testName;
    private int screenshotCounter;

    public TestExecutionContext(String testName, WebDriver innerDriver) {
        this.testName = testName;
        this.innerDriver = innerDriver;
        this.screenshotCounter = 0;
    }

    public WebDriver getInnerDriver() {
        return innerDriver;
    }

    public String getTestName() {
        return testName;
    }

    public int getScreenshotCounter() {
        return ++screenshotCounter;
    }
}
