package com.guretsky_tsarionok.repository;

import com.guretsky_tsarionok.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository <Device, Long> {
}
