package com.technical.mercury.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String userName;
    private String userPosition;
    private String userLocation;
    private String userManager;
    private double userSalary;
    private String companyEmploymentPeriod;
    private String totalEmploymentPeriod;
    private String notes;
    private String trialPeriod;
}
