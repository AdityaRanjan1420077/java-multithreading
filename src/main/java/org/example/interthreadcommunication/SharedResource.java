package org.example.interthreadcommunication;

public class SharedResource {

    private boolean foodAvailable = false;

    // Producer calls this method to produce food
    public synchronized void produce() {

        System.out.println("Chef is preparing food...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        foodAvailable = true;

        System.out.println("Chef prepared the food.");

        // Notify the waiting consumer that food is available.
        notify();
    }

    // Consumer calls this method to consume food
    public synchronized void consume() {

        // If food is not available, consumer has to wait.
        while (!foodAvailable) {

            System.out.println(
                    "Waiter: No food available, waiting..."
            );

            try {
                // wait() releases the lock and puts
                // the current thread into WAITING state.
                wait();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Consumer wakes up after producer calls notify().
        System.out.println("Waiter: Food received!");

        foodAvailable = false;

        System.out.println("Waiter: Serving the food.");
    }
}