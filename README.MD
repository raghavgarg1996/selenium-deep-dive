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

### Test implementation details:
The test works with multiple browsers. Default browser is Chrome. To change browser, set environment variable 'browser'
```
    export browser=firefox (Mac OS X / Linux)

    or

    set browser=firefox (Windows)
```