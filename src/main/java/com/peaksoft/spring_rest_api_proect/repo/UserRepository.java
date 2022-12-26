package com.peaksoft.spring_rest_api_proect.repo;

import com.peaksoft.spring_rest_api_proect.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email = :email")
    User findByEmail(String email);

    @Query("select count(u) from User u")
    Long countOfUsers();
}