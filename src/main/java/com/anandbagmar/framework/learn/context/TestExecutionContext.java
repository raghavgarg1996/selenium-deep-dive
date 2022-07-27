package com.anandbagmar.framework.learn.context;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class TestExecutionContext {
    private final WebDriver innerDriver;
    private final String testName;
    private final Map<String, String> testState;

    public TestExecutionContext(String testName, WebDriver innerDriver) {
        this.testName = testName;
        this.innerDriver = innerDriver;
        this.testState = new HashMap<>();
    }

    public WebDriver getInnerDriver() {
        return innerDriver;
    }

    public String getTestName() {
        return testName;
    }

    public int getScreenshotCounter() {
        return SessionContext.getScreenshotCounter();
    }

    public void addState(String name, String value) {
        testState.put(name, value);
    }

    public String getState(String name) {
        return testState.get(name);
    }
}
