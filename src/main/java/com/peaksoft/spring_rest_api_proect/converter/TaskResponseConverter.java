package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.TaskResponse;
import com.peaksoft.spring_rest_api_proect.entities.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskResponseConverter {

    public TaskResponse viewTask (Task task) {
        if(task == null) {
            return  null;
        }
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(task.getId());
        taskResponse.setTaskName(task.getTaskName());
        taskResponse.setTaskText(task.getTaskText());
        taskResponse.setDeadline(task.getDeadline());
        return taskResponse;
    }

    public List<TaskResponse> view (List<Task>tasks) {
        List<TaskResponse> taskResponses = new ArrayList<>();
        for (Task task:tasks) {
            taskResponses.add(viewTask(task));
        }
        return taskResponses;
    }
}
