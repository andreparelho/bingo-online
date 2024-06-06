package com.app.bingoonline.user.repository;

import com.app.bingoonline.user.entity.RoleEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository {
    RoleEntity findByName(String name);
}
