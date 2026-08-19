package org.example;

public class Test {
    public static void main(String[] args) {
//        System.out.println();
        System.out.println("Hello World");
        System.out.println("The name of the this thread is " + Thread.currentThread().getName());
//        for(int i = 0; i < 1000; i++){
//            System.out.println("Hello");
//        }


        //I have to run the thread present in the World class
//        1. Create the object of the World class
//        2. call the start method of the object
        World world = new World();  //NEW STATE
        world.start(); //RUNNABLE
        System.out.println(Thread.currentThread().getName());


//        System.out.println("Infinite Loop");
//        //Infinite Looping
////        for(; ;) {
////            System.out.println("Hello");
////        }
//
//        for(; ;) {
//            System.out.println(Thread.currentThread().getName());
//        }
        //By Implementing the Runnable Interface
//    1. Create a new Object of the class
//    2.Create a new thread
//    3. Pass the name of object in that thread constructor
//    4. t1.start();

        System.out.println("Creating the object of the Car class");
        Car car = new Car();
        System.out.println("Creating a new thread t1 and passing car into that constructor");
        Thread t1 = new Thread(car);
        System.out.println("Start the thread");
        t1.start();





    }





}
