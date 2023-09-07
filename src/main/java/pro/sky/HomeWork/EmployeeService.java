package pro.sky.HomeWork;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork.exception.EmployeeNotFoundException;
import pro.sky.HomeWork.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {
    int maxEmployee = 10;
    Map<String, Employee> employees = new HashMap<>();

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Превышен лимит");
        } else if (employees.containsKey(firstName + " " + lastName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else {
            Employee e = new Employee(firstName, lastName);
            employees.put(firstName + " " + lastName, e);
            return e;
        }
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (employees.containsKey(firstName + " " + lastName)) {
            employees.remove(firstName + " " + lastName);
            return e;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (employees.containsKey(firstName + " " + lastName)) {
            return e;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Map<String, Employee> getAllEmployee() {
        return employees;
    }
}

