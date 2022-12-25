package com.peaksoft.spring_rest_api_proect.repo;

import com.peaksoft.spring_rest_api_proect.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}