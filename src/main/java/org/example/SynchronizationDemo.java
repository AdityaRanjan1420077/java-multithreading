package org.example;

public class SynchronizationDemo {

    public static void main(String[] args) throws InterruptedException {

        // Create one shared SynchronizedCounter object.
        //
        // Both threads will access this SAME object.
        SynchronizedCounter counter = new SynchronizedCounter();

        // First thread
        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }

        });

        // Second thread
        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }

        });

        // Start both threads.
        t1.start();
        t2.start();

        // Main thread waits until t1 finishes.
        t1.join();

        // Main thread waits until t2 finishes.
        t2.join();

        // Expected result:
        //
        // Thread 1 → 10,000
        // Thread 2 → 10,000
        // Total    → 20,000
        System.out.println("Final Count: " + counter.getCount());


//              SynchronizedCounter
//                     │
//                count = 0
//                     │
//             ┌───────┴───────┐
//             ↓               ↓
//         Thread-1        Thread-2
//             │               │
//        increment()      increment()
//             │               │
//          🔒 LOCK          waits
//             │
//          count++
//             │
//         UNLOCK
//                             │
//                          🔒 LOCK
//                             │
//                          count++
//                             │
//                          UNLOCK
    }
}