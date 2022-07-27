package com.anandbagmar.se4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Deprecated {
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
    public void deprecatedMethods() {
        driver.get("https://www.google.com");

        // findElement
        WebElement searchTextBox = driver.findElement(By.name("q"));

        //findElements
        List<WebElement> allLinks = driver.findElements(By.tagName("a"));


        // Selenium 3 usage
        Actions se3Actions = new Actions(driver);
        WebElement toDoList = driver.findElement(By.id("toDoListBtn"));

        // click method - to click on a webElement
        se3Actions.moveToElement(toDoList)
                  .click();

        // double click - double click on a webElement
        se3Actions.moveToElement(toDoList)
                  .doubleClick();

        // Context click - Right click on a webElement
        se3Actions.moveToElement(toDoList)
                  .contextClick();

        //clickAndHold method - click and hold on a webElement without releasing
        se3Actions.moveToElement(toDoList)
                  .clickAndHold();

        // release -release the hold on a webElement
        se3Actions.moveToElement(toDoList)
                  .release();

        // Selenium 4 usage
        Actions se4Actions = new Actions(driver);
        WebElement toDoList1 = driver.findElement(By.id("toDoListBtn"));

        // click method - to click on a webElement
        se4Actions.click(toDoList);

        //clickAndHold method - click and hold on a webElement without releasing
        se4Actions.clickAndHold(toDoList);

        // Context click - Right click on a webElement
        se4Actions.contextClick(toDoList);

        // double click - double click on a webElement
        se4Actions.doubleClick(toDoList);

        // release -release the hold on a webElement
        se4Actions.release(toDoList);

    }
}
