package org.example;

public class Counter {


//                  Counter
//                 |
//              count = 0
//                 |
//       ┌─────────┴─────────┐
//       ↓                   ↓
//   Thread-1            Thread-2
//       │                   │
//       └──── increment ────┘
//                 ↓
//          Race Condition

    private int count = 0;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }



}