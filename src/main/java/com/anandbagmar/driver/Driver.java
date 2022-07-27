package com.anandbagmar.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private static String downloadedDriverVersion;
    private String downloadedDriverPath;
    private WebDriver driver;

    public ChromeDriver createChromeDriver() {
        return (ChromeDriver) createDriver("chrome");
    }

    public FirefoxDriver createFirefoxDriver() {
        return (FirefoxDriver) createDriver("chrome");
    }

    public SafariDriver createSafariDriver() {
        return (SafariDriver) createDriver("safari");
    }

    public EdgeDriver createEdgeDriver() {
        return (EdgeDriver) createDriver("edge");
    }

    public WebDriver createDriver(String browser) {
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
                System.setProperty("webdriver.chrome.driver", downloadedDriverPath);
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.setProperty("webdriver.firefox.driver", downloadedDriverPath);
                break;
            case "safari":
                driver = new SafariDriver();
                System.setProperty("webdriver.safari.driver", downloadedDriverPath);
                break;
            case "edge":
                driver = new EdgeDriver();
                System.setProperty("webdriver.edge.driver", downloadedDriverPath);
                break;
            default:
                throw new IllegalArgumentException("Browser " + browser + " is not supported");
        }
        return driver;
    }
}
