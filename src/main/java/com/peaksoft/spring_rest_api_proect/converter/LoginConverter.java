package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.LoginResponse;
import com.peaksoft.spring_rest_api_proect.entities.Role;
import com.peaksoft.spring_rest_api_proect.entities.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class LoginConverter {

    public LoginResponse loginView(String token,
                                   String deadline,
                                   String message,
                                   User user) {
        var loginResponse = new LoginResponse();
        if (user != null) {
            setAuthorite(loginResponse, user.getRoles());
        }
        loginResponse.setDeadline(deadline);
        loginResponse.setJwtToken(token);
        loginResponse.setMessage(message);
        return loginResponse;
    }

    private void setAuthorite(LoginResponse loginResponse, List<Role> roles) {
        Set<String> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(role.getRoleName());
        }
        loginResponse.setAuthorities(authorities);
    }
}