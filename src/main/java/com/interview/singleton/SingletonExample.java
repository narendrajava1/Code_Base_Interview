package com.interview.singleton;

public class SingletonExample {

    // Private constructor to prevent instantiation
    private SingletonExample() {
        System.out.println("Singleton instance created");
    }
        private static final SingletonExample INSTANCE = new SingletonExample();

    // Static inner class responsible for holding the Singleton instance
    private static class SingletonHelper {
    }

    // Public method to provide access to the Singleton instance
    public static SingletonExample getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        // Access the singleton instance
        SingletonExample instance1 = SingletonExample.getInstance();
        SingletonExample instance2 = SingletonExample.getInstance();

        // Verify that both references point to the same instance
        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }
}