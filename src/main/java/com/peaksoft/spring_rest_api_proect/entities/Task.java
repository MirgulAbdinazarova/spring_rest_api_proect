package com.peaksoft.spring_rest_api_proect.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq",
            sequenceName = "task_seq",
            allocationSize = 1)
    private Long id;

    private String taskName;

    private String taskText;

    private int deadline;

    @ManyToOne(cascade ={DETACH,REFRESH,MERGE},fetch = FetchType.EAGER)
    private Lesson lesson;
}