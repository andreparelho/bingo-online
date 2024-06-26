package com.app.bingoonline.user.repository.impl;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.exception.UserNotFoundException;
import com.app.bingoonline.user.repository.UserRepository;
import com.app.bingoonline.user.repository.crud.UserCrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepositoryImpl implements UserRepository {
    private final UserCrudRepository userCrudRepository;

    public UserRepositoryImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        Optional<UserEntity> user = Optional.ofNullable(userCrudRepository.findByUsername(username));
        return user;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userCrudRepository.save(userEntity);
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return this.userCrudRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(UUID id) {
        Optional<UserEntity> user = Optional.ofNullable(this.userCrudRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("user not found")
        ));
        return user;
    }

    @Override
    public void deleteUser(UserEntity userEntity) {
        this.userCrudRepository.delete(userEntity);
    }
}
