package com.anandbagmar;

public class HelloWorld {
    public HelloWorld() {
        System.out.println("HelloWorld instantiated");
    }

    public static void main(String[] args) {
        System.out.println(new HelloWorld().getDefaultMessage());
    }

    public String getDefaultMessage() {
        return this.getClass().getSimpleName();
    }
}
