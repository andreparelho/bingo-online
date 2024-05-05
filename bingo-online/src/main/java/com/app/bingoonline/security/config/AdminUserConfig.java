package com.app.bingoonline.security.config;

import com.app.bingoonline.entity.RoleEntity;
import com.app.bingoonline.entity.UserEntity;
import com.app.bingoonline.repository.RoleRepository;
import com.app.bingoonline.repository.UserRepository;
import com.app.bingoonline.repository.crud.RoleCrudRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class AdminUserConfig implements CommandLineRunner {
    private RoleRepository roleRepository;
    private RoleCrudRepository roleCrudRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

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

        RoleEntity adminRole = roleCrudRepository.findByName(RoleEntity.Values.ADMIN.name());
        if (adminRole == null) {
            adminRole = RoleEntity.builder()
                    .name(RoleEntity.Values.ADMIN.name())
                    .build();
            roleCrudRepository.save(adminRole);
            System.out.println("role admin created" + "  " + roleCrudRepository.save(adminRole));
        }


        RoleEntity sellerRole = roleCrudRepository.findByName(RoleEntity.Values.BASIC.name());
        if (sellerRole == null) {
            sellerRole = RoleEntity.builder()
                        .name(RoleEntity.Values.BASIC.name())
                    .build();
            roleCrudRepository.save(sellerRole);
            System.out.println("role seller created");
        }

        UserEntity user = UserEntity.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .roles(Set.of(adminRole))
                .build();

        userRepository.save(user);
        System.out.println("user created");
    }

}
