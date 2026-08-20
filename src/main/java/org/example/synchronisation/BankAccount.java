package org.example.synchronisation;

public class BankAccount {

    private int balance = 10000;

    // synchronized ensures that only one thread
    // can execute the withdraw() method at a time
    // on the same BankAccount object.
    public synchronized void withdraw(int amount) {

        System.out.println(
                Thread.currentThread().getName()
                        + " is trying to withdraw ₹"
                        + amount
        );

        // Check whether sufficient balance is available.
        if (balance >= amount) {

            System.out.println(
                    Thread.currentThread().getName()
                            + " found sufficient balance."
            );

            // Simulate some processing time.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            balance = balance - amount;

            System.out.println(
                    Thread.currentThread().getName()
                            + " successfully withdrew ₹"
                            + amount
            );

            System.out.println(
                    "Remaining Balance: ₹" + balance
            );

        } else {

            System.out.println(
                    Thread.currentThread().getName()
                            + " cannot withdraw ₹"
                            + amount
                            + " because balance is insufficient."
            );
        }
    }

    public int getBalance() {
        return balance;
    }
}