package com.anandbagmar.se.learn.businessLayer;

import com.anandbagmar.se.learn.pages.ListViewSchedulePage;

import static org.testng.Assert.assertEquals;

public class ScheduleBL {
    public ScheduleBL addSessionToMySchedule() {
        ListViewSchedulePage listViewSchedulePage = new ListViewSchedulePage();
        int initialCount = listViewSchedulePage.sessionsInMySchedule();
        System.out.println("Initial count = " + initialCount);

        int finalCount = listViewSchedulePage
                                 .addSessionToMySchedule()
                                 .cancelLogin()
                                 .sessionsInMySchedule();
        System.out.println("Final count = " + finalCount);

        assertEquals(finalCount, initialCount + 1, "Session not added to my schedule");
        return this;
    }
}
