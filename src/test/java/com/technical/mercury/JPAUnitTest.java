package com.technical.mercury;

import com.technical.mercury.model.Department;
import com.technical.mercury.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DataJpaTest
public class JPAUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    DepartmentRepository repository;

    @Test
    public void should_find_no_departments(){
        List<Department> departments = repository.findAll();
        assert (departments.isEmpty());
    }

    @Test
    public void insertDepartment(){
        repository.save(new Department());

    }}
