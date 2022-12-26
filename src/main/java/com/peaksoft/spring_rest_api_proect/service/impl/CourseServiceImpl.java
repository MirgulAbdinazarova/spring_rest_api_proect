package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.CompanyResponseConverter;
import com.peaksoft.spring_rest_api_proect.converter.CourseRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.CourseResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.CourseRequest;
import com.peaksoft.spring_rest_api_proect.dto.CourseResponse;
import com.peaksoft.spring_rest_api_proect.dto.UserResponse;
import com.peaksoft.spring_rest_api_proect.entities.*;
import com.peaksoft.spring_rest_api_proect.repo.CompanyRepository;
import com.peaksoft.spring_rest_api_proect.repo.CourseRepository;
import com.peaksoft.spring_rest_api_proect.service.CourseService;
import com.peaksoft.spring_rest_api_proect.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CompanyRepository companyRepository;

    private final UserService userService;

    private final CourseRequestConverter courseRequestConverter;

    private final CourseResponseConverter courseResponseConverter;

    @Override
    public CourseResponse saveCourse(Long companyId,CourseRequest courseRequest) {
        Course course = courseRequestConverter.create(courseRequest);
        Company company = companyRepository.findById(companyId).get();
        course.setCompany(company);
        company.addCourse(course);
        courseRepository.save(course);
        return courseResponseConverter.viewCourse(course);
    }

    @Override
    public List<CourseResponse> findAllCourses() {
        return courseResponseConverter.view(courseRepository.findAll());
    }

    @Override
    public CourseResponse findByIdCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        return courseResponseConverter.viewCourse(course);
    }

    @Override
    public CourseResponse deleteByIdCourse(Long id) {
        Course course = courseRepository.findById(id).get();
        Long count = 0L;
        for (Group group : course.getGroups()) {
            for (Student student : group.getStudents()) {
                count++;
                UserResponse user = userService.findUserByEmail(student.getEmail());
                userService.deleteUserById(Long.valueOf(user.getId()));
            }
        }
        Long count1 = course.getCompany().getCount();
        count1 -= count;
        course.getCompany().setCount(count1);
        for (Instructor instructor : course.getInstructors()) {
            UserResponse user = userService.findUserByEmail(instructor.getEmail());
            userService.deleteUserById(Long.valueOf(user.getId()));
        }

        courseRepository.delete(course);
        return courseResponseConverter.viewCourse(course);
    }

    @Override
    public CourseResponse updateCourse(Long courseId, CourseRequest courseRequest) {
        Course course = courseRepository.findById(courseId).get();
        courseRequestConverter.update(course,courseRequest);
        return courseResponseConverter.viewCourse(courseRepository.save(course));
    }


    @Override
    public List<CourseResponse> viewAllCourses(Long companyId) {
        return courseResponseConverter.view(courseRepository.getAllCoursesByCompanyId(companyId));
    }
}
