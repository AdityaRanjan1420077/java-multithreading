package org.example.deadlock;

public class DeadlockDemo {


//             DEADLOCK
//                │
//       ┌────────┴────────┐
//       ↓                 ↓
//   Thread-1          Thread-2
//       │                 │
//   Has Lock-1        Has Lock-2
//       │                 │
//   Wants Lock-2      Wants Lock-1
//       │                 │
//       └───────┐ ┌───────┘
//               ↓ ↓
//             WAIT
//               ↕
//            FOREVER

    // Two shared resources / locks
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        // Thread-1 acquires lock1 first,
        // then tries to acquire lock2.
        Thread thread1 = new Thread(() -> {

            synchronized (lock1) {

                System.out.println(
                        "Thread-1 acquired Lock-1"
                );

                // Give Thread-2 a chance to acquire Lock-2.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Thread-1 is waiting for Lock-2"
                );

                synchronized (lock2) {

                    System.out.println(
                            "Thread-1 acquired Lock-2"
                    );
                }
            }
        });


        // Thread-2 acquires lock2 first,
        // then tries to acquire lock1.
        Thread thread2 = new Thread(() -> {

            synchronized (lock2) {

                System.out.println(
                        "Thread-2 acquired Lock-2"
                );

                // Give Thread-1 a chance to acquire Lock-1.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Thread-2 is waiting for Lock-1"
                );

                synchronized (lock1) {

                    System.out.println(
                            "Thread-2 acquired Lock-1"
                    );
                }
            }
        });


        thread1.start();
        thread2.start();
    }
}