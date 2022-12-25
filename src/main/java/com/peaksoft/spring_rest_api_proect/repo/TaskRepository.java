package com.peaksoft.spring_rest_api_proect.repo;

import com.peaksoft.spring_rest_api_proect.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("select t from  Task t where t.lesson.id=:lessonId")
     List<Task> getAllTaskByLessonId(Long lessonId);
}