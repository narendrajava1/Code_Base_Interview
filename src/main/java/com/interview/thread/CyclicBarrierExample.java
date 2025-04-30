package com.interview.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
//What: A CyclicBarrier is a synchronization aid that allows a set of threads to wait for each other to reach a common barrier point. Once all threads reach the barrier, they can proceed.
//Why: It is useful when multiple threads need to perform a task in phases and must wait for all threads to complete a phase before moving to the next.
//Example Use Case: Simulating a relay race where all runners must wait at the starting line before beginning.
public class CyclicBarrierExample {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All threads reached the barrier"));

        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + " reached the barrier");
            try {
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        };

        new Thread(task).start();
        new Thread(task).start();
        new Thread(task).start();
    }
}