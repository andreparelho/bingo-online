package com.app.bingoonline.user.repository.crud;

import com.app.bingoonline.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserCrudRepository extends CrudRepository<UserEntity, UUID>  {
    UserEntity findByUsername(String username);
}
