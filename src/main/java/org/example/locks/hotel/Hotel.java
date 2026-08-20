package org.example.locks.hotel;

import java.util.concurrent.locks.ReentrantLock;

public class Hotel {

    // Shared resource
    private boolean roomAvailable = true;

    // Explicit lock
    private final ReentrantLock lock = new ReentrantLock();

    public void bookRoom(String customer) {

        // Acquire the lock.
        // If another customer is already checking/book the room,
        // this customer waits here.
        lock.lock();

        try {

            System.out.println(
                    customer + " is checking room availability..."
            );

            // Simulate booking/checking time.
            Thread.sleep(1000);

            if (roomAvailable) {

                System.out.println(
                        customer + " found the room available."
                );

                // Book the room.
                roomAvailable = false;

                System.out.println(
                        "Room successfully booked by " + customer
                );

            } else {

                System.out.println(
                        "Sorry " + customer
                                + ", room is already booked."
                );
            }

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

        } finally {

            // Always release the lock.
            lock.unlock();

            System.out.println(
                    customer + " released the hotel booking lock.\n"
            );
        }
    }
}