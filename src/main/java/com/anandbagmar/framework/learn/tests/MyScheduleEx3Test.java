package com.anandbagmar.framework.learn.tests;

import com.anandbagmar.framework.learn.businessLayer.ConferenceBL;

public class MyScheduleEx3Test
        extends BaseTest {

    //    @Test
    public void addSessionToMySchedule() {
        new ConferenceBL().selectUpcomingConference("Selenium Conf 2020")
                          .viewSchedule()
                          .addSessionToMySchedule();
    }

    //    @Test
    public void shouldNotBeAbleToAddSessionToMyScheduleForPastConference() {
        new ConferenceBL().selectConferenceFromPast("Appium Conf 2019")
                          .viewSchedule()
                          .shouldNotBeAbleToAddSessionToMySchedule();
    }
}
