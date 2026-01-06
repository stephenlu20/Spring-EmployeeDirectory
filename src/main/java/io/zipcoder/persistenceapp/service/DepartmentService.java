package io.zipcoder.persistenceapp.service;

import io.zipcoder.persistenceapp.entity.*;
import io.zipcoder.persistenceapp.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final EmployeeRepository employeeRepo;

    public DepartmentService(DepartmentRepository d, EmployeeRepository e) {
        this.departmentRepo = d;
        this.employeeRepo = e;
    }

    public Department createDepartment(Long num, String name) {
        return departmentRepo.save(new Department(num, name, null));
    }

    public Department setManager(Long deptNum, Long empId) {
        Department d = departmentRepo.findById(deptNum).orElseThrow();
        Employee e = employeeRepo.findById(empId).orElseThrow();

        d.setManager(e);
        e.setDepartment(d);
        employeeRepo.save(e);

        return departmentRepo.save(d);
    }

    public Department rename(Long deptNum, String name) {
        Department d = departmentRepo.findById(deptNum).orElseThrow();
        d.setName(name);
        return departmentRepo.save(d);
    }
}
