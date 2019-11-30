package com.guretsky_tsarionok.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @Enumerated(value = EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<Device> devices;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<Advertising> advertisingList;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<Schedule> schedules;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<DeviceGroup> deviceGroups;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @JsonIgnore
    @LazyCollection(LazyCollectionOption.TRUE)
    List<Log> logs;
}
