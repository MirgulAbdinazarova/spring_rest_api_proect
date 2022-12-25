package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.CompanyResponse;
import com.peaksoft.spring_rest_api_proect.dto.CourseResponse;
import com.peaksoft.spring_rest_api_proect.entities.Company;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseResponseConverter {

    public CourseResponse viewCourse(Course course) {
        if(course==null) {
            return  null;
        }
        CourseResponse courseResponse = new CourseResponse();
        courseResponse.setId(course.getId());
        courseResponse.setCourseName(course.getCourseName());
        courseResponse.setDuration(course.getDuration());
        courseResponse.setDescription(course.getDescription());
        return courseResponse;
    }

    public List<CourseResponse> view(List<Course> courses) {
        List<CourseResponse> courseResponses = new ArrayList<>();
        for (Course course:courses) {
           courseResponses .add(viewCourse(course));
        }
        return courseResponses;

    }
}
