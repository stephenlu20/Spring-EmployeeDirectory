package io.zipcoder.persistenceapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dpt_num")
    private Long departmentNumber;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Employee manager;

    public Department() {}

    public Department(Long departmentNumber, String name) {
        this.departmentNumber = departmentNumber;
        this.name = name;
    }

    public Long getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(Long departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}
