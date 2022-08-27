package com.technical.mercury.services;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.EmployeeSalary;
import com.technical.mercury.repository.EmployeeRepository;
import com.technical.mercury.repository.EmployeeSalaryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @Autowired
    private final EmployeeSalaryRepository employeeSalaryRepository;

    public List<Employee> getAll(){
        return employeeRepository.findAll();
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee getById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }


    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public EmployeeSalary getSalaryById(Long id){
        return employeeSalaryRepository.findById(id).orElse(null);
    }

    public List<EmployeeSalary> getSalariesByEmpId(Long empId) { return employeeSalaryRepository.findAllByEmployeeId(empId);}
    public void saveEmpSalary(EmployeeSalary employeeSalary){
        employeeSalaryRepository.save(employeeSalary);
    }
}
