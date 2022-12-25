package com.peaksoft.spring_rest_api_proect.dto;

import com.peaksoft.spring_rest_api_proect.entities.Company;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {

    private String courseName;
    private int duration;
    private String description;

}
