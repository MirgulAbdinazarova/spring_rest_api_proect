package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.TaskRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.TaskResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.TaskRequest;
import com.peaksoft.spring_rest_api_proect.dto.TaskResponse;
import com.peaksoft.spring_rest_api_proect.entities.Lesson;
import com.peaksoft.spring_rest_api_proect.entities.Task;
import com.peaksoft.spring_rest_api_proect.repo.LessonRepository;
import com.peaksoft.spring_rest_api_proect.repo.TaskRepository;
import com.peaksoft.spring_rest_api_proect.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private  final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;
    private final TaskRequestConverter taskRequestConverter;
    private  final TaskResponseConverter taskResponseConverter;

    @Override
    public TaskResponse saveTask(Long lessonId, TaskRequest taskRequest) {
        Lesson lesson = lessonRepository.findById(lessonId).get();
        Task task = taskRequestConverter.create(taskRequest);
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public List<TaskResponse> getAllTaskByLessonId(Long lessonId) {
        return taskResponseConverter.view(taskRepository.getAllTaskByLessonId(lessonId));
    }

    @Override
    public TaskResponse getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId).get();
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse updateTask(Long taskId,TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId).get();
        taskRequestConverter.update(task,taskRequest);
        return taskResponseConverter.viewTask(task);
    }

    @Override
    public TaskResponse deleteTask(Long taskId) {
        Task task =taskRepository.findById(taskId).get();
        taskRepository.delete(task);
        return taskResponseConverter.viewTask(task);
    }
}
