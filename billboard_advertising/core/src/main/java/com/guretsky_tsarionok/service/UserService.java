package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.model.User;

import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> findByUsername(String username);
}
