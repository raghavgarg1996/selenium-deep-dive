package com.se4.devtools;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.emulation.Emulation;

import java.util.HashMap;
import java.util.Optional;

public class GeoMocking {

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();

        DevTools devTools;
        ChromeDriver driver;

        ChromeOptions options = new ChromeOptions();
        HashMap<String, Integer> contentSettings = new HashMap<String, Integer>();
        HashMap<String, Object> profile = new HashMap<String, Object>();
        HashMap<String, Object> prefs = new HashMap<String, Object>();

        contentSettings.put("geolocation", 1);
        contentSettings.put("notifications", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Emulation.setGeolocationOverride(Optional.of(35.8235),
                Optional.of(-78.8256),
                Optional.of(1)));

        driver.navigate().refresh();
        driver.get("https://oldnavy.gap.com/stores");
    }
}
