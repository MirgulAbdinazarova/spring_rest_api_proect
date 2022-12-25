package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.CompanyRequest;
import com.peaksoft.spring_rest_api_proect.dto.CourseRequest;
import com.peaksoft.spring_rest_api_proect.entities.Company;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseRequestConverter {

    public Course create(CourseRequest courseRequest) {
        if(courseRequest==null) {
            return null;
        }
        Course course=new Course();
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
        return  course;
    }

    public void update(Course course,CourseRequest courseRequest) {
        course.setCourseName(courseRequest.getCourseName());
        course.setDuration(courseRequest.getDuration());
        course.setDescription(courseRequest.getDescription());
    }
}
