package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.LessonRequest;
import com.peaksoft.spring_rest_api_proect.dto.LessonResponse;

import java.util.List;

public interface LessonService {

    LessonResponse saveLesson(Long courseId, LessonRequest lessonRequest);

    LessonResponse getLessonById(Long lessonId);

    List<LessonResponse> getAllLessons(Long courseId);

    LessonResponse updateLesson(Long lessonId,LessonRequest lessonRequest);

    LessonResponse deleteLessonById(Long lessonId);
}
