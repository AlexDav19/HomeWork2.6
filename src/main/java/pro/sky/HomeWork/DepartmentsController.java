package pro.sky.HomeWork;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentsController {
    private final DepartmentsService departmentsService;

    public DepartmentsController(DepartmentsService departmentsService) {
        this.departmentsService = departmentsService;
    }


    @GetMapping(path = "/min-salary")
    public Optional<Employee> findMinSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentsService.findMinSalaryInDepartment(departmentId);
    }

    @GetMapping(path = "/max-salary")
    public Optional<Employee> findMaxSalaryInDepartment(@RequestParam("departmentId") Integer departmentId) {
        return departmentsService.findMaxSalaryInDepartment(departmentId);
    }

    @GetMapping(path = "/all")
    public List<Employee> getAllEmployeeInDepartment(@RequestParam(required = false) Integer departmentId) {
        return departmentsService.getAllEmployeeInDepartment(departmentId);
    }
}