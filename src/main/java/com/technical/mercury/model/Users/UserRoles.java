package com.technical.mercury.model.Users;

import com.technical.mercury.model.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id", nullable = false)
    private Long id;
    private String userRole;

    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_Role_Emp_ID"))
    private Employee employee;


    @ManyToOne
    @JoinColumn(name = "user_Id", foreignKey = @ForeignKey(name = "FK_Role_User_ID"))
    private User user;

}
