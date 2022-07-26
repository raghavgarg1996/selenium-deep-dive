package com.anandbagmar.se.learn.businessLayer;

import com.anandbagmar.se.learn.pages.ListViewSchedulePage;

import static org.assertj.core.api.Assertions.assertThat;

public class ScheduleBL {
    public ScheduleBL addSessionToMySchedule() {
        int expectedChangeInSessionCount = 1;
        String errorMessage = "Session should have been added to My Schedule";
        addSessionAndValidate(expectedChangeInSessionCount, errorMessage);
        return this;
    }

    private void addSessionAndValidate(int expectedChangeInSessionCount, String errorMessage) {
        ListViewSchedulePage listViewSchedulePage = new ListViewSchedulePage();
        int initialCount = listViewSchedulePage.sessionsInMySchedule();
        System.out.println("Initial count = " + initialCount);

        int finalCount = listViewSchedulePage.addSessionToMySchedule()
                                             .cancelLogin()
                                             .sessionsInMySchedule();
        System.out.println("Final count = " + finalCount);

        assertThat(finalCount).as(errorMessage)
                              .isEqualTo(initialCount + expectedChangeInSessionCount);
    }

    public ScheduleBL shouldNotBeAbleToAddSessionToMySchedule() {
        int expectedChangeInSessionCount = 0;
        String errorMessage = "Session should NOT have been added to My Schedule";
        addSessionAndValidate(expectedChangeInSessionCount, errorMessage);
        return this;
    }
}
