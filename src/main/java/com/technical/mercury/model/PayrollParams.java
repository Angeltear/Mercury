package com.technical.mercury.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PayrollParams {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "param_Id", nullable = false)
    private Long id;
    private String parameterName;
    private Double parameterPercentage;
}