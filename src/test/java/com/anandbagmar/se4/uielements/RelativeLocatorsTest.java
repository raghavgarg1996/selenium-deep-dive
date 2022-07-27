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
        driver.get("https://automationbookstore.dev/");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void relativeLocatorToLeftTest() {
        By idLocator = RelativeLocator.with(By.tagName("li"))
                .toLeftOf(By.id("pid6"));

        String id = driver.findElement(idLocator)
                          .getText();
        System.out.println("Found book: \n" + id);

        assertThat(id).as("Incorrect book is found")
                .contains("Java For Testers");

        Sleep.forSeconds(2);
    }

    @Test
    public void relativeLocatorBelowTest(){
        By idLocator = RelativeLocator.with(By.tagName("li"))
                .below(By.id("pid1"));

        String id = driver.findElement(idLocator)
                .getText();
        System.out.println("Found book: \n" + id);

        assertThat(id).as("Incorrect book is found")
                .contains("Java For Testers");

        Sleep.forSeconds(2);
    }

    @Test
    public void relativeLocatorToRightTest(){
        By idLocator = RelativeLocator.with(By.tagName("li"))
                .toRightOf(By.id("pid7"));

        String id = driver.findElement(idLocator)
                .getText();
        System.out.println("Found book: \n" + id);

        assertThat(id).as("Incorrect book is found")
                .contains("BDD");

        Sleep.forSeconds(2);
    }

    @Test
    public void relativeLocatorAboveTest(){
        By idLocator = RelativeLocator.with(By.tagName("li"))
                .above(By.id("pid8"));

        String id = driver.findElement(idLocator)
                .getText();
        System.out.println("Found book: \n" + id);

        assertThat(id).as("Incorrect book is found")
                .contains("Google");

        Sleep.forSeconds(2);
    }
}