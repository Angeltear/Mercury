package com.technical.mercury.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Payslip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "payslip_id", nullable = false)
    private Long id;
    private Double baseSalary;
    private Double withholds;
    private String month;
    private int year;
    private Double receivable;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_Employee_Payslip_ID"))
    private Employee employee;
}
