package com.anandbagmar.se.learn.pages;

import org.openqa.selenium.By;

import static com.anandbagmar.se.learn.ScreenShots.takeScreenshot;

public class ConferenceLandingPage
        extends BasePage {
    private static final String CONFNAME = "CONFNAME";
    private static final String viewScheduleLocator = "//div/a[contains(@href,'/schedule')]";

    public ListViewSchedulePage viewSchedule() {
        driver.findElement(By.xpath(viewScheduleLocator))
              .click();
        takeScreenshot("View Schedule");
        return new ListViewSchedulePage();
    }

    private By getConfNameLocatorFor(String conferenceName) {
        return By.xpath(viewScheduleLocator.replace("CONFNAME", conferenceName));
    }
}
