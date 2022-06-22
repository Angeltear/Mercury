package com.technical.mercury.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private int userID;
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
