package com.technical.mercury.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VacationRequest {
    private int userID;
    private String userName;
    private String vacationType;
    private int duration;
    private String approverName;
}
