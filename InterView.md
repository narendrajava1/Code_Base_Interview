## Core Java Interview Practice Guide

### 🔹 1. Object-Oriented Programming (OOP) - BankAccount Example

#### ✅ Theory

- **Encapsulation**: Keep data private and expose it via getters/setters.
- **Inheritance**: Allows a class to inherit properties/methods from another.
- **Polymorphism**: One method behaves differently based on context.
- **Abstraction**: Focus on "what" an object does, not "how".

#### 💻 Code Example

```java
public class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double initialDeposit) {
        this.owner = owner;
        this.balance = initialDeposit;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }
}
```

---

### 🔹 2. Strings - Palindrome Check

#### ✅ Theory

- A **palindrome** is a string that reads the same backward as forward.
- Use two-pointer technique for optimal performance.

#### 💻 Code Example

```java
public boolean isPalindrome(String input) {
    int left = 0, right = input.length() - 1;
    while (left < right) {
        if (input.charAt(left++) != input.charAt(right--)) {
            return false;
        }
    }
    return true;
}
```

---

### 🔹 3. Collections - Frequency Counter

#### ✅ Theory

- Use a `HashMap` to count occurrences.
- Useful for detecting duplicates or common patterns.

#### 💻 Code Example

```java
public Map<Integer, Integer> frequencyCount(int[] nums) {
    Map<Integer, Integer> freq = new HashMap<>();
    for (int num : nums) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }
    return freq;
}
```

---

### 🔹 4. Multithreading - Even/Odd Printer

#### ✅ Theory

- Use `synchronized` blocks with `wait()`/`notify()` for thread coordination.

#### 💻 Code Example

```java
class Printer {
    private boolean isEven = false;

    public synchronized void printEven(int number) throws InterruptedException {
        while (!isEven) wait();
        System.out.println("Even: " + number);
        isEven = false;
        notify();
    }

    public synchronized void printOdd(int number) throws InterruptedException {
        while (isEven) wait();
        System.out.println("Odd: " + number);
        isEven = true;
        notify();
    }
}
```

---

### 🔹 5. Java 8 - Stream Filtering

#### ✅ Theory

- Java 8 introduced Streams for declarative data processing.
- Use `filter`, `map`, `collect` for clean code.

#### 💻 Code Example

```java
List<String> names = List.of("Alice", "Bob", "Alex", "Amanda");
List<String> aNames = names.stream()
                           .filter(name -> name.startsWith("A"))
                           .collect(Collectors.toList());
```

---

### 🔹 6. JUnit + Mockito Testing

#### ✅ Theory

- **JUnit** is used for unit testing in Java.
- **Mockito** allows mocking dependencies.
- Use `@SpringBootTest` for integration tests.

#### 💻 Controller Unit Test Example

```java
@WebMvcTest(MyController.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyService myService;

    @Test
    public void testGetHello() throws Exception {
        Mockito.when(myService.getMessage()).thenReturn("Hello");

        mockMvc.perform(get("/hello"))
               .andExpect(status().isOk())
               .andExpect(content().string("Hello"));
    }
}
```

#### 💻 Service Layer Test Example

```java
@SpringBootTest
public class MyServiceTest {

    @Mock
    private MyRepository repository;

    @InjectMocks
    private MyService service;

    @Test
    public void testGetById() {
        MyEntity entity = new MyEntity(1, "test");
        Mockito.when(repository.findById(1)).thenReturn(Optional.of(entity));

        assertEquals("test", service.getById(1).getName());
    }
}
```

---

### 🔹 7. Design Patterns in Java

#### ✅ Singleton

- Ensures only one instance of a class exists.

