package com.app.bingoonline.repository;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.model.request.UserUpdateRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<UserEntity> findByUsername(String username);
    UserEntity save(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity);
    Optional<UserEntity> findById(UUID id);
}
