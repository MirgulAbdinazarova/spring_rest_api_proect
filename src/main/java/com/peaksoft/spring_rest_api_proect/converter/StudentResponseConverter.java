package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.StudentResponse;
import com.peaksoft.spring_rest_api_proect.entities.Student;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentResponseConverter {

    public StudentResponse viewStudent(Student student) {
        if(student == null) {
            return null;
        }
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setFirstName(student.getFirstName());
        studentResponse.setLastName(student.getLastName());
        studentResponse.setPhoneNumber(student.getPhoneNumber());
        studentResponse.setEmail(student.getEmail());
        studentResponse.setStudyFormat(student.getStudyFormat());
        return studentResponse;
    }

    public List<StudentResponse> view(List<Student>students) {
        List<StudentResponse>studentResponses = new ArrayList<>();
        for (Student student:students) {
            studentResponses.add(viewStudent(student));
        }
        return studentResponses;
    }
}
