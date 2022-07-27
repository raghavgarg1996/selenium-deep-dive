package com.anandbagmar.se4.uielements;

import com.anandbagmar.se4.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LocatorStrategiesTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new Driver().createChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm#");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void byNameTest() {
        // name
        WebElement firstNameTextBox = driver.findElement(By.name("firstname"));
        assertThat(firstNameTextBox).as("first name text box is null")
                                    .isNotNull();

        WebElement lastNameTextBox = driver.findElement(By.name("lastname"));
        assertThat(lastNameTextBox).as("last name text box is null")
                                   .isNotNull();
    }

    @Test
    public void byXpathTest() {
        // xpath - https://devhints.io/xpath
        WebElement expRadioWithTwoYears = driver.findElement(By.xpath("//input[@name='exp' and @value='2']"));
        assertThat(expRadioWithTwoYears).as("Experience Radio Button for 2 Years is null")
                                        .isNotNull();
    }

    @Test
    public void byCSSTest() {
        // css - https://www.w3schools.com/cssref/css_selectors.asp
        WebElement toolCheckBoxRC = driver.findElement(By.cssSelector("input[name='tool'][value='RC']"));
        assertThat(toolCheckBoxRC).as("Tools check box is null")
                                  .isNotNull();
    }

    @Test
    public void byIdTest() {
        // id
        WebElement dynamicColorBtn = driver.findElement(By.id("dynamicColor"));
        assertThat(dynamicColorBtn).as("Dynamic color button is null")
                                   .isNotNull();
    }

    @Test
    public void byTagNameTest() {
        // tagname
        List<WebElement> allLabels = driver.findElements(By.tagName("strong"));
        assertThat(allLabels).as("All labels is null")
                             .isNotEmpty();
    }

    @Test
    public void byLinkTextTest() {
        // link text
        List<WebElement> nextPageLink = driver.findElements(By.linkText("Next Page "));
        assertThat(nextPageLink).as("Next Page link is null")
                                .isNotNull();
    }

    @Test
    public void byPartialLinkTextTest() {
        // Partial Link Text
        List<WebElement> printLink = driver.findElements(By.partialLinkText("Print"));
        assertThat(printLink).as("Print Link is null")
                             .isNotNull();
    }

    @Test
    public void byFindElementsTest() {
        List<WebElement> bottomNextPageLinks = driver.findElements(By.className("nxt-btn"));
        System.out.println("Number of bottom next page links: " + bottomNextPageLinks.size());
        assertThat(bottomNextPageLinks).as("Bottom Next page link is null")
                                      .isNotEmpty();
        assertThat(bottomNextPageLinks.size()).as("Bottom Next page link is null")
                                      .isNotEqualTo(0);
    }
}
