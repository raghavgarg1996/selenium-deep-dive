package com.se4.devtools;

import com.se4.driver.Driver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.openqa.selenium.devtools.v101.performance.Performance;
import org.openqa.selenium.devtools.v101.performance.model.Metric;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.devtools.v101.performance.Performance.disable;
import static org.openqa.selenium.devtools.v101.performance.Performance.getMetrics;

public class GetPerformanceMetricsTest {
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
    public void getPerformanceMetricsTest() {
        chromeDevTools.send(Performance.enable(Optional.empty()));
        driver.get("https://www.google.org");
        List<Metric> metrics = chromeDevTools.send(getMetrics());
        chromeDevTools.send(disable());
        metrics.forEach(capturedMetric -> System.out.println(capturedMetric.getName() + "::" + capturedMetric.getValue()));
        assertThat(metrics).as("No performance metrics captured")
                           .isNotEmpty();
    }
}
