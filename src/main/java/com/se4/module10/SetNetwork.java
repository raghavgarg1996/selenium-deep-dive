package com.se4.module10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.model.ConnectionType;

import java.util.HashMap;
import java.util.Map;

public class SetNetwork {

    final static String PROJECT_PATH = System.getProperty("user.dir");

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();

        DevTools devTools;
        ChromeDriver driver;

        driver = new ChromeDriver();
        devTools = driver.getDevTools();
        devTools.createSession();
        Map networkConditions = new HashMap()
        {{
            put("offline", false);
            put("latency", 20);
            put("downloadThroughput", 20);
            put("uploadThroughput", 50);
            put("connectionType",ConnectionType.CELLULAR4G);
        }};
        driver.executeCdpCommand("Network.emulateNetworkConditions", networkConditions);
        driver.get("https://www.google.com");
    }
}
