package com.se4.module7;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ButtonsImagesLinks {

    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver()
                        .setup();
        driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm#");
    }

    @Test
    public void ButtonTest() {
        WebElement loginBtn = driver.findElement(By.name("photo"));
        assertThat(loginBtn).as("Dynamic color button is null")
                            .isNotNull();

        loginBtn.click();

    }

}
