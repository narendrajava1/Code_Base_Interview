package com.interview.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2); // Allow 2 threads to access the resource at a time.

        Runnable task = () -> {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + " acquired a permit.");
                Thread.sleep(1000); // Simulate work
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " released a permit.");
                semaphore.release();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}