package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.InstructorResponse;
import com.peaksoft.spring_rest_api_proect.entities.Instructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InstructorResponseConverter {

    public InstructorResponse viewInstructor(Instructor instructor) {
        if(instructor==null) {
            return null;
        }
        InstructorResponse instructorResponse = new InstructorResponse();
        instructorResponse.setId(instructor.getId());
        instructorResponse.setFirstName(instructor.getFirstName());
        instructorResponse.setLastName(instructor.getLastName());
        instructorResponse.setPhoneNumber(instructor.getPhoneNumber());
        instructorResponse.setEmail(instructor.getEmail());
        instructorResponse.setSpecialization(instructor.getSpecialization());
        return instructorResponse;
    }

    public List<InstructorResponse> view(List<Instructor>instructors) {
        List<InstructorResponse>instructorResponses = new ArrayList<>();
        for (Instructor instructor:instructors) {
            instructorResponses.add(viewInstructor(instructor));
        }
        return instructorResponses;
    }
}
