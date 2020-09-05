package com.anandbagmar.se.learn.pages;

import org.openqa.selenium.By;

public class LandingPage extends BasePage {
    private static final By upcomingLocator = By.xpath("//a[text()=\"Upcoming\"]");
    private static final By conferenceNameLocator = By.cssSelector("img[title='Selenium Conf 2020']");

    public LandingPage() {
        getDriver().get(baseUrl);
        takeScreenshot("Landing page");
        waitFor(2);
    }

    public LandingPage selectFromUpcomingConferences() {
        driver.findElement(upcomingLocator).click();
        return this;
    }

    public ConferenceLandingPage selectConference(String conferenceName) {
        driver.findElement(conferenceNameLocator).click();
        takeScreenshot(String.format("Selected conference - '%s'", conferenceName));
        return new ConferenceLandingPage();
    }

}
