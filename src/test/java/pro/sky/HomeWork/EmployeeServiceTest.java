package pro.sky.HomeWork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pro.sky.HomeWork.exception.EmployeeAlreadyAddedException;
import pro.sky.HomeWork.exception.EmployeeNotFoundException;
import pro.sky.HomeWork.exception.EmployeeStorageIsFullException;
import pro.sky.HomeWork.exception.ValidationException;
import pro.sky.HomeWork.utils.EmployeeGenerator;

import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService();
    private final EmployeeGenerator employeeGenerator = new EmployeeGenerator();

    String firstNameCorrect = "Alex";
    String firstNameIllegal = "Alex123";
    String lastNameCorrect = "Davletov";
    String lastNameIllegal = "D avletov";
    int departmentCorrect = 1;
    double salaryCorrect = 50_000;

    @Test
    public void addEmployeeTest_success() {
        Employee expected = new Employee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        Employee actual = employeeService.addEmployee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void addEmployeeTest_IsFullException() {
        String firstName = "";
        String lastName = "";
        for (int i = 0; i < employeeService.maxEmployee; i++) {
            firstName += "A";
            lastName += "B";
            employeeService.addEmployee(firstName, lastName, 1, 10);
        }
        Assertions.assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(
                firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect));
    }

    @Test
    public void addEmployeeTest_AlreadyAddedException() {
        employeeService.addEmployee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(
                firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect));
    }

    @Test
    public void removeEmployeeTest_success() {
        Employee expectedEmployee = new Employee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        employeeService.addEmployee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        Employee actualEmployee = employeeService.removeEmployee(firstNameCorrect, lastNameCorrect);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }


    @Test
    public void findEmployeeTest_success() {
        Employee expectedEmployee = new Employee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        employeeService.addEmployee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        Employee actualEmployee = employeeService.findEmployee(firstNameCorrect, lastNameCorrect);
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    public void findEmployeeTest_NotFoundException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee(firstNameCorrect, lastNameCorrect));
    }

    @Test
    public void getAllEmployeeTest_success() {
        Map<String, Employee> expectedEmployee = new HashMap<>();
        expectedEmployee.put(firstNameCorrect + " " + lastNameCorrect,
                new Employee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect));
        employeeService.addEmployee(firstNameCorrect, lastNameCorrect, departmentCorrect, salaryCorrect);
        Map<String, Employee>  actualEmployee = employeeService.getAllEmployee();
        Assertions.assertEquals(expectedEmployee, actualEmployee);
    }


    @Test
    public void validateInputTest_success() {
        Assertions.assertDoesNotThrow(() -> employeeService.validateInput(firstNameCorrect, lastNameCorrect));
    }

    @Test
    public void validateInputTest_withValidationException() {
        String expectedMessage = "В имени и фамилии должны быть только буквы";
        Exception exception1 = Assertions.assertThrows(ValidationException.class, () -> employeeService
                .validateInput(firstNameIllegal, lastNameCorrect));
        Assertions.assertEquals(expectedMessage, exception1.getMessage());

        Exception exception2 = Assertions.assertThrows(ValidationException.class, () -> employeeService
                .validateInput(firstNameCorrect, lastNameIllegal));
        Assertions.assertEquals(expectedMessage, exception2.getMessage());

        Exception exception3 = Assertions.assertThrows(ValidationException.class, () -> employeeService
                .validateInput(firstNameIllegal, lastNameIllegal));
        Assertions.assertEquals(expectedMessage, exception3.getMessage());
    }

}
