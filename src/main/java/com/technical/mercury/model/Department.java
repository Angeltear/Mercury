package com.technical.mercury.model;

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
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "department_Id", nullable = false)
    private Long id;
    private String departmentLocation;
    private String departmentName;

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    @ToString.Exclude
    private List<Employee> employees = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_Id", foreignKey = @ForeignKey(name ="FK_Location_ID"))
    private Location location;

}
