package org.example;

public class NewThread extends Thread {

    @Override
    public void run() {

        // currentThread() returns the thread that is currently executing.
        System.out.println("Current Thread: "
                + Thread.currentThread().getName());

        // getName() returns the name of the current thread.
        System.out.println("Thread Name: " + getName());

        // getPriority() returns the priority of the thread.
        System.out.println("Thread Priority: " + getPriority());

        for (int i = 0; i <= 5; i++) {

            try {
                // sleep() pauses the CURRENT thread for 1 second.
                // During this time, the thread enters TIMED_WAITING state.
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                // interrupt() can interrupt a sleeping/waiting thread.
                System.out.println("Thread was interrupted.");
                return;
            }

            System.out.println(i);

            // getState() returns the current state of the thread.
            System.out.println("Thread State: " + getState());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        // currentThread() returns the main thread.
        Thread mainThread = Thread.currentThread();

        System.out.println("Main Thread Name: "
                + mainThread.getName());

        System.out.println("Main Thread State: "
                + mainThread.getState());


        // Creating the NewThread object.
        // The thread is in the NEW state because start()
        // has not been called yet.
        NewThread t1 = new NewThread();

        System.out.println("State before start: "
                + t1.getState());

        // setName() changes the name of the thread.
        t1.setName("My-Thread");

        System.out.println("Thread Name: "
                + t1.getName());

        // setPriority() changes the priority of the thread.
        // Priority range: 1 to 10.
        t1.setPriority(Thread.NORM_PRIORITY);//Priority is default by 5
        t1.setPriority(Thread.MAX_PRIORITY);//Priority is default by 10
        t1.setPriority(Thread.MIN_PRIORITY);//Priority is default by 1

        System.out.println("Thread Priority: "
                + t1.getPriority());


        // start() creates a NEW thread and executes run()
        // on that new thread.
        t1.start();

        System.out.println("State after start: "
                + t1.getState());


        // isAlive() returns true if the thread has started
        // and has not yet terminated.
        System.out.println("Is thread alive? "
                + t1.isAlive());


        // join() makes the MAIN thread wait until t1 finishes.
        //
        // The main thread enters the WAITING state while
        // waiting for t1 to complete.
        t1.join();


        // This statement executes after t1 has completed.
        System.out.println("Is thread alive after join? "
                + t1.isAlive());

        System.out.println("Final Thread State: "
                + t1.getState());

        // Create multiple thread objects.
        NewThread t2 = new NewThread();
        NewThread t3 = new NewThread();
        NewThread t4 = new NewThread();

        // Give each thread a different name.
        t2.setName("Low-Priority-Thread");
        t2.setName("Normal-Priority-Thread");
        t3.setName("High-Priority-Thread");

        // Set different priorities.
        t2.setPriority(Thread.MIN_PRIORITY);    // Priority = 1
        t3.setPriority(Thread.NORM_PRIORITY);   // Priority = 5
        t4.setPriority(Thread.MAX_PRIORITY);    // Priority = 10

        // Display the priorities.
        System.out.println(t1.getName() + " : " + t1.getPriority());
        System.out.println(t2.getName() + " : " + t2.getPriority());
        System.out.println(t3.getName() + " : " + t3.getPriority());
        System.out.println(t4.getName() + " : " + t4.getPriority());

        System.out.println("\nStarting threads...\n");

        // Start all three threads.
        t2.start();
        t3.start();
        t4.start();

        // Main thread waits until all three threads finish.
        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nAll threads completed.");
    }
}


        // Thread ID.
        // For modern Java, threadId() is preferred.