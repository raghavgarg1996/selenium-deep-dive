package com.se4.browserdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BrowserDriverTest {

    private static WebDriver driver;
    private final String browser = System.getenv("browser") == null ? "chrome" : System.getenv("browser")
                                                                                       .toLowerCase()
                                                                                       .trim();
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
        switch(browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
                driver = new ChromeDriver(options);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Browser " + browser + " is not supported");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void browserDriverTest() {
        assertThat(downloadedDriverVersion).as("Downloaded driver version is null")
                                           .isNotNull();
        assertThat(downloadedDriverPath).as("Downloaded driver path is null")
                                        .isNotNull();
    }
}
