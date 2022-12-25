package com.peaksoft.spring_rest_api_proect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private int phoneNumber;

    private String email;

    private String specialization;
}
