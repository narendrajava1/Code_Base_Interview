package com.interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SecondHighestSalary {

    static List<Employee> employees = Arrays.asList(
            new Employee("Alice", "IT", 70000),
            new Employee("Bob", "HR", 50000),
            new Employee("Charlie", "IT", 80000),
            new Employee("David", "Finance", 90000),
            new Employee("Eva", "HR", 60000)
    );

    public static void main(String[] args) {
        //second highest salary
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).forEach(System.out::println);
        System.out.println("====================================");
        //group by deapartment and get the highest salary in each department
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment))
                .forEach((department, empList) -> System.out.println(department + ": " + empList));

        // Group by and get average salary:
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingInt(Employee::getSalary))).forEach((department, avgSalary) -> {
            System.out.println(department + ": " + avgSalary);
        });

        //Optional Example
       String name = Optional.ofNullable((String) null).orElse("Default");
       System.out.println(name);
    }
}

class Employee {
    private String name;
    private String department;
    private int salary;

    // Constructor
    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
