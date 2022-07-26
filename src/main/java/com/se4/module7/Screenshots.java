package com.se4.module7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Screenshots {

    WebDriver driver;
    final static String PROJECT_PATH = System.getProperty("user.dir");

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }


    @Test
    public void loginTest() throws IOException {
        driver.get("https://www.google.com");

        // take screenshot of an element
        WebElement logo = driver.findElement(By.xpath("//img[@alt='Google']"));
        File file = logo.getScreenshotAs(OutputType.FILE);
        File destFile = new File(PROJECT_PATH + "/target/screenshots/logo.png");
        FileUtils.copyFile(file, destFile);

        driver.quit();
    }


}
