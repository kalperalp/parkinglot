package com.huawei.parkinglot.entity;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "parking_area_id")
    private Integer parkingAreaId;

    @Column(name = "lower_hour")
    private Integer lowerHour;

    @Column(name = "upper_hour")
    private Integer upperHour;
    @Column(name = "hourly_pricing")
    private Double hourlyPricing;



}
