package com.technical.mercury.repository;

import com.technical.mercury.model.PayrollParams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollParamsRepository extends JpaRepository<PayrollParams, Long> {
    PayrollParams findPayrollParamsByParameterName(final String paramName);
    List<PayrollParams> findPayrollParamsByParameterNameIsNot(final String paramName);
}