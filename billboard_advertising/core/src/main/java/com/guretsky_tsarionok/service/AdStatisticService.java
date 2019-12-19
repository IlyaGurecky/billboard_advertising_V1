package com.guretsky_tsarionok.service;

import com.guretsky_tsarionok.model.AdvertisingStatistic;

import java.util.List;

public interface AdStatisticService extends CrudService<AdvertisingStatistic> {
    List<AdvertisingStatistic> getAllByAdId(long adId);
}
