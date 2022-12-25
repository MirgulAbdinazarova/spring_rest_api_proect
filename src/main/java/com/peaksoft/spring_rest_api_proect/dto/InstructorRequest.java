package com.peaksoft.spring_rest_api_proect.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorRequest {

    private String firstName;

    private String lastName;

    private int phoneNumber;

    private String email;

    private String specialization;
}
