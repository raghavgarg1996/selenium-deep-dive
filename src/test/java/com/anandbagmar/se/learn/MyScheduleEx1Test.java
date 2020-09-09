package com.anandbagmar.se.learn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyScheduleEx1Test {
    @Test
    public void addSessionToMySchedule() {
        String chromeDriverPath = System.getenv("HOME") + "/.m2/repository/webdriver/chromedriver/mac64/85.0.4183.87/chromedriver";
        System.out.println("ChromeDriver path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
//        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://dev.confengine.com";
        driver.get(url);

        driver.findElement(By.xpath("//a[text()=\"Upcoming\"]")).click();

        driver.findElement(By.cssSelector("img[title='Selenium Conf 2020']")).click();

        driver.findElement(By.xpath("//div/a[@href='/selenium-conf-2020/schedule']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int initialCount = Integer.parseInt(driver.findElement(By.id("my-schedule-count")).getText());
        System.out.println("Initial count = " + initialCount);

        driver.findElement(By.cssSelector("a[data-tooltip='Add to My Schedule']")).click();

        driver.findElement(By.id("cancel_login_model")).click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int finalCount = Integer.parseInt(driver.findElement(By.id("my-schedule-count")).getText());
        System.out.println("Final count = " + finalCount);

        Assert.assertEquals(finalCount, initialCount + 1);

        driver.quit();
    }
}
