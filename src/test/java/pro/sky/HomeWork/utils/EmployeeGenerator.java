package pro.sky.HomeWork.utils;

import pro.sky.HomeWork.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeGenerator {

    //Добавить позже

    public final Map<String, Employee> employees = new HashMap<>(Map.of(
            "Alex Davletov", new Employee("Alex", "Davletov", 1, 50000),
            "Sergey Bert", new Employee("Sergey", "Bert", 1, 20000),
            "Ken Durg", new Employee("Ken", "Durg", 5, 10000),
            "Bob Bond", new Employee("Bob", "Bond", 4, 40000),
            "Jon sd", new Employee("Jon", "sd", 4, 60000),
            "Michael lastName", new Employee("Michael", "lastName", 1, 70000)));

    public Map<String, Employee> getAll() {
        return employees;
    }
}
