package com.anandbagmar.se4.uielements;

import com.anandbagmar.se4.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptureScreenshotsTest {

    WebDriver driver;
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    private final String SCREENSHOT_DIR = PROJECT_PATH + "/build/screenshots/";

    @BeforeMethod
    public void setup() {
        driver = new Driver().createChromeDriver();
        driver.get("https://www.google.com");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void takeFullPageScreenShotTest() throws
                                             Exception {
        // take screenshot of the entire page
        //Convert web driver object to TakeScreenshot
        TakesScreenshot takesScreenshot = ((TakesScreenshot) driver);

        //Call getScreenshotAs method to create image file
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        String screenshotFileName = SCREENSHOT_DIR + "fullPage.png";
        File destFile = new File(screenshotFileName);

        //Copy file at destination
        FileUtils.copyFile(srcFile, destFile);

        assertThat(new File(screenshotFileName).exists()).as("Screenshot is not available here: " + screenshotFileName)
                                                         .isTrue();
    }

    @Test
    public void takeElementScreenShotTest() throws
                                            Exception {
        // take screenshot of an element
        WebElement logo = driver.findElement(By.xpath("//img[@alt='Google']"));

        //Call getScreenshotAs method to create image file
        File srcFile = logo.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        String screenshotFileName = SCREENSHOT_DIR + "logo.png";
        File destFile = new File(screenshotFileName);

        //Copy file at destination
        FileUtils.copyFile(srcFile, destFile);
        assertThat(new File(screenshotFileName).exists()).as("Screenshot is not available here: " + screenshotFileName)
                                                         .isTrue();
    }
}
