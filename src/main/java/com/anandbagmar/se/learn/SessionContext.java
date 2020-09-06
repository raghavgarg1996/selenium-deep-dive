package com.anandbagmar.se.learn;

import java.util.HashMap;
import java.util.Map;

public class SessionContext {
    private static Map<Long, TestExecutionContext> sessionContext;
    private static int screenshotCounter;

    static void instantiate() {
        if (null == sessionContext) {
            System.out.println("Instantiate SessionContext");
            sessionContext = new HashMap<>();
            screenshotCounter = 0;
        }
    }

    static synchronized void addContext(long threadId, TestExecutionContext testExecutionContext) {
        System.out.println("Adding context for threadId: " + threadId);
        sessionContext.put(threadId, testExecutionContext);
    }

    private static synchronized void dumpSessionContext() {
        System.out.println("SessionContext dump");
        for (Long aLong : sessionContext.keySet()) {
            System.out.println("ThreadID: " + aLong + ", TestExecutionContext hashcode: " + sessionContext.get(aLong).hashCode());
        }
    }

    public static synchronized TestExecutionContext getContext(long threadId) {
        return sessionContext.get(threadId);
    }

    static synchronized void removeContext(long threadId) {
        if (null != sessionContext) {
            System.out.println("SessionContext is initialized");
            dumpSessionContext();

            TestExecutionContext testExecutionContext = sessionContext.remove(threadId);
            if (null == testExecutionContext) {
                System.out.println("ERROR: TestExecutionContext was already removed. This is crazy!");
            } else {
                System.out.println("Removed TestExecutionContext for test: " + testExecutionContext.getTestName());
            }

            dumpSessionContext();
        }
    }

    public static int getScreenshotCounter() {
        return ++screenshotCounter;
    }
}
