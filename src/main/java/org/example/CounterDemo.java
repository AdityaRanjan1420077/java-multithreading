package org.example;

public class CounterDemo {

    public static void main(String[] args) throws InterruptedException {

//               Counter
//                 |
//              count = 0
//                 |
//       ┌─────────┴─────────┐
//       ↓                   ↓
//   Thread-1            Thread-2
//       │                   │
//       └──── increment ────┘
//                 ↓
//          Race Condition

        Counter counter = new Counter();

        // Create Thread 1
        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }

        });

        // Create Thread 2
        Thread t2 = new Thread(() -> {

            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }

        });

        // Start both threads
        t1.start();
        t2.start();

        // Wait for both threads to finish
        t1.join();
        t2.join();

        // Expected:
        // 10000 + 10000 = 20000
        System.out.println("Final count: " + counter.getCount());
    }


//    Thread-1 → increment()
//              ↓
//           LOCK 🔒
//              ↓
//           count++
//              ↓
//          UNLOCK
//              ↓
//Thread-2 → increment()
//              ↓
//           LOCK 🔒
//              ↓
//           count++
}