package io.zipcoder.persistenceapp.dto;

import java.time.LocalDate;

public class EmployeeCreateRequest {
    public String firstName;
    public String lastName;
    public String title;
    public String phone;
    public String email;
    public LocalDate hireDate;
    public Long departmentNumber;
}
