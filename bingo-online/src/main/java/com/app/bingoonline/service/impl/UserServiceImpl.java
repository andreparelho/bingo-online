package com.app.bingoonline.service.impl;

import com.app.bingoonline.entity.RoleEntity;
import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.exception.user.UserAlreadyExistsException;
import com.app.bingoonline.mapper.UserMapper;
import com.app.bingoonline.model.request.CreateUserRequest;
import com.app.bingoonline.repository.RoleRepository;
import com.app.bingoonline.repository.crud.UserCrudRepository;
import com.app.bingoonline.service.JwtService;
import com.app.bingoonline.service.UserService;
import com.app.bingoonline.util.LogUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserCrudRepository userCrudRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private static final LogUtil logger = new LogUtil();

    public UserServiceImpl(UserCrudRepository userCrudRepository, RoleRepository roleRepository, JwtService jwtService, UserMapper userMapper) {
        this.userCrudRepository = userCrudRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        this.logger.createLog(getClass().getName(), "createUser", "creating user", createUserRequest.username());

        String user = createUserRequest.username();
        Optional<UserEntity> userResponse = Optional.ofNullable(this.userCrudRepository.findByUsername(user));

        if (userResponse.isPresent()){
            this.logger.createLogError("createUser", "user already exists", createUserRequest.username());
            throw new UserAlreadyExistsException("user already exists, please change username");
        }

        String encryptedPassword = this.jwtService.encodePassword(createUserRequest.password());
        CreateUserRequest request = new CreateUserRequest(createUserRequest.username(), encryptedPassword);
        RoleEntity roleName = this.roleRepository.findByName(RoleEntity.Values.BASIC.name());

        UserEntity userEntity = this.userMapper.dtoToEntity(request);
        userEntity.setRoles(Set.of(roleName));

        this.logger.createLog(getClass().getName(), "createUser", "user created with success", createUserRequest.username());
        this.userCrudRepository.save(userEntity);
    }
}
