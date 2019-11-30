package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisingRepository extends JpaRepository<Advertising, Long> {
    List<Advertising> getByUserId(long id);
}
