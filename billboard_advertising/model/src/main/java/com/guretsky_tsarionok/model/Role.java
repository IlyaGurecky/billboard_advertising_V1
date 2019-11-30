package com.guretsky_tsarionok.model;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ADMIN"),
    DEVICE_OWNER("DEVICE_OWNER");

    private String name;

    Role(String roleName) {
        this.name = roleName;
    }
}
