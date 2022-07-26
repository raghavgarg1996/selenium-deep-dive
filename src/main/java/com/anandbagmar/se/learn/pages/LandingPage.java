package com.anandbagmar.se.learn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.anandbagmar.se.learn.ScreenShots.takeScreenshot;

public class LandingPage
        extends BasePage {
    private static final By upcomingLocator = By.xpath("//a[text()=\"Upcoming\"]");
    private static final String CONFNAME = "CONFNAME";
    private static final String conferenceNameLocator = "img[title='" + CONFNAME + "']";
    private static final By pastLocator = By.xpath("//a[text()=\"Past\"]");

    public LandingPage() {
        getDriver().get(baseUrl);
        takeScreenshot("Landing page");
        waitFor(2);
    }

    public LandingPage selectFromUpcomingConferences() {
        driver.findElement(upcomingLocator)
              .click();
        return this;
    }

    public ConferenceLandingPage selectConference(String conferenceName) {
        getTestExecutionContext().addState("conferenceName", conferenceName);
        WebElement confElement = driver.findElement(getConfNameLocatorFor(conferenceName));
        //        scrollElementIntoView(confElement);
        //        driver.findElement((withTagName("")))
        driver.findElement(getConfNameLocatorFor(conferenceName))
              .click();
        takeScreenshot(String.format("Selected conference - '%s'", conferenceName));
        return new ConferenceLandingPage();
    }

    private By getConfNameLocatorFor(String conferenceName) {
        return By.cssSelector(conferenceNameLocator.replace("CONFNAME", conferenceName));
    }

    public LandingPage selectFromPastConferences() {
        driver.findElement(pastLocator)
              .click();
        return this;
    }
}
