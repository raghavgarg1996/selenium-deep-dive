package com.anandbagmar.se.learn;

import com.anandbagmar.se.learn.businessLayer.ConferenceBL;
import org.testng.annotations.Test;

public class MyScheduleEx3Test extends BaseTest {

    @Test
    public void addSessionToMySchedule() {
        new ConferenceBL()
                .selectUpcomingConference("Selenium Conf 2020")
                .viewSchedule()
                .addSessionToMySchedule();
    }

    @Test
    public void shouldNotBeAbleToAddSessionToMyScheduleForPastConference() {
        new ConferenceBL()
                .selectConferenceFromPast("Appium Conf 2019")
                .viewSchedule()
                .shouldNotBeAbleToAddSessionToMySchedule();
    }
}
