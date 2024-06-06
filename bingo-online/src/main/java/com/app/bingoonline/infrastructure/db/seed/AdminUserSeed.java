package com.app.bingoonline.infrastructure.db.seed;

import com.app.bingoonline.user.entity.RoleEntity;
import com.app.bingoonline.user.entity.UserEntity;
import com.app.bingoonline.user.repository.crud.RoleCrudRepository;
import com.app.bingoonline.user.repository.crud.UserCrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminUserSeed {
    private final UserCrudRepository userCrudRepository;
    private final RoleCrudRepository roleCrudRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserSeed(UserCrudRepository userCrudRepository, RoleCrudRepository roleCrudRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userCrudRepository = userCrudRepository;
        this.roleCrudRepository = roleCrudRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createAdminUserSeed(){
        var user = this.userCrudRepository.findByUsername("admin");

        if (user != null){
            return;
        }

        var roleAdmin = this.roleCrudRepository.findByName(RoleEntity.Values.ADMIN.name());
        UserEntity adminUser = UserEntity.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .roles(Set.of(roleAdmin))
                .build();

        this.userCrudRepository.save(adminUser);
    }
}
