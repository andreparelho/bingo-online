package com.app.bingoonline.infrastructure.db.run;

import com.app.bingoonline.infrastructure.db.seed.AdminUserSeed;
import com.app.bingoonline.infrastructure.db.seed.RoleSeed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DatabaseSeedInitializer implements CommandLineRunner {
    private final RoleSeed roleSeed;
    private final AdminUserSeed adminUserSeed;

    public DatabaseSeedInitializer(RoleSeed roleSeed, AdminUserSeed adminUserSeed) {
        this.roleSeed = roleSeed;
        this.adminUserSeed = adminUserSeed;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        this.roleSeed.createRoleSeed();
        this.adminUserSeed.createAdminUserSeed();
    }
}
