package com.peaksoft.spring_rest_api_proect.repo;

import com.peaksoft.spring_rest_api_proect.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.roleName = :name")
    Role findByName(String name);

    @Query("select count(r) from Role r")
    Long countOfRoles();

}