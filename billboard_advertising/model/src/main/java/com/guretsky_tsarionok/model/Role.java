package com.guretsky_tsarionok.model;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("admin"),
    DEVICE_OWNER("deviceOwner");

    private String name;

    Role(String roleName) {
        this.name = roleName;
    }
}