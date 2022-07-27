package com.anandbagmar.framework.learn.tests;

import com.anandbagmar.driver.Driver;
import com.anandbagmar.framework.learn.context.SessionContext;
import com.anandbagmar.framework.learn.context.TestExecutionContext;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.anandbagmar.framework.learn.context.SessionContext.addContext;
import static com.anandbagmar.framework.learn.context.SessionContext.getContext;
import static com.anandbagmar.framework.learn.utilities.ScreenShots.takeScreenshot;

public abstract class BaseTest {

    @BeforeSuite
    protected void beforeSuite() throws
                                 IOException {
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
        String browser = (null == envBrowser) ? "chrome" : envBrowser.trim()
                                                                     .toLowerCase();
        System.out.println("Running test with browser - " + browser);
        WebDriver driver = new Driver().createDriver(browser);
        addContext(Thread.currentThread()
                         .getId(), new TestExecutionContext(method.getName(), driver));
        System.out.println("--------------------------------------------------------------------");
    }

    @AfterMethod
    public void tearDown() {
        long threadId = Thread.currentThread()
                              .getId();
        TestExecutionContext testExecutionContext = getContext(threadId);
        WebDriver driver = testExecutionContext.getInnerDriver();
        if(null != driver) {
            takeScreenshot("Before Closing Browser");
            try {
                driver.close();
                driver.quit();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            } finally {
                System.out.println("Driver tearDown complete");
            }
        }
        SessionContext.removeContext(threadId);
    }
}
