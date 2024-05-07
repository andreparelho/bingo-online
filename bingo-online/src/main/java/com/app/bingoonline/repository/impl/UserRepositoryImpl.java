package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserNotFoundException;
import com.app.bingoonline.model.request.UserUpdateRequest;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.repository.crud.UserCrudRepository;
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
        Optional<UserEntity> user = Optional.ofNullable((Optional.ofNullable(userCrudRepository.findByUsername(username))
                .orElseThrow(() -> new UserNotFoundException("User not found"))));
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
}
