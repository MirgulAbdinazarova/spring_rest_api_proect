package com.peaksoft.spring_rest_api_proect.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LoginResponse {

    private String jwtToken;

    private String deadline;//

    private String message;

    private Set<String> authorities;
}

