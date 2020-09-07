# Selenium Deep Dive
Code snippets used in the Selenium Deep Dive workshop

## Getting started
* Clone the repository
* Import the project in your IDE as a Java Gradle project
* There is a simple HelloWorld test included in the project

# Running the tests
Run the tests from the IDE or from command line
```
./gradlew clean test
```

or, you can run the testng tests from your IDE


# Exercise 3 - Implement new test

### Site: https://dev.confengine.com/

### Scenario: As a non-logged-in user, I should be asked to login when adding a session to my schedule

* From UPCOMING section, Select “Selenium Conf 2020”
* “View Schedule”
* Add the 1st session to My Schedule (that is NOT already added)
* Do not login to sync the schedule to other devices
* Verify count has increased in “My Schedule”

### Scenario: As a non-logged-in user, I shouldn’t be able to add a session to my schedule for a conference that has completed
* From PAST section, Select “Appium Conf 2019”
* “View Schedule”
* Add the 1st session to My Schedule (that is NOT already added)
* Verify adding session to Schedule does not increase the count in “My Schedule” for a conference in the PAST

### Test implementation details:
The test works with multiple browsers. Default browser is Chrome. To change browser, set environment variable 'browser'
```
    export browser=firefox (Mac OS X / Linux)

    or

    set browser=firefox (Windows)
```