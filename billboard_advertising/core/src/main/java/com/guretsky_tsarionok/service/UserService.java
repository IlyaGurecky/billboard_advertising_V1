package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.dto.UserDto;
import com.guretsky_tsarionok.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends CrudService<User> {
    Optional<User> findByUsername(String username);
    List<User> getDeviceOwners();
    User save(UserDto dto);
}
