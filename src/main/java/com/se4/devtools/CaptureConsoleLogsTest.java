package com.se4.devtools;

import com.se4.driver.Driver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.log.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptureConsoleLogsTest {

    private DevTools chromeDevTools;
    private ChromeDriver driver;

    @BeforeMethod
    void setup() {
        driver = (ChromeDriver) new Driver().createDriver("chrome");
        chromeDevTools = driver.getDevTools();
        chromeDevTools.createSession();
    }

    @AfterMethod
    void tearDown() {
        chromeDevTools.close();
        driver.quit();
    }

    @Test
    void captureConsoleLogsTest() {
        chromeDevTools.send(Log.enable());
        ArrayList capturedLogs = new ArrayList();

        chromeDevTools.addListener(Log.entryAdded(), logEntry -> {
            System.out.println("\tlog: " + logEntry.getText());
            System.out.println("\tlevel: " + logEntry.getLevel());
            capturedLogs.add(logEntry.getText());
        });
        driver.get("https://testersplayground.herokuapp.com/console-5d63b2b2-3822-4a01-8197-acd8aa7e1343.php");
        assertThat(capturedLogs).as("Captured logs is empty")
                                .isNotEmpty();
    }
}
