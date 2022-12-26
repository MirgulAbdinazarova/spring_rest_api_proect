package com.peaksoft.spring_rest_api_proect.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "companies")
public class Company {

    @Id
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    private Long id;

    @Column(name = "name")
    private String companyName;

    @Column(name="country")
    private String locatedCountry;

    private  Long count=0L;


    @OneToMany(cascade = ALL, fetch = FetchType.LAZY,mappedBy = "company")
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course){
        if(courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }

    public void plusStudent(Course course1) {
        for (Group group : course1.getGroups()) {
            for (Student student : group.getStudents()) {
                count++;
            }
        }
    }

    public void minusStudent(Course course1) {
        for (Group group : course1.getGroups()) {
            for (Student student : group.getStudents()) {
                count--;
            }
        }
    }

    public void plus() {
        count++;
    }

    public void minus() {
        count--;
    }
}