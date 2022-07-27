package com.se4.uielements;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

public class RelativeLocatorsExample {


    public static void main(String[] args) {
        WebDriverManager.chromedriver()
                        .setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://automationbookstore.dev/");
        By idLocator = RelativeLocator.with(By.tagName("li"))
                                      .toLeftOf(By.id("pid6"));

        String id = driver.findElement(idLocator)
                          .getText();
        System.out.println(id);

    }
}