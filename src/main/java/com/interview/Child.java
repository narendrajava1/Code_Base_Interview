package com.interview;

public class Child extends Parent{
    @Override
    public void m1() {
        System.out.println("Child m1");
    }

    @Override
    public void m2() {
        System.out.println("Child m2");
    }

    public static void main(String[] args) {
        Parent p = new Parent();
        p.m1(); // Calls Child's m1
        p.m2(); // Calls Child's m2
    }
}
