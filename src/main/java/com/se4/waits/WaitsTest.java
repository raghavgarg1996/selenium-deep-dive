package com.se4.waits;

import com.anandbagmar.driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class WaitsTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new Driver().createChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void implicitWaitTest() {
        driver.manage()
              .timeouts()
              .implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.swiggy.com/");
        driver.findElement(By.id("location"))
              .sendKeys("Bangalore");
        driver.findElement(By.xpath("//button[@tabindex='2']"))
              .click();
        String numberOfRestaurantsFound = driver.findElement(By.xpath("//div[@id='all_restaurants']//div[contains(text(), ' restaurants')]"))
                                                .getText();
        System.out.println(numberOfRestaurantsFound);
        int numberOfRestaurants = Integer.parseInt(numberOfRestaurantsFound.split(" ")[0]);
        System.out.printf("Found %d restaurants\n", numberOfRestaurants);
        assertThat(numberOfRestaurants).as("Incorrect number of restaurants found")
                                       .isGreaterThan(500);
    }

    @Test
    public void explicitWaitTest() {
        driver.get("https://www.swiggy.com/");
        driver.findElement(By.id("location"))
              .sendKeys("Bangalore");
        WebElement dropdownElement = new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@tabindex='2']")));
        dropdownElement.click();
        WebElement numberOfRestaurantsElement = new WebDriverWait(driver, Duration.ofSeconds(5)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='all_restaurants']//div[contains(text(), ' restaurants')]")));
        String numberOfRestaurantsFound = numberOfRestaurantsElement.getText();
        System.out.println(numberOfRestaurantsFound);
        int numberOfRestaurants = Integer.parseInt(numberOfRestaurantsFound.split(" ")[0]);
        System.out.printf("Found %d restaurants\n", numberOfRestaurants);
        assertThat(numberOfRestaurants).as("Incorrect number of restaurants found")
                                       .isGreaterThan(500);
    }

    @Test
    public void fluentWaitTest() {

        driver.get("https://www.swiggy.com/");
        driver.findElement(By.id("location"))
              .sendKeys("Bangalore");

        fluentlyWaitUntil(By.xpath("//button[@tabindex='2']")).click();

        WebElement numberOfRestaurantsElement = fluentlyWaitUntil(By.xpath("//div[@id='all_restaurants']//div[contains(text(), ' restaurants')]"));
        String numberOfRestaurantsFound = numberOfRestaurantsElement.getText();
        System.out.println(numberOfRestaurantsFound);
        int numberOfRestaurants = Integer.parseInt(numberOfRestaurantsFound.split(" ")[0]);
        System.out.printf("Found %d restaurants\n", numberOfRestaurants);
        assertThat(numberOfRestaurants).as("Incorrect number of restaurants found")
                                       .isGreaterThan(500);
    }

    private WebElement fluentlyWaitUntil(By elementLocator) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
                                                                .pollingEvery(Duration.ofMillis(500))
                                                                .ignoring(org.openqa.selenium.NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(elementLocator);
            }
        });
        return element;
    }
}
