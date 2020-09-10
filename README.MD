# Selenium Deep Dive
Code snippets used in the Selenium Deep Dive workshop

## Getting started
* Clone the repository
* Import the project in your IDE as a Java Gradle project
* There is a simple HelloWorld test included in the project

# Running the tests
Run the tests from the IDE or from command line

* Windows:

```
    gradlew.bat clean test
```

 * Mac OS / Linux
 ```
    ./gradlew clean test
```

or, you can run the testng tests from your IDE

# Exercise 1 - Implement 1st test

## Site: https://dev.confengine.com/

### Scenario: As a non-logged-in user, I should be asked to login when adding a session to my schedule

* From UPCOMING section, Select “Selenium Conf 2020”
* “View Schedule”
* Add the 1st session to My Schedule (that is NOT already added)
* Do not login to sync the schedule to other devices
* Verify count has increased in “My Schedule”

This test assumes:
* The test runs on Chrome browser
* chromedriver is available in path ~/.m2/repository/webdriver/chromedriver/mac64/85.0.4183.87/chromedriver