package com.technical.mercury.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayslipView {
    private Employee employee;
    private List<PayrollParams> payrollParamsDeducts;
    private List<PayrollParams> payrollParamsAccruals;
    private Payslip payslip;
}
