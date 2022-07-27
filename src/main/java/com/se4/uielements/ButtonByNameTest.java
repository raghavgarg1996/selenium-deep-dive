package com.se4.uielements;

import com.anandbagmar.driver.Driver;
import com.anandbagmar.driver.Sleep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ButtonByNameTest {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = new Driver().createChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm#");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void buttonByNameTest() {
        Sleep.forSeconds(1);
        WebElement cookieElement = driver.findElement(By.id("banner-accept"));
        if(null != cookieElement) {
            cookieElement.click();
            Sleep.forSeconds(1);
        }
        WebElement submitButton = driver.findElement(By.name("submit"));
        assertThat(submitButton).as("Submit button is not found")
                                .isNotNull();

        submitButton.click();
        Sleep.forSeconds(2);
    }
}
