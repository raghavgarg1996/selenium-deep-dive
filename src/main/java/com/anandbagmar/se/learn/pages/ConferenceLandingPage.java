package com.anandbagmar.se.learn.pages;

import org.openqa.selenium.By;

import static com.anandbagmar.se.learn.ScreenShots.takeScreenshot;

public class ConferenceLandingPage extends BasePage {
    private static final String CONFNAME = "CONFNAME";
    private static final String viewScheduleLocator = "//div/a[@href='/" + CONFNAME + "/schedule']";

    public ListViewSchedulePage viewSchedule() {
        String conferenceName = getTestExecutionContext().getState("conferenceName");
        conferenceName = conferenceName.toLowerCase().replaceAll(" ", "-");
        System.out.println(conferenceName);
        System.out.println(getConfNameLocatorFor(conferenceName));
        driver.findElement(getConfNameLocatorFor(conferenceName)).click();
        takeScreenshot("View Schedule");
        return new ListViewSchedulePage();
    }

    private By getConfNameLocatorFor(String conferenceName) {
        return By.xpath(viewScheduleLocator.replace("CONFNAME", conferenceName));
    }
}
