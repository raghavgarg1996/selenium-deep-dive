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

```
    mvn clean test
```

* To run a specific test:

```
    ./gradlew clean test --tests <test_name>
    
    ex: 
    
    ./gradlew clean test --tests HelloWorldTest
```

```
    mvn clean test -Dtest=<test_name>
    
    ex: 
    
    mvn clean test -Dtest=HelloWorldTest
```


or, you can run the tests from your IDE