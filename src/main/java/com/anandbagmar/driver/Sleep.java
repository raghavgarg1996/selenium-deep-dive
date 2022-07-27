package com.anandbagmar.driver;

public class Sleep {
    public static void forSec(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
