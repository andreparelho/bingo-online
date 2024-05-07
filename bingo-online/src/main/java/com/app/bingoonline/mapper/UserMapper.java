package com.app.bingoonline.mapper;

import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.model.request.CreateUserRequest;
import com.app.bingoonline.model.request.UserUpdateRequest;
import org.springframework.stereotype.Component;

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
}
