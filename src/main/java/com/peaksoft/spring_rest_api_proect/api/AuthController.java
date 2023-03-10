package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.config.ValidationExceptionType;
import com.peaksoft.spring_rest_api_proect.config.jwt.JwtTokenUtil;
import com.peaksoft.spring_rest_api_proect.converter.LoginConverter;
import com.peaksoft.spring_rest_api_proect.dto.LoginResponse;
import com.peaksoft.spring_rest_api_proect.dto.UserRequest;
import com.peaksoft.spring_rest_api_proect.dto.UserResponse;
import com.peaksoft.spring_rest_api_proect.entities.User;
import com.peaksoft.spring_rest_api_proect.repo.UserRepository;
import com.peaksoft.spring_rest_api_proect.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {

    private final UserServiceImpl userServiceImpl;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRepository userRepository;

    private final LoginConverter loginConverter;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody UserRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = userRepository.findByEmail(token.getName());
            return ResponseEntity.ok().body(loginConverter.loginView(jwtTokenUtil.generateToken(user),
                    String.valueOf(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 70 * 1000L)), ValidationExceptionType.SUCCESSFUL, user));
        }catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginConverter.
                    loginView("", String.valueOf(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 70 * 1000L)),
                            ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("/registration")
    public UserResponse create(@RequestBody UserRequest request) {
        return userServiceImpl.saveUser(request);
    }

    @GetMapping("/all")
    public List<UserResponse> findAllUser() {
        return userServiceImpl.viewAllUser();
    }

    @GetMapping("/{userId}")
    public UserResponse findById(@PathVariable Long userId) {
        return userServiceImpl.findUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public UserResponse deleteUserById(@PathVariable Long userId) {
        return userServiceImpl.deleteUserById(userId);
    }

    @PutMapping
    public UserResponse updateUser(@RequestParam String userEmail,
                                   @RequestBody UserRequest userRequest) throws IOException {
        return userServiceImpl.updateUser(userEmail, userRequest);
    }

    @PostMapping("/addRoleToUser")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserResponse addRoleToUser(@RequestParam String userEmail, @RequestParam String roleName) {
        return userServiceImpl.addRoleToUser(userEmail, roleName);
    }

}


