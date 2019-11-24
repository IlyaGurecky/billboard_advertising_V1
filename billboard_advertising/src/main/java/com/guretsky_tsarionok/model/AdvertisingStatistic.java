package com.guretsky_tsarionok.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdvertisingStatistic extends Entity {
    Instant time;
    Advertising advertising;
    String content;
}
