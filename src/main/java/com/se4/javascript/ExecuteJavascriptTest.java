package com.se4.javascript;

import com.anandbagmar.driver.Driver;
import com.anandbagmar.driver.Sleep;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExecuteJavascriptTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new Driver().createChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void javascriptTest() {
        driver.get("https://www.amazon.in/");
        Sleep.forSeconds(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0, 2500)");
        Sleep.forSeconds(2);
    }
}
