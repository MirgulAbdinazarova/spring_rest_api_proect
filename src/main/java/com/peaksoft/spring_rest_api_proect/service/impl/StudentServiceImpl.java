package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.StudentRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.StudentResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.StudentRequest;
import com.peaksoft.spring_rest_api_proect.dto.StudentResponse;
import com.peaksoft.spring_rest_api_proect.entities.Group;
import com.peaksoft.spring_rest_api_proect.entities.Student;
import com.peaksoft.spring_rest_api_proect.repo.GroupRepository;
import com.peaksoft.spring_rest_api_proect.repo.StudentRepository;
import com.peaksoft.spring_rest_api_proect.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl  implements StudentService {

    private  final StudentRepository studentRepository;
    private  final GroupRepository groupRepository;
    private final StudentRequestConverter studentRequestConverter;
    private final StudentResponseConverter studentResponseConverter;


    @Override
    public void assignStudentToGroup(Long studentId, Long groupId) {
        if(studentId !=null) {
            Student student = studentRepository.findById(studentId).get();
            if(groupId != null) {
                Group group = groupRepository.findById(groupId).get();
                student.setGroup(group);
                group.addStudent(student);
            }else {
                System.out.println("Group id is null");
            }
        }
        System.out.println("Student id is null");
    }

    @Override
    public List<StudentResponse> getAllStudents(Long groupId) {
        return null;
    }

    @Override
    public StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) {
        return null;
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        return null;
    }

    @Override
    public StudentResponse deleteStudentById(Long studentId) {
        return null;
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        return null;
    }
}
