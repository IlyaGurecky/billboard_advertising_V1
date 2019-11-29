package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.Role;
import com.guretsky_tsarionok.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> getByRole(Role role);
}
