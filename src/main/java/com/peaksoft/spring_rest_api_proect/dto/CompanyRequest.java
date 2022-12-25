package com.peaksoft.spring_rest_api_proect.dto;

import com.peaksoft.spring_rest_api_proect.entities.Course;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
public class CompanyRequest {

    private String companyName;
    private String locatedCountry;
//    private List<Course> courses;

}
