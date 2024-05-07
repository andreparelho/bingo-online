package com.app.bingoonline.db.seed;

import com.app.bingoonline.entity.RoleEntity;
import com.app.bingoonline.repository.crud.RoleCrudRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleSeed {

    private final RoleCrudRepository roleCrudRepository;

    public RoleSeed(RoleCrudRepository roleCrudRepository) {
        this.roleCrudRepository = roleCrudRepository;
    }

    public void createRoleSeed(){
        var roleAdmin = this.roleCrudRepository.findByName(RoleEntity.Values.ADMIN.name());
        var roleBasic = this.roleCrudRepository.findByName(RoleEntity.Values.ADMIN.name());

        if (roleAdmin != null && roleBasic != null){
            return;
        }

        RoleEntity adminRole = RoleEntity.builder()
                .name(RoleEntity.Values.ADMIN.name())
                .build();

        RoleEntity basicRole = RoleEntity.builder()
                .name(RoleEntity.Values.BASIC.name())
                .build();

        this.roleCrudRepository.save(adminRole);
        this.roleCrudRepository.save(basicRole);
    }
}
