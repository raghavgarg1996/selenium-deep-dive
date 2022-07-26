package com.se4.module6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorStrategiesTest {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm#");
    }


    @Test
    public void locators(){
        // name
        WebElement fnameTxtBox = driver.findElement(By.name("firstname"));
        assertThat(fnameTxtBox).as("first name text box is null").isNotNull();

        WebElement lnameTxtBox = driver.findElement(By.name("lastname"));
        assertThat(lnameTxtBox).as("last name text box is null").isNotNull();

        // xpath - https://devhints.io/xpath
        WebElement expRadioWithTwoYears = driver.findElement(By.xpath("//input[@name='exp' and @value='2']"));
        assertThat(expRadioWithTwoYears).as("Experience Radio Button for 2 Years is null").isNotNull();

        // css - https://www.w3schools.com/cssref/css_selectors.asp
        WebElement toolCheckBoxRC = driver.findElement(By.cssSelector("input[name='tool'][value='RC']"));
        assertThat(toolCheckBoxRC).as("Tools check box is null").isNotNull();

        // id
        WebElement dynamicColorBtn = driver.findElement(By.id("dynamicColor"));
        assertThat(dynamicColorBtn).as("Dynamic color button is null").isNotNull();

        // tagname
        List<WebElement> allLabels = driver.findElements(By.tagName("strong"));
        assertThat(allLabels).as("All labels is null").isNotEmpty();

        // link text
        List<WebElement> nextPageLink = driver.findElements(By.linkText("Next Page "));
        assertThat(nextPageLink).as("Next Page link is null").isNotNull();

        // Partial Link Text
        List<WebElement> printLink = driver.findElements(By.partialLinkText("Print"));
        assertThat(printLink).as("Print Link is null").isNotNull();

        List<WebElement> bottomNextPageLink = driver.findElements(By.className("nxt-btn"));
        assertThat(bottomNextPageLink).as("Bottom Next page link is null").isNotNull();

    }

}
