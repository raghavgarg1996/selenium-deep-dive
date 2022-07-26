package com.se4.module8;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class WindowsAndTabs {
    WebDriver driver;

    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void newWindowTest() {
        driver.get("https://www.google.com/");

        // open a new window
        driver.switchTo().newWindow(WindowType.WINDOW);
        //navigate to a URL on newly opened window
        driver.navigate().to("https://www.youtube.com/");

        // get the window ids to switch between them
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String googleWindow = it.next();
        String youtubeWindow = it.next();
        System.out.println(driver.getTitle());

        // switch the control to the google window
        driver.switchTo().window(googleWindow);
        System.out.println(driver.getTitle());

        // open a new tabbed window
        driver.switchTo().newWindow(WindowType.TAB);
        //navigate to a URL on newly opened tabbed window
        driver.navigate().to("https://www.gmail.com/");
        System.out.println(driver.getTitle());

        driver.switchTo().window(youtubeWindow);
        System.out.println(driver.getTitle());

        driver.quit();
    }

    @Test
    public void tabbedWindowTest() {
        driver.get("https://www.gmail.com/");
        System.out.println(driver.getTitle());

        // open a new tabbed window
        driver.switchTo().newWindow(WindowType.TAB);
        //navigate to a URL on newly opened tabbed window

        Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String googleWindow = it.next();
        String youtubeWindow = it.next();

        driver.switchTo().window(youtubeWindow);
        driver.navigate().to("https://www.youtube.com/");

        System.out.println(driver.getTitle());

        driver.quit();
    }

}
