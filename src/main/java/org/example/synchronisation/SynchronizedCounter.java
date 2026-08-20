package org.example.synchronisation;

public class SynchronizedCounter {

    private int count = 0;

    // synchronized ensures that only one thread at a time
    // can execute this method on the same object.
    //
    // This prevents multiple threads from modifying
    // count at the same time.
    public synchronized void increment() {
        count++;
    }

    // Returns the current value of count.
    public int getCount() {
        return count;
    }
}