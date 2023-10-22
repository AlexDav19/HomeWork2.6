package pro.sky.HomeWork;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.HomeWork.exception.*;

import java.util.*;

@Service
public class EmployeeService {
    int maxEmployee = 6;
    private final Map<String, Employee> employees = new HashMap<>();
    //private final Departments departments;

//    public EmployeeService(Departments departments) {
//        this.departments = departments;
//    }

//    public Map<String, Employee> getEmployees() {
//        return employees;
//    }

    //Добавить сотрудника
    public Employee addEmployee(String firstName, String lastName, Integer departmentId, double salary) {
        validateInput(firstName, lastName);
        if (employees.size() >= maxEmployee) {
            throw new EmployeeStorageIsFullException("Превышен лимит");
        } else if (employees.containsKey(firstName + " " + lastName)) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже есть");
//        } else if (!departments.getDepartments().contains(departmentId)) {
//            throw new DepartmentNotFoundException("Нет такого отдела");
        } else {
            Employee e = new Employee(firstName, lastName, departmentId, salary);
            employees.put(firstName + " " + lastName, e);
            return e;
        }
    }

    //Удалить сотрудника
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        findEmployee(firstName, lastName);      //проверка есть ли такой сотрудник
        Employee result = employees.get(firstName + " " + lastName);    //Сохранение, чтобы вернуть после удаления
        employees.remove(firstName + " " + lastName);
        return result;
    }

    //Найти сотрудника
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);
        if (employees.containsKey(firstName + " " + lastName)) {
            return employees.get(firstName + " " + lastName);
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    //Показать всех сотрудников
    public Map<String, Employee> getAllEmployee() {
        return employees;
    }

    //Проверка имени и фамилии
    public void validateInput(String firstName, String lastName) {
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)) {
            throw new ValidationException("В имени и фамилии должны быть только буквы");
        }
    }
}