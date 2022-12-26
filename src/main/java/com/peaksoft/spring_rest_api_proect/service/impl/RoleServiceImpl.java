package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.entities.Role;
import com.peaksoft.spring_rest_api_proect.repo.RoleRepository;
import com.peaksoft.spring_rest_api_proect.service.RoleService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }
}
