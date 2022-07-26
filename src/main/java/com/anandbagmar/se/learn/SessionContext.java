package com.anandbagmar.se.learn;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class SessionContext {
    private static final String env = (null == System.getenv("env") ? "dev" : System.getenv("env"));
    private static Map<Long, TestExecutionContext> sessionContext;
    private static int screenshotCounter;
    private static String baseUrl;

    static void instantiate() throws
                              IOException {
        if(null == sessionContext) {
            System.out.println("Instantiate SessionContext");
            sessionContext = new HashMap<>();
            screenshotCounter = 0;
        }
        loadEnvironmentConfig();
    }

    private static void loadEnvironmentConfig() throws
                                                IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/env.json"));
        JsonObject parser = JsonParser.parseReader(reader)
                                      .getAsJsonObject();
        JsonObject envConfig = parser.get(env)
                                     .getAsJsonObject();
        baseUrl = envConfig.get("url")
                           .getAsString();
        System.out.printf("Running test against env: '%s' with config: '%s'%n", env, envConfig);
    }

    static synchronized void addContext(long threadId, TestExecutionContext testExecutionContext) {
        System.out.printf("Adding context for threadId: '%d': Test name: '%s'%n", threadId, testExecutionContext.getTestName());
        sessionContext.put(threadId, testExecutionContext);
    }

    public static synchronized TestExecutionContext getContext(long threadId) {
        return sessionContext.get(threadId);
    }

    static synchronized void removeContext(long threadId) {
        if(null != sessionContext) {
            System.out.println("SessionContext is initialized");
            dumpSessionContext();

            TestExecutionContext testExecutionContext = sessionContext.remove(threadId);
            if(null == testExecutionContext) {
                System.out.println("ERROR: TestExecutionContext was already removed. This is crazy!");
            } else {
                System.out.println("Removed TestExecutionContext for test: " + testExecutionContext.getTestName());
            }

            dumpSessionContext();
        }
    }

    private static synchronized void dumpSessionContext() {
        System.out.println("SessionContext dump");
        for(Long aLong : sessionContext.keySet()) {
            System.out.println("ThreadID: " + aLong + ", TestExecutionContext hashcode: " + sessionContext.get(aLong)
                                                                                                          .hashCode());
        }
    }

    public static int getScreenshotCounter() {
        return ++screenshotCounter;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }
}
