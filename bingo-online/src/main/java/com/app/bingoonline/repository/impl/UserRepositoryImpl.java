package com.app.bingoonline.repository.impl;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserNotFoundException;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.repository.crud.UserCrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
