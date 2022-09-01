package com.technical.mercury.repository;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.Payslip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByEmployeeManagerAndActive(final Employee emp, final boolean active);

    @Query(value = "SELECT p.* FROM employee p where not exists (select 1 from payslip p where p.month=?1 and p.year=?2)", nativeQuery = true)
    List<Employee> findAllNotProcessedForMonth(String month, int year);

    List<Employee> findEmployeeByActive(boolean isActive);

    Employee findEmployeeByEmployeeEmail(String email);
}