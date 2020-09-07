package com.anandbagmar.se.learn;

import com.anandbagmar.se.learn.businessLayer.ConferenceBL;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MyScheduleEx4Test extends BaseTest {

    @DataProvider(name = "upcomingConferences")
    public Object[][] upcomingConferencesData() {
        return new Object[][]{
                {"Selenium Conf 2020"},
                {"Agile India 2020"},
        };
    }

    @Test(dataProvider = "upcomingConferences")
    public void addSessionToMySchedule(String conferenceName) {
        new ConferenceBL()
                .selectUpcomingConference(conferenceName)
                .viewSchedule()
                .addSessionToMySchedule();
    }

    @DataProvider(name = "pastConferences")
    public Object[][] pastConferencesData() {
        return new Object[][]{
                {"Appium Conf 2019"},
                {"Selenium Conf 2018"},
        };
    }

    @Test(dataProvider = "pastConferences")
    public void shouldNotBeAbleToAddSessionToMyScheduleForPastConference(String conferenceName) {
        new ConferenceBL()
                .selectConferenceFromPast(conferenceName)
                .viewSchedule()
                .shouldNotBeAbleToAddSessionToMySchedule();
    }
}
