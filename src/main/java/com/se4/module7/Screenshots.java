package com.se4.module7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class Screenshots {

    WebDriver driver;
    final static String PROJECT_PATH = System.getProperty("user.dir");

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test
    public void loginTest() throws Exception {
        driver.get("https://www.google.com");

        // take screenshot of an element
        WebElement logo = driver.findElement(By.xpath("//img[@alt='Google']"));
        takeElementScreenshot(logo, PROJECT_PATH + "/target/screenshots/logo.png");

        // take screenshot of the entire page
        takeFullPageScreenshot(driver, PROJECT_PATH + "/target/screenshots/fullPage.png");
        driver.quit();
    }

    public static void takeFullPageScreenshot(WebDriver webdriver, String fileWithPath) throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile = new File(fileWithPath);

        //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }

    public static void takeElementScreenshot(WebElement element, String filePath) throws Exception {
        File file = element.getScreenshotAs(OutputType.FILE);
        File destFile = new File(filePath);
        FileUtils.copyFile(file, destFile);
    }

}