```java
public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

#### ✅ Factory

- Creates objects without exposing creation logic to the client.

```java
public class ShapeFactory {
    public Shape getShape(String type) {
        if ("CIRCLE".equals(type)) return new Circle();
        if ("SQUARE".equals(type)) return new Square();
        return null;
    }
}
```

---

### 🔹 8. Sorting & Searching Algorithms

#### ✅ Binary Search

```java
public int binarySearch(int[] arr, int target) {
    int low = 0, high = arr.length - 1;
    while (low <= high) {
        int mid = (low + high) / 2;
        if (arr[mid] == target) return mid;
        else if (arr[mid] < target) low = mid + 1;
        else high = mid - 1;
    }
    return -1;
}
```

#### ✅ Bubble Sort

```java
public void bubbleSort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
        for (int j = 0; j < arr.length - 1 - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```

---

### 🔹 9. Functional Interfaces & Lambdas

#### ✅ Theory

- A **functional interface** has only one abstract method.
- Can be used with lambda expressions for cleaner code.

#### 💻 Code Example

```java
@FunctionalInterface
interface GreetingService {
    void sayHello(String name);
}

public class LambdaExample {
    public static void main(String[] args) {
        GreetingService greet = name -> System.out.println("Hello, " + name);
        greet.sayHello("Java");
    }
}
```

---

### 🔹 10. Fail-Fast vs Fail-Safe Collections

#### ✅ Fail-Fast

- `ArrayList`, `HashMap` are fail-fast.
- Structural modifications during iteration throw `ConcurrentModificationException`.

```java
List<String> names = new ArrayList<>(List.of("Alice", "Bob"));
for (String name : names) {
    if (name.equals("Bob")) {
        names.remove(name); // Throws ConcurrentModificationException
    }
}
```

#### ✅ Safe Iteration

```java
Iterator<String> iterator = names.iterator();
while (iterator.hasNext()) {
    if (iterator.next().equals("Bob")) {
        iterator.remove(); // Safe
    }
}
```

#### ✅ Fail-Safe

- `CopyOnWriteArrayList`, `ConcurrentHashMap` are fail-safe.
- Do not throw exceptions on concurrent modification.

```java
CopyOnWriteArrayList<String> safeList = new CopyOnWriteArrayList<>(List.of("Alice", "Bob"));
for (String name : safeList) {
    safeList.remove(name); // No exception
}
```

---

Let me know if you'd like examples of:

- Testcontainers or full integration tests
- Dynamic programming or backtracking algorithms



---

Let me know if you'd like examples of:

- Testcontainers or full integration tests
- Dynamic programming or backtracking algorithms

public boolean isPalindrome(String input) {
int left = 0, right = input.length() - 1;
while (left < right) {
if (input.charAt(left++) != input.charAt(right--)) {
return false;
}
}
return true;
}

This method checks if a given string is a palindrome (a word, phrase, or sequence that reads the same backward as forward).

### Explanation:
1. **Initialization**:
    - `left` starts at the beginning of the string (`0`).
    - `right` starts at the end of the string (`input.length() - 1`).

2. **While Loop**:
    - The loop runs as long as `left` is less than `right`, ensuring that the comparison stops when the middle of the string is reached.

3. **Character Comparison**:
    - `input.charAt(left++)` retrieves the character at the `left` index and increments `left` after the comparison.
    - `input.charAt(right--)` retrieves the character at the `right` index and decrements `right` after the comparison.
    - If the characters at `left` and `right` are not equal, the method returns `false` (the string is not a palindrome).

4. **Return Statement**:
    - If the loop completes without finding mismatched characters, the method returns `true`, indicating the string is a palindrome.

### Example:
For the input `"radar"`:
- `left = 0`, `right = 4`: Compare `'r'` and `'r'` → Match.
- `left = 1`, `right = 3`: Compare `'a'` and `'a'` → Match.
- `left = 2`, `right = 2`: Loop ends (middle reached).

The method returns `true` because all characters match symmetrically.

## Java8 features
Absolutely! Here are **Java 8 features explained with simple code examples**, each compared (where possible) to Java 7:

---

### 1. **Lambda Expressions**

#### Java 7:

```java
Runnable r = new Runnable() {
    public void run() {
        System.out.println("Running in Java 7");
    }
};
r.run();
```

#### Java 8:

```java
Runnable r = () -> System.out.println("Running in Java 8");
r.run();
```

---

### 2. **Functional Interfaces**

```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

public class Example {
    public static void main(String[] args) {
        Calculator calc = (a, b) -> a + b;
        System.out.println(calc.add(10, 20));
    }
}
```

---

### 3. **Streams API**

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.stream()
     .filter(name -> name.startsWith("A"))
     .forEach(System.out::println);  // Output: Alice
```

---

### 4. **Default & Static Methods in Interfaces**

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle started");
    }

    static void stop() {
        System.out.println("Vehicle stopped");
    }
}

