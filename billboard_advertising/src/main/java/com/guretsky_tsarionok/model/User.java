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
public class User extends Entity {
    String userName;
    Role role;
    List<Device> devices;
    List<Advertising> advertisingList;
    List<Schedule> schedules;
}
