package com.peaksoft.spring_rest_api_proect.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name = "instructor_seq",
            sequenceName = "instructor_seq",
            allocationSize = 1)
    private Long id;
    @Column(name = "first_name",nullable = false)

    private String firstName;

    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Email
    @Column(name = "email")
    private String email;

    private String password;

    @Column(name = "specialization")
    private String specialization;

    private Long count=0L;

    @ManyToOne(cascade = {DETACH,REFRESH,MERGE},fetch = FetchType.EAGER)
    private Course course;

    public void plus() {
        count++;
    }

    public void minus() {
        count--;
    }


}