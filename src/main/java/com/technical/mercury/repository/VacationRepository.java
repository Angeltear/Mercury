package com.technical.mercury.repository;

import com.technical.mercury.model.Employee;
import com.technical.mercury.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {

    List<Vacation> findAllByStatusAndEmployeeRequestor(final String status, final Employee emp);

    List<Vacation> findAllByStatusAndEmployeeApprover(final String status, final Long empId);

    List<Vacation> findAllByEmployeeRequestor(final Employee emp);

    List<Vacation> findAllByEmployeeApprover(final Long empId);


}