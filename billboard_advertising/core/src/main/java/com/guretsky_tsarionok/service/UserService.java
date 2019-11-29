package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.model.User;
import com.guretsky_tsarionok.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IService<User> {

    private final UserRepository userRepository;

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
    public User add(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public User update(User obj) {
        return userRepository.save(obj);
    }

    @Override
    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
