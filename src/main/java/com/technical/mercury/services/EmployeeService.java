package com.technical.mercury.services;

import com.technical.mercury.model.Employee;
import com.technical.mercury.repository.EmployeeRepository;
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
}
