package com.se4.module6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsExample {


    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        driver.get("https://automationbookstore.dev/");
        By idLocator =RelativeLocator.with(By.tagName("li")).toLeftOf(By.id("pid6"));
//                        .below(By.id("pid1"))
//                .getAttribute("id");
         String id = driver.findElement(idLocator).getText();
        System.out.println(id);


//        driver.get("https://www.netflix.com/in/");
//
//
//        WebElement emailTxtBox= driver.findElement(By.id("id_email_hero_fuji"));
//        emailTxtBox.sendKeys("some_email@id.com");
//        driver.findElement(with(By.tagName("div")).toLeftOf(emailTxtBox)).click();
//
//        String data = RelativeLocator.with(By.tagName(("li")).above(By.xpath("//li[@placeholder='footer_responsive_link_help_item']")).below(By.xpath("//li[@placeholder='footer_responsive_link_help_item']")))
//                    .getText();
//        System.out.println(data);


    }
}