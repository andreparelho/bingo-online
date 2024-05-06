package com.app.bingoonline.security.config;

import com.app.bingoonline.entity.RoleEntity;
import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.repository.RoleRepository;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.repository.crud.RoleCrudRepository;
import com.app.bingoonline.util.LogUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private RoleRepository roleRepository;
    private RoleCrudRepository roleCrudRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private static final LogUtil logger = new LogUtil();

    public AdminUserConfig(RoleRepository roleRepository, RoleCrudRepository roleCrudRepository,
                           UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.roleCrudRepository = roleCrudRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        var roleAdmin = roleRepository.findByName(RoleEntity.Values.ADMIN.name());

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    logger.createLog(getClass().getSimpleName(), "run", "admin already created", null);
                },
                () -> {
                    logger.createLog(getClass().getSimpleName(), "run", "creating admin user", null);

                    UserEntity user = UserEntity.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("123"))
                            .roles(Set.of(roleAdmin))
                            .build();

                    logger.createLog("run", "admin created", user.getUsername());

                    userRepository.save(user);
                }
        );
    }

}
