package org.example.interthreadcommunication;


public class Restaurant {


//                   Waiter
//                     ↓
//              No food available
//                     ↓
//                   wait()
//                     ↓
//                   WAITING
//                     │
//                     │
//                     │ Chef produces food
//                     │
//                     ↓
//                    Chef
//                     ↓
//                Food prepared
//                     ↓
//                  notify()
//                     │
//                     ↓
//              Waiter wakes up
//                     ↓
//              Food received
//                     ↓
//                 Serving food

    public static void main(String[] args) throws InterruptedException {

        // Shared resource.
        // Both threads will use the same restaurant.
        SharedResource restaurant = new SharedResource();

        // Producer thread = Chef
        Thread chef = new Thread(() -> {

            restaurant.produce();

        }, "Chef");

        // Consumer thread = Waiter
        Thread waiter = new Thread(() -> {

            restaurant.consume();

        }, "Waiter");


        // Start the waiter first.
        // Since food is not available,
        // the waiter will enter WAITING state.
        waiter.start();

        // Give waiter time to start waiting.
        Thread.sleep(1000);

        // Now start the chef.
        // Chef produces food and calls notify().
        chef.start();


        // Wait for both threads to finish.
        waiter.join();
        chef.join();

        System.out.println(
                "Restaurant work completed."
        );
    }
}