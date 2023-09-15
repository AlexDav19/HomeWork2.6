package pro.sky.HomeWork;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork.exception.DepartmentNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {
    EmployeeService employeeService = new EmployeeService();
    Departments departments = new Departments();

    //Сотрудника с минимальной зарплатой в отделе.
    public Optional<Employee> findMinSalaryInDepartment(Integer departmentId) {
        checkDepartment(departmentId);
        return employeeService.employees.values().stream()
                .filter(employee -> departmentId == employee.getDepartment())
                .min(Comparator.comparingDouble(Employee::getSalary));
    }

    //Сотрудника с максимальной зарплатой в отделе.
    public Optional<Employee> findMaxSalaryInDepartment(Integer departmentId) {
        checkDepartment(departmentId);
        return employeeService.employees.values().stream()
                .filter(employee -> departmentId == employee.getDepartment())
                .max(Comparator.comparingDouble(Employee::getSalary));
    }

    //Напечатать всех сотрудников отдела или по отделам.
    public List<Employee> getAllEmployeeInDepartment(Integer departmentId) {
        List<Employee> result;
        if (departmentId == null) {
            result = employeeService.employees.values().stream().sorted(Comparator.comparingInt(Employee::getDepartment))
                    .collect(Collectors.toList());

        } else {
            checkDepartment(departmentId);
            result = employeeService.employees.values().stream()
                    .filter(employee -> departmentId == employee.getDepartment())
                    .collect(Collectors.toList());
        }
        return result;
    }

    //Проверка правильности отдела
    public void checkDepartment(Integer departmentId) {
        if (departmentId < 1 || departmentId > departments.getDepartments().size()) {
            throw new DepartmentNotFoundException("Нет такого отдела");
        }
    }
}
