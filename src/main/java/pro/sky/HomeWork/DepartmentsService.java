package pro.sky.HomeWork;

import org.springframework.stereotype.Service;
import pro.sky.HomeWork.exception.DepartmentNotFoundException;
import pro.sky.HomeWork.exception.EmployeeNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentsService {
    private final EmployeeService employeeService;
    private final Departments departments;

    public DepartmentsService(EmployeeService employeeService, Departments departments) {
        this.employeeService = employeeService;
        this.departments = departments;
    }

    //Сотрудника с минимальной зарплатой в отделе.
    public Employee findMinSalaryInDepartment(Integer departmentId) {
        checkDepartment(departmentId);
        return employeeService.getEmployees().values().stream()
                .filter(employee -> departmentId == employee.getDepartment())
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    //Сотрудника с максимальной зарплатой в отделе.
    public Employee findMaxSalaryInDepartment(Integer departmentId) {
        checkDepartment(departmentId);
        return employeeService.getEmployees().values().stream()
                .filter(employee -> departmentId == employee.getDepartment())
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(() -> new EmployeeNotFoundException("Сотрудник не найден"));
    }

    //Напечатать всех сотрудников отдела или по отделам.
    public List<Employee> getAllEmployeeInDepartment(Integer departmentId) {
        checkDepartment(departmentId);
        return employeeService.getEmployees().values().stream()
                .filter(employee -> departmentId == employee.getDepartment())
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> getAllEmployeeInDepartment() {
        return employeeService.getEmployees().values().stream().sorted(Comparator.comparingInt(Employee::getDepartment))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    //Проверка правильности отдела
    public void checkDepartment(Integer departmentId) {
        if (departmentId < 1 || departmentId > departments.getDepartments().size()) {
            throw new DepartmentNotFoundException("Нет такого отдела");
        }
    }
}
