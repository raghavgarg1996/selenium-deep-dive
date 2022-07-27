package com.se4.devtools;

import com.anandbagmar.driver.Driver;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class SetAuthHeaderTest {

    private static final String USERNAME = "guest";
    private static final String PASSWORD = "guest";
    private static Map<String, Object> params = new HashMap<>();
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
    public void setAuthHeadersTest() {
        driver.get("https://jigsaw.w3.org/HTTP/");
        driver.executeCdpCommand("Network.enable", params);

        Map<String, String> headers = new HashMap<>();
        Base64 base64 = new Base64();
        headers.put("Authorization", "Basic " + new String(base64.encode(String.format("%s:%s", USERNAME, PASSWORD)
                                                                               .getBytes())));
        params = new HashMap<>();
        params.put("headers", headers);
        driver.executeCdpCommand("Network.setExtraHTTPHeaders", params);

        driver.findElement(By.cssSelector("table td> a[href=\"Basic/\"]"))
              .click();

        String loginSuccessMsg = driver.findElement(By.tagName("html"))
                                       .getText();
        System.out.println("Login message: " + loginSuccessMsg);
        assertThat(loginSuccessMsg).as("Login failed")
                                   .isEqualTo("Your browser made it!");
    }
}
