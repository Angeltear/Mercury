package com.technical.mercury.services;

import com.technical.mercury.model.Location;
import com.technical.mercury.model.PayrollParams;
import com.technical.mercury.repository.PayrollParamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PayrollService {

    private final PayrollParamsRepository payrollParamsRepository;

    public List<PayrollParams> getAll(){
        return payrollParamsRepository.findAll();
    }

    public PayrollParams save(PayrollParams payrollParams){return payrollParamsRepository.save(payrollParams);}

    public PayrollParams getById(Long id) {
        return payrollParamsRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        payrollParamsRepository.deleteById(id);
    }

    public List<PayrollParams> getDeducts(){
        return payrollParamsRepository.findPayrollParamsByParameterPercentageGreaterThan(Double.parseDouble("0.00"));
    }
    public List<PayrollParams> getAccruals(){
        return payrollParamsRepository.findPayrollParamsByParameterPercentageLessThan(Double.parseDouble("0.00"));
    }
}
