package com.peaksoft.spring_rest_api_proect.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(name = "course_gen", sequenceName = "course_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_gen")
    private Long id;

    @Column(name = "course_name", length = 50000)
    private String courseName;

    @Column(name = "duration", length = 50000)

    private int duration;
    //
    @Column(name = "discription", length = 50000)
    private String description;

    @ManyToOne(cascade = {DETACH, REFRESH, MERGE}, fetch = FetchType.EAGER)
    private Company company;

    @ManyToMany(cascade = ALL, fetch = FetchType.LAZY,mappedBy = "courses")
    private List<Group> groups;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Instructor> instructors;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Lesson> lessons;
    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);
    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }

    public void addInstructor(Instructor instructor) {
        if (instructors == null) {
            instructors = new ArrayList<>();
        }
        instructors.add(instructor);
    }
}