package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.model.Log;

import java.util.List;

public interface LogService extends CrudService<Log> {
    List<Log> findByUserId(long userId);
}
