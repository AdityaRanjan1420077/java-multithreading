package org.example.synchronisation;

public class BankAccountDemo {

    public static void main(String[] args) throws InterruptedException {


//                      Person-1
//                          │
//                          ↓
//                      🔒 LOCK
//                          │
//                  Check balance ₹10,000
//                          │
//                  Withdraw ₹7,000
//                          │
//                  Balance = ₹3,000
//                          │
//                      🔓 UNLOCK
//                          │
//                          ↓
//                      Person-2
//                          │
//                          ↓
//                      🔒 LOCK
//                          │
//                  Check balance ₹3,000
//                          │
//                 ₹7,000 not available
//                          │
//                  Transaction rejected

        // One shared bank account.
        BankAccount account = new BankAccount();

        // Person 1 tries to withdraw ₹7,000.
        Thread person1 = new Thread(() -> {
            account.withdraw(7000);
        }, "Person-1");

        // Person 2 also tries to withdraw ₹7,000.
        Thread person2 = new Thread(() -> {
            account.withdraw(7000);
        }, "Person-2");

        // Both people try to withdraw at almost the same time.
        person1.start();
        person2.start();

        // Main thread waits for both transactions to finish.
        person1.join();
        person2.join();

        // Display final balance.
        System.out.println(
                "Final Balance: ₹" + account.getBalance()
        );
    }


    //Why Synchronisation
//                  BankAccount
//                       │
//                       ↓
//                     balance
//                       ↑
//                       │
//                   ┌───┴────┐
//                   │        │
//                Person 1  Person 2
}