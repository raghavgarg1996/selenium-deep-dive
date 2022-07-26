package com.se4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class BreakingChanges {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void breakingChangesTest(){
        // selenium 3 usage
        // uncomment to see the breaking change

//        FluentWait waitSe3 = new FluentWait(driver)
//                .pollingEvery(20, TimeUnit.MILLISECONDS)
//                .withTimeout(20, TimeUnit.SECONDS)
//                .ignoring(NoSuchElementException.class);

//selenium 4 usage
        FluentWait waitSe4 = new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .withTimeout(Duration.ofSeconds(60));

    }
}
