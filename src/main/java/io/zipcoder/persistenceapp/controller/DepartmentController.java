package io.zipcoder.persistenceapp.controller;

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
    public Department create(@RequestParam Long num, @RequestParam String name) {
        return service.createDepartment(num, name);
    }

    @PutMapping("/{dept}/manager/{emp}")
    public Department setManager(@PathVariable Long dept, @PathVariable Long emp) {
        return service.setManager(dept, emp);
    }

    @PutMapping("/{dept}/rename")
    public Department rename(@PathVariable Long dept, @RequestParam String name) {
        return service.rename(dept, name);
    }
}
