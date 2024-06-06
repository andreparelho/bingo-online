package com.app.bingoonline.user.repository;

import com.app.bingoonline.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository {
    Optional<UserEntity> findByUsername(String username);
    UserEntity save(UserEntity userEntity);
    UserEntity updateUser(UserEntity userEntity);
    Optional<UserEntity> findById(UUID id);
    void deleteUser(UserEntity userEntity);
}
