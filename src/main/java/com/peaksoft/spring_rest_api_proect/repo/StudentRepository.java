package com.peaksoft.spring_rest_api_proect.repo;

import com.peaksoft.spring_rest_api_proect.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("select s from Student s where s.group.id=:groupId")
    List<Student> getAllStudentByGroupId(Long groupId);

    @Query("select s from Student s where s.email = :email")
    Student findByEmail(String email);
}