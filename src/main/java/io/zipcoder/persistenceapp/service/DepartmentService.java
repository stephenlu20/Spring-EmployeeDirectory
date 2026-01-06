package io.zipcoder.persistenceapp.service;

import io.zipcoder.persistenceapp.entity.*;
import io.zipcoder.persistenceapp.repository.*;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final DepartmentRepository deptRepo;
    private final EmployeeRepository empRepo;

    public DepartmentService(DepartmentRepository d, EmployeeRepository e) {
        this.deptRepo = d;
        this.empRepo = e;
    }

    public Department createDepartment(Long num, String name) {
        return deptRepo.save(new Department(num, name));
    }

    public Department setManager(Long deptNum, Long empId) {
        Department d = deptRepo.findById(deptNum).orElseThrow();
        Employee e = empRepo.findById(empId).orElseThrow();

        d.setManager(e);
        e.setDepartment(d);
        empRepo.save(e);

        return deptRepo.save(d);
    }

    public Department rename(Long deptNum, String name) {
        Department d = deptRepo.findById(deptNum).orElseThrow();
        d.setName(name);
        return deptRepo.save(d);
    }
}
