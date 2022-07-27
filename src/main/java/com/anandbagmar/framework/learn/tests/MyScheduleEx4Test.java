package com.anandbagmar.framework.learn.tests;

import com.anandbagmar.framework.learn.businessLayer.ConferenceBL;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MyScheduleEx4Test
        extends BaseTest {

    @DataProvider(name = "upcomingConferences")
    public Object[][] upcomingConferencesData() {
        return new Object[][]{{"Selenium Conf 2020"}, {"Agile India 2020"},};
    }

    //    @Test(dataProvider = "upcomingConferences")
    public void addSessionToMySchedule(String conferenceName) {
        new ConferenceBL().selectUpcomingConference(conferenceName)
                          .viewSchedule()
                          .addSessionToMySchedule();
    }

    @DataProvider(name = "pastConferences")
    public Object[][] pastConferencesData() {
        return new Object[][]{{"Appium Conf 2019"},
                              //                {"Selenium Conf 2018"},
        };
    }

    @Test(dataProvider = "pastConferences")
    public void shouldNotBeAbleToAddSessionToMyScheduleForPastConference(String conferenceName) {
        new ConferenceBL().selectConferenceFromPast(conferenceName)
                          .viewSchedule()
                          .shouldNotBeAbleToAddSessionToMySchedule();
    }

    //    @Test
    //    public static void relativeLocators() {
    ////        System.setProperty("webdriver.chrome.driver", PROJECT_PATH+ "/src/main/resources/chromedriver");
    //        TestExecutionContext testExecutionContext = getContext(Thread.currentThread().getId());
    //        WebDriver driver = testExecutionContext.getInnerDriver();
    //        driver.get("https://automationbookstore.dev/");
    //
    //        String id = driver.findElement(
    //                withTagName("li").toLeftOf(By.id("pid6"))
    //                        .below(By.id("pid1")))
    //                            .getAttribute("id");
    //        System.out.println(id);
    //        assertThat(id).as("relative id").isEqualTo("pid5");
    //    }
}
