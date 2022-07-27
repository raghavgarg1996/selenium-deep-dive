package com.anandbagmar.framework.learn.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyScheduleEx1Test {
    @Test
    public void addSessionToMySchedule() {
        String chromeDriverPath = System.getenv("HOME") + "/.cache/selenium/chromedriver/mac64/103.0.5060.134/chromedriver";
        System.out.println("ChromeDriver path: " + chromeDriverPath);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        //        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);

        String url = "https://dev.confengine.com";
        driver.get(url);

        driver.findElement(By.xpath("//a[text()=\"Upcoming\"]"))
              .click();

        driver.findElement(By.cssSelector("img[title='Selenium Conf 2022']"))
              .click();

        driver.findElement(By.xpath("//div/a[@href='/selenium-conf-2022/schedule']"))
              .click();

        int initialCount = Integer.parseInt(driver.findElement(By.id("my-schedule-count"))
                                                  .getText());
        System.out.println("Initial count = " + initialCount);

        driver.findElement(By.cssSelector("a[data-tooltip='Add to My Schedule']"))
              .click();

        driver.findElement(By.id("cancel_login_model"))
              .click();

        int finalCount = Integer.parseInt(driver.findElement(By.id("my-schedule-count"))
                                                .getText());
        System.out.println("Final count = " + finalCount);

        assertThat(finalCount).isEqualTo(initialCount + 1);

        driver.quit();
    }
}
