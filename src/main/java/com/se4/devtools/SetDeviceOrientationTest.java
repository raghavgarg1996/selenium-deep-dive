package com.se4.devtools;

import com.se4.driver.Driver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.deviceorientation.DeviceOrientation;
import org.openqa.selenium.devtools.v101.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// device tilt angle
public class SetDeviceOrientationTest {

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
    public void setDeviceOrientationTest() {
        Command deviceOrientationCmd = DeviceOrientation.setDeviceOrientationOverride(50, 40, 5);
        chromeDevTools.send(deviceOrientationCmd);
        driver.get("https://www.google.com");
    }
}
