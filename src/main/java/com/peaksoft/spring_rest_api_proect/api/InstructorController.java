package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.InstructorRequest;
import com.peaksoft.spring_rest_api_proect.dto.InstructorResponse;
import com.peaksoft.spring_rest_api_proect.entities.Instructor;
import com.peaksoft.spring_rest_api_proect.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ins")
public class InstructorController {

    private final InstructorService instructorService;

    @PostMapping("/{courseId}")
    public InstructorResponse saveIns(@PathVariable Long courseId, @RequestBody InstructorRequest instructorRequest) throws IOException {
        return instructorService.saveInstructor(courseId,instructorRequest);
    }

    @GetMapping("/all/{courseId}")
    public List<InstructorResponse> getAllIns(@PathVariable Long courseId) {
        return instructorService.getAllInstructors(courseId);
    }

    @PutMapping("/{instructorId}")
    public InstructorResponse updateIns(@PathVariable Long instructorId,
                                        @RequestBody InstructorRequest instructorRequest) {
        return instructorService.updateInstructor(instructorId, instructorRequest);
    }

    @DeleteMapping("/{instructorId}")
    public InstructorResponse deleteInstructor(@PathVariable Long instructorId) {
        return  instructorService.deleteInstructorById(instructorId);
    }

    @GetMapping("/{instructorId}")
    public InstructorResponse getInstructorById(@PathVariable Long instructorId) {
        return  instructorService.getInstructorById(instructorId);
    }



}
