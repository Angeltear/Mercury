package com.technical.mercury.services;

import com.technical.mercury.model.Department;
import com.technical.mercury.model.Employee;
import com.technical.mercury.repository.DepartmentRepository;
import com.technical.mercury.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAll(){
        return departmentRepository.findAll();
    }

    public Department save(Department department){return departmentRepository.save(department);}

    public void delete(Department department){departmentRepository.delete(department);}
}
