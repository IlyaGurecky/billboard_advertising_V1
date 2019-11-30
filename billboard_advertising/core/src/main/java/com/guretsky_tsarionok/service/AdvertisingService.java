package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.dto.AdvertisingDto;
import com.guretsky_tsarionok.model.Advertising;

import java.util.List;

public interface AdvertisingService extends CrudService<Advertising> {
    List<Advertising> getByUserId(long id);
    Advertising save(AdvertisingDto dto, Long userId);
}
