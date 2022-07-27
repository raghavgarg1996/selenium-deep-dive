package com.se4.javascript;

import com.anandbagmar.driver.Driver;
import com.anandbagmar.driver.Sleep;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExecuteJavascriptTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new Driver().createDriver("chrome");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void javascriptTest() {
        driver.get("https://www.amazon.in/");
        Sleep.forSec(2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scrollBy(0, 2500)");
        Sleep.forSec(2);
    }
}
