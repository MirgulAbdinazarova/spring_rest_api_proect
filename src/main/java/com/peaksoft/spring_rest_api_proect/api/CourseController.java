package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.CompanyResponse;
import com.peaksoft.spring_rest_api_proect.dto.CourseRequest;
import com.peaksoft.spring_rest_api_proect.dto.CourseResponse;
import com.peaksoft.spring_rest_api_proect.service.CourseService;
import com.peaksoft.spring_rest_api_proect.service.GroupService;
import com.peaksoft.spring_rest_api_proect.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;
    private  final GroupService groupService;

    private  final InstructorService instructorService;

    @PostMapping("/{companyId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseResponse save(@PathVariable Long companyId,@RequestBody CourseRequest courseRequest) {
        return courseService.saveCourse(companyId,courseRequest);
    }
    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CourseResponse getByIdCourse(@PathVariable Long id) {
        return courseService.findByIdCourse(id);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<CourseResponse> findAllCourses() {
        return courseService.findAllCourses();
    }
    @GetMapping("/{companyId}")
    @PreAuthorize("isAuthenticated()")
    public List<CourseResponse> findAllCoursesByCompanyId(@PathVariable Long companyId) {
        return  courseService.viewAllCourses(companyId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseResponse deleteByIdCourse(@PathVariable Long id) {
        return courseService.deleteByIdCourse(id);
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseResponse updateCourseById(@PathVariable Long courseId,CourseRequest courseRequest) {
        return courseService.updateCourse(courseId,courseRequest);
    }

    @PostMapping("/{groupId}/aGroupToCourse/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseResponse assignGroupToCourse(@PathVariable Long courseId,
                                              @PathVariable Long groupId) {
        groupService.assignGroupToCourse(groupId, courseId);
        return courseService.findByIdCourse(courseId);
    }
    @PostMapping("/{insId}/assInsToCourse/{courseId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CourseResponse assingInsToCourse(@PathVariable Long insId,
                                            @PathVariable Long courseId) {
        instructorService.assignInstructorToCourse(insId,courseId);
        return courseService.findByIdCourse(courseId);
    }

}
