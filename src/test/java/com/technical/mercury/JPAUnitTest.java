package com.technical.mercury;

import com.technical.mercury.model.Department;
import com.technical.mercury.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class JPAUnitTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private DepartmentRepository repository;

    @Test
    public void should_find_no_departments(){
        List<Department> departments = repository.findAll();
        assertNotNull(departments);
    }

    @Test
    public void insertDepartment(){
        Department department = repository.save(new Department());
         assertNotNull(department);
    }}
