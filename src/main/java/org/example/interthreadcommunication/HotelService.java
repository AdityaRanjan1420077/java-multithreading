package org.example.interthreadcommunication;



public class HotelService {

    private boolean foodReady = false;

    // Customer waits for food.
    public synchronized void waitForFood(String customer) {

        while (!foodReady) {

            System.out.println(
                    customer + " is waiting for food..."
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

        System.out.println(
                customer + " received the food."
        );
    }

    // Room service prepares the food.
    public synchronized void prepareFood() {

        System.out.println(
                "Room Service is preparing food..."
        );

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        foodReady = true;

        System.out.println(
                "Room Service: Food is ready!"
        );

        // Wake up ALL customers waiting for food.
        notifyAll();
    }
}