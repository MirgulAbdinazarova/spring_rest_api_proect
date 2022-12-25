package com.peaksoft.spring_rest_api_proect.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {

    private Long id;

    private String taskName;

    private String taskText;

    private int deadline;
}
