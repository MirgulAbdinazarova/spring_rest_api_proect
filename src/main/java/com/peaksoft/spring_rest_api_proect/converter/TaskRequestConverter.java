package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.TaskRequest;
import com.peaksoft.spring_rest_api_proect.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskRequestConverter {

    public Task create (TaskRequest taskRequest) {
        if(taskRequest == null) {
            return  null;
        }
        Task task = new Task();
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
        return task;
    }

    public  void update (Task task,TaskRequest taskRequest) {
        task.setTaskName(taskRequest.getTaskName());
        task.setTaskText(taskRequest.getTaskText());
        task.setDeadline(taskRequest.getDeadline());
    }
}
