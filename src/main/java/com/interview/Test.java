package com.interview;

public class Test {

    public synchronized void m1() throws InterruptedException {
        m2();
        System.out.println("Test m1"+ Thread.currentThread().getName());
    }
    public synchronized void m2() throws InterruptedException {
        System.out.println("Test m2"+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Test test = new Test();
//        test.m1(); // Calls m1, which in turn calls m2
//        test.m2(); // Calls m2 directly

        Thread t1 = new Thread(() -> {
            try {
                test.m1();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught in thread t2: " + e.getMessage());
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                test.m2();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught in thread t2: " + e.getMessage());
            }
        });
        t1.start();
        t2.start();
    }
}
