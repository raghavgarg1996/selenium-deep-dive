package com.anandbagmar.se4.devtools;

import com.anandbagmar.se4.Driver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CaptureNetworkTrafficTest {

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
    void captureNetworkTrafficTest() {
        ArrayList capturedTraffic = new ArrayList();

        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        chromeDevTools.addListener(Network.requestWillBeSent(), entry -> {
            System.out.println("Request URI : " + entry.getRequest()
                                                       .getUrl() + "\n" + " With method : " + entry.getRequest()
                                                                                                   .getMethod() + "\n");
            capturedTraffic.add(entry.getRequest());
        });
        driver.get("https://www.google.com");
        assertThat(capturedTraffic.size()).as("Captured traffic is empty")
                                          .isNotEqualTo(0);
    }
}
