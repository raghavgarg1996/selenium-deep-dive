package com.anandbagmar.se.learn.businessLayer;

import com.anandbagmar.se.learn.pages.ConferenceLandingPage;
import com.anandbagmar.se.learn.pages.LandingPage;

public class ConferenceBL {

    public ConferenceBL selectUpcomingConference(String conferenceName) {
        new LandingPage()
                .selectFromUpcomingConferences()
                .selectConference(conferenceName);
        return this;
    }

    public ScheduleBL viewSchedule() {
        new ConferenceLandingPage().viewSchedule();
        return new ScheduleBL();
    }
}