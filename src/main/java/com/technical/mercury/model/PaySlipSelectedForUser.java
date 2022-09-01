package com.technical.mercury.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaySlipSelectedForUser {
    private PayslipSelectedObject selectedObject;
    private Employee employee;
}
