package com.app.bingoonline.repository;

import com.app.bingoonline.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<UserEntity> findByUsername(String username);
    UserEntity save(UserEntity userEntity);
}
