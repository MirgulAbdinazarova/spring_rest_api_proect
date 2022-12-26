package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.StudentRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.StudentResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.StudentRequest;
import com.peaksoft.spring_rest_api_proect.dto.StudentResponse;
import com.peaksoft.spring_rest_api_proect.dto.UserRequest;
import com.peaksoft.spring_rest_api_proect.dto.UserResponse;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.entities.Group;
import com.peaksoft.spring_rest_api_proect.entities.Instructor;
import com.peaksoft.spring_rest_api_proect.entities.Student;
import com.peaksoft.spring_rest_api_proect.repo.GroupRepository;
import com.peaksoft.spring_rest_api_proect.repo.StudentRepository;
import com.peaksoft.spring_rest_api_proect.repo.UserRepository;
import com.peaksoft.spring_rest_api_proect.service.StudentService;
import com.peaksoft.spring_rest_api_proect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements StudentService {

    private  final StudentRepository studentRepository;
    private  final GroupRepository groupRepository;

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final StudentRequestConverter studentRequestConverter;
    private final StudentResponseConverter studentResponseConverter;


    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        if(studentId !=null) {
            Student student = studentRepository.findById(studentId).get();
            if(groupId != null) {
                Group group = groupRepository.findById(groupId).get();
                student.setGroup(group);
                group.addStudent(student);
            }else {
                System.out.println("Group id is null");
            }
        }
        System.out.println("Student id is null");
    }

    @Override
    public List<StudentResponse> getAllStudents(Long groupId) {
        return studentResponseConverter.view(studentRepository.getAllStudentByGroupId(groupId));
    }

    @Override
    public StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) throws IOException {
        Group group = groupRepository.findById(groupId).get();
        Student student = studentRequestConverter.create(studentRequest);
        validator(student.getPhoneNumber().replace(" ", ""), student.getLastName()
                .replace(" ", ""), student.getFirstName()
                .replace(" ", ""));
        if (userService.findUserByEmail(student.getEmail()) == null && studentRepository.findByEmail(student.getEmail()) == null) {
            //
            for (Course course : group.getCourses()) {
                course.getCompany().plus();
            }
            for (Course course : group.getCourses()) {
                for (Instructor instructor : course.getInstructors()) {
                    instructor.plus();
                }
            }
            //
            System.out.println("save user 1");
            UserResponse user = userService.saveUser(new UserRequest(student.getEmail(), student.getPassword()));
            userService.addRoleToUser(user.getEmail(), "ROLE_STUDENT");
            group.addStudent(student);
            student.setGroup(group);
            String encodePassword = passwordEncoder.encode(student.getPassword());
            student.setPassword(encodePassword);
            studentRepository.save(student);
            return studentResponseConverter.viewStudent(student);
        } else {
            throw new IOException("Student with this email already exists!!!");
        }
    }


    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).get();
        studentRequestConverter.update(student,studentRequest);
        return studentResponseConverter.viewStudent(studentRepository.save(student));
    }

    @Override
    public StudentResponse deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.delete(student);
        return studentResponseConverter.viewStudent(student);
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return studentResponseConverter.viewStudent(student);
    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В имени студента нельзя вставлять цифры");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В фамилию студента нельзя вставлять цифры");
                }
            }
        } else {
            throw new IOException("В имени или фамилии студента должно быть как минимум 3 буквы");
        }

        if (phoneNumber.length() == 13
                && phoneNumber.charAt(0) == '+'
                && phoneNumber.charAt(1) == '9'
                && phoneNumber.charAt(2) == '9'
                && phoneNumber.charAt(3) == '6') {
            int counter = 0;

            for (Character i : phoneNumber.toCharArray()) {
                if (counter != 0) {
                    if (!Character.isDigit(i)) {
                        throw new IOException("Формат номера не правильный");
                    }
                }
                counter++;
            }
        } else {
            throw new IOException("Формат номера не правильный");
        }
    }
}
