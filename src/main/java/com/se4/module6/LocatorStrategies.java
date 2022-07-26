package com.se4.module6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorStrategies {

    @Test
    public void locators(){
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://www.tutorialspoint.com/selenium/selenium_automation_practice.htm#");

        // name
        WebElement fnameTxtBox = driver.findElement(By.name("firstname"));
        WebElement lnameTxtBox = driver.findElement(By.name("lastname"));

        // xpath - https://devhints.io/xpath
        WebElement expRadioWithTwoYears = driver.findElement(By.xpath("//input[@name='exp' and @value='2']"));

        // css - https://www.w3schools.com/cssref/css_selectors.asp
        WebElement toolCheckBoxRC = driver.findElement(By.cssSelector("input[name='tool'][value='RC']"));

        WebElement dynamicColorBtn = driver.findElement(By.id("dynamicColor"));

        // tagname
        List<WebElement> allLabels = driver.findElements(By.tagName("strong"));

        // link text
        List<WebElement> nextPageLink = driver.findElements(By.linkText("Next Page "));

        // Partial Link Text
        List<WebElement> printLink = driver.findElements(By.partialLinkText("Print"));

        List<WebElement> bottomNextPageLink = driver.findElements(By.className("nxt-btn"));


    }

}
