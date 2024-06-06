package com.app.bingoonline.user.repository.crud;

import com.app.bingoonline.user.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepository extends CrudRepository<RoleEntity, Long>  {
    RoleEntity findByName(String name);
}
