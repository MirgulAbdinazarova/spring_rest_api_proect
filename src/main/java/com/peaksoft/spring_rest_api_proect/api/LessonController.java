package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.LessonRequest;
import com.peaksoft.spring_rest_api_proect.dto.LessonResponse;
import com.peaksoft.spring_rest_api_proect.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lesson")
public class LessonController {

    private  final LessonService lessonService;

    @PostMapping("/{courseId}")
    public LessonResponse saveLesson(@PathVariable Long courseId, @RequestBody LessonRequest lessonRequest) {
        return lessonService.saveLesson(courseId,lessonRequest);
    }

    @GetMapping("/{lessonId}")
    public LessonResponse getLesson(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

    @GetMapping("/all/{courseId}")
    public List<LessonResponse> getAllLessons(@PathVariable Long courseId) {
        return lessonService.getAllLessons(courseId);
    }

    @PutMapping("/{lessonId}")
    public LessonResponse update(@PathVariable Long lessonId,
                                 @RequestBody LessonRequest lessonRequest) {
        return lessonService.updateLesson(lessonId,lessonRequest);
    }

    @DeleteMapping("/{lessonId}")
    public  LessonResponse deleteLesson(@PathVariable Long lessonId) {
        return lessonService.deleteLessonById(lessonId);
    }
}
