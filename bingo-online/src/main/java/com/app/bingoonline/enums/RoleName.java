package com.app.bingoonline.enums;

public enum RoleName {
    ROLE_SELLER(2l),
    ROLE_ADMINISTRATOR(1l);

    long roleId;

    RoleName(long roleId){
        this.roleId = roleId;
    }

    public long getRoleId() {
        return roleId;
    }
}
