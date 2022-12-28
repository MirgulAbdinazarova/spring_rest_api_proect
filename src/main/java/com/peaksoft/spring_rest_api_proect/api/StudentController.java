package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.StudentRequest;
import com.peaksoft.spring_rest_api_proect.dto.StudentResponse;
import com.peaksoft.spring_rest_api_proect.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/{groupId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public StudentResponse saveStudent(@PathVariable Long groupId,
                                       @RequestBody StudentRequest studentRequest) throws IOException {
        return studentService.saveStudent(groupId,studentRequest);
    }

    @GetMapping("/all/{groupId}")
    @PreAuthorize("isAuthenticated()")
    public List<StudentResponse> getAllStudent(@PathVariable Long groupId) {
        return studentService.getAllStudents(groupId);
    }

    @GetMapping("/{studentId}")
    @PreAuthorize("isAuthenticated()")
    public StudentResponse getStudentById(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }


    @PutMapping("/{studentId}")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public StudentResponse updateStudent(@PathVariable Long studentId,
                                         @RequestBody StudentRequest studentRequest) {
        return studentService.updateStudent(studentId,studentRequest);
    }
    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public StudentResponse deleteStudent(@PathVariable Long studentId) {
        return  studentService.deleteStudentById(studentId);
    }


}
