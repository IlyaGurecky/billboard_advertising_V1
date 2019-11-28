package com.guretsky_tsarionok.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends EntityBase {
    @NotBlank
    String username;

    @NotBlank
    @Enumerated(value = EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    List<Device> devices;

    @OneToMany(mappedBy = "user")
    List<Advertising> advertisingList;

    @OneToMany(mappedBy = "user")
    List<Schedule> schedules;

    @OneToMany(mappedBy = "user")
    List<DeviceGroup> deviceGroups;
}
