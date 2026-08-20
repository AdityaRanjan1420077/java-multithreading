package org.example.deadlock;

public class DeadlockPrevention {

    // Two shared locks
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {

        // Thread-1 acquires Lock-1 first,
        // then tries to acquire Lock-2.
        Thread thread1 = new Thread(() -> {

            synchronized (lock1) {

                System.out.println(
                        "Thread-1 acquired Lock-1"
                );

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

                    System.out.println(
                            "Thread-1 completed its work"
                    );
                }
            }
        });


        // Thread-2 ALSO acquires Lock-1 first,
        // and then tries to acquire Lock-2.
        //
        // This is the important change.
        Thread thread2 = new Thread(() -> {

            synchronized (lock1) {

                System.out.println(
                        "Thread-2 acquired Lock-1"
                );

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println(
                        "Thread-2 is waiting for Lock-2"
                );

                synchronized (lock2) {

                    System.out.println(
                            "Thread-2 acquired Lock-2"
                    );

                    System.out.println(
                            "Thread-2 completed its work"
                    );
                }
            }
        });


        // Start both threads.
        thread1.start();
        thread2.start();
    }
}