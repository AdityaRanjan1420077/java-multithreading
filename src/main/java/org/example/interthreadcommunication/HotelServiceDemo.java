package org.example.interthreadcommunication;


public class HotelServiceDemo {


//          Customer-1 ──→ wait() ──→ WAITING
//          Customer-2 ──→ wait() ──→ WAITING
//          Customer-3 ──→ wait() ──→ WAITING
//                         │
//                         │
//                  Room Service
//                         │
//                    Food Ready
//                         │
//                    notifyAll()
//                         │
//              ┌──────────┼──────────┐
//              ↓          ↓          ↓
//         Customer-1 Customer-2 Customer-3
//            WAKE       WAKE       WAKE

    public static void main(String[] args)
            throws InterruptedException {

        // One shared hotel service.
        HotelService hotelService = new HotelService();

        // Customer 1
        Thread customer1 = new Thread(() -> {
            hotelService.waitForFood("Customer-1");
        });

        // Customer 2
        Thread customer2 = new Thread(() -> {
            hotelService.waitForFood("Customer-2");
        });

        // Customer 3
        Thread customer3 = new Thread(() -> {
            hotelService.waitForFood("Customer-3");
        });

        // Start all customers.
        customer1.start();
        customer2.start();
        customer3.start();

        // Give customers time to enter WAITING state.
        Thread.sleep(1000);

        // Room service thread.
        Thread roomService = new Thread(() -> {
            hotelService.prepareFood();
        });

        // Start room service.
        roomService.start();

        // Wait for all threads to finish.
        customer1.join();
        customer2.join();
        customer3.join();
        roomService.join();

        System.out.println(
                "Hotel service completed."
        );
    }
}