package com.anandbagmar.se4;

public class Sleep {
    public static void forSeconds(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
