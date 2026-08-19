package org.example;

public class Car implements Runnable{

    @Override
    public void run() {
        for(;;) {
            System.out.println("Car is running");
        }
    }
}
