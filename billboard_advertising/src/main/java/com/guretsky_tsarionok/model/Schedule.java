package com.guretsky_tsarionok.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@javax.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends Entity {
    private String name;
    private long time;
    private List<Advertising> advertisingList;
    private User user;
    private Device device;
    private DeviceGroup group;
}
