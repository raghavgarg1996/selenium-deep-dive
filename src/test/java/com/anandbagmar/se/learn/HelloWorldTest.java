package com.anandbagmar.se.learn;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldTest {

    //    @Test
    public void testGetDefaultMessage() {
        String expectedDefaultMessage = "HelloWorld";
        HelloWorld helloWorld = new HelloWorld();
        String message = helloWorld.getDefaultMessage();
        System.out.println(message);
        assertThat(message).as("Default message is incorrect").isEqualTo(expectedDefaultMessage);
    }
}