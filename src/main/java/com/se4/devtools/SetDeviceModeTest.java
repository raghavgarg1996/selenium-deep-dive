package com.se4.devtools;

import com.anandbagmar.driver.Driver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class SetDeviceModeTest {

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
        chromeDevTools.send(Network.disable());
        chromeDevTools.close();
        driver.quit();
    }

    @Test
    public void setDeviceModeTest() {
        Map deviceMetrics = new HashMap() {{
            put("width", 600);
            put("height", 1000);
            put("mobile", true);
            put("deviceScaleFactor", 100);
        }};
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://www.youtube.com");
    }
}
