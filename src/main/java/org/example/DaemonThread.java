package org.example;

public class DaemonThread extends Thread {

    @Override
    public void run() {

        //        HOTEL / JAVA APPLICATION
//                |
//              ┌───────────┴───────────┐
//              ↓                       ↓
//        MAIN THREAD              DAEMON THREAD
//        Guest                  Housekeeping
//              |                       |
//        Important work          Background work
//              |                       |
//              ↓                       ↓
//        Finishes             Keeps working...
//              |
//              ↓
//        No user threads left
//              |
//              ↓
//        JVM exits
//              |
//              ↓
//        Daemon thread also stops



        // REAL-LIFE EXAMPLE:
        //
        // Imagine this program is a HOTEL.
        //
        // The main/user thread represents the GUEST.
        // The daemon thread represents HOUSEKEEPING.
        //
        // Guest:
        //     Performs the important work of the hotel.
        //
        // Housekeeping:
        //     Works in the background by cleaning rooms,
        //     collecting trash, checking supplies, etc.
        //
        // Housekeeping is useful while the hotel is operating,
        // but we don't want to keep the hotel open just because
        // housekeeping is still working.
        //
        // Similarly, a daemon thread performs background work
        // while the main application is running.
        //
        // When all user/non-daemon threads finish,
        // the JVM can terminate without waiting for the daemon thread.

        for (int i = 0; i < 10; i++) {

            // This represents the background work being performed
            // by the housekeeping/daemon thread.
            System.out.println(
                    "Daemon Thread: " + i
            );

            try {

                // The housekeeping thread pauses for 1 second
                // before performing its next background task.
                Thread.sleep(1000);

            } catch (InterruptedException e) {

                // If the daemon thread is interrupted,
                // we stop its background work.
                System.out.println("Daemon thread interrupted.");
                return;
            }
        }
    }

    public static void main(String[] args) {

        // Create a thread object.
        //
        // Think of this as creating the HOTEL HOUSEKEEPING worker.
        DaemonThread t1 = new DaemonThread();


        // Set the thread as a DAEMON thread.
        //
        // This MUST be done BEFORE start().
        //
        // A daemon thread performs background/supporting work
        // and does not keep the JVM alive.
        t1.setDaemon(true);


        // Check whether t1 is a daemon thread.
        //
        // Output:
        // true
        System.out.println("Is Daemon: " + t1.isDaemon());


        // Start the daemon thread.
        //
        // The daemon thread starts performing its background work.
        //
        // In our hotel example:
        // Housekeeping starts working in the background.
        t1.start();


        // The main thread represents the important work of the hotel.
        //
        // Once the main/user thread finishes and there are no
        // other non-daemon threads running, the JVM can terminate.
        //
        // The JVM does NOT wait for the daemon thread to finish.
        //
        // Therefore, the daemon thread may stop before printing
        // all 10 values.

        System.out.println("Main thread finished.");
    }
}