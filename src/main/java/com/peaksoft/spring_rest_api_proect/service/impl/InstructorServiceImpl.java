package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.InstructorRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.InstructorResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.InstructorRequest;
import com.peaksoft.spring_rest_api_proect.dto.InstructorResponse;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.entities.Instructor;
import com.peaksoft.spring_rest_api_proect.repo.CourseRepository;
import com.peaksoft.spring_rest_api_proect.repo.InstructorRepository;
import com.peaksoft.spring_rest_api_proect.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl  implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final InstructorRequestConverter instructorRequestConverter;
    private final InstructorResponseConverter instructorResponseConverter;


    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseId) {
        if(instructorId!=null) {
            Instructor instructor = instructorRepository.findById(instructorId).get();
            if(courseId != null) {
                Course course = courseRepository.findById(courseId).get();
                instructor.setCourse(course);
                course.addInstructor(instructor);
            }else {
                System.out.println("Course id is null");
            }
        }else {
            System.out.println("Instructor id is null");
        }
    }

    @Override
    public List<InstructorResponse> getAllInstructors(Long courseId) {
        return instructorResponseConverter.view(instructorRepository.getAllInstructorsByCourseId(courseId));
    }

    @Override
    public InstructorResponse saveInstructor(Long courseId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRequestConverter.create(instructorRequest);
        Course course = courseRepository.findById(courseId).get();
        instructor.setCourse(course);
        course.addInstructor(instructor);
        instructorRepository.save(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse updateInstructor(Long instructorId, InstructorRequest instructorRequest) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRequestConverter.update(instructor,instructorRequest);
        return instructorResponseConverter.viewInstructor(instructorRepository.save(instructor));
    }

    @Override
    public InstructorResponse deleteInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        instructorRepository.delete(instructor);
        return instructorResponseConverter.viewInstructor(instructor);
    }

    @Override
    public InstructorResponse getInstructorById(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId).get();
        return instructorResponseConverter.viewInstructor(instructor);
    }
}
