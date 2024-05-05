package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.RoleEntity;
import com.app.bingoonline.repository.RoleRepository;
import com.app.bingoonline.repository.crud.RoleCrudRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleCrudRepository roleCrudRepository;

    public RoleRepositoryImpl(RoleCrudRepository roleCrudRepository) {
        this.roleCrudRepository = roleCrudRepository;
    }


    @Override
    public RoleEntity findByName(String name) {
        return this.roleCrudRepository.findByName(name);
    }
}
