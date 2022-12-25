package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.TaskRequest;
import com.peaksoft.spring_rest_api_proect.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse saveTask (Long lessonId, TaskRequest taskRequest);

    List<TaskResponse> getAllTaskByLessonId(Long lessonId);

    TaskResponse getTaskById(Long taskId);

    TaskResponse updateTask(Long taskId,TaskRequest taskRequest);

    TaskResponse deleteTask(Long taskId);


}
