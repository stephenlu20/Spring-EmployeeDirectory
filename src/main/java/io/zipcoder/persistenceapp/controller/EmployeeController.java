package io.zipcoder.persistenceapp.controller;

import io.zipcoder.persistenceapp.dto.*;
import io.zipcoder.persistenceapp.entity.Employee;
import io.zipcoder.persistenceapp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/API/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService s) {
        this.service = s;
    }

    @PostMapping
    public Employee create(@RequestBody EmployeeCreateRequest req) {
        return service.createEmployee(req);
    }

    @PutMapping("/{empId}/manager/{mgrId}")
    public Employee assignManager(@PathVariable Long empId, @PathVariable Long mgrId) {
        return service.assignManager(empId, mgrId);
    }

    @GetMapping("/manager/{mgrId}")
    public List<Employee> underManager(@PathVariable Long mgrId) {
        return service.getEmployeesUnderManager(mgrId);
    }

    @GetMapping("/unmanaged")
    public List<Employee> unmanaged() {
        return service.getUnmanagedEmployees();
    }

    @GetMapping("/{id}/hierarchy")
    public List<Employee> hierarchy(@PathVariable Long id) {
        return service.getManagerChain(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }
}
