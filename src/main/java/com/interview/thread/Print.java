package com.interview.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Print {
    public static void main(String[] args) {
        Test test = new Test();
        Thread t1 = new Thread(() -> {
            test.print();
        });
        Thread t2 = new Thread(() -> {
            test.activate();
        });

        t1.start();
        t2.start();
    }

}

class Test{
    private boolean isActive = false;

    public synchronized void print() {
        while (!isActive) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Thread is running");
        isActive = false;
        notify();
    }

    public synchronized void activate() {
        isActive = true;
        notify();
    }

}

class Test1{
    private boolean isEven = false;

    public synchronized void  printEven(int number) throws InterruptedException{
         while(!isEven){
                wait();
         }
         System.out.println("Even Number: " + number);
         isEven=false;
         notify();
    }

    public synchronized void printOdd(int number) throws InterruptedException{
        while(isEven){
            wait();
        }
        System.out.println("Odd Number: " + number);
        isEven=true;
        notify();
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i += 2) { // Only even numbers
                try {
                    test1.printEven(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 1; i < 10; i += 2) { // Only odd numbers
                try {
                    test1.printOdd(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

}

class Test2{
private boolean isEven = false;

private final Lock lock = new ReentrantLock();
private final Condition evenCondition = lock.newCondition();
private final Condition oddCondition = lock.newCondition();


public  void  printEven(int number) throws InterruptedException{
    lock.lock();
    try {
        while(!isEven){
            evenCondition.await();
        }
        System.out.println("Even Number: " + number);
        isEven=false;
        oddCondition.signal();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    } finally {
        lock.unlock();
    }
}

public void printOdd(int number) throws InterruptedException{
    lock.lock();
    try {
        while(isEven){
            oddCondition.await();
        }
        System.out.println("Odd Number: " + number);
        isEven=true;
        evenCondition.signal();
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    } finally {
        lock.unlock();
    }
}

public static void main(String[] args) {
    Test2 test2 = new Test2();
    Thread t1 = new Thread(() -> {
        for (int i = 0; i < 10; i += 2) { // Only even numbers
            try {
                test2.printEven(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    Thread t2 = new Thread(() -> {
        for (int i = 1; i < 10; i += 2) { // Only odd numbers
            try {
                test2.printOdd(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    t1.start();
    t2.start();
}

}
