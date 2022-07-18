package com.technical.mercury.model;

import lombok.*;

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
    private String employeeManager;
    private LocalDate employmentStartDate;
    private LocalDate careerStartDate;
    private LocalDate birthDate;
    private LocalDate trialPeriod;
    private String employeeEmail;
    private String employeePhoneNum;

    @ManyToOne
    @JoinColumn(name = "department_Id",foreignKey = @ForeignKey(name="FK_Department_ID"))
    private Department department;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private List<EmployeeSalary> employeeSalaries = new ArrayList<>();

}
