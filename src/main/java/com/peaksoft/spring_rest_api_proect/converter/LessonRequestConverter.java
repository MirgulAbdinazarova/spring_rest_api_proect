package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.LessonRequest;
import com.peaksoft.spring_rest_api_proect.entities.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonRequestConverter {

    public Lesson create(LessonRequest lessonRequest) {
        if(lessonRequest==null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setLessonName(lessonRequest.getLessonName());
        return lesson;
    }

    public void update(Lesson lesson,LessonRequest lessonRequest) {
        if(lessonRequest.getLessonName() != null) {
            lesson.setLessonName(lessonRequest.getLessonName());
        }
    }
}
