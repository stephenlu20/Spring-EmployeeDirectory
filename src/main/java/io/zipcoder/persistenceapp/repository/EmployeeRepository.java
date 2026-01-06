package io.zipcoder.persistenceapp.repository;

import io.zipcoder.persistenceapp.entity.Employee;
import io.zipcoder.persistenceapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByManager(Employee manager);

    List<Employee> findByManagerIsNull();

    List<Employee> findByDepartment(Department department);
}
