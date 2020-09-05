package com.anandbagmar.se.learn.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.anandbagmar.se.learn.ScreenShots.takeScreenshot;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class ListViewSchedulePage extends BasePage {
    private static final By myScheduleCountLocator = By.id("my-schedule-count");
    private static final By addSessionToMyScheduleLocator = By.cssSelector("a[data-tooltip='Add to My Schedule']");
    private static final By cancelLoginModelLocator = By.id("cancel_login_model");

    public int sessionsInMySchedule() {
        return Integer.parseInt(driver.findElement(myScheduleCountLocator).getText());
    }

    public ListViewSchedulePage addSessionToMySchedule() {
        driver.findElement(addSessionToMyScheduleLocator).click();
        explicitlyWaitFor(elementToBeClickable(cancelLoginModelLocator), 3);
        takeScreenshot("Add session to My Schedule");
        return this;
    }

    public ListViewSchedulePage cancelLogin() {
        WebElement cancelButtonElement = driver.findElement(cancelLoginModelLocator);
        cancelButtonElement.click();
        explicitlyWaitFor(invisibilityOf(cancelButtonElement));
        takeScreenshot("Cancel Login when adding session to My Schedule");
        return this;
    }
}
