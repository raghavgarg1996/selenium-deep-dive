package com.anandbagmar.se4.devtools;

import com.anandbagmar.se4.Driver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.openqa.selenium.devtools.v101.network.model.ConnectionType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SimulateNetworkBandwidthTest {

    private DevTools chromeDevTools;
    private ChromeDriver driver;

    @BeforeMethod
    void setup() {
        driver = new Driver().createChromeDriver();
        chromeDevTools = driver.getDevTools();
        chromeDevTools.createSession();
    }

    @AfterMethod
    void tearDown() {
        chromeDevTools.send(Network.disable());
        chromeDevTools.close();
        driver.quit();
    }

    @Test
    public void simulateNetworkBandwidthTest() {
        Map networkConditions = new HashMap() {{
            put("offline", false);
            put("latency", 20);
            put("downloadThroughput", 20);
            put("uploadThroughput", 50);
            put("connectionType", ConnectionType.CELLULAR4G);
        }};
        driver.executeCdpCommand("Network.emulateNetworkConditions", networkConditions);
        driver.get("https://www.google.com");
    }
}
