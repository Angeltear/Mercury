package com.technical.mercury.repository;

import com.technical.mercury.model.EmployeeSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, Long> {

    List<EmployeeSalary> findAllByEmployeeId(final Long empId);
}