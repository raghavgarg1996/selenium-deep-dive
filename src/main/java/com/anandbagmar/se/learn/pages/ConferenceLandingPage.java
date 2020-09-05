package com.anandbagmar.se.learn.pages;

import org.openqa.selenium.By;

public class ConferenceLandingPage extends BasePage {
    private static final By viewScheduleLocator = By.xpath("//div/a[@href='/selenium-conf-2020/schedule']");

    public ListViewSchedulePage viewSchedule() {
        driver.findElement(viewScheduleLocator).click();
        takeScreenshot("View Schedule");
        return new ListViewSchedulePage();
    }
}
