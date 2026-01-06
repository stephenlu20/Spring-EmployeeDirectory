package io.zipcoder.persistenceapp.service;

import io.zipcoder.persistenceapp.entity.*;
import io.zipcoder.persistenceapp.repository.*;
import io.zipcoder.persistenceapp.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository e, DepartmentRepository d) {
        this.employeeRepository = e;
        this.departmentRepository = d;
    }

    public Employee createEmployee(EmployeeCreateRequest req) {
        Department dept = departmentRepository.findById(req.departmentNumber)
                .orElseThrow();

        Employee e = new Employee();
        e.setFirstName(req.firstName);
        e.setLastName(req.lastName);
        e.setTitle(req.title);
        e.setPhone(req.phone);
        e.setEmail(req.email);
        e.setHireDate(req.hireDate);
        e.setDepartment(dept);

        return employeeRepository.save(e);
    }

    public Employee assignManager(Long empId, Long mgrId) {
        Employee emp = employeeRepository.findById(empId).orElseThrow();
        Employee mgr = employeeRepository.findById(mgrId).orElseThrow();

        emp.setManager(mgr);
        emp.setDepartment(mgr.getDepartment());

        return employeeRepository.save(emp);
    }

    public List<Employee> getEmployeesUnderManager(Long mgrId) {
        Employee mgr = employeeRepository.findById(mgrId).orElseThrow();
        return employeeRepository.findByManager(mgr);
    }

    public List<Employee> getUnmanagedEmployees() {
        return employeeRepository.findByManagerIsNull();
    }

    public List<Employee> getDepartmentEmployees(Long deptNum) {
        Department d = departmentRepository.findById(deptNum).orElseThrow();
        return employeeRepository.findByDepartment(d);
    }

    public List<Employee> getManagerChain(Long empId) {
        List<Employee> chain = new ArrayList<>();
        Employee e = employeeRepository.findById(empId).orElseThrow();

        while (e.getManager() != null) {
            e = e.getManager();
            chain.add(e);
        }
        return chain;
    }

    public Set<Employee> getAllReports(Employee manager) {
        Set<Employee> results = new HashSet<>();
        for (Employee e : employeeRepository.findByManager(manager)) {
            results.add(e);
            results.addAll(getAllReports(e));
        }
        return results;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
