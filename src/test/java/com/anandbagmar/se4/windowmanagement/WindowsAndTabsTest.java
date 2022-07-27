package com.anandbagmar.se4.windowmanagement;

import com.anandbagmar.se4.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class WindowsAndTabsTest {
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
    public void newWindowTest() {
        driver.get("https://www.google.com/");

        // open a new window
        driver.switchTo()
              .newWindow(WindowType.WINDOW);
        //navigate to a URL on newly opened window
        driver.navigate()
              .to("https://www.bing.com/");

        // get the window ids to switch between them
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String googleWindow = it.next();
        String bingWindow = it.next();
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle())
                .as("Title of the page is not as expected")
                .isEqualTo("Search");

        // switch the control to the google window
        driver.switchTo()
              .window(googleWindow);
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle())
                .as("Title of the page is not as expected")
                .isEqualTo("Google");

        // open a new tabbed window
        driver.switchTo()
              .newWindow(WindowType.TAB);
        //navigate to a URL on newly opened tabbed window
        driver.navigate()
              .to("https://www.gmail.com/");
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle())
                .as("Title of the page is not as expected")
                .contains("Gmail");

        driver.switchTo()
              .window(bingWindow);
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle())
                .as("Title of the page is not as expected")
                .isEqualTo("Search");
    }

    @Test
    public void tabbedWindowTest() {
        driver.get("https://www.gmail.com/");
        System.out.println(driver.getTitle());
        assertThat(driver.getTitle())
                .as("Title of the page is not as expected")
                .contains("Gmail");

        // open a new tabbed window
        driver.switchTo()
              .newWindow(WindowType.TAB);
        //navigate to a URL on newly opened tabbed window

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String googleWindow = it.next();
        String gmailWindow = it.next();

        driver.switchTo()
              .window(gmailWindow);
        driver.navigate()
              .to("https://www.google.com/");

        System.out.println(driver.getTitle());
        assertThat(driver.getTitle())
                .as("Title of the page is not as expected")
                .isEqualTo("Google");
    }

}
