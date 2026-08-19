package org.example;

public class MyThread extends Thread {

    @Override
    public void run() {

        // This method is executed by the newly created thread
        // after the start() method is called.
        //
        // Java does not have a separate RUNNING state.
        // When a thread is running or ready to run,
        // Java represents it using the RUNNABLE state.
        System.out.println("RUNNING");

        try {

            // sleep(2000) pauses the current thread for 2 seconds.
            //
            // The current thread (t1) enters the TIMED_WAITING state
            // because it is waiting for a specified amount of time.
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // After 2 seconds, the sleep() method finishes.
        //
        // The thread becomes RUNNABLE again and continues execution.
        //
        // super.run() does nothing here because Thread's default
        // run() method does not contain any task.
        super.run();
    }

    public static void main(String[] args) throws InterruptedException {

        // Create a Thread object.
        //
        // The thread object has been created,
        // but start() has NOT been called yet.
        //
        // Therefore, the thread is in the NEW state.
        MyThread t1 = new MyThread();

        // getState() returns the current state of the thread.
        //
        // Since t1 has not been started yet:
        // Output: NEW
        System.out.println(t1.getState());


        // start() starts a new thread.
        //
        // The JVM creates a new execution path and
        // eventually calls the run() method of t1.
        //
        // After start(), t1 enters the RUNNABLE state.
        t1.start();


        // The exact state here is not guaranteed.
        //
        // t1 may be:
        // RUNNABLE       -> if it is ready/running
        // TIMED_WAITING  -> if it has already reached sleep(2000)
        //
        // This depends on the thread scheduler.
        System.out.println(t1.getState());


        // Thread.currentThread() returns the thread that is
        // currently executing this code.
        //
        // Here, this code is inside main(),
        // so the current thread is the MAIN thread.
        //
        // The main thread is normally in the RUNNABLE state
        // while executing this statement.
        //
        // NOTE:
        // Java does not have a separate RUNNING state.
        System.out.println(Thread.currentThread().getState());


        // The MAIN thread sleeps for 200 milliseconds.
        //
        // Therefore, the MAIN thread enters the
        // TIMED_WAITING state for 200 milliseconds.
        //
        // This does NOT put t1 into TIMED_WAITING.
        // It puts the MAIN thread into TIMED_WAITING.
        Thread.sleep(200);


        // After the main thread wakes up,
        // it checks the state of t1.
        //
        // Since t1 is sleeping for 2000 milliseconds,
        // t1 will most likely still be in TIMED_WAITING.
        System.out.println(t1.getState());


        // join() makes the CURRENT thread wait until t1 finishes.
        //
        // Since join() is called by the main thread:
        //
        // MAIN THREAD → WAITING
        //
        // The main thread waits here until t1 completes
        // its run() method.
        t1.join();


        // join() returns only after t1 has completed.
        //
        // Once run() finishes, t1 enters the TERMINATED state.
        //
        // Therefore, this prints:
        // TERMINATED
        System.out.println(t1.getState());
    }
}