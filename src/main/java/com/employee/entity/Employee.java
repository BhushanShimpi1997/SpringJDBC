package com.employee.entity;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Employee {

    private int empId;
    private String empName;
    private String empDept;
    private double empSalary;
    private String empCity;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
