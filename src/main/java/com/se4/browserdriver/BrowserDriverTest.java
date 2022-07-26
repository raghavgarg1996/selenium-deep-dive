package com.se4.browserdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrowserDriverTest {

    private static ChromeDriver driver;
    private final String browser = System.getenv("browser") == null ? "chrome" : System.getenv("browser");
    private String downloadedDriverVersion;
    private String downloadedDriverPath;

    @BeforeTest
    public void setUp() {
        WebDriverManager webDriverManager = WebDriverManager.getInstance(browser);
        webDriverManager.setup();
        downloadedDriverVersion = webDriverManager.getDownloadedDriverVersion();
        System.out.println("WebDriverManager - browser version: " + downloadedDriverVersion);
        downloadedDriverPath = webDriverManager.getDownloadedDriverPath();
        System.out.println("WebDriverManager - browser driver path: " + downloadedDriverPath);
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test() {
        assertThat(downloadedDriverVersion).as("Downloaded driver version is null")
                                           .isNotNull();
        assertThat(downloadedDriverPath).as("Downloaded driver path is null")
                                        .isNotNull();
    }
}
