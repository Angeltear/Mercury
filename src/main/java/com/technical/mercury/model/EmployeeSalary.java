package com.technical.mercury.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class EmployeeSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endPeriod;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startPeriod;
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_Employee_ID"))
    private Employee employee;

}
