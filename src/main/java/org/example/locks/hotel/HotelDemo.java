package org.example.locks.hotel;

public class HotelDemo {

    public static void main(String[] args) throws InterruptedException {

        // One hotel with one available room.
        Hotel hotel = new Hotel(;

        // Customer 1 tries to book the room.
        Thread customer1 = new Thread(() -> {

            hotel.bookRoom("Customer-1");

        });

        // Customer 2 tries to book the same room.
        Thread customer2 = new Thread(() -> {

            hotel.bookRoom("Customer-2");

        });

        // Customer 3 tries to book the same room.
        Thread customer3 = new Thread(() -> {

            hotel.bookRoom("Customer-3");

        });

        // Start all customers.
        customer1.start();
        customer2.start();
        customer3.start();

        // Wait for all customers to finish.
        customer1.join();
        customer2.join();
        customer3.join();

        System.out.println("Hotel booking process completed.");
    }
}