package com.technical.mercury.model;

import lombok.*;

import javax.persistence.*;

@Table(name="VACATION_REQUEST")
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class VacationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private long userID;
    private String userName;
    private String vacationType;
    private int duration;
    private long approverId;
    private String approverName;
}
