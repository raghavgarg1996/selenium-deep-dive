package com.anandbagmar.framework.learn.utilities;

import com.anandbagmar.framework.learn.context.TestExecutionContext;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static com.anandbagmar.framework.learn.context.SessionContext.getContext;

public class ScreenShots {
    private static final String screenshotsDir = (null == System.getenv("screenshotsDir")) ? "reports/screenshots" : System.getenv("screenshotsDir");

    public static void takeScreenshot(String screenshotName) {
        TestExecutionContext testExecutionContext = getContext(Thread.currentThread()
                                                                     .getId());
        int screenshotCounter = testExecutionContext.getScreenshotCounter();
        WebDriver driver = testExecutionContext.getInnerDriver();
        File screenshotAs = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        screenshotName = screenshotName.replaceAll(" ", "")
                                       .replaceAll("'", "");
        String destinationScreenshotFileName = screenshotsDir + "/" + screenshotCounter + "-" + testExecutionContext.getTestName() + "-" + screenshotName + ".png";
        System.out.println("Saving screenshot: " + destinationScreenshotFileName);
        try {
            FileUtils.copyFile(screenshotAs, new File(destinationScreenshotFileName));
        } catch(IOException e) {
            throw new RuntimeException("Error in saving screenshot to " + destinationScreenshotFileName, e);
        }
    }
}
