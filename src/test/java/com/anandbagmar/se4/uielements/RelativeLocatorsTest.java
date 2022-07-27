package com.anandbagmar.se4.uielements;

import com.anandbagmar.se4.Driver;
import com.anandbagmar.se4.Sleep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RelativeLocatorsTest {
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
    public void beRelativeLocatorsTest() {
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