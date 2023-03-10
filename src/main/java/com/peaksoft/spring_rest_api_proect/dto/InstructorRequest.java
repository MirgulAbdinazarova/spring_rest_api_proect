package com.peaksoft.spring_rest_api_proect.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
public class InstructorRequest {

    private String firstName;

    private String lastName;

    private String phoneNumber;
    @Email
    private String email;

    private String password;

    private String specialization;
}
