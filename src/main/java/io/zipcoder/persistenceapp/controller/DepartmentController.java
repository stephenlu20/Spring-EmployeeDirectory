package io.zipcoder.persistenceapp.controller;

import io.zipcoder.persistenceapp.dto.DepartmentCreateRequest;
import io.zipcoder.persistenceapp.entity.Department;
import io.zipcoder.persistenceapp.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/API/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService s) {
        this.service = s;
    }

    @PostMapping
    public Department create(@RequestBody DepartmentCreateRequest req) {
        return service.createDepartment(req.departmentNumber, req.name);
    }

    @PutMapping("/{deptNum}/manager/{empId}")
    public Department setManager(@PathVariable Long deptNum, @PathVariable Long empId) {
        return service.setManager(deptNum, empId);
    }

    @PutMapping("/{deptNum}/rename")
    public Department rename(@PathVariable Long deptNum, @RequestParam String name) {
        return service.rename(deptNum, name);
    }
}
