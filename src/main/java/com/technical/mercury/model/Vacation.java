package com.technical.mercury.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "VACATION")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String vacationType;
    private Long employeeApprover;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vacationStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate vacationEndDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_Employee_ID_Req"))
    private Employee employeeRequestor;

}
