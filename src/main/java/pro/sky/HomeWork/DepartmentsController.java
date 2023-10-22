package pro.sky.HomeWork;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/department")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }


    @GetMapping(path = "/{departmentId}/salary/min") //Минимальная зарплата в отделе
    public Employee findMinSalaryInDepartment(@PathVariable Integer departmentId) {
        return departmentsService.findMinSalaryInDepartment(departmentId);
    }

    @GetMapping(path = "/{departmentId}/salary/max") //Максимальная зарплата в отделе
    public Employee findMaxSalaryInDepartment(@PathVariable Integer departmentId) {
        return departmentsService.findMaxSalaryInDepartment(departmentId);
    }

    @GetMapping(path = "/{departmentId}/employees") //Все сотрудники отдела
    public List<Employee> getAllEmployeeInDepartment(@PathVariable Integer departmentId) {
        return departmentsService.getAllEmployeeInDepartment(departmentId);
    }

    @GetMapping(path = "/employees") //Все сотрудники по отделам
    public Map<Integer, List<Employee>> getAllEmployeeInDepartment() {
        return departmentsService.getAllEmployeeInDepartment();
    }

    @GetMapping(path = "/{departmentId}/salary/sum") //Сумма затрат в отделе
    public Double findSumSalaryInDepartment(@PathVariable Integer departmentId) {
        return departmentsService.findSumSalaryInDepartment(departmentId);
    }
}