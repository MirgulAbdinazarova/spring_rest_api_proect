package com.peaksoft.spring_rest_api_proect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

    private Long id;
    private String groupName;
    private Date dateOfStart;
    private String image;
}