class Car implements Vehicle {}

new Car().start();     // Vehicle started
Vehicle.stop();        // Vehicle stopped
```

---

### 5. **New Date & Time API**

```java
import java.time.*;

LocalDate date = LocalDate.now();
LocalTime time = LocalTime.now();
LocalDateTime dateTime = LocalDateTime.now();
System.out.println(date);     // e.g., 2025-05-07
System.out.println(time);     // e.g., 13:45:33
```

---

### 6. **Optional Class**

```java
Optional<String> name = Optional.of("John");
name.ifPresent(System.out::println);  // Output: John

String defaultName = name.orElse("Default");
```

---

### 7. **Nashorn JavaScript Engine**

```java
import javax.script.*;

ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
engine.eval("print('Hello from JavaScript')");
```

---

### 8. **Method References**

```java
List<String> names = Arrays.asList("A", "B", "C");
names.forEach(System.out::println);
```

---

### 9. **Collectors API**

```java
Map<String, Long> countByLetter = names.stream()
    .collect(Collectors.groupingBy(s -> s, Collectors.counting()));
```

---

### 10. **Parallel Streams**

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
list.parallelStream().forEach(System.out::println);  // Runs in parallel
```

---

Would you like these examples in a runnable mini project structure or explained in a downloadable PDF/markdown format?

Certainly! Let's break down **Dependency Injection (DI)** in Java with a focus on **Spring Framework**, which is widely used for implementing DI.

---

## 🔧 What is Dependency Injection?

**Dependency Injection** is a design pattern used to remove the hard-coded dependencies between objects, making code more flexible, testable, and maintainable.

### 💡 Core Idea:

Instead of a class creating its own dependencies, **they are injected from the outside** (usually by a framework like Spring).

---

## 🧱 Key Concepts

| Term                           | Meaning                                                                           |
| ------------------------------ | --------------------------------------------------------------------------------- |
| **Dependency**                 | An object that another object depends on                                          |
| **Injection**                  | Supplying the dependency from outside rather than creating it inside              |
| **IoC (Inversion of Control)** | The control of creating and managing dependencies is transferred to the framework |

---

## 📦 Types of Dependency Injection

| Type                      | Example                                        |
| ------------------------- | ---------------------------------------------- |
| **Constructor Injection** | Dependencies are passed via constructor        |
| **Setter Injection**      | Dependencies are set via public setters        |
| **Field Injection**       | Dependencies are injected directly into fields |

---

## 🔨 Constructor Injection (Recommended)

```java
@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

```java
@Component
public class Engine {
    public void start() {
        System.out.println("Engine started");
    }
}
```

---

## 🔧 Setter Injection

```java
@Component
public class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

---

## 🔩 Field Injection (not preferred for testability)

```java
@Component
public class Car {
    @Autowired
    private Engine engine;
}
```

---

## 🎯 Spring Configuration Example

```java
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(App.class, args);
        Car car = context.getBean(Car.class);
        car.start();
    }
}
```

---

## ✅ Why Use Dependency Injection?

| Benefit                    | Description                                                          |
| -------------------------- | -------------------------------------------------------------------- |
| **Decoupling**             | Reduces tight coupling between classes                               |
| **Easier Testing**         | Can inject mocks or stubs easily                                     |
| **Better Maintainability** | Changes in dependencies do not affect the dependent classes directly |
| **Code Reusability**       | More flexible and reusable components                                |

---

Would you like a small runnable Spring Boot demo project to practice constructor and setter injection?

// File: Application.java
package com.example.didemoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
public static void main(String[] args) {
SpringApplication.run(Application.class, args);
}
}

// File: Car.java
package com.example.didemoproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {
private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }
}

// File: Engine.java
package com.example.didemoproject.service;

import org.springframework.stereotype.Component;

@Component
public class Engine {
public void start() {
System.out.println("Engine started.");
}
}

// File: CarController.java
package com.example.didemoproject.controller;

import com.example.didemoproject.service.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private final Car car;

    @Autowired
    public CarController(Car car) {
        this.car = car;
    }

    @GetMapping("/start-car")
    public String startCar() {
        car.start();
        return "Car started successfully!";
    }
}
