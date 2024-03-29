package com.guretsky_tsarionok.service.impl;

import com.guretsky_tsarionok.dto.UserDto;
import com.guretsky_tsarionok.model.Role;
import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.repository.UserRepository;
import com.guretsky_tsarionok.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getDeviceOwners() {
        return userRepository.getByRole(Role.DEVICE_OWNER);
    }

    @Override
    public User save(UserDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isEmpty()) {
            return add(User.builder()
                    .username(dto.getUsername())
                    .role(Role.DEVICE_OWNER)
                    .build());
        }
        return null;
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User add(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User update(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public boolean deleteById(long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
