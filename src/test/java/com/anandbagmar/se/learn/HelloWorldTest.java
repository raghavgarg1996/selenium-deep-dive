package com.anandbagmar.se.learn;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class HelloWorldTest {

    @Test
    public void testGetDefaultMessage() {
        String expectedDefaultMessage = "HelloWorld";
        HelloWorld helloWorld = new HelloWorld();
        String message = helloWorld.getDefaultMessage();
        System.out.println(message);
        assertEquals(message, expectedDefaultMessage, "Default message is incorrect");
    }
}