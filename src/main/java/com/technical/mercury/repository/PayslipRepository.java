package com.technical.mercury.repository;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PayslipRepository extends JpaRepository<Payslip, Long> {
    Payslip findPayslipByEmployeeAndMonthAndYear(Employee employee, String month, int year);
}