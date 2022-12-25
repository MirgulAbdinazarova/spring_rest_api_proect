package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.CompanyResponseConverter;
import com.peaksoft.spring_rest_api_proect.converter.CourseRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.CourseResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.CourseRequest;
import com.peaksoft.spring_rest_api_proect.dto.CourseResponse;
import com.peaksoft.spring_rest_api_proect.entities.Company;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.repo.CompanyRepository;
import com.peaksoft.spring_rest_api_proect.repo.CourseRepository;
import com.peaksoft.spring_rest_api_proect.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    private final CompanyRepository companyRepository;

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
