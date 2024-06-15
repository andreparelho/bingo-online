package com.app.bingoonline.user.service.impl;

import com.app.bingoonline.login.dto.request.LoginRequest;
import com.app.bingoonline.user.entity.RoleEntity;
import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.exception.UserAlreadyExistsException;
import com.app.bingoonline.user.exception.UserNotFoundException;
import com.app.bingoonline.user.mapper.UserMapper;
import com.app.bingoonline.contest.dto.request.CreateUserRequest;
import com.app.bingoonline.user.dto.request.UserUpdateRequest;
import com.app.bingoonline.user.repository.RoleRepository;
import com.app.bingoonline.user.repository.UserRepository;
import com.app.bingoonline.infrastructure.config.security.jwt.JwtService;
import com.app.bingoonline.user.service.UserService;
import com.app.bingoonline.infrastructure.util.LogUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.app.bingoonline.user.constant.UserConstant.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private BCryptPasswordEncoder passwordEncoder;
    private static final LogUtil logger = new LogUtil();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           JwtService jwtService, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(CreateUserRequest createUserRequest) {
        this.logger.createLog(getClass().getName(), "createUser", "creating user", createUserRequest.username());

        String user = createUserRequest.username();
        Optional<UserEntity> userResponse = this.userRepository.findByUsername(user);

        if (userResponse.isPresent()){
            this.logger.createLogError("createUser", "user already exists", createUserRequest.username());
            throw new UserAlreadyExistsException("user already exists, please change username");
        }

        String encryptedPassword = this.jwtService.encodePassword(createUserRequest.password());
        CreateUserRequest request = new CreateUserRequest(createUserRequest.username(), encryptedPassword);
        RoleEntity roleName = this.roleRepository.findByName(RoleEntity.Values.BASIC.name());

        if (roleName == null) {
            this.logger.createLogError("createUser", "role not found", RoleEntity.Values.BASIC.name());
            throw new RuntimeException("Role not found");
        }

        UserEntity userEntity = this.userMapper.dtoToEntity(request);
        userEntity.setRoles(Set.of(roleName));

        this.logger.createLog(getClass().getName(), "createUser", "user created with success", createUserRequest.username());
        this.userRepository.save(userEntity);
    }

    @Override
    public void updateUser(UUID id, UserUpdateRequest userUpdateRequest) {
        Optional<UserEntity> user = this.userRepository.findById(id);

        if (!user.isPresent()){
            this.logger.createLogError("updateUser", "user not found", null);
            throw new UserNotFoundException("user not found");
        }
        this.logger.createLog(getClass().getName(), "updateUser", "updating user", user.get().getUsername());

        String encryptedPassword = this.jwtService.encodePassword(userUpdateRequest.password());

        UserEntity entity = this.userMapper.dtoToEntity(userUpdateRequest);

        UserEntity updatedEntity = UserEntity.builder()
                .id(id)
                .username(entity.getUsername())
                .password(encryptedPassword)
                .build();


        UserEntity userUpdated = this.userRepository.updateUser(updatedEntity);
        this.logger.createLog(getClass().getName(), "updateUser", "user update with success", userUpdated.getUsername());
    }

    @Override
    public Optional<UserEntity> findUserById(UUID uuid) {
        Optional<UserEntity> user = Optional.ofNullable(this.userRepository.findById(uuid)
                .orElseThrow(() -> new UserNotFoundException("User not found")));

        return user;
    }

    @Override
    public void deleteUser(UUID userId) {
        Optional<UserEntity> user = this.userRepository.findById(userId);

        if(user.isEmpty()){
            throw new UserNotFoundException("user not found");
        }

        UserEntity userEntity = this.userMapper.optionalToEntity(user);

        this.userRepository.deleteUser(userEntity);
    }

    @Override
    public Boolean checkPasswordIsValid(LoginRequest userLoginPassword, Optional<UserEntity> userPassword) throws UserNotFoundException {
        Boolean password = this.passwordEncoder.matches(userLoginPassword.password(), userPassword.get().getPassword());

        if (!password){
            throw new UserNotFoundException(PASSWORD_INVALID);
        }

        return true;
    }
}
