package com.app.bingoonline.repository;

import com.app.bingoonline.entity.RoleEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository {
    RoleEntity findByName(String name);
}
