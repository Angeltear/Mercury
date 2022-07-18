package com.technical.mercury.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PathToPage {
    private String pageName;
    private String pageLink;
}
