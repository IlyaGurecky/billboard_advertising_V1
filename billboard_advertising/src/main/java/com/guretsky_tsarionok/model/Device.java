package com.guretsky_tsarionok.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Device extends Entity {
    Schedule schedule;
    DeviceGroup group;
    User user;
}
