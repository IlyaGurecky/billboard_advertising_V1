package com.guretsky_tsarionok.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule extends Entity {
    String name;
    long time;
    List<Advertising> advertisingList;
    User user;
    Device device;
    DeviceGroup group;
}
