package com.peaksoft.spring_rest_api_proect.dto;

import com.peaksoft.spring_rest_api_proect.entities.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResponse {

    private Long id;
    private String courseName;
    private int duration;
    private String description;


}
