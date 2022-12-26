package com.peaksoft.spring_rest_api_proect.service.impl;
import com.peaksoft.spring_rest_api_proect.converter.UserRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.UserResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.UserRequest;
import com.peaksoft.spring_rest_api_proect.dto.UserResponse;
import com.peaksoft.spring_rest_api_proect.entities.Role;
import com.peaksoft.spring_rest_api_proect.entities.User;
import com.peaksoft.spring_rest_api_proect.repo.RoleRepository;
import com.peaksoft.spring_rest_api_proect.repo.UserRepository;
import com.peaksoft.spring_rest_api_proect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import javax.annotation.PostConstruct;
import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final UserRequestConverter userRequestConverter;

    private final UserResponseConverter userResponseConverter;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        if (userRepository.countOfUsers() != 0 && roleRepository.countOfRoles() != 0) {
            System.out.println("Already exists" + userRepository.countOfUsers() + " " + roleRepository.countOfRoles());
        }else {
            roleRepository.save(new Role("ROLE_STUDENT"));//компанияны , курсту коро алат //task
            roleRepository.save(new Role("ROLE_INSTRUCTOR"));//компанияны, курсту, группаны student task lesson
            roleRepository.save(new Role("ROLE_ADMIN"));//all

            userRepository.save(new User("aza@gmail.com", passwordEncoder.encode("aza"), "Aza Bacytov"));
            userRepository.save(new User("umar@gmail.com", passwordEncoder.encode("umar"), "Umar Talantov"));
            userRepository.save(new User("amir@gmail.com", passwordEncoder.encode("amir"), "Amir Sadyrov"));

            addRoleToUser("aza@gmail.com", "ROLE_STUDENT");
            addRoleToUser("umar@gmail.com", "ROLE_ADMIN");
            addRoleToUser("amir@gmail.com", "ROLE_INSTRUCTOR");
        }
    }

    @Override
    public UserResponse saveUser(UserRequest request) {
        User user = userRequestConverter.saveUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return userResponseConverter.viewUser(user);
    }

    @Override
    public List<UserResponse> viewAllUser() {
        return userResponseConverter.viewAllUser(userRepository.findAll());
    }

    @Override
    public UserResponse addRoleToUser(String userEmail, String roleName) {
        Role role = roleRepository.findByName(roleName);
        User user = userRepository.findByEmail(userEmail);
        user.addRole(role);
        userRepository.save(user);
        return userResponseConverter.viewUser(user);
    }

    @Override
    public UserResponse deleteUserById(Long userId) {
        User  user = userRepository.findById(userId).get();
        user.getRoles().add(null);
        userRepository.save(user);
        userRepository.delete(user);
        return userResponseConverter.viewUser(user);
    }

    @Override
    public UserResponse updateUser(String userEmail, UserRequest userRequest) {
        User user = userRepository.findByEmail(userEmail);
        userRequestConverter.update(user,userRequest);
        if (userRequest.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        return userResponseConverter.viewUser(userRepository.save(user));
    }

    @Override
    public UserResponse findUserById(Long userId) {
        return userResponseConverter.viewUser(userRepository.findById(userId).get());
    }

    @Override
    public UserResponse findUserByEmail(String userEmail) {
        return userResponseConverter.viewUser(userRepository.findByEmail(userEmail));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepository.findByEmail(username) != null) {
            return userRepository.findByEmail(username);
        } else {
            throw new UsernameNotFoundException("not found email");
        }
    }
}
