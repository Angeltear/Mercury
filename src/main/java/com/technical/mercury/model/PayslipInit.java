package com.technical.mercury.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

@NoArgsConstructor
@Getter
public class PayslipInit {

    private final List<String> months = List.of("January", "February", "March", "April", "June", "July", "August", "September", "October", "November", "December");
    private final List<Integer> years = List.of(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.YEAR) - 1, Calendar.getInstance().get(Calendar.YEAR) - 2, Calendar.getInstance().get(Calendar.YEAR) - 3, Calendar.getInstance().get(Calendar.YEAR) - 4);

}
