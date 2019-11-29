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

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "advertising")
public class Advertising extends EntityBase {
    @NotBlank String name;
    @NotBlank String contentPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "advertisingList")
    @ToString.Exclude
    @JsonIgnore
    List<Schedule> schedules;

    @OneToMany(mappedBy = "advertising")
    @ToString.Exclude
    @JsonIgnore
    List<AdvertisingStatistic> statistics;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "advertisingList")
    @ToString.Exclude
    @JsonIgnore
    List<Device> devices;
}
