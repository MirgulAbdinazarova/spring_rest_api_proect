package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.StudentRequest;
import com.peaksoft.spring_rest_api_proect.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    void assignStudentToGroup(Long studentId,Long groupId);

    List<StudentResponse> getAllStudents(Long groupId);

    StudentResponse saveStudent(Long groupId, StudentRequest studentRequest);

    StudentResponse updateStudent(Long studentId,StudentRequest studentRequest);

    StudentResponse deleteStudentById(Long studentId);

    StudentResponse getStudentById(Long studentId);

}
