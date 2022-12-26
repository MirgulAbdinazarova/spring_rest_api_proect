package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.UserRequest;
import com.peaksoft.spring_rest_api_proect.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse saveUser(UserRequest request);

    List<UserResponse> viewAllUser();

    UserResponse addRoleToUser(String userEmail, String roleName);

    UserResponse deleteUserById(Long userId);

    UserResponse updateUser(String userEmail, UserRequest userRequest);

    UserResponse findUserById(Long userId);

    UserResponse findUserByEmail(String userEmail);
}
