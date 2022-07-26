package com.se4.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private static String downloadedDriverVersion;
    private String downloadedDriverPath;
    private WebDriver driver;

    public WebDriver createDriver(String browser) {
        WebDriverManager webDriverManager = WebDriverManager.getInstance(browser);
        webDriverManager.setup();
        downloadedDriverVersion = webDriverManager.getDownloadedDriverVersion();
        System.out.println("WebDriverManager - browser version: " + downloadedDriverVersion);
        downloadedDriverPath = webDriverManager.getDownloadedDriverPath();
        System.out.println("WebDriverManager - browser driver path: " + downloadedDriverPath);
        switch(browser) {
            case "chrome":
                driver = new ChromeDriver();
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
        return driver;
    }
}
