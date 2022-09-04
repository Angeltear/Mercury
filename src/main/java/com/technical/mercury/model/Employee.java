package com.technical.mercury.model;

import com.technical.mercury.model.Users.User;
import com.technical.mercury.model.Users.UserRoles;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
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
    private LocalDate employmentEndDate;
    private String employeeEmail;
    private String employeePhoneNum;
    private boolean active;
    private boolean hrRole;
    private boolean adminRole;
    @Transient
    private String password;

    @ManyToOne
    @JoinColumn(name = "department_Id", foreignKey = @ForeignKey(name = "FK_Department_ID"))
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
    private List<EmployeeHistory> employeeHistory = new ArrayList<>();

    @OneToMany(mappedBy = "employeeRequestor", orphanRemoval = true)
    @ToString.Exclude
    private List<Vacation> vacationRequests = new ArrayList<>();

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private List<Payslip> payslips = new ArrayList<>();

    @OneToOne(mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private User users;

    @OneToMany(mappedBy = "employee", orphanRemoval = true)
    @ToString.Exclude
    private List<UserRoles> userRoles = new ArrayList<>();

}
