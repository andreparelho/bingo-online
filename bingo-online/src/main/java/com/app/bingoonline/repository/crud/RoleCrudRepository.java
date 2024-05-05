package com.app.bingoonline.repository.crud;

import com.app.bingoonline.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleCrudRepository extends CrudRepository<RoleEntity, Long>  {
    RoleEntity findByName(String name);
}
