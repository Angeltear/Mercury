package com.technical.mercury.services;

import com.technical.mercury.model.Department;
import com.technical.mercury.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    public void delete(Department department) {
        departmentRepository.delete(department);
    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    public Department getById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}
