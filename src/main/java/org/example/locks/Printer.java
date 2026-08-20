package org.example.locks;

import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    // Explicit lock
    private final ReentrantLock lock = new ReentrantLock();

    public void printDocument(String employee, String document) {

        // Explicitly acquire the lock.
        // If another thread is already using the printer,
        // this thread waits here.
        lock.lock();

        try {

            System.out.println(
                    employee + " is using the printer."
            );

            // Simulate printing time.
            for (int i = 1; i <= 3; i++) {

                System.out.println(
                        employee + " printing "
                                + document
                                + " - Page " + i
                );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            System.out.println(
                    employee + " finished printing."
            );

        } finally {

            // Release the lock.
            //
            // This is important because other threads
            // are waiting to use the printer.
            lock.unlock();

            System.out.println(
                    employee + " released the printer.\n"
            );
        }
    }
}