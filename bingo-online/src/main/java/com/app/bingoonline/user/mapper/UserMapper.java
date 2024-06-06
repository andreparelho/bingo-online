package com.app.bingoonline.user.mapper;

import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.contest.dto.request.CreateUserRequest;
import com.app.bingoonline.user.dto.request.UserUpdateRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {
    public UserEntity dtoToEntity(CreateUserRequest createUserRequest){
        return UserEntity.builder()
                .username(createUserRequest.username())
                .password(createUserRequest.password())
                .build();
    }

    public UserEntity dtoToEntity(UserUpdateRequest userUpdateRequest){
        return UserEntity.builder()
                .username(userUpdateRequest.username())
                .password(userUpdateRequest.password())
                .build();
    }

    public UserEntity optionalToEntity(Optional<UserEntity> userEntityOptional){
        return UserEntity.builder()
                .id(userEntityOptional.get().getId())
                .username(userEntityOptional.get().getUsername())
                .password(userEntityOptional.get().getPassword())
                .roles(userEntityOptional.get().getRoles())
                .build();
    }
}
