package com.se4.module10;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.performance.Performance;
import org.openqa.selenium.devtools.v101.performance.model.Metric;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.openqa.selenium.devtools.v101.performance.Performance.*;

public class GetMetrics {

    private static ChromeDriver driver;
    private static DevTools chromeDevTools;

    final static String PROJECT_PATH = System.getProperty("user.dir");

    public static void main(String[] args){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        chromeDevTools = driver.getDevTools();
        chromeDevTools.createSession();

        List<String> toCaptureMetricNames = Arrays
                .asList(new String[] { "Timestamp", "Documents", "Frames",
                        "JSEventListeners", "LayoutObjects", "MediaKeySessions", "Nodes",
                        "Resources", "DomContentLoaded", "NavigationStart" });

        chromeDevTools.send(Performance.enable(Optional.empty()));

            driver.get("https://www.google.org");
            List<Metric> metrics = chromeDevTools.send(getMetrics());
        chromeDevTools.send(disable());
        List<String> metricNames = metrics.stream().map(o -> o.getName())
                .collect(Collectors.toList());

        toCaptureMetricNames.forEach( metric  -> System.out.println(metric +
                                            " is : " +
                                            metrics.get(metricNames.indexOf(metric)).getValue()));




    }




}
