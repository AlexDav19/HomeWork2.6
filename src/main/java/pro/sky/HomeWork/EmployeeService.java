package pro.sky.HomeWork;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork.exception.EmployeeNotFoundException;
import pro.sky.HomeWork.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    int maxEmployee = 10;
    List<Employee> employees = new ArrayList<>(List.of(new Employee("Иван", "Иванов")));

    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Превышен лимит");
        } else if (employees.contains(new Employee(firstName, lastName))) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
        } else {
            Employee e = new Employee(firstName, lastName);
            employees.add(e);
            return e;
        }
    }

    public Employee removeEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (employees.contains(e)) {
            employees.remove((Employee) e);
            return e;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee e = new Employee(firstName, lastName);
        if (employees.contains(e)) {
            System.out.println(employees.get(employees.indexOf(e)));
            return e;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public List<Employee> getAllEmployee() {
        return employees;
    }
}
