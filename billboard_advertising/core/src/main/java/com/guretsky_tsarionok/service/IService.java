package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.model.EntityBase;

import java.util.List;
import java.util.Optional;

public interface IService<T extends EntityBase> {
    List<T> getAll();

    Optional<T> findById(long id);

    T add(T obj);

    T update(T obj);

    void deleteById(long id);
}
