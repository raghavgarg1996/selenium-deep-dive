package com.se4.uielements;

import com.anandbagmar.driver.Driver;
import com.anandbagmar.driver.Sleep;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelativeLocatorsTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new Driver().createChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void byNameTest() {
        driver.get("https://automationbookstore.dev/");
        By idLocator = RelativeLocator.with(By.tagName("li"))
                                      .toLeftOf(By.id("pid6"));

        String id = driver.findElement(idLocator)
                          .getText();
        System.out.println("Found book: \n" + id);
        assertThat(id).as("Incorrect book is found")
                .contains("Java For Testers");
        Sleep.forSeconds(2);
    }
}