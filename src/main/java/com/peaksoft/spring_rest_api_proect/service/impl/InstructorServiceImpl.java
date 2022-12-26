package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.InstructorRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.InstructorResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.InstructorRequest;
import com.peaksoft.spring_rest_api_proect.dto.InstructorResponse;
import com.peaksoft.spring_rest_api_proect.dto.UserRequest;
import com.peaksoft.spring_rest_api_proect.dto.UserResponse;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.entities.Group;
import com.peaksoft.spring_rest_api_proect.entities.Instructor;
import com.peaksoft.spring_rest_api_proect.entities.Student;
import com.peaksoft.spring_rest_api_proect.repo.CourseRepository;
import com.peaksoft.spring_rest_api_proect.repo.InstructorRepository;
import com.peaksoft.spring_rest_api_proect.service.InstructorService;
import com.peaksoft.spring_rest_api_proect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl  implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final InstructorRequestConverter instructorRequestConverter;
    private final InstructorResponseConverter instructorResponseConverter;


    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        if(instructorId!=null) {
            Instructor instructor = instructorRepository.findById(instructorId).get();
            if(courseId != null) {
                Course course = courseRepository.findById(courseId).get();
                instructor.setCourse(course);
                course.addInstructor(instructor);
            }else {
                System.out.println("Course id is null");
            }
        }else {
            System.out.println("Instructor id is null");
        }
    }

    @Override
    public List<InstructorResponse> getAllInstructors(Long courseId) {
        return instructorResponseConverter.view(instructorRepository.getAllInstructorsByCourseId(courseId));
    }

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) throws IOException {
        Instructor instructor = instructorRequestConverter.create(instructorRequest);
        Course course = courseRepository.findById(courseId).get();
        validator(instructor.getPhoneNumber().replace(" ", ""), instructor.getLastName()
                .replace(" ", ""), instructor.getFirstName()
                .replace(" ", ""));
        //
        if (instructorRepository.findByEmail(instructor.getEmail()) == null && userService.findUserByEmail(instructor.getEmail()) == null) {
            Long count = 0L;
            for (Group group : course.getGroups()) {
                for (Student student : group.getStudents()) {
                    count++;

                }
            }
            instructor.setCount(count);
            //
            UserResponse user = userService.saveUser(new UserRequest(instructor.getEmail(), instructor.getPassword()));
            userService.addRoleToUser(user.getEmail(), "ROLE_STUDENT");
            course.addInstructor(instructor);
            instructor.setCourse(course);
            String encodePassword = passwordEncoder.encode(instructor.getPassword());
            instructor.setPassword(encodePassword);
            instructorRepository.save(instructor);
            return instructorResponseConverter.viewInstructor(instructor);
        } else {
            throw new IOException("Instructor with this email already exists!!!");
        }

    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRequestConverter.update(instructor,instructorRequest);
        return instructorResponseConverter.viewInstructor(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse deleteInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRepository.delete(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        return instructorResponseConverter.viewInstructor(instructor);
    }

    private void validator(String phoneNumber, String firstName, String lastName) throws IOException {
        if (firstName.length() > 2 && lastName.length() > 2) {
            for (Character i : firstName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В имени инструктора нельзя вставлять цифры");
                }
            }

            for (Character i : lastName.toCharArray()) {
                if (!Character.isAlphabetic(i)) {
                    throw new IOException("В фамилию инструктора нельзя вставлять цифры");
                }
            }
        } else {
            throw new IOException("В имени или фамилии инструктора должно быть как минимум 3 буквы");
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
