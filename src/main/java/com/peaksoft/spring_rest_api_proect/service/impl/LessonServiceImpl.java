package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.LessonRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.LessonResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.LessonRequest;
import com.peaksoft.spring_rest_api_proect.dto.LessonResponse;
import com.peaksoft.spring_rest_api_proect.entities.Course;
import com.peaksoft.spring_rest_api_proect.entities.Lesson;
import com.peaksoft.spring_rest_api_proect.repo.CourseRepository;
import com.peaksoft.spring_rest_api_proect.repo.LessonRepository;
import com.peaksoft.spring_rest_api_proect.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private  final LessonRepository lessonRepository;
    private  final CourseRepository courseRepository;
    private final LessonRequestConverter lessonRequestConverter;
    private  final LessonResponseConverter lessonResponseConverter;

    @Override
    public LessonResponse saveLesson(Long courseId, LessonRequest lessonRequest) {
        Course course =courseRepository.findById(courseId).get();
        Lesson lesson = lessonRequestConverter.create(lessonRequest);
        lesson.setCourse(course);
        course.addLesson(lesson);
        lessonRepository.save(lesson);
        return lessonResponseConverter.viewLesson(lesson);
    }

    @Override
    public LessonResponse getLessonById(Long lessonId) {
        Lesson lesson =lessonRepository.findById(lessonId).get();
        return lessonResponseConverter.viewLesson(lesson);
    }

    @Override
    public List<LessonResponse> getAllLessons(Long courseId) {
        return lessonResponseConverter.view(lessonRepository.getAllLessonByCourseId(courseId));
    }

    @Override
    public LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lessonRequestConverter.update(lesson,lessonRequest);
        return lessonResponseConverter.viewLesson(lessonRepository.save(lesson));
    }

    @Override
    public LessonResponse deleteLessonById(Long lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        lessonRepository.delete(lesson);
        return lessonResponseConverter.viewLesson(lesson);
    }
}
