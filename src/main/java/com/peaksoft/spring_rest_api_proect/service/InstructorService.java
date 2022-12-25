package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.InstructorRequest;
import com.peaksoft.spring_rest_api_proect.dto.InstructorResponse;

import java.io.IOException;
import java.util.List;

public interface InstructorService {

    void assignInstructorToCourse(Long instructorId,Long courseId);

    List<InstructorResponse> getAllInstructors(Long courseId);

    InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest);

    InstructorResponse updateInstructor(Long instructorId,InstructorRequest instructorRequest);

    InstructorResponse deleteInstructorById(Long instructorId);

    InstructorResponse getInstructorById(Long instructorId);

}
