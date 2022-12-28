package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.TaskRequest;
import com.peaksoft.spring_rest_api_proect.dto.TaskResponse;
import com.peaksoft.spring_rest_api_proect.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/{lessonId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public TaskResponse saveTask(@PathVariable Long lessonId,
                                 @RequestBody TaskRequest taskRequest) {
        return taskService.saveTask(lessonId,taskRequest);
    }

    @GetMapping("/all/{lessonId}")
    @PreAuthorize("isAuthenticated()")
    public List<TaskResponse> getAllTasks(@PathVariable Long lessonId) {
        return taskService.getAllTaskByLessonId(lessonId);
    }

    @GetMapping("{taskId}")
    @PreAuthorize("isAuthenticated()")
    public TaskResponse getTask (@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public TaskResponse updateTask (@PathVariable Long taskId,
                                    @RequestBody TaskRequest taskRequest) {
        return taskService.updateTask(taskId,taskRequest);
    }

    @DeleteMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_INSTRUCTOR')")
    public TaskResponse deleteTask(@PathVariable Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
