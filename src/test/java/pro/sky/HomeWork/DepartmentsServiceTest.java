package pro.sky.HomeWork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.HomeWork.exception.EmployeeNotFoundException;
import pro.sky.HomeWork.utils.EmployeeGenerator;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentsServiceTest {

    @Mock
    private EmployeeService employeeService;
//    private Departments departments;

    @InjectMocks
    private DepartmentsService departmentsService;
    EmployeeGenerator employeeGenerator = new EmployeeGenerator();

    @Test
    void findMinSalaryInDepartment_success() {
        Integer departmentId = 1;
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        Employee expectedEmployee = employeeGenerator.employees.get("Sergey Bert");
        Employee actualEmployee = departmentsService.findMinSalaryInDepartment(departmentId);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void findMinSalaryInDepartment_withNotFoundException() {
        Integer departmentId = 3;
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        String expectedMessage = "Сотрудник не найден";
        Exception actualException = Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentsService.findMinSalaryInDepartment(departmentId));
        Assertions.assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    void findMaxSalaryInDepartment_success() {
        Integer departmentId = 1;
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        Employee expectedEmployee = employeeGenerator.employees.get("Michael lastName");
        Employee actualEmployee = departmentsService.findMaxSalaryInDepartment(departmentId);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void findMaxSalaryInDepartment_withNotFoundException() {
        Integer departmentId = 3;
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        String expectedMessage = "Сотрудник не найден";
        Exception actualException = Assertions.assertThrows(EmployeeNotFoundException.class,
                () -> departmentsService.findMaxSalaryInDepartment(departmentId));
        Assertions.assertEquals(expectedMessage, actualException.getMessage());
    }

    @Test
    void getAllEmployeeInDepartment_withDepartmentId() {
        Integer departmentId = 4;
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        List<Employee> expectedList = new ArrayList<>(Arrays.asList(employeeGenerator.employees.get("Bob Bond"),
                employeeGenerator.employees.get("Jon sd")));

        Assertions.assertEquals(expectedList, departmentsService.getAllEmployeeInDepartment(departmentId));
    }

    @Test
    void getAllEmployeeInDepartment() {
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        expectedMap.put(1, Arrays.asList(employeeGenerator.employees.get("Michael lastName"),
                employeeGenerator.employees.get("Alex Davletov"),
                employeeGenerator.employees.get("Sergey Bert")));
        expectedMap.put(4, Arrays.asList(employeeGenerator.employees.get("Bob Bond"),
                employeeGenerator.employees.get("Jon sd")));
        expectedMap.put(5, Collections.singletonList(employeeGenerator.employees.get("Ken Durg")));

        Map<Integer, List<Employee>> actualMap = departmentsService.getAllEmployeeInDepartment();

        Assertions.assertEquals(expectedMap, actualMap);
    }

    @Test
    void findSumSalaryInDepartment() {
        Integer departmentId = 4;
        when(employeeService.getAllEmployee()).thenReturn(employeeGenerator.getAll());
        Double expectedSum = employeeGenerator.employees.get("Bob Bond").getSalary() +
                employeeGenerator.employees.get("Jon sd").getSalary();
        Double actualSum = departmentsService.findSumSalaryInDepartment(departmentId);

        Assertions.assertEquals(expectedSum, actualSum);
    }
}
