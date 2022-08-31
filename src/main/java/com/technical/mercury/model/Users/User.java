package com.technical.mercury.model.Users;

import com.technical.mercury.model.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_Id", nullable = false)
    private Long id;
    private boolean active;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<UserRoles> userRoles;

    @OneToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "FK_Emp_User_ID"))
    private Employee employee;

}
