package com.technical.mercury.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id", nullable = false)
    private Long id;
    private String employeeFirstName;
    private String employeeLastName;
    private String employeePosition;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate employmentStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate careerStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate trialPeriod;
    private LocalDate employmentEndDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String employeeEmail;
    private String employeePhoneNum;

    @ManyToOne
    @JoinColumn(name = "department_Id",foreignKey = @ForeignKey(name="FK_Department_ID"))
    private Department department;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private List<EmployeeSalary> employeeSalaries = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "employee_manager_id", foreignKey = @ForeignKey(name = "FK_Manager_ID"))
    private Employee employeeManager;

    @OneToMany(mappedBy = "employeeManager")
    @ToString.Exclude
    private List<Employee> employees;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private List<EmployeeSalary> employeeHistory = new ArrayList<>();

}
