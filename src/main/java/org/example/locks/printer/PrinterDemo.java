package org.example.locks.printer;

public class PrinterDemo {

    public static void main(String[] args) throws InterruptedException {


//               Employee-1
//                  ↓
//              🔒 LOCK
//                  ↓
//              Print Report.pdf
//                  ↓
//                Page 1
//                Page 2
//                Page 3
//                  ↓
//              🔓 UNLOCK
//                  ↓
//              Employee-2
//                  ↓
//              🔒 LOCK
//                  ↓
//          Print Resume.pdf
//                  ↓
//                Page 1
//                Page 2
//                Page 3
//                  ↓
//               🔓 UNLOCK

        // One shared printer.
        Printer printer = new Printer();

        // Employee 1 wants to print.
        Thread employee1 = new Thread(() -> {

            printer.printDocument(
                    "Employee-1",
                    "Report.pdf"
            );

        });

        // Employee 2 wants to print.
        Thread employee2 = new Thread(() -> {

            printer.printDocument(
                    "Employee-2",
                    "Resume.pdf"
            );

        });

        // Employee 3 wants to print.
        Thread employee3 = new Thread(() -> {

            printer.printDocument(
                    "Employee-3",
                    "Presentation.pdf"
            );

        });

        // Start all employees.
        employee1.start();
        employee2.start();
        employee3.start();

        // Wait for all employees to finish.
        employee1.join();
        employee2.join();
        employee3.join();

        System.out.println("All documents printed.");
    }
}