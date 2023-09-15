package pro.sky.HomeWork;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork.exception.DepartmentNotFoundException;
import pro.sky.HomeWork.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork.exception.EmployeeNotFoundException;
import pro.sky.HomeWork.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeService {
    int maxEmployee = 10;
    Map<String, Employee> employees = new HashMap<>(Map.of(
            "1", new Employee("Alex", "Davletov", 1, 50000),
            "2", new Employee("Sergey", "Bert", 1, 20000),
            "3", new Employee("Ken", "Durg", 5, 10000),
            "4", new Employee("Bob", "Bond", 4, 40000),
            "5", new Employee("Jon", "sd", 4, 60000),
            "6", new Employee("Michael", "lastName", 1, 70000)));
    Departments departments = new Departments();

    //Добавить сотрудника
    public Employee addEmployee(String firstName, String lastName, Integer departmentId, double salary) {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Превышен лимит");
        } else if (employees.containsKey(firstName + " " + lastName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else if (!departments.getDepartments().contains(departmentId)) {
            throw new DepartmentNotFoundException("Нет такого отдела");
        } else {
            Employee e = new Employee(firstName, lastName, departmentId, salary);
            employees.put(firstName + " " + lastName, e);
            return e;
        }
    }

    //Удалить сотрудника
    public Employee removeEmployee(String firstName, String lastName) {
        findEmployee(firstName, lastName);      //проверка есть ли такой сотрудник
        Employee result = employees.get(firstName + " " + lastName);    //Сохранение, чтобы вернуть после удаления
        employees.remove(firstName + " " + lastName);
        return result;
    }

    //Найти сотрудника
    public Employee findEmployee(String firstName, String lastName) {
        if (employees.containsKey(firstName + " " + lastName)) {
            return employees.get(firstName + " " + lastName);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    //Показать всех сотрудников
    public Map<String, Employee> getAllEmployee() {
        return employees;
    }
}