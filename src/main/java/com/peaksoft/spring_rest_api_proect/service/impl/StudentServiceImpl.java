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
        return studentResponseConverter.view(studentRepository.getAllStudentByGroupId(groupId));
    }

    @Override
    public StudentResponse saveStudent(Long groupId, StudentRequest studentRequest) {
        Student student = studentRequestConverter.create(studentRequest);
        Group group = groupRepository.findById(groupId).get();
        student.setGroup(group);
        group.addStudent(student);
        groupRepository.save(group);
        return studentResponseConverter.viewStudent(student);
    }

    @Override
    public StudentResponse updateStudent(Long studentId, StudentRequest studentRequest) {
        Student student = studentRepository.findById(studentId).get();
        studentRequestConverter.update(student,studentRequest);
        return studentResponseConverter.viewStudent(student);
    }

    @Override
    public StudentResponse deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.delete(student);
        return studentResponseConverter.viewStudent(student);
    }

    @Override
    public StudentResponse getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return studentResponseConverter.viewStudent(student);
    }
}
