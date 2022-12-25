package com.peaksoft.spring_rest_api_proect.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

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


    @OneToMany(cascade = ALL, fetch = FetchType.LAZY,mappedBy = "company")
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course){
        if(courses==null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }
}