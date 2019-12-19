package com.guretsky_tsarionok.controller;

import com.guretsky_tsarionok.model.AdvertisingStatistic;
import com.guretsky_tsarionok.service.AdStatisticService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/ad_statistic")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdvertisingStatisticController {

    AdStatisticService service;

    @GetMapping
    public List<AdvertisingStatistic> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public AdvertisingStatistic getById(@PathVariable long id) {
        return service.findById(id).orElse(null);
    }

    @GetMapping(value = "/ad/{adId}")
    public List<AdvertisingStatistic> getAllByAdId(@PathVariable long adId) {
        return service.getAllByAdId(adId);
    }

    @PostMapping
    public AdvertisingStatistic add(@RequestBody AdvertisingStatistic statistic) {
        return service.add(statistic);
    }

    @PatchMapping
    public AdvertisingStatistic update(@RequestBody AdvertisingStatistic statistic) {
        return service.update(statistic);
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteById(@PathVariable long id) {
        return service.deleteById(id);
    }
}
