package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.CourseRequest;
import com.peaksoft.spring_rest_api_proect.dto.CourseResponse;

import java.util.List;

public interface CourseService {

    CourseResponse saveCourse(Long companyId,CourseRequest courseRequest);

    List<CourseResponse> findAllCourses();

    CourseResponse findByIdCourse(Long id);

    CourseResponse deleteByIdCourse(Long id);

    CourseResponse updateCourse(Long courseId,CourseRequest courseRequest);


    List<CourseResponse> viewAllCourses(Long companyId);

}
